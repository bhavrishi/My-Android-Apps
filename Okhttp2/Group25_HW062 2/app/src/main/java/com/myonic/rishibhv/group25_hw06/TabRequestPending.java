package com.myonic.rishibhv.group25_hw06;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

public class TabRequestPending extends Fragment {
    public static final String UID = "uid";
    ArrayList<User> tempList;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    //ArrayList<Post> posts;
    //ArrayList<Friends> friends;
    ArrayList<User> userSentRequest ;


    RecyclerView rv;
    DatabaseReference databaseUsers, dataBaseRequestPending,databaseUser,databaseSentRequest;
    FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_requests_pending_tab, container, false);
        /*TextView textView = (TextView) rootView.findViewById(R.id.section_label);
        textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));*/
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mAuth=FirebaseAuth.getInstance();

        dataBaseRequestPending =FirebaseDatabase.getInstance().getReference("userFriends").child(getActivity().getIntent().getStringExtra(UID)).child("RequestPending");
        databaseSentRequest =FirebaseDatabase.getInstance().getReference("userFriends").child(getActivity().getIntent().getStringExtra(UID)).child("SentRequests");

        databaseSentRequest.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userSentRequest =new ArrayList<>();
               userSentRequest.clear();
                for (DataSnapshot data:dataSnapshot.getChildren()
                        ) {
                    userSentRequest.add(data.getValue(User.class));
                }
                for (User u:userSentRequest
                     ) {
                    u.setPassword("Sent");
                }
                sendDataToRequestPendingAdapter(userSentRequest);
                Log.d("demo_raz,","In Adapter _BeforeActivityCheck");

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }

    public void sendDataToRequestPendingAdapter(final ArrayList<User> usersRqtsPending){
        tempList=new ArrayList<User>();

        dataBaseRequestPending.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
              //  usersRqtsPending.clear();
                Log.d("demourp",usersRqtsPending.toString());
                tempList.clear();
                for(int i=0;i<usersRqtsPending.size();i++)
                    tempList.add(usersRqtsPending.get(i));
                Log.d("demotemp",tempList.toString());
                //usersRqtsPending.clear();

                Log.d("demourp",usersRqtsPending.toString());
                Log.d("demotemp",tempList.toString());
                for (DataSnapshot data:dataSnapshot.getChildren()
                        ) {
                    tempList.add(data.getValue(User.class));
                }
                if(getActivity()!=null) {
                    Log.d("demo_raz,","In Adapter _ActivityCheck");
                    rv = (RecyclerView) getActivity().findViewById(R.id.recycleViewRequestPendingTab);
                    mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                    rv.setLayoutManager(mLayoutManager);
                    rv.removeAllViews();
                    RequestPendingAdapter madapter = new RequestPendingAdapter(getActivity().getApplicationContext(), tempList);


                    rv.setAdapter(madapter);
                    rv.setHasFixedSize(true);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //usersRqtsPending.clear();
    }
}
