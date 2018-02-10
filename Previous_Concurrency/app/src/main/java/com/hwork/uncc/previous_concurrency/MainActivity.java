package com.hwork.uncc.previous_concurrency;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
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
    Button btnasync, btnThread;
    public Handler handler;
    public ProgressDialog pb;
    int progressValue;
    SeekBar seekBar;
    TextView result, selecttext, setValue;
    int countarray = 0;
    Double sum = 0.0;
    Double sumavg = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_linear);

        seekBar = (SeekBar) findViewById(R.id.seekBar);
        // setValue = (TextView) findViewById(R.id.textselect);
        btnasync = (Button) findViewById(R.id.btnasync);
        btnThread = (Button) findViewById(R.id.btnHandler);
        selecttext = (TextView) findViewById(R.id.textselect);
        result = (TextView) findViewById(R.id.textselect);
        setValue = (TextView) findViewById(R.id.textresult);
        selecttext.setVisibility(View.INVISIBLE);
        // selected.setVisibility(View.INVISIBLE);
        seekBar.setMax(10);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                progressValue = i;
                Log.d("demo", "" + progressValue);
                setValue.setText(String.valueOf(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        btnasync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AsyncTaskRunner runner = new AsyncTaskRunner();
                Log.d("demo", String.valueOf(progressValue));
                runner.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, String.valueOf(progressValue));/*.execute(String.valueOf(progressValue));*/

            }
        });

        btnThread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pb = new ProgressDialog(MainActivity.this);
                pb.setMessage("Progressing");
                pb.setMax(100);
                pb.setCancelable(false);
                pb.setIndeterminate(false);
                pb.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                handler = new Handler(new Handler.Callback() {
                    @Override
                    public boolean handleMessage(Message message) {
                        switch (message.what) {
                            case ThreadImp.STATUS_START:
                                pb.show();
                                break;
                            case ThreadImp.STATUS_STEP:
                                // pb.setProgress((Integer) message.obj);

Log.d("demop",""+message.getData().getInt("progress"));
                                break;
                            case ThreadImp.STATUS_STOP:
                                pb.dismiss();
                                Log.d("demo", "" + message.getData().getDouble("avg"));
                                Log.d("demo", "" + sumavg);
                                setValue.setText("" + sumavg);
                                break;
                        }
                        return false;
                    }
                });
                new java.lang.Thread(new ThreadImp()).start();
            }
        });
    }

    List<Double> listItems = new ArrayList<>();
    double[] nums = new double[10];
    int FINAL1 = (20 * 100) / 100;

    private class AsyncTaskRunner extends AsyncTask<String, String, String> {
        private String resp;
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);


        @Override
        protected String doInBackground(String... params) {
            Log.d("demo", "ength" + params.length);
            int i = 0;
            // Calls onProgressUpdate()
            try {

                for (i = 0; i < progressValue; i++) {
                   //publishProgress("Wait");
                     publishProgress("" + (int) ((i * 100) / (progressValue)));
                   // progressDialog.setProgress((i*100)/progressValue);
                   // progressDialog.incrementProgressBy(FINAL1);
                    nums[i] = HeavyWork.getNumber();
                    sum = sum + nums[i];
                    Log.d("demoresp", "" + nums[i]);
                    Log.d("demosum", "" + sum);
                }
                if (i == progressValue)
                    sumavg = sum / (double) progressValue;
                Log.d("demoavg", "" + sumavg);
            } catch (Exception e) {
                e.printStackTrace();
                // resp = e.getMessage();
            }

            Log.d("demo", "Times");

            return resp;
        }

        @Override
        protected void onPreExecute() {

            for (int i = 0; i < progressValue; i++) {
                progressDialog.setMessage("Retreiving number");

                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.setCancelable(false);
                progressDialog.setProgress(0);
                progressDialog.setMax(100);
                progressDialog.incrementProgressBy(FINAL1);
                countarray++;
                Log.d("PredemoExecuteCount", "count" + countarray);
                progressDialog.show();
            }
        }

        @Override
        protected void onPostExecute(String s) {
            if (progressDialog.isShowing()) progressDialog.dismiss();
            Log.d("demo", "Count" + countarray);
            //  if (countarray == 2) {
            final CharSequence charSequenceItems = "" + sumavg;
            //listItems.toArray(new CharSequence[listItems.size()]);
            result.setVisibility(View.VISIBLE);
            result.setText(String.valueOf(charSequenceItems));

            Log.d("Dialogdemo", "" + charSequenceItems);

            final AlertDialog simpleBuild = builder.create();
            simpleBuild.show();

        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);progressDialog.setProgress(Integer.parseInt(values[0]));
        }
    }

    class ThreadImp implements Runnable {
        static final int STATUS_START = 0x00;
        static final int STATUS_STEP = 0x01;
        static final int STATUS_STOP = 0x02;

        @Override
        public void run() {
            int i = 0;
            Message msg = new Message();

            msg.what = STATUS_START;
            handler.sendMessage(msg);
            for (i = 0; i < progressValue; i++) {
                nums[i] = HeavyWork.getNumber();
                sum = sum + nums[i];
                Log.d("demoresp", "" + nums[i]);
                Log.d("demosum", "" + sum);
                pb.setProgress((i*100)/progressValue);
            }
            if (i == progressValue)
                sumavg = sum / (double) progressValue;
            Log.d("demoavg", "" + sumavg);
            msg = new Message();
            msg.what = STATUS_STEP;
            // msg.obj = i + 1;
            Bundle b = new Bundle();
            Log.d("idemo", "" + i);
            b.putInt("progress", (i * 10000) / 100);
            b.putDouble("avg", sumavg);
            msg.setData(b);
            handler.sendMessage(msg);

            msg = new Message();

            msg.what = STATUS_STOP;

            handler.sendMessage(msg);
        }
    }
}
