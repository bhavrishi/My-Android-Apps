package com.myonic.rishibhv.previous_final;

import java.io.Serializable;

/**
 * Created by Rishi on 03/12/17.
 */

public class AutocompleteCity implements Serializable {
    @Override
    public String toString() {
        return "AutocompleteCity{" +
                "placeid='" + placeid + '\'' +
                ", city='" + city + '\'' +
                ", id='" + id + '\'' +
                ", lat=" + lat +
                ", lang=" + lang +
                '}';
    }

    public String getPlaceid() {
        return placeid;
    }

    public AutocompleteCity() {
    }

    public String getTripname() {
        return tripname;
    }

    public void setTripname(String tripname) {
        this.tripname = tripname;
    }

    public AutocompleteCity(String placeid, String city, String id, Double lat, Double lang, String tripname) {
        this.placeid = placeid;
        this.city = city;
        this.id = id;
        this.lat = lat;
        this.lang = lang;
        this.tripname=tripname;

    }

    public void setPlaceid(String placeid) {
        this.placeid = placeid;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    String placeid,city,tripname;
    String id;
    Double lat,lang;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
