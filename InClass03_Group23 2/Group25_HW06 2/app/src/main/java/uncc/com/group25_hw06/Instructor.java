package uncc.com.group25_hw06;

import io.realm.RealmObject;

/**
 * Created by rosyazad on 05/11/17.
 */

public class Instructor extends RealmObject {
    private String userName,img,fname,lname,website,email;

    @Override
    public String toString() {
        return "Instructor{" +
                "userName='" + userName + '\'' +
                ", img='" + img + '\'' +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", website='" + website + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
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

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
