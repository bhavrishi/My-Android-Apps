package com.inclass.uncc.fragmentdemo;

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


public class AFragment extends Fragment {


    public AFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_a, container, false);
    }
    OnFragmentChanged mlistener;
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
mlistener=(OnFragmentChanged)activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString());
        }
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final EditText et=getView().findViewById(R.id.editTextfr);
        getView().findViewById(R.id.buttonf).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

mlistener.onTextChanged(et.getText().toString());
            }
        });
    }

    public void changecolor(int color)
{
getActivity().findViewById(R.id.layout).setBackgroundColor(color);
}
public interface OnFragmentChanged
    {
        public void onTextChanged(String text);
    }
}
