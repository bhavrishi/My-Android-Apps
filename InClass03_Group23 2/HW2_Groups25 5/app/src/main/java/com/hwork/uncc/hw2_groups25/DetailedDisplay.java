package com.hwork.uncc.hw2_groups25;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class DetailedDisplay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_display_detailed);
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();

        /*List<Contact> selectedCont=
                (List<Contact>)bundle.getSerializable("value");*/
        int position = bundle.getInt("position");
        final List<Contact> storedCont=CreateContactActivity.retrieveContactDetails();
        Contact contact = storedCont.get(position);
        TextView fname= (TextView) findViewById(R.id.detName);
        TextView lname =(TextView)findViewById(R.id.detLastName);
        TextView  phoneNumber =(TextView)findViewById(R.id.detPhoneNumber);
        TextView  email =(TextView)findViewById(R.id.detEmail);
        final TextView  url =(TextView)findViewById(R.id.detUrl);
        final TextView  skypeUrl =(TextView)findViewById(R.id.detSkypeUrl);
        final TextView  youtubeUrl =(TextView)findViewById(R.id.detYutubeUrl);
        final TextView  fbUrl =(TextView)findViewById(R.id.detFbUrl);
        final TextView  twitter =(TextView)findViewById(R.id.detTwitter);
        TextView  company =(TextView)findViewById(R.id.detCompany);
        TextView  address =(TextView)findViewById(R.id.detAddress);
        TextView  birthday =(TextView)findViewById(R.id.detBirthday);
        TextView  nickname =(TextView)findViewById(R.id.detNickname);


        fname.setText(contact.getFirstName());
        lname.setText(contact.getLastname());
        phoneNumber.setText(contact.getPhoneNumber());
        email.setText(contact.getEmail());
        url.setText(contact.getUrl());
        skypeUrl.setText(contact.getSkype());
        youtubeUrl.setText(contact.getUtb());
        fbUrl.setText(contact.getFburl());
        twitter.setText(contact.getTwitURL());
        company.setText(contact.getCompany());
        address.setText(contact.getAddress());
        birthday.setText(contact.getBday());
        nickname.setText(contact.getNickname());

        url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(url.length() !=0) {
                    try {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url.getText().toString()));
                        startActivity(intent);
                    }
                    catch (Exception ex){
                        Toast.makeText(DetailedDisplay.this, "Invalid Uri", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        skypeUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (skypeUrl.length() != 0) {
                    try {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(skypeUrl.getText().toString()));
                        startActivity(intent);
                    } catch (Exception ex) {
                        Toast.makeText(DetailedDisplay.this, "Invalid Uri", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        youtubeUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (youtubeUrl.length() != 0) {
                    try {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(youtubeUrl.getText().toString()));
                        startActivity(intent);
                    } catch (Exception ex) {
                        Toast.makeText(DetailedDisplay.this, "Invalid Uri", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        fbUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fbUrl.length() != 0) {
                    try {

                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(fbUrl.getText().toString()));
                        startActivity(intent);
                    } catch (Exception ex) {
                        Toast.makeText(DetailedDisplay.this, "Invalid Uri", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if (twitter.length() != 0) {
                        try {

                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(twitter.getText().toString()));
                            startActivity(intent);
                        } catch (Exception ex) {
                            Toast.makeText(DetailedDisplay.this, "Invalid Uri", Toast.LENGTH_SHORT).show();
                        }
                    }
            }
        });



    }
}
