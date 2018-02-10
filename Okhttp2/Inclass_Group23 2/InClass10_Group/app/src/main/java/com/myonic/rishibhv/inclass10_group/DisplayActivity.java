package com.myonic.rishibhv.inclass10_group;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DisplayActivity extends AppCompatActivity {
    DatabaseReference databaseContacts;
    ListView lv; String id_main;
    public static final String UID="uid";
    Button btnCreate,btnLogOut;
    List<Contact> contacts;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        btnLogOut= (Button) findViewById(R.id.btnlogout);
        Intent intent = getIntent();
        id_main=intent.getStringExtra(UID);
      /*if( getIntent().getExtras() != null) */ databaseContacts = FirebaseDatabase.getInstance().getReference("contacts").child(intent.getStringExtra(UID));
contacts=new ArrayList<Contact>();
        btnCreate= (Button) findViewById(R.id.btnCreate);
        lv= (ListView) findViewById(R.id.lv);
       // lv.setBackgroundColor(Color.LTGRAY);
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                deleteContact(contacts.get(position).getId(),position);
                return true;
            }
        });
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Toast.makeText(DisplayActivity.this,"Logging Out",Toast.LENGTH_SHORT).show();
                Intent i=new Intent(DisplayActivity.this,MainActivity.class);

                startActivity(i);
            }
        });
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
Intent i=new Intent(DisplayActivity.this,ContactDetailsActivity.class);
                i.putExtra(UID,id_main);
startActivity(i);
            }
        });
        databaseContacts.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                contacts.clear();
                for(DataSnapshot postsnapshot:dataSnapshot.getChildren())
                {
                    contacts.add(postsnapshot.getValue(Contact.class));
                }
                CustomListAdapater madapter=new CustomListAdapater(DisplayActivity.this,contacts);
                lv.setAdapter(madapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private boolean deleteContact(String id, int position) {
       DatabaseReference dR = FirebaseDatabase.getInstance().getReference("contacts").child(id_main).child(id);

        //removing artist
        dR.removeValue();
contacts.remove(position);
        CustomListAdapater madapter=new CustomListAdapater(DisplayActivity.this,contacts);
        lv.setAdapter(madapter);

        //getting the tracks reference for the specified artist

        Toast.makeText(getApplicationContext(), "Artist Deleted", Toast.LENGTH_LONG).show();

        return true;
    }
}
