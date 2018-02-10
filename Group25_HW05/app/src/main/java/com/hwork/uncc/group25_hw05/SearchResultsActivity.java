package com.hwork.uncc.group25_hw05;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SearchResultsActivity extends AppCompatActivity {

    private ListView listView;
    public ArrayList<Track> listTracks,selectedList;
    public ArrayList<Track> listTracksfav;
  static   SharedPreferencesFavourites sharedPreferences;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_home:
                Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(SearchResultsActivity.this, MainActivity.class);
                listTracksfav = sharedPreferences.getFavorites(this);
                i.putExtra("FAV",listTracksfav);
                Log.d("demohome",listTracksfav.toString());
                startActivity(i);
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_display_main);

        sharedPreferences=new SharedPreferencesFavourites();

      //  listTracksfav=sharedPreferences.getFavorites(this);
        Bundle b = getIntent().getExtras();
        if (b != null) {
            listTracks = (ArrayList<Track>) b.getSerializable("ALIST");
            Log.d("demointent", listTracks.toString());
        }

        selectedList= new ArrayList<Track>();
        listView= (ListView) findViewById(R.id.listTrack);

        /*listContacts.add(new Contact("Rishi","ë@uncc.edu","9898989898"));
        listContacts.add(new Contact("Bhavya","ë@uncc.edu","9898989898"));
        listContacts.add(new Contact("Doctor","ë@uncc.edu","9898989898"));*/
        DisplayAdapter cadapt=new DisplayAdapter(this,listTracks);
        listTracksfav=DisplayAdapter.returnList();
        cadapt=new DisplayAdapter(this,listTracksfav);
        listView.setAdapter(cadapt);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedList.add ( listTracks.get(position));
                Bundle bundle = new Bundle();
                //bundle.putSerializable("value", (Serializable) selectedList);
                Intent intent = new Intent(SearchResultsActivity.this, DetailedDisplayActivity.class);
                intent.putExtra("position", position);
                intent.putExtra("ALIST",listTracks);


                Log.d("demoto",listTracks.get(position).toString());
                Log.d("demoeq",selectedList.toString());
                startActivity(intent);
            }
        });
    }
}