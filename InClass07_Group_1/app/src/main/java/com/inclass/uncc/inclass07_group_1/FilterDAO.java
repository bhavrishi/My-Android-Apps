package com.inclass.uncc.inclass07_group_1;

/**
 * Created by Rishi on 23/10/17.
 */

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Filter;

public class FilterDAO {
    private SQLiteDatabase db;

    public FilterDAO(SQLiteDatabase db) {
        this.db = db;
    }

    public long save(ITunes tune) {
        ContentValues value = new ContentValues();
        value.put(FilterTable.COLUMN_NAME, tune.getName());
        value.put(FilterTable.COLUMN_Price, tune.getPrice());
        value.put(FilterTable.COLUMN_ThumbUrl, tune.getImage());


        return db.insert(FilterTable.TABLE_NAME, null, value);
    }

    public boolean update(ITunes tune) {
        ContentValues value = new ContentValues();
        value.put(FilterTable.COLUMN_NAME, tune.getName());
        value.put(FilterTable.COLUMN_Price, tune.getPrice());
        value.put(FilterTable.COLUMN_ThumbUrl, tune.getImage());
        return db.update(FilterTable.TABLE_NAME, value, FilterTable.COLUMN_ID + "=?", new String[]{tune.getId() + ""}) > 0;

    }

    public boolean delete(ITunes tune) {
        return db.delete(FilterTable.TABLE_NAME, FilterTable.COLUMN_ID + "=?", new String[]{tune.getId() + ""}) > 0;
    }

    public ITunes get(long id) {
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

    public List<ITunes> getAll() {
        List<ITunes> notes = new ArrayList<ITunes>();
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
    public ITunes buildTuneFromCursor(Cursor c) {
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
