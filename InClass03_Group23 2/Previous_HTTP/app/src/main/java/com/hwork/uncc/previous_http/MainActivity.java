package com.hwork.uncc.previous_http;

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
import java.net.URLConnection;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
int click=0;
    ArrayList listid=new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_relative);
        if(isConnected())
        {
            new GetData().execute("http://dev.theappsdr.com/lectures/inclass_photos/index.php");
            ImageView next= (ImageView) findViewById(R.id.imgnext);
            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    click=click+1;
                    new GetImage().execute("http://dev.theappsdr.com/lectures/inclass_photos/index.php?pid="+listid.get(click));
                }
            });
            ImageView back= (ImageView) findViewById(R.id.imgback);
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    click=click-1;
                    new GetImage().execute("http://dev.theappsdr.com/lectures/inclass_photos/index.php?pid="+listid.get(click));
                }
            });
        }
        else
            Toast.makeText(this,"Not connected",Toast.LENGTH_SHORT);
    }
    private class GetImage extends AsyncTask<String, Object, Bitmap>
    {

        @Override
        protected void onPostExecute(Bitmap s) {
            if(s!=null)
            {
                ImageView iv= (ImageView) findViewById(R.id.imgdisplay);
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
            for(int i=0;i<listid.size();i++)
            {
                Log.d("demolist",""+listid.get(i));
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
                    listid.add(line);
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
