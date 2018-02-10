package com.hw.uncc.okhttp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    OkHttpClient client;  private String dataHeader;
    private String dataBody;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void run() throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient();

        Request.Builder builder = new Request.Builder();
        Request request = new Request.Builder()
                .url("https://api.github.com/repos/square/okhttp/issues")
                .header("User-Agent", "OkHttp Headers.java")
                .addHeader("Accept", "application/json; q=0.5")
                .addHeader("Accept", "application/vnd.github.v3+json")
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
              dataBody = "Error\n" + e.getMessage();

                //updateView();
            }

            @Override
            public void onResponse(Response response) {
                if (response.isSuccessful()) {
                    getResponseData(response);
                } else {
                    dataBody = "Not Success\ncode : " + response.code();
                }

                updateView();
            }

            public void updateView() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                       // showView();
                    }
                });
            }
        });
}
    private void getResponseData(Response response) {
        Headers headers = response.headers();
        for (String header : headers.names()) {
            dataHeader += "name : " + header + "\n+ value : " + headers.get(header) + "\n";
            Log.d("demodhead",dataHeader);
        }

        try {
            dataBody = response.body().string();
            Log.d("demodbdy",dataBody);
        } catch (IOException e) {
            e.printStackTrace();
            dataBody = "Error !\n\n" + e.getMessage();
            Log.d("demodbdy",dataBody);
        }
    }}
      /*Request req=new Request.Builder().url("https://api.github.com/repos/square/okhttp/issues").build();
        client.newCall(req).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                Log.d("demo",response.body().string());
            }
        });
*/

    /*
MyAsync asyn=new MyAsync();
        asyn.execute("Sending Param","Second next param");

    }
    public  class MyAsync extends AsyncTask<String,Void,String>
    {

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("demo",s);
        }

        @Override
        protected String doInBackground(String... strings) {
            OkHttpClient client=new OkHttpClient();
            Request req=new Request.Builder().url("https://api.github.com/repos/square/okhttp/issues").build();
            Response response=null;
            try {
                response=client.newCall(req).execute();
                return response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
*/