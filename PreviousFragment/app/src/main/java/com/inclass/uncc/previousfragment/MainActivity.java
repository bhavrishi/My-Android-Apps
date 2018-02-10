package com.inclass.uncc.previousfragment;

import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements loginFragment.OnFragmentInteractionListener
        ,ComposeFragment.OnFragmentInteractionListener{
static DBManager dm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dm = new DBManager(this);
        getFragmentManager().beginTransaction().add(R.id.container,new loginFragment(),"tag_af")
                .commit();


    }

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
        getFragmentManager().beginTransaction().replace(R.id.container,new SignUpFragment(),"tag_af2").addToBackStack(null)
                .commit();

    }

    @Override
    public void gotocomposefrag() {
        getFragmentManager().beginTransaction().replace(R.id.container,new ComposeFragment(),"tag_af3").addToBackStack(null)
                .commit();
    }

    @Override
    public void onFragmentMsg(String s) {
        Message m=new Message(s);
Log.d("demo",m.toString());
        dm.saveNote(new Message(s));
        ArrayList<Message> notes = (ArrayList<Message>) dm.getAllNotes();
        Log.d("demosaved", notes.toString());
        ListFragmentin lstfrag = (ListFragmentin) getFragmentManager().findFragmentByTag("tag_list");
        if(lstfrag==null)
        {
            lstfrag=new ListFragmentin();
            FragmentTransaction tx=getFragmentManager().beginTransaction();
            tx.replace(R.id.container,new ListFragmentin(),"tag_list").addToBackStack(null)
                    .commit();
        }

    }
public static  ArrayList<Message>  returnlist()
{
    ArrayList<Message> notes = (ArrayList<Message>) dm.getAllNotes();
    Log.d("demosavedlist", notes.toString());
    return notes;
}
    @Override
    public void gotolistfrag() {
        ListFragmentin lstfrag = (ListFragmentin) getFragmentManager().findFragmentByTag("tag_list");
        if(lstfrag==null)
        {
            lstfrag=new ListFragmentin();
            FragmentTransaction tx=getFragmentManager().beginTransaction();
            tx.replace(R.id.container,new ListFragmentin(),"tag_list").addToBackStack(null)
                    .commit();
        }
    }

    @Override
    public void onFragmentInteraction(String name, String pwd) {
        Log.d("demo",name+"  "+pwd);
    }


}
