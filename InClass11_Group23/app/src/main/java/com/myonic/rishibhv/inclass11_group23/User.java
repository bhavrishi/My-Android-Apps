package com.myonic.rishibhv.inclass11_group23;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Rishi on 20/11/17.
 */

@IgnoreExtraProperties
public class User {
    String id,name, email, password;
    String url;

    public User() {
    }

    /*public User(String id, String firstName, String lastName, String email, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }*/


    public User(String id, String name, String email) {
        this.name = name;
        this.id=id;
        this.email = email;

    }


    public User(String id, String name, String email, String password, String url) {
        this.name = name;
        this.id=id;
        this.email = email;

        this.password = password;
        this.url = url;
    }
    public User(String id, String name, String email, String password) {
        this.name = name;
        this.id=id;
        this.email = email;

        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String firstName) {
        this.name = firstName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}