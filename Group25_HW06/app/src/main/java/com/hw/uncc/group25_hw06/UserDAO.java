package com.hw.uncc.group25_hw06;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Rishi on 02/11/17.
 */

public class UserDAO {
    private SQLiteDatabase db;
    String value1,key;
    UserDAO(SQLiteDatabase db) {
        this.db = db;
    }

    long save(User user) {
        ContentValues value = new ContentValues();
        HashMap<String,String> map=user.getUpwd();
        for ( Map.Entry<String, String> entry : map.entrySet()) {
            key = entry.getKey();
            value1= entry.getValue();
            // do something with key and/or tab
        }
        // Map.Entry<String,String> entry= (Map.Entry<String, String>) map.entrySet().;
        value.put(UserTable.COLUMN_UNAME, key);
        value.put(UserTable.COLUMN_PWD, value1);
        value.put(UserTable.COLUMN_FNAME, user.getFname());
        value.put(UserTable.COLUMN_LNAME, user.getLname());

        return db.insert(UserTable.TABLE_NAME, null, value);
    }



    boolean delete(User tune) {
        return db.delete(UserTable.TABLE_NAME, UserTable.COLUMN_ID + "=?", new String[]{tune.getUserid() + ""}) > 0;
    }

    boolean update(User user) {
        ContentValues value = new ContentValues();
        HashMap<String,String> map=user.getUpwd();
        Map.Entry<String,String> entry= (Map.Entry<String, String>) map.entrySet();
        value.put(UserTable.COLUMN_UNAME, key);
        value.put(UserTable.COLUMN_PWD, value1);
        value.put(UserTable.COLUMN_FNAME, user.getFname());
        value.put(UserTable.COLUMN_LNAME, user.getLname());
        return db.update(UserTable.TABLE_NAME, value, UserTable.COLUMN_ID + "=?", new String[]{user.getUserid() + ""}) > 0;

    }



    User get(long id) {
        User tune = null;
        Cursor c = db.query(true, CourseTable.TABLE_NAME, new String[]{UserTable.COLUMN_ID, UserTable.COLUMN_UNAME, UserTable.COLUMN_PWD,
                        UserTable.COLUMN_FNAME,UserTable.COLUMN_LNAME}
                , CourseTable.COLUMN_ID + "=?", new String[]{id + ""}, null, null, null, null);

        if (c != null && c.moveToFirst()) {
            tune = buildTuneFromCursor(c);
            if (!c.isClosed()) {
                c.close();
            }
        }

        return null;
    }

    List<User> getAll() {
        List<User> notes = new ArrayList<>();
        Cursor c = db.query(UserTable.TABLE_NAME,
                new String[]{UserTable.COLUMN_ID, UserTable.COLUMN_UNAME, UserTable.COLUMN_PWD,
                        UserTable.COLUMN_FNAME,UserTable.COLUMN_LNAME}, null,
                null, null, null, null);
        if (c != null && c.moveToFirst()) {
            do {
                User user = buildTuneFromCursor(c);
                notes.add(user);
            } while (c.moveToNext());
        }
        return notes;
    }

    private User buildTuneFromCursor(Cursor c) {
        User user = null;
        if (c != null) {
            user = new User();
            user.setUserid(c.getLong(0));
            HashMap<String,String> map=new HashMap<String, String>();
            map.put(c.getString(1),c.getString(2));
            user.setUpwd((HashMap<String, String>) map);
            user.setFname(c.getString(3));
            user.setLname(c.getString(4));


        }
        return user;
    }

}
