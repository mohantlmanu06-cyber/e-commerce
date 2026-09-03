package com.velocity.model;

public class Passenger {
    private String id;
    private String firstName;
    private String lastName;
    private int age;
    private String gender; // MALE, FEMALE, TRANSGENDER
    private String berthPreference; // LOWER, MIDDLE, UPPER, SIDE_LOWER, SIDE_UPPER, WINDOW, NO_PREFERENCE
    private String mealPreference; // VEG, NON_VEG, JAIN_VEG, NO_MEAL
    private String dateOfBirth;
    private String passengerType; // ADULT, CHILD, SENIOR_CITIZEN, INFANT
    private String seatNumber;
    private String car;
    private String status; // CONFIRMED, RAC, WAITLIST, CANCELLED
    private double fare;
    private boolean seniorCitizen;

    public Passenger() {}

    public Passenger(String id, String firstName, String lastName, int age, String gender,
                     String berthPreference, String mealPreference, String dateOfBirth,
                     String passengerType, String seatNumber, String car, String status, double fare) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.berthPreference = berthPreference;
        this.mealPreference = mealPreference;
        this.dateOfBirth = dateOfBirth;
        this.passengerType = passengerType;
        this.seatNumber = seatNumber;
        this.car = car;
        this.status = status;
        this.fare = fare;
        this.seniorCitizen = (age >= 60);
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public int getAge() { return age; }
    public void setAge(int age) {
        this.age = age;
        if (age >= 60) this.seniorCitizen = true;
    }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getBerthPreference() { return berthPreference; }
    public void setBerthPreference(String berthPreference) { this.berthPreference = berthPreference; }

    public String getMealPreference() { return mealPreference; }
    public void setMealPreference(String mealPreference) { this.mealPreference = mealPreference; }

    public String getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(String dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public String getPassengerType() { return passengerType; }
    public void setPassengerType(String passengerType) { this.passengerType = passengerType; }

    public String getSeatNumber() { return seatNumber; }
    public void setSeatNumber(String seatNumber) { this.seatNumber = seatNumber; }

    public String getCar() { return car; }
    public void setCar(String car) { this.car = car; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public double getFare() { return fare; }
    public void setFare(double fare) { this.fare = fare; }

    public boolean isSeniorCitizen() { return seniorCitizen; }
    public void setSeniorCitizen(boolean seniorCitizen) { this.seniorCitizen = seniorCitizen; }
}
