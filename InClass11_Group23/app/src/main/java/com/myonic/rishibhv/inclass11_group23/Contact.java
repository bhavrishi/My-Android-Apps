package com.myonic.rishibhv.inclass11_group23;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;

/**
 * Created by Rishi on 20/11/17.
 */
@IgnoreExtraProperties
public class Contact implements Serializable {

    String fname,lname,email,phone,imgurl,id;

    public Contact() {
    }

    public Contact(String id,String fname, String lname, String email, String phone, String imgurl) {
    this.id=id;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.phone = phone;
        this.imgurl = imgurl;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", imgurl='" + imgurl + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
