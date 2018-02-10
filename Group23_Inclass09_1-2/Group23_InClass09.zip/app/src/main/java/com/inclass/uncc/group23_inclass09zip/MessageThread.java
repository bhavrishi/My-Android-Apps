package com.inclass.uncc.group23_inclass09zip;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by Rishi on 07/11/17.
 */

public class MessageThread implements Serializable {
    private String user_fname,user_lname,title,created_at;
    int user_id,id;


    @Override
    public String toString() {
        return "MessageThread{" +
                "user_fname='" + user_fname + '\'' +
                ", user_lname='" + user_lname + '\'' +
                ", user_id='" + user_id + '\'' +
                ", id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", created_at='" + created_at + '\'' +
                '}';
    }

    public String getUser_fname() {
        return user_fname;
    }

    public void setUser_fname(String user_fname) {
        this.user_fname = user_fname;
    }

    public String getUser_lname() {
        return user_lname;
    }

    public void setUser_lname(String user_lname) {
        this.user_lname = user_lname;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public void createMsgThreads(JSONObject objJsonObject){
        try{

            //JSONObject name =objJsonObject.getJSONObject("im:name");
            String firstname = objJsonObject.optString("user_fname");
            this.setUser_fname(firstname);

            String lastName =objJsonObject.optString("user_lname");
            this.setUser_lname(lastName);
            int userId =objJsonObject.optInt("user_id");
            this.setUser_id(userId);
            int Id =objJsonObject.optInt("id");
            this.setId(Id);
            String title =objJsonObject.optString("title");
            this.setTitle(title);
            String createdAt =objJsonObject.optString("created_at");
            this.setCreated_at(createdAt);




        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
