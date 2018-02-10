package com.midterm.uncc.sqllitedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {
DatabaseDataManager dm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dm=new DatabaseDataManager(this);
        dm.saveNote(new Notes("Note 1","This is Note 1"));
        dm.saveNote(new Notes("Note 2","This is Note 2"));
        dm.saveNote(new Notes("Note 3","This is Note 3"));
        List<Notes> notes=dm.getAllNotes();
        Log.d("demo",notes.toString());
    }

    @Override
    protected void onDestroy() {
        dm.close();
    }
}
