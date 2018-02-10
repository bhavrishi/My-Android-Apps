package com.inclass.uncc.group23_inclass04;
/*#Assignment: In Class Assignment $
     #   File Name : Group#_InClass04.zip
        #Full Name: Rosy Azad, Bhavya Gedi*/
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    TextView inputName, inputAge, inputDept, inputZip;
    Button btnAsync, btnThread, btnClose, btnClear;
    TextView selectedResult;
    ProgressDialog objProgressDialog;
    Handler objHandler;
    String[] arrayofPasswords;
    AlertDialog.Builder builderSingle;
    Executor objExecutor;
    String name, dept, tempage, tempzip;
    public int age, zip;
    int times = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
        btnAsync = (Button) findViewById(R.id.btnAsync);
        btnThread = (Button) findViewById(R.id.btnThread);
        inputName = (EditText) findViewById(R.id.inputName);
        inputAge = (EditText) findViewById(R.id.inputAge);
        inputDept = (EditText) findViewById(R.id.inputDept);
        inputZip = (EditText) findViewById(R.id.inputZip);
        selectedResult = (TextView) findViewById(R.id.result);
        btnClear = (Button) findViewById(R.id.btnclear);
        btnClose = (Button) findViewById(R.id.btnClose);
        selectedResult.setVisibility(View.INVISIBLE);
        objProgressDialog = new ProgressDialog(this);
        objProgressDialog.setMessage("GeneratingPasswords");
        objProgressDialog.setMax(100);
        objProgressDialog.setCancelable(false);
        objProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        objHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                switch (msg.what) {
                    case DoWork.START:
                        objProgressDialog.show();
                        break;
                    case DoWork.PROCESSING:
                        objProgressDialog.setMax(5);
                        objProgressDialog.setProgress((Integer) msg.obj);
                        break;
                    case DoWork.FINISH:
                        objProgressDialog.dismiss();
                        builderSingle = new AlertDialog.Builder(MainActivity.this);
                        builderSingle.setCancelable(false);

                        builderSingle.setTitle("Choose a Password:");

                        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.select_dialog_item);
                        for (int i = 0; i < arrayofPasswords.length; i++) {
                            arrayAdapter.add(arrayofPasswords[i]);
                        }

                        builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String strName = arrayAdapter.getItem(which);
                                selectedResult.setText(strName);
                              selectedResult.setVisibility(View.VISIBLE);
                            }
                        });

                        builderSingle.show();

                        break;

                }

                return false;
            }
        });

          btnThread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkAge(inputAge.getText().toString()) && checkZip(inputZip.getText().toString()) && checkName(inputName.getText().toString())&&inputName.getText().toString()!=""&&inputAge.getText().toString()!=""&&inputDept.getText().toString()!=""&&inputZip.getText().toString()!="") {

                    objExecutor = Executors.newFixedThreadPool(2);
                    objExecutor.execute(new DoWork());
                }
                else {

                    if (checkAge(inputAge.getText().toString()) == false) {
                        Toast.makeText(MainActivity.this, "Please enter valid age", Toast.LENGTH_SHORT).show();
                    }
                    if (checkZip(inputZip.getText().toString()) == false) {
                        Toast.makeText(MainActivity.this, "Please enter valid zip", Toast.LENGTH_SHORT).show();
                    }
                    if (checkName(inputName.getText().toString()) == false) {
                        Toast.makeText(MainActivity.this, "Please enter valid Name", Toast.LENGTH_SHORT).show();
                    }
                   /* if(inputName.getText().toString()!=""||inputAge.getText().toString()!=""||inputZip.getText().toString()!=""||inputDept.getText().toString()!="")
                        Toast.makeText(MainActivity.this, "Please enter valid input", Toast.LENGTH_SHORT).show();
               */ }
            }
        });



        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputName.setText("");
                inputAge.setText("");
                inputDept.setText("");
                inputZip.setText("");
            }
        });

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

       /* if(inputName.getText().toString().isEmpty()||inputAge.getText().toString().isEmpty()||inputDept.getText().toString().isEmpty()||inputZip.getText().toString().isEmpty())
        {
            Log.d("demonull",inputName.getText().toString());
            Toast.makeText(this,"Please enter all the values",Toast.LENGTH_SHORT);
        }
        else {*/
        // if(checkAge(inputAge.getText().toString())) {
        btnAsync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("demonull", inputName.getText().toString());
                name = inputName.getText().toString();
                dept = inputDept.getText().toString();
                tempage = inputAge.getText().toString();
                tempzip = inputZip.getText().toString();
                Log.d("democ", "" + checkAge(inputAge.getText().toString()));
                if (checkAge(inputAge.getText().toString()) && checkZip(inputZip.getText().toString()) && checkName(inputName.getText().toString())) {
                    AsyncTaskRunner runner = new AsyncTaskRunner();
                    Log.d("demo", String.valueOf(times));
                    runner.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, String.valueOf(times));/*.execute(String.valueOf(progressValue));*/
                } else {

                    if (checkAge(inputAge.getText().toString()) == false) {
                        Toast.makeText(MainActivity.this, "Please enter valid age", Toast.LENGTH_SHORT).show();
                    }
                    if (checkZip(inputZip.getText().toString()) == false) {
                        Toast.makeText(MainActivity.this, "Please enter valid zip", Toast.LENGTH_SHORT).show();
                    }
                    if (checkName(inputName.getText().toString()) == false) {
                        Toast.makeText(MainActivity.this, "Please enter valid Name", Toast.LENGTH_SHORT).show();
                    }

                }
                Log.d("demo", name + "" + dept + " ");
            }
        });
        //}
       /* else {

            if(checkAge(inputAge.getText().toString())==false)
            {
                Toast.makeText(this,"Please enter valid age",Toast.LENGTH_SHORT);
            }
        }*/
        //}

    }

    List<String> listItems = new ArrayList<String>();

    private boolean checkName(String name) {

            if(name==""||name.isEmpty()) return true;
            CharSequence inputStr = name;
            Pattern pattern = Pattern.compile(new String("^[a-zA-Z\\s]*$"));
            Matcher matcher = pattern.matcher(inputStr);
            if (matcher.matches()) {
                Log.d("demo", "correctName");
            }
            else{
                return false;
            }

        return true;
    }

    private boolean checkAge(String age) {

            if (age.isEmpty())
                return true;

            if (Integer.parseInt(age) < 0)
                return false;

       return true;
    }

    private boolean checkZip(String zip) {

            int inzip = Integer.parseInt(zip);
            if (zip.isEmpty())
                return true;
            if (zip.length() < 5 || zip.length() > 5)
                return false;

        return true;
    }

    class DoWork implements Runnable {
        static final int START = 0;
        static final int PROCESSING = 1;
        static final int FINISH = 2;

        @Override
        public void run() {
            try {
                Message objMessage = new Message();
                objMessage.what = START;

                objHandler.sendMessage(objMessage);
//
//                int count =  Integer.parseInt(objPasswordCount.getText().toString());
                arrayofPasswords = new String[5];
                for (int i = 0; i < 5; i++) {
                    Log.d("demon", inputName.getText().toString());
                    Log.d("demod", inputDept.getText().toString());
                    Log.d("demoa", inputAge.getText().toString());
                    Log.d("demoz", inputZip.getText().toString());

                    arrayofPasswords[i] = Util.getPassword(inputName.getText().toString(), inputDept.getText().toString(), Integer.parseInt(inputAge.getText().toString()), Integer.parseInt(inputZip.getText().toString()));
                    //arrayofPasswords[i]="sd";

                    objMessage = new Message();

                    objMessage.obj = i + 1;


                    objMessage.what = PROCESSING;
                    objHandler.sendMessage(objMessage);
                }
                objMessage = new Message();
                objMessage.what = FINISH;
                objHandler.sendMessage(objMessage);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    private class AsyncTaskRunner extends AsyncTask<String, String, String> {
        int countarray;

        private String resp;
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);


        @Override
        protected String doInBackground(String[] params) {
            Log.d("demo", "ength" + params.length);
            // Calls onProgressUpdate()
            try {
               /* int time = Integer.parseInt(params[0]) * 1000;

                Thread.sleep(time);
                resp = "Slept for " + params[0] + " seconds";*/

                for (int i = 1; i <= 5; i++) {
                    publishProgress("" + (i * 100) / 5);

                    resp = Util.getPassword(name, dept, Integer.parseInt(tempage), Integer.parseInt(tempzip));
                    listItems.add(resp);
                    Log.d("demo", resp);
                }

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
            progressDialog.setMessage("Progressing");
            progressDialog.setMax(100);
            progressDialog.setCancelable(false);
            progressDialog.setIndeterminate(false);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            countarray++;
            Log.d("PreExecuteCount", "count" + countarray);
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

                            // resu.setVisibility(View.VISIBLE);
                            selectedResult.setVisibility(View.VISIBLE);
                            selectedResult.setText(String.valueOf(charSequenceItems[i]));
                        }
                    })
            ;
            final AlertDialog simpleBuild = builder.create();
            simpleBuild.show();
            //}


        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            progressDialog.setProgress(Integer.parseInt(values[0]));
        }
    }
}

