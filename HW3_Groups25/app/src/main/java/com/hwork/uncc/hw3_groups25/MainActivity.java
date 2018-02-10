package com.hwork.uncc.hw3_groups25;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import static android.R.attr.fragment;
import static com.hwork.uncc.hw3_groups25.WordsFoundActivity.result;

public class MainActivity extends Activity {
    Button btnDisplay;
    ImageButton btnAdd;
    ArrayList<String> result = new ArrayList<>();
    static String [] resultStrings=new String[10000];
    static int count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_relative);
        btnAdd = (ImageButton) findViewById(R.id.btnAdd);
        btnAdd.setImageResource(R.drawable.add);
        btnDisplay = (Button) findViewById(R.id.btnDisplay);
new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if((v.getResources().getResourceEntryName(v.getId())).equals("btnAdd"))
        {
            Log.d("demoview", "" + getResources().getResourceEntryName(v.getId()));
        }
    }
};
        ScrollView scrollView= (ScrollView) findViewById(R.id.scrollView1);

        scrollView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("demosview", "" + getResources().getResourceEntryName(v.getId()));
            }
        });




        MyLayoutOperation. add(this, btnAdd);

      //  MyLayoutOperation.display(MainActivity.this, btnDisplay);


    final HashMap<Integer, List<ArrayList<String>>> resultmap=new HashMap<>();


        btnDisplay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                LinearLayout scrollViewlinerLayout = (LinearLayout) findViewById(R.id.linearLayoutForm);
                ArrayList<String> msg = new ArrayList<String>();
                for (int i = 0; i < scrollViewlinerLayout.getChildCount(); i++)
                {
                    LinearLayout innerLayout = (LinearLayout) scrollViewlinerLayout.getChildAt(i);
                    EditText edit = (EditText) innerLayout.findViewById(R.id.editDescricao);
                    msg.add(edit.getText().toString());
                }
                Toast t = Toast.makeText(MainActivity.this, msg.toString(), Toast.LENGTH_SHORT);
                t.show();
                for(int i=0;i<msg.size();i++)
                {int size;
                  //  size=TextSearchUtil.SearchKeyWordInFile(activity.getApplicationContext(),msg.get(i)).size();
                   result=TextSearchUtil.SearchKeyWordInFile(getApplicationContext(),msg.get(i));
                    for(String j:result)
                    { resultStrings[count]=j;
                        count++;
                   // resultmap.put(size, Arrays.asList(TextSearchUtil.SearchKeyWordInFile(activity.getApplicationContext(),msg.get(i))));
                   }}
                Log.d("demosize", ""+resultmap.size());
            for(int i=0;resultStrings[i]!=null;i++)
               Log.d("demoresult", ""+resultStrings[i]);
                Intent i=new Intent(MainActivity.this,WordsFoundActivity.class);
                startActivity(i);
            }
        });


       /* btnDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(MainActivity.this,WordsFoundActivity.class);
                startActivity(i);
            }
        });*/
    }

    public static String[] retrieveWordFound()
    {return resultStrings;

    }


}    /*Button b1= (Button) findViewById(R.id.button);
       final TextView t= (TextView) findViewById(R.id.textView);
        b1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                String text="";
                try {
                    InputStream i=getAssets().open("textfile.txt");
                    int size=i.available();
                    byte[] b=new byte[size];
                    i.read(b);
                    i.close();
                    text=new String(b);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                t.setText(text);
            }
        });*/



