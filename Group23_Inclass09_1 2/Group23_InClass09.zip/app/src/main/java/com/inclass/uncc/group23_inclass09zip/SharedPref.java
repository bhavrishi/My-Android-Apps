package com.inclass.uncc.group23_inclass09zip;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Rishi on 06/11/17.
 */

public class SharedPref {




        public static final String PREFS_NAME = "PRODUCT_APP";
        public static final String FAVORITES = "Product_Favorite";

        public SharedPref() {
            super();
        }

        // This four methods are used for maintaining favorites.
        public void saveFavorites(Context context, ArrayList<SignUp> user) {
            SharedPreferences settings;
            SharedPreferences.Editor editor;

            settings =  context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
            editor = settings.edit();

            Gson gson = new Gson();
            String jsonFavorites = gson.toJson(user);

            editor.putString(FAVORITES, jsonFavorites);

            editor.commit();
        }



        public void addFavorite(Context context, SignUp user) {
            ArrayList<SignUp> favorites = getFavorites(context);
            if (favorites == null)
                favorites = new ArrayList<SignUp>();
            favorites.add(user);
            //notifyDataSetChanged();
            saveFavorites(context, favorites);

        }

        public void removeFavorite(Context context, SignUp product, int arg0) {
            ArrayList<SignUp> favorites = getFavorites(context);
            if (favorites != null) {
                for(int i=0;i<favorites.size();i++)
                {
                    //if((favorites.get(i).getName().equals(product.getName()))&&(favorites.get(i).getOverview().equals(product.getOverview())))
                        favorites.remove(i);
                }

                // notifyDataSetChanged();
                saveFavorites(context, favorites);
            }
        }

        public static ArrayList<SignUp> getFavorites(Context context) {
            SharedPreferences settings;
            List<SignUp> favorites;

            settings = context.getSharedPreferences(PREFS_NAME,
                    Context.MODE_PRIVATE);

            if (settings.contains(FAVORITES)) {
                String jsonFavorites = settings.getString(FAVORITES, null);
                Gson gson = new Gson();
                SignUp[] favoriteItems = gson.fromJson(jsonFavorites,SignUp[].class);

                favorites = Arrays.asList(favoriteItems);
                favorites = new ArrayList<SignUp>(favorites);
            } else
                return null;

            return (ArrayList<SignUp>) favorites;
        }
    }


