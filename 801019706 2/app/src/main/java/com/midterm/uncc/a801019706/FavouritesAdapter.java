package com.midterm.uncc.a801019706;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Rishi on 16/10/17.
 */

public class FavouritesAdapter extends ArrayAdapter<Movie> {

    SharedPreferencesFavourites sharedPreference;

    public FavouritesAdapter(Context mcontext, ArrayList<Movie> mlist) {
        super(mcontext, R.layout.layout_display, mlist);
        this.mcontext = mcontext;
        this.mlist = mlist;
        sharedPreference = new SharedPreferencesFavourites();
    }

    private Context mcontext;
    Bitmap bm;
    private static ArrayList<Movie> mlist;

    List<Integer> selectedpos=null;


    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public Movie getItem(int arg0) {
        return mlist.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @Override
    public View getView(final int arg0, View arg1, ViewGroup arg2) {
        Movie entry = mlist.get(arg0);
        if (arg1 == null) {
            LayoutInflater inflater = LayoutInflater.from(mcontext);
            arg1 = inflater.inflate(R.layout.layout_display, null);
        }
        String imgurl="http://image.tmdb.org/t/p/w154/"+entry.getPosterpath();
        if (entry.getPosterpath() != null) {
            ImageView img = (ImageView) arg1.findViewById(R.id.imgdefault);

            try {
                bm = new AsyncImage().execute(imgurl).get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            //  img.setImageResource(R.drawable.default_image);
            if (bm != null) img.setImageBitmap(bm);
        }
        //   Track track = (Track) getItem(arg0);

      /*  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
          date  = dateFormat.parse(entry.getDate().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
       String year= String.valueOf(calendar.get(Calendar.YEAR));*/
        String year=entry.getDate().substring(0,4);
        TextView name = (TextView) arg1.findViewById(R.id.name);
        name.setText(entry.getName());
        TextView outdate = (TextView) arg1.findViewById(R.id.date);
        outdate.setText("Released in "+year);
        final ImageButton starsilver = (ImageButton) arg1.findViewById(R.id.imgsilver);



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
                     mlist.remove(arg0);
                    notifyDataSetChanged();
                }

            }
        });

        starsilver.setFocusable(false);
        starsilver.setFocusableInTouchMode(false);
        return arg1;
    }


    /*Checks whether a particular product exists in SharedPreferences*/
    public boolean checkFavoriteItem(Movie checkProduct) {
        boolean check = false;
        Log.d("demofav",checkProduct.toString());
        List<Movie> favorites = SharedPreferencesFavourites.getFavorites(mcontext);
        if (favorites != null) {
            for (Movie product : favorites) {
                if (product.getId().equals(checkProduct.getId())) {
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
    public void add(Movie product) {
        super.add(product);
        mlist.add(product);
        Log.d("demoadd",product.toString());
        notifyDataSetChanged();
    }

    @Override
    public void remove(Movie product) {
        super.remove(product);
        mlist.remove(product);
        notifyDataSetChanged();
    }
    public static ArrayList<Movie> returnList()
    {
        return mlist;
    }
}

