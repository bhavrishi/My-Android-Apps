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

public class WallActivity extends AppCompatActivity {
    public static final String UID = "uid";
    public static final String NAME = "name";
    String id_main, uname, cname, cid;
    public static final String CLICKEDNAME = "cname";
    public static final String CLICKEDID = "cid";
    TextView outname;
    ImageView imgpost;
    DatabaseReference databaseGenericPosts, userrefposts, databaseUsers;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList<Post> posts;
    RecyclerView rv;
    FirebaseAuth mAuth;
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
                Intent i=new Intent(WallActivity.this,MainActivity.class);
                startActivity(i);
                return (true);

        }
        return (super.onOptionsItemSelected(item));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wall);
        Intent intent = getIntent();
        id_main = intent.getStringExtra(UID);
        uname = intent.getStringExtra(NAME);
        cname = intent.getStringExtra(CLICKEDNAME);
        Log.d("click3",cname+uname);
        cid = intent.getStringExtra(CLICKEDID);
        Log.d("democn", cid);
        mAuth=FirebaseAuth.getInstance();
        databaseGenericPosts = FirebaseDatabase.getInstance().getReference("genposts");
        databaseUsers = FirebaseDatabase.getInstance().getReference("userPosts").child(cid);
        userrefposts = FirebaseDatabase.getInstance().getReference("userPosts").child(intent.getStringExtra(UID));
        posts = new ArrayList<Post>();
        outname= (TextView) findViewById(R.id.outUname);
        rv= (RecyclerView) findViewById(R.id.rv5);
        outname.setText(cname);

        ImageView imghome= (ImageView) findViewById(R.id.tv_header_title);
        imghome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(WallActivity.this,HomeActivity.class);
                i.putExtra(UID,id_main);
                i.putExtra(NAME,uname);
                startActivity(i);
            }
        });
      TextView  bobposts= (TextView) findViewById(R.id.txtpersonname);
        bobposts.setText(cname+"'s "+"posts");
        databaseUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                posts.clear();
                for (DataSnapshot postsnapshot : dataSnapshot.getChildren()) {
                    posts.add(postsnapshot.getValue(Post.class));

                }
                mLayoutManager = new LinearLayoutManager(WallActivity.this, LinearLayoutManager.VERTICAL, false);
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

                FriendsWallAdapter madapter = new FriendsWallAdapter(WallActivity.this, posts);
                Log.d("demopost", posts.toString());
                rv.setAdapter(madapter);
                rv.setHasFixedSize(true);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
      /*  FriendsWallAdapter madapter = new FriendsWallAdapter(WallActivity.this, posts);
        Log.d("demopost", posts.toString());
        rv.setAdapter(madapter);
        rv.setHasFixedSize(true);*/
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
