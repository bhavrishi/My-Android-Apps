package com.hw.uncc.group25_hw06;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements RegisterFragment.OnFragmentInteractionListener, AddInstructorFragment.OnFragmentInteractionListener, CourseManagerFragment.OnFragmentInteractionListener, LoginFragment.OnFragmentInteractionListener, CreateCourseFragment.OnFragmentInteractionListener {
    DBManager dm;
    static String usname;
    UserDBManager dmuser;
    InstructorDBManager dminst;
    HashMap<String, String> globmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dm = new DBManager(this);
        dmuser = new UserDBManager(this);
        dminst = new InstructorDBManager(this);
        globmap = new HashMap<String, String>();

        // dm.saveNote(new Course(map,"Note 1","This is Note 1","This is Note 1","This is Note 1","This is Note 1"));


        //  dminst.saveNote(new Instructor("Ins","Ins","Ins","Ins"));
        List<Instructor> ins = dminst.getAllNotes(MainActivity.returnuname());
        // Log.d("demo",ins.toString());
        getFragmentManager().beginTransaction().add(R.id.container, new LoginFragment(), "tag_af")
                .commit();
    }

    @Override
    public void onFragmentInteraction(String s, String s1) {
        Log.d("demo", s + "  " + s1);
        String key = null, value = null;
        boolean flag = false;
        HashMap<String, String> map = new HashMap<String, String>();
        map.put(s, s1);
        usname=s;
        globmap = map;
        // dm.saveNote(new Course(map,null,null,null,null,null,null));
        ArrayList<User> notes = (ArrayList<User>) dmuser.getAllNotes();

        Log.d("demo", "" + notes.toString());
        for (int i = 0; i < notes.size(); i++) {
            HashMap<String, String> tempmap = notes.get(i).getUpwd();
            for (Map.Entry<String, String> entry : tempmap.entrySet()) {
                key = entry.getKey();
                if (s.equals(key)) {
                    flag = true;
                }
                //value= entry.getValue();
                // do something with key and/or tab
            }

        }

        if (flag) {
            flag = true;
            Toast.makeText(MainActivity.this, "Welcome Back "+s1, Toast.LENGTH_SHORT).show();
            getFragmentManager().beginTransaction().replace(R.id.container, new CourseManagerFragment(), "tag_af3").addToBackStack(null)
                    .commit();
        } else {
            Toast.makeText(MainActivity.this, "Please Sign Up", Toast.LENGTH_SHORT).show();
            getFragmentManager().beginTransaction().replace(R.id.container, new RegisterFragment(), "tag_af5").addToBackStack(null)
                    .commit();
        }


    }

    @Override
    public void gotoAddInsfrag() {
        getFragmentManager().beginTransaction().replace(R.id.container, new AddInstructorFragment(), "tag_af4").addToBackStack(null)
                .commit();
    }

    @Override
    public void gotoLogoutListener() {
        usname=null;
        getFragmentManager().beginTransaction().replace(R.id.container, new LoginFragment(), "tag_af4").addToBackStack(null)
                .commit();
    }

    @Override
    public void getListInstructors() {
        getFragmentManager().beginTransaction().replace(R.id.container, new ListInstFragment(), "tag_af4").addToBackStack(null)
                .commit();
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else
            super.onBackPressed();
    }
public  static String returnuname()
{
    return usname;
}
    @Override
    public void onFragmentInteraction(String coursename, String selectedDay, String timeseq, String credit, String semester, String fname) {
        Log.d("demoday", selectedDay);
        Log.d("demodcourse", coursename);
        Log.d("demodtime", timeseq);
        Log.d("demodcredit", credit);
        Log.d("demodsem", semester);
        Log.d("demodinsname", fname);
        dm.saveNote(new Course(globmap, coursename, selectedDay, timeseq, credit, semester, fname));
        ArrayList<Course> notes = (ArrayList<Course>) dm.getAllNotes(MainActivity.returnuname());

        Log.d("demodnotes", "" + notes.toString());

    }

    @Override
    public void gotoAddCourseFragment() {
        getFragmentManager().beginTransaction().replace(R.id.container, new CreateCourseFragment(), "tag_af2").addToBackStack(null)
                .commit();
    }

    @Override
    public void gotoSaveInstructorProfile(String fname, String lname, String email, String website, String profileString, String returnuname) {
        Log.d("demodfname", fname);
        Log.d("demodlname", lname);
        Log.d("demodemail", email);
        Log.d("demodwebsite", website);
        Log.d("demodimg", profileString);
        dminst.saveNote(new Instructor(profileString, fname, lname, email, website,returnuname));
        List<Instructor> ins = dminst.getAllNotes(MainActivity.returnuname());

    }

    @Override
    public void getRegister(String profileString, String fname, String lname, String uname, String pwd) {
        Log.d("demodfname", fname);
        Log.d("demodlname", lname);
        Log.d("demodemail", uname);
        Log.d("demodwebsite", pwd);
        Log.d("demodimg", profileString);
        HashMap<String, String> map = new HashMap<String, String>();
        map.put(uname, pwd);
        globmap = map;
        usname=uname;
        dmuser.saveNote(new User(profileString, fname, lname, map));
        ArrayList<User> notes = (ArrayList<User>) dmuser.getAllNotes();

        Log.d("demouser", "" + notes.toString());
        getFragmentManager().beginTransaction().replace(R.id.container, new CourseManagerFragment(), "tag_af3").addToBackStack(null)
                .commit();
    }

    @Override
    public void gotoCourseManagerfrag() {
        getFragmentManager().beginTransaction().replace(R.id.container, new CourseManagerFragment(), "tag_af3").addToBackStack(null)
                .commit();
    }
}
