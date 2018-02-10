package com.midterm.uncc.sqllitedemo;

/**
 * Created by Rishi on 21/10/17.
 */

public class Notes {
    private long _id;
    String sub,text;

    public long get_id() {
        return _id;
    }

    public Notes(String sub, String text) {
        this.sub = sub;
        this.text = text;
    }

    public Notes() {
    }

    public void set_id(long _id) {
        this._id = _id;

    }

    public String getSub() {
        return sub;
    }

    @Override
    public String toString() {
        return "Notes{" +
                "_id=" + _id +
                ", sub='" + sub + '\'' +
                ", text='" + text + '\'' +
                '}';
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
