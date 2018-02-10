package com.inclass.uncc.inclass07_group_1;

/**
 * Created by Rishi on 24/10/17.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseOpenHelper extends SQLiteOpenHelper{
    static final String DBNAME="mynotes1.db";
    static final int version=1;

    public DataBaseOpenHelper(Context context) {
        super(context, DBNAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        FilterTable.OnCreate(db);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        FilterTable.OnUpgrade(db);

    }
}
