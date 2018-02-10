package com.midterm.uncc.a801019706;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import  java.util.Collections;

public class FavouritesResultsActivity extends AppCompatActivity {
    private ListView listView;
    public ArrayList<Movie> listTracks,listTracksfav,selectedList;
    SharedPreferencesFavourites sharedPreferences;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_home:
                Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(FavouritesResultsActivity.this, MainActivity.class);
                listTracksfav = sharedPreferences.getFavorites(this);
                i.putExtra("FAV",listTracksfav);
                Log.d("demohome",listTracksfav.toString());

                startActivity(i);
                return true;
            case R.id.item_home2:
                Toast.makeText(this, "Quit", Toast.LENGTH_SHORT).show();
                Intent in = new Intent(FavouritesResultsActivity.this, MainActivity.class);
                startActivity(in);
                return true;

            case R.id.item_quit:
                Toast.makeText(this, "Quit", Toast.LENGTH_SHORT).show();
                Intent homeIntent = new Intent(Intent.ACTION_MAIN);
                homeIntent.addCategory( Intent.CATEGORY_HOME );
                homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeIntent);
                return true;
            case R.id.item_sort_rating:
                Log.d("listtrackbefore",listTracks.toString());

                Collections.sort(listTracks, new SortMovie());

                Log.d("listtrackafter",listTracks.toString());
                DisplayAdapter cadapt;

                cadapt=new DisplayAdapter(this,listTracks);
                cadapt.notifyDataSetChanged();
                listView.setAdapter(cadapt);

              return  true;
            case R.id.item_sort_pop:
                Log.d("listtrackbefore",listTracks.toString());

                Collections.sort(listTracks, new SortMoviep());

                Log.d("listtrackafter",listTracks.toString());


                cadapt=new DisplayAdapter(this,listTracks);
                cadapt.notifyDataSetChanged();
                listView.setAdapter(cadapt);

                return  true;

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
        Bundle b = getIntent().getExtras();
        if (b != null) {
            listTracks = (ArrayList<Movie>) b.getSerializable("FAV");
            Log.d("demointent", listTracks.toString());
        }

        listView= (ListView) findViewById(R.id.listMovie);

        /*listContacts.add(new Contact("Rishi","ë@uncc.edu","9898989898"));
        listContacts.add(new Contact("Bhavya","ë@uncc.edu","9898989898"));
        listContacts.add(new Contact("Doctor","ë@uncc.edu","9898989898"));*/
        FavouritesAdapter cadapt;
        listTracksfav=FavouritesAdapter.returnList();
        cadapt=new FavouritesAdapter(this,listTracks);
        listView.setAdapter(cadapt);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(DisplayActivity.this, listContacts.get(position).toString(),Toast.LENGTH_SHORT).show();
              //  selectedList.add ( listTracks.get(position));
                Bundle bundle = new Bundle();
                //bundle.putSerializable("value", (Serializable) selectedList);
                Intent intent = new Intent(FavouritesResultsActivity.this, DetailedDisplayActivity.class);
                intent.putExtra("position", position);
                intent.putExtra("ALIST",listTracks);


                Log.d("demoto",listTracks.get(position).toString());
             //   Log.d("demoeq",selectedList.toString());
                startActivity(intent);
            }
        });
    }
}
 class SortMovie implements Comparator<Movie> {
    @Override
    public int compare(Movie object1, Movie object2) {
       Double d1 = 0.0,d2=0.0;
        d1=Double.parseDouble(object1.getRating());
                d2=Double.parseDouble(object1.getRating());


        Log.d("d1",""+d1);
                Log.d("d2",""+d2);
        Log.d("demoreturn",""+Double.compare(d1, d2));
        return Double.compare(d1, d2);
    }
}
class SortMoviep implements Comparator<Movie> {
    @Override
    public int compare(Movie object1, Movie object2) {
        Double d1 = 0.0,d2=0.0;
        d1=Double.parseDouble(object1.getPopularity());
        d2=Double.parseDouble(object1.getPopularity());


        Log.d("d1",""+d1);
        Log.d("d2",""+d2);
        Log.d("demoreturn",""+Double.compare(d1, d2));
        return Double.compare(d1, d2);
    }
}