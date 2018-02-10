package com.hwork.uncc.previouslist;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by Rishi on 15/10/17.
 */

class ItunesUtil {
    static JSONArray dataTrackArray=null;

    static LinkedHashMap<Integer,List<String>> map=new LinkedHashMap<Integer, List<String>>();
    static List<String> l = new ArrayList<>();
    public static class TriviaJSONParser {
        static public ArrayList<Itunes> parseQuestions(String s)throws JSONException {
            ArrayList<Itunes> qlist = new ArrayList<Itunes>();

            try {
                JSONObject root = new JSONObject(s);
                Log.d("demoobj", root.toString());
               // JSONObject resultobj = root.getJSONObject("feed");
              //  JSONObject trackmatchobj = resultobj.getJSONObject("entry");

                dataTrackArray= root.getJSONObject("feed").getJSONArray("entry");
                Log.d("demoobj", dataTrackArray.toString());
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < dataTrackArray.length(); i++) {
                JSONObject js = dataTrackArray.getJSONObject(i);
                String img[] =null;
                ITu track = new Itunes();
                track.setSummary(js.getJSONObject("summary").optString("label"));
                track.setTitle(js.getJSONObject("im:name").optString("label"));
                track.setDate(js.getJSONObject("im:releaseDate").optString("label"));



                JSONArray dataImageArray = js.getJSONArray("im:image");

              /*  question.setChoices(jsChoiceobj.getString("choice"));
                JSONArray temp=jsChoiceobj.getJSONArray("choice");
                question.setAnswer(jsChoiceobj.getInt("answer"));*/
                l = new ArrayList<String>();
                //img=new String[dataImageArray.length()];
                for (int q = 0; q < dataImageArray.length(); q=q++) {

                    JSONObject jsimgObj = dataImageArray.getJSONObject(q);
                    int height = jsimgObj.getJSONObject("attributes").getInt("height");
                    if(height == 55)
                    {
                       track.setImg1(jsimgObj.getString("label"));
                    }
                    else if (height == 170)
                    {
                        track.setImg2(jsimgObj.getString("label"));
                    }

                }

                qlist.add(track);
                Log.d("demolist",qlist.toString());
            }
           /* for(Map.Entry<Integer, List<String>> entry: map.entrySet()) {

                Log.d("demomap",entry.getKey() + " = " + entry.getValue());
            }*/

            return qlist;
        }}}
