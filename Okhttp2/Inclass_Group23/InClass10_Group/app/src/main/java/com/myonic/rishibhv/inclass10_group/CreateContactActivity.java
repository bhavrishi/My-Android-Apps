/*
package com.myonic.rishibhv.inclass10_group;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateContactActivity extends AppCompatActivity {
    DatabaseReference databaseContacts;
    Button btnCreate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        databaseContacts = FirebaseDatabase.getInstance().getReference("contacts").child(intent.getStringExtra(MainActivity.UID));

    }
}
*/
