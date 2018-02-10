package com.inclass.uncc.group23_inclass09zip;

import java.io.Serializable;

/**
 * Created by Rishi on 06/11/17.
 */

public class SignUp implements Serializable {
    String fname,lname,token,email;
    int userid;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "SignUp{" +
                "fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", token='" + token + '\'' +
                ", email='" + email + '\'' +
                '}';
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
