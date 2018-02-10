package com.inclass.uncc.inclass08_23;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class RecipePuppyFragment extends Fragment {
    RecyclerView rv2;
    public static RecyclerView.Adapter madapter;         ImageView img;
    public static RecyclerView.LayoutManager mlayoutmanager;
    OnPuppyFragmentInteractionListener mListener;
    ArrayList<Integer> clicked;
    public RecipePuppyFragment() {
        // Required empty public constructor
        clicked=new ArrayList<>();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final ViewGroup view= (ViewGroup) inflater.inflate(R.layout.fragment_recipe_puppy, container, false);
        // Inflate the layout for this fragment

       return  view;
    }

    // TODO: Rename method, update argument and hook method into UI event


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnPuppyFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString());
        }
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
final EditText text1= (EditText) getView().findViewById(R.id.input1);
        final EditText dish= (EditText) getView().findViewById(R.id.editText2);
        getView().findViewById(R.id.btnsearch).setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                /*if( dish.getText().length() ==0){
                    Toast.makeText(getActivity(),"Please enter dish",Toast.LENGTH_SHORT).show();
                }*/
                 if(text1.getText().length() == 0 && dish.getText().length() ==0 ){
                    Toast.makeText(getActivity(),"Please enter dish and ingredient",Toast.LENGTH_SHORT).show();
                }
                else
                    //if(dish.getText().length() !=0 )
                     {

                    mListener.onFragmentInteraction(text1.getText().toString(), dish.getText().toString(), AddingAdapter.input);
                }
            }
        });
img = (ImageView) getView().findViewById(R.id.imageView2);
        img.setTag("add");

            getView().findViewById(R.id.imageView2).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(img.getTag().equals("add")) {
                    img.setImageResource(R.drawable.remove);
                    //  img.setTag("remove");

                    rv2 = (RecyclerView) getView().findViewById(R.id.rvinput);
                    mlayoutmanager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                    rv2.setLayoutManager(mlayoutmanager);
                    clicked.add(1);
                    madapter = new AddingAdapter(getActivity(), clicked);

                    rv2.setAdapter(madapter);
                    madapter.notifyDataSetChanged();
                    setRetainInstance(true);
                    img.setTag("remove");
                    img.setImageResource(R.drawable.remove);}
                    else
                    {

                        Toast.makeText(getActivity(),"Minimum One Ingredient Recommended",Toast.LENGTH_SHORT).show();
                    }
                }

            });

    }
    public interface OnPuppyFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(String text1, String text2, ArrayList<String> size);
    }
}
