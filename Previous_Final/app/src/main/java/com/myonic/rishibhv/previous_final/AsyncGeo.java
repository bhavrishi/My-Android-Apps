package com.myonic.rishibhv.previous_final;

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
import java.util.List;

/**
 * Created by Rishi on 03/12/17.
 */

public class AsyncGeo extends AsyncTask<String, Void, ArrayList<String>> {


/*

    public Context ccontext;


    public AsyncAutoComplete(Context applicationContext) {

        ccontext = applicationContext;
    }

*/



    @Override
    protected void onPreExecute() {
      /*  dialog = new ProgressDialog(ccontext);
        dialog.setMessage("Loading");
        dialog.setCancelable(false);

        dialog.show();*/

        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(ArrayList<String> track) {
        super.onPostExecute(track);
       /* if(dialog.isShowing())
            dialog.dismiss();*/
        // delegate.processFinish(track);
        //  Log.d("demointent", track.toString());

/*
        if (track.size() > 0) dialog.dismiss();
        else
            dialog.show();*/
    }

    @Override
    protected ArrayList<String> doInBackground(String... strings) {

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
                return ParseGeo.TriviaJSONParser.parseQuestions(sb.toString());

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




}

