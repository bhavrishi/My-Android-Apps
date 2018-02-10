package com.example.rishi.previous_inclass03;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    SeekBar seekBar;
    TextView setValue,selected,selecttext;int countarray = 0;

    int progressValue;
    Button generateButton;
    final static int REQ_CODE=100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_relative);

        seekBar = (SeekBar) findViewById(R.id.seekBar2);
        setValue = (TextView) findViewById(R.id.countvalue);
        generateButton = (Button) findViewById(R.id.generateButton);
        selecttext=(TextView) findViewById(R.id.textselect);
        selected=(TextView) findViewById(R.id.valueselect);
        selecttext.setVisibility(View.INVISIBLE);
        selected.setVisibility(View.INVISIBLE);
        seekBar.setMax(10);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                progressValue = i;
                setValue.setText(String.valueOf(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AsyncTaskRunner runner = new AsyncTaskRunner();
                Log.d("demo", String.valueOf(progressValue));
                runner.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,String.valueOf(progressValue));/*.execute(String.valueOf(progressValue));*/

            }
        });


    }

    List<String> listItems = new ArrayList<String>();


    private class AsyncTaskRunner extends AsyncTask<String, String, String> {
        private String resp;AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);


        @Override
        protected String doInBackground(String[] params) {
            Log.d("demo", "ength" + params.length);
            publishProgress("Sleeping..."); // Calls onProgressUpdate()
            try {
               /* int time = Integer.parseInt(params[0]) * 1000;

                Thread.sleep(time);
                resp = "Slept for " + params[0] + " seconds";*/

for(int i=0;i<progressValue;i++)
{resp = Util.getPassword();listItems.add(resp);
 }
                Log.d("demo", resp);
            } catch (Exception e) {
                e.printStackTrace();
                resp = e.getMessage();
            }

            Log.d("demo", "Times");

            return resp;
        }

        @Override
        protected void onPreExecute() {
            progressDialog.setMessage("Wait");
            progressDialog.setCancelable(false);
            countarray++;
            Log.d("PreExecuteCount","count"+countarray);
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(String s) {
            if (progressDialog.isShowing()) progressDialog.dismiss();
            Log.d("demo", "Count" + countarray);
          //  if (countarray == 2) {
                final CharSequence[] charSequenceItems = listItems.toArray(new CharSequence[listItems.size()]);


                Log.d("Dialogdemo", String.valueOf(charSequenceItems[0]));
                builder.setTitle("Delete content")
                      //  .setMessage("Äre you sure wanna delete")
                        .setCancelable(false)

                        .setItems(charSequenceItems, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                              //  generateButton.setText(charSequenceItems[i]);
                                Log.d("iialogdemo", String.valueOf(charSequenceItems[i]));
                                /*Intent intent=new Intent(MainActivity.this,SecondActivity.class) ;
                                startActivityForResult(intent,REQ_CODE);*/
                                selecttext.setVisibility(View.VISIBLE);
                                selected.setVisibility(View.VISIBLE);
                                selected.setText(String.valueOf(charSequenceItems[i]));
                            }
                        })
                ;
                final AlertDialog simpleBuild=builder.create();
            simpleBuild.show();
            //}

          

        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);//progressDialog.setProgress(Integer.parseInt(values[0]));
        }
    }
}
