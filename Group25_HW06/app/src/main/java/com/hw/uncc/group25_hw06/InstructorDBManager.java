package com.hw.uncc.group25_hw06;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

/**
 * Created by Rishi on 01/11/17.
 */

public class InstructorDBManager {

    private Context mctx;
    private SQLiteDatabase db;
    private DatabaseOpenHelper dbopenhelper;
    private InstructorDAO insDAO;

    InstructorDBManager(Context mContext) {
        this.mctx = mctx;
        dbopenhelper = new DatabaseOpenHelper(mContext);
        db = dbopenhelper.getWritableDatabase();
        insDAO = new InstructorDAO(db);
    }

    public void close() {
        if (db != null) {
            db.close();
        }
    }

    public InstructorDAO getNoteDAO() {
        return this.insDAO;
    }

    long saveNote(Instructor note) {
        return this.insDAO.save(note);
    }

    public boolean updateNote(Instructor note) {
        return this.insDAO.update(note);
    }

    public boolean deleteNote(Instructor note) {
        return this.insDAO.delete(note);
    }

    public Instructor getNote(long id) {
        return this.insDAO.get(id);
    }

    List<Instructor> getAllNotes(String uname) {
        return this.insDAO.getAll(uname);
    }

}

