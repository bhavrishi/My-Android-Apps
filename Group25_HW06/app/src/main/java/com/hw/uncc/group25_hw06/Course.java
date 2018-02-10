package com.hw.uncc.group25_hw06;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Rishi on 31/10/17.
 */

public class Course {
    HashMap<String,String> upwd;
    String courseName;
    String day,instructor;

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    long id;

    @Override
    public String toString() {
        return "Course{" +
                "upwd=" + upwd +
                ", courseName='" + courseName + '\'' +
                ", day='" + day + '\'' +
                ", instructor='" + instructor + '\'' +
                ", id=" + id +
                ", time='" + time + '\'' +
                ", credit_hours='" + credit_hours + '\'' +
                ", semester='" + semester + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public HashMap<String, String> getUpwd() {
        return upwd;
    }

    public Course() {
    }

    public Course(HashMap<String, String> upwd, String courseName, String day, String time, String credit_hours, String semester,String inst) {
        this.upwd = upwd;
        this.courseName = courseName;
        this.day = day;
        this.time = time;
        this.credit_hours = credit_hours;
        this.semester = semester;
        this.instructor=inst;
    }

    public void setUpwd(HashMap<String, String> upwd) {
        this.upwd = upwd;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCredit_hours() {
        return credit_hours;
    }

    public void setCredit_hours(String credit_hours) {
        this.credit_hours = credit_hours;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    String time;
    String credit_hours;
    String semester;
}
