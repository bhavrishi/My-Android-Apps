package com.myonic.rishibhv.group25_hw06;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by rosyazad on 19/11/17.
 */

public class TabAddTheFriends  extends Fragment {

    public static final String UID = "uid";

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    //ArrayList<Post> posts;
    ArrayList<Friends> friends;
    ArrayList<User> allFriends;
    ArrayList<User> users = new ArrayList<>();
    RecyclerView rv;
    DatabaseReference databaseUsers, dataBaseFriend,databaseUser,sentRequest,databasePendingRequest,databaseAllFriends;
    FirebaseAuth mAuth;
    ArrayList<User> sentRequestUsers;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_new_friend_tab, container, false);
        /*TextView textView = (TextView) rootView.findViewById(R.id.section_label);
        textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));*/
        return rootView;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mAuth=FirebaseAuth.getInstance();
        databaseUsers = FirebaseDatabase.getInstance().getReference("users");
        databaseUser = FirebaseDatabase.getInstance().getReference("users").child(getActivity().getIntent().getStringExtra(HomeActivity.UID));
        dataBaseFriend = FirebaseDatabase.getInstance().getReference("userFriends").child(getActivity().getIntent().getStringExtra(HomeActivity.UID)).child("AddFriend");
        //final DatabaseReference databasePendingRequest =FirebaseDatabase.getInstance().getReference("userFriends").child(id).child("RequestPending");
        sentRequest =FirebaseDatabase.getInstance().getReference("userFriends").child(getActivity().getIntent().getStringExtra(HomeActivity.UID)).child("SentRequests");

        //databaseAllFriends=FirebaseDatabase.getInstance().getReference("userFriends").child(getActivity().getIntent().getStringExtra(HomeActivity.UID)).child("AllFriends");

        rv =(RecyclerView)getActivity().findViewById(R.id.recycleViewAddNewFriend);

        sentRequestUsers = new ArrayList<>();





        //if sentRequest.
        sentRequest.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data:dataSnapshot.getChildren()
                        ) {
                    sentRequestUsers.add(data.getValue(User.class));
                }

                //sentDataToAdapter(sentRequestUsers);

                sentRecieveUsersData(sentRequestUsers);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        dataBaseFriend.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<User> userAddFriends = new ArrayList<User>();
                for (DataSnapshot data:dataSnapshot.getChildren()
                        ) {
                    userAddFriends.add(data.getValue(User.class));
                }
                if(getActivity()!=null) {
                    mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                    rv.setLayoutManager(mLayoutManager);
                    AddNewFriendsAdapter madapter = new AddNewFriendsAdapter(getActivity().getApplicationContext(), userAddFriends);


                    Log.d("demopost", userAddFriends.toString());
                    rv.setAdapter(madapter);
                    madapter.notifyDataSetChanged();
                    rv.setHasFixedSize(true);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }

    public void sentRecieveUsersData (final ArrayList<User> sentRequestUsers){
        //final ArrayList<User> sentReceiveUsers = new ArrayList<>();
        if(getActivity()!=null) {
            databasePendingRequest = FirebaseDatabase.getInstance().getReference("userFriends").child(getActivity().getIntent().getStringExtra(HomeActivity.UID)).child("RequestPending");
            databasePendingRequest.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot data : dataSnapshot.getChildren()
                            ) {
                            sentRequestUsers.add(data.getValue(User.class));
                    }
                    allFriendsUsersData(sentRequestUsers);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }


    }

    public void allFriendsUsersData (final ArrayList<User> sentRequestUsers){
        if(getActivity()!=null) {
            databaseAllFriends=FirebaseDatabase.getInstance().getReference("userFriends").child(getActivity().getIntent().getStringExtra(HomeActivity.UID)).child("AllFriends");
            databaseAllFriends.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot data : dataSnapshot.getChildren()
                            ) {
                        sentRequestUsers.add(data.getValue(User.class));
                    }
                    sentDataToAdapter(sentRequestUsers);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }


    }

    public void sentDataToAdapter(final ArrayList<User> sentRequestUsers){
        databaseUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                users = new ArrayList<User>();
                for (DataSnapshot data:dataSnapshot.getChildren()
                        ) {

                    users.add(data.getValue(User.class));
                    Log.d("demoRosy",users.toString());

                }
                /* for(int i =0; i<users.size();i++){
                    *//*Friends f = new Friends();
                    f.setAddFriend(users.get(i).getId());*//*
                    if(users.get(i).getId().equals(getActivity().getIntent().getStringExtra(HomeActivity.UID)))
                        users.remove(i);

                    if(!users.get(i).getId().equals(getActivity().getIntent().getStringExtra(HomeActivity.UID)))
                        dataBaseFriend.child(users.get(i).getId()).setValue(users.get(i));
                }
                for(int i =0; i<users.size();i++) {
                    for (int j = 0; j < sentRequestUsers.size(); j++) {
                        if (users.get(i).getId().equals(sentRequestUsers.get(j).getId())){
                            users.remove(i);
                        }
                    }
                }*/

                if (getActivity() != null) {
                ArrayList<User> userToremove =new ArrayList<User>();


                for (User user:users
                        ) {

                    for (User sentReqUser : sentRequestUsers
                            ) {
                        if (user.getId().equals(sentReqUser.getId()))
                            userToremove.add(user);
                        //users.remove(user);


                    }
                        if (user.getId().equals((getActivity().getIntent().getStringExtra(HomeActivity.UID))))
                            userToremove.add(user);

                        //users.remove(user);

                   /* if(!user.getId().equals(getActivity().getIntent().getStringExtra(HomeActivity.UID)))
                        dataBaseFriend.child(user.getId()).setValue(user);*/

                    }
                    users.removeAll(userToremove);

                    for (User user : users
                            ) {
                        dataBaseFriend.child(user.getId()).setValue(user);
                    }

                    mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                    rv.setLayoutManager(mLayoutManager);
                    AddNewFriendsAdapter madapter = new AddNewFriendsAdapter(getActivity().getApplicationContext(), users);

Log.d("demousers",users.toString());
                    //Log.d("demopost", users.toString());
                  //  rv.setAdapter(madapter);
                    rv.setHasFixedSize(true);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    @Override
    public void onStart() {
        super.onStart();
        dataBaseFriend.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<User> userAddFriends = new ArrayList<User>();
                for (DataSnapshot data:dataSnapshot.getChildren()
                        ) {
                    userAddFriends.add(data.getValue(User.class));
                }
                if(getActivity()!=null) {
                    mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                    rv.setLayoutManager(mLayoutManager);
                    AddNewFriendsAdapter madapter = new AddNewFriendsAdapter(getActivity().getApplicationContext(), userAddFriends);
Log.d("demofrag",userAddFriends.toString());

                    //Log.d("demopost", users.toString());
                    rv.setAdapter(madapter);
                    madapter.notifyDataSetChanged();
                    rv.setHasFixedSize(true);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
