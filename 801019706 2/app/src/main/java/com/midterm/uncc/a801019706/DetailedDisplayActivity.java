package com.midterm.uncc.a801019706;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class DetailedDisplayActivity extends AppCompatActivity {
    public ArrayList<Movie> listTracks;
    int selectedpos;
    Bitmap bm;
    private ListView listView;
    public ArrayList<Movie> listTracksfav,listfav;
    static SharedPreferencesFavourites sharedPreferences;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_home:

                listfav=new ArrayList<Movie>();
                Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(DetailedDisplayActivity.this, FavouritesResultsActivity.class);
                listfav = sharedPreferences.getFavorites(this);
                i.putExtra("FAV", listfav);
                Log.d("demohome", listfav.toString());
                startActivity(i);
                return true;

            case R.id.item_quit:
                Toast.makeText(this, "Quit", Toast.LENGTH_SHORT).show();
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
        ImageView selectedimg = (ImageView) findViewById(R.id.outimg);
        TextView selectedtitle = (TextView) findViewById(R.id.outtitle);
        final TextView selecteddate = (TextView) findViewById(R.id.outdate);
        TextView selectedrating  = (TextView) findViewById(R.id.outrating);
        TextView selectedovrvw  = (TextView) findViewById(R.id.outoverview);
        Bundle b = this.getIntent().getExtras();
        if (b != null) {
            listTracks = (ArrayList<Movie>) b.getSerializable("ALIST");
            selectedpos = b.getInt("position");
            Log.d("demoselect", listTracks.toString());
            Log.d("demopos", "" + selectedpos);
        }
        selectedtitle.setText(listTracks.get(selectedpos).getName());
        selecteddate.setText(listTracks.get(selectedpos).getDate());
        selectedrating.setText(listTracks.get(selectedpos).getRating()+"/10");
        selectedovrvw.setText(listTracks.get(selectedpos).getOverview());
        String third = listTracks.get(selectedpos).getPosterpath();
        try {
            String imgurl="http://image.tmdb.org/t/p/w342/"+third;
            bm = new AsyncImage().execute(imgurl).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        listTracksfav=new ArrayList<Movie>();
        if (bm != null) selectedimg.setImageBitmap(bm);



    }
}
