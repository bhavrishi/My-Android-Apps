package com.myonic.rishibhv.previous_final;

import java.io.Serializable;

/**
 * Created by Rishi on 03/12/17.
 */

public class Place implements Serializable {
    String tripid,placeid;
    String lat,lang;
    String placename;
    String imgurl;

    public Place() {
    }

    public Place(String tripid, String placeid, String lat, String lang, String placename, String imgurl) {

        this.tripid = tripid;
        this.placeid = placeid;
        this.lat = lat;
        this.lang = lang;
        this.placename = placename;
        this.imgurl = imgurl;
    }

    @Override
    public String toString() {
        return "Place{" +
                "tripid='" + tripid + '\'' +
                ", placeid='" + placeid + '\'' +
                ", lat=" + lat +
                ", lang=" + lang +
                ", placename='" + placename + '\'' +
                ", imgurl='" + imgurl + '\'' +
                '}';
    }

    public String getTripid() {
        return tripid;
    }

    public void setTripid(String tripid) {
        this.tripid = tripid;
    }

    public String getPlaceid() {
        return placeid;
    }

    public void setPlaceid(String placeid) {
        this.placeid = placeid;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getPlacename() {
        return placename;
    }

    public void setPlacename(String placename) {
        this.placename = placename;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }
}
