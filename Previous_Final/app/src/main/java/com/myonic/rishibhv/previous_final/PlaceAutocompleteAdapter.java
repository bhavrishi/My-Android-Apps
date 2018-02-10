package com.myonic.rishibhv.previous_final;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Rishi on 03/12/17.
 */

public class PlaceAutocompleteAdapter extends BaseAdapter {

    private List<AutocompleteCity> cities;

    private MapsActivity activity;

    private int x=0;



    public PlaceAutocompleteAdapter(MapsActivity activity, List<AutocompleteCity> contacts) {
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView defaultimg;
        view =  LayoutInflater.from(activity.getApplicationContext()).inflate(R.layout.layoutautocomplete,viewGroup,false);
        ((TextView)view.findViewById(R.id.outcity)).setText(cities.get(i).getCity());

        //((TextView)view.findViewById(R.id.)).setText(contacts.get(i).getDept());
      //  String value=contacts.get(i).getImg();


        return view;
    }
}