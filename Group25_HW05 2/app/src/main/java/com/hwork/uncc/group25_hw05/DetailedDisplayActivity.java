package com.hwork.uncc.group25_hw05;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class DetailedDisplayActivity extends AppCompatActivity /*implements AsyncTaskJSON.AsyncResponse */{
    public ArrayList<Track> listTracks;
    int selectedpos;
    Bitmap bm;
    private ListView listView;
    public ArrayList<Track> listTracksfav,listfav;
    static SharedPreferencesFavourites sharedPreferences;

    /*@Override
    public void onDestroy()
    {
        super.onDestroy();
        this.getSharedPreferences("PREFS_NAME", 0).edit().clear().commit();
       myPrefs.edit().remove("PREFS_NAME");
        myPrefs.edit().clear();
        myPrefs.edit().commit();
    }*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_home:
                listfav=new ArrayList<Track>();
                Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(DetailedDisplayActivity.this, MainActivity.class);
               listfav = sharedPreferences.getFavorites(this);
                i.putExtra("FAV", listfav);
                Log.d("demohome", listfav.toString());
                startActivity(i);
                return true;
            case R.id.item_quit:
                Toast.makeText(this, "Quit", Toast.LENGTH_SHORT).show();
                //finish();
                Intent homeIntent = new Intent(Intent.ACTION_MAIN);
                homeIntent.addCategory( Intent.CATEGORY_HOME );
                homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);


        return super.onCreateOptionsMenu(menu);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_detailed_display);
        ImageView selectedimg = (ImageView) findViewById(R.id.imgsmall);
        TextView selctedName = (TextView) findViewById(R.id.outname);
        final TextView selectedurl = (TextView) findViewById(R.id.outurl);
        TextView selectedArtist = (TextView) findViewById(R.id.outartist);
        listView = (ListView) findViewById(R.id.disListView);
        Bundle b = this.getIntent().getExtras();
        if (b != null) {
            listTracks = (ArrayList<Track>) b.getSerializable("ALIST");
            selectedpos = b.getInt("position");
            Log.d("demoselect", listTracks.toString());
            Log.d("demopos", "" + selectedpos);
        }
        selctedName.setText(listTracks.get(selectedpos).getName());
        selectedArtist.setText(listTracks.get(selectedpos).getArtist());
        selectedurl.setText(listTracks.get(selectedpos).getUrl());
        String[] third = listTracks.get(selectedpos).getImg();
        try {
            bm = new AsyncImage().execute(third[2]).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
listTracksfav=new ArrayList<Track>();
        if (bm != null) selectedimg.setImageBitmap(bm);
        DisplayAdapter cadapt;//=new DisplayAdapter(this,listTracks);
        // listTracksfav=DisplayAdapter.returnList();
        try {
            listTracksfav= new AsyncTaskSimilarJSON(DetailedDisplayActivity.this).execute("http://ws.audioscrobbler.com/2.0/?format=json&method=track.getsimilar&track="+listTracks.get(selectedpos).getName()+"&artist="+listTracks.get(selectedpos).getArtist()+"&api_key=5ef94bff4902f6c9ad09df62c7a60ca6&limit=20").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        if (listTracksfav.size() != 0) {
        Log.d("demosim",listTracksfav.toString());
            Log.d("demosim","http://ws.audioscrobbler.com/2.0/?format=json&method=track.getsimilar&track="+listTracks.get(selectedpos).getName()+"&artist="+listTracks.get(selectedpos).getArtist()+"&api_key=5ef94bff4902f6c9ad09df62c7a60ca6&limit=20");
            cadapt = new DisplayAdapter(this, listTracksfav);
            listView.setAdapter(cadapt);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
            });
        }


        selectedurl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedurl.length() !=0) {
                    try {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(selectedurl.getText().toString()));
                        startActivity(intent);
                    }
                    catch (Exception ex){
                        Toast.makeText(DetailedDisplayActivity.this, "Invalid Uri", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
/*
    @Override
    public void processFinish(ArrayList<Track> output) {
        listTracksfav = output;

    }*/

}
