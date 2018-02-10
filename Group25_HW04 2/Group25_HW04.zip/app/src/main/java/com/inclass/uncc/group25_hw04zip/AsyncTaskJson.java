package com.inclass.uncc.group25_hw04zip;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Rishi on 27/09/17.
 */

public class AsyncTaskJson  extends AsyncTask<String,Void,ArrayList<TriviaBean>>{



    public Context ccontext;
    public AsyncResponse delegate = null;



    public  AsyncTaskJson(Context context) {
         ccontext=context;
    }

    //public ProgressDialog dialog ;
    @Override
    protected void onPreExecute() {
       /* dialog=    new ProgressDialog(ccontext);
dialog.setMessage("Loading");
        dialog.setCancelable(false);

        dialog.show();*/

        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(ArrayList<TriviaBean> triviaBeen) {
        super.onPostExecute(triviaBeen);
       /* if(dialog.isShowing())
            dialog.dismiss();*/
        delegate.processFinish(triviaBeen);



    }

    @Override
    protected ArrayList<TriviaBean> doInBackground(String... strings) {

        try {
            URL url=new URL(strings[0]);
            HttpURLConnection con= (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            int statuscpde=con.getResponseCode();
            if(statuscpde==HttpURLConnection.HTTP_OK)
            {
                Thread.sleep(3500);
                BufferedReader reader=new BufferedReader(new InputStreamReader(con.getInputStream()));
           StringBuilder sb=new StringBuilder();
                String line=reader.readLine();
                while(line!=null)
                {
                    sb.append(line);
                    line=reader.readLine();
                }
                Log.d("demo",sb.toString());
                return TriviaUtil.TriviaJSONParser.parseQuestions(sb.toString());

            }




        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
