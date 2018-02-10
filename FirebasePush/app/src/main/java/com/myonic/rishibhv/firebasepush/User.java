package com.myonic.rishibhv.firebasepush;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;

/**
 * Created by Rishi on 12/11/17.
 */

@IgnoreExtraProperties
public class User implements Serializable{
    public String name;
    public String phone;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String username, String phone) {
        this.name = username;
        this.phone = phone;
    }
}