package com.hwork.uncc.datapassing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity implements Async.IData {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Async(this).execute("Some url..");
    }
    public void setupdata(LinkedList<String> data)
    {
       ListView lv= (ListView) findViewById(R.id.lview);
        ArrayAdapter adp=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,data);
lv.setAdapter(adp);
    }
}
