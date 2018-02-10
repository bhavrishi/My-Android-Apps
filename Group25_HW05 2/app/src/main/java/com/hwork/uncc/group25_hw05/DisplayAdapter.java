package com.hwork.uncc.group25_hw05;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Rishi on 12/10/17.
 */

public class DisplayAdapter extends ArrayAdapter<Track> {
    SharedPreferencesFavourites sharedPreference;

    public DisplayAdapter(Context mcontext, ArrayList<Track> mlist) {
        super(mcontext, R.layout.layout_display, mlist);
        this.mcontext = mcontext;
        this.mlist = mlist;
        sharedPreference = new SharedPreferencesFavourites();
    }

    private Context mcontext;
    Bitmap bm;
    private static ArrayList<Track> mlist;

    List<Integer> selectedpos=null;


    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public Track getItem(int arg0) {
        return mlist.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @Override
    public View getView(final int arg0, View arg1, ViewGroup arg2) {
        Track entry = mlist.get(arg0);
        if (arg1 == null) {
            LayoutInflater inflater = LayoutInflater.from(mcontext);
            arg1 = inflater.inflate(R.layout.layout_display, null);
        }

        if (entry.getImg() != null) {
            ImageView img = (ImageView) arg1.findViewById(R.id.imgdefault);
            String[] third = entry.getImg();
            try {
                bm = new AsyncImage().execute(third[0]).get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            //  img.setImageResource(R.drawable.default_image);
            if (bm != null) img.setImageBitmap(bm);
        }
        //   Track track = (Track) getItem(arg0);
        TextView name = (TextView) arg1.findViewById(R.id.name);
        name.setText(entry.getName());
        TextView email = (TextView) arg1.findViewById(R.id.artist);
        email.setText(entry.getArtist());
        final ImageButton starsilver = arg1.findViewById(R.id.imgsilver);



        if (checkFavoriteItem(entry)) {
           // Log.d("democheck","true");
            starsilver.setImageResource(R.drawable.gold);
            starsilver.setTag("red");
        } else {
            //Log.d("democheck","false");
            starsilver.setImageResource(R.drawable.silver);
            starsilver.setTag("grey");
        }


        starsilver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tag = starsilver.getTag().toString();
                if (tag.equalsIgnoreCase("grey")) {
                    sharedPreference.addFavorite(mcontext, mlist.get(arg0));
                    Toast.makeText(mcontext,
                            "Aded Favourite",
                            Toast.LENGTH_SHORT).show();

                    starsilver.setTag("red");
                    starsilver.setImageResource(R.drawable.gold);
                    starsilver.setFocusable(false);
                    starsilver.setFocusableInTouchMode(false);
                } else {
                    starsilver.setFocusable(true);
                    starsilver.setFocusableInTouchMode(true);
                    sharedPreference.removeFavorite(mcontext, mlist.get(arg0),arg0);
//                    selectedpos.add(arg0);

                    starsilver.setTag("grey");
                    starsilver.setImageResource(R.drawable.silver);
                    Toast.makeText(mcontext,
                          "Removed Fav",
                            Toast.LENGTH_SHORT).show();
                    starsilver.setFocusable(false);
                    starsilver.setFocusableInTouchMode(false);
                   // mlist.remove(arg0);
                   notifyDataSetChanged();
                }

          }
        });

        starsilver.setFocusable(false);
        starsilver.setFocusableInTouchMode(false);
        return arg1;
    }


    /*Checks whether a particular product exists in SharedPreferences*/
    public boolean checkFavoriteItem(Track checkProduct) {
        boolean check = false;
        Log.d("demofav",checkProduct.toString());
        List<Track> favorites = SharedPreferencesFavourites.getFavorites(mcontext);
        if (favorites != null) {
            for (Track product : favorites) {
                if (product.getUrl().equals(checkProduct.getUrl())) {
                    check = true;
                    break;
                }
            }
        }
        Log.d("democheck",""+ checkProduct.getName()+"  "+check);
        return check;
    }

      /*  starsilver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                starsilver.setImageResource(R.drawable.gold);
                selectedpos.add(arg0);
                starsilver.setFocusable(false);
                starsilver.setFocusableInTouchMode(false);
                Log.d("demoposs",""+arg0);}
        });*/

    @Override
    public void add(Track product) {
        super.add(product);
        mlist.add(product);
        Log.d("demoadd",product.toString());
        notifyDataSetChanged();
    }

    @Override
    public void remove(Track product) {
        super.remove(product);
        mlist.remove(product);
        notifyDataSetChanged();
    }
    public static ArrayList<Track> returnList()
    {
        return mlist;
    }
}
