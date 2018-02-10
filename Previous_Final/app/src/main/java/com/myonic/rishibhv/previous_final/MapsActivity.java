package com.myonic.rishibhv.previous_final;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MapsActivity extends AppCompatActivity /*FragmentActivity implements OnMapReadyCallback*/ {

    private GoogleMap mMap;
    EditText inputtripname,cityname;
    Button btnsearch,addTrip;ListView lv;int pos;
    DatabaseReference dbCities;
    ArrayList<AutocompleteCity> cities;boolean selectedcity=false;
    ArrayList<String> l;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_add_trip);

        cities=new ArrayList<AutocompleteCity>();
        l = new ArrayList<>();
        dbCities = FirebaseDatabase.getInstance().getReference("cities");

        inputtripname= (EditText) findViewById(R.id.inputtrip);
        cityname=(EditText)findViewById(R.id.inputcity);
        btnsearch= (Button) findViewById(R.id.btnsearch);
        addTrip=(Button)findViewById(R.id.btnaddtrip);
        lv= (ListView) findViewById(R.id.lv);
        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Log.d("democityurl","https://maps.googleapis.com/maps/api/place/autocomplete/json?input="+cityname.getText().toString()+"&types=(cities)&language=pt_BR&key=AIzaSyBMovGdEmLnbpayEtiY8iJdd4ozLuZQQH4");
                    cities=new AsyncAutoComplete().execute("https://maps.googleapis.com/maps/api/place/autocomplete/json?input="+cityname.getText().toString()+"&types=(cities)&language=pt_BR&key=AIzaSyBMovGdEmLnbpayEtiY8iJdd4ozLuZQQH4").get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                if(cities!=null)
                {
                    PlaceAutocompleteAdapter madapter=new PlaceAutocompleteAdapter(MapsActivity.this,cities);
                    lv.setAdapter(madapter);
                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            cityname.setText(cities.get(position).getCity());
                            pos=position;
                            selectedcity=true;
                        }
                    });
                }
            }
        });
        addTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!selectedcity)
                {
                    Toast.makeText(MapsActivity.this,"Please select city",Toast.LENGTH_SHORT).show();
                }
                else {
                   // saveCitytoDB(cities.get(pos).getPlaceid(),cities.get(pos).getCity());
                    Log.d("demogeourl","https://maps.googleapis.com/maps/api/place/details/json?placeid="+cities.get(pos).getPlaceid()+"&key=AIzaSyBMovGdEmLnbpayEtiY8iJdd4ozLuZQQH4");
                    try {
                        l= new AsyncGeo().execute("https://maps.googleapis.com/maps/api/place/details/json?placeid="+cities.get(pos).getPlaceid()+"&key=AIzaSyBMovGdEmLnbpayEtiY8iJdd4ozLuZQQH4").get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    if(l!=null)
                    {
                        saveCitytoDB(cities.get(pos).getPlaceid(),cities.get(pos).getCity(),l.get(0),l.get(1));
                    }
                }
            }
        });

     /*   // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);*/
    }

    private void saveCitytoDB(String placeid, String city,String lat,String lang) {
        String id = dbCities.push().getKey();
        AutocompleteCity contact = new AutocompleteCity(placeid, city, id,Double.valueOf(lat),Double.valueOf(lang),inputtripname.getText().toString());
        dbCities.child(id).setValue(contact);
        // editTextName.setText("");

        //displaying a success toast
        Toast.makeText(this, "Trip added", Toast.LENGTH_LONG).show();
       Intent i = new Intent(MapsActivity.this, TripsDisplayActivity.class);
      //  i.putExtra(UID, main_id);
        //i.putExtra("ALIST",contacts);
        startActivity(i);
    }
/*
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }*/
}
