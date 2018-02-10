package com.inclass.uncc.inclass07_group_01;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rishi on 23/10/17.
 */

class ItunesUtil { static JSONArray dataTrackArray;

    static List<String> l = new ArrayList<>();
    static public class ItunesJSONParser {
        public static ArrayList<ITunes> parseApps(String s) throws JSONException {
            ArrayList<ITunes> qlist = new ArrayList<ITunes>();

            try {
                JSONObject root = new JSONObject(s);
             //   Log.d("demoobj", root.toString());
                JSONObject resultobj = root.getJSONObject("feed");
                //  JSONObject trackmatchobj = resultobj.getJSONObject("entry");

                dataTrackArray= resultobj.getJSONArray("entry");
                Log.d("demoobj", dataTrackArray.toString());
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < dataTrackArray.length(); i++) {
                JSONObject js = dataTrackArray.getJSONObject(i);
                String img[] =null;
                ITunes track = new ITunes();
                track.setPrice(js.getJSONObject("im:price").getString("label"));
                track.setName(js.getJSONObject("im:name").getString("label"));
Log.d("demos",track.getName());



                JSONArray dataImageArray = js.getJSONArray("im:image");

              /*  question.setChoices(jsChoiceobj.getString("choice"));
                JSONArray temp=jsChoiceobj.getJSONArray("choice");
                question.setAnswer(jsChoiceobj.getInt("answer"));*/
              //  l = new ArrayList<String>();
                //img=new String[dataImageArray.length()];
                for (int q = 0; q < dataImageArray.length(); q++) {

                    JSONObject jsimgObj = dataImageArray.getJSONObject(q);
                    int height = jsimgObj.getJSONObject("attributes").getInt("height");
                    if(height == 53)
                    {
                        track.setImage(jsimgObj.getString("label"));
                    }
                    else if (height == 100)
                    {
                        track.setImage(jsimgObj.getString("label"));
                    }

                }

                qlist.add(track);
                Log.d("demolist",qlist.get(0).getName());
            }
           /* for(Map.Entry<Integer, List<String>> entry: map.entrySet()) {

                Log.d("demomap",entry.getKey() + " = " + entry.getValue());
            }*/

            return qlist;
        }}}
