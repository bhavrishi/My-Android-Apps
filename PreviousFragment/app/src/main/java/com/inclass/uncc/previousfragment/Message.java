package com.inclass.uncc.previousfragment;

/**
 * Created by Rishi on 29/10/17.
 */

public class Message {
    long id;

    public Message() {
    }

    public Message(String s) {
        this.msg=s;

    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", msg='" + msg + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    String msg;
}
