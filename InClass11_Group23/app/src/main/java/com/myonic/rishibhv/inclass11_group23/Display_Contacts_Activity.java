package com.myonic.rishibhv.inclass11_group23;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class Display_Contacts_Activity extends AppCompatActivity {
String id_main;
    DatabaseReference databaseContacts;
    private Bitmap bitmap;
    private RecyclerView recyclerView;
    private FirebaseAuth mAuth;
    //adapter object
    private RecyclerView.Adapter adapter;


    private ProgressDialog progressDialog;


    private List<Contact> contacts;
    private StorageReference imageReference,fileRef;
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
                Intent i=new Intent(Display_Contacts_Activity.this,MainActivity.class);
                startActivity(i);
                return (true);

        }
        return (super.onOptionsItemSelected(item));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_contacts);
        Intent intent = getIntent();
        id_main=intent.getStringExtra(Constants.UID);
        Log.d("demo",id_main);
        String name=intent.getStringExtra(Constants.NAME);
        mAuth=FirebaseAuth.getInstance();
        databaseContacts = FirebaseDatabase.getInstance().getReference("contacts").child(id_main);
        fileRef=null;
        imageReference = FirebaseStorage.getInstance().getReference().child("images").child(id_main);

        recyclerView = (RecyclerView) findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        progressDialog = new ProgressDialog(this);
TextView outname= (TextView) findViewById(R.id.outName);
        outname.setText(name);
        contacts = new ArrayList<>();

        //displaying progress dialog while fetching images
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        ImageView imgadd= (ImageView) findViewById(R.id.imgadd);
        ImageView imgedit=(ImageView) findViewById(R.id.imageView2);
        imgedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Display_Contacts_Activity.this,EditSignInUserActivity.class);
                i.putExtra(Constants.UID,id_main);
                startActivity(i);
            }
        });
        imgadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Display_Contacts_Activity.this,AddContactActivity.class);
                i.putExtra(Constants.UID,id_main);
                startActivity(i);
            }
        });

        databaseContacts.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                //dismissing the progress dialog

contacts.clear();
                //iterating through all the values in database
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    Contact contact = postSnapshot.getValue(Contact.class);
                    contacts.add(contact);
                }
                //creating adapter
                adapter = new MyAdapter(Display_Contacts_Activity.this, contacts);

                //adding adapter to recyclerview
                recyclerView.setAdapter(adapter);
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                progressDialog.dismiss();
            }
        });

    }
    public  void editContactMethod(Contact cont)
    {
        Intent i=new Intent(Display_Contacts_Activity.this,EditContactActivity.class);
        i.putExtra(Constants.UID,id_main);
        i.putExtra("ALIST",cont);
        startActivity(i);

    }

    public void deleteContact(String cont, String phone) {
        DatabaseReference dbC = FirebaseDatabase.getInstance().getReference("contacts").child(id_main).child(cont);
        fileRef = FirebaseStorage.getInstance().getReference().child("images").child(id_main).child(phone+"."+"jpg");
        dbC.removeValue();
        fileRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(Display_Contacts_Activity.this, "Deleted", Toast.LENGTH_LONG).show();
            }
        });
        Toast.makeText(Display_Contacts_Activity.this,"Contact Removed",Toast.LENGTH_SHORT).show();
    }
}
