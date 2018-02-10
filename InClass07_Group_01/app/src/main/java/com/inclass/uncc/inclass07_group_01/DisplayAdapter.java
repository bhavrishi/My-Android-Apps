package com.inclass.uncc.inclass07_group_01;

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
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Rishi on 23/10/17.
 */

public class DisplayAdapter extends ArrayAdapter<ITunes> {
    Date date;

    public DisplayAdapter(Context mcontext, ArrayList<ITunes> mlist) {
        super(mcontext, R.layout.layout_top_list, mlist);
        this.mcontext = mcontext;
        this.mlist = mlist;

    }

    private Context mcontext;
    Bitmap bm;
    private static ArrayList<ITunes> mlist;

    List<Integer> selectedpos=null;


    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public ITunes getItem(int arg0) {
        return mlist.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @Override
    public View getView(final int arg0, View arg1, ViewGroup arg2) {
        ITunes entry = mlist.get(arg0);
        if (arg1 == null) {
            LayoutInflater inflater = LayoutInflater.from(mcontext);
            arg1 = inflater.inflate(R.layout.layout_top_list, null);
        }
       // String imgurl = "http://image.tmdb.org/t/p/w154/" + entry.getImage();
        if (entry.getImage() != null) {
            ImageView img = (ImageView) arg1.findViewById(R.id.imageView);

            try {
                bm = new AsyncImage().execute(entry.getImage()).get();
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
        int priceleng = entry.getPrice().length();
        String price = entry.getPrice().substring(1, priceleng);
        TextView name = (TextView) arg1.findViewById(R.id.appName);
        name.setText(entry.getName());
        TextView tvprice = (TextView) arg1.findViewById(R.id.price);
        tvprice.setText("USD" + price);
        final ImageView starsilver = (ImageView) arg1.findViewById(R.id.imageButton);

        Double doubleprice = Double.parseDouble(price);

        if (doubleprice < 1.99)
            starsilver.setImageResource(R.drawable.price_low);
        else if (doubleprice > 2 && doubleprice < 4.99)
            starsilver.setImageResource(R.drawable.price_medium);

        else if (doubleprice > 5)
            starsilver.setImageResource(R.drawable.price_high);
        return arg1;
    }
       /* if (checkFavoriteItem(entry)) {
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


    *//*Checks whether a particular product exists in SharedPreferences*//*
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
*/
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
    public void add(ITunes product) {
        super.add(product);
        mlist.add(product);
        Log.d("demoadd",product.toString());
        notifyDataSetChanged();
    }

    @Override
    public void remove(ITunes product) {
        super.remove(product);
        mlist.remove(product);
        notifyDataSetChanged();
    }
    public static ArrayList<ITunes> returnList()
    {
        return mlist;
    }
}

