package com.inclass.uncc.fragmentdemo;

import android.app.Fragment;
import android.graphics.Color;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements AFragment.OnFragmentChanged{
AFragment f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
getFragmentManager().beginTransaction().add(R.id.container,new AFragment(),"tag_af")
        .commit();
        getFragmentManager().beginTransaction().add(R.id.container,new AFragment(),"tag_af1")
                .commit();
        RadioGroup rg= (RadioGroup) findViewById(R.id.rg);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                f= (AFragment) getFragmentManager().findFragmentByTag("tag_af");
       // f= (AFragment) getFragmentManager().findFragmentById(R.id.fragment);
                if(i==R.id.radioButtonblue)
                {
f.changecolor(Color.BLUE);
                }
                else if(i==R.id.radioButtongreen)
                {
f.changecolor(Color.GREEN);
                }
              else  if(i==R.id.radioButtonred)
                {
f.changecolor(Color.RED);
                }
            }
        });

    }

    @Override
    public void onTextChanged(String text) {
        TextView tv= (TextView) findViewById(R.id.textView);
        tv.setText(text);
    }
}
