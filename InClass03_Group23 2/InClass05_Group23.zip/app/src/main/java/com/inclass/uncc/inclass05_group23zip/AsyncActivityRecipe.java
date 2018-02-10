/*
package com.inclass.uncc.inclass05_group23zip;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
*/
/**
 * Created by Rishi on 25/09/17.
 *//*


public class AsyncActivityRecipe  extends AsyncTask<String,Void,ArrayList<Recipe>> {

    private ProgressDialog dialog;

    @Override
    protected void onPreExecute() {
        dialog.setMessage("Doing something, please wait.");
        dialog.show();
    }

    */
/**
     * Created by rosyazad on 25/09/17.
     *//*




        //Idata recipeActivity;

    */
/*public AsyncActivityRecipe(Idata mainActivity) {
        this.recipeActivity = mainActivity;
    }*//*


        @Override
        protected void onPostExecute(ArrayList<Recipe> recipes) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
            super.onPostExecute(recipes);
            RecipeActivity recipeActivity =new RecipeActivity();
            recipeActivity.getArticles(recipes);
        }


        @Override
        protected ArrayList<Recipe> doInBackground(String... params) {
            BufferedReader reader =null;
            StringBuilder sb =null;
            try {
                URL url =new URL(params[0]);
                HttpURLConnection con =(HttpURLConnection)url.openConnection();
                con.setRequestMethod("GET");
                con.connect();
                int statuscode = con.getResponseCode();
                if(statuscode== HttpURLConnection.HTTP_OK) {

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
                    if(reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        public static interface Idata{

            public void getRecipes(ArrayList<Recipe> objArticle);
        }


    }
}
*/
