package com.inclass.uncc.inclass08_23;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


public class RecipesFragment extends Fragment {

    private RecyclerView.Adapter madapter;
    private RecyclerView.LayoutManager mlayoutmanager;

    OnFragmentInteractionListener mlistener;
    public RecipesFragment() {

        // Required empty public constructor
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
      
        getView().findViewById(R.id.btnfinish).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mlistener.gotomainfinishfrag();
            }
        });
       
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_recipes, container, false);

        //final ListView pwd=view.findViewById(R.id.rv);
        ArrayList<Recipe> msgs = new ArrayList<Recipe>();

        // msgs=MainActivity.returnlist();
        try {
            String url=MainActivity.returndata();
            if(url.equals("http://www.recipepuppy.com/api/?i=&q="))
                Toast.makeText(getActivity(),"Please enter atleast one ingredient",Toast.LENGTH_SHORT).show();
            else
            msgs = new AsyncReceipe().execute(url).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        if (msgs.size() > 0) {

            Log.d("demomsg", "" + msgs.toString());
        /*ListViewAdapter cadapt;
        cadapt = new ListViewAdapter(getActivity(), msgs,dm);
        pwd.setAdapter(cadapt);*/

            RecyclerView rv = (RecyclerView) view.findViewById(R.id.rv);
            SnapHelper snapHelper = new PagerSnapHelper();
            snapHelper.attachToRecyclerView(rv);
            mlayoutmanager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
            rv.setLayoutManager(mlayoutmanager);
            madapter = new RecycleAdapter(msgs);

            rv.setAdapter(madapter);
            setRetainInstance(true);
        } else {

mlistener.gotomainfrag();
        }
      /*  pwd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });*/
        return view;
    }
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name

        void   gotomainfrag();

        void gotomainfinishfrag();
    }
}


