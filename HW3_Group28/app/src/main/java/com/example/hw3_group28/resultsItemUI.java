package com.example.hw3_group28;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by prajvalb on 9/24/17.
 */

public class resultsItemUI extends LinearLayout {
    LinearLayout linearLayout;
    TextView textView;

    public resultsItemUI(Context context) {
        super(context);
        inflateResults(context);
    }

    private void inflateResults(Context context){
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.displayfinalresults, this);
        this.textView = (TextView) findViewById(R.id.textViewdisplayResults);
        this.linearLayout = (LinearLayout) findViewById(R.id.linearLayoutResults);
    }
}
