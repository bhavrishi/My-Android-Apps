package com.inclass.uncc.group23_inclass09zip;

/**
 * Created by Rishi on 07/11/17.
 */

public class Chat {
    String name,time,msg;
    int userid; int msgid;

    public int getMsgid() {
        return msgid;
    }

    public void setMsgid(int msgid) {
        this.msgid = msgid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "Chat{" +
                "name='" + name + '\'' +
                ", time='" + time + '\'' +
                ", msg='" + msg + '\'' +
                ", userid=" + userid +
                ", msgid=" + msgid +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
