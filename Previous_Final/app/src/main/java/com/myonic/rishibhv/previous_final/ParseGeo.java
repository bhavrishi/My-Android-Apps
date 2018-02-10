package com.myonic.rishibhv.previous_final;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static com.myonic.rishibhv.previous_final.ParseSearchCity.dataTrackArray;

/**
 * Created by Rishi on 03/12/17.
 */

public class ParseGeo {



    LinkedHashMap<Integer,List<String>> map=new LinkedHashMap<Integer, List<String>>();
   static ArrayList<String> l = new ArrayList<>();
    public  static class TriviaJSONParser {
        public static ArrayList<String> parseQuestions(String s) throws JSONException {
            //    ArrayList<AutocompleteCity> qlist = new ArrayList<AutocompleteCity>();

            try {
                JSONObject root = new JSONObject(s);
                Log.d("demoobj", root.toString());
                // JSONObject resultobj = root.getJSONObject("feed");
                //  JSONObject trackmatchobj = resultobj.getJSONObject("entry");

                l.add(root.getJSONObject("result").getJSONObject("geometry").getJSONObject("location").optString("lat"));
                l.add(root.getJSONObject("result").getJSONObject("geometry").getJSONObject("location").optString("lng"));
                //  Log.d("demoobj", dataTrackArray.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }

            Log.d("demolist", l.get(0) + " " + l.get(1));

           /* for(Map.Entry<Integer, List<String>> entry: map.entrySet()) {

                Log.d("demomap",entry.getKey() + " = " + entry.getValue());
            }*/

            return l;
    }}}