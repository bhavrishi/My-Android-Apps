package com.myonic.rishibhv.inclass13_801019706;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rishi on 04/12/17.
 */

public class DealAdapter extends BaseAdapter {

    private List<Place> cities;
    List<Integer> myList = new ArrayList<Integer>();


    private CreateTripActivity activity;

    private int x=0;



    public DealAdapter(CreateTripActivity activity, List<Place> contacts) {
        this.cities=contacts;
        this.activity=activity;
    }

    @Override
    public int getCount() {
        return cities.size();
    }

    @Override
    public Object getItem(int i) {
        return cities.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i,  View view, ViewGroup viewGroup) {
        ImageView defaultimg;

        view =  LayoutInflater.from(activity.getApplicationContext()).inflate(R.layout.layout_place_list,viewGroup,false);
        ((TextView)view.findViewById(R.id.outtrip)).setText(cities.get(i).getPlace());
        ((TextView)view.findViewById(R.id.daynight)).setText(cities.get(i).getDuration());
        ((TextView)view.findViewById(R.id.outCost)).setText(cities.get(i).getCost());
        ((TextView)view.findViewById(R.id.outmiles)).setText(cities.get(i).getMiles()+" "+"miles away");

      CheckBox cb=  (CheckBox)view.findViewById(R.id.checkBox);
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    Log.d("demosel",""+i);
                    myList.add(i);

                    activity.changeCost(i);
                }
            }
        });
       // ((TextView)view.findViewById(R.id.outmiles)).setText(cities.get(i).get());

        //((TextView)view.findViewById(R.id.)).setText(contacts.get(i).getDept());
        //  String value=contacts.get(i).getImg();


        return view;
    }
}