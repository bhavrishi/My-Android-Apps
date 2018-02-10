package uncc.com.group23_inclass09;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
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
    private  RecyclerView.LayoutManager mLayoutManager;


    ArrayList<MessageThread> messageThreadList = new ArrayList<>() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_threads);

        final EditText et;
        TextView tv = (TextView) findViewById(R.id.textView);
        rv = (RecyclerView) findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(MessageThreadsActivity.this, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(mLayoutManager);
        //if(messageThreadListAsync!= null) {
        // Log.d("demo_list__insideMainIf",messageThreadListAsync.toString());
        mAdapter = new MessageThreadAdapter(messageThreadList,MessageThreadsActivity.this);
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
                        .header("Authorization","BEARER eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1MTAwNDI3NTksImV4cCI6MTU0MTU3ODc1OSwianRpIjoiMWFGVDZzMUh0UzBxVW5pRTVNOFQ0OSIsInVzZXIiOjI5OH0.i2iNKyqA89zbIOWe8FPRqxo9xRi02vM3VrFvThpmYl8")
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
                mAdapter = new MessageThreadAdapter(messageThreadList,MessageThreadsActivity.this);
                rv.setAdapter(mAdapter);
                rv.setHasFixedSize(true);
            }
        });

    }

    public void createIntentforChatbox(MessageThread openMessageThread){
        //Intent i = new Intent(MessageThreadsActivity.this,MessageActivity.class);
        Log.d("DemoOpenMessadeThread",openMessageThread.toString());


    }

    public void deleteMessageThread(MessageThread delMessageThread){
        String delMessage =delMessageThread.getId();
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://ec2-54-164-74-55.compute-1.amazonaws.com/api/thread/delete/" + delMessage)
                .header("Authorization","BEARER eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1MDk2ODY2OTYsImV4cCI6MTU0MTIyMjY5NiwianRpIjoiMkdKV2c3U0hKS3NiT2IyZVNkVzFWayIsInVzZXIiOjF9.rRTLX3i-kFYxAtbhUXrqQKDxXs0KoTEgV4iRX2q3p5M")
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
                .header("Authorization","BEARER eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1MDk2ODY2OTYsImV4cCI6MTU0MTIyMjY5NiwianRpIjoiMkdKV2c3U0hKS3NiT2IyZVNkVzFWayIsInVzZXIiOjF9.rRTLX3i-kFYxAtbhUXrqQKDxXs0KoTEgV4iRX2q3p5M")
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.getMessage();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (response.isSuccessful()) {

                    String val =response.body().string();
                    Log.d("demo_ra_Success", val);



                } else {
                    Log.d("demoerror", "Not Success\ncode : " + response.code());
                }


            }
        });
    }
}
