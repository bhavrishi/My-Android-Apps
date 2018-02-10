package com.myonic.rishibhv.inclass13_801019706;

/**
 * Created by Rishi on 04/12/17.
 */

public class Trip {
    String trip;
    Double cost;

    public Trip() {
    }

    public Trip(String trip, Double cost) {

        this.trip = trip;
        this.cost = cost;
    }

    public String getTrip() {

        return trip;
    }

    public void setTrip(String trip) {
        this.trip = trip;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }
}
