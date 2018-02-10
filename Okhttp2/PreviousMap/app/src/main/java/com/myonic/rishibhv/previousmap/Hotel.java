package com.myonic.rishibhv.previousmap;

/**
 * Created by Rishi on 26/11/17.
 */

public class Hotel {

    double lat,lang;
    String name;

    @Override
    public String toString() {
        return "Hotel{" +
                "lat=" + lat +
                ", lang=" + lang +
                ", name='" + name + '\'' +
                '}';
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLang() {
        return lang;
    }

    public void setLang(double lang) {
        this.lang = lang;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
