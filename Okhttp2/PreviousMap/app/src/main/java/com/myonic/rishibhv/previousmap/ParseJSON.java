package com.myonic.rishibhv.previousmap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Rishi on 26/11/17.
 */

public class ParseJSON {
    public static ArrayList<Hotel> loadJSONFromAsset(InputStream is) {
        ArrayList<Hotel> locList = new ArrayList<>();
        String json = null;
        try {

            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        try {
            JSONObject obj = new JSONObject(json);
            JSONArray m_jArry = obj.getJSONArray("results");

            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                Hotel location = new Hotel();
                location.setLat((float) jo_inside.getJSONObject("location").getDouble("latitude"));
                location.setLat((float) jo_inside.getJSONObject("location").getDouble("longitude"));

                location.setName(jo_inside.getString("name"));



                //Add your values in your `ArrayList` as below:
                locList.add(location);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return locList;
    }
}
