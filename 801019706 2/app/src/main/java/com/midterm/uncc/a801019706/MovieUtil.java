package com.midterm.uncc.a801019706;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by Rishi on 16/10/17.
 */

class MovieUtil {
    static JSONArray dataTrackArray = null;

    static List<String> l = new ArrayList<>();
    static public class MovieJSONParser {
        public static ArrayList<Movie> parseMovies(String s) throws JSONException {
            ArrayList<Movie> qlist = new ArrayList<Movie>();

            try {
                JSONObject root = new JSONObject(s);
                Log.d("demoobj", root.toString());
             //   JSONObject resultobj = root.getJSONObject("array");


                dataTrackArray = root.getJSONArray("results");
                Log.d("demoobj", dataTrackArray.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < dataTrackArray.length(); i++) {
                JSONObject js = dataTrackArray.getJSONObject(i);
                String img[] = null;
                Movie movie = new Movie();
                movie.setId(js.optString("id"));
                movie.setRating(js.optString("vote_average"));
                movie.setDate(js.optString("release_date"));
                movie.setName(js.optString("title"));
                movie.setOverview(js.optString("overview"));
                movie.setPopularity(js.optString("popularity"));
                movie.setPosterpath(js.optString("poster_path"));




                qlist.add(movie);

            }
            Log.d("demolist", qlist.toString());

            return qlist;
        }
    }
}
