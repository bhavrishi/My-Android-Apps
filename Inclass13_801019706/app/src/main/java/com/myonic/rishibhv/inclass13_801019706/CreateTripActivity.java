package com.myonic.rishibhv.inclass13_801019706;

import android.content.Intent;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class CreateTripActivity extends AppCompatActivity {
    DatabaseReference dbPlaces,dbtrips;List<Integer> selectedcheck;
    DatabaseReference dbLoc;EditText inputname,inputpeopl;
    Double globalcost;
    TextView inputcost;
    ArrayList<Place> places;int pl=0;ListView lv;Button btnaddTrip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_trip);
        dbPlaces = FirebaseDatabase.getInstance().getReference("Deals");
        dbtrips = FirebaseDatabase.getInstance().getReference("Trips");
        dbLoc = FirebaseDatabase.getInstance().getReference("Deals").child("Location");
        places=new ArrayList<Place>() ;
        lv=(ListView )findViewById(R.id.lv2);
        inputcost=(TextView)findViewById(R.id.inputcost);
       // inputcost.setText("0");
        try{globalcost=Double.parseDouble(inputcost.getText().toString());
    }catch(NumberFormatException ex){ // handle your exception
        ex.getMessage();
    }
        inputname=(EditText)findViewById(R.id.inputtripname);
        inputpeopl=(EditText)findViewById(R.id.inputPeople);
        inputpeopl.setText("1");
        selectedcheck = new ArrayList<Integer>();
        btnaddTrip=(Button)findViewById(R.id.btnaddtrip);
        dbPlaces.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                places.clear();
                for (DataSnapshot postsnapshot : dataSnapshot.getChildren()) {
                    Place placetemp=new Place();
                  placetemp.setCost(String.valueOf(postsnapshot.child("Cost").getValue(Long.class)));
           placetemp.setDuration(postsnapshot.child("Duration").getValue(String.class));
                    placetemp.setLat(postsnapshot.child("Location").child("Lat").getValue(Double.class));
                    placetemp.setLang(postsnapshot.child("Location").child("Lon").getValue(Double.class));
                    placetemp.setPlace(postsnapshot.child("Place").getValue(String.class));
                    LatLng latlngchar = new LatLng(35.2271,-80.8431);
                    LatLng dest=new LatLng(placetemp.getLat(),placetemp.getLang());
                    Location locationA = new Location("point A");
                    locationA.setLatitude(latlngchar.latitude);
                    locationA.setLongitude(latlngchar.longitude);
                    Location locationB = new Location("point B");
                    locationB.setLatitude(dest.latitude);
                    locationB.setLongitude(dest.longitude);
                    double distance = locationA.distanceTo(locationB)*0.621371;
                    placetemp.setMiles(""+distance);
               places.add(placetemp);
                }

               Log.d("dem0places",places.toString());
               if(places!=null){ DealAdapter madapter = new DealAdapter(CreateTripActivity.this, places);
                lv.setAdapter(madapter);}
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

btnaddTrip.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String id = dbtrips.push().getKey();
        Trip contact = new Trip( inputname.getText().toString(),globalcost);
        dbtrips.child(id).setValue(contact);
        // editTextName.setText("");

        //displaying a success toast

        Intent i = new Intent(CreateTripActivity.this, MapsActivity.class);
        //  i.putExtra(UID, main_id);
        //i.putExtra("ALIST",contacts);
        startActivity(i);
Toast.makeText(CreateTripActivity.this,"Trip Added to DB",Toast.LENGTH_LONG).show();
    }
});
    }

    public void changeCost(int i) {

        Double cost = 0.0;
        if(globalcost==null)
            globalcost=0.0;
        Log.d("demotopcost",""+globalcost+" " +Double.parseDouble(places.get(i).getCost()));
     /*   LatLng latlngchar = new LatLng(35.2271,-80.8431);
        LatLng dest=new LatLng(places.get(i).getLat(),places.get(i).getLang());
        Location locationA = new Location("point A");
        locationA.setLatitude(latlngchar.latitude);
        locationA.setLongitude(latlngchar.longitude);
        Location locationB = new Location("point B");
        locationB.setLatitude(dest.latitude);
        locationB.setLongitude(dest.longitude);
        double distance = locationA.distanceTo(locationB)*0.621371;
        Double cost=distance*Integer.parseInt(inputpeopl.getText().toString())*0.2;
        inputcost.setText(""+cost);*/
    // selectedcheck=i;
        Double peoplecount=Double.parseDouble(inputpeopl.getText().toString());

      try{ cost =(globalcost+Double.parseDouble(places.get(i).getCost()))*peoplecount;
          Log.d("democost",""+cost);
        inputcost.setText(""+cost);
          globalcost=cost;
    }catch(NumberFormatException ex){ // handle your exception
ex.getMessage();
    }
        Log.d("democost",""+cost);
    }
}
