package com.inclass.uncc.inclass07_group_01;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Rishi on 23/10/17.
 */

class AsyncImage  extends AsyncTask<String, Object, Bitmap> {
    Context ccontext;



    public ProgressDialog dialog;

    @Override
    protected void onPreExecute() {
      /*  dialog = new ProgressDialog(ccontext);
        dialog.setMessage("Loading");
        dialog.setCancelable(false);

        dialog.show();
*/
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Bitmap s) {
        if (s != null) {
            // return s;
            Log.d("demoimg", "" + s);

        } else {
            Log.d("demo", "NO DATA");
        }
        /*if (dialog.isShowing())
            dialog.dismiss();*/
        super.onPostExecute(s);
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        BufferedReader reader = null;
        try {
            URL url = new URL(params[0]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("GET");
            Bitmap bm = BitmapFactory.decodeStream(con.getInputStream());


            return bm;
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }


        return null;
    }
}
