package com.hwork.uncc.thread;

import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Thread extends AppCompatActivity {
    // ExecutorService threadPool;

    public Handler handler;
    public ProgressDialog pb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
        //  Button b = (Button) findViewById(R.id.button);
        // threadPool = Executors.newFixedThreadPool(5);
        /* b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //threadPool.execute(new DoWork());
            }
        });*/
        pb = new ProgressDialog(this);
        pb.setMessage("Progressing");
        pb.setMax(100);
        pb.setCancelable(false);
        pb.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        handler = new Handler(new Handler.Callback()
        {
            @Override
            public boolean handleMessage(Message message) {
                switch (message.what) {
                    case DoWork.STATUS_START:
                        pb.show();
                        break;
                    case DoWork.STATUS_STEP:
                       // pb.setProgress((Integer) message.obj);
                        pb.setProgress(message.getData().getInt("progress"));
                        break;
                    case DoWork.STATUS_STOP:
                        pb.dismiss();
                        break;
                }
                return false;
            }
        });
new java.lang.Thread(new DoWork()).start();
    }


    class DoWork implements Runnable
    {
        static final int STATUS_START = 0x00;
        static final int STATUS_STEP = 0x01;
        static final int STATUS_STOP = 0x02;

        @Override
        public void run() {
            Message msg = new Message();

            msg.what = STATUS_START;
            handler.sendMessage(msg);
            for (int i = 0; i < 100; i++) {
                for (int j = 0; j < 10000000; j++) {

                }
                msg = new Message();

                msg.what = STATUS_STEP;
               // msg.obj = i + 1;
                Bundle b=new Bundle();
                b.putInt("progress",i);
               msg.setData(b);
                handler.sendMessage(msg);
            }
            msg = new Message();

            msg.what = STATUS_STOP;

            handler.sendMessage(msg);
        }
    }
}
/*
-----------
Basic Thread Pool

public class Thread extends AppCompatActivity {
    ExecutorService threadPool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
        Button b = (Button) findViewById(R.id.button);
        threadPool = Executors.newFixedThreadPool(5);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                threadPool.execute(new DoWork());
            }
        });
    }
}

class DoWork implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 1000000; i++) {
            for (int j = 0; j < 1000000; j++) {

            }
        }
    }
}-----------------
SENDING MESSAGES

public class Thread extends AppCompatActivity {
    // ExecutorService threadPool;

    public Handler handler;
    public ProgressDialog pb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
        //  Button b = (Button) findViewById(R.id.button);
        // threadPool = Executors.newFixedThreadPool(5);
        /* b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //threadPool.execute(new DoWork());
            }
        });
      //  pb = new ProgressDialog(this);
pb.setMessage("Progressing");
                pb.setMax(100);
                pb.setCancelable(false);
                pb.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                handler = new Handler(new Handler.Callback() {
@Override
public boolean handleMessage(Message message) {
        switch (message.what) {
        case DoWork.STATUS_START:
        pb.show();
        break;
        case DoWork.STATUS_STEP:
        pb.setProgress((Integer) message.obj);
        break;
        case DoWork.STATUS_STOP:
        pb.dismiss();
        break;
        }
        return false;
        }
        });
        new java.lang.Thread(new DoWork()).start();
        }


class DoWork implements Runnable {
    static final int STATUS_START = 0x00;
    static final int STATUS_STEP = 0x01;
    static final int STATUS_STOP = 0x02;

    @Override
    public void run() {
        Message msg = new Message();

        msg.what = STATUS_START;
        handler.sendMessage(msg);
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 1000000; j++) {

            }
            msg = new Message();

            msg.what = STATUS_STEP;
            msg.obj = i + 1;
            handler.sendMessage(msg);
        }
        msg = new Message();

        msg.what = STATUS_STOP;

        handler.sendMessage(msg);
    }
}
}
 */
