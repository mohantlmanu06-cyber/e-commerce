package com.velocity.dto;

import com.velocity.model.Passenger;

import java.util.List;

public class CreateBookingRequest {
    private String trainNumber;
    private String journeyDate;
    private String classId;
    private String car;
    private String paymentMode; // UPI, NET_BANKING, DEBIT_CARD, CREDIT_CARD
    private List<Passenger> passengers;
    private String contactEmail;
    private String contactPhone;

    public CreateBookingRequest() {}

    public String getTrainNumber() { return trainNumber; }
    public void setTrainNumber(String trainNumber) { this.trainNumber = trainNumber; }

    public String getJourneyDate() { return journeyDate; }
    public void setJourneyDate(String journeyDate) { this.journeyDate = journeyDate; }

    public String getClassId() { return classId; }
    public void setClassId(String classId) { this.classId = classId; }

    public String getCar() { return car; }
    public void setCar(String car) { this.car = car; }

    public String getPaymentMode() { return paymentMode; }
    public void setPaymentMode(String paymentMode) { this.paymentMode = paymentMode; }

    public List<Passenger> getPassengers() { return passengers; }
    public void setPassengers(List<Passenger> passengers) { this.passengers = passengers; }

    public String getContactEmail() { return contactEmail; }
    public void setContactEmail(String contactEmail) { this.contactEmail = contactEmail; }

    public String getContactPhone() { return contactPhone; }
    public void setContactPhone(String contactPhone) { this.contactPhone = contactPhone; }
}
