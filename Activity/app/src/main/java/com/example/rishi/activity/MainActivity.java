package com.example.rishi.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.preference.DialogPreference;
import android.support.annotation.RequiresPermission;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends Activity {
    final static String NAME_KEY="NAME";
    final static String AGE_KEY="AGE";
    final static String USER_KEY="USER";
    final static int REQ_CODE=100;
    final static String VALUE_KEY="";
    ArrayList<Integer> select=new ArrayList();


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
               Log.d("demo",value);
           }
       }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_relative);
        Log.d("demo", "Inside Oncreate Method");

//        RelativeLayout relLayout = new RelativeLayout(this);
//        relLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
//        setContentView(relLayout);
//
//        TextView hello = new TextView(this);
//        hello.setText(R.string.helloDear);
//        hello.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
//        hello.setId(100);
//        relLayout.addView(hello);
//
//
//        Button button = new Button(this);
//        button.setText(R.string.button);
//        RelativeLayout.LayoutParams buttonLayoutParams = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
//        buttonLayoutParams.addRule(RelativeLayout.BELOW, hello.getId());
//        button.setLayoutParams(buttonLayoutParams);
//        relLayout.addView(button);


     /*   findViewById(R.id.mainButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent i=new Intent(MainActivity.this,SecondActivity.class);
i.putExtra(NAME_KEY,"Rishitha");
User user=new User("Bhavya",20.3);
i.putExtra(USER_KEY,user);
             //   i.putExtra(AGE_KEY,(double)22.3);
             //  Intent i=new Intent("com.example.rishi.activity.intent.action.VIEW");
               // i.addCategory(Intent.CATEGORY_DEFAULT);
                startActivity(i);
            }
        });

*/






/*
    @Override
    protected void onStart() {
        super.onStart();
        Log.d("demo","Ïnside Onstart method");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("demo","Ïnside OnResume Method");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("demo","Ïnside OnPause Method");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("demo","Ïnside OnStop Method");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("demo","Ïnside OnDestroy Method");*/

/*
        AlertDialog.Builder builder=new AlertDialog.Builder(this);

final CharSequence items[]={"ed","reen","blue","ÿellow"};
builder.setTitle("Delete content")
        .setMessage("Äre you sure wanna delete")
        .setCancelable(false)
      .setSingleChoiceItems(items, 1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d("demo",items[i].toString());
            }
        })

       builder.setMultiChoiceItems(items, null, new DialogInterface.OnMultiChoiceClickListener() {
           @Override
           public void onClick(DialogInterface dialogInterface, int i, boolean b) {
               if(b)
               {
select.add(Integer.valueOf(i));
                   Log.d("demo",""+items[i]);
               }
               else
               {
                   select.remove(Integer.valueOf(i));
                   Log.d("demo",""+items[i]);
               }
           }
       })

.setTitle("electt color")

        .setPositiveButton("Ÿes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {

                for(Integer i:select)
                {
Log.d("demo",items[i.intValue()]+");Log.d("demo","clicked yes");
                }

            }
        })/*


.setNegativeButton("No",new DialogInterface.OnClickListener() {
    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        Log.d("demo","licked No");
    }
})


        ;
        final AlertDialog simpleBuild=builder.create();
        findViewById(R.id.mainButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                simpleBuild.show();
            }
        });*/



findViewById(R.id.mainButton).setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
       Intent i=new Intent(MainActivity.this,SecondActivity.class) ;
        startActivityForResult(i,REQ_CODE);
    }
});



    }
}
