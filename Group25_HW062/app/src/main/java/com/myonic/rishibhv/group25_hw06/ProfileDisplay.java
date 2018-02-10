package com.myonic.rishibhv.group25_hw06;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileDisplay extends AppCompatActivity {
    DatabaseReference databaseUsers;
    public static final String UID = "uid";
    public static final String NAME = "name";
    public static final String CLICKEDNAME = "cname";
    public static final String CLICKEDID = "cid";
    String id_main, uname, cname, cid; FirebaseAuth mAuth;
    TextView fname, lname, email, dob, pwd;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                mAuth.signOut();
                Intent i=new Intent(ProfileDisplay.this,MainActivity.class);
                startActivity(i);
                return (true);

        }
        return (super.onOptionsItemSelected(item));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_display);
        Intent intent = getIntent();
        id_main = intent.getStringExtra(UID);
        uname = intent.getStringExtra(NAME);
        mAuth=FirebaseAuth.getInstance();
        //  cname = intent.getStringExtra(CLICKEDNAME);
        // cid = intent.getStringExtra(CLICKEDID);
        databaseUsers = FirebaseDatabase.getInstance().getReference("users").child(intent.getStringExtra(UID));

        fname = (TextView) findViewById(R.id.editTextFirstName);
        lname = (TextView) findViewById(R.id.editTextLastName);
        email = (TextView) findViewById(R.id.editTextEmailAct2);
        dob = (TextView) findViewById(R.id.dob);
        pwd = (TextView) findViewById(R.id.editTextPasswordAct2);


        databaseUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // users.clear();
                for (DataSnapshot postsnapshot : dataSnapshot.getChildren()) {
                    if ((postsnapshot.getKey()).equals("dob"))
                        dob.setText(postsnapshot.getValue(String.class));
                    else if ((postsnapshot.getKey()).equals("email"))
                        email.setText(postsnapshot.getValue(String.class));
                    else if ((postsnapshot.getKey()).equals("name"))
                        fname.setText(postsnapshot.getValue(String.class));
                    else if ((postsnapshot.getKey()).equals("password"))
                        pwd.setText(postsnapshot.getValue(String.class));
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
