package com.velocity.dto;

import java.util.ArrayList;
import java.util.List;

public class PriceCalculationRequest {
    private String trainNumber;
    private String classId;
    private List<PassengerItem> passengers = new ArrayList<>();

    public PriceCalculationRequest() {}

    public static class PassengerItem {
        private String passengerType; // ADULT, CHILD, INFANT
        private String seatNumber;

        public PassengerItem() {}

        public PassengerItem(String passengerType, String seatNumber) {
            this.passengerType = passengerType;
            this.seatNumber = seatNumber;
        }

        public String getPassengerType() {
            return passengerType;
        }

        public void setPassengerType(String passengerType) {
            this.passengerType = passengerType;
        }

        public String getSeatNumber() {
            return seatNumber;
        }

        public void setSeatNumber(String seatNumber) {
            this.seatNumber = seatNumber;
        }
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public List<PassengerItem> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<PassengerItem> passengers) {
        this.passengers = passengers;
    }
}
