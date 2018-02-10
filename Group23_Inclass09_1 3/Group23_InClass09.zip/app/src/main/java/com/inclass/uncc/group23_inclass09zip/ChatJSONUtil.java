package com.inclass.uncc.group23_inclass09zip;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rishi on 07/11/17.
 */

public class ChatJSONUtil
{
    static JSONArray dataTrackArray = null;

        static List<String> l = new ArrayList<>();
static public class ChatJSONParser {
    public static ArrayList<Chat> parseChats(String s) throws JSONException {
        ArrayList<Chat> qlist = new ArrayList<Chat>();

        try {
            JSONObject root = new JSONObject(s);
            Log.d("demoobj", root.toString());
            //   JSONObject resultobj = root.getJSONObject("array");


            dataTrackArray = root.getJSONArray("messages");
            Log.d("demoobj", dataTrackArray.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < dataTrackArray.length(); i++) {
            JSONObject js = dataTrackArray.getJSONObject(i);
            String img[] = null;
            Chat chat = new Chat();
            chat.setName(js.optString("user_fname")+" "+js.optString("user_lname"));
            chat.setMsg(js.optString("message"));
            chat.setTime(js.optString("created_at"));
            chat.setUserid(js.optInt("user_id"));
chat.setMsgid(js.optInt("id"));



            qlist.add(chat);

        }
        Log.d("demolist", qlist.toString());

        return qlist;
    }
}
}
