package com.myonic.rishibhv.previousmap;

import android.os.AsyncTask;
import android.util.Log;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Rishi on 26/11/17.
 */

public class AsyncJSON extends AsyncTask<Void,ArrayList<Hotel>,ArrayList<Hotel>> {
    InputStream isin; ArrayList<Hotel> hotels;
    public AsyncJSON(InputStream is) {
        isin=is;
    }

    @Override
    protected void onPostExecute(ArrayList<Hotel> aVoid) {
        super.onPostExecute(aVoid);


    }

    @Override
    protected ArrayList<Hotel> doInBackground(Void... params) {

hotels=ParseJSON.loadJSONFromAsset(isin);
        Log.d("demo",hotels.toString());

        return hotels;
    }
}
