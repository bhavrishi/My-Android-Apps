package com.example.hw3_group28;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hw3_group28.R;

/**
 * Created by prajvalb on 9/22/17.
 */

public class listItemUI extends LinearLayout {
    //LinearLayout linearLayout;
    EditText editText;
    ImageButton imageButton;
    public listItemUI(Context context) {
        super(context);
        inflateXML(context);
    }

    private void inflateXML(Context context){
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.add_button, this, true);
        //this.linearLayout = (LinearLayout) findViewById(R.id.linearlayoutOther);
        this.editText = (EditText) findViewById(R.id.editTextMainOther);
        this.imageButton = (ImageButton) findViewById(R.id.imageButtonMainOther);
        this.imageButton.setImageResource(R.drawable.add);
        //this.imageButton.setTag("Add");

    }
}
