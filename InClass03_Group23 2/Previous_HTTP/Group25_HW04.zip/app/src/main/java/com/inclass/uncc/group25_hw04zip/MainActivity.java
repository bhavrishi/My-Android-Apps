package com.inclass.uncc.group25_hw04zip;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
     ProgressBar prog;
    ArrayList<TriviaBean> response =new ArrayList<TriviaBean>();
    AsyncTaskJson async;
    Context context;
    boolean clicked=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_home);
        final Button btnStart= (Button) findViewById(R.id.btnStart);
     /* prog= (ProgressBar) findViewById(R.id.progressBar);


prog.setVisibility(View.INVISIBLE);*/
        final ImageView imgtrivia= (ImageView) findViewById(R.id.imgTrivia);
        imgtrivia.setVisibility(View.INVISIBLE);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnStart.setEnabled(false);
                clicked=true;//context=this.getApplicationContext();
//imgtrivia.setVisibility(View.INVISIBLE);
               /* prog.setVisibility(View.VISIBLE);*/

                try {
                    response=  new AsyncTaskJson(MainActivity.this).execute("http://dev.theappsdr.com/apis/trivia_json/index.php").get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
Log.d("demores", ""+response.toString());

                   /* if(clicked==true)
                    {   Log.d("demor", response.toString());
                        btnStart.setEnabled(true);
                        prog.setVisibility(View.INVISIBLE);
                        imgtrivia.setVisibility(View.VISIBLE);

                    }*/

                if((btnStart.isEnabled())==false){
                    btnStart.setEnabled(true);
                    imgtrivia.setVisibility(View.VISIBLE);
                    btnStart.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Log.d("demo","Hii");

                            Intent i=new Intent(MainActivity.this,TriviaActivity.class);
                            i.putExtra("ALIST",response);
                            startActivity(i);
                        }
                    });
                }
            }
        });


    }





}
