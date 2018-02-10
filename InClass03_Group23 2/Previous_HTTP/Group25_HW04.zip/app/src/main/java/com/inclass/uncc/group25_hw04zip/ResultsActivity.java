package com.inclass.uncc.group25_hw04zip;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {
int resultCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_results);
        Bundle b=getIntent().getExtras();
       if(b!=null){

          resultCount= b.getInt("COUNT");
           Log.d("resultcount",""+resultCount);
    }
    int value=(resultCount/15)*100;
        TextView textpercent= (TextView) findViewById(R.id.textView5);
        textpercent.setText(value+"%");
}}
