package com.inclass.uncc.group25_hw04zip;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class ResultsActivity extends AppCompatActivity implements AsyncResponse {
    int resultCount;
    ArrayList<TriviaBean> response = new ArrayList<TriviaBean>();
    AsyncTaskJson async = new AsyncTaskJson(ResultsActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_results);
        Button btnQuit = (Button) findViewById(R.id.buttonQuit);
        Button btnTryAgain = (Button) findViewById(R.id.buttonTryAgain);


        TextView messageTextView = (TextView) findViewById(R.id.messageTextView);
        ProgressBar perentageDisplay = (ProgressBar) findViewById(R.id.percentageProgressBar);
        perentageDisplay.setMax(100);

        async.delegate = this;
        async.execute("http://dev.theappsdr.com/apis/trivia_json/index.php");


        Bundle b = getIntent().getExtras();
        if (b != null) {

            resultCount = b.getInt("COUNT");
            Log.d("resultcount", "" + resultCount);
        }
        int value = (100 * resultCount) / 15;
        perentageDisplay.setProgress(value);
        TextView textpercent = (TextView) findViewById(R.id.textView5);
        textpercent.setText(value + "%");

        if (value == 100) {
            messageTextView.setText("Congratulations!!! You got all the answers correct!");
        } else {
            messageTextView.setText("Try again and see if you can get all the correct answers!");
        }
        btnTryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent intent = new Intent(ResultsActivity.this,MainActivity.class);
                startActivity(intent);*/
                if (response.size() != 0) {
                    Intent i = new Intent(ResultsActivity.this, TriviaActivity.class);
                    i.putExtra("ALIST", response);
                    startActivity(i);
                }
            }
        });
        btnQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResultsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void processFinish(ArrayList<TriviaBean> output) {
        response = output;
    }
}
