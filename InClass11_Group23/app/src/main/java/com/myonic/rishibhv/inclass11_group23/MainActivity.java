package com.myonic.rishibhv.inclass11_group23;
/*Assignment Name:InClass11_Group23
Group 23
Full Names: Rosy Azad, Bhavya Gedi
 */
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    public static final int INTER = 3001;
    public static final String UID = "uid";
    public static final String NAME = "name";
    String uname = null;
    GoogleApiClient mGoogleApiClient;
    EditText email, pwd;
    DatabaseReference databaseUsers, databaseName;
    TextView signup;
    private FirebaseAuth mAuth;
    Button btn2;
    TextView txt;
    ArrayList<User> users;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == INTER) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handlesigninrresult(result);
        }
    }

    private void handlesigninrresult(GoogleSignInResult result) {
        Log.d("demosuccess", "" + result.isSuccess());
        final String[] tempuser = {null};
        if (result.isSuccess()) {
            final GoogleSignInAccount gaccnt = result.getSignInAccount();
            Log.d("demo", gaccnt.getDisplayName() + " " + gaccnt.getId() + "" + gaccnt.getEmail());

            databaseUsers.child(gaccnt.getId()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                   // tempUsers.clear();
                    for (DataSnapshot postsnapshot : dataSnapshot.getChildren()) {
                        if(postsnapshot.getKey().equals("name"))
                        {
                            tempuser[0] =postsnapshot.getValue(String.class);
                            if(tempuser[0].equals(gaccnt.getDisplayName())) {
                                Log.d("demot", tempuser[0]);

                                User user = new User(gaccnt.getId(), gaccnt.getDisplayName(), gaccnt.getEmail());
                                databaseUsers.child(gaccnt.getId()).setValue(user);
                            }
                        }}
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            //  String id = databaseUsers.push().getKey();
          // User user = new User(gaccnt.getId(),gaccnt.getDisplayName(), gaccnt.getEmail());
            //databaseUsers.child(gaccnt.getId()).setValue(user);

            Intent i = new Intent(MainActivity.this, Display_Contacts_Activity.class);
            i.putExtra(Constants.UID, gaccnt.getId());
            i.putExtra(Constants.NAME, gaccnt.getDisplayName());
            Log.d("demo", gaccnt.getId() + uname);
            startActivity(i);
            //displaying a success toast
            Toast.makeText(this, "User Login Added to DB", Toast.LENGTH_LONG).show();

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseUsers = FirebaseDatabase.getInstance().getReference("users");
        mAuth = FirebaseAuth.getInstance();
        final FirebaseUser userAlreadyLoggedIn = mAuth.getCurrentUser();
        email = (EditText) findViewById(R.id.lgUName);
     /*   ValueEventListener postListener = new ValueEventListener() {
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
        };
        databaseUsers.addValueEventListener(postListener);
*/
        final int[] test = {0};
        if (userAlreadyLoggedIn != null) {
            Log.d("demona",userAlreadyLoggedIn.getUid()+" "+userAlreadyLoggedIn.getProviderData().toString());
            for(UserInfo profile:userAlreadyLoggedIn.getProviderData())
            {
                uname = profile.getDisplayName();
                Log.d("demoname", userAlreadyLoggedIn.getUid() + uname);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                databaseUsers.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        users.clear();

                        for (DataSnapshot postsnapshot : dataSnapshot.getChildren()) {

                            users.add(postsnapshot.getValue(User.class));}
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });}
            Log.d("test", "Test = " + test[0]);
           /* Query deleteQuery = databaseUsers.orderByChild("id").equalTo(userAlreadyLoggedIn.getUid());
            deleteQuery.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    Iterable<DataSnapshot> snapshotIterator = dataSnapshot.getChildren();
                    Iterator<DataSnapshot> iterator = snapshotIterator.iterator();
                    while((iterator.hasNext())){
                        Log.d("Item found: ",iterator.next().getValue().toString()+"---");
                    }
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.d("Item not found: ","this item is not in the list");
                }
            });*/
       /* if (userAlreadyLoggedIn != null) {*/
           /* for (int i = 0; i < users.size(); i++) {
                if ((users.get(i).getEmail()).equals(email.getText().toString())) {
                    uname = users.get(i).getName();
                }
            }*/

            Intent i = new Intent(MainActivity.this, Display_Contacts_Activity.class);
            i.putExtra(Constants.UID, userAlreadyLoggedIn.getUid());

            //  i.putExtra(NAME, uname);
            Log.d("demo", userAlreadyLoggedIn.getUid() + uname);
            startActivity(i);
            Toast.makeText(MainActivity.this, "Login Successfulllll", Toast.LENGTH_SHORT).show();
        }
        signup = (TextView) findViewById(R.id.lgsignup);

        pwd = (EditText) findViewById(R.id.lgpwd);
        Button btnLogin = (Button) findViewById(R.id.btnlogin);
        users = new ArrayList<User>();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        mGoogleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, this).addApi(Auth.GOOGLE_SIGN_IN_API, gso).build();
        // txt= (TextView) findViewById(R.id.textView);
        SignInButton btnsignin = (SignInButton) findViewById(R.id.btngoogle);
        btnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInt = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(signInt, INTER);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailst = email.getText().toString();
                String passwordst = pwd.getText().toString();
                if (emailst.equals("")) {
                    Toast.makeText(MainActivity.this, "Please enter email to login", Toast.LENGTH_SHORT).show();
                } else if (passwordst.equals("")) {
                    Toast.makeText(MainActivity.this, "Please enter password to log in", Toast.LENGTH_SHORT).show();
                } else
                    signIn(emailst, passwordst);

            }
        });


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(i);
            }
        });

    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                //add the function to perform here
                return (true);

        }
        return (super.onOptionsItemSelected(item));
    }*/

    @Override
    protected void onStart() {
        super.onStart();
        databaseUsers.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                users.clear();
                for (DataSnapshot postsnapshot : dataSnapshot.getChildren()) {

                    users.add(postsnapshot.getValue(User.class));}
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
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
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d("demofailed", "" + connectionResult);
    }

    public void signIn(final String email, String password) {

        Log.e("demo", "signIn:" + email);
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Log.e("demo", "signIn: Success!");

                    // update UI with the signed-in user's information
                    FirebaseUser user = mAuth.getCurrentUser();
                    if (user != null) {
                        for (int i = 0; i < users.size(); i++) {
                            if ((users.get(i).email).equals(email)) {
                                uname = users.get(i).getName();
                            }
                        }

                        Intent i = new Intent(MainActivity.this, Display_Contacts_Activity.class);
                        i.putExtra(Constants.UID, user.getUid());
                        i.putExtra(NAME, uname);
                        Log.d("demo", user.getUid() + uname);
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