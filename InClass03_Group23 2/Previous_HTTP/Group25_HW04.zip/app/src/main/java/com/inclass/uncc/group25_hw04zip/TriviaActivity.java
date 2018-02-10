package com.inclass.uncc.group25_hw04zip;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.inclass.uncc.group25_hw04zip.TriviaUtil.map;

public class TriviaActivity extends AppCompatActivity {
    ArrayList<TriviaBean> arr;
    boolean[] checked = {false};
    int resultcount = 0;
    int idx = -1;
    int checkedanswers[] = new int[16];
    RadioButton temprdbtn[];
    LinearLayout mLinearLayout;
    RadioButton returntemprdbtn[];
    Button btnnext,btnlast;
    Bitmap bm;
    int i = 1;
    int j = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_question);
        final TextView qnum = (TextView) findViewById(R.id.textQunum);
        final TextView ques = (TextView) findViewById(R.id.textquestion);
        final ImageView qimg = (ImageView) findViewById(R.id.imgque);
        btnnext = (Button) findViewById(R.id.btnNext);
        btnlast = (Button) findViewById(R.id.btnnt);
        btnlast.setVisibility(View.INVISIBLE);
        Bundle b = getIntent().getExtras();

        if (b != null) {
            arr = (ArrayList<TriviaBean>) b.getSerializable("ALIST");
            Log.d("demointent", arr.toString());
        }

        qnum.setText("Q" + 1);
        try {
            bm = new AsyncImage(TriviaActivity.this).execute(arr.get(0).getImage()).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        qimg.setImageBitmap(bm);
        ques.setText(arr.get(0).getQuestion());
        Log.d("demosize", "" + arr.get(0).getChoices().length());
        temprdbtn = new RadioButton[arr.get(0).getChoices().length()];
        // returntemprdbtn=new RadioButton[arr.get(0).getChoices().length()];
        addRadioButtons(0);
      for(int d=0;d<arr.size()-1;d++){
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLinearLayout.removeAllViews();
                //   RadioGroup rg = (RadioGroup) findViewById(R.id.radiogrp);
                // Log.d("demoradio",   ""+rg.getChildAt(0));
                // LinkedHashMap<Integer, List<String>> inmap = TriviaUtil.retrieveMap();

                qnum.setText("Q" + i);

                try {
                    if (arr.get(i).getImage() == "")

                        bm = new AsyncImage(TriviaActivity.this).execute("https://chartselect.humminbird.com/no_photo.png").get();
                    else
                        bm = new AsyncImage(TriviaActivity.this).execute(arr.get(i).getImage()).get();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                qimg.setImageBitmap(bm);
                ques.setText(arr.get(i).getQuestion());
                Log.d("demosize", "" + arr.get(i).getChoices().length());
                addRadioButtons(i);
if(i==15)
{
    btnnext.setVisibility(View.INVISIBLE);
    btnlast.setVisibility(View.VISIBLE);
}
                i++;
                Log.d("demoi", "" + i + "result" + resultcount);
            }
        });}

     btnlast.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             if (i >15) {
                 for (int m = 1; m < 4; m++) {       //Log.d("resultar", "" + checkedanswers[m]);
                     if (checkedanswers[m] == arr.get(j).getAnswer()) {

                         resultcount++;j++;
                     }

                 }
                 Intent i=new Intent(TriviaActivity.this,ResultsActivity.class);
                 i.putExtra("COUNT",resultcount);
                 startActivity(i);
             }
         }
     });
        }



    public void addRadioButtons(final int number) {

        final LinkedHashMap<Integer, List<String>> map = TriviaUtil.retrieveMap();

        for (int row = 0; row < 1; row++) {
            mLinearLayout = (LinearLayout) findViewById(R.id.llout);
            mLinearLayout.setOrientation(LinearLayout.VERTICAL);
            final RadioGroup rg = new RadioGroup(this);
            // final RadioGroup rg = (RadioGroup) findViewById(R.id.radiogrp);
               /* */


            rg.setOrientation(RadioGroup.VERTICAL);

//i=0;
            for (String entry : map.get(map.keySet().toArray()[number])) {

                RadioButton rdbtn = new RadioButton(this);
                List<String> l = new ArrayList<String>();
//temprdbtn[i]=rdbtn;i++;
                rdbtn.setText("" + entry);
                /*if (rdbtn.getParent() != null)
            ui        ((ViewGroup) rdbtn.getParent()).removeView(rdbtn);*/
                rg.addView(rdbtn);
                // <- fix


                Log.d("demomapin", entry);

            }


            mLinearLayout.addView(rg);


            // map.remove(number);

            //((ViewGroup) findViewById(R.id.radiogrp)).addView(rg);
/*if(!rg.hasFocus())
{
    for (String entry : map.get(map.keySet().toArray()[0])) {


        rg.removeView(temprdbtn[j]);j++;
        // <- fix


        Log.d("demomapin", entry);

    }
}*/
            rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                    checked[0] = true;
                    RadioButton btn = (RadioButton) findViewById(i);
                    idx = rg.indexOfChild(btn);
                    Log.d("Youradio button id", "" + idx + checked[0]);

                }


            });
            if (checked[0]) {
                checkedanswers[number] = idx + 1;
                Log.d("radiocheckif", "" + idx + checked[0]);
            } else {
                checkedanswers[number] = -1;
                Log.d("radiocheckelse", "" + idx + checked[0]);
            }
            checked[0] = false;
            // i=0;

            //  ((ViewGroup) findViewById(R.id.radiogrp)).addView(rg);
            /*int count = rg.getChildCount();
            ArrayList<RadioButton> listOfRadioButtons = new ArrayList<RadioButton>();
            for (int i=0;i<count;i++) {
                View o = rg.getChildAt(i);
                if (o instanceof RadioButton) {
                    listOfRadioButtons.add((RadioButton)o);
                }
            }
            Log.d("demo","you have "+listOfRadioButtons.size()+" radio buttons");*/
        }

    }
}
