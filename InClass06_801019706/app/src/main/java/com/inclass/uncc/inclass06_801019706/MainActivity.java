package com.inclass.uncc.inclass06_801019706;
/*#Assignment: In Class Assignment $
     #   File Name : Table1_InClass06.zip
        #Full Name:  Bhavya Gedi*/
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    private Button buttonSearch;
    private ImageButton mainAdd;
    ArrayList<Receipe> receipes;
    private EditText editText,inputDish;
    private int count;
    String URL=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonSearch = (Button) findViewById(R.id.buttonSearch);
        mainAdd = (ImageButton) findViewById(R.id.imageButtonMain1);
        editText = (EditText) findViewById(R.id.textViewMain1);
        inputDish = (EditText) findViewById(R.id.inputDish);

receipes=new ArrayList<Receipe>();

        mainAdd.setTag("Add");
        mainAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String check = (String) mainAdd.getTag();
                count++;
                Log.d("prb_log","Check string " + check);
                if (check.equals("Add")) {
                    LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearlayoutMain);
                    listItemUI itemUI = new listItemUI(MainActivity.this);
                    linearLayout.addView(itemUI);
                    mainAdd.setImageResource(R.drawable.remove);
                    mainAdd.setTag("Sub");
                }else {
                    LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearlayoutMain);
                    linearLayout.removeView(findViewById(R.id.linearlayoutMain1));
                }

            }
        });

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int validCheck = 0;
                ArrayList<EditText> editTexts = new ArrayList<EditText>();
                int myCount = 0;
                LinearLayout mainLinearLayout = (LinearLayout)findViewById(R.id.linearlayoutMain);
                int childcount = mainLinearLayout.getChildCount();
                String[] newStr = new String[childcount];
                for (int i = 0; i < childcount; i++){
                    if (mainLinearLayout.getChildAt(i) == (LinearLayout)findViewById(R.id.linearlayoutMain1)){
                        String tmp1 = ((EditText) findViewById(R.id.textViewMain1)).getText().toString();
                        if (tmp1.isEmpty() || tmp1.equals("")) {
                            Toast.makeText(getBaseContext(),"Empty String !!", Toast.LENGTH_LONG).show();
                            validCheck = 1;

                        }else {
                            newStr[myCount++] = ((EditText) findViewById(R.id.textViewMain1)).getText().toString();
                        }

                    } else {

                        LinearLayout subLinearLayout = (LinearLayout) mainLinearLayout.getChildAt(i);
                        LinearLayout myLinearLayout = (LinearLayout) subLinearLayout.getChildAt(0);
                        int myChildCount = myLinearLayout.getChildCount();

                        if (myLinearLayout.getChildAt(0) instanceof EditText){
                            String tmp2 = ((EditText) myLinearLayout.getChildAt(0)).getText().toString();
                            if (tmp2.isEmpty() || tmp2.equals("")){
                                Toast.makeText(getBaseContext(),"Empty String !!", Toast.LENGTH_LONG).show();
                                validCheck = 1;
                            }else {
                                newStr[myCount++] = ((EditText) myLinearLayout.getChildAt(0)).getText().toString();
                            }

                        } else if (myLinearLayout.getChildAt(1) instanceof EditText) {
                            newStr[myCount++] = ((EditText) myLinearLayout.getChildAt(1)).getText().toString();
                        }
                    }

                }
                Log.d("demo", String.valueOf(newStr.length));
                String inputDishtext=inputDish.getText().toString();
if(newStr.length==1)
{
    URL = "http://www.recipepuppy.com/api/?format=xml&i="+newStr[0]+"&q="+inputDishtext;
    Log.d("demo", URL);
}
                if(newStr.length==2)
                {
                    URL = "http://www.recipepuppy.com/api/?format=xml&i="+newStr[0]+","+newStr[1]+"&q="+inputDishtext;
                    Log.d("demo", URL);
                }
                if(newStr.length==3)
                {
                    URL = "http://www.recipepuppy.com/api/?format=xml&i="+newStr[0]+","+newStr[1]+","+newStr[2]+"&q="+inputDishtext;
                    Log.d("demo", URL);
                }
                if(newStr.length==4)
                {
                    URL = "http://www.recipepuppy.com/api/?format=xml&i="+newStr[0]+","+newStr[1]+","+newStr[2]+","+newStr[3]+"&q="+inputDishtext;
                    Log.d("demo", URL);
                }
                if(newStr.length==5)
                {
                    URL = "http://www.recipepuppy.com/api/?format=xml&i="+newStr[0]+","+newStr[1]+","+newStr[2]+","+newStr[3]+","+newStr[4]+"&q="+inputDishtext;
                    Log.d("demo", URL);
                }

                if(URL!=null)
                {
                    try {
                        receipes=   new AsyncTaskReceipe(MainActivity.this).execute(URL).get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
               if(receipes!=null){ Intent i=new Intent(MainActivity.this,ResultActivity.class);
                   i.putExtra("ALIST",receipes);
                   startActivity(i);}
            }
        });
    }

    public void onClickButton(View view){
        String check1 = (String) view.findViewById(R.id.imageButtonMainOther).getTag();
        Log.d("prb_log","Check string 1" + check1);
        int id = view.getId();
        Log.d("prb_log", "Check id: " + id);

        if (((LinearLayout)findViewById(R.id.linearlayoutMain)).getChildCount() <= 20) {


            if (check1.equals("Add")) {
                LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearlayoutMain);
                listItemUI itemUI = new listItemUI(MainActivity.this);
                linearLayout.addView(itemUI);
                ((ImageButton) view.findViewById(R.id.imageButtonMainOther)).setImageResource(R.drawable.remove);
                ((ImageButton) view.findViewById(R.id.imageButtonMainOther)).setTag("Sub");
            } else {
                LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearlayoutMain);
                ImageButton ib = ((ImageButton) view.findViewById(R.id.imageButtonMainOther));
                LinearLayout removeparent = (LinearLayout) ib.getParent();
                linearLayout.removeView(((LinearLayout) removeparent.getParent()));

            }
        }
    }
}
