package uncc.com.group25_hw06;

import io.realm.RealmObject;

/**
 * Created by rosyazad on 05/11/17.
 */

public class Course extends RealmObject {
    private String userName,courseName ,day,instructor,time,creditHours,semester;

    @Override
    public String toString() {
        return "Course{" +
                "userName='" + userName + '\'' +
                ", courseName='" + courseName + '\'' +
                ", day='" + day + '\'' +
                ", instructor='" + instructor + '\'' +
                ", time='" + time + '\'' +
                ", creditHours='" + creditHours + '\'' +
                ", semester='" + semester + '\'' +
                '}';
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCreditHours() {
        return creditHours;
    }

    public void setCreditHours(String creditHours) {
        this.creditHours = creditHours;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }
}
