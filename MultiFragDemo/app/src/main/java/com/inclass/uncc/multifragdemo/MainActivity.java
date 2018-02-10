package com.inclass.uncc.multifragdemo;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements Fragment1.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getFragmentManager().beginTransaction().add(R.id.container,new Fragment1(),"tag_af")
                .commit(); }


    @Override
    public void onBackPressed() {
        if(getFragmentManager().getBackStackEntryCount()>0)
        {
            getFragmentManager().popBackStack();
        }else
        super.onBackPressed();
    }

    @Override
    public void gotonextfrag() {
        getFragmentManager().beginTransaction().replace(R.id.container,new Fragment2(),"tag_af2").addToBackStack(null)
                .commit();

    }
}
