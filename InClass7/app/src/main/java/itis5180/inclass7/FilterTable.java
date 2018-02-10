package itis5180.inclass7;

import android.database.sqlite.SQLiteDatabase;

/**
 * Assignment: InClass07
 * Name: FilterTable.java
 * Date: 10/24/2017
 * Bradley Wright
 * Anvesh Kottapelli
 * Bhavya Gedi
 */

public class FilterTable {
    static final String TABLE_NAME = "Filter";
    static final String COLUMN_ID = "id";
    static final String COLUMN_NAME = "name";
    static final String COLUMN_Price = "price";
    static final String COLUMN_ThumbUrl = "thumb_url";

    static public void OnCreate(SQLiteDatabase db) {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE " + TABLE_NAME + " ( ");
        sb.append(COLUMN_ID + " integer primary key autoincrement ,");
        sb.append(COLUMN_NAME + " text not null, ");
        sb.append(COLUMN_Price + " text not null, ");
        sb.append(COLUMN_ThumbUrl + " text not null); ");

        try {
            db.execSQL(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void OnUpgrade(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        FilterTable.OnCreate(db);
    }
}
