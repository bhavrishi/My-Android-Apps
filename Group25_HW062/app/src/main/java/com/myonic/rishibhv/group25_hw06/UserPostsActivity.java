package com.myonic.rishibhv.group25_hw06;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import java.util.Comparator;
import java.util.Date;

public class UserPostsActivity extends AppCompatActivity {
    public static final String UID = "uid";
    public static final String NAME = "name";
    String id_main, uname, cname, cid;
    public static final String CLICKEDNAME = "cname";
    public static final String CLICKEDID = "cid";
    TextView outUname;
    ImageView imgedit;
    DatabaseReference databaseGenericPosts, userrefposts, databaseUsers;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList<Post> posts;
    RecyclerView rv;
    FirebaseAuth mAuth;
    Calendar calander;
    SimpleDateFormat simpledateformat;
    String Date;
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
                Intent i=new Intent(UserPostsActivity.this,MainActivity.class);
                startActivity(i);
                return (true);

        }
        return (super.onOptionsItemSelected(item));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_wall);
        Intent intent = getIntent();
        id_main = intent.getStringExtra(UID);
        uname = intent.getStringExtra(NAME);
        cname = intent.getStringExtra(CLICKEDNAME);
        cid = intent.getStringExtra(CLICKEDID);
        Log.d("democn", cid);
        mAuth=FirebaseAuth.getInstance();
        databaseGenericPosts = FirebaseDatabase.getInstance().getReference("genposts");
        databaseUsers = FirebaseDatabase.getInstance().getReference("userPosts").child(cid);
        userrefposts = FirebaseDatabase.getInstance().getReference("userPosts").child(intent.getStringExtra(UID));
        posts = new ArrayList<Post>();
        outUname= (TextView) findViewById(R.id.outUname);
  //     inputmsg = (EditText) findViewById(R.id.inputpost);
        imgedit = (ImageView) findViewById(R.id.imageView3);

        imgedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(UserPostsActivity.this,EditProfileActivity.class);
                i.putExtra(NAME,uname);
                i.putExtra(UID,id_main);
                i.putExtra(CLICKEDNAME,cname);
                i.putExtra(CLICKEDID,cid);
                startActivity(i);
            }
        });
        rv = (RecyclerView) findViewById(R.id.rv5);
        if (uname.equals(cname)) {
            outUname.setText(uname);
            userrefposts.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    posts.clear();
                    for (DataSnapshot postsnapshot : dataSnapshot.getChildren()) {
                        posts.add(postsnapshot.getValue(Post.class));
                    }
                    mLayoutManager = new LinearLayoutManager(UserPostsActivity.this, LinearLayoutManager.VERTICAL, false);
                    rv.setLayoutManager(mLayoutManager);
                    for (int i = 0; i < posts.size(); i++) {
                        java.util.Date date1 = null, date2 = null;
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
                    WalfSelfAdapter madapter = new WalfSelfAdapter(UserPostsActivity.this, posts);
                    Log.d("demopost", posts.toString());
                    rv.setAdapter(madapter);
                    rv.setHasFixedSize(true);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        } else {
            Intent i= new Intent(UserPostsActivity.this,WallActivity.class);
            i.putExtra(NAME,uname);
            i.putExtra(UID,id_main);
            i.putExtra(CLICKEDNAME,cname);
            i.putExtra(CLICKEDID,cid);
            startActivity(i);
         /*   databaseUsers.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                     posts.clear();
                    for (DataSnapshot postsnapshot : dataSnapshot.getChildren()) {
                        posts.add(postsnapshot.getValue(Post.class));

                    }
                    mLayoutManager = new LinearLayoutManager(UserPostsActivity.this, LinearLayoutManager.VERTICAL, false);
                    rv.setLayoutManager(mLayoutManager);

                    FriendsWallAdapter madapter = new FriendsWallAdapter(UserPostsActivity.this, posts);
                    Log.d("demopost", posts.toString());
                    rv.setAdapter(madapter);
                    rv.setHasFixedSize(true);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            FriendsWallAdapter madapter = new FriendsWallAdapter(UserPostsActivity.this, posts);
            Log.d("demopost", posts.toString());
            rv.setAdapter(madapter);
            rv.setHasFixedSize(true);*/
        }


    }

    private void saveToPostsDB(String s, String time) {
        String id = databaseGenericPosts.push().getKey();
        //  String id2 = userrefposts.push().getKey();
        Post post = new Post(id,id_main, uname, s, time);

        Log.d("demo", uname);
        databaseGenericPosts.child(id).setValue(post);
        userrefposts.child(id).setValue(post);

        Toast.makeText(this, "Contact added", Toast.LENGTH_LONG).show();

    }

    public void callDeleteMethod(final String id, final int position) {
        AlertDialog.Builder alert = new AlertDialog.Builder(UserPostsActivity.this);
        alert.setTitle("Delete");
        alert.setMessage("Are you sure you want to delete?");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                DatabaseReference dR = FirebaseDatabase.getInstance().getReference("userPosts").child(id_main).child(id);
                DatabaseReference dRgen = FirebaseDatabase.getInstance().getReference("genposts").child(id);
                //removing artist
                dR.removeValue();
                dRgen.removeValue();
                posts.remove(position);
                for (int i = 0; i < posts.size(); i++) {
                    java.util.Date date1 = null, date2 = null;
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

                WalfSelfAdapter madapter = new WalfSelfAdapter(UserPostsActivity.this, posts);

                rv.setAdapter(madapter);
                rv.setHasFixedSize(true);

                //getting the tracks reference for the specified artist

                Toast.makeText(getApplicationContext(), "Artist Deleted", Toast.LENGTH_LONG).show();


                dialog.dismiss();
            }
        });

        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alert.show();


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
