package com.velocity.model;

import java.util.List;

public class Seat {
    private String id;
    private String car;
    private String seatNumber;
    private String classId;
    private String status; // AVAILABLE, RESERVED, OCCUPIED
    private boolean window;
    private boolean aisle;
    private List<String> amenities;
    private double price;
    private String bookedPnr;

    public Seat() {}

    public Seat(String id, String car, String seatNumber, String classId, String status, boolean window, boolean aisle, List<String> amenities, double price) {
        this.id = id;
        this.car = car;
        this.seatNumber = seatNumber;
        this.classId = classId;
        this.status = status;
        this.window = window;
        this.aisle = aisle;
        this.amenities = amenities;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isWindow() {
        return window;
    }

    public void setWindow(boolean window) {
        this.window = window;
    }

    public boolean isAisle() {
        return aisle;
    }

    public void setAisle(boolean aisle) {
        this.aisle = aisle;
    }

    public List<String> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<String> amenities) {
        this.amenities = amenities;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBookedPnr() {
        return bookedPnr;
    }

    public void setBookedPnr(String bookedPnr) {
        this.bookedPnr = bookedPnr;
    }
}
