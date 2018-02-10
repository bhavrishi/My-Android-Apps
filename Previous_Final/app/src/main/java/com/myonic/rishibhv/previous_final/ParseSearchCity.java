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

class ParseSearchCity {
    static JSONArray dataTrackArray=null;

    LinkedHashMap<Integer,List<String>> map=new LinkedHashMap<Integer, List<String>>();
    ArrayList<String> l = new ArrayList<>();
    public  static class TriviaJSONParser {
        public static ArrayList<AutocompleteCity> parseQuestions(String s)throws JSONException {
            ArrayList<AutocompleteCity> qlist = new ArrayList<AutocompleteCity>();

            try {
                JSONObject root = new JSONObject(s);
                Log.d("demoobj", root.toString());
                // JSONObject resultobj = root.getJSONObject("feed");
                //  JSONObject trackmatchobj = resultobj.getJSONObject("entry");

                dataTrackArray= root.getJSONArray("predictions");
                Log.d("demoobj", dataTrackArray.toString());
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < dataTrackArray.length(); i++) {
                JSONObject js = dataTrackArray.getJSONObject(i);

                AutocompleteCity track = new AutocompleteCity();
                track.setCity(js.optString("description"));
                track.setPlaceid(js.optString("place_id"));



                qlist.add(track);
                Log.d("demolist",qlist.toString());
            }
           /* for(Map.Entry<Integer, List<String>> entry: map.entrySet()) {

                Log.d("demomap",entry.getKey() + " = " + entry.getValue());
            }*/

            return qlist;
        }}}
