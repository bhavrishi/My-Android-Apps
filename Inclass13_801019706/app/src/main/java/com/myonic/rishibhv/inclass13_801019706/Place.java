package com.myonic.rishibhv.inclass13_801019706;

import java.util.HashMap;

/**
 * Created by Rishi on 04/12/17.
 */

public class Place {

    String Cost,Duration,Place;
  Double lat,lang;
    String id;
    String miles;

    public String getMiles() {
        return miles;
    }

    public void setMiles(String miles) {
        this.miles = miles;
    }

    public String getCost() {
        return Cost;
    }

    @Override
    public String toString() {
        return "Place{" +
                "Cost='" + Cost + '\'' +
                ", Duration='" + Duration + '\'' +
                ", Place='" + Place + '\'' +
                ", lat=" + lat +
                ", lang=" + lang +
                ", id='" + id + '\'' +
                ", miles='" + miles + '\'' +
                '}';
    }

    public String getPlace() {
        return Place;
    }

    public void setPlace(String place) {
        Place = place;
    }

    public Double getLat() {

        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLang() {
        return lang;
    }

    public void setLang(Double lang) {
        this.lang = lang;
    }

    public void setCost(String cost) {
        Cost = cost;
    }

    public String getDuration() {
        return Duration;
    }

    public void setDuration(String duration) {
        Duration = duration;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
