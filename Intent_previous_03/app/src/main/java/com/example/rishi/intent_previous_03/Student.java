package com.example.rishi.intent_previous_03;

import java.io.Serializable;

/**
 * Created by Rishi on 10/09/17.
 */

public class Student implements Serializable {
    String name,email,lang;
    String account;
    String mood;

    public Student(String name, String email, String lang, String account, String mood) {
        this.name = name;
        this.email = email;
        this.lang = lang;
        this.account = account;
        this.mood = mood;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", lang='" + lang + '\'' +
                ", account='" + account + '\'' +
                ", mood='" + mood + '\'' +
                '}';
    }
}
