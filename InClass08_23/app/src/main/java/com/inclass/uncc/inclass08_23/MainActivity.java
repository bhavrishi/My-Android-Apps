package com.inclass.uncc.inclass08_23;

/*Assignment :InClass08_Group23.zip.
        Group : InClass08 23
        Full names of all of the teammates : Rosy Azad, Bhavya Gedi */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements RecipePuppyFragment.OnPuppyFragmentInteractionListener, RecipesFragment.OnFragmentInteractionListener {
    static String URL;
    ArrayList<String> temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getFragmentManager().beginTransaction().add(R.id.container, new RecipePuppyFragment(), "tag_af")
                .commit();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else
            super.onBackPressed();
    }

    @Override
    public void onFragmentInteraction(String text1, String text2, ArrayList<String> listid) {
        getFragmentManager().beginTransaction().replace(R.id.container, new RecipesFragment(), "tag_af2").addToBackStack(null)
                .commit();
        if (listid!=null) {
            Log.d("demolist", listid.toString());
            temp = new ArrayList<String>();
            for (int i = 0; i < listid.size(); i++) {
                if (listid.get(i)!=null)
                    temp.add(listid.get(i));
            }
            Set<String> hs = new HashSet<>();
            hs.addAll(temp);
            temp.clear();
            temp.addAll(hs);
            Log.d("demolistte", temp.toString());
        }
       if(text1.length()>0&&text2.length()>0) {
           URL = "http://www.recipepuppy.com/api/?i=" + text1 + "&q=" + text2;
       }

        if (listid==null) {
            URL = "http://www.recipepuppy.com/api/?i=" + text1 + "&q=" + text2;
            Log.d("demo", URL);
        }
else        if (temp.size() == 1) {
            URL = "http://www.recipepuppy.com/api/?i=" + text1 + "," + temp.get(0) + "&q=" + text2;
            Log.d("demo", URL);
        }
      else  if (temp.size() == 2) {
            URL = "http://www.recipepuppy.com/api/?i=" + text1 + "," + temp.get(0) + "," + temp.get(1) + "&q=" + text2;
            Log.d("demo", URL);
        }
       else if (temp.size() == 3) {
            URL = "http://www.recipepuppy.com/api/?i=" + text1 + "," + temp.get(0) + "," + temp.get(1) + "," + temp.get(2) + "&q=" + text2;
            Log.d("demo", URL);
        }
      /*  if (listid.size()==4) {
            URL = "http://www.recipepuppy.com/api/?i="+text1+"&q="+listid.get(5);
            Log.d("demo", URL);
        }*/
      else  if (temp.size() == 4) {
            URL = "http://www.recipepuppy.com/api/?i=" + text1 + "," + temp.get(0) + "," + temp.get(1) + "," + temp.get(2) + "," + temp.get(3) + "&q=" + text2;
            Log.d("demo", URL);
        }


    }

    @Override
    public void gotomainfrag() {
        Toast.makeText(MainActivity.this, "No Recepies Found", Toast.LENGTH_SHORT).show();
        getFragmentManager().beginTransaction().replace(R.id.container, new RecipePuppyFragment(), "tag_af2").addToBackStack(null)
                .commit();
    }

    @Override
    public void gotomainfinishfrag() {
        getFragmentManager().beginTransaction().replace(R.id.container, new RecipePuppyFragment(), "tag_af2").addToBackStack(null)
                .commit();
    }


    public static String returndata() {
        Log.d("url", URL);
        return URL;
    }
}
