package com.hwork.uncc.group25_hw05;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AsyncTaskJSON.AsyncResponse {
    ArrayList<Track> response;
    static ArrayList<Track> listTracksfav;
    ListView listView;
    static SharedPreferencesFavourites sharedPreferences;
    boolean firstClick = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = new SharedPreferencesFavourites();
        setContentView(R.layout.layout_main);
        //   AsyncTaskJSON async = new AsyncTaskJSON(MainActivity.this);
        SharedPreferences preferences = getSharedPreferences("PREFS_NAME", this.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
        /*PreferenceManager.getDefaultSharedPreferences(getBaseContext()).
                edit().clear().commit();*/
        response = new ArrayList<Track>();
        Button btnsearch = (Button) findViewById(R.id.btnsearch);
        final EditText inputText = (EditText) findViewById(R.id.editText);
        //  async.delegate =this;
        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // new AsyncTaskJSON(MainActivity.this).execute("http://ws.audioscrobbler.com/2.0/?format=json&method=track.search&track=Believe&api_key=5ef94bff4902f6c9ad09df62c7a60ca6&limit=20");
                new AsyncTaskJSON(MainActivity.this, MainActivity.this).execute("http://ws.audioscrobbler.com/2.0/?format=json&method=track.search&track=" + inputText.getText() + "&api_key=5ef94bff4902f6c9ad09df62c7a60ca6&limit=20");

                if (response.size() != 0) {
                    Intent i = new Intent(MainActivity.this, SearchResultsActivity.class);
                    i.putExtra("ALIST", response);
                    firstClick = true;
                    startActivity(i);
                }
            }
        });
        listView = (ListView) findViewById(R.id.listvwmain);
        Bundle b = getIntent().getExtras();
        if (b != null) {
            listTracksfav = (ArrayList<Track>) b.getSerializable("FAV");
            Log.d("demointent", listTracksfav.toString());

        /*listContacts.add(new Contact("Rishi","ë@uncc.edu","9898989898"));
        listContacts.add(new Contact("Bhavya","ë@uncc.edu","9898989898"));
        listContacts.add(new Contact("Doctor","ë@uncc.edu","9898989898"));*/
            //if (firstClick == true) {
            FavouritesMainAdapter cadapt;//=new DisplayAdapter(this,listTracks);
            //  listTracksfav = sharedPreferences.getFavorites(MainActivity.this);
            // Log.d("demohome",listTracksfav.toString());
            cadapt = new FavouritesMainAdapter(this, listTracksfav);
            listView.setAdapter(cadapt);
        }
        //}

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_home:
                Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();

                return true;
            case R.id.item_quit:
                Toast.makeText(this, "Quit", Toast.LENGTH_SHORT).show();
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


    public void processFinish(ArrayList<Track> output) {
        response = output;
       /*if(response.size() !=0) {
            Log.d("demointent", output.toString());
        }*/
    }
}
