package itis5180.inclass7;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Assignment: InClass07
 * Name: MainActivity.java
 * Date: 10/24/2017
 * Bradley Wright
 * Anvesh Kottapelli
 * Bhavya Gedi
 */

public class MainActivity extends AppCompatActivity implements AsyncTaskJSON.AsyncResponse {
    public ArrayList<ITunes> listTracksfav, response;
    ListView listView;
    private RecyclerView.Adapter madapter;
    private RecyclerView.LayoutManager mlayoutmanager;
    DBManager dm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dm = new DBManager(this);
        response = new ArrayList<>();
        try {
            new AsyncTaskJSON(MainActivity.this, MainActivity.this).execute("http://itunes.apple.com/us/rss/toppaidapplications/limit=25/json");
        } catch (Exception e) {
            e.printStackTrace();
        }

        listView = (ListView) findViewById(R.id.lv);
        ImageButton refresh = (ImageButton) findViewById(R.id.refresh);

        refresh.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                try {
                    new AsyncTaskJSON(MainActivity.this, MainActivity.this).execute("http://itunes.apple.com/us/rss/toppaidapplications/limit=25/json");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        final Switch simpleSwitch = (Switch) findViewById(R.id.switchOrder);
        simpleSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked && listView.getAdapter() != null) {
                    simpleSwitch.setText("Ascending");
                    DisplayAdapter adp = (DisplayAdapter) listView.getAdapter();
                    adp.sort(new Comparator<ITunes>() {
                        @Override
                        public int compare(ITunes iTunes, ITunes i2) {

                            return iTunes.getPrice().compareTo(i2.getPrice());
                        }
                    });
                    listView.setAdapter(adp);


                } else if (!isChecked && listView.getAdapter() != null) {
                    simpleSwitch.setText("Descending");
                    DisplayAdapter adp = (DisplayAdapter) listView.getAdapter();
                    adp.sort(new Comparator<ITunes>() {
                        @Override
                        public int compare(ITunes iTunes, ITunes i2) {

                            return i2.getPrice().compareTo(iTunes.getPrice());
                        }
                    });
                    listView.setAdapter(adp);
                }
            }
        });

        ArrayList<ITunes> dmSize = (ArrayList<ITunes>) dm.getAllNotes();
        if (dmSize.size() != 0) {
            TextView tv = (TextView) findViewById(R.id.noApps);
            tv.setVisibility(View.INVISIBLE);
        } else {
            TextView tv = (TextView) findViewById(R.id.noApps);
            tv.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void processFinish(ArrayList<ITunes> output) {
        response = output;
        if (response.size() > 0) {

            Log.d("demo_r", response.toString());
            Log.d("demo_dm", dm.getAllNotes().toString());

            ArrayList<ITunes> dbm = (ArrayList<ITunes>) dm.getAllNotes();

//            for (int i = 0; i < response.size(); i++) {
//                ITunes item = response.get(i);
//                for (int j = 0; j < dbm.size(); j++) {
//                    Log.d("autolog1", item.getName());
//                    if (item.getName().contains(dbm.get(j).getName())) {
//                        response.remove(item);
//                        Log.d("autolog2", "removed: " + item.getName() + " " + dbm.get(j).getName());
//                    }
//                    if (item.getName().contains("Heads Up!")) {
//                        Log.d("autolog3", dbm.get(j).getName());
//                    }
//                }
//            }

            for (int i = 0; i < dbm.size(); i++) {
                ITunes item = dbm.get(i);

                for (int j = 0; j < response.size(); j++) {

                    if (item.getName().contains(response.get(j).getName())) {
                        response.remove(response.get(j));
                    }
                }
            }


//            response.removeAll(dbm);

            Log.d("demomain", response.toString());
            DisplayAdapter cadapt;
            cadapt = new DisplayAdapter(this, response);

            Switch s = (Switch) findViewById(R.id.switchOrder);

            if (s.isChecked()) {
                cadapt.sort(new Comparator<ITunes>() {
                    @Override
                    public int compare(ITunes iTunes, ITunes i2) {

                        return iTunes.getPrice().compareTo(i2.getPrice());
                    }
                });
            } else {
                cadapt.sort(new Comparator<ITunes>() {
                    @Override
                    public int compare(ITunes iTunes, ITunes i2) {

                        return i2.getPrice().compareTo(iTunes.getPrice());
                    }
                });
            }

            listView.setAdapter(cadapt);

            ArrayList<ITunes> notes = (ArrayList<ITunes>) dm.getAllNotes();

            if (notes.size() > 0) {
                RecyclerView rv = (RecyclerView) findViewById(R.id.my_recycler_view2);
                mlayoutmanager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
                rv.setLayoutManager(mlayoutmanager);
                madapter = new FilteredAdapter(notes, response, dm);
                rv.setAdapter(madapter);
            }


            listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                               int pos, long id) {
                    dm.saveNote(response.get(pos));
                    ArrayList<ITunes> notes = (ArrayList<ITunes>) dm.getAllNotes();
                    Log.d("demosaved", notes.toString());
                    Log.v("long clicked", "pos: " + pos);


                    RecyclerView rv = (RecyclerView) findViewById(R.id.my_recycler_view2);
                    mlayoutmanager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
                    rv.setLayoutManager(mlayoutmanager);
                    madapter = new FilteredAdapter(notes, response, dm);

                    ArrayList<ITunes> dmSize = (ArrayList<ITunes>) dm.getAllNotes();
                    if (dmSize.size() != 0) {
                        View v = arg1.getRootView();
                        TextView tv = v.findViewById(R.id.noApps);
                        tv.setVisibility(View.INVISIBLE);
                        rv.setAdapter(madapter);
                    }

                    response.remove(pos);
                    DisplayAdapter change = new DisplayAdapter(arg0.getContext(), response);
                    listView.setAdapter(change);
                    return true;

                }
            });

        }
    }

}