package com.inclass.uncc.inclass03_group23;

//Assignment Inclass 03 Group 23
//Names: Bhavya Gedi, Rosy Azad
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int progress;
    ImageView defaultimg;
    String selectedimg;
    String mood;Department user;
    private RadioGroup radiogrp;
    private RadioButton radiobtn;
    final static int REQ_CODE = 100;
    final static String VALUE_KEY = "value";
    final static String Dept_KEY = "DEPARTMENT";

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
        setContentView(R.layout.layout_one);
        Button submit = (Button) findViewById(R.id.btnSubmit);
        final TextView valuemood = (TextView) findViewById(R.id.valuemood);
        defaultimg = (ImageView) findViewById(R.id.defaultimage);
        defaultimg.setImageResource(R.drawable.select_avatar);

        final ImageView imgmood = (ImageView) findViewById(R.id.imgmood);
        imgmood.setImageResource(R.drawable.angry);
        valuemood.setText("Ängry");

        final SeekBar seek = (SeekBar) findViewById(R.id.seekBar);
        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                progress = i;
                if (progress == 0) {
                    imgmood.setImageResource(R.drawable.angry);
                    valuemood.setText("Ängry");
                    mood = "Angry";
                } else if (progress == 1) {
                    imgmood.setImageResource(R.drawable.sad);
                    valuemood.setText("Sad");
                    mood = "Sad";
                } else if (progress == 2) {
                    imgmood.setImageResource(R.drawable.happy);
                    valuemood.setText("Happy");
                    mood = "Happy";
                } else if (progress == 3) {


                    imgmood.setImageResource(R.drawable.awesome);
                    valuemood.setText("Awesome");
                    mood = "Awesome";
                }
                Log.d("demo", String.valueOf(i));
            }


            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        defaultimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, SelectAvatar.class);
                startActivityForResult(i, REQ_CODE);
            }
        });

        final TextView textname = (TextView) findViewById(R.id.inputname);
        final TextView textemail = (TextView) findViewById(R.id.inputemail);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radiogrp = (RadioGroup) findViewById(R.id.radiogrp);
                int selectedId = radiogrp.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                radiobtn = (RadioButton) findViewById(selectedId);
 String depatmnt=findDepartment(selectedId);
                user = new Department(textname.getText().toString(), textemail.getText().toString(), depatmnt, selectedimg, mood);
                if(user.name.length()==0||user.email.length()==0||user.img.length()==0||selectedId==-1)
                {   Toast.makeText(MainActivity.this, "Please fill all the values before submitting", Toast.LENGTH_SHORT).show();

                }
              else   if(isEmailValid(user.email)){

                    Toast.makeText(MainActivity.this, "Please enter valid email format", Toast.LENGTH_SHORT).show();
                }
                else {


                    Intent i = new Intent(MainActivity.this, DisplayActivity.class);
                    // i.putExtra(NAME_KEY, "Rishitha");

                    i.putExtra(Dept_KEY, user);
                    Log.d("demo", user.name.toString() + user.email.toString() + depatmnt + selectedimg + mood);
                    startActivity(i);
                }
            }
        });

    }

    private String findDepartment(int checkedRadioButtonId) {

        String department = "default";
        if(checkedRadioButtonId==R.id.radiosis){
            department = "SIS";
        }
        else if(checkedRadioButtonId==R.id.radiocs){
            department = "CS";
        }
        else if(checkedRadioButtonId==R.id.radiobio){
            department = "BIO";
        }
        return department;
    }
   public  boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();}
}