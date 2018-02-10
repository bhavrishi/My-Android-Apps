package itis5180.inclass7;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Assignment: InClass07
 * Name: ItunesUtil.java
 * Date: 10/24/2017
 * Bradley Wright
 * Anvesh Kottapelli
 * Bhavya Gedi
 */

class ItunesUtil {
    private static JSONArray dataTrackArray;

    static List<String> l = new ArrayList<>();

    static class ItunesJSONParser {
        static ArrayList<ITunes> parseApps(String s) throws JSONException {
            ArrayList<ITunes> qlist = new ArrayList<>();

            try {
                JSONObject root = new JSONObject(s);
                JSONObject resultobj = root.getJSONObject("feed");
                dataTrackArray = resultobj.getJSONArray("entry");
                Log.d("demoobj", dataTrackArray.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < dataTrackArray.length(); i++) {
                JSONObject js = dataTrackArray.getJSONObject(i);
                String img[] = null;
                ITunes track = new ITunes();
                track.setPrice(js.getJSONObject("im:price").getString("label"));
                track.setName(js.getJSONObject("im:name").getString("label"));

                JSONArray dataImageArray = js.getJSONArray("im:image");

                for (int q = 0; q < dataImageArray.length(); q++) {

                    JSONObject jsimgObj = dataImageArray.getJSONObject(q);
                    int height = jsimgObj.getJSONObject("attributes").getInt("height");
                    if (height == 53) {
                        track.setImage(jsimgObj.getString("label"));
                    } else if (height == 100) {
                        track.setImage(jsimgObj.getString("label"));
                    }
                }

                qlist.add(track);
            }
            return qlist;
        }
    }
}

