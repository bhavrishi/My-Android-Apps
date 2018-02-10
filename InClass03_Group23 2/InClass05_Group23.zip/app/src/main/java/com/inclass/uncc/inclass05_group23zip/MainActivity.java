package com.inclass.uncc.inclass05_group23zip;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList listid = new ArrayList();
    String URL=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_one);
        final EditText inputDish = (EditText) findViewById(R.id.inputdish);
        final EditText ing1 = (EditText) findViewById(R.id.input1);
        final EditText ing2 = (EditText) findViewById(R.id.input2);
        final EditText ing3 = (EditText) findViewById(R.id.input3);
        final EditText ing4 = (EditText) findViewById(R.id.input4);
        final EditText ing5 = (EditText) findViewById(R.id.input5);
        final ImageView img1 = (ImageView) findViewById(R.id.imgadd1);
        final ImageView img2 = (ImageView) findViewById(R.id.img2);
        final ImageView img3 = (ImageView) findViewById(R.id.img3);
        final ImageView img4 = (ImageView) findViewById(R.id.img4);
        final ImageView img5 = (ImageView) findViewById(R.id.img5);
        ing2.setVisibility(View.INVISIBLE);
        img2.setVisibility(View.INVISIBLE);
        ing3.setVisibility(View.INVISIBLE);
        img3.setVisibility(View.INVISIBLE);
        ing4.setVisibility(View.INVISIBLE);
        img4.setVisibility(View.INVISIBLE);
        img5.setVisibility(View.INVISIBLE);
        ing5.setVisibility(View.INVISIBLE);

if(inputDish.getText().toString()!=null) {
    Button btnSearch = (Button) findViewById(R.id.button);
    btnSearch.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (ing1.getText().toString() != null) {
                Log.d("demoin", ing1.getText().toString());
                listid.add(ing1.getText().toString());
            }
            if (ing2.getText().toString() != null) {
                Log.d("demoin", ing2.getText().toString());
                listid.add(ing2.getText().toString());
            }
            if (ing3.getText().toString() != null) {
                Log.d("demoin", ing3.getText().toString());
                listid.add(ing3.getText().toString());
            }
            if (ing4.getText().toString() != null) {
                Log.d("demoin", ing4.getText().toString());
                listid.add(ing4.getText().toString());
            }
            if (ing5.getText().toString() != null) {
                Log.d("demoin", ing5.getText().toString());
                listid.add(ing5.getText().toString());
            }
            if (inputDish.getText().toString() != null) {
                Log.d("demoin", inputDish.getText().toString());
                listid.add(inputDish.getText().toString());
            }
            if (ing2.getText().toString() != null) {
                URL = "http://www.recipepuppy.com/api/?i=" + listid.get(0)+","+listid.get(1)+"&q="+listid.get(5);
                Log.d("demo", URL);
            }
            if (ing3.getText().toString() != null) {
                URL = "http://www.recipepuppy.com/api/?i=" + listid.get(0)+","+listid.get(1)+","+listid.get(2)+"&q="+listid.get(5);
                Log.d("demo", URL);
            }
            if (ing4.getText().toString() != null) {
                URL = "http://www.recipepuppy.com/api/?i=" + listid.get(0)+","+listid.get(1)+","+listid.get(2)+","+listid.get(3)+"&q="+listid.get(5);
                Log.d("demo", URL);
            }
            if (ing1.getText().toString() != null) {
                URL = "http://www.recipepuppy.com/api/?i=" + listid.get(0)+"&q="+listid.get(5);
                Log.d("demo", URL);
            }
            if (ing5.getText().toString() != null) {
                URL = "http://www.recipepuppy.com/api/?i=" + listid.get(0)+","+listid.get(1)+","+listid.get(2)+","+listid.get(3)+","+listid.get(4)+"&q="+listid.get(5);
                Log.d("demo", URL);
            }
        }
    });
}


        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img1.setImageResource(R.drawable.remove);
                ing2.setVisibility(View.VISIBLE);
                img2.setVisibility(View.VISIBLE);

            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img2.setImageResource(R.drawable.remove);
                ing3.setVisibility(View.VISIBLE);
                img3.setVisibility(View.VISIBLE);
            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img3.setImageResource(R.drawable.remove);
                ing4.setVisibility(View.VISIBLE);
                img4.setVisibility(View.VISIBLE);
            }
        });
        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img4.setImageResource(R.drawable.remove);
                ing5.setVisibility(View.VISIBLE);
                img5.setVisibility(View.VISIBLE);
            }
        });
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img1.setImageResource(R.drawable.remove);
                ing2.setVisibility(View.VISIBLE);
                img2.setVisibility(View.VISIBLE);
            }
        });



    }
}