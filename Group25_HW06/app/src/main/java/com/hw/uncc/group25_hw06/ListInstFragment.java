package com.hw.uncc.group25_hw06;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class ListInstFragment extends Fragment {
    private RecyclerView.Adapter madapter;
    private RecyclerView.LayoutManager mlayoutmanager;
    ArrayList<Instructor> instructors;  TextView noitems;
    InstructorAdapter.RecyclerViewClickListener mlistner;
    RecyclerView rv;

    InstructorDBManager dm;
   //private OnFragmentInteractionListener mListener;

    public ListInstFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_list_inst, container, false);
        //final ListView pwd=view.findViewById(R.id.rv);


        // msgs=MainActivity.returnlist();

        dm = new InstructorDBManager(getActivity());
        instructors = (ArrayList<Instructor>) dm.getAllNotes(MainActivity.returnuname());
        Log.d("demomsg", "" + instructors.toString());
        /*ListViewAdapter cadapt;
        cadapt = new ListViewAdapter(getActivity(), msgs,dm);
        pwd.setAdapter(cadapt);*/

        rv = (RecyclerView) view.findViewById(R.id.rv3);
        noitems = (TextView) view.findViewById(R.id.noins);
        noitems.setVisibility(View.INVISIBLE);
        mlayoutmanager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(mlayoutmanager);
        madapter = new InstructorAdapter(instructors, dm, new InstructorAdapter.RecyclerViewClickListener() {
            @Override
            public void recyclerViewListClicked(View v, int position) {
                position=position;
                Log.d("clicked pos",""+position);
            }
        });
        if(instructors.size()>0){
           // rv.setVisibility(View.INVISIBLE);
            rv.setAdapter(madapter);
            setRetainInstance(true);
        }
        else
        {
            noitems.setVisibility(View.VISIBLE);
        }
        return view;
    }

 /*   @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }*/
}
