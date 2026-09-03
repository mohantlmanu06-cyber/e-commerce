package com.velocity.model;

import java.util.ArrayList;
import java.util.List;

public class Train {
    private String trainNumber;
    private String name;
    private String trainType; // Vande Bharat Express, Rajdhani Express, Shatabdi Express, Tejas Express, Superfast, Mail/Express
    private double rating; // e.g. 4.8
    private int totalRatings; // e.g. 4210
    private int punctualityScore; // e.g. 96 (%)
    private double cleanlinessScore; // e.g. 4.9
    private double foodScore; // e.g. 4.7
    private String fromStationCode;
    private String fromStationName;
    private String toStationCode;
    private String toStationName;
    private String departureTime;
    private String arrivalTime;
    private String departurePlatform;
    private String arrivalPlatform;
    private String duration;
    private int distanceKm;
    private String status; // ON_TIME, DELAYED_10M, RUNNING
    private List<TrainClass> classes;
    private List<String> runningDays;
    private List<String> intermediateStops;
    private List<Seat> seats = new ArrayList<>();

    public Train() {}

    public Train(String trainNumber, String name, String trainType, double rating, int totalRatings,
                 int punctualityScore, double cleanlinessScore, double foodScore,
                 String fromStationCode, String fromStationName, String toStationCode, String toStationName,
                 String departureTime, String arrivalTime, String departurePlatform, String arrivalPlatform,
                 String duration, int distanceKm, String status, List<TrainClass> classes,
                 List<String> runningDays, List<String> intermediateStops) {
        this.trainNumber = trainNumber;
        this.name = name;
        this.trainType = trainType;
        this.rating = rating;
        this.totalRatings = totalRatings;
        this.punctualityScore = punctualityScore;
        this.cleanlinessScore = cleanlinessScore;
        this.foodScore = foodScore;
        this.fromStationCode = fromStationCode;
        this.fromStationName = fromStationName;
        this.toStationCode = toStationCode;
        this.toStationName = toStationName;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.departurePlatform = departurePlatform;
        this.arrivalPlatform = arrivalPlatform;
        this.duration = duration;
        this.distanceKm = distanceKm;
        this.status = status;
        this.classes = classes;
        this.runningDays = runningDays;
        this.intermediateStops = intermediateStops;
    }

    public String getTrainNumber() { return trainNumber; }
    public void setTrainNumber(String trainNumber) { this.trainNumber = trainNumber; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getTrainType() { return trainType; }
    public void setTrainType(String trainType) { this.trainType = trainType; }

    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }

    public int getTotalRatings() { return totalRatings; }
    public void setTotalRatings(int totalRatings) { this.totalRatings = totalRatings; }

    public int getPunctualityScore() { return punctualityScore; }
    public void setPunctualityScore(int punctualityScore) { this.punctualityScore = punctualityScore; }

    public double getCleanlinessScore() { return cleanlinessScore; }
    public void setCleanlinessScore(double cleanlinessScore) { this.cleanlinessScore = cleanlinessScore; }

    public double getFoodScore() { return foodScore; }
    public void setFoodScore(double foodScore) { this.foodScore = foodScore; }

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

    public String getDuration() { return duration; }
    public void setDuration(String duration) { this.duration = duration; }

    public int getDistanceKm() { return distanceKm; }
    public void setDistanceKm(int distanceKm) { this.distanceKm = distanceKm; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public List<TrainClass> getClasses() { return classes; }
    public void setClasses(List<TrainClass> classes) { this.classes = classes; }

    public List<String> getRunningDays() { return runningDays; }
    public void setRunningDays(List<String> runningDays) { this.runningDays = runningDays; }

    public List<String> getIntermediateStops() { return intermediateStops; }
    public void setIntermediateStops(List<String> intermediateStops) { this.intermediateStops = intermediateStops; }

    public List<Seat> getSeats() { return seats; }
    public void setSeats(List<Seat> seats) { this.seats = seats; }
}
