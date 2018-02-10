package com.myonic.rishibhv.previous_final;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class AddPlacesActivity extends AppCompatActivity {
AutocompleteCity City;
    DatabaseReference dbPlaces;
    ArrayList<Place> places;ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_places);
        City=new AutocompleteCity();
        places=new ArrayList<Place>();
        lv= (ListView) findViewById(R.id.lv3);
        Intent intent = getIntent();

        City= (AutocompleteCity) intent.getSerializableExtra("ALIST");
        dbPlaces = FirebaseDatabase.getInstance().getReference("places").child(City.getId());
        Log.d("demonearurl","https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="+City.getLat()+","+City.getLang()+"&radius=1000&language=pt_BR&key=AIzaSyBMovGdEmLnbpayEtiY8iJdd4ozLuZQQH4");
        try {
            places= new AsyncNearBy().execute("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="+City.getLat()+","+City.getLang()+"&radius=1000&language=pt_BR&key=AIzaSyBMovGdEmLnbpayEtiY8iJdd4ozLuZQQH4").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
if(places!=null)
{
    SelectPlaceAdapter madapter=new SelectPlaceAdapter(AddPlacesActivity.this,places);
    lv.setAdapter(madapter);
    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String idgen = dbPlaces.push().getKey();
            Place contact = new Place(City.getId(), idgen,places.get(position).getLat(),places.get(position).getLang(),places.get(position).getPlacename(),places.get(position).getImgurl());
            dbPlaces.child(idgen).setValue(contact);
            // editTextName.setText("");

            //displaying a success toast
            Toast.makeText(AddPlacesActivity.this, "Place added", Toast.LENGTH_LONG).show();
           Intent i = new Intent(AddPlacesActivity.this, TripsDisplayActivity.class);
            //  i.putExtra(UID, main_id);
            i.putExtra("ALIST",places.get(position));
            startActivity(i);
        }
    });
}

    }
}
