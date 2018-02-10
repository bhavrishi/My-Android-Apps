package com.hwork.uncc.hw2_groups25;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class DetailedDisplay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_display_detailed);
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();

        List<Contact> selectedCont=
                (List<Contact>)bundle.getSerializable("value");
        final List<Contact> storedCont=CreateContactActivity.retrieveContactDetails();
        TextView fname= (TextView) findViewById(R.id.detName);
        fname.setText(selectedCont.get(0).getFirstName());
    }
}
