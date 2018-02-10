package com.hw.uncc.group25_hw06;

import java.util.HashMap;

/**
 * Created by Rishi on 02/11/17.
 */

public class User {
    String img,fname,lname;
    long userid;

    public User(String img, String fname, String lname, HashMap<String, String> upwd) {
        this.img = img;
        this.fname = fname;
        this.lname = lname;
        this.upwd = upwd;
    }

    public User() {

    }

    @Override
    public String toString() {
        return "User{" +
                "img='" + img + '\'' +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", userid=" + userid +
                ", upwd=" + upwd +
                '}';
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

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public HashMap<String, String> getUpwd() {
        return upwd;
    }

    public void setUpwd(HashMap<String, String> upwd) {
        this.upwd = upwd;
    }

    HashMap<String,String> upwd;

}
