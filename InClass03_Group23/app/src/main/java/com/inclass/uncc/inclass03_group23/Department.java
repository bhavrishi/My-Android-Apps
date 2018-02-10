package com.inclass.uncc.inclass03_group23;

import java.io.Serializable;

/**
 * Created by Rishi on 11/09/17.
 */

public class Department implements Serializable {
    String name,email,deptname;
    String img;
    String mood;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public Department(String name, String email, String dept, String img, String mood) {
        this.name = name;
        this.email = email;
        this.deptname = dept;
        this.img = img;
        this.mood = mood;

    }

    @Override
    public String toString() {
        return "Department{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", dept='" + deptname + '\'' +
                ", img='" + img + '\'' +
                ", mood='" + mood + '\'' +
                '}';
    }
}
