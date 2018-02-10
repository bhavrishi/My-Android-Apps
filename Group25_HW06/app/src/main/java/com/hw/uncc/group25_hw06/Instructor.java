package com.hw.uncc.group25_hw06;

import android.graphics.Bitmap;

/**
 * Created by Rishi on 01/11/17.
 */

public class Instructor {
    long intid;
String uname;
    public long getIntid() {
        return intid;
    }

    public Instructor() {
    }


    @Override
    public String toString() {
        return "Instructor{" +
                "intid=" + intid +
                ", uname='" + uname + '\'' +
                ", img='" + img + '\'' +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", website='" + website + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public Instructor(String img, String fname, String lname , String email, String website, String uname) {

        this.img = img;
        this.fname = fname;
        this.lname = lname;
        this.email=email;
        this.website = website;
        this.uname=uname;

    }

    public void setIntid(long intid) {
        this.intid = intid;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    String img;
    String fname,lname,website,email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
