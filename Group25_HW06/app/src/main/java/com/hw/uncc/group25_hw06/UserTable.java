package com.hw.uncc.group25_hw06;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Rishi on 02/11/17.
 */

public class UserTable{
        static final String TABLE_NAME = "user";
        static final String COLUMN_ID = "uid";
        static final String COLUMN_FNAME = "fname";
        static final String COLUMN_LNAME = "lname";
        static final String COLUMN_UNAME = "uname";
        static final String COLUMN_PWD = "pwd";



        static public void OnCreate(SQLiteDatabase db) {
            StringBuilder sb = new StringBuilder();
            sb.append("CREATE TABLE " + TABLE_NAME + " ( ");
            sb.append(COLUMN_ID + " integer primary key autoincrement ,");
            sb.append(COLUMN_UNAME + " , ");
            sb.append(COLUMN_PWD + " , ");
            sb.append(COLUMN_FNAME + " , ");

            sb.append(COLUMN_LNAME + " ); ");
            Log.d("demo",sb.toString());
            try {
                db.execSQL(sb.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        static void OnUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            UserTable.OnCreate(db);
        }
}
