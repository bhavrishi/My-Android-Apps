package com.inclass.uncc.inclass07_group_01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements AsyncTaskJSON.AsyncResponse{
ArrayList<ITunes> listTracksfav,response; ListView listView; private RecyclerView.Adapter madapter;
  //  private RecyclerView.LayoutManager mlayoutmanager;
    DBManager dm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main_screen);
        dm=new DBManager(this);
        response = new ArrayList<ITunes>();
        try {
       new AsyncTaskJSON(MainActivity.this,MainActivity.this).execute("https://itunes.apple.com/us/rss/toppaidapplications/limit=25/json");
        } catch (Exception e) {
            e.printStackTrace();
        }

         listView = (ListView) findViewById(R.id.lv);
       // listView = (ListView) findViewById(R.id.lv2);
    }
        @Override
        public void processFinish(ArrayList<ITunes > output) {
            response = output;
            if (response.size() > 0) {
                Log.d("demomain",response.toString());
                DisplayAdapter cadapt;
                listTracksfav = DisplayAdapter.returnList();
                cadapt = new DisplayAdapter(this, response);
                listView.setAdapter(cadapt);
                listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                                   int pos, long id) {
                        // TODO Auto-generated method stub
dm.saveNote(response.get(pos));
                        ArrayList<ITunes> notes= (ArrayList<ITunes>) dm.getAllNotes();
                        Log.d("demosaved",notes.toString());
                        Log.v("long clicked","pos: " + pos);
                        RecyclerView rv= (RecyclerView) findViewById(R.id.my_recycler_view2);
                        rv.setHasFixedSize(true);
                      //  final  layoutManager = new LinearLayoutManager(context);
                     //   final LinearLayoutManager mLayoutManager= new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, true);

                       // mlayoutmanager =new LinearLayoutManager(MainActivity.this);
                      //  mlayoutmanager.setOrientation(LinearLayoutManager.HORIZONTAL);
                        rv.setLayoutManager(new LinearLayoutManager(rv.getContext()));
                        madapter=new FilteredAdapter(notes);
                        rv.setAdapter(madapter);
                        return true;

                    }
                });


            }
        }
    }