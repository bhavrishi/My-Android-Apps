package com.example.rishi.intent_previous_03;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Rishi on 10/09/17.
 */

public class SecondActivity extends Activity {

    Student stu;
    TextView name;
    final static int REQ_CODE=100;
    final static String VALUE_KEY="value";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==REQ_CODE)
        {
            if(resultCode==RESULT_CANCELED)
            {
                Log.d("demo","No valuew");
            }
            if(resultCode==RESULT_OK)
            {
                String value=data.getExtras().getString(VALUE_KEY);
                name.setText(value);
                Log.d("demo",value);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_second);
         name = findViewById(R.id.valuename);
        TextView email = findViewById(R.id.valueemail);
        TextView account = findViewById(R.id.valueaccount);
        TextView lang = findViewById(R.id.valuelang);
        TextView mood = findViewById(R.id.valuemood);
        ImageView editname = findViewById(R.id.nameedit);
        ImageView editemail = findViewById(R.id.emailedit);
        ImageView editlang = findViewById(R.id.langedit);
        ImageView editacnt = findViewById(R.id.accountedit);
        ImageView editmood = findViewById(R.id.moodedit);
editname.setImageResource(R.drawable.edit);
        
        editemail.setImageResource((R.drawable.two));
        editlang.setImageResource(R.drawable.edit3);
        editacnt.setImageResource((R.drawable.edit5));
        if (getIntent().getExtras() != null)
            stu = (Student) getIntent().getExtras().getSerializable(MainActivity.STU_KEY);
        name.setText(stu.name.toString());
        email.setText(stu.email.toString());
        account.setText(stu.account.toString());
        lang.setText(stu.lang.toString());
        mood.setText(stu.mood.toString());
        Log.d("demo", stu.toString());
        // setContentView(mTextView);


        editname.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             Intent i=new Intent(SecondActivity.this,EditActivity.class) ;
             startActivityForResult(i,REQ_CODE);
         }
     });
    }

}
