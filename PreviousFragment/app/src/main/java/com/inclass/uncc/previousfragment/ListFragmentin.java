package com.inclass.uncc.previousfragment;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;


public class ListFragmentin extends Fragment {
    private RecyclerView.Adapter madapter;
    private RecyclerView.LayoutManager mlayoutmanager;
    DBManager dm;
  //  OnFragmentChanged mlistener;
    public ListFragmentin() {

        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup view= (ViewGroup) inflater.inflate(R.layout.fragment_list, container, false);

        //final ListView pwd=view.findViewById(R.id.rv);
        ArrayList<Message> msgs=new ArrayList<Message>();

       // msgs=MainActivity.returnlist();

        dm= new DBManager(getActivity());
        msgs= (ArrayList<Message>) dm.getAllNotes();
        Log.d("demomsg",""+msgs.toString());
        /*ListViewAdapter cadapt;
        cadapt = new ListViewAdapter(getActivity(), msgs,dm);
        pwd.setAdapter(cadapt);*/

        RecyclerView rv = view.findViewById(R.id.rv);
        mlayoutmanager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(mlayoutmanager);
        madapter = new RecycleAdapter(msgs, dm);

        rv.setAdapter(madapter);
        setRetainInstance(true);
      /*  pwd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });*/
        return view;
    }

      /* @Override
       public void onAttach(Activity activity) {
           super.onAttach(activity);
           try {
               mlistener=(OnFragmentChanged)activity;
           } catch (ClassCastException e) {
               throw new ClassCastException(activity.toString());
           }
       }*/
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // final EditText name=getView().findViewById(R.id.inputname);





    }
/*    public interface OnFragmentChanged {
        // TODO: Update argument type and name
        void onFragmentInteraction(String name, String pwd);
        void   gotonextfrag();

        void gotocomposefrag();

        void onFragmentMsg(String s);


        void gotolistfrag();
    }*/

}
