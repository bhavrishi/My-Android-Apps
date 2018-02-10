package itis5180.inclass7;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

/**
 * Assignment: InClass07
 * Name: DBManager.java
 * Date: 10/24/2017
 * Bradley Wright
 * Anvesh Kottapelli
 * Bhavya Gedi
 */

class DBManager {
    private SQLiteDatabase db;
    private FilterDAO filterDAO;

    DBManager(Context mContext) {
        DataBaseOpenHelper dbopenhelper = new DataBaseOpenHelper(mContext);
        db = dbopenhelper.getWritableDatabase();
        filterDAO = new FilterDAO(db);
    }

    public void close() {
        if (db != null) {
            db.close();
        }
    }

    long saveNote(ITunes note) {
        return this.filterDAO.save(note);
    }

    public boolean updateNote(ITunes note) {
        return this.filterDAO.update(note);
    }

    public boolean deleteNote(ITunes note) {
        return this.filterDAO.delete(note);
    }

    public ITunes getNote(long id) {
        return this.filterDAO.get(id);
    }

    List<ITunes> getAllNotes() {
        return this.filterDAO.getAll();
    }

}
