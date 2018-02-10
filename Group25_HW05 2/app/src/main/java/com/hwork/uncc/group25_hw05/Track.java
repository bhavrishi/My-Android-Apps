package com.hwork.uncc.group25_hw05;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by Rishi on 12/10/17.
 */

public class Track implements Serializable {
    String name;
    String artist;
    String url;
    String img[];


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Track other = (Track) obj;
        if (url != other.url)
            return false;
        return true;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Track{" +
                "name='" + name + '\'' +
                ", artist='" + artist + '\'' +
                ", url='" + url + '\'' +
                ", img=" + Arrays.toString(img) +
                '}';
    }

    public String[] getImg() {
        return img;
    }

    public void setImg(String[] img) {
        this.img = img;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}
