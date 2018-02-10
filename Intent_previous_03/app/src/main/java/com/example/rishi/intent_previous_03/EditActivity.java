package com.example.rishi.intent_previous_03;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import static com.example.rishi.intent_previous_03.R.id.text;

/**
 * Created by Rishi on 10/09/17.
 */

public class EditActivity extends Activity {
    Button btnsave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_edit);
        final TextView nameedit = (TextView) findViewById(R.id.name3);
        final TextView textemail = (TextView) findViewById(R.id.emailfinal);
        final Switch s = (Switch) findViewById(R.id.switch2);
        final SeekBar seek = (SeekBar) findViewById(R.id.seekBar2);
        textemail.setVisibility(View.INVISIBLE);
        s.setVisibility(View.INVISIBLE);
        seek.setVisibility(View.INVISIBLE);

        findViewById(R.id.buttonsave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value=nameedit.getText().toString();
                if(value==null||value.length()==0)
                {
                    setResult(RESULT_CANCELED);
                }
                else
                {
                    Intent i=new Intent();
                    i.putExtra(SecondActivity.VALUE_KEY,value);
                    setResult(RESULT_OK,i);
                }
                finish();
            }
        });
    }
}
