package com.midterm.uncc.a801019706;

import java.io.Serializable;

/**
 * Created by Rishi on 16/10/17.
 */

public class Movie implements Serializable {
    String id,name,overview,date,rating,popularity,posterpath;

    @Override
    public String toString() {
        return "Movie{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", overview='" + overview + '\'' +
                ", date='" + date + '\'' +
                ", rating='" + rating + '\'' +
                ", popularity='" + popularity + '\'' +
                ", posterpath='" + posterpath + '\'' +
                '}';
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

    public void setName(String name) {
        this.name = name;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getPosterpath() {
        return posterpath;
    }

    public void setPosterpath(String posterpath) {
        this.posterpath = posterpath;
    }
}
