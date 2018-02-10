package com.hwork.uncc.hw3_groups25;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MyLayoutOperation extends Activity{

   /* static ArrayList<String> result = new ArrayList<>();
   static String [] resultStrings=new String[10000];
    Context ctx=getApplicationContext();static int count=0;
    public void display(final Activity activity, Button btn)
    {final HashMap<Integer, List<ArrayList<String>>> resultmap=new HashMap<>();

final String filepath="*//*/Users//Rishi//Downloads//Resources_HW03-2//textfile.txt";
        btn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                LinearLayout scrollViewlinerLayout = (LinearLayout) activity.findViewById(R.id.linearLayoutForm);
                ArrayList<String> msg = new ArrayList<String>();
                for (int i = 0; i < scrollViewlinerLayout.getChildCount(); i++)
                {
                    LinearLayout innerLayout = (LinearLayout) scrollViewlinerLayout.getChildAt(i);
                    EditText edit = (EditText) innerLayout.findViewById(R.id.editDescricao);
                    msg.add(edit.getText().toString());
                }
                Toast t = Toast.makeText(activity.getApplicationContext(), msg.toString(), Toast.LENGTH_SHORT);
                t.show();
                for(int i=0;i<msg.size();i++)
                {int size;
                  //  size=TextSearchUtil.SearchKeyWordInFile(activity.getApplicationContext(),msg.get(i)).size();
                   result=TextSearchUtil.SearchKeyWordInFile(activity.getApplicationContext(),msg.get(i));
                    for(String j:result)
                    { resultStrings[count]=j;
                        count++;
                   // resultmap.put(size, Arrays.asList(TextSearchUtil.SearchKeyWordInFile(activity.getApplicationContext(),msg.get(i))));
                   }}
                Log.d("demosize", ""+resultmap.size());
            for(int i=0;resultStrings[i]!=null;i++)
               Log.d("demoresult", ""+resultStrings[i]);
                Intent i=new Intent(activity.getApplicationContext(),WordsFoundActivity.class);
                startActivity(i);
            }
        });
    }*/
int id=0;


    public static void add(final Activity activity, ImageButton btn)
    {
        final LinearLayout linearLayoutForm = (LinearLayout) activity.findViewById(R.id.linearLayoutForm);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final LinearLayout newView = (LinearLayout)activity.getLayoutInflater().inflate(R.layout.layout_row, null);
                newView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

                ImageButton btnRemove = (ImageButton) newView.findViewById(R.id.btnRemove);
                btnRemove.setImageResource(R.drawable.remove);
                btnRemove.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        linearLayoutForm.removeView(newView);

                    }
                });
               /* ImageButton btnAdd = (ImageButton) newView.findViewById(R.id.btnAdd);
                btnAdd.setImageResource(R.drawable.add);
                btnAdd.setOnClickListener(new View.OnClickListener() {
p[\4e3
                    @Override
                    public void onClick(View v) {
                        linearLayoutForm.addView(newView);
                    }
                });*/
                linearLayoutForm.addView(newView);
            }
        });
    }

}

