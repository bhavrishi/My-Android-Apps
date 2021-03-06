package com.myonic.rishibhv.group25_hw06;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditProfileActivity extends AppCompatActivity {
    DatabaseReference databaseUsers;
    public static final String UID = "uid";
    public static final String NAME = "name";
    public static final String CLICKEDNAME = "cname";
    public static final String CLICKEDID = "cid";
    String id_main, uname, cname, cid;
    EditText fname, lname, dob, pwd;
    FirebaseAuth mAuth;
    TextView email;
    Button btnsubmit,btnCancel;
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
                Intent i=new Intent(EditProfileActivity.this,MainActivity.class);
                startActivity(i);
                return (true);

        }
        return (super.onOptionsItemSelected(item));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        Intent intent = getIntent();
        id_main = intent.getStringExtra(UID);
        uname = intent.getStringExtra(NAME);
        mAuth=FirebaseAuth.getInstance();
        //  cname = intent.getStringExtra(CLICKEDNAME);
        // cid = intent.getStringExtra(CLICKEDID);
        databaseUsers = FirebaseDatabase.getInstance().getReference("users").child(intent.getStringExtra(UID));
btnsubmit= (Button) findViewById(R.id.btnfinish);
        btnCancel= (Button) findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(EditProfileActivity.this,HomeActivity.class);
                i.putExtra(UID,id_main);
                i.putExtra(NAME,uname);
                startActivity(i);
            }
        });
        ImageView imghome= (ImageView) findViewById(R.id.tv_header_title);
        imghome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(EditProfileActivity.this,HomeActivity.class);
                i.putExtra(UID,id_main);
                i.putExtra(NAME,uname);
                startActivity(i);
            }
        });
        fname = (EditText) findViewById(R.id.editTextFirstName);
        lname = (EditText) findViewById(R.id.editTextLastName);
        email = (TextView) findViewById(R.id.editTextEmailAct2);
        dob = (EditText) findViewById(R.id.dob);
        pwd = (EditText) findViewById(R.id.editTextPasswordAct2);
email.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Toast.makeText(EditProfileActivity.this,"Sorry!! You Can not edit Email",Toast.LENGTH_SHORT).show();
    }
});

        databaseUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // users.clear();
                for (DataSnapshot postsnapshot : dataSnapshot.getChildren()) {
                    if ((postsnapshot.getKey()).equals("dob"))
                        dob.setText(postsnapshot.getValue(String.class));
                    else if ((postsnapshot.getKey()).equals("email"))
                        email.setText(postsnapshot.getValue(String.class));
                    else if ((postsnapshot.getKey()).equals("name")){
                    cname=postsnapshot.getValue(String.class);
                        String[] items = cname.split(" ");
                        fname.setText(items[0]);
                        lname.setText(items[1]);}
                    else if ((postsnapshot.getKey()).equals("password"))
                        pwd.setText(postsnapshot.getValue(String.class));
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeNewUser(id_main,fname.getText().toString(),email.getText().toString(),dob.getText().toString(),pwd.getText().toString());

            }
        });
    }

    private  void writeNewUser(String userId, String name,String email, String dob, String password)
    {
        User user = new User(userId,name,email,dob,password);

        FirebaseDatabase.getInstance().getReference().child("users").child(userId).setValue(user);
    }
}
