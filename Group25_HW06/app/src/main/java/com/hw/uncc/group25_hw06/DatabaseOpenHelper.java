package com.hw.uncc.group25_hw06;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Rishi on 01/11/17.
 */

public class DatabaseOpenHelper  extends SQLiteOpenHelper {
    private static final String DBNAME="user_course.db";
    private static final int version=7;

    public DatabaseOpenHelper(Context context) {
        super(context, DBNAME, null, version);
        //context.deleteDatabase(DBNAME);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        CourseTable.OnCreate(db);
        UserTable.OnCreate(db);
        InstructorTable.OnCreate(db);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        CourseTable.OnUpgrade(db,oldVersion,newVersion);
        UserTable.OnUpgrade(db,oldVersion,newVersion);
        InstructorTable.OnUpgrade(db,oldVersion,newVersion);
    }
}
