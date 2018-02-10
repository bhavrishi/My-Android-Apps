package com.inclass.uncc.inclass08_23;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

// Created by Rishi on 30/10/17.



public class AsyncReceipe extends AsyncTask<String,Void,ArrayList<Recipe>> {
    private ProgressDialog dialog;
    //  uncc.com.inclass05_group23.AsyncActivityRecipe.Idata recipeActivity;
  /*  Context context;

    public AsyncReceipe(Context context) {
        this.context = context;
    }*/




    @Override
    protected void onPreExecute() {
  /*      dialog = new ProgressDialog(context);
        dialog.setMessage("Doing something, please wait.");
        dialog.show();*/
    }


    @Override
    protected void onPostExecute(ArrayList<Recipe> recipes) {
        super.onPostExecute(recipes);
        //RecipeActivity recipeActivity =new RecipeActivity();
       /* if (dialog.isShowing()) {
            dialog.dismiss();
        }*/

        // getRecipes(recipes);
    }


    @Override
    protected ArrayList<Recipe> doInBackground(String... params) {
        BufferedReader reader = null;
        StringBuilder sb = null;
        try {
            URL url = new URL(params[0]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            int statuscode = con.getResponseCode();
            if (statuscode == HttpURLConnection.HTTP_OK) {

                reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                sb = new StringBuilder();
                String line = "";
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");

                }
            }
            return RecipeUtil.jsonParsor(sb.toString());


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
