package com.velocity.service;

import com.velocity.dto.CancellationPreviewResponse;
import com.velocity.dto.PriceCalculationRequest;
import com.velocity.dto.PriceCalculationResponse;
import com.velocity.model.*;

import java.util.ArrayList;
import java.util.List;

public class PricingService {
    public static final double GST_RATE = 0.05; // 5% GST on AC travel (exempt on SL & 2S)
    public static final double IRCTC_CONVENIENCE_FEE_AC = 35.40; // ₹30 + 18% GST = ₹35.40
    public static final double IRCTC_CONVENIENCE_FEE_NON_AC = 17.70; // ₹15 + 18% GST = ₹17.70
    public static final double IRCTC_CONVENIENCE_FEE = 35.40;

    private final TrainService trainService;

    public PricingService(TrainService trainService) {
        this.trainService = trainService;
    }

    public PriceCalculationResponse calculatePrice(PriceCalculationRequest request) {
        Train train = trainService.getTrain(request.getTrainNumber());
        if (train == null) {
            throw new IllegalArgumentException("Train not found: " + request.getTrainNumber());
        }

        TrainClass trainClass = train.getClasses().stream()
                .filter(c -> c.getId().equalsIgnoreCase(request.getClassId()))
                .findFirst()
                .orElse(train.getClasses().get(0));

        boolean isAcClass = !"SL".equalsIgnoreCase(trainClass.getId()) && !"2S".equalsIgnoreCase(trainClass.getId());
        double convenienceFee = isAcClass ? IRCTC_CONVENIENCE_FEE_AC : IRCTC_CONVENIENCE_FEE_NON_AC;
        double baseTicketPrice = trainClass.getPrice();
        double totalBaseFare = 0.0;
        int adultCount = 0;
        int childCount = 0;
        int seniorCount = 0;
        int infantCount = 0;

        List<PriceCalculationResponse.PriceLineItem> breakdown = new ArrayList<>();

        if (request.getPassengers() != null && !request.getPassengers().isEmpty()) {
            for (PriceCalculationRequest.PassengerItem p : request.getPassengers()) {
                String type = p.getPassengerType() != null ? p.getPassengerType().toUpperCase() : "ADULT";
                switch (type) {
                    case "CHILD":
                        totalBaseFare += (baseTicketPrice * 0.50); // 50% child concession with berth
                        childCount++;
                        break;
                    case "SENIOR_CITIZEN":
                        totalBaseFare += (baseTicketPrice * 0.60); // 40% senior citizen concession
                        seniorCount++;
                        break;
                    case "INFANT":
                        infantCount++; // Free below 5 years (no separate berth)
                        break;
                    default:
                        totalBaseFare += baseTicketPrice;
                        adultCount++;
                        break;
                }
            }
        } else {
            totalBaseFare = baseTicketPrice;
            adultCount = 1;
        }

        if (adultCount > 0) {
            breakdown.add(new PriceCalculationResponse.PriceLineItem(
                    "Base Ticket Fare (" + adultCount + " Adult" + (adultCount > 1 ? "s" : "") + ")",
                    baseTicketPrice * adultCount,
                    "₹" + String.format(java.util.Locale.US, "%.2f", baseTicketPrice * adultCount)
            ));
        }
        if (childCount > 0) {
            double childFare = (baseTicketPrice * 0.50) * childCount;
            breakdown.add(new PriceCalculationResponse.PriceLineItem(
                    "Child Fare (50% Concession x " + childCount + ")",
                    childFare,
                    "₹" + String.format(java.util.Locale.US, "%.2f", childFare)
            ));
        }
        if (seniorCount > 0) {
            double seniorFare = (baseTicketPrice * 0.60) * seniorCount;
            breakdown.add(new PriceCalculationResponse.PriceLineItem(
                    "Senior Citizen Fare (40% Concession x " + seniorCount + ")",
                    seniorFare,
                    "₹" + String.format(java.util.Locale.US, "%.2f", seniorFare)
            ));
        }
        if (infantCount > 0) {
            breakdown.add(new PriceCalculationResponse.PriceLineItem(
                    "Infant Passenger (Free without berth x " + infantCount + ")",
                    0.0,
                    "₹0.00"
            ));
        }

        // GST: 5% on AC travel, 0% on non-AC
        double gst = isAcClass ? (Math.round(totalBaseFare * GST_RATE * 100.0) / 100.0) : 0.0;
        if (isAcClass) {
            breakdown.add(new PriceCalculationResponse.PriceLineItem(
                    "GST (5% Indian Railways AC Service Tax)",
                    gst,
                    "₹" + String.format(java.util.Locale.US, "%.2f", gst)
            ));
        } else {
            breakdown.add(new PriceCalculationResponse.PriceLineItem(
                    "GST (Non-AC Travel - Exempt 0%)",
                    0.0,
                    "₹0.00"
            ));
        }

        // Convenience Fee
        breakdown.add(new PriceCalculationResponse.PriceLineItem(
                "IRCTC Convenience Fee (incl. PG & Digital Processing)",
                convenienceFee,
                "₹" + String.format(java.util.Locale.US, "%.2f", convenienceFee)
        ));

        double totalAmount = Math.round((totalBaseFare + gst + convenienceFee) * 100.0) / 100.0;

        return new PriceCalculationResponse(
                totalBaseFare,
                gst + convenienceFee,
                0.0,
                totalAmount,
                "₹",
                breakdown
        );
    }

    public CancellationPreviewResponse previewCancellation(Booking booking) {
        if (booking == null) {
            throw new IllegalArgumentException("Booking not found");
        }

        double totalPaid = booking.getTotalAmount();
        double convenienceFee = booking.getConvenienceFee() > 0 ? booking.getConvenienceFee() : IRCTC_CONVENIENCE_FEE;
        String classId = booking.getTravelClassId() != null ? booking.getTravelClassId().toUpperCase() : "CC";
        int passengerCount = (booking.getPassengers() != null && !booking.getPassengers().isEmpty())
                ? booking.getPassengers().size() : 1;

        // IRCTC Standard Flat Cancellation Charges (> 48 hrs before departure)
        double cancellationFeePerPassenger;
        switch (classId) {
            case "1A":
            case "EC":
            case "EA":
                cancellationFeePerPassenger = 240.0;
                break;
            case "2A":
            case "FC":
                cancellationFeePerPassenger = 200.0;
                break;
            case "3A":
            case "3E":
            case "CC":
                cancellationFeePerPassenger = 180.0;
                break;
            case "SL":
                cancellationFeePerPassenger = 120.0;
                break;
            case "2S":
                cancellationFeePerPassenger = 60.0;
                break;
            default:
                cancellationFeePerPassenger = 180.0;
                break;
        }

        double totalCancellationFee = cancellationFeePerPassenger * passengerCount;
        double clerkageFee = 0.0;
        if ("WAITLIST".equalsIgnoreCase(booking.getStatus()) || "RAC".equalsIgnoreCase(booking.getStatus())) {
            totalCancellationFee = 0.0;
            clerkageFee = 60.0 * passengerCount; // IRCTC RAC/WL clerkage charge is flat ₹60/passenger
        }

        double totalDeductions = totalCancellationFee + clerkageFee + convenienceFee;
        double refundAmount = Math.max(0.0, totalPaid - totalDeductions);
        refundAmount = Math.round(refundAmount * 100.0) / 100.0;

        return new CancellationPreviewResponse(
                booking.getPnr(),
                totalPaid,
                totalCancellationFee,
                clerkageFee,
                convenienceFee,
                refundAmount,
                "₹",
                "3 - 5 Banking Working Days"
        );
    }
}
