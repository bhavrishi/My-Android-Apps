package itis5180.inclass7;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Assignment: InClass07
 * Name: AsyncTaskJSON.java
 * Date: 10/24/2017
 * Bradley Wright
 * Anvesh Kottapelli
 * Bhavya Gedi
 */

class AsyncTaskJSON extends AsyncTask<String, Void, ArrayList<ITunes>> {

    private AsyncResponse delegate;
    private Context ccontext;

    AsyncTaskJSON(AsyncResponse delegate, Context applicationContext) {
        this.delegate = delegate;
        ccontext = applicationContext;
    }

    private ProgressDialog dialog;

    @Override
    protected void onPreExecute() {
        dialog = new ProgressDialog(ccontext);
        dialog.setMessage("Loading");
        dialog.setCancelable(false);
        dialog.show();
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(ArrayList<ITunes> movie) {
        super.onPostExecute(movie);
        delegate.processFinish(movie);
        Log.d("demointent", movie.toString());
        dialog.dismiss();
    }

    @Override
    protected ArrayList<ITunes> doInBackground(String... strings) {

        try {
            URL url = new URL(strings[0]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            int statuscpde = con.getResponseCode();
            if (statuscpde == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = reader.readLine();
                while (line != null) {
                    sb.append(line);
                    line = reader.readLine();
                }
                Log.d("demo", sb.toString());
                return ItunesUtil.ItunesJSONParser.parseApps(sb.toString());
            }

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    interface AsyncResponse {
        void processFinish(ArrayList<ITunes> output);
    }
}

