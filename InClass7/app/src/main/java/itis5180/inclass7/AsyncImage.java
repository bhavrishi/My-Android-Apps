package itis5180.inclass7;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Assignment: InClass07
 * Name: AsyncImage.java
 * Date: 10/24/2017
 * Bradley Wright
 * Anvesh Kottapelli
 * Bhavya Gedi
 */

class AsyncImage extends AsyncTask<String, Object, Bitmap> {

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Bitmap s) {
        if (s != null) {
            Log.d("demoimg", "" + s);
        } else {
            Log.d("demo", "NO DATA");
        }
        super.onPostExecute(s);
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        try {
            URL url = new URL(params[0]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("GET");
            return BitmapFactory.decodeStream(con.getInputStream());
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        return null;
    }
}

