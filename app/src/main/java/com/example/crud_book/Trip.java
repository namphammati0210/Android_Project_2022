package com.example.crud_book;

public class Trip {
    private String trip_id;
    private String trip_name;
    private String trip_destination;
    private String trip_date;
    private String trip_risk;
    private String trip_description;

    // creating getter and setter methods
    public String getTripId() {
        return trip_id;
    }

    public void setTripId(String id) {
        this.trip_id = id;
    }

    public String getTripName() {
        return trip_name;
    }

    public void setTripName(String name) {
        this.trip_name = name;
    }

    public String getTripDestination() {
        return trip_destination;
    }

    public void setTripDestination(String destination) {
        this.trip_destination = destination;
    }

    public String getTripDate() {
        return trip_date;
    }

    public void setTripDate(String date) {
        this.trip_date = date;
    }

    public String getTripRisk() {
        return trip_risk;
    }

    public void setTripRisk(String risk) {
        this.trip_risk = risk;
    }

    public String getTripDescription() {
        return trip_description;
    }

    public void setTripDescription(String description) {
        this.trip_description = description;
    }

    // constructor
    public Trip(String id, String name, String destination, String date, String risk, String description) {
        this.trip_id = id;
        this.trip_name = name;
        this.trip_destination = destination;
        this.trip_date = date;
        this.trip_risk = risk;
        this.trip_description = description;

    }
}
