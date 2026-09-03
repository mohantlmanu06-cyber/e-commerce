package com.velocity.model;

import java.util.List;

public class TrainClass {
    private String id; // 1A, 2A, 3A, 3E, EC, CC, SL, 2S
    private String name;
    private String description;
    private double price;
    private String currency; // ₹
    private int totalSeats;
    private int availableSeats;
    private int racSeats;
    private int waitlistSeats;
    private String availabilityStatus; // AVAILABLE, FILLING_FAST, FEW_SEATS, RAC, WL
    private List<String> features;
    private String imageUrl;

    public TrainClass() {}

    public TrainClass(String id, String name, String description, double price, String currency,
                      int totalSeats, int availableSeats, int racSeats, int waitlistSeats,
                      List<String> features, String imageUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.currency = currency != null ? currency : "₹";
        this.totalSeats = totalSeats;
        this.availableSeats = availableSeats;
        this.racSeats = racSeats;
        this.waitlistSeats = waitlistSeats;
        this.features = features;
        this.imageUrl = imageUrl;
        this.availabilityStatus = calculateAvailabilityStatus(availableSeats, racSeats, waitlistSeats);
    }

    public static String calculateAvailabilityStatus(int available, int rac, int wl) {
        if (available > 10) {
            return "AVAILABLE";
        } else if (available > 3) {
            return "FILLING_FAST";
        } else if (available > 0) {
            return "FEW_SEATS";
        } else if (rac > 0) {
            return "RAC";
        } else {
            return "WL";
        }
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }

    public int getTotalSeats() { return totalSeats; }
    public void setTotalSeats(int totalSeats) { this.totalSeats = totalSeats; }

    public int getAvailableSeats() { return availableSeats; }
    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
        this.availabilityStatus = calculateAvailabilityStatus(this.availableSeats, this.racSeats, this.waitlistSeats);
    }

    public int getRacSeats() { return racSeats; }
    public void setRacSeats(int racSeats) {
        this.racSeats = racSeats;
        this.availabilityStatus = calculateAvailabilityStatus(this.availableSeats, this.racSeats, this.waitlistSeats);
    }

    public int getWaitlistSeats() { return waitlistSeats; }
    public void setWaitlistSeats(int waitlistSeats) {
        this.waitlistSeats = waitlistSeats;
        this.availabilityStatus = calculateAvailabilityStatus(this.availableSeats, this.racSeats, this.waitlistSeats);
    }

    public String getAvailabilityStatus() { return availabilityStatus; }
    public void setAvailabilityStatus(String availabilityStatus) { this.availabilityStatus = availabilityStatus; }

    public List<String> getFeatures() { return features; }
    public void setFeatures(List<String> features) { this.features = features; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}
