package com.hw.uncc.group25_hw06;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Rishi on 01/11/17.
 */

public class InstructorTable {
    static final String TABLE_NAME = "instructor";
    static final String COLUMN_ID = "id";
    static final String COLUMN_IMAGE = "image";
    static final String COLUMN_FNAME = "fname";
    static final String COLUMN_LNAME = "lname";
    static final String COLUMN_EMAIL = "email";
    static final String COLUMN_WEBSITE = "website";
    static final String COLUMN_UNAME = "uname";

    static public void OnCreate(SQLiteDatabase db) {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE " + TABLE_NAME + " ( ");
        sb.append(COLUMN_ID + " integer primary key autoincrement ,");
        sb.append(COLUMN_IMAGE + " , ");
        sb.append(COLUMN_FNAME + " , ");
        sb.append(COLUMN_LNAME + " , ");
        sb.append(COLUMN_EMAIL + " , ");
        sb.append(COLUMN_UNAME + " , ");
        sb.append(COLUMN_WEBSITE + " ); ");
        Log.d("demo",sb.toString());
        try {
            db.execSQL(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void OnUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        InstructorTable.OnCreate(db);
    }}