package com.inclass.uncc.inclass07_group_1;

/**
 * Created by Rishi on 23/10/17.
 */


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;


public class DBManager {
    private Context mContext;
    private DataBaseOpenHelper dbopenhelper;
    private SQLiteDatabase db;
    private FilterDAO filterDAO;

    public DBManager(Context mContext) {
        this.mContext = mContext;
        dbopenhelper = new DataBaseOpenHelper(mContext);
        db = dbopenhelper.getWritableDatabase();
        filterDAO = new FilterDAO(db);

    }

    public void close() {
        if (db != null) {
            db.close();
        }
    }

    public long saveNote(ITunes note) {
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

    public List<ITunes> getAllNotes() {
        return this.filterDAO.getAll();
    }

}