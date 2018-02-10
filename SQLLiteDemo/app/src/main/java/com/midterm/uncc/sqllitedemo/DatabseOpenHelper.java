package com.midterm.uncc.sqllitedemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Rishi on 21/10/17.
 */

public class DatabseOpenHelper extends SQLiteOpenHelper {

    static final String DB_NAME="mynotes.db";
    static final int DB_VERSION=6;
    public DatabseOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
NotesTable.onCreate(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
NotesTable.onUpgrade(sqLiteDatabase,i,i1);
    }
}
