package com.hwork.uncc.periousmid;

import java.io.Serializable;

/**
 * Created by Rishi on 15/10/17.
 */

public class Music implements Serializable{
    String id,devname,title,smallimg,largeimg,price,appurl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDevname() {
        return devname;
    }

    public void setDevname(String devname) {
        this.devname = devname;
    }

    @Override
    public String toString() {
        return "Music{" +
                "id='" + id + '\'' +
                ", devname='" + devname + '\'' +
                ", title='" + title + '\'' +
                ", smallimg='" + smallimg + '\'' +
                ", largeimg='" + largeimg + '\'' +
                ", price='" + price + '\'' +
                ", appurl='" + appurl + '\'' +
                '}';
    }

    public String getAppurl() {
        return appurl;
    }

    public void setAppurl(String appurl) {
        this.appurl = appurl;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSmallimg() {
        return smallimg;
    }

    public void setSmallimg(String smallimg) {
        this.smallimg = smallimg;
    }

    public String getLargeimg() {
        return largeimg;
    }

    public void setLargeimg(String largeimg) {
        this.largeimg = largeimg;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
