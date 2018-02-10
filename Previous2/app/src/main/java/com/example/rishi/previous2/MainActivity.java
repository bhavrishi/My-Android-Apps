package com.example.rishi.previous2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_relative);
Switch switchB= (Switch) findViewById(R.id.switcham);
        final TextView switchStatus= (TextView) findViewById(R.id.minText);

       final EditText input1= (EditText) findViewById(R.id.hoursText);
       final EditText input2= (EditText) findViewById(R.id.minutes);

        switchB.setChecked(true);switchStatus.setText("AM");
        switchB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    switchStatus.setText("AM");
                }
                else
                    switchStatus.setText("PM");
            }
        });

input1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        String time = input1.getText().toString();
        Log.d("demo", time);
    }
});
        input2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String min = input2.getText().toString();
                Log.d("demo", min);
            }
        });

     //   String time= input1.getText().toString()+":"+input2.getText().toString();
     //   Log.d("demo",time);



        Button clearButton= (Button) findViewById(R.id.clearButton);
        Button cstButton= (Button) findViewById(R.id.cstButton);
        Button pstButton= (Button) findViewById(R.id.pstButton);
        Button mstButton= (Button) findViewById(R.id.mstButton);
        Button estButton= (Button) findViewById(R.id.estButton);

        View.OnClickListener onClick=new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
              Button btn=(Button)view;
                 switch (btn.getId())
                {
                    case R.id.clearButton:input1.setText("");input2.setText("");break;
                    case R.id.estButton:
                }
            }
        };

clearButton.setOnClickListener(onClick);

    }
}
