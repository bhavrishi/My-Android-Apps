package com.inclass.uncc.previossax;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Rishi on 01/10/17.
 */

public class AsyncTaskSAX extends AsyncTask<String,Void,ArrayList<Weather>> {
    @Override
    protected void onPostExecute(ArrayList<Weather> weathers) {
        super.onPostExecute(weathers);
        if(weathers!=null)
        {
            Log.d("demo",weathers.toString());
        }
    }

    @Override
    protected ArrayList<Weather> doInBackground(String... strings) {
        try{
        URL url=new URL(strings[0]);
        HttpURLConnection con= (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        int statuscpde=con.getResponseCode();
        if(statuscpde==HttpURLConnection.HTTP_OK) {
            // Thread.sleep(10000);
            InputStream in=con.getInputStream();
return WeatherUTil.PSAXParser.parseParser(in);
            }

        } catch (ProtocolException e1) {
            e1.printStackTrace();
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }

        return null;

}}
