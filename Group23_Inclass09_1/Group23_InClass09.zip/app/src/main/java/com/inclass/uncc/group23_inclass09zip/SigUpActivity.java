package com.inclass.uncc.group23_inclass09zip;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

public class SigUpActivity extends AppCompatActivity {


    public  static final String EMAIL_KEY="email";
    public  static final String FNAME_KEY="fname";
    public  static final String LNAME_KEY="lname";
    public  static final String PWD_KEY="password";
    ArrayList<SignUp> signupUser;
    SharedPref sharedPreference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_signup);
        final EditText fname = (EditText) findViewById(R.id.fname);
        final EditText lname = (EditText) findViewById(R.id.lname);
        final EditText email = (EditText) findViewById(R.id.email);
        final EditText pwd1 = (EditText) findViewById(R.id.pwd1);
        final EditText pwd2 = (EditText) findViewById(R.id.pwd2);
        Button btnSignup = (Button) findViewById(R.id.btnsignup);
        Button btnCancel = (Button) findViewById(R.id.btnCancel);
        signupUser=new ArrayList<SignUp>();
        sharedPreference=new SharedPref();
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(pwd1.equals(pwd2))
                    callPostSignUp(fname.getText().toString(),lname.getText().toString(),email.getText().toString(),pwd1.getText().toString());
                else{
                        Toast.makeText(SigUpActivity.this,"Please enter matching passwords",Toast.LENGTH_SHORT).show();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(SigUpActivity.this,MainActivity.class);
                startActivity(i);
            }
        });


    }

    private void callPostSignUp(String fname, String lname, String email, String pwd1) throws IOException {


        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder()
                .add(FNAME_KEY, fname)
                .add(LNAME_KEY, lname)
                .add(PWD_KEY, pwd1)
                .add(EMAIL_KEY, email)
                .build();
        Log.d("demo",fname);
        Request request = new Request.Builder()
                .url("http://ec2-54-164-74-55.compute-1.amazonaws.com/api/signup")
                .post(formBody)  // Use PUT on this line.
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.getMessage();
                SigUpActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //Log.d("demore",""+Sea);
                        Toast.makeText(SigUpActivity.this, "Please Try Again", Toast.LENGTH_SHORT).show();





                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseString=response.body().string();
                    Log.d("demo", responseString);

                        try {
                            signupUser=  SignUPJSONUtil.parseSignUp(responseString);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.d("demo", signupUser.toString());
                        sharedPreference.addFavorite(SigUpActivity.this, signupUser.get(0));
                        List<SignUp> favorites = SharedPref.getFavorites(SigUpActivity.this);
                        Log.d("fav", signupUser.toString());
                        SigUpActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //Log.d("demore",""+Sea);
                                Toast.makeText(SigUpActivity.this, "Sign In Succesful", Toast.LENGTH_SHORT).show();

                                Intent i = new Intent(SigUpActivity.this, MessageThreadsActivity.class);
                                i.putExtra("ALIST",signupUser);

                                startActivity(i);



                            }
                        });
                        // saveResult(loginuser);



                } else {
                    Log.d("demoerror", "Not Success\ncode : " + response.code());
                    SigUpActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //Log.d("demore",""+Sea);
                            Toast.makeText(SigUpActivity.this, "Please Try Again", Toast.LENGTH_SHORT).show();





                        }
                    });
                }


            }
        });
    }
}
