package com.inclass.uncc.inclass07_group_01;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Rishi on 23/10/17.
 */

public class FilterTable {
    static final String TABLE_NAME="Filter";
    static final String COLUMN_ID="id";
    static final String COLUMN_NAME="name";
    static final String COLUMN_Price="price";
    static final String COLUMN_ThumbUrl="thumb_url";

    static public void OnCreate(SQLiteDatabase db){
        StringBuilder sb=new StringBuilder();
        sb.append("CREATE TABLE "+TABLE_NAME + " ( ");
        sb.append(COLUMN_ID +" integer primary key autoincrement ,");
        sb.append(COLUMN_NAME + " text not null, ");
        sb.append(COLUMN_Price + " text not null, ");
        sb.append(COLUMN_ThumbUrl + " text not null); ");

        try{
            db.execSQL(sb.toString());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    static public void OnUpgrade(SQLiteDatabase db){
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        FilterTable.OnCreate(db);
    }
}

