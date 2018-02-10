package com.hwork.uncc.previouslist;

import java.io.Serializable;

/**
 * Created by Rishi on 15/10/17.
 */

public class Itunes implements Serializable{
    String date,title,Summary;
    String img1;

    @Override
    public String toString() {
        return "Itunes{" +
                "date='" + date + '\'' +
                ", title='" + title + '\'' +
                ", Summary='" + Summary + '\'' +
                ", img1='" + img1 + '\'' +
                ", img2='" + img2 + '\'' +
                '}';
    }

    String img2;

    public String getDate() {
        return date;
    }

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public String getImg2() {
        return img2;
    }

    public void setImg2(String img2) {
        this.img2 = img2;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return Summary;
    }

    public void setSummary(String summary) {
        Summary = summary;
    }
}
