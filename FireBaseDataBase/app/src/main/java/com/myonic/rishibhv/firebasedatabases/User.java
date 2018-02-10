package com.myonic.rishibhv.firebasedatabases;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Rishi on 12/11/17.
 */

@IgnoreExtraProperties
public class User {
    public String name;
    public String email;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String username, String email) {
        this.name = username;
        this.email = email;
    }
}