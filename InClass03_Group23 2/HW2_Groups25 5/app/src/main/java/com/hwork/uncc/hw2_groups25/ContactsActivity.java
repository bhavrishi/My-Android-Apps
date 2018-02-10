package com.hwork.uncc.hw2_groups25;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ContactsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_one);
        Button btncreate= (Button) findViewById(R.id.btncreate);
        Button btndisplay= (Button) findViewById(R.id.btndisplay);
        Button btndelete= (Button) findViewById(R.id.btndelete);
        Button btnFinish = (Button) findViewById(R.id.btnfinish);
        btncreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContactsActivity.this, CreateContactActivity.class);
                startActivity(intent);
            }
        });
        btndisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactsActivity.this, DisplayActivity.class);
                startActivity(intent);
            }
        });
        Button btnedit= (Button) findViewById(R.id.btnedit);
        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactsActivity.this, DeleteActivity.class);
                startActivity(intent);
            }
        });
        btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactsActivity.this, EditActivity.class);
                startActivity(intent);
            }
        });
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
