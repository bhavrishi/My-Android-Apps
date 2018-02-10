package com.myonic.rishibhv.previous_final;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Rishi on 03/12/17.
 */

public class SelectPlaceAdapter extends BaseAdapter {

    private List<Place> places;

    private AddPlacesActivity activity;

    private int x = 0;


    public SelectPlaceAdapter(AddPlacesActivity activity, List<Place> places) {
        this.places = places;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return places.size();
    }

    @Override
    public Object getItem(int i) {
        return places.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView defaultimg;
        view = LayoutInflater.from(activity.getApplicationContext()).inflate(R.layout.layout_place_list, viewGroup, false);
        ((TextView) view.findViewById(R.id.outplacename)).setText(places.get(i).getPlacename());
        defaultimg = (ImageView) view.findViewById(R.id.imgplaceicon);
        Picasso.with(activity).load(places.get(i).getImgurl()).into(defaultimg);

        //((TextView)view.findViewById(R.id.)).setText(contacts.get(i).getDept());
        //  String value=contacts.get(i).getImg();


        return view;
    }
}