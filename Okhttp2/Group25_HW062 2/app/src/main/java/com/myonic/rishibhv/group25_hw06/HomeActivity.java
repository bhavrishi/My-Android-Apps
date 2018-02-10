package com.myonic.rishibhv.group25_hw06;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Comparator;

public class HomeActivity extends AppCompatActivity {
    DatabaseReference databaseGenericPosts, userrefposts, databaseUsers;
    ImageView friendsTab;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList<Post> posts;
    RecyclerView rv;
    String id_main, uname;
    User users;
    EditText inputmsg;
    ImageView imgpost;
    TextView profilename;
    public static final String UID = "uid";
    public static final String NAME = "name";
    public static final String CLICKEDNAME = "cname";
    public static final String CLICKEDID = "cid";
    Calendar calander;
    SimpleDateFormat simpledateformat;
    private FirebaseAuth mAuth;
    FirebaseUser fUser;
    String Date;

    @Override
    protected void onStart() {
        super.onStart();
        databaseUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // users.clear();
                for (DataSnapshot postsnapshot : dataSnapshot.getChildren()) {
                    if ((postsnapshot.getKey()).equals("name")) {
                        uname = postsnapshot.getValue(String.class);
                        profilename.setText(uname);
                        Log.d("demot", postsnapshot.getKey() + uname);
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
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
                Intent i=new Intent(HomeActivity.this,MainActivity.class);
                startActivity(i);
                return (true);

        }
        return (super.onOptionsItemSelected(item));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        final Intent intent = getIntent();
        id_main = intent.getStringExtra(UID);
        // uname=intent.getStringExtra(NAME);
//          Log.d("demo",uname);
        databaseGenericPosts = FirebaseDatabase.getInstance().getReference("genposts");
        databaseUsers = FirebaseDatabase.getInstance().getReference("users").child(intent.getStringExtra(UID));
        userrefposts = FirebaseDatabase.getInstance().getReference("userPosts").child(intent.getStringExtra(UID));
        inputmsg = (EditText) findViewById(R.id.inputpost);
mAuth=FirebaseAuth.getInstance();
    fUser=mAuth.getCurrentUser();
        databaseUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // users.clear();
                for (DataSnapshot postsnapshot : dataSnapshot.getChildren()) {
                    if ((postsnapshot.getKey()).equals("name")) {
                        uname = postsnapshot.getValue(String.class);
                        profilename.setText(uname);
                        Log.d("demot", postsnapshot.getKey() + uname);
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        profilename = (TextView) findViewById(R.id.outUname);
        profilename.setFocusable(true);
        profilename.setVisibility(View.VISIBLE);
        profilename.setText(uname);
//       Log.d("uname",uname);
        profilename.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, ProfileDisplay.class);
                i.putExtra(NAME, uname);
                Log.d("uuname",uname);
                i.putExtra(UID, id_main);
                startActivity(i);

            }
        });
        posts = new ArrayList<Post>();
        users = new User();
        rv = (RecyclerView) findViewById(R.id.rv3);
        imgpost = (ImageView) findViewById(R.id.imageView2);
       /* friends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("demo","friends clicked");
            }
        });*/
        imgpost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inputmsg.getText().toString().length() > 200) {
                    Toast.makeText(HomeActivity.this, "Please Post with in 200 Char Range", Toast.LENGTH_SHORT).show();
                } else {
                    calander = Calendar.getInstance();
                    simpledateformat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
                    Date = simpledateformat.format(calander.getTime());
                    saveToPostsDB(inputmsg.getText().toString(), Date);
                }
            }
        });
        databaseGenericPosts.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                posts.clear();
                for (DataSnapshot postsnapshot : dataSnapshot.getChildren()) {
                    posts.add(postsnapshot.getValue(Post.class));
                }
                for (int i = 0; i < posts.size(); i++) {
                    Date date1 = null, date2 = null;
                    String datetemp;
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
                    datetemp = dateFormat.format(Calendar.getInstance().getTime());
                    try {
                        date1 = dateFormat.parse(posts.get(i).getTime());
                        date2 = dateFormat.parse(datetemp);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    long diff = date2.getTime() - date1.getTime();
                    long diffHours = diff / (60 * 60 * 1000) % 24;
                    if (diffHours > 24)
                        posts.remove(i);
                }
                Collections.sort(posts, new CustomComparator());
                mLayoutManager = new LinearLayoutManager(HomeActivity.this, LinearLayoutManager.VERTICAL, false);
                rv.setLayoutManager(mLayoutManager);
                CustomAdapter madapter = new CustomAdapter(HomeActivity.this, posts);

                rv.setAdapter(madapter);
                rv.setHasFixedSize(true);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        friendsTab =(ImageView) findViewById(R.id.tv_header_title);
        friendsTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tabIntent = new Intent(HomeActivity.this,FriendsTabActivity.class);
                tabIntent.putExtra(NAME, uname);
                tabIntent.putExtra(UID, id_main);
                Log.d("Demo_Rosy_HomeAct",id_main);
                startActivity(tabIntent);
            }
        });


    }

    public void callMethod(String clickedname, String id) {
        Log.d("demo", "Call method");
        Intent i = new Intent(HomeActivity.this, UserPostsActivity.class);
        i.putExtra(NAME, uname);
        i.putExtra(UID, id_main);
        Log.d("clickedname",clickedname);
        i.putExtra(CLICKEDNAME, clickedname);
        i.putExtra(CLICKEDID, id);
        startActivity(i);
    }

    private void saveToPostsDB(String s, String time) {
        String id = databaseGenericPosts.push().getKey();
        //    String id2 = userrefposts.push().getKey();
        Post post = new Post(id, id_main, uname, s, time);

        Log.d("demo", uname);
        databaseGenericPosts.child(id).setValue(post);
        userrefposts.child(id).setValue(post);
        // editTextName.setText("");

        //displaying a success toast
        Toast.makeText(this, "Contact added", Toast.LENGTH_LONG).show();
      /*  Intent i = new Intent(ContactDetailsActivity.this, DisplayActivity.class);
        i.putExtra(UID, main_id);
        //i.putExtra("ALIST",contacts);
        startActivity(i);*/
    }

    public class CustomComparator implements Comparator<Post> {
        Date date1, date2;

        @Override
        public int compare(Post obj1, Post obj2) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");

            try {
                date1 = dateFormat.parse(obj1.getTime());
                date2 = dateFormat.parse(obj2.getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return date2.compareTo(date1);// compare two objects
        }
    }
}
