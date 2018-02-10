package com.myonic.rishibhv.group25_hw06;

import java.util.ArrayList;

/**
 * Created by rosyazad on 19/11/17.
 */

public class Friends {
    String id;
    String userFriend;
    String requestFromUser;
    String addFriend;

    public Friends() {
    }

    public Friends(String id, String userFriend, String requestFromUser, String addFriend) {
        this.id = id;
        this.userFriend = userFriend;
        this.requestFromUser = requestFromUser;
        this.addFriend = addFriend;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserFriend() {
        return userFriend;
    }

    public void setUserFriend(String userFriend) {
        this.userFriend = userFriend;
    }

    public String getRequestFromUser() {
        return requestFromUser;
    }

    public void setRequestFromUser(String requestFromUser) {
        this.requestFromUser = requestFromUser;
    }

    public String getAddFriend() {
        return addFriend;
    }

    public void setAddFriend(String addFriend) {
        this.addFriend = addFriend;
    }

    @Override
    public String toString() {
        return "Friends{" +
                "id='" + id + '\'' +
                ", userFriend='" + userFriend + '\'' +
                ", requestFromUser='" + requestFromUser + '\'' +
                ", addFriend='" + addFriend + '\'' +
                '}';
    }
}
