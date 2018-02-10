package com.myonic.rishibhv.inclass10_group;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    EditText mEditTextEmail;
    EditText mEditTextPassword;
    Button mButtonLogin;
    Button mButtonSignUp;
public static final String UID="uid";
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
        mAuth = FirebaseAuth.getInstance();
      //  Button button2= (Button) findViewById(R.id.button2);
      /*  button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,DisplayActivity.class);
                i.putExtra(UID,"fdfdf");
                startActivity(i);
            }
        });*/
        mEditTextEmail =(EditText) findViewById(R.id.editTextEmail);;
        mEditTextPassword = (EditText) findViewById(R.id.editTextPassword);
        mButtonLogin= (Button)findViewById(R.id.buttonLogIn);
        mButtonSignUp=(Button) findViewById(R.id.buttonSignUpAct2);

        mButtonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });
        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEditTextEmail.getText().toString();
                String password =mEditTextPassword.getText().toString();
                if(email.equals("")){
                    Toast.makeText(MainActivity.this, "Please enter email to login", Toast.LENGTH_SHORT).show();
                }
                else if (password.equals("")){
                    Toast.makeText(MainActivity.this, "Please enter password to log in", Toast.LENGTH_SHORT).show();
                }
                else
                    signIn(email,password);

            }
        });

    }

    public void signIn(String email, String password) {
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
                            Toast.makeText(MainActivity.this, "Login Successfulllll", Toast.LENGTH_SHORT).show();
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

}