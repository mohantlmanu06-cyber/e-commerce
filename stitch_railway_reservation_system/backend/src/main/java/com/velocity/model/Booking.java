package com.velocity.model;

import java.util.ArrayList;
import java.util.List;

public class Booking {
    private String pnr; // e.g. 246-8910432
    private String trainNumber;
    private String trainName;
    private String trainType;
    private double trainRating;
    private String fromStationCode;
    private String fromStationName;
    private String toStationCode;
    private String toStationName;
    private String departureTime;
    private String arrivalTime;
    private String departurePlatform;
    private String arrivalPlatform;
    private String journeyDate;
    private String travelClass;
    private String travelClassId;
    private String car;
    private List<String> seatNumbers = new ArrayList<>();
    private List<Passenger> passengers = new ArrayList<>();
    private double baseFare;
    private double taxesAndFees;
    private double convenienceFee;
    private double totalAmount;
    private String currency; // ₹
    private String status; // CONFIRMED, WAITLIST, CANCELLED
    private String paymentMode; // UPI, NET_BANKING, DEBIT_CARD, CREDIT_CARD
    private String createdAt;
    private String cancelledAt;
    private String contactEmail;
    private String contactPhone;
    private String qrCodeData;
    private RefundDetails refundDetails;

    public Booking() {}

    public String getPnr() { return pnr; }
    public void setPnr(String pnr) { this.pnr = pnr; }

    public String getTrainNumber() { return trainNumber; }
    public void setTrainNumber(String trainNumber) { this.trainNumber = trainNumber; }

    public String getTrainName() { return trainName; }
    public void setTrainName(String trainName) { this.trainName = trainName; }

    public String getTrainType() { return trainType; }
    public void setTrainType(String trainType) { this.trainType = trainType; }

    public double getTrainRating() { return trainRating; }
    public void setTrainRating(double trainRating) { this.trainRating = trainRating; }

    public String getFromStationCode() { return fromStationCode; }
    public void setFromStationCode(String fromStationCode) { this.fromStationCode = fromStationCode; }

    public String getFromStationName() { return fromStationName; }
    public void setFromStationName(String fromStationName) { this.fromStationName = fromStationName; }

    public String getToStationCode() { return toStationCode; }
    public void setToStationCode(String toStationCode) { this.toStationCode = toStationCode; }

    public String getToStationName() { return toStationName; }
    public void setToStationName(String toStationName) { this.toStationName = toStationName; }

    public String getDepartureTime() { return departureTime; }
    public void setDepartureTime(String departureTime) { this.departureTime = departureTime; }

    public String getArrivalTime() { return arrivalTime; }
    public void setArrivalTime(String arrivalTime) { this.arrivalTime = arrivalTime; }

    public String getDeparturePlatform() { return departurePlatform; }
    public void setDeparturePlatform(String departurePlatform) { this.departurePlatform = departurePlatform; }

    public String getArrivalPlatform() { return arrivalPlatform; }
    public void setArrivalPlatform(String arrivalPlatform) { this.arrivalPlatform = arrivalPlatform; }

    public String getJourneyDate() { return journeyDate; }
    public void setJourneyDate(String journeyDate) { this.journeyDate = journeyDate; }

    public String getTravelClass() { return travelClass; }
    public void setTravelClass(String travelClass) { this.travelClass = travelClass; }

    public String getTravelClassId() { return travelClassId; }
    public void setTravelClassId(String travelClassId) { this.travelClassId = travelClassId; }

    public String getCar() { return car; }
    public void setCar(String car) { this.car = car; }

    public List<String> getSeatNumbers() { return seatNumbers; }
    public void setSeatNumbers(List<String> seatNumbers) { this.seatNumbers = seatNumbers; }

    public List<Passenger> getPassengers() { return passengers; }
    public void setPassengers(List<Passenger> passengers) { this.passengers = passengers; }

    public double getBaseFare() { return baseFare; }
    public void setBaseFare(double baseFare) { this.baseFare = baseFare; }

    public double getTaxesAndFees() { return taxesAndFees; }
    public void setTaxesAndFees(double taxesAndFees) { this.taxesAndFees = taxesAndFees; }

    public double getConvenienceFee() { return convenienceFee; }
    public void setConvenienceFee(double convenienceFee) { this.convenienceFee = convenienceFee; }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getPaymentMode() { return paymentMode; }
    public void setPaymentMode(String paymentMode) { this.paymentMode = paymentMode; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }

    public String getCancelledAt() { return cancelledAt; }
    public void setCancelledAt(String cancelledAt) { this.cancelledAt = cancelledAt; }

    public String getContactEmail() { return contactEmail; }
    public void setContactEmail(String contactEmail) { this.contactEmail = contactEmail; }

    public String getContactPhone() { return contactPhone; }
    public void setContactPhone(String contactPhone) { this.contactPhone = contactPhone; }

    public String getQrCodeData() { return qrCodeData; }
    public void setQrCodeData(String qrCodeData) { this.qrCodeData = qrCodeData; }

    public RefundDetails getRefundDetails() { return refundDetails; }
    public void setRefundDetails(RefundDetails refundDetails) { this.refundDetails = refundDetails; }
}
