package com.midterm.uncc.recyclervw;

/**
 * Created by Rishi on 22/10/17.
 */

public class Email {
    String sub,summary,sender;

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public String getSummary() {
        return summary;
    }

    public Email(String sub, String summary, String sender) {
        this.sub = sub;
        this.summary = summary;
        this.sender = sender;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
