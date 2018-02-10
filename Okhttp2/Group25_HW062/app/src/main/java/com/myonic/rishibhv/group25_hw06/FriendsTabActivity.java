package com.myonic.rishibhv.group25_hw06;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

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

public class FriendsTabActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    ArrayList<User> users =new ArrayList<>();
    ArrayList<User> allFriends = new ArrayList<>();

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;
    public static final String UID = "uid";String id_main;

/*    @Override
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
                Intent i=new Intent(FriendsTabActivity.this,MainActivity.class);
                startActivity(i);
                return (true);

        }
        return (super.onOptionsItemSelected(item));
    }*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_tab);
        Intent intent = getIntent();
        id_main = intent.getStringExtra(UID);
ImageView imghome= (ImageView) findViewById(R.id.tv_header_title);
     imghome.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
           Intent i=new Intent(FriendsTabActivity.this,HomeActivity.class);
             i.putExtra(UID,id_main);
             startActivity(i);
         }
     });
mAuth=FirebaseAuth.getInstance();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

    }

/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_friends_tab, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

   //deleted PlaceholderFragment

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            //return PlaceholderFragment.newInstance(position + 1);
            switch(position){
                case 0:
                    TabFriends tabFriends =new TabFriends();
                    return tabFriends;
                case 1:
                    TabAddTheFriends tabAddTheFriends=new TabAddTheFriends();
                    return tabAddTheFriends;
                case 2:
                    TabRequestPending tabRequestPending= new TabRequestPending();
                    return tabRequestPending;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Friends";
                case 1:
                    return "Add New Friend";
                case 2:
                    return "Requests Pending";
            }
            return null;
        }
    }

    public void callAddDeleteMethod(final String id, final int position, final User user) {


        mAuth=FirebaseAuth.getInstance();
        final DatabaseReference databaseUsers = FirebaseDatabase.getInstance().getReference("users");
        final DatabaseReference dataBaseFriend = FirebaseDatabase.getInstance().getReference("userFriends").child(getIntent().getStringExtra(HomeActivity.UID)).child("AddFriend").child(id);
        dataBaseFriend.removeValue();
        //final DatabaseReference sentRequest =FirebaseDatabase.getInstance().getReference("userFriends").child(getIntent().getStringExtra(HomeActivity.UID)).child("SentRequests");
//DatabaseReference userToAddinRqstPending = FirebaseDatabase.getInstance().getReference("users").child(getIntent().getStringExtra(HomeActivity.UID));



        //final DatabaseReference databasePendingRequest =FirebaseDatabase.getInstance().getReference("userFriends").child(id).child("RequestPending");
        //databasePendingRequest.child((id)
        databaseUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data:dataSnapshot.getChildren()
                     ) {
                    users.add(data.getValue(User.class));
                }
                for (User userPendingRqt:users
                     ) {
                    if(userPendingRqt.getId().equals(getIntent().getStringExtra(HomeActivity.UID))){
                        addUserToPendingRequest(userPendingRqt,id,user);
                    }

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }

    public void onRejectRequest(final String id, final User userToAdd){
        final ArrayList<User> userList = new ArrayList<>();
        mAuth=FirebaseAuth.getInstance();

        DatabaseReference databasePendingDelete = FirebaseDatabase.getInstance().getReference("userFriends").child(getIntent().getStringExtra(HomeActivity.UID)).child("RequestPending").child(id);

        databasePendingDelete.removeValue();

        DatabaseReference databasePendingDeleteForSendingUsr = FirebaseDatabase.getInstance().getReference("userFriends").child(getIntent().getStringExtra(HomeActivity.UID)).child("SentRequests").child(id);
        databasePendingDeleteForSendingUsr.removeValue();

        DatabaseReference sentRequest =FirebaseDatabase.getInstance().getReference("userFriends").child(id).child("SentRequests").child(getIntent().getStringExtra(HomeActivity.UID));
        sentRequest.removeValue();

        final DatabaseReference databaseAddToFriendTab = FirebaseDatabase.getInstance().getReference("userFriends").child(getIntent().getStringExtra(HomeActivity.UID)).child("AddFriend");
        databaseAddToFriendTab.child(id).setValue(userToAdd);

        final DatabaseReference databaseAddFriendTabOfSendingUser = FirebaseDatabase.getInstance().getReference("userFriends").child(id).child("AddFriend");
        //databaseFriendOfSendingUser.child(getIntent().getStringExtra(HomeActivity.UID)).setValue();


        final DatabaseReference databaseUsers =FirebaseDatabase.getInstance().getReference("users");




        databaseUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data:dataSnapshot.getChildren()
                        ) {
                    userList.add(data.getValue(User.class));
                }
                for (User user:userList
                        ) {
                    if(user.getId().equals(getIntent().getStringExtra(HomeActivity.UID))){
                        databaseAddFriendTabOfSendingUser.child(getIntent().getStringExtra(HomeActivity.UID)).setValue(user);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }

    public void onAcceptRequest(final String id, final User userToAdd){
        final ArrayList<User> userList =new ArrayList<>();
        mAuth=FirebaseAuth.getInstance();
        DatabaseReference databasePendingDelete = FirebaseDatabase.getInstance().getReference("userFriends").child(getIntent().getStringExtra(HomeActivity.UID)).child("RequestPending").child(id);
        databasePendingDelete.removeValue();
        final DatabaseReference databaseFriends = FirebaseDatabase.getInstance().getReference("userFriends").child(getIntent().getStringExtra(HomeActivity.UID)).child("AllFriends");
        databaseFriends.child(id).setValue(userToAdd);
        final DatabaseReference sentRequest =FirebaseDatabase.getInstance().getReference("userFriends").child(id).child("SentRequests").child(getIntent().getStringExtra(HomeActivity.UID));
        sentRequest.removeValue();
        final DatabaseReference databaseFriendOfSendingUser = FirebaseDatabase.getInstance().getReference("userFriends").child(id).child("AllFriends");
        //databaseFriendOfSendingUser.child(getIntent().getStringExtra(HomeActivity.UID)).setValue();


        final DatabaseReference databaseUsers =FirebaseDatabase.getInstance().getReference("users");


        databaseUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data:dataSnapshot.getChildren()
                     ) {
                    userList.add(data.getValue(User.class));
                }
                for (User user:userList
                     ) {
                    if(user.getId().equals(getIntent().getStringExtra(HomeActivity.UID))){
                        databaseFriendOfSendingUser.child(getIntent().getStringExtra(HomeActivity.UID)).setValue(user);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        databaseFriends.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data:dataSnapshot.getChildren()
                     ) {
                    allFriends.add(data.getValue(User.class));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void addUserToPendingRequest(final User mUsers, final String id, final User user){
        final DatabaseReference databasePendingRequest =FirebaseDatabase.getInstance().getReference("userFriends").child(id).child("RequestPending");
        final DatabaseReference sentRequest =FirebaseDatabase.getInstance().getReference("userFriends").child(getIntent().getStringExtra(HomeActivity.UID)).child("SentRequests");
        databasePendingRequest.child(getIntent().getStringExtra(HomeActivity.UID)).setValue(mUsers);
        sentRequest.child(id).setValue(user);


    }
}
