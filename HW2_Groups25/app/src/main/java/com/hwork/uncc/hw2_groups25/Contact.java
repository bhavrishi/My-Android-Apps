package com.hwork.uncc.hw2_groups25;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by Rishi on 19/09/17.
 */

public class Contact implements Serializable {
    String firstName , email,lastname,company,url,address,bday,nickname,fburl,twitURL,skype,utb;
    String phoneNumber;
    Bitmap img;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBday() {
        return bday;
    }

    public void setBday(String bday) {
        this.bday = bday;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getFburl() {
        return fburl;
    }

    public void setFburl(String fburl) {
        this.fburl = fburl;
    }

    public String getTwitURL() {
        return twitURL;
    }

    public void setTwitURL(String twitURL) {
        this.twitURL = twitURL;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getUtb() {
        return utb;
    }

    public void setUtb(String utb) {
        this.utb = utb;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "firstName='" + firstName + '\'' +
                ", email='" + email + '\'' +
                ", lastname='" + lastname + '\'' +
                ", company='" + company + '\'' +
                ", url='" + url + '\'' +
                ", address='" + address + '\'' +
                ", bday='" + bday + '\'' +
                ", nickname='" + nickname + '\'' +
                ", fburl='" + fburl + '\'' +
                ", twitURL='" + twitURL + '\'' +
                ", skype='" + skype + '\'' +
                ", utb='" + utb + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", img=" + img +
                '}';
    }

    public Bitmap getImg() {
        return img;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }

    public Contact(String firstName, String lastname, String email, String company, String url,
                   String address, String bday, String nickname, String fburl, String twitURL, String skype,
                   String utb, String phoneNumber, Bitmap img) {

        this.firstName = firstName;
        this.email = email;
        this.lastname = lastname;
        this.company = company;
        this.url = url;
        this.address = address;
        this.bday = bday;
        this.nickname = nickname;
        this.fburl = fburl;
        this.twitURL = twitURL;
        this.skype = skype;
        this.utb = utb;
        this.phoneNumber = phoneNumber;
        this.img = img;
    }
}
