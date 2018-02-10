package com.inclass.uncc.group25_hw04zip;

/*Assignment : group25_hw04.zip
        FileName : group25_hw04zip
        Group Member names : Rosy Azad , Bhavya Gedi*/

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.renderscript.Sampler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements AsyncResponse {
     ProgressBar prog;
    ImageView imgtrivia;
    Button btnStart;
    Button btnExit;
    TextView loadintri;

    public ArrayList<TriviaBean> getResponse() {
        return response;
    }

    ArrayList<TriviaBean> response =new ArrayList<TriviaBean>();
    AsyncTaskJson async = new AsyncTaskJson(MainActivity.this);
    Context context;
    boolean clicked=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_home);
        btnStart= (Button) findViewById(R.id.btnStart);
        btnExit =(Button) findViewById(R.id.btnExit);
      prog= (ProgressBar) findViewById(R.id.progressBar);
        loadintri = (TextView) findViewById(R.id.textView6);

        imgtrivia= (ImageView) findViewById(R.id.imgTrivia);
        imgtrivia.setVisibility(View.INVISIBLE);

        btnStart.setEnabled(false);

        async.delegate =this;
        async.execute("http://dev.theappsdr.com/apis/trivia_json/index.php");

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,TriviaActivity.class);
                i.putExtra("ALIST",response);
                startActivity(i);
            }
        });
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //finish();
                //System.exit(1);
                Intent homeIntent = new Intent(Intent.ACTION_MAIN);
                homeIntent.addCategory( Intent.CATEGORY_HOME );
                homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeIntent);
            }
        });


        /*btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnStart.setEnabled(false);
                clicked=true;//context=this.getApplicationContext();
//imgtrivia.setVisibility(View.INVISIBLE);
               *//* prog.setVisibility(View.VISIBLE);*//*

                try {
                    response=  new AsyncTaskJson(MainActivity.this).execute("http://dev.theappsdr.com/apis/trivia_json/index.php").get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
Log.d("demores", ""+response.toString());

                *//*if(response.size() !=0){
                    prog.setVisibility(View.INVISIBLE);
                }*//*

                   *//* if(clicked==true)
                    {   Log.d("demor", response.toString());
                        btnStart.setEnabled(true);
                        prog.setVisibility(View.INVISIBLE);
                        imgtrivia.setVisibility(View.VISIBLE);

                    }*//*

                if((btnStart.isEnabled())==false){
                    btnStart.setEnabled(true);
                    //prog.setVisibility(View.INVISIBLE);
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
        });*/


    }


    @Override
    public void processFinish(ArrayList<TriviaBean> output) {
        response = output;
        if(response.size() !=0){
            prog.setVisibility(View.INVISIBLE);
            imgtrivia.setVisibility(View.VISIBLE);
            btnStart.setEnabled(true);
            loadintri.setText("Trivia Ready");
        }
    }
}
