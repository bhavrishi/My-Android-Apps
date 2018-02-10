package com.myonic.rishibhv.inclass10_group;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;

/**
 * Created by Rishi on 13/11/17.
 */

@IgnoreExtraProperties
public class Contact implements Serializable{
    String id,name,phone,email,dept,img;

    public String getId() {
        return id;
    }

    public Contact(String id, String name, String phone, String email, String dept, String img) {

        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.dept = dept;
        this.img = img;
    }

    public Contact() {

    }

    public String getName() {

        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getDept() {
        return dept;
    }

    public String getImg() {
        return img;
    }
}
