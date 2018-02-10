
package com.hwork.uncc.hw3_groups25;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

public class WordsFoundActivity extends AppCompatActivity {
    static String[] result = new String[10000];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_display);
       // LinearLayout ll=(LinearLayout) findViewById(R.id.ll);
//
        ScrollView sv = new ScrollView(this);
        sv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        LinearLayout ll = new LinearLayout(this);
        ll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        ll.setOrientation(1);
        sv.addView(ll);
        result=MainActivity.retrieveWordFound();
        for(int i = 0; result[i]!=null; i++)
        {

/* Button b = new Button(this);
            b.setText("Button "+i);*/

            Log.d("demoresult", ""+result[i]);
            TextView text=new TextView(this);
            text.setText(result[i]);
            ll.addView(text);
        }

       // ll.addView(sv);
      this.setContentView(sv);


/* If you want to set entire layout as dynamically, then remove below lines in program :
* setContentView(R.layout.activity_main);
* RelativeLayout rl=(RelativeLayout)findViewById(R.id.rl);
* rl.addView(sv);
*
* And Add below line :
* this.setContentView(sv);

*/

    }
    }

