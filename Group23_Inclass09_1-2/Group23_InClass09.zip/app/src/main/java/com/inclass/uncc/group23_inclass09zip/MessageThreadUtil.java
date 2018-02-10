package com.inclass.uncc.group23_inclass09zip;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Rishi on 07/11/17.
 */

public class MessageThreadUtil {
    public static ArrayList<MessageThread> jsonParsor(String input) throws JSONException {

        ArrayList<MessageThread> objMessageThreadList = new ArrayList<>();

        JSONObject root = new JSONObject(input);
        /*JSONObject feed = root.getJSONObject("threads");*/
        JSONArray entry = root.getJSONArray("threads");

        for(int i=0;i<entry.length();i++){
            MessageThread messageThread = new MessageThread();
            JSONObject objJsonObject =entry.getJSONObject(i);


            try {
                messageThread.createMsgThreads(objJsonObject);
            } catch (Exception e) {
                e.printStackTrace();
            }

            objMessageThreadList.add(messageThread);

        }

        return objMessageThreadList;
    }
}
