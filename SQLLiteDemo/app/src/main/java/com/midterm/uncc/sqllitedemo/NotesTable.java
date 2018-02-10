package com.midterm.uncc.sqllitedemo;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Rishi on 21/10/17.
 */

public class NotesTable {
    static final String TABLE_NAME="notes";
    static final String COLUMN_ID="_id";
    static final String COLUMN_SUB="sub";
    static final String COLUMN_TEXT="text";

    static public void onCreate(SQLiteDatabase db)
    {
StringBuilder sb=new StringBuilder();
        sb.append("CREATE TABLE "+ TABLE_NAME+"(");
        sb.append(COLUMN_ID +" integer primary key autoincrement, ");
        sb.append(COLUMN_SUB +" text not null, ");
        sb.append(COLUMN_TEXT +" text not null);");
        try{
            Log.d("demo",sb.toString());
            db.execSQL(sb.toString());
        }catch (SQLException e)
        {
e.getMessage();
        }

    }
    static public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion)
    {
db.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME);
NotesTable.onCreate(db);
    }
}
