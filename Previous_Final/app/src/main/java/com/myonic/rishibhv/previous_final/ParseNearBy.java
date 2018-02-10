package com.myonic.rishibhv.previous_final;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by Rishi on 03/12/17.
 */

public class ParseNearBy{
        static JSONArray dataTrackArray=null;

        LinkedHashMap<Integer,List<String>> map=new LinkedHashMap<Integer, List<String>>();
        ArrayList<String> l = new ArrayList<>();
public  static class TriviaJSONParser {
    public static ArrayList<Place> parseQuestions(String s)throws JSONException {
        ArrayList<Place> qlist = new ArrayList<Place>();

        try {
            JSONObject root = new JSONObject(s);
            Log.d("demoobj", root.toString());
            // JSONObject resultobj = root.getJSONObject("feed");
            //  JSONObject trackmatchobj = resultobj.getJSONObject("entry");

            dataTrackArray= root.getJSONArray("results");
            Log.d("demoobj", dataTrackArray.toString());
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < dataTrackArray.length(); i++) {
            JSONObject js = dataTrackArray.getJSONObject(i);

            Place track = new Place();
            track.setLat(js.getJSONObject("geometry").getJSONObject("location").optString("lat"));
            track.setLang(js.getJSONObject("geometry").getJSONObject("location").optString("lng"));
            track.setImgurl(js.optString("icon"));

            track.setPlacename(js.optString("name"));

            qlist.add(track);
            Log.d("demoPlacelist",qlist.toString());
        }
           /* for(Map.Entry<Integer, List<String>> entry: map.entrySet()) {

                Log.d("demomap",entry.getKey() + " = " + entry.getValue());
            }*/

        return qlist;
    }}}
