package com.hwork.uncc.periousmid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new AsyncParse(MainActivity.this).execute("https://itunes.apple.com/us/rss/topgrossingapplications/limit=100/xml");
    }
}
