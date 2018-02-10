package com.myonic.rishibhv.upload_three;

/**
 * Created by Rishi on 13/11/17.
 */import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by rosyazad on 13/11/17.
 */

@IgnoreExtraProperties
public class User {
    String id, email, password;

    public User() {
    }

    /*public User(String id, String firstName, String lastName, String email, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }*/

    public User(String id,String email, String password) {
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