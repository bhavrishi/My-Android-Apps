package itis5180.inclass7;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Assignment: InClass07
 * Name: FilterDAO.java
 * Date: 10/24/2017
 * Bradley Wright
 * Anvesh Kottapelli
 * Bhavya Gedi
 */

class FilterDAO {
    private SQLiteDatabase db;

    FilterDAO(SQLiteDatabase db) {
        this.db = db;
    }

    long save(ITunes tune) {
        ContentValues value = new ContentValues();
        value.put(FilterTable.COLUMN_NAME, tune.getName());
        value.put(FilterTable.COLUMN_Price, tune.getPrice());
        value.put(FilterTable.COLUMN_ThumbUrl, tune.getImage());

        return db.insert(FilterTable.TABLE_NAME, null, value);
    }

    boolean update(ITunes tune) {
        ContentValues value = new ContentValues();
        value.put(FilterTable.COLUMN_NAME, tune.getName());
        value.put(FilterTable.COLUMN_Price, tune.getPrice());
        value.put(FilterTable.COLUMN_ThumbUrl, tune.getImage());
        return db.update(FilterTable.TABLE_NAME, value, FilterTable.COLUMN_ID + "=?", new String[]{tune.getId() + ""}) > 0;

    }

    boolean delete(ITunes tune) {
        return db.delete(FilterTable.TABLE_NAME, FilterTable.COLUMN_NAME + "=?", new String[]{tune.getName() + ""}) > 0;
    }

    ITunes get(long id) {
        ITunes tune = null;
        Cursor c = db.query(true, FilterTable.TABLE_NAME, new String[]{FilterTable.COLUMN_ID, FilterTable.COLUMN_NAME, FilterTable.COLUMN_Price, FilterTable.COLUMN_ThumbUrl}
                , FilterTable.COLUMN_ID + "=?", new String[]{id + ""}, null, null, null, null);

        if (c != null && c.moveToFirst()) {
            tune = buildTuneFromCursor(c);
            if (!c.isClosed()) {
                c.close();
            }
        }

        return null;
    }

    List<ITunes> getAll() {
        List<ITunes> notes = new ArrayList<>();
        Cursor c = db.query(FilterTable.TABLE_NAME, new String[]{FilterTable.COLUMN_ID, FilterTable.COLUMN_NAME, FilterTable.COLUMN_Price, FilterTable.COLUMN_ThumbUrl}, null,
                null, null, null, null);
        if (c != null && c.moveToFirst()) {
            do {
                ITunes tune = buildTuneFromCursor(c);
                notes.add(tune);
            } while (c.moveToNext());
        }
        return notes;
    }

    private ITunes buildTuneFromCursor(Cursor c) {
        ITunes tune = null;
        if (c != null) {
            tune = new ITunes();
            tune.setId(c.getLong(0));
            tune.setName(c.getString(1));
            tune.setPrice(c.getString(2));
            tune.setImage(c.getString(3));
        }
        return tune;
    }

}
