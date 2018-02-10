package com.hw.uncc.group25_hw06;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rishi on 01/11/17.
 */

public class InstructorDAO {
    private SQLiteDatabase db;

    InstructorDAO(SQLiteDatabase db) {
        this.db = db;
    }

    long save(Instructor tune) {
        ContentValues value = new ContentValues();
        value.put(InstructorTable.COLUMN_IMAGE, tune.getImg());
        value.put(InstructorTable.COLUMN_FNAME,tune.getFname());
        value.put(InstructorTable.COLUMN_LNAME,tune.getLname());
        value.put(InstructorTable.COLUMN_EMAIL,tune.getEmail());
        value.put(InstructorTable.COLUMN_WEBSITE,tune.getWebsite());
        value.put(InstructorTable.COLUMN_UNAME,tune.getUname());

        return db.insert(InstructorTable.TABLE_NAME, null, value);
    }

    boolean update(Instructor tune) {
        ContentValues value = new ContentValues();
        value.put(InstructorTable.COLUMN_IMAGE, tune.getImg());
        value.put(InstructorTable.COLUMN_FNAME,tune.getFname());
        value.put(InstructorTable.COLUMN_LNAME,tune.getLname());
        value.put(InstructorTable.COLUMN_EMAIL,tune.getEmail());
        value.put(InstructorTable.COLUMN_WEBSITE,tune.getWebsite());
        value.put(InstructorTable.COLUMN_UNAME,tune.getUname());
        return db.update(InstructorTable.TABLE_NAME, value, InstructorTable.COLUMN_ID + "=?", new String[]{tune.getIntid() + ""}) > 0;

    }

    boolean delete(Instructor tune) {
        return db.delete(InstructorTable.TABLE_NAME, InstructorTable.COLUMN_ID + "=?", new String[]{tune.getIntid() + ""}) > 0;
    }

    Instructor get(long id) {
        Instructor tune = null;
        Cursor c = db.query(true, InstructorTable.TABLE_NAME, new String[]{InstructorTable.COLUMN_ID,InstructorTable.COLUMN_IMAGE,InstructorTable.COLUMN_FNAME,
                        InstructorTable.COLUMN_LNAME ,InstructorTable.COLUMN_EMAIL,InstructorTable.COLUMN_WEBSITE}
                , InstructorTable.COLUMN_ID + "=?", new String[]{id + ""}, null, null, null, null);

        if (c != null && c.moveToFirst()) {
            tune = buildTuneFromCursor(c);
            if (!c.isClosed()) {
                c.close();
            }
        }

        return null;
    }

    List<Instructor> getAll(String uname) {
        List<Instructor> notes = new ArrayList<>();
        Cursor c = db.query(InstructorTable.TABLE_NAME, new String[]{InstructorTable.COLUMN_ID,InstructorTable.COLUMN_IMAGE,InstructorTable.COLUMN_FNAME,
                        InstructorTable.COLUMN_LNAME ,InstructorTable.COLUMN_EMAIL,InstructorTable.COLUMN_WEBSITE,InstructorTable.COLUMN_UNAME}
                , InstructorTable.COLUMN_UNAME + "=?", new String[]{uname + ""}, null, null, null, null);
        if (c != null && c.moveToFirst()) {
            do {
                Instructor tune = buildTuneFromCursor(c);
                if (tune != null) notes.add(tune);
            } while (c.moveToNext());
        }
        return notes;
    }

    private Instructor buildTuneFromCursor(Cursor c) {
        Instructor tune = null;
        if (c != null) {
            tune = new Instructor();
            tune.setIntid(c.getLong(0));
            tune.setImg(c.getString(1));
            tune.setFname(c.getString(2));
            tune.setLname(c.getString(3));
            tune.setEmail(c.getString(4));
            tune.setWebsite(c.getString(5));
            tune.setUname(c.getString(6));

        }
        return tune;
    }
}