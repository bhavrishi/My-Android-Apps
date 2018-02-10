package com.hw.uncc.group25_hw06;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.support.annotation.StringDef;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class LoginFragment extends Fragment {
DBManager dm; String key,value;
    private OnFragmentInteractionListener mListener;

    public LoginFragment() {
        // Required empty public constructor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        dm=new DBManager(getActivity());
        return inflater.inflate(R.layout.fragment_login, container, false);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
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
                Toast.makeText(getActivity(), "Please log in/Sign Up to Continue", Toast.LENGTH_SHORT).show();
              //  mlistener.gotocomposefrag();
                return true;
            case R.id.item_inst:
                Toast.makeText(getActivity(), "Please log in/Sign Up to Continue", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item_add_ins:
            //    mListener.gotoAddInsfrag();
                Toast.makeText(getActivity(), "ADD Instructor!",Toast.LENGTH_SHORT).show();

                return true;
            case R.id.item_exit:
                //    mListener.gotoAddInsfrag();
                getActivity().finish();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final EditText name= (EditText) getView().findViewById(R.id.inputname);
        final EditText pwd= (EditText) getView().findViewById(R.id.inputpwd);
        getView().findViewById(R.id.btnlogn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ArrayList<Course> courses= (ArrayList<Course>) dm.getAllNotes(MainActivity.returnuname());

                for(int i=0;i<courses.size();i++)
                {
                    HashMap<String,String> map=courses.get(i).getUpwd();
                    for ( Map.Entry<String, String> entry : map.entrySet()) {
                        key = entry.getKey();
                        value= entry.getValue();
                        // do something with key and/or tab
                    }
                    if((name.getText().toString()).equals(key))
                    {
                        
                    }
                }

                mListener.onFragmentInteraction(name.getText().toString(),pwd.getText().toString());
            }
        });

    }
public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name


    void onFragmentInteraction(String s, String s1);

    void gotoAddInsfrag();
}
}
