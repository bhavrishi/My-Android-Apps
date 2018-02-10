package com.inclass.uncc.group23_inclass09zip;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;



class SignUPJSONUtil {
    public static ArrayList<SignUp> parseSignUp(String string) throws JSONException {
        ArrayList<SignUp> qlist = new ArrayList<SignUp>();


            JSONObject root = new JSONObject(string);
            Log.d("demoobj", root.toString());

            SignUp user = new SignUp();
            user.setFname(root.optString("user_fname"));
        user.setFname(root.optString("user_fname"));
        user.setLname(root.optString("user_lname"));
        user.setEmail(root.optString("user_email"));
        user.setToken(root.optString("token"));
        user.setUserid(root.optInt("user_id"));






            qlist.add(user);


        Log.d("demolist", qlist.toString());

        return qlist;
    }
}
