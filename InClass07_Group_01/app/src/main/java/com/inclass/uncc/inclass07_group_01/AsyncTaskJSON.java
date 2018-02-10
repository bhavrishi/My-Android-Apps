package com.inclass.uncc.inclass07_group_01;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Rishi on 23/10/17.
 */

public class AsyncTaskJSON extends AsyncTask<String, Void, ArrayList<ITunes>> {


    AsyncResponse delegate;
    public Context ccontext;


    public AsyncTaskJSON(AsyncResponse delegate, Context applicationContext) {
        this.delegate = delegate;
        ccontext = applicationContext;
    }

    public ProgressDialog dialog;


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
       /* if(dialog.isShowing())
            dialog.dismiss();*/
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
                //    Thread.sleep(2000);
                return ItunesUtil.ItunesJSONParser.parseApps(sb.toString());

            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //   return null;
        return null;
    }

    static public interface AsyncResponse {
        void processFinish(ArrayList<ITunes> output);

    }
}

