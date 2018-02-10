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

public class TripAdapter extends BaseAdapter {

    private List<Trip> cities;
    List<Integer> myList = new ArrayList<Integer>();


    private MapsActivity activity;

    private int x=0;



    public TripAdapter(MapsActivity activity, List<Trip> contacts) {
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ImageView defaultimg;

        view =  LayoutInflater.from(activity.getApplicationContext()).inflate(R.layout.layout_trip_list,viewGroup,false);
        ((TextView)view.findViewById(R.id.outtrip)).setText(cities.get(i).getTrip());
        ((TextView)view.findViewById(R.id.outcity)).setText(""+cities.get(i).getCost());

        // ((TextView)view.findViewById(R.id.outmiles)).setText(cities.get(i).get());

        //((TextView)view.findViewById(R.id.)).setText(contacts.get(i).getDept());
        //  String value=contacts.get(i).getImg();


        return view;
    }
}