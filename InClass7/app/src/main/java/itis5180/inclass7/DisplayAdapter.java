package itis5180.inclass7;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Assignment: InClass07
 * Name: DisplayAdapter.java
 * Date: 10/24/2017
 * Bradley Wright
 * Anvesh Kottapelli
 * Bhavya Gedi
 */

class DisplayAdapter extends ArrayAdapter<ITunes> {

    DisplayAdapter(Context mcontext, ArrayList<ITunes> mlist) {
        super(mcontext, R.layout.app_list, mlist);
        this.mcontext = mcontext;
        DisplayAdapter.mlist = mlist;

    }

    private Context mcontext;
    private Bitmap bm;
    private static ArrayList<ITunes> mlist;

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
            arg1 = inflater.inflate(R.layout.app_list, null);
        }
        if (entry.getImage() != null) {
            ImageView img = arg1.findViewById(R.id.imageView);
            try {
                bm = new AsyncImage().execute(entry.getImage()).get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

            if (bm != null) img.setImageBitmap(bm);
        }

        int priceleng = entry.getPrice().length();
        String price = entry.getPrice().substring(1, priceleng);
        TextView name = arg1.findViewById(R.id.appName);
        name.setText(entry.getName());
        TextView tvprice = arg1.findViewById(R.id.price);
        tvprice.setText("USD" + price);
        final ImageView starsilver = arg1.findViewById(R.id.priceImage);

        Double doubleprice = Double.parseDouble(price);

        if (doubleprice < 1.99)
            starsilver.setImageResource(R.drawable.price_low);
        else if (doubleprice > 2 && doubleprice < 4.99)
            starsilver.setImageResource(R.drawable.price_medium);
        else if (doubleprice > 5)
            starsilver.setImageResource(R.drawable.price_high);
        return arg1;
    }

    @Override
    public void add(ITunes product) {
        super.add(product);
        mlist.add(product);
        Log.d("demoadd", product.toString());
        notifyDataSetChanged();
    }

    @Override
    public void remove(ITunes product) {
        super.remove(product);
        mlist.remove(product);
        notifyDataSetChanged();
    }

    static ArrayList<ITunes> returnList() {
        return mlist;
    }
}


