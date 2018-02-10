package com.midterm.uncc.a801019706;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Rishi on 16/10/17.
 */

public class SharedPreferencesFavourites {


    public static final String PREFS_NAME = "PRODUCT_APP";
    public static final String FAVORITES = "Product_Favorite";

    public SharedPreferencesFavourites() {
        super();
    }

    // This four methods are used for maintaining favorites.
    public void saveFavorites(Context context, ArrayList<Movie> favorites) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings =  context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();

        Gson gson = new Gson();
        String jsonFavorites = gson.toJson(favorites);

        editor.putString(FAVORITES, jsonFavorites);

        editor.commit();
    }



    public void addFavorite(Context context, Movie product) {
        ArrayList<Movie> favorites = getFavorites(context);
        if (favorites == null)
            favorites = new ArrayList<Movie>();
        favorites.add(product);
        //notifyDataSetChanged();
        saveFavorites(context, favorites);

    }

    public void removeFavorite(Context context, Movie product, int arg0) {
        ArrayList<Movie> favorites = getFavorites(context);
        if (favorites != null) {
            for(int i=0;i<favorites.size();i++)
            {
                if((favorites.get(i).getName().equals(product.getName()))&&(favorites.get(i).getOverview().equals(product.getOverview())))
                    favorites.remove(i);
            }

            // notifyDataSetChanged();
            saveFavorites(context, favorites);
        }
    }

    public static ArrayList<Movie> getFavorites(Context context) {
        SharedPreferences settings;
        List<Movie> favorites;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);

        if (settings.contains(FAVORITES)) {
            String jsonFavorites = settings.getString(FAVORITES, null);
            Gson gson = new Gson();
            Movie[] favoriteItems = gson.fromJson(jsonFavorites,Movie[].class);

            favorites = Arrays.asList(favoriteItems);
            favorites = new ArrayList<Movie>(favorites);
        } else
            return null;

        return (ArrayList<Movie>) favorites;
    }
}
