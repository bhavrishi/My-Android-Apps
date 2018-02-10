package com.myonic.rishibhv.previous_final;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rishi on 03/12/17.
 */

public class TripsDisplayAdapter extends BaseAdapter {

    private List<AutocompleteCity> cities;

    private TripsDisplayActivity activity;

    private int x = 0;
    ArrayList<Place> places;


    public TripsDisplayAdapter(TripsDisplayActivity activity, List<AutocompleteCity> contacts) {
        this.cities = contacts;
        this.activity = activity;
    }

    public TripsDisplayAdapter(TripsDisplayActivity activity, List<AutocompleteCity> contacts, ArrayList<Place> place) {
        this.cities = contacts;
        this.activity = activity;
        this.places = place;
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
    public int getItemViewType(int position) {
        //view type is managed as zero-based index.


            places = activity.returnPlaces(position);

      //  Log.d("dempplace",places.toString());
        if (places != null && places.size() > 0) {
            Log.d("dempplacein",places.toString());
            return 1;
        } else

            return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ImageView loca, add, placeicon, deltimg;

        int type = getItemViewType(i);
        switch (type) {
            case 0:
                view = LayoutInflater.from(activity.getApplicationContext()).inflate(R.layout.layout_display_1, viewGroup, false);
                ((TextView) view.findViewById(R.id.outcity)).setText(cities.get(i).getCity());
                ((TextView) view.findViewById(R.id.outtrip)).setText(cities.get(i).getTripname());
                loca = (ImageView) view.findViewById(R.id.imgloc);
                add = (ImageView) view.findViewById(R.id.imgltAdd);
                loca.setImageResource(R.drawable.loc);
                add.setImageResource(R.drawable.add);
                loca.setFocusable(true);
                add.setFocusable(true);
                loca.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d("democ", "Loc clicked");
                        Intent intnt = new Intent(activity, PathActivity.class);
                        //  i.putExtra(UID, main_id);
                        intnt.putExtra("ALIST", cities.get(i));
                        activity.startActivity(intnt);
                    }
                });
                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d("democ", "add clicked");
                        Intent intnt = new Intent(activity, AddPlacesActivity.class);
                        //  i.putExtra(UID, main_id);
                        intnt.putExtra("ALIST", cities.get(i));
                        activity.startActivity(intnt);
                    }
                });

                break;

            case 1:
                //inflate webview here.
                for(int d=0;d<places.size();d++) {
                    view = LayoutInflater.from(activity.getApplicationContext()).inflate(R.layout.layout_trip_place, viewGroup, false);
                    ((TextView) view.findViewById(R.id.outcity)).setText(cities.get(i).getCity());
                    ((TextView) view.findViewById(R.id.outtrip)).setText(cities.get(i).getTripname());
                    ((TextView) view.findViewById(R.id.outplacename)).setText(places.get(d).getPlacename());
                    ((TextView) view.findViewById(R.id.outtrip)).setText(cities.get(i).getTripname());
                    loca = (ImageView) view.findViewById(R.id.imgloc);
                    add = (ImageView) view.findViewById(R.id.imgltAdd);
                    placeicon = (ImageView) view.findViewById(R.id.imgplaceicon);
                    deltimg = (ImageView) view.findViewById(R.id.imgdelete);
                    //  deltimg.setImageResource(R.drawable.);
                    loca.setImageResource(R.drawable.loc);
                    add.setImageResource(R.drawable.add);
                    Picasso.with(activity).load(places.get(d).getImgurl()).into(placeicon);
                    loca.setFocusable(true);
                    add.setFocusable(true);
                    loca.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Log.d("democ", "Loc clicked");
                        }
                    });
                    add.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Log.d("democ", "add clicked");
                            Intent intnt = new Intent(activity, AddPlacesActivity.class);
                            //  i.putExtra(UID, main_id);
                            intnt.putExtra("ALIST", cities.get(i));
                            activity.startActivity(intnt);
                        }
                    });
                }
                break;
        }
        return view;
    }
}