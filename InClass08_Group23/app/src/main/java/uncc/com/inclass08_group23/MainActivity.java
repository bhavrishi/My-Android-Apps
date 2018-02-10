package uncc.com.inclass08_group23;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecipePuppyFragment.OnFragmentInteractionListener{

    ArrayList listid = new ArrayList();
    String URL=null;

    int i = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getFragmentManager().beginTransaction()
                .add(R.id.container,new RecipePuppyFragment(),"first1")
                .addToBackStack(null)
                .commit();

        //RecipePuppyFragment recipePuppyFragment = getFragmentManager().findFragmentById()



        EditText inputDish =(EditText) findViewById(R.id.editText2);

        if(inputDish.getText().toString()!=null) {
            Button btnSearch = (Button) findViewById(R.id.buttonSearch);
            btnSearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(getFragmentManager().findFragmentByTag("first1").getView().)

                    if (ing1.getText().toString() != null) {
                        if()
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
                    Intent intent =new Intent(MainActivity.this,RecipeActivity.class);
                    startActivity(intent);
                }
            });
        }

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onFragmentAddIngredient() {
        if(getFragmentManager().getBackStackEntryCount()<5) {
       // if(i<5){
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new RecipePuppyFragment(), "first"+i)
                    .addToBackStack(null)
                    .commit();
            i++;
        }
    }

    @Override
    public void onFragmentRemoveIngredient() {
        getFragmentManager().beginTransaction()
                .remove(new RecipePuppyFragment());
        i--;
    }
}
