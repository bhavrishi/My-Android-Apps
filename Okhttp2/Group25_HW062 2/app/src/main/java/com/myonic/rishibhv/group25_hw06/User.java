package com.myonic.rishibhv.group25_hw06;

/**
 * Created by Rishi on 13/11/17.
 */import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by rosyazad on 13/11/17.
 */

@IgnoreExtraProperties
public class User {
    String id,name, email, password,dob;

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", dob='" + dob + '\'' +
                '}';
    }

    public User() {
    }

    /*public User(String id, String firstName, String lastName, String email, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }*/

    public void setDob(String dob) {
        this.dob = dob;
    }

    public User(String id,String name, String email) {
        this.name = name;
this.id=id;
        this.email = email;

    }

    public String getDob() {
        return dob;
    }

    public User(String id,String name, String email, String dob, String password) {
        this.name = name;
this.id=id;
        this.email = email;
        this.dob=dob;

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