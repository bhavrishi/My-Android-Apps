package com.hw.uncc.group25_hw06;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

/**
 * Created by Rishi on 02/11/17.
 */

public class UserDBManager {

    private Context mctx;
    private SQLiteDatabase db;
    private DatabaseOpenHelper dbopenhelper;
    private UserDAO userDAO;

    UserDBManager(Context mContext) {
        this.mctx = mctx;
        dbopenhelper = new DatabaseOpenHelper(mContext);
        db = dbopenhelper.getWritableDatabase();
        userDAO = new UserDAO(db);
    }

    public void close() {
        if (db != null) {
            db.close();
        }
    }
    public UserDAO getNoteDAO()
    {
        return this.userDAO;
    }
    long saveNote(User note) {
        return this.userDAO.save(note);
    }

    public boolean updateNote(User note) {
        return this.userDAO.update(note);
    }

    public boolean deleteNote(User note) {
        return this.userDAO.delete(note);
    }

    public User getNote(long id) {
        return this.userDAO.get(id);
    }

    List<User> getAllNotes() {
        return this.userDAO.getAll();
    }
}
