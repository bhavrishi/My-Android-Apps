package com.inclass.uncc.group23_inclass09zip;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MessageThreadsActivity extends AppCompatActivity {

    //private static RecyclerView rv;
    private  RecyclerView rv;
    private  RecyclerView.Adapter mAdapter;
    private  RecyclerView.LayoutManager mLayoutManager; ArrayList<SignUp> myList;
int userid;
String token=null;
    ArrayList<MessageThread> messageThreadList = new ArrayList<>() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_threads);
        myList = (ArrayList<SignUp>) getIntent().getSerializableExtra("ALIST");
        Log.d("demomsg",myList.toString());
userid=myList.get(0).getUserid();
        token=myList.get(0).getToken();
        TextView tv =(TextView) findViewById(R.id.textView);
        tv.setText(myList.get(0).getFname()+ " "+ myList.get(0).getLname());
        ImageView logout= (ImageView) findViewById(R.id.imageView);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                token=null;
                finish();
                Intent i=new Intent(MessageThreadsActivity.this,MainActivity.class);
                startActivity(i);
            }
        });
        final EditText et;
        rv = (RecyclerView) findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(MessageThreadsActivity.this, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(mLayoutManager);
        //if(messageThreadListAsync!= null) {
        // Log.d("demo_list__insideMainIf",messageThreadListAsync.toString());
        mAdapter = new MessageThreadAdapter(messageThreadList,MessageThreadsActivity.this,userid);
        //}
        rv.setAdapter(mAdapter);
        rv.setHasFixedSize(true);

        populateRecyclerView();

        et= (EditText) findViewById(R.id.editTextAddThread);

        // Log.d("demoSSSS",s);

        findViewById(R.id.imageViewaddThread).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpClient okHttpClient = new OkHttpClient();
                String s = et.getText().toString();

                RequestBody formBody = new FormBody.Builder()
                        .add("title",s )
                        .build();
                Log.d("demoSSSS",s);
                Request request = new Request.Builder()
                        .url("http://ec2-54-164-74-55.compute-1.amazonaws.com/api/thread/add")
                        .header("Authorization","BEARER "+token)
                        .post(formBody)
                        .build();
                okHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.getMessage();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {

                        if (response.isSuccessful()) {
                            //final ArrayList<MessageThread> messageThreadListAsync ;
                            String val =response.body().string();
                            Log.d("demo_ra_AddSuccess", val);
                            populateRecyclerView();
                            //et.setText("");
                            /*try {

                                //messageThreadListAsync = MessageThreadUtil.jsonParsor(val);
                                //getMessageThreadResponse(messageThreadListAsync);
                                Log.d("demoList_raAdd",val);



                            } catch (JSONException e) {
                                e.printStackTrace();
                            }*/


                        } else {
                            Log.d("demoerror", "Not Success\ncode : " + response.code());
                        }


                    }
                });
                et.setText("");
            }
        });

    }

    public  void  getMessageThreadResponse(ArrayList<MessageThread> messageThreads){

        messageThreadList = messageThreads;
        Log.d("demo_list_newMethod",messageThreadList.toString());

        MessageThreadsActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //rv = (RecyclerView) findViewById(R.id.recyclerView);
                //mLayoutManager = new LinearLayoutManager(MessageThreadsActivity.this, LinearLayoutManager.VERTICAL, false);
                rv.setLayoutManager(mLayoutManager);
                //if(messageThreadListAsync!= null) {
                // Log.d("demo_list__insideMainIf",messageThreadListAsync.toString());
                mAdapter = new MessageThreadAdapter(messageThreadList,MessageThreadsActivity.this,userid);
                rv.setAdapter(mAdapter);
                rv.setHasFixedSize(true);
            }
        });

    }

    public void createIntentforChatbox(MessageThread openMessageThread){
        Intent i = new Intent(MessageThreadsActivity.this,MessageActivity.class);
        i.putExtra("THREAD",openMessageThread);
        i.putExtra("tok",token);
        i.putExtra("UID",userid);
        i.putExtra("ALIST",myList);

        Log.d("DemoOpenMessadeThread",openMessageThread.toString());
        startActivity(i);


    }

    public void deleteMessageThread(MessageThread delMessageThread){
        int delMessage =delMessageThread.getId();
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://ec2-54-164-74-55.compute-1.amazonaws.com/api/thread/delete/"+delMessage)
                .header("Authorization","BEARER "+token)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.getMessage();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (response.isSuccessful()) {
                    //final ArrayList<MessageThread> messageThreadListAsync ;
                    String val =response.body().string();
                    Log.d("demo_ra_Success", val);



                } else {
                    Log.d("demoerror", "Not Success\ncode : " + response.code());
                }


            }
        });

    }

    public  void populateRecyclerView(){

        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://ec2-54-164-74-55.compute-1.amazonaws.com/api/thread")
                .header("Authorization","BEARER "+token)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.getMessage();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (response.isSuccessful()) {
                    final ArrayList<MessageThread> messageThreadListAsync ;
                    String val =response.body().string();
                    Log.d("demo_ra_Success", val);
                    try {

                        messageThreadListAsync = MessageThreadUtil.jsonParsor(val);
                        getMessageThreadResponse(messageThreadListAsync);
                        Log.d("demoList_ra",messageThreadListAsync.toString());



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                } else {
                    Log.d("demoerror", "Not Success\ncode : " + response.code());
                }


            }
        });
    }
}