package com.inclass.uncc.inclass03_group23;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class DisplayActivity extends AppCompatActivity {
    Department dept;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_display);
        TextView finalname = (TextView) findViewById(R.id.valuename);
        TextView email = (TextView) findViewById(R.id.valueemail);
        TextView deptname = (TextView) findViewById(R.id.valuedept);
        TextView moodtext = (TextView) findViewById(R.id.textfinalmood);
        ImageView imgfinalmood= (ImageView) findViewById(R.id.imgfinalmood);
        ImageView imgfinal= (ImageView) findViewById(R.id.imgfinal);

        if (getIntent().getExtras() != null)
            dept = (Department) getIntent().getExtras().getSerializable(MainActivity.Dept_KEY);
        finalname.setText(dept.getName());
       email.setText(dept.email);

        deptname.setText(dept.deptname);
        if(dept.img.equals("img1"))
        {
            imgfinal.setImageResource(R.drawable.avatar_f_1);
        }
         if(dept.img.equals("img2"))
        {
            imgfinal.setImageResource(R.drawable.avatar_f_2);
        }
        if(dept.img.equals("img3"))
        {
            imgfinal.setImageResource(R.drawable.avatar_f_3);
        }
        if(dept.img.equals("img4"))
        {
            imgfinal.setImageResource(R.drawable.avatar_m_1);
        }
        if(dept.img.equals("img5"))
        {
            imgfinal.setImageResource(R.drawable.avatar_m_2);
        }
        if(dept.img.equals("img6"))
        {
            imgfinal.setImageResource(R.drawable.avatar_m_3);
        }
        if (dept.mood.equals("Angry"))
        {
            moodtext.setText("Ï am Angry");
            imgfinalmood.setImageResource(R.drawable.angry);
        }
        if (dept.mood .equals("Sad"))
        {
            moodtext.setText("I am Sad");
            imgfinalmood.setImageResource(R.drawable.sad);
        }
        if (dept.mood.equals("Happy"))
        {
            moodtext.setText("Ï am Happy");
            imgfinalmood.setImageResource(R.drawable.happy);
        }
        if (dept.mood.equals("Awesome"))
        {
            moodtext.setText("Ï am Awesome");
            imgfinalmood.setImageResource(R.drawable.awesome);
        }


        Log.d("demo", dept.toString());

    }
}
