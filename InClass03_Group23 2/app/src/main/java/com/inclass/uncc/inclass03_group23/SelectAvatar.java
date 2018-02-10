package com.inclass.uncc.inclass03_group23;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class SelectAvatar extends AppCompatActivity {
    String value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_select_avatar);
        final ImageView img1 = (ImageView) findViewById(R.id.img1);
        img1.setImageResource(R.drawable.avatar_f_1);
        final ImageView img2 = (ImageView) findViewById(R.id.img2);
        img2.setImageResource(R.drawable.avatar_f_2);
        final ImageView img3 = (ImageView) findViewById(R.id.img3);
        img3.setImageResource(R.drawable.avatar_f_3);
        final ImageView img4 = (ImageView) findViewById(R.id.img4);
        img4.setImageResource(R.drawable.avatar_m_1);
        final ImageView img5 = (ImageView) findViewById(R.id.img5);
        img5.setImageResource(R.drawable.avatar_m_2);
        final ImageView img6 = (ImageView) findViewById(R.id.img6);
        img6.setImageResource(R.drawable.avatar_m_3);


        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                value = "img1";
                Log.d("demo", value);

                if (value == null || value.length() == 0) {
                    setResult(RESULT_CANCELED);
                } else {
                    Intent i = new Intent();
                    i.putExtra(MainActivity.VALUE_KEY, value);
                    setResult(RESULT_OK, i);
                }
                finish();
            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                value = "img2";
                Log.d("demo", value);

                if (value == null || value.length() == 0) {
                    setResult(RESULT_CANCELED);
                } else {
                    Intent i = new Intent();
                    i.putExtra(MainActivity.VALUE_KEY, value);
                    setResult(RESULT_OK, i);
                }
                finish();
            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                value = "img3";
                Log.d("demo", value);

                if (value == null || value.length() == 0) {
                    setResult(RESULT_CANCELED);
                } else {
                    Intent i = new Intent();
                    i.putExtra(MainActivity.VALUE_KEY, value);
                    setResult(RESULT_OK, i);
                }
                finish();
            }
        });

        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                value = "img4";
                Log.d("demo", value);

                if (value == null || value.length() == 0) {
                    setResult(RESULT_CANCELED);
                } else {
                    Intent i = new Intent();
                    i.putExtra(MainActivity.VALUE_KEY, value);
                    setResult(RESULT_OK, i);
                }
                finish();
            }
        });
        img5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                value = "img5";
                Log.d("demo", value);

                if (value == null || value.length() == 0) {
                    setResult(RESULT_CANCELED);
                } else {
                    Intent i = new Intent();
                    i.putExtra(MainActivity.VALUE_KEY, value);
                    setResult(RESULT_OK, i);
                }
                finish();
            }
        });
        img6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                value = "img6";
                Log.d("demo", value);

                if (value == null || value.length() == 0) {
                    setResult(RESULT_CANCELED);
                } else {
                    Intent i = new Intent();
                    i.putExtra(MainActivity.VALUE_KEY, value);
                    setResult(RESULT_OK, i);
                }
                finish();
            }
        });
    }

}
