package com.inclass.uncc.inclass06_801019706;
/*#Assignment: In Class Assignment $
     #   File Name : Table1_InClass06.zip
        #Full Name:  Bhavya Gedi*/
import android.graphics.Bitmap;
import android.os.AsyncTask;

import java.io.Serializable;

/**
 * Created by Rishi on 02/10/17.
 */

public class Receipe implements Serializable {
    String title;

    String url;
    String ingredients;

    @Override
    public String toString() {
        return "Receipe{" +
                "title='" + title + '\''  +
                ", url='" + url + '\'' +
                ", ingredients='" + ingredients + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }





    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }
}
