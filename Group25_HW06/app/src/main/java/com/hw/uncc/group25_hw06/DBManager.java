package com.hw.uncc.group25_hw06;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

/**
 * Created by Rishi on 01/11/17.
 */

public class DBManager {
    private Context mctx;
    private SQLiteDatabase db;
    private DatabaseOpenHelper dbopenhelper;
    private CourseDAO courseDAO;

    DBManager(Context mContext) {
        this.mctx = mctx;
        dbopenhelper = new DatabaseOpenHelper(mContext);
        db = dbopenhelper.getWritableDatabase();
        courseDAO = new CourseDAO(db);
    }

    public void close() {
        if (db != null) {
            db.close();
        }
    }
    public CourseDAO getNoteDAO()
    {
        return this.courseDAO;
    }
    long saveNote(Course note) {
        return this.courseDAO.save(note);
    }

    public boolean updateNote(Course note) {
        return this.courseDAO.update(note);
    }

    public boolean deleteNote(Course note) {
        return this.courseDAO.delete(note);
    }

    public Course getNote(long id) {
        return this.courseDAO.get(id);
    }

    List<Course> getAllNotes(String uname) {
        return this.courseDAO.getAll(uname);
    }

}