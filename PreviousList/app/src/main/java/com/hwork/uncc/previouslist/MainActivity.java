package com.hwork.uncc.previouslist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    ArrayList<Itunes> qlist ;private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
       qlist   = new ArrayList<Itunes>();
        try {
            qlist=  new AsyncTaskJSON(MainActivity.this).execute("https://itunes.apple.com/us/rss/toppodcasts/limit=30/json").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        DisplayAdapter cadapt; listView= (ListView) findViewById(R.id.listvw);

        if (qlist.size() != 0) {
            Log.d("demosim",qlist.toString());
            cadapt = new DisplayAdapter(this, qlist);
            listView.setAdapter(cadapt);

           /* listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //Toast.makeText(DisplayActivity.this, listContacts.get(position).toString(),Toast.LENGTH_SHORT).show();
                    // selectedList.add ( listTracks.get(position));
                    Bundle bundle = new Bundle();
                    //bundle.putSerializable("value", (Serializable) selectedList);
                    Intent intent = new Intent(DetailedDisplayActivity.this, DetailedDisplayActivity.class);
                    intent.putExtra("position", position);
                    intent.putExtra("ALIST",listTracksfav);


                    Log.d("demoto",listTracks.get(position).toString());
                    //  Log.d("demoeq",selectedList.toString());
                    startActivity(intent);
                }
            });*/
        }
    }
}
