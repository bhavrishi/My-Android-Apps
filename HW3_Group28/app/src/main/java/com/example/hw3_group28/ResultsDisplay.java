package com.example.hw3_group28;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class ResultsDisplay extends AppCompatActivity {
    private LinearLayout linearLayout;
    private Button finishButton;
    private int colorcount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_display);
        Intent resultIntent = getIntent();


        ArrayList<ArrayList<String>>resultDisplay = (ArrayList<ArrayList<String>>) resultIntent.getExtras().getSerializable(MainActivity.SEARCH_RESULT);
        //ArrayList<String> keyString = (ArrayList<String>)resultIntent.getExtras().getSerializable(MainActivity.SEARCH_KEY);
        String[] keyString = resultIntent.getExtras().getStringArray(MainActivity.SEARCH_KEY);
        linearLayout = (LinearLayout)findViewById(R.id.linearLayoutResults);
        finishButton = (Button) findViewById(R.id.buttonfinish);

        Log.d("prb_log", "String lenght " + resultDisplay.size());
        for (int i = 0; i < resultDisplay.size(); i++){
            for (int j = 0; j < resultDisplay.get(i).size(); j++) {
                colorcount = colorcount + 1;
                //Log.d("prb_log", "String value " + resultDisplay.get(i).get(j));
                resultsItemUI resultsItem = new resultsItemUI(ResultsDisplay.this);
                linearLayout.addView(resultsItem);
                String beforeDisplay = resultDisplay.get(i).get(j);
                //String coloredString  = matchString(keyString[i], beforeDisplay, colorcount);
                String coloredString  = matchString(keyString, beforeDisplay, colorcount);
                //String coloredString;
                //if (colorcount % 3 == 0)
                //    coloredString = beforeDisplay.replace(keyString[i],"<font color='#FF0000'>" +keyString[i] +"</font>");
                //else if (colorcount % 3 == 1)
                //    coloredString = beforeDisplay.replace(keyString[i],"<font color='#00FF00'>" +keyString[i] +"</font>");
                //else
                //    coloredString = beforeDisplay.replace(keyString[i],"<font color='#0000FF'>" +keyString[i] +"</font>");
                resultsItem.textView.setText(Html.fromHtml("..." + coloredString + "..."));
            }
            //((TextView)findViewById(R.id.textViewdisplayResults)).setText();
        }


        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

    //public String matchString(String key, String string, int colorcount){
    public String matchString(String[] keyString, String string, int colorcount) {

        for (int k = 0; k < keyString.length; k++) {
            ArrayList<String> tmp = TextSearchUtil.PrintCombinations(keyString[k]);
            for (int i = 0; i < tmp.size(); i++) {
                if (string.indexOf(tmp.get(i)) > 0) {
                    if (colorcount % 3 == 0)
                        return string.replace(tmp.get(i), "<font color='#FF0000'>" + tmp.get(i) + "</font>");
                    else if (colorcount % 3 == 1)
                        return string.replace(tmp.get(i), "<font color='#00FF00'>" + tmp.get(i) + "</font>");
                    else
                        return string.replace(tmp.get(i), "<font color='#0000FF'>" + tmp.get(i) + "</font>");
                }
            }

        }
        return null;
    }


    public ArrayList<String> findallComb(String[] keyString){
        ArrayList<String> tmp = new ArrayList<String>();
        ArrayList<String> tmpStr = new ArrayList<String>();

        for (int k = 0; k < keyString.length; k++) {
            tmp = TextSearchUtil.PrintCombinations(keyString[k]);
            for (int j = 0; j < tmp.size(); j++){
                tmpStr.add(tmp.get(j));
            }
        }
        //Log.d("prb_log",tmpStr);
        return tmpStr;

    }
}
