package com.inclass.uncc.inclass06_801019706;
/*#Assignment: In Class Assignment $
     #   File Name : Table1_InClass06.zip
        #Full Name:  Bhavya Gedi*/
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;

import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Rishi on 02/10/17.
 */

public class AsyncTaskReceipe extends AsyncTask<String,Integer,ArrayList<Receipe>> {
Context c;ProgressDialog dialog;
    public AsyncTaskReceipe(Context applicationContext) {
        c=applicationContext;
    }

    @Override
        protected void onPostExecute(ArrayList<Receipe> recepies) {
        super.onPostExecute(recepies);
        if (recepies != null) {
            Log.d("demo", recepies.toString());
        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
       // dialog.setProgress(values[0]);

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog=new ProgressDialog(c);
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
       dialog.setMax(100);
        dialog.show();


    }

    @Override
        protected ArrayList<Receipe> doInBackground(String... strings) {
            try{
                URL url=new URL(strings[0]);
                HttpURLConnection con= (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                int statuscpde=con.getResponseCode();
                if(statuscpde==HttpURLConnection.HTTP_OK) {

                    InputStream in=con.getInputStream();
                    Log.d("demo", "connected");
publishProgress();
                   return ReceipeUtil.PSAXParser.parseParser(in);
                }

            } catch (ProtocolException e1) {
                e1.printStackTrace();
            } catch (MalformedURLException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            }
        return null;

        }
}
