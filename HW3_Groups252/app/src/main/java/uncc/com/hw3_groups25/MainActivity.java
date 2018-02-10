package uncc.com.hw3_groups25;
/*#Assignment: Home Work Assignment
     #   File Name : HW3_Groups25.zip
        #Full Name: Rosy Azad, Bhavya Gedi*/


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    private String[] str;
    ExecutorService threadpool;
    Handler handler;
    private Button buttonSearch;
    private ImageButton mainAdd;
    private EditText editText;
    private int count;
    private CheckBox checkBoxSearch;
    ProgressBar progressBar;
    private CheckBox checkBox;
    final static String SEARCH_RESULT = "SEARCH";
    final static String SEARCH_KEY = "SEARCH_KEY";
    private static ArrayList<String> fileLines;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        buttonSearch = (Button) findViewById(R.id.buttonSearch);
        mainAdd = (ImageButton) findViewById(R.id.imageButtonMain1);
        editText = (EditText) findViewById(R.id.textViewMain1);
        checkBox = (CheckBox) findViewById(R.id.checkBoxSearch);

        mainAdd.setTag("Add");
        mainAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String check = (String) mainAdd.getTag();
                Log.d("prb_log","Check string " + check);
                if (check.equals("Add")) {
                    LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearlayoutMain);
                    listItemUILayout itemUI = new listItemUILayout(MainActivity.this);
                    linearLayout.addView(itemUI);
                    mainAdd.setImageResource(R.drawable.remove);
                    mainAdd.setTag("Sub");
                }else {
                    LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearlayoutMain);
                    linearLayout.removeView(findViewById(R.id.linearlayoutMain1));
                }

            }
        });

        progressBar = (ProgressBar)findViewById(R.id.progressBarBottom);
        progressBar.setMax(100);
        buttonSearch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Log.d("prb_log","Called dowork");

                int isCaseSensitive;
                int validCheck = 0;
                if (checkBox.isChecked()){

                    isCaseSensitive = 1;
                }else {
                    isCaseSensitive = 0;
                }

                int myCount = 0;
                LinearLayout mainLinearLayout = (LinearLayout)findViewById(R.id.linearlayoutMain);
                int childcount = mainLinearLayout.getChildCount();
                String[] newStr = new String[childcount];
                for (int i = 0; i < childcount; i++){
                    if (mainLinearLayout.getChildAt(i) == (LinearLayout)findViewById(R.id.linearlayoutMain1)){
                        String tmp1 = ((EditText) findViewById(R.id.textViewMain1)).getText().toString();
                        if (tmp1.isEmpty() || tmp1.equals("")) {
                            Toast.makeText(getBaseContext(),"Empty String !!", Toast.LENGTH_LONG).show();
                            validCheck = 1;

                        }else {
                            newStr[myCount++] = ((EditText) findViewById(R.id.textViewMain1)).getText().toString();
                        }

                    } else {

                        LinearLayout subLinearLayout = (LinearLayout) mainLinearLayout.getChildAt(i);
                        LinearLayout myLinearLayout = (LinearLayout) subLinearLayout.getChildAt(0);
                        int myChildCount = myLinearLayout.getChildCount();

                        if (myLinearLayout.getChildAt(0) instanceof EditText){
                            String tmp2 = ((EditText) myLinearLayout.getChildAt(0)).getText().toString();
                            if (tmp2.isEmpty() || tmp2.equals("")){
                                Toast.makeText(getBaseContext(),"Empty String !!", Toast.LENGTH_LONG).show();
                                validCheck = 1;
                            }else {
                                newStr[myCount++] = ((EditText) myLinearLayout.getChildAt(0)).getText().toString();
                            }

                        } else if (myLinearLayout.getChildAt(1) instanceof EditText) {
                            newStr[myCount++] = ((EditText) myLinearLayout.getChildAt(1)).getText().toString();
                        }
                    }

                }
                if (validCheck != 1 && checkRepeatedString(newStr)) {
                    str = newStr;
                    threadpool = Executors.newFixedThreadPool(20);
                    getDataFromFile(MainActivity.this,"textfile.txt");
                    for (int length = 0; length < str.length; length++){
                        threadpool.execute(new DoWork(str[length], isCaseSensitive, fileLines, length, newStr.length));
                    }
                }else{
                    Toast.makeText(getBaseContext(),"Try Again !!", Toast.LENGTH_LONG).show();
                }
            }
        });

        handler = new Handler(new Handler.Callback() {
            private ArrayList<ArrayList<String>> finalResult = new ArrayList<ArrayList<String>>();
            private ArrayList<String> finalStr, passStr;
            private int processCount = 0;
            @Override
            public boolean handleMessage(Message message) {
                switch (message.what){
                    case DoWork.PROCESS_START:
                        progressBar.setProgress(0);
                         progressBar.setProgress(30);
                        break;

                    case DoWork.PROCESS_STEP:


                        progressBar.setProgress(60);
                        processCount++;
                        ArrayList<String> tmpFinalResult = new ArrayList<String>();
                        tmpFinalResult = message.getData().getStringArrayList("WordSearch");
                        finalResult.add(tmpFinalResult);
                        break;

                    case DoWork.PROCESS_END:
                        progressBar.setProgress(80);
                        progressBar.setProgress(100);
                        Log.d("prb_log", "Starting activity for displaying results");
                        if (processCount == str.length) {
                            Intent intent = new Intent(MainActivity.this, DisplaySearch.class);
                            intent.putExtra(SEARCH_RESULT, finalResult);
                            intent.putExtra(SEARCH_KEY, str);
                            startActivity(intent);
                            finalResult.clear();
                            processCount = 0;
                        }
                        progressBar.setProgress(0);

                        break;
                }
                return false;
            }
        });

    }

    public void onClickButton(View view){
        String check1 = (String) view.findViewById(R.id.imageButtonMainOther).getTag();
        Log.d("prb_log","Check string 1" + check1);
        int id = view.getId();
        Log.d("prb_log", "Check id: " + id);

        if (((LinearLayout)findViewById(R.id.linearlayoutMain)).getChildCount() <= 20) {


            if (check1.equals("Add")) {
                LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearlayoutMain);
                listItemUILayout itemUI = new listItemUILayout(MainActivity.this);
                linearLayout.addView(itemUI);
                ((ImageButton) view.findViewById(R.id.imageButtonMainOther)).setImageResource(R.drawable.remove);
                ((ImageButton) view.findViewById(R.id.imageButtonMainOther)).setTag("Sub");
            } else {
                LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearlayoutMain);
                ImageButton ib = ((ImageButton) view.findViewById(R.id.imageButtonMainOther));
                LinearLayout removeparent = (LinearLayout) ib.getParent();
                linearLayout.removeView(((LinearLayout) removeparent.getParent()));

            }
        }
    }



    class DoWork implements Runnable{
        static final int PROCESS_START = 0x01;
        static final int PROCESS_STEP = 0x02;
        static final int PROCESS_END = 0x03;
        String str;
        int casesensitive, length, totallength;
        ArrayList<String> result;
        ArrayList<String> filelines;
        public DoWork(String str, int checkcase, ArrayList<String> mylines, int length, int totallength){
            this.str = str;
            this.casesensitive = checkcase;
            this.filelines = mylines;
            this.length = length;
            this.totallength = totallength;
        }
        @Override
        public void run() {
            Message msg = new Message();
            msg.what = PROCESS_START;
            //msg.obj = 10;
            handler.sendMessage(msg);

            result = TextSearchUtil.SearchKeyWordInFile(filelines,"textfile.txt", str,casesensitive);
            msg = new Message();
            msg.what = PROCESS_STEP;
            //msg.obj = (int)((totallength/length) * 100);
            Bundle bundle = new Bundle();
            bundle.putStringArrayList("WordSearch", result);
            msg.setData(bundle);
            handler.sendMessage(msg);
            //}




            msg = new Message();
            msg.what = PROCESS_END;
            handler.sendMessage(msg);
        }
    }

    public boolean checkRepeatedString(String[] strings) {
        Arrays.sort(strings);
        for (int i = 0; i < strings.length - 1; i++){
            if (strings[i].equals(strings[i+1])){
                return false;
            }
        }
        return true;
    }



    private static void getDataFromFile(Context context, String filePath) {
        fileLines = new ArrayList<String>();
        if (filePath == null || filePath.isEmpty()) {
            Log.d("prb_log","Invalid File Path");
            return;
        }

        BufferedReader inputStream = null;
        InputStream is = null;

        try {
            try {
                is = context.getAssets().open(filePath);
                inputStream = new BufferedReader(new InputStreamReader(is));
                String lineContent = null;


                while ((lineContent = inputStream.readLine()) != null) {
                    fileLines.add(lineContent.replaceAll("\t", " "));
                }
                is.close();
            }
            finally {
                if (inputStream != null) {
                    inputStream.close();
                    is.close();
                }

            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
