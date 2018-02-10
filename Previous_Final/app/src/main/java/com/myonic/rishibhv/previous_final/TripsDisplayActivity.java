package com.myonic.rishibhv.previous_final;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TripsDisplayActivity extends AppCompatActivity {
    DatabaseReference dbCities, dbPlaces,dbplaceschild;
    ArrayList<AutocompleteCity> cities;
    ArrayList<Place> places;
    Place place;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
        cities = new ArrayList<AutocompleteCity>();
        places = new ArrayList<Place>();
        place = new Place();
        dbCities = FirebaseDatabase.getInstance().getReference("cities");
        dbPlaces = FirebaseDatabase.getInstance().getReference("places");
        lv = (ListView) findViewById(R.id.lv2);

        dbCities.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                cities.clear();
                for (DataSnapshot postsnapshot : dataSnapshot.getChildren()) {
                    cities.add(postsnapshot.getValue(AutocompleteCity.class));
                }
                TripsDisplayAdapter madapter = new TripsDisplayAdapter(TripsDisplayActivity.this, cities,places);
                lv.setAdapter(madapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
      /*  dbplaceschild = FirebaseDatabase.getInstance().getReference("places").child("-L-U0e5B1yGl4kJip7kf");
        dbplaceschild.addListenerForSingleValueEvent( new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                places.clear();
                for (DataSnapshot postsnapshot : dataSnapshot.getChildren()) {
                    places.add(postsnapshot.getValue(Place.class));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        Log.d("actplac",places.toString());*/
        Intent intent = getIntent();

        if (getIntent().getExtras() != null) {
            place = (Place) intent.getSerializableExtra("ALIST");


            dbCities.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    cities.clear();
                    for (DataSnapshot postsnapshot : dataSnapshot.getChildren()) {
                        cities.add(postsnapshot.getValue(AutocompleteCity.class));
                    }
                    TripsDisplayAdapter madapter = new TripsDisplayAdapter(TripsDisplayActivity.this, cities);
                    lv.setAdapter(madapter);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }

    }

    public ArrayList<Place> returnPlaces(int position) {
        final boolean[] temp = {false};
        final ArrayList<Place> tempplces = new ArrayList<Place>();
        dbplaceschild = FirebaseDatabase.getInstance().getReference("places").child(cities.get(position).getId());
        dbplaceschild.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                tempplces.clear();
                places.clear();
                for (DataSnapshot postsnapshot : dataSnapshot.getChildren()) {
                    places.add(postsnapshot.getValue(Place.class));

                }
                for (int t = 0; t < places.size(); t++) {
                    temp[0] = true;
                    tempplces.add(places.get(t));
                }
                Log.d("actplac", places.toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        // Thread.sleep(2000);

        if (temp[0]) {

            Log.d("dempplace", tempplces.toString());
            return tempplces;
        } else {
            /*try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            Log.d("dempplaceout", tempplces.toString());
            return tempplces;
        }
    }
}
