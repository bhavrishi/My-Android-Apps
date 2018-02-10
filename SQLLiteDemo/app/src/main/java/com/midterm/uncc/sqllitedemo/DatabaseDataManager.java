package com.midterm.uncc.sqllitedemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

/**
 * Created by Rishi on 22/10/17.
 */

public class DatabaseDataManager {
    private Context mctx;
    private DatabseOpenHelper helper;
    private SQLiteDatabase db;
    private NotesDAO noteDAO;

    public DatabaseDataManager(Context mctx) {
        this.mctx = mctx;
        helper=new DatabseOpenHelper(this.mctx);
        db=helper.getWritableDatabase();
        noteDAO=new NotesDAO(db);
    }
    public  void close(){
        if(db!=null)
        {
            db.close();
        }
    }

    public NotesDAO getNoteDAO()
    {
        return this.noteDAO;
    }

    public long saveNote(Notes note) {
        return this.noteDAO.save(note);
    }


    public boolean updateNote(Notes note){
        return  this.noteDAO.update(note);
    }
    public boolean deleteNode(Notes note){
        return  this.noteDAO.delete(note);
    }
    public Notes getNote(long id){
        return  this.noteDAO.get(id);
    }
    public List<Notes> getAllNotes(){
        return  this.noteDAO.getAll();
    }

}
