package com.inclass.uncc.group23_inclass09zip;
/*Assignment Name:Group23_InClass09
Group 23
Full Names: Bhavya Gedi,Rosy Azad
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import org.json.JSONException;

import java.io.BufferedReader;
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

public class MainActivity extends AppCompatActivity  {
    public static final String EMAIL_KEY = "email";
    public static final String PWD_KEY = "password";
    public static final String ALIST = "password";
    ArrayList<SignUp> loginuser, retrieveduser;
    SharedPref sharedPreference;
    Boolean search = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);
        Button btnlogin = (Button) findViewById(R.id.btnlogin);
        Button btnsignup = (Button) findViewById(R.id.btnlogsignup);
        final EditText email = (EditText) findViewById(R.id.inputemail);
        final EditText pwd = (EditText) findViewById(R.id.inputpwd);
        retrieveduser = new ArrayList<SignUp>();

        sharedPreference = new SharedPref();
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((!email.getText().toString().equals(""))&&(!pwd.getText().toString().equals("")))
                callPostLogin(email.getText().toString(), pwd.getText().toString());
                else
                    Toast.makeText(MainActivity.this,"Please enter both the inputs",Toast.LENGTH_SHORT).show();
               // Log.d("demore",""+retrieveduser);



            }
        });
        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SigUpActivity.class);
                startActivity(i);

            }
        });
    }

    private void callPostLogin(final String email, String pwd) {

        loginuser = null;
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder()
                .add(EMAIL_KEY, email)
                .add(PWD_KEY, pwd)
                .build();
        Log.d("demo", email);
        Request request = new Request.Builder()
                .url("http://ec2-54-164-74-55.compute-1.amazonaws.com/api/login")
                .post(formBody)  // Use PUT on this line.
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //search= false;
                e.getMessage();
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "Login UnSuccesful", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseString = response.body().string();
                    Log.d("demo", responseString);
                    try {
                        loginuser = LoginJSONUtil.parseLogin(responseString);
                        Log.d("demolog", loginuser.toString());
                        if (loginuser.get(0).getToken() != null) {
                            Log.d("demot", "true");


                            MainActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    //Log.d("demore",""+Sea);
                                    Toast.makeText(MainActivity.this, "User Succesfully Logged In", Toast.LENGTH_SHORT).show();

                                    Intent i = new Intent(MainActivity.this, MessageThreadsActivity.class);
                                    i.putExtra("ALIST",loginuser);

                                    startActivity(i);



                                }
                            });
                           // saveResult(loginuser);
                        }


                   /* if(loginuser.size()>0)  */ //
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {

                    Log.d("demoerror", "Not Success\ncode : " + response.code());
                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "Login UnSuccesful", Toast.LENGTH_SHORT).show();
                        }
                    });

                }


            }

        });


    }

    public static ArrayList<SignUp> saveResult(ArrayList<SignUp> loginuser) {
        return loginuser;
    }


}
