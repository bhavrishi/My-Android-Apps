package com.inclass.uncc.previousfragment;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

/**
 * Created by Rishi on 29/10/17.
 */
class DBManager {
    private Context mctx;
    private SQLiteDatabase db;
    private DataBaseOpenHelper dbopenhelper;
    private MessageDAO filterDAO;

    DBManager(Context mContext) {
        this.mctx = mctx;
         dbopenhelper = new DataBaseOpenHelper(mContext);
        db = dbopenhelper.getWritableDatabase();
        filterDAO = new MessageDAO(db);
    }

    public void close() {
        if (db != null) {
            db.close();
        }
    }
    public MessageDAO getNoteDAO()
    {
        return this.filterDAO;
    }
    long saveNote(Message note) {
        return this.filterDAO.save(note);
    }

    public boolean updateNote(Message note) {
        return this.filterDAO.update(note);
    }

    public boolean deleteNote(Message note) {
        return this.filterDAO.delete(note);
    }

    public Message getNote(long id) {
        return this.filterDAO.get(id);
    }

    List<Message> getAllNotes() {
        return this.filterDAO.getAll();
    }

}