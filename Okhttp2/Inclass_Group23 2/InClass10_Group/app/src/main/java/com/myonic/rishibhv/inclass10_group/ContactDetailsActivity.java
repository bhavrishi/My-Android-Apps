package com.myonic.rishibhv.inclass10_group;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.List;

public class ContactDetailsActivity extends AppCompatActivity {
    Button btnsubmit;
    EditText name, email, phone;
    RadioGroup radiogrp;
    RadioButton radiobtn;
    DatabaseReference databaseContacts;
    public static final String UID = "uid";
    String main_id;
    ImageView defaultimg;
    String selectedimg = null;
    List<Contact> contacts;
    final static int REQ_CODE = 100;
    final static String VALUE_KEY = "value";

    @Override
    protected void onStart() {
        super.onStart();
        databaseContacts.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                contacts.clear();
                for (DataSnapshot postsnapshot : dataSnapshot.getChildren()) {
                    contacts.add(postsnapshot.getValue(Contact.class));
                }
                // ArtistList madapter=new ArtistList(MainActivity.this,artists);
                // listViewArtists.setAdapter(madapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQ_CODE) {
            if (resultCode == RESULT_CANCELED) {
                Log.d("demo", "No valuew");
            }
            if (resultCode == RESULT_OK) {
                String value = data.getExtras().getString(VALUE_KEY);
                if (value.equals("img1")) {
                    defaultimg.setImageResource(R.drawable.avatar_f_1);
                    selectedimg = "img1";
                }

                if (value.equals("img2")) {
                    defaultimg.setImageResource(R.drawable.avatar_f_2);
                    selectedimg = "img2";
                }
                if (value.equals("img3")) {
                    defaultimg.setImageResource(R.drawable.avatar_f_3);
                    selectedimg = "img3";
                }

                if (value.equals("img4")) {
                    defaultimg.setImageResource(R.drawable.avatar_m_1);
                    selectedimg = "img4";
                }
                if (value.equals("img5")) {
                    defaultimg.setImageResource(R.drawable.avatar_m_2);
                    selectedimg = "img2";
                }
                if (value.equals("img6")) {
                    defaultimg.setImageResource(R.drawable.avatar_m_3);
                    selectedimg = "img6";
                }
                Log.d("demo", value);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);
        Intent intent = getIntent();
        main_id = intent.getStringExtra(UID);
        databaseContacts = FirebaseDatabase.getInstance().getReference("contacts").child(intent.getStringExtra(UID));
        contacts = new ArrayList<Contact>();

        btnsubmit = (Button) findViewById(R.id.btnSubmit);
        name = (EditText) findViewById(R.id.inputname);
        phone = (EditText) findViewById(R.id.inputphone);
        email = (EditText) findViewById(R.id.inputemail);
        defaultimg = (ImageView) findViewById(R.id.imgcontact);
        defaultimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ContactDetailsActivity.this, SelectAvatar.class);
                startActivityForResult(i, REQ_CODE);
            }
        });

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radiogrp = (RadioGroup) findViewById(R.id.radiogrp);
                int selectedId = radiogrp.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                radiobtn = (RadioButton) findViewById(selectedId);
                String depatmnt = findDepartment(selectedId);
                Log.d("demodept", depatmnt + "" + selectedimg + " " + name.getText().toString() + " " + phone.getText().toString());
                saveContacttoDB(name.getText().toString(), email.getText().toString(), phone.getText().toString(), depatmnt, selectedimg);
            }
        });
    }

    private void saveContacttoDB(String name, String email, String phone, String dept, String img) {
        /*if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(phone)) {
            String id = databaseContacts.push().getKey();
            Contact contact = new Contact(id, name, email, phone, dept, img);
            databaseContacts.child(id).setValue(contact);
            // editTextName.setText("");

            //displaying a success toast
            Toast.makeText(this, "Artist added", Toast.LENGTH_LONG).show();
            Intent i = new Intent(ContactDetailsActivity.this, DisplayActivity.class);
            i.putExtra(UID, main_id);
            //i.putExtra("ALIST",contacts);
            startActivity(i);
        } else {
            Toast.makeText(ContactDetailsActivity.this, "Please input name and phone number", Toast.LENGTH_SHORT).show();
        }*/

        if(TextUtils.isEmpty(name)){
            Toast.makeText(this, "Please enter the name", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(phone)){
            Toast.makeText(this, "Please enter the phone", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(dept)){
            Toast.makeText(this, "Please select the department", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(img)){
            Toast.makeText(this, "Please select the avatar image", Toast.LENGTH_SHORT).show();
        }
        else{
            String id = databaseContacts.push().getKey();
            Contact contact = new Contact(id, name, email, phone, dept, img);
            databaseContacts.child(id).setValue(contact);
            // editTextName.setText("");

            //displaying a success toast
            Toast.makeText(this, "Contact added", Toast.LENGTH_LONG).show();
            Intent i = new Intent(ContactDetailsActivity.this, DisplayActivity.class);
            i.putExtra(UID, main_id);
            //i.putExtra("ALIST",contacts);
            startActivity(i);
        }

    }

    private String findDepartment(int checkedRadioButtonId) {
        String department = "";
        if (checkedRadioButtonId == R.id.radiosis) {
            department = "SIS";
        } else if (checkedRadioButtonId == R.id.radiocs) {
            department = "CS";
        } else if (checkedRadioButtonId == R.id.radiobio) {
            department = "BIO";
        }
        return department;
    }
}
