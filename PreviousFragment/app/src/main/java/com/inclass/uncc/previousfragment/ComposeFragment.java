package com.inclass.uncc.previousfragment;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


public class ComposeFragment extends Fragment {
OnFragmentInteractionListener mlistener;
    public ComposeFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_compose, container, false);
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mlistener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString());
        }
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final EditText name=getView().findViewById(R.id.inputmsg);

        getView().findViewById(R.id.btnsend).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mlistener.onFragmentMsg(name.getText().toString());
            }
        });

    }

    public interface OnFragmentInteractionListener {

        void onFragmentMsg(String name);



    }
}
