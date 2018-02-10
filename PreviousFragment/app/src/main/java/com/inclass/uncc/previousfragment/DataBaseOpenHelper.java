package com.inclass.uncc.previousfragment;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Rishi on 29/10/17.
 */

class DataBaseOpenHelper  extends SQLiteOpenHelper {
    private static final String DBNAME="message.db";
    private static final int version=1;

    public DataBaseOpenHelper(Context context) {
        super(context, DBNAME, null, version);
        //context.deleteDatabase(DBNAME);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        MessageTable.OnCreate(db);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        MessageTable.OnUpgrade(db,oldVersion,newVersion);

    }
}
