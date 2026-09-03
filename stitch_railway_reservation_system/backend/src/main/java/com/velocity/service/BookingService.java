package com.velocity.service;

import com.velocity.dto.CancellationPreviewResponse;
import com.velocity.dto.CreateBookingRequest;
import com.velocity.model.*;
import com.velocity.util.JsonUtils;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class BookingService {
    private static final String DATA_FILE_PATH = "backend/data/bookings_db.json";

    private final Map<String, Booking> bookings = new ConcurrentHashMap<>();
    private final TrainService trainService;
    private final PricingService pricingService;
    private final Random random = new Random();

    public BookingService(TrainService trainService, PricingService pricingService) {
        this.trainService = trainService;
        this.pricingService = pricingService;
        loadFromDisk();
    }

    private void loadFromDisk() {
        try {
            File file = new File(DATA_FILE_PATH);
            if (file.exists()) {
                String content = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
                if (content != null && !content.trim().isEmpty()) {
                    List<Object> list = JsonUtils.parseList(content);
                    if (list != null) {
                        for (Object obj : list) {
                            if (obj instanceof Map) {
                                String json = JsonUtils.toJson(obj);
                                Booking b = JsonUtils.fromJson(json, Booking.class);
                                if (b != null && b.getPnr() != null) {
                                    bookings.put(b.getPnr(), b);
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Note: Starting fresh bookings cache: " + e.getMessage());
        }
    }

    private synchronized void saveToDisk() {
        try {
            File dir = new File("backend/data");
            if (!dir.exists()) {
                dir.mkdirs();
            }
            List<Booking> list = new ArrayList<>(bookings.values());
            String json = JsonUtils.toJson(list);
            Files.write(new File(DATA_FILE_PATH).toPath(), json.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            System.err.println("Failed to persist bookings to disk: " + e.getMessage());
        }
    }

    public synchronized Booking createBooking(CreateBookingRequest request) {
        Train train = trainService.getTrain(request.getTrainNumber());
        if (train == null) {
            throw new IllegalArgumentException("Train not found: " + request.getTrainNumber());
        }

        TrainClass trainClass = train.getClasses().stream()
                .filter(c -> c.getId().equalsIgnoreCase(request.getClassId()))
                .findFirst()
                .orElse(train.getClasses().get(0));

        String pnr = generateIndianRailwaysPnr();
        Booking booking = new Booking();
        booking.setPnr(pnr);
        booking.setTrainNumber(train.getTrainNumber());
        booking.setTrainName(train.getName());
        booking.setTrainType(train.getTrainType());
        booking.setTrainRating(train.getRating());
        booking.setFromStationCode(train.getFromStationCode());
        booking.setFromStationName(train.getFromStationName());
        booking.setToStationCode(train.getToStationCode());
        booking.setToStationName(train.getToStationName());
        booking.setDepartureTime(train.getDepartureTime());
        booking.setArrivalTime(train.getArrivalTime());
        booking.setDeparturePlatform(train.getDeparturePlatform());
        booking.setArrivalPlatform(train.getArrivalPlatform());
        booking.setJourneyDate(request.getJourneyDate());
        booking.setTravelClass(trainClass.getName());
        booking.setTravelClassId(trainClass.getId());
        booking.setPaymentMode(request.getPaymentMode() != null ? request.getPaymentMode() : "UPI");
        booking.setContactEmail(request.getContactEmail());
        booking.setContactPhone(request.getContactPhone());

        String defaultCar = request.getCar() != null && !request.getCar().isEmpty() ? request.getCar() :
                (trainClass.getId().equals("EC") || trainClass.getId().equals("1A") ? "E1" : "C1");
        booking.setCar(defaultCar);

        List<Passenger> passengers = new ArrayList<>();
        List<String> seatNumbers = new ArrayList<>();
        double totalBaseFare = 0.0;

        int passIndex = 1;
        if (request.getPassengers() != null && !request.getPassengers().isEmpty()) {
            for (Passenger pReq : request.getPassengers()) {
                Passenger p = new Passenger();
                p.setId("P-" + System.currentTimeMillis() + "-" + (passIndex++));
                p.setFirstName(pReq.getFirstName());
                p.setLastName(pReq.getLastName());
                p.setAge(pReq.getAge() > 0 ? pReq.getAge() : 30);
                p.setGender(pReq.getGender() != null ? pReq.getGender() : "MALE");
                p.setBerthPreference(pReq.getBerthPreference() != null ? pReq.getBerthPreference() : "NO_PREFERENCE");
                p.setMealPreference(pReq.getMealPreference() != null ? pReq.getMealPreference() : "VEG");
                p.setPassengerType(pReq.getPassengerType() != null ? pReq.getPassengerType() : (p.getAge() >= 60 ? "SENIOR_CITIZEN" : (p.getAge() < 12 ? "CHILD" : "ADULT")));
                p.setCar(defaultCar);

                double fare = trainClass.getPrice();
                if ("CHILD".equalsIgnoreCase(p.getPassengerType())) fare *= 0.50;
                else if ("SENIOR_CITIZEN".equalsIgnoreCase(p.getPassengerType()) || p.getAge() >= 60) {
                    p.setPassengerType("SENIOR_CITIZEN");
                    fare *= 0.60;
                } else if ("INFANT".equalsIgnoreCase(p.getPassengerType())) fare = 0.0;
                p.setFare(fare);
                totalBaseFare += fare;

                String seatNum = (pReq.getSeatNumber() != null && !pReq.getSeatNumber().equalsIgnoreCase("Auto") && !pReq.getSeatNumber().trim().isEmpty())
                        ? pReq.getSeatNumber() : allocateSeatNumber(train, trainClass, passIndex);
                p.setSeatNumber(seatNum);
                p.setStatus("CONFIRMED");
                seatNumbers.add(seatNum);
                passengers.add(p);
            }
        }

        booking.setPassengers(passengers);
        booking.setSeatNumbers(seatNumbers);
        booking.setBaseFare(totalBaseFare);

        double gst = Math.round(totalBaseFare * PricingService.GST_RATE * 100.0) / 100.0;
        booking.setTaxesAndFees(gst);
        booking.setConvenienceFee(PricingService.IRCTC_CONVENIENCE_FEE);
        booking.setTotalAmount(Math.round((totalBaseFare + gst + PricingService.IRCTC_CONVENIENCE_FEE) * 100.0) / 100.0);
        booking.setCurrency("₹");
        booking.setStatus("CONFIRMED");
        booking.setCreatedAt(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
        booking.setQrCodeData("IRCTC:PNR=" + pnr + ":TRN=" + train.getTrainNumber() + ":DATE=" + request.getJourneyDate() + ":HASH=" + Integer.toHexString(pnr.hashCode()));

        bookings.put(pnr, booking);
        saveToDisk();

        return booking;
    }

    public synchronized CancellationPreviewResponse previewCancellation(String pnr) {
        Booking booking = bookings.get(pnr);
        if (booking == null) {
            throw new IllegalArgumentException("Booking not found with PNR: " + pnr);
        }
        return pricingService.previewCancellation(booking);
    }

    public synchronized boolean cancelBooking(String pnr) {
        Booking booking = bookings.get(pnr);
        if (booking == null || "CANCELLED".equalsIgnoreCase(booking.getStatus())) {
            return false;
        }

        CancellationPreviewResponse preview = pricingService.previewCancellation(booking);

        // Update status to CANCELLED
        booking.setStatus("CANCELLED");
        booking.setCancelledAt(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));

        // Generate Refund Details
        String refundId = "RFD-2026-" + (100000 + random.nextInt(900000));
        String txnRef = "TXN-UPI-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        RefundDetails refund = new RefundDetails(
                refundId,
                preview.getOriginalFare(),
                preview.getCancellationFee(),
                preview.getClerkageFee(),
                preview.getNonRefundableConvenienceFee(),
                preview.getFinalRefundAmount(),
                "INITIATED",
                LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME),
                booking.getPaymentMode() != null ? booking.getPaymentMode() : "Original Payment Method (UPI/Bank)",
                "3 - 5 Banking Working Days",
                txnRef
        );
        booking.setRefundDetails(refund);

        if (booking.getPassengers() != null) {
            for (Passenger p : booking.getPassengers()) {
                p.setStatus("CANCELLED");
            }
        }

        saveToDisk();
        return true;
    }

    public Booking getBookingByPnr(String pnr) {
        if (pnr == null) return null;
        return bookings.get(pnr.trim());
    }

    public List<Booking> getAllBookings(String filter) {
        List<Booking> list = new ArrayList<>(bookings.values());
        list.sort((a, b) -> (b.getCreatedAt() != null && a.getCreatedAt() != null)
                ? b.getCreatedAt().compareTo(a.getCreatedAt()) : 0);

        if (filter == null || filter.trim().isEmpty() || "ALL".equalsIgnoreCase(filter)) {
            return list;
        }
        if ("UPCOMING".equalsIgnoreCase(filter)) {
            return list.stream()
                    .filter(b -> !"CANCELLED".equalsIgnoreCase(b.getStatus()))
                    .collect(Collectors.toList());
        }
        if ("CANCELLED".equalsIgnoreCase(filter) || "PAST".equalsIgnoreCase(filter)) {
            return list.stream()
                    .filter(b -> "CANCELLED".equalsIgnoreCase(b.getStatus()))
                    .collect(Collectors.toList());
        }
        return list;
    }

    private String generateIndianRailwaysPnr() {
        int first3 = 200 + random.nextInt(800);
        int last7 = 1000000 + random.nextInt(9000000);
        return first3 + "-" + last7;
    }

    private String allocateSeatNumber(Train train, TrainClass tc, int index) {
        return (10 + index) + (index % 2 == 0 ? "A" : "B");
    }
}
