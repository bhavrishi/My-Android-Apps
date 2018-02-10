package com.myonic.rishibhv.group25_hw06;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Rishi on 14/11/17.
 */
@IgnoreExtraProperties
public class Post {

    String name;
    String msg;
    String time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    String id,iduser;

    public String getIduser() {
        return iduser;
    }

    public void setIduser(String iduser) {
        this.iduser = iduser;
    }

    public Post(String id, String iduser, String name, String msg, String time) {
      this.id=id;
        this.name = name;
        this.msg = msg;
        this.time = time;
        this.iduser=iduser;

    }

    public Post() {
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Post{" +
                "name='" + name + '\'' +
                ", msg='" + msg + '\'' +
                ", time='" + time + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
