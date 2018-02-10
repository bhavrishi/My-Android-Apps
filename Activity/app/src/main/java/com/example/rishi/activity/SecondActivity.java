package com.example.rishi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {
    EditText text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
/*findViewById(R.id.done).setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        finish();
    }
});*/
/*
if(getIntent().getExtras()!=null)
{
    String name=getIntent().getExtras().getString(MainActivity.NAME_KEY);
    Double age= getIntent().getExtras().getDouble(MainActivity.AGE_KEY,22);
User user= (User) getIntent().getExtras().getSerializable(MainActivity.USER_KEY);
   // Toast.makeText(this,name+","+age,Toast.LENGTH_LONG).show();
    Toast.makeText(this,user.toString(),Toast.LENGTH_LONG).show();
}
*/



        text= (EditText) findViewById(R.id.input);
findViewById(R.id.done).setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        String value=text.getText().toString();
        if(value==null||value.length()==0)
        {
            setResult(RESULT_CANCELED);
        }
        else
        {
            Intent i=new Intent();
            i.putExtra(MainActivity.VALUE_KEY,value);
            setResult(RESULT_OK,i);
        }
        finish();
    }
});
    }

}
