package com.myonic.rishibhv.group25_hw06;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class SignUpActivity extends AppCompatActivity {
EditText dob; int mYear,mMonth,mDay;          DatePickerDialog mDatePicker;    Calendar myCalendar;
    EditText email,fname,lname,pwd1,pwd2; Button submit;
    DatabaseReference databaseUsers;String uname;
    ArrayList<User> users;
    private FirebaseAuth mAuth;public static final String UID="uid";
    public static final String NAME="name";
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                mAuth.signOut();
                Intent i=new Intent(SignUpActivity.this,MainActivity.class);
                startActivity(i);
                return (true);

        }
        return (super.onOptionsItemSelected(item));
    }
    @Override
    protected void onStart() {
        super.onStart();
        databaseUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                users.clear();
                for (DataSnapshot postsnapshot : dataSnapshot.getChildren()) {

                    users.add(postsnapshot.getValue(User.class));

                    //   Log.d("demot", postsnapshot.getKey() + uname);

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        databaseUsers = FirebaseDatabase.getInstance().getReference("users");
        dob= (EditText) findViewById(R.id.dob);
        email= (EditText) findViewById(R.id.editTextEmailAct2);
        fname= (EditText) findViewById(R.id.editTextFirstName);
        lname= (EditText) findViewById(R.id.editTextLastName);
        users=new ArrayList<User>();
        pwd1= (EditText) findViewById(R.id.editTextPasswordAct2);
        pwd2= (EditText) findViewById(R.id.editTextPasswordAct2);
        submit= (Button) findViewById(R.id.btnsubmit);
        mAuth = FirebaseAuth.getInstance();
        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             myCalendar=Calendar.getInstance();
                mYear=myCalendar.get(Calendar.YEAR);
                mMonth=myCalendar.get(Calendar.MONTH);
                mDay=myCalendar.get(Calendar.DAY_OF_MONTH);

            mDatePicker =new DatePickerDialog(SignUpActivity.this, new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                    myCalendar.set(Calendar.YEAR, selectedyear);
                    myCalendar.set(Calendar.MONTH, selectedmonth);
                    myCalendar.set(Calendar.DAY_OF_MONTH, selectedday);
                    String myFormat = "dd/MMM/yyyy"; //In which you need put here
                    SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.UK);

                    dob.setText(sdf.format(myCalendar.getTime()));
                }
            },mYear, mMonth, mDay);
            mDatePicker.setTitle("Select date");
            mDatePicker.show();}
        });
submit.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String firstName =fname.getText().toString();
        String lastName = lname.getText().toString();
        String emailst = email.getText().toString();

        String password =pwd1.getText().toString();
        String confirmPassword =pwd2.getText().toString();
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
            createNewUser(fname.getText().toString()+" " +lname.getText().toString(),email.getText().toString(),dob.getText().toString(),pwd1.getText().toString());


    }
});
    }

    private void createNewUser(final String name, final String email, final String dob, final String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.e("demo", "createAccount: Success!");

                            // update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            //writeNewUser(user.getUid(), getUsernameFromEmail(user.getEmail()), user.getEmail());
                            writeNewUser(user.getUid(),name,user.getEmail(),dob, password);
                            if(user!=null)
                            {
                                for(int i=0;i<users.size();i++)
                                {
                                    if((users.get(i).email).equals(email))
                                    {
                                        uname=users.get(i).getName();
                                    }
                                }

                                Intent i=new Intent(SignUpActivity.this,HomeActivity.class);
                                i.putExtra(UID,user.getUid());
                                i.putExtra(NAME,uname);
                                Log.d("ra_demoUserUid",user.getUid().toString());
                                startActivity(i);
                                Toast.makeText(SignUpActivity.this, "SignUp Successfulllll", Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            Log.e("demo", "createAccount: Fail!", task.getException());
                            Toast.makeText(getApplicationContext(), "Authentication failed!", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    private  void writeNewUser(String userId, String name,String email, String dob, String password)
    {
        User user = new User(userId,name,email,dob,password);

        FirebaseDatabase.getInstance().getReference().child("users").child(userId).setValue(user);
    }
}
