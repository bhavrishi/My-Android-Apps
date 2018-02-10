package com.midterm.uncc.sqllitedemo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rishi on 21/10/17.
 */

public class NotesDAO {
    private SQLiteDatabase db;

    public NotesDAO(SQLiteDatabase db) {
        this.db = db;
    }


    public long save(Notes note) {
        ContentValues values = new ContentValues();
        values.put(NotesTable.COLUMN_SUB, note.getSub());
        values.put(NotesTable.COLUMN_TEXT, note.getText());

        return db.insert(NotesTable.TABLE_NAME, null, values);
    }

    public boolean update(Notes note) {
        ContentValues values = new ContentValues();
        values.put(NotesTable.COLUMN_SUB, note.getSub());
        values.put(NotesTable.COLUMN_TEXT, note.getText());

        return db.update(NotesTable.TABLE_NAME, values, NotesTable.COLUMN_ID + "=?", new String[]{note.get_id() + ""}) > 0;
    }

    public boolean delete(Notes note) {
        return db.delete(NotesTable.TABLE_NAME, NotesTable.COLUMN_ID + "=?", new String[]{note.get_id() + ""}) > 0;
    }

    public Notes get(long id) {
        Notes note = null;
        Cursor c = db.query(true, NotesTable.TABLE_NAME, new String[]{
                        NotesTable.COLUMN_ID, NotesTable.COLUMN_SUB, NotesTable.COLUMN_TEXT},
                NotesTable.COLUMN_ID + "=?", new String[]{id + ""}, null, null, null, null, null);

        if (c != null && c.moveToFirst()) {
            note = buildNoteFromCursor(c);
            if (!c.isClosed())
                c.close();
        }


        return note;
    }

    private Notes buildNoteFromCursor(Cursor c) {
        Notes note = null;
        if (c != null) {
            note = new Notes();
            note.set_id(c.getLong(0));
            note.setSub(c.getString(1));
            note.setText(c.getString(2));

        }
        return note;
    }

    public List<Notes> getAll() {

        List<Notes> notes = new ArrayList<Notes>();
        Cursor c = db.query(NotesTable.TABLE_NAME, new String[]{
                NotesTable.COLUMN_ID, NotesTable.COLUMN_SUB, NotesTable.COLUMN_TEXT}, null, null, null, null, null);

        if (c != null && c.moveToFirst())
            do {

                Notes note = buildNoteFromCursor(c);
                if (note != null)
                    notes.add(note);

            } while (c.moveToNext());
        if (!c.isClosed())
            c.close();
        return notes;
    }

}
