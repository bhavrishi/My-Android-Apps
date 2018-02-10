package itis5180.inclass7;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Assignment: InClass07
 * Name: DataBaseOpenHelper.java
 * Date: 10/24/2017
 * Bradley Wright
 * Anvesh Kottapelli
 * Bhavya Gedi
 */

public class DataBaseOpenHelper extends SQLiteOpenHelper{
    private static final String DBNAME="itunes.db";
    private static final int version=1;

    public DataBaseOpenHelper(Context context) {
        super(context, DBNAME, null, version);
        //context.deleteDatabase(DBNAME);
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
