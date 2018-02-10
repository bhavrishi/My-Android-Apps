package com.myonic.rishibhv.firebasedatabases;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Rishi on 12/11/17.
 */
@IgnoreExtraProperties
public class Message {

    public String author;
    public String body;
    public String time;

    public Message() {
        // Default constructor required for calls to DataSnapshot.getValue(Post.class)
    }

    public Message(String author, String body, String time) {
        this.author = author;
        this.body = body;
        this.time = time;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("author", author);
        result.put("body", body);
        result.put("time", time);

        return result;
    }
}
