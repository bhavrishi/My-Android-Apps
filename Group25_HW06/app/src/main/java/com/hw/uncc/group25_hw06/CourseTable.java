package com.hw.uncc.group25_hw06;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Rishi on 31/10/17.
 */

public class CourseTable {
    static final String TABLE_NAME = "course";
    static final String COLUMN_ID = "id";
    static final String COLUMN_TITLE = "ctitle";
    static final String COLUMN_TIME = "ctime";
    static final String COLUMN_CREDIT = "ccredit";
    static final String COLUMN_SEM = "csemester";
    static final String COLUMN_DAY = "cday";
    static final String COLUMN_UNAME = "uname";
    static final String COLUMN_PWD = "pwd";
    static final String COLUMN_INST = "instructor";



    static public void OnCreate(SQLiteDatabase db) {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE " + TABLE_NAME + " ( ");
        sb.append(COLUMN_ID + " integer primary key autoincrement ,");
        sb.append(COLUMN_UNAME + " , ");
        sb.append(COLUMN_PWD + " , ");
        sb.append(COLUMN_TITLE + " , ");
        sb.append(COLUMN_DAY + " , ");
        sb.append(COLUMN_TIME + " , ");
        sb.append(COLUMN_SEM + " , ");
        sb.append(COLUMN_INST + " , ");
        sb.append(COLUMN_CREDIT + " ); ");
Log.d("demo",sb.toString());
        try {
            db.execSQL(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void OnUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        CourseTable.OnCreate(db);
    }
}
