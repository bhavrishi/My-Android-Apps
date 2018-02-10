package com.example.rishi.intent_previous_03;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    final static int REQ_CODE = 100;
    final static String NAME_KEY = "name";
    final static String STU_KEY = "STUDENT";
    int progress;
    private RadioGroup radiogrp;
    String mood;
    private RadioButton radiobtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_relative);
        Button btnsubmit = (Button) findViewById(R.id.buttonsubmit);

        final TextView textname = (TextView) findViewById(R.id.textname);
        final TextView textemail = (TextView) findViewById(R.id.textemail);
        final Switch s = (Switch) findViewById(R.id.switch1);
        final SeekBar seek = (SeekBar) findViewById(R.id.seekBar);
        /*TextView mood= (TextView) findViewById(R.id.);
        TextView textname= (TextView) findViewById(R.id.textname);
        TextView textname= (TextView) findViewById(R.id.textname);*/
        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                progress = i;
                Log.d("demo", String.valueOf(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radiogrp = (RadioGroup) findViewById(R.id.radiogrp);
                int selectedId = radiogrp.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                radiobtn = (RadioButton) findViewById(selectedId);
                if (s.isChecked()) {
                    mood = "Ünsearchable";
                } else {
                    mood = "Searchable";
                }

                Intent i = new Intent(MainActivity.this, SecondActivity.class);
                i.putExtra(NAME_KEY, "Rishitha");
                Student user = new Student(textname.getText().toString(), textemail.getText().toString(), String.valueOf(radiobtn.getText()), String.valueOf(progress), mood);
                i.putExtra(STU_KEY, user);
                startActivity(i);
            }
        });
    }
}
