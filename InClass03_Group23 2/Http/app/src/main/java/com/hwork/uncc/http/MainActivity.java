package com.hwork.uncc.http;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(isConnected())
                {
                    Toast.makeText(MainActivity.this,"Connected",Toast.LENGTH_SHORT).show();
                    //new GetData().execute("http://rss.cnn.com/rss/money_latest.rss");
                  //  new GetImage().execute("https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png");
                    RequestParams requestParams=new RequestParams("GET", "http://dev.theappsdr.com/lectures/params.php");
                    requestParams.addParams("Key1","Value1 va;");
                    requestParams.addParams("Key2","Value2");
                    requestParams.addParams("Key3","Value3");
                   // requestParams.addParams("Key4","Value4");

new GetParams().execute(requestParams);

                }
                else
                    Toast.makeText(MainActivity.this,"Not Connected",Toast.LENGTH_SHORT).show();

            }
        });
    }
    private class GetImage extends AsyncTask<String, Object, Bitmap>
    {

        @Override
        protected void onPostExecute(Bitmap s) {
            if(s!=null)
            {
                ImageView iv= (ImageView) findViewById(R.id.imageView);
                iv.setImageBitmap(s);

            }
            else
            {
                Log.d("demo","NO DATA");
            }
            super.onPostExecute(s);
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            BufferedReader reader=null;
            try {
                URL url=new URL(params[0]);
                HttpURLConnection con= (HttpURLConnection) url.openConnection();

                con.setRequestMethod("GET");
                Bitmap bm= BitmapFactory.decodeStream(con.getInputStream());


                return bm;
            } catch (MalformedURLException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }





            return null;
        }
    }
    private class GetParams extends AsyncTask<RequestParams,Void,String>
    {

        @Override
        protected void onPostExecute(String s) {
            if(s!=null)
            {
                Log.d("demo",s);

            }
            else
            {
                Log.d("demo","NO DATA");
            }
            super.onPostExecute(s);
        }

        @Override
        protected String doInBackground(RequestParams... params) {
            BufferedReader reader=null;
            try {
                HttpURLConnection con= params[0].setupConnection();
                con.setRequestMethod("GET");

                reader= new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuilder sb=new StringBuilder();
                String line="";
                while((line=reader.readLine())!=null)
                {
                    sb.append(line+"\n");
                }

                return sb.toString();
            } catch (MalformedURLException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            finally{
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }




            return null;
        }
    }
private class GetData extends AsyncTask<String,Void,String>
{

    @Override
    protected void onPostExecute(String s) {
       if(s!=null)
       {
           Log.d("demo",s);

       }
       else
       {
           Log.d("demo","NO DATA");
       }
        super.onPostExecute(s);
    }

    @Override
    protected String doInBackground(String... params) {
        BufferedReader reader=null;
        try {
            URL url=new URL(params[0]);
            HttpURLConnection con= (HttpURLConnection) url.openConnection();

con.setRequestMethod("GET");
            reader= new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder sb=new StringBuilder();
            String line="";
           while((line=reader.readLine())!=null)
            {
                sb.append(line+"\n");
            }

return sb.toString();
            } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        finally{
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }




        return null;
    }
}
    public boolean isConnected()
    {
        ConnectivityManager c= (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nw=c.getActiveNetworkInfo();
        if(nw.isConnected()&&nw!=null)
        {
            return true;
        }
        return false;

    }
}
