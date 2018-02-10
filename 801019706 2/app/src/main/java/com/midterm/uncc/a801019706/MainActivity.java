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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AsyncTaskJSON.AsyncResponse {
    ArrayList<Movie> response;

    static ArrayList<Movie> listTracksfav, selectedList;
    ListView listView;
    static SharedPreferencesFavourites sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
        response = new ArrayList<Movie>();
        sharedPreferences=new SharedPreferencesFavourites();
        Button btnsearch = (Button) findViewById(R.id.btnsearch);
        final EditText inputText = (EditText) findViewById(R.id.inputText);
        final String[] splitStr = inputText.getText().toString().split("\\s+");
        final String[] url = {null};

        //  async.delegate =this;
        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i=0;i<splitStr.length;i++)
                {
                    url[0] =splitStr[i]+"+";
                }
                Log.d("demourl",splitStr[0]);
                Log.d("demourl", url[0]);
                if(inputText.length() != 0) {
                    // new AsyncTaskJSON(MainActivity.this).execute("http://ws.audioscrobbler.com/2.0/?format=json&method=track.search&track=Believe&api_key=5ef94bff4902f6c9ad09df62c7a60ca6&limit=20");

                    new AsyncTaskJSON(MainActivity.this,MainActivity.this).execute("https://api.themoviedb.org/3/search/movie?query="+inputText.getText()+"&api_key=a6ca8bdaabfe34a173c4142c2056f0ee");
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Please enter the movie name to search", Toast.LENGTH_LONG).show();
                }
                /*if (response.size() != 0) {
                    Intent i = new Intent(MainActivity.this, FavouritesResultsActivity.class);
                    i.putExtra("ALIST", response);
                    firstClick = true;
                    startActivity(i);
                }*/
            }
        });

        listView = (ListView) findViewById(R.id.listvwmain);
        /*listTracksfav = sharedPreferences.getFavorites(this);
        if(listTracksfav.size() !=0){
            FavouritesMainAdapter cadapt;
            cadapt = new FavouritesMainAdapter(this, listTracksfav);
            listView.setAdapter(cadapt);
        }

        selectedList= new ArrayList<Track>();

        Bundle b = getIntent().getExtras();
        if (b != null) {
            listTracksfav = (ArrayList<Track>) b.getSerializable("FAV");
            Log.d("demointent", listTracksfav.toString());

        *//*listContacts.add(new Contact("Rishi","ë@uncc.edu","9898989898"));
        listContacts.add(new Contact("Bhavya","ë@uncc.edu","9898989898"));
        listContacts.add(new Contact("Doctor","ë@uncc.edu","9898989898"));*//*
            //if (firstClick == true) {
            FavouritesMainAdapter cadapt;//=new DisplayAdapter(this,listTracks);
            //  listTracksfav = sharedPreferences.getFavorites(MainActivity.this);
            // Log.d("demohome",listTracksfav.toString());
            cadapt = new FavouritesMainAdapter(this, listTracksfav);
            listView.setAdapter(cadapt);
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                selectedList.add(listTracksfav.get(position));
                Intent intent = new Intent(MainActivity.this, DetailedDisplayActivity.class);
                intent.putExtra("position", position);
                intent.putExtra("ALIST",listTracksfav);
                startActivity(intent);
            }
        });
*/
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_home:
                ArrayList<Movie> listfav = new ArrayList<Movie>();
                Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(MainActivity.this, FavouritesResultsActivity.class);
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
    public void processFinish(ArrayList<Movie> output) {
        response = output;
        if (response.size() != 0) {
            DisplayAdapter cadapt;
            listTracksfav=DisplayAdapter.returnList();
            cadapt=new DisplayAdapter(this,response);
            listView.setAdapter(cadapt);


            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //Toast.makeText(DisplayActivity.this, listContacts.get(position).toString(),Toast.LENGTH_SHORT).show();
                    //  selectedList.add ( listTracks.get(position));
                    Bundle bundle = new Bundle();
                    //bundle.putSerializable("value", (Serializable) selectedList);
                    Intent intent = new Intent(MainActivity.this, DetailedDisplayActivity.class);
                    intent.putExtra("position", position);
                    intent.putExtra("ALIST",response);


                    Log.d("demoto",response.get(position).toString());
                    //   Log.d("demoeq",selectedList.toString());
                    startActivity(intent);
                }
            });
        }
    }
}
