package com.myonic.rishibhv.firebasepush;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.plus.model.people.Person;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

import static android.R.attr.value;

public class DisplayActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private DatabaseReference mMessageReference;
    private ValueEventListener mMessageListener;
    private String personId;
    EditText name,phonenum;
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
//        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        Intent intent = getIntent();
        mDatabase=FirebaseDatabase.getInstance().getReference("Person").child(intent.getStringExtra(MainActivity.UID));;
      //  FirebaseDatabase.getInstance().getReference("tracks").child(intent.getStringExtra(MainActivity.ARTIST_ID));

        personId= mDatabase.push().getKey();
        name= (EditText) findViewById(R.id.fullNameEditText);
        phonenum= (EditText) findViewById(R.id.phoneNumberEditText);
        lv= (ListView) findViewById(R.id.peopleList);
        findViewById(R.id.addBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
addPerson(name.getText().toString(),phonenum.getText().toString());
            }
        });
        findViewById(R.id.loadBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadview();
            }
        });
        findViewById(R.id.updateBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePerson(name.getText().toString(),phonenum.getText().toString());
            }
        });

        (findViewById(R.id.findBtn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findPerson(((EditText)findViewById(R.id.fullNameEditText)).getText().toString());
            }
        });
    }

    private void findPerson(String s) {
        Query deleteQuery = mDatabase.orderByChild("name").equalTo(s);
        deleteQuery.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Iterable<DataSnapshot> snapshotIterator = dataSnapshot.getChildren();
                Iterator<DataSnapshot> iterator = snapshotIterator.iterator();
                while((iterator.hasNext())){
                    Log.d("Item found: ",iterator.next().getValue().toString()+"---");
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("Item not found: ","this item is not in the list");
            }
        });
    }

    private void updatePerson(String s, String s1) {
        mDatabase.child(personId).child("name").setValue(s);
        mDatabase.child(personId).child("phone").setValue(s1);
    }

    private void loadview() {
        final ArrayList names = new ArrayList<>();
        final ArrayList phoneNumbers = new ArrayList<>();
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                names.clear();
                phoneNumbers.clear();
                Iterable<DataSnapshot> snapshotIterator = dataSnapshot.getChildren();
                Iterator<DataSnapshot> iterator = snapshotIterator.iterator();
                while((iterator.hasNext())){

                    User value = iterator.next().getValue(User.class);

                    names.add(value.name);
                    phoneNumbers.add(value.phone);}
                    ((ListView)findViewById(R.id.peopleList)).
                            setAdapter(new CustomListAdapater(names,phoneNumbers,DisplayActivity.this)); }



            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //setAdapter(new ArrayAdapter<>(getApplicationContext(), R.layout.people_list_row, R.id.personNameTv,names));
    }

    private void addPerson(String name, String phoneNumber) {
        //mDatabase=FirebaseDatabase.getInstance().getReference("Person");
        String personId = mDatabase.push().getKey();
        User person = new User(name,phoneNumber);
        mDatabase.child(personId).setValue(person);
    }
}
