package com.inclass.uncc.previousfragment;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Rishi on 29/10/17.
 */


public class MessageTable {
    static final String TABLE_NAME = "message";
    static final String COLUMN_ID = "id";
    static final String COLUMN_NAME = "msg";


    static public void OnCreate(SQLiteDatabase db) {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE " + TABLE_NAME + " ( ");
        sb.append(COLUMN_ID + " integer primary key autoincrement ,");

        sb.append(COLUMN_NAME + " text not null); ");

        try {
            Log.d("demo",sb.toString());
            db.execSQL(sb.toString());
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    static void OnUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        MessageTable.OnCreate(db);
    }
}
