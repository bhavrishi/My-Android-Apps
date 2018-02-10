package com.inclass.uncc.group25_hw04zip;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
//import com.google.common.collect.*;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Rishi on 27/09/17.
 */

class TriviaUtil {

    static LinkedHashMap<Integer,List<String>> map=new LinkedHashMap<Integer, List<String>>();
    static List<String> l = new ArrayList<>();
    static public class TriviaJSONParser {

        public static ArrayList<TriviaBean> parseQuestions(String s) throws JSONException {
            ArrayList<TriviaBean> qlist = new ArrayList<TriviaBean>();


            JSONObject root = new JSONObject(s);

            JSONArray dataTriviaArray = root.getJSONArray("questions");

            for (int i = 0; i < dataTriviaArray.length(); i++) {
                JSONObject js = dataTriviaArray.getJSONObject(i);
                TriviaBean question = new TriviaBean();
                question.setId(js.getInt("id"));
                question.setQuestion(js.optString("text"));

                question.setImage(js.optString("image"));
                //  question.setChoices(js.getJSONObject("choices"));

                JSONObject jsChoiceobj =js.getJSONObject("choices");



                   // TriviaBean choice = new TriviaBean();
                    question.setChoices(jsChoiceobj.getString("choice"));
                JSONArray temp=jsChoiceobj.getJSONArray("choice");
                    question.setAnswer(jsChoiceobj.getInt("answer"));
                l = new ArrayList<String>();
              for (int q = 0; q < temp.length(); q++) {

                    Log.d("Type",i+""+temp.getString(q));
                  l.add(temp.getString(q));

                }
                map.put(i,l);
                Log.d("demosize",""+l.size());
               /* for (Map.Entry<Integer, String> entry : map.entrySet()) {
                    Integer key = entry.getKey();
                    String value = entry.getValue();
                 */
                //}
             //   Log.d("demolist",jsChoiceobj.getString("choice"));


qlist.add(question);
             //   Log.d("demolist",qlist.toString());
                }
            for(Map.Entry<Integer, List<String>> entry: map.entrySet()) {

                Log.d("demomap",entry.getKey() + " = " + entry.getValue());
            }

            return qlist; }

    }

public static LinkedHashMap<Integer,List<String>> retrieveMap(){return  map;}

}