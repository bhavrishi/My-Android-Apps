package com.inclass.uncc.previousfragment;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rishi on 29/10/17.
 */

public class MessageDAO{
        private SQLiteDatabase db;

        MessageDAO(SQLiteDatabase db) {
            this.db = db;
        }

        long save(Message tune) {
            ContentValues value = new ContentValues();
            value.put(MessageTable.COLUMN_NAME, tune.getMsg());

            return db.insert(MessageTable.TABLE_NAME, null, value);
        }

        boolean update(Message tune) {
            ContentValues value = new ContentValues();
            value.put(MessageTable.COLUMN_NAME, tune.getMsg());

            return db.update(MessageTable.TABLE_NAME, value, MessageTable.COLUMN_ID + "=?", new String[]{tune.getId() + ""}) > 0;

        }

        boolean delete(Message tune) {
            return db.delete(MessageTable.TABLE_NAME, MessageTable.COLUMN_ID + "=?", new String[]{tune.getId() + ""}) > 0;
        }

        Message get(long id) {
            Message tune = null;
            Cursor c = db.query(true, MessageTable.TABLE_NAME, new String[]{MessageTable.COLUMN_ID, MessageTable.COLUMN_NAME}
                    , MessageTable.COLUMN_ID + "=?", new String[]{id + ""}, null, null, null, null);

            if (c != null && c.moveToFirst()) {
                tune = buildTuneFromCursor(c);
                if (!c.isClosed()) {
                    c.close();
                }
            }

            return null;
        }

        List<Message> getAll() {
            List<Message> notes = new ArrayList<>();
            Cursor c = db.query(MessageTable.TABLE_NAME, new String[]{MessageTable.COLUMN_ID,
                            MessageTable.COLUMN_NAME}, null,
                    null, null, null, null);
            if (c != null && c.moveToFirst()) {
                do {
                    Message tune = buildTuneFromCursor(c);
                    if (tune != null)notes.add(tune);
                } while (c.moveToNext());
            }
            return notes;
        }

        private Message buildTuneFromCursor(Cursor c) {
            Message tune = null;
            if (c != null) {
                tune = new Message();
                tune.setId(c.getLong(0));
                tune.setMsg(c.getString(1));

            }
            return tune;
        }

}
