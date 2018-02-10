package com.myonic.rishibhv.inclass13_801019706;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MapsActivity extends AppCompatActivity /*FragmentActivity implements OnMapReadyCallback*/ {

    private GoogleMap mMap;DatabaseReference dbtrips;
    ImageView imgaddtrip;ListView lv;
ArrayList<Trip> trips;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
        imgaddtrip=(ImageView) findViewById(R.id.imgadd);
        dbtrips = FirebaseDatabase.getInstance().getReference("Trips");
        lv= (ListView) findViewById(R.id.lv);
        trips=new ArrayList<Trip>();
        imgaddtrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MapsActivity.this,CreateTripActivity.class);
                startActivity(i);


            }
        });
     /*   if (getIntent().getExtras() != null) {
*/
            dbtrips.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    trips.clear();
                    for (DataSnapshot postsnapshot : dataSnapshot.getChildren()) {
                        trips.add(postsnapshot.getValue(Trip.class));
                    }
                    TripAdapter madapter = new TripAdapter(MapsActivity.this, trips);
                    lv.setAdapter(madapter);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
       // }



        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
      /*  SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);*/
    }



  /*  @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }*/
}
