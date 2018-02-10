package com.myonic.rishibhv.inclass10_group;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
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
import com.google.firebase.database.FirebaseDatabase;


public class SignUpActivity extends AppCompatActivity /* implements View.OnClickListener */ {

    EditText sEditTextFirstName;
    EditText sEditTextLastName;
    EditText sEditTextEmail;
    EditText sEditTextPassword;
    EditText sEditTextConfirmPassword;
    Button sButtonSignUp;
    Button sButtonCancel;
    private FirebaseAuth mAuth;
    public static final String UID = "uid";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_signup);
        sEditTextFirstName = (EditText) findViewById(R.id.editTextFirstName);
        sEditTextLastName =(EditText) findViewById(R.id.editTextLastName);
        sEditTextEmail = (EditText) findViewById(R.id.editTextEmailAct2);
        sEditTextPassword = (EditText) findViewById(R.id.editTextPasswordAct2);
        sEditTextConfirmPassword=(EditText) findViewById(R.id.editTextConfirmPassword);
        sButtonSignUp =(Button) findViewById(R.id.buttonSignUpAct2);
        sButtonCancel= (Button) findViewById(R.id.buttonCancelAct2);
        mAuth = FirebaseAuth.getInstance();
        sButtonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName =sEditTextFirstName.getText().toString();
                String lastName = sEditTextLastName.getText().toString();
                String email = sEditTextEmail.getText().toString();

                String password =sEditTextPassword.getText().toString();
                String confirmPassword =sEditTextConfirmPassword.getText().toString();
                if(firstName.equals("")){
                    Toast.makeText(SignUpActivity.this, "Please enter first name", Toast.LENGTH_SHORT).show();
                }
                else if(lastName.equals("")){
                    Toast.makeText(SignUpActivity.this, "Please enter last name", Toast.LENGTH_SHORT).show();
                }
                else if(email.equals("")){
                    Toast.makeText(SignUpActivity.this, "Please enter email", Toast.LENGTH_SHORT).show();
                }
                else if(password.equals("")){
                    Toast.makeText(SignUpActivity.this, "Please enter password", Toast.LENGTH_SHORT).show();
                }
                else if(confirmPassword.equals("")){
                    Toast.makeText(SignUpActivity.this, "Please enter confirm password", Toast.LENGTH_SHORT).show();
                }
                else if(!password.equals(confirmPassword)){
                    Toast.makeText(SignUpActivity.this, "There is a password mismatch", Toast.LENGTH_SHORT).show();
                }

                //Log.d("demo_ra", "ClickedSignUpInMain");
                else
                    createNewUser(sEditTextFirstName.getText().toString(),sEditTextLastName.getText().toString(),sEditTextEmail.getText().toString(),sEditTextPassword.getText().toString());



            }
        });
        sButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this,MainActivity.class);
                startActivity(intent);

            }
        });



    }



    public void createNewUser(final String firstName, final String lastName, final String email, final String password){

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.e("demo", "createAccount: Success!");

                            // update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            //writeNewUser(user.getUid(), getUsernameFromEmail(user.getEmail()), user.getEmail());
                            writeNewUser(user.getUid(),firstName,lastName,user.getEmail(), password);
                            if(user!=null)
                            {
                                Intent i=new Intent(SignUpActivity.this,DisplayActivity.class);
                                i.putExtra(UID,user.getUid());
                                Log.d("ra_demoUserUid",user.getUid().toString());
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

    private  void writeNewUser(String userId, String firstName,String lastName, String email, String password)
    {
        User user = new User(firstName,lastName,email,password);

        FirebaseDatabase.getInstance().getReference().child("users").child(userId).setValue(user);
    }
}

