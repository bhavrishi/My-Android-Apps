package com.inclass.uncc.group23_inclass09zip;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MessageActivity extends AppCompatActivity {
String token=null;
    private  RecyclerView.Adapter mAdapter;
    private  RecyclerView.LayoutManager mLayoutManager;
        int userId;RecyclerView rv;
    int threadid;
    ArrayList<Chat> chats;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_messages);
         rv= (RecyclerView) findViewById(R.id.rv);
        MessageThread myList = (MessageThread) getIntent().getSerializableExtra("THREAD");
       final ArrayList<MessageThread> alist = (ArrayList<MessageThread>) getIntent().getSerializableExtra("ALIST");
        token= (String) getIntent().getSerializableExtra("tok");
        Log.d("demomsg",myList.toString());
        userId= (int) getIntent().getSerializableExtra("UID");
        threadid=myList.getId();
        List<SignUp> favorites = SharedPref.getFavorites(MessageActivity.this);
        chats=new ArrayList<Chat>();
     /*   for(int i=0;i<favorites.size();i++)
        {
            if((favorites.get(i).getUserid())==userId)
                token=favorites.get(i).getToken();

        }*/

        Log.d("demotoken",token);

        ImageView imghome= (ImageView) findViewById(R.id.imghome);
        imghome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MessageActivity.this,MessageThreadsActivity.class);
                i.putExtra("ALIST",alist);
                startActivity(i);
            }
        });
        rv = (RecyclerView) findViewById(R.id.rv);
        mLayoutManager = new LinearLayoutManager(MessageActivity.this, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(mLayoutManager);
        callMessagesinThread();

        final EditText addmsg= (EditText) findViewById(R.id.addmsg);
        final TextView outname= (TextView) findViewById(R.id.outname);
        outname.setText(myList.getTitle());
        ImageView imgsend= (ImageView) findViewById(R.id.imgsend);
        imgsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               callAddMsg(addmsg.getText().toString());
                addmsg.setText("");
            }
        });

    }

    private void callAddMsg(String s) {


        OkHttpClient okHttpClient = new OkHttpClient();

        RequestBody formBody = new FormBody.Builder()
                .add("message",s)
                .add("thread_id", String.valueOf(threadid))
                .build();
        //Log.d("demo",fname);
        Request request = new Request.Builder()
                .url("http://ec2-54-164-74-55.compute-1.amazonaws.com/api/message/add")
                .header("Authorization","BEARER "+token)
                .post(formBody).build();
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
                    Log.d("demoAddSuccess", val);
                    callMessagesinThread();
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

    private void callMessagesinThread() {


        OkHttpClient okHttpClient = new OkHttpClient();

       // Log.d("demo",fname);
        Request request = new Request.Builder()
                .url("http://ec2-54-164-74-55.compute-1.amazonaws.com/api/messages/"+threadid)
                .addHeader("Authorization","BEARER "+token)
               // .post(formBody)  // Use PUT on this line.
                .build();


        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.getMessage();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseString=response.body().string();
                    Log.d("msgs", responseString);
                    try {
                        chats=  ChatJSONUtil.ChatJSONParser.parseChats(responseString);
                       getMessageThreadResponse(chats);
                        Log.d("demochat", chats.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.d("demoerror", "Not Success\ncode : " + response.code());
                }


            }
        });
    }
public void callDelete(int id)
{
    Log.d("demodelete",""+id);



        OkHttpClient okHttpClient = new OkHttpClient();


        //Log.d("demo",fname);
        Request request = new Request.Builder()
                .url("http://ec2-54-164-74-55.compute-1.amazonaws.com/api/message/delete/"+id)
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
                    Log.d("demoDeleteSuccess", val);
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

    private void getMessageThreadResponse(final ArrayList<Chat> chatss) {
        Log.d("demo_list_newMethod",chatss.toString());

        MessageActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(MessageThreadsActivity.this, android.R.layout.simple_list_item_1, nfl);
                //listview.setAdapter(listViewAdapter);

                //if(messageThreadListAsync!= null) {
                // Log.d("demo_list__insideMainIf",messageThreadListAsync.toString());
                mAdapter = new MessageAdapter(MessageActivity.this,chatss,userId);
                //}
                rv.setAdapter(mAdapter);
                rv.setHasFixedSize(true);
            }
        });

    }
}
