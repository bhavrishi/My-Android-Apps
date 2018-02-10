package com.inclass.uncc.inclass06_801019706;
/*#Assignment: In Class Assignment $
     #   File Name : Table1_InClass06.zip
        #Full Name:  Bhavya Gedi*/
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

/**
 * Created by Rishi on 02/10/17.
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
            View view = layoutInflater.inflate(R.layout.layout_add_button, this, true);
            //this.linearLayout = (LinearLayout) findViewById(R.id.linearlayoutOther);
            this.editText = (EditText) findViewById(R.id.editTextMainOther);
            this.imageButton = (ImageButton) findViewById(R.id.imageButtonMainOther);
            this.imageButton.setImageResource(R.drawable.add);
            //this.imageButton.setTag("Add");

        }
    }


