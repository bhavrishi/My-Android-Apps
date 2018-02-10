package com.myonic.rishibhv.firebasepush;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String UID = "uid";
    private EditText edtEmail;
    private EditText edtPassword;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtEmail = (EditText) findViewById(R.id.email);
        edtPassword = (EditText) findViewById(R.id.password);
        findViewById(R.id.sign_up_button).setOnClickListener(this);
        findViewById(R.id.sign_in_button).setOnClickListener(this);
       
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();

        if (i == R.id.sign_up_button) {
            createAccount(edtEmail.getText().toString(), edtPassword.getText().toString());
        } else if (i == R.id.sign_in_button) {
            signIn(edtEmail.getText().toString(), edtPassword.getText().toString());
        }
    }

    private void signIn(String email, String password) {
        {
            Log.e("demo", "signIn:" + email);
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Log.e("demo", "signIn: Success!");

                        // update UI with the signed-in user's information
                        FirebaseUser user = mAuth.getCurrentUser();
                        if(user!=null)
                        {
                            Intent i=new Intent(MainActivity.this,DisplayActivity.class);
                            i.putExtra(UID,user.getUid());
                            startActivity(i);
                        }
                       // updateUI(user);
                    } else {
                        Log.e("demo", "signIn: Fail!", task.getException());
                        Toast.makeText(getApplicationContext(), "Authentication failed!", Toast.LENGTH_SHORT).show();
                        //updateUI(null);
                    }

                    if (!task.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Task failed!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }


    private void createAccount(String email, String password) {  Log.e("demo", "createAccount:" + email);

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.e("demo", "createAccount: Success!");

                            // update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            writeNewUser(user.getUid(), getUsernameFromEmail(user.getEmail()), user.getEmail());
                            if(user!=null)
                            {
                                Intent i=new Intent(MainActivity.this,DisplayActivity.class);
                                i.putExtra(UID,user.getUid());
                                startActivity(i);
                            }
                            //updateUI(user);
                        } else {
                            Log.e("demo", "createAccount: Fail!", task.getException());
                            Toast.makeText(getApplicationContext(), "Authentication failed!", Toast.LENGTH_SHORT).show();
                           // updateUI(null);
                        }
                    }
                });
    }

    private String getUsernameFromEmail(String email) {
        if (email.contains("@")) {
            return email.split("@")[0];
        } else {
            return email;
        }
    }

    private  void writeNewUser(String userId, String username, String email)
    {
        User user = new User(username, email);

        FirebaseDatabase.getInstance().getReference().child("users").child(userId).setValue(user);
    }
}
