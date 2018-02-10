package com.myonic.rishibhv.fbrishi;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity
       /* GoogleApiClient.OnConnectionFailedListener */{
    /*public static final int INTER=3001;
    GoogleApiClient mGoogleApiClient;
    Button btn2;
TextView txt;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==INTER){
            GoogleSignInResult result=Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handlesigninrresult(result);
        }
    }

    private void handlesigninrresult(GoogleSignInResult result) {
        Log.d("demosuccess",""+result.isSuccess());
        if(result.isSuccess())
        {
            GoogleSignInAccount gaccnt=result.getSignInAccount();
            txt.setText(gaccnt.getDisplayName());
        }

    }*/
TextView text;Button sun,fog;

    @Override
    protected void onStart() {
        super.onStart();
        mconref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                text.setText(dataSnapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    DatabaseReference mref= FirebaseDatabase.getInstance().getReference();
    DatabaseReference mconref=mref.child("condition");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
text= (TextView) findViewById(R.id.textView2);
        sun= (Button) findViewById(R.id.btnsun);
        fog= (Button) findViewById(R.id.btnfog);
        sun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mconref.setValue("Sunny day!");
            }
        });
        fog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mconref.setValue("Foggy day!");
            }
        });

    }
        /*   GoogleSignInOptions gso=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        mGoogleApiClient=new GoogleApiClient.Builder(this).enableAutoManage(this, this).addApi(Auth.GOOGLE_SIGN_IN_API,gso).build();
txt= (TextView) findViewById(R.id.textView);
        SignInButton btnsignin= (SignInButton) findViewById(R.id.sign_in_button);
        btnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInt=Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(signInt, INTER);
            }
        });
findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                txt.setText("signedout");
            }
        });
    }
});
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
Log.d("demofailed",""+connectionResult);
    }*/


}
