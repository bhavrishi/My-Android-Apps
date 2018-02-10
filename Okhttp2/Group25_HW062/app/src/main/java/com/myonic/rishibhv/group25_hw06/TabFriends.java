package com.myonic.rishibhv.group25_hw06;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

public class TabFriends extends Fragment {
    public static final String UID = "uid";

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    RecyclerView rv;


    DatabaseReference allFriendsDatabase;
    FirebaseAuth mAuth;
    ArrayList<User> allUsers = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_friends_tab, container, false);
        /*TextView textView = (TextView) rootView.findViewById(R.id.section_label);
        textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));*/
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        allFriendsDatabase = FirebaseDatabase.getInstance().getReference("userFriends").child(getActivity().getIntent().getStringExtra(UID)).child("AllFriends");

        allFriendsDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                allUsers.clear();
                for (DataSnapshot data:dataSnapshot.getChildren()
                        ) {
                    allUsers.add(data.getValue(User.class));
                }
                if(getActivity()!=null) {
                    RecyclerView rv;

                    Log.d("demo_raz,","In Adapter _ActivityCheck");
                    rv = (RecyclerView) getActivity().findViewById(R.id.recycleViewFriendsTab);
                    if(rv !=null) {
                        mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                        rv.setLayoutManager(mLayoutManager);
                        AllFriendsAdapter madapter = new AllFriendsAdapter(getActivity().getApplicationContext(), allUsers);


                        rv.setAdapter(madapter);
                        rv.setHasFixedSize(true);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
