package com.example.rishi.activity;

import java.io.Serializable;

/**
 * Created by Rishi on 07/09/17.
 */

public class User implements Serializable{

    String name;
    Double age;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public User(String name, Double age) {
        this.name = name;
        this.age = age;
    }
}
