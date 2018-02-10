package com.hwork.uncc.group25_hw05;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by Rishi on 15/10/17.
 */

public class SimilarUtil {
    static JSONArray dataTrackArray=null;

    static LinkedHashMap<Integer,List<String>> map=new LinkedHashMap<Integer, List<String>>();
    static List<String> l = new ArrayList<>();
    static public class TriviaJSONParser {

        public static ArrayList<Track> parseQuestions(String s) throws JSONException {
            ArrayList<Track> qlist = new ArrayList<Track>();

            try {
                JSONObject root = new JSONObject(s);
                Log.d("demoobj", root.toString());
                JSONObject resultobj = root.getJSONObject("similartracks");
             //   JSONObject trackmatchobj = resultobj.getJSONObject("trackmatches");

                dataTrackArray= resultobj.getJSONArray("track");
                Log.d("demoobj", dataTrackArray.toString());
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < dataTrackArray.length(); i++) {
                JSONObject js = dataTrackArray.getJSONObject(i);
                String img[] =null;
                Track track = new Track();
               // track.setArtist(js.optString("artist"));
                track.setName(js.optString("name"));
                track.setUrl(js.optString("url"));
                // track.setImage(js.optString("image"));
                /*question.setId(js.getInt("id"));
                question.setQuestion(js.optString("text"));

                question.setImage(js.optString("image"));*/
JSONObject jsartist=js.getJSONObject("artist");
                track.setArtist(jsartist.getString("name"));

                JSONArray dataImageArray = js.getJSONArray("image");

              /*  question.setChoices(jsChoiceobj.getString("choice"));
                JSONArray temp=jsChoiceobj.getJSONArray("choice");
                question.setAnswer(jsChoiceobj.getInt("answer"));*/
                l = new ArrayList<String>();
                img=new String[dataImageArray.length()];
                for (int q = 0; q < dataImageArray.length(); q++) {

                    JSONObject jsimgObj = dataImageArray.getJSONObject(q);
                    img[q]=jsimgObj.getString("#text");

                }
                track.setImg(img);
                Log.d("demosize",""+l.size());



                qlist.add(track);
                Log.d("demolist",qlist.toString());
            }
           /* for(Map.Entry<Integer, List<String>> entry: map.entrySet()) {

                Log.d("demomap",entry.getKey() + " = " + entry.getValue());
            }*/

            return qlist;
        }}}
