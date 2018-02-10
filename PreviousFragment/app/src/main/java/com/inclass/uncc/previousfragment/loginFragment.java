package com.inclass.uncc.previousfragment;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;


public class loginFragment extends Fragment {

     OnFragmentInteractionListener mlistener;

    public loginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menu.clear();
        menuInflater.inflate(R.menu.menu, menu);
        super.onCreateOptionsMenu(menu, menuInflater);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_home:
                // TODO put your code here to respond to the button tap
                Toast.makeText(getActivity(), "ADD!", Toast.LENGTH_SHORT).show();
                mlistener.gotocomposefrag();
                return true;
            case R.id.item_refresh:
                // TODO put your code here to respond to the button tap
                Toast.makeText(getActivity(), "ADD!", Toast.LENGTH_SHORT).show();
                mlistener.gotolistfrag();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final EditText name=getView().findViewById(R.id.inputname);
        final EditText pwd=getView().findViewById(R.id.inputpwd);
        getView().findViewById(R.id.btnlogn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mlistener.onFragmentInteraction(name.getText().toString(),pwd.getText().toString());
            }
        });
        getActivity().findViewById(R.id.btnsignup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mlistener.gotonextfrag();
            }
        });
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(String name, String pwd);
     void   gotonextfrag();

        void gotocomposefrag();

        void onFragmentMsg(String s);


        void gotolistfrag();
    }
}
