package com.inclass.uncc.inclass07_group_1;

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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements AsyncTaskJSON.AsyncResponse{
    ArrayList<ITunes> listTracksfav,response; ListView listView,lv2;  RecyclerView mRecyclerView;ImageView deleteclick; ArrayList<ITunes> notes;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;
    DisplayAdapter cadapt;
    DBManager dm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dm=new DBManager(this);
        response = new ArrayList<ITunes>();
        try {
            new AsyncTaskJSON(MainActivity.this,MainActivity.this).execute("https://itunes.apple.com/us/rss/toppaidapplications/limit=25/json");
        } catch (Exception e) {
            e.printStackTrace();
        }

        listView = (ListView) findViewById(R.id.lv);
         mRecyclerView= (RecyclerView) findViewById(R.id.rv);
        mLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
deleteclick= (ImageView) findViewById(R.id.imageButton2);

        mRecyclerView.setHasFixedSize(true);
        notes= (ArrayList<ITunes>) dm.getAllNotes();
        mAdapter=new FilteredAdapter(MainActivity.this,notes);
        mRecyclerView.setAdapter(mAdapter);
        //lv2 = (ListView) findViewById(R.id.lv2);
    }
    @Override
    public void processFinish(ArrayList<ITunes > output) {
        response = output;
        if (response.size() > 0) {
            Log.d("demomain",response.toString());
            notes= (ArrayList<ITunes>) dm.getAllNotes();
            for(int i=0;i<notes.size();i++)
            {
                for (int j=0;j<response.size();j++)
                    if((notes.get(i).getName().equals(response.get(j).getName()))&&(notes.get(i).getPrice().equals(response.get(j).getPrice())))
                    {
                        response.remove(i);
                    }
            }

            listTracksfav = DisplayAdapter.returnList();
            cadapt = new DisplayAdapter(this, response);
            listView.setAdapter(cadapt);
            listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                               int pos, long id) {
                    // TODO Auto-generated method stub
                    dm.saveNote(response.get(pos));
                    Toast.makeText(MainActivity.this,"Added App to Filetered List",Toast.LENGTH_SHORT).show();
                   notes= (ArrayList<ITunes>) dm.getAllNotes();
                    Log.d("demosaved",notes.toString());
                    Log.v("long clicked","pos: " + pos);





                    // The number of Columns

                    //  final  layoutManager = new LinearLayoutManager(context);

                   // mlayoutmanager =new LinearLayoutManager(MainActivity.this);
                    //  mlayoutmanager.setOrientation(LinearLayoutManager.HORIZONTAL);
                   // rv.setLayoutManager(mlayoutmanager);
                    mAdapter=new FilteredAdapter(MainActivity.this,notes);
                    mRecyclerView.setAdapter(mAdapter);
                    response.remove(pos);
                    cadapt = new DisplayAdapter(MainActivity.this, response);
                    Toast.makeText(MainActivity.this,"Change in list view",Toast.LENGTH_SHORT).show();
                    listView.setAdapter(cadapt);
                    /*FilteredAdapter fadapt;
                    fadapt = new FilteredAdapter(MainActivity.this, notes);
                    lv2.setAdapter(fadapt);
lv2.setFocusable(false);*/
                    return true;

                }
            });


        }
    }
}