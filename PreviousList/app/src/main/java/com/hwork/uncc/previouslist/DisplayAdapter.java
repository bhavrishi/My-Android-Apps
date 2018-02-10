package com.hwork.uncc.previouslist;

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
 * Created by Rishi on 15/10/17.
 */

class DisplayAdapter extends ArrayAdapter<Itunes> {


    public DisplayAdapter(Context mcontext, ArrayList<Itunes> mlist) {
        super(mcontext, R.layout.layout_display, mlist);
        this.mcontext = mcontext;
        this.mlist = mlist;

    }

    private Context mcontext;
    Bitmap bm;
    private static ArrayList<Itunes> mlist;

    List<Integer> selectedpos=null;


    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public Itunes getItem(int arg0) {
        return mlist.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @Override
    public View getView(final int arg0, View arg1, ViewGroup arg2) {
        Itunes entry = mlist.get(arg0);
        if (arg1 == null) {
            LayoutInflater inflater = LayoutInflater.from(mcontext);
            arg1 = inflater.inflate(R.layout.layout_display, null);
        }

        if (entry.getImg1() != null) {
            ImageView img = (ImageView) arg1.findViewById(R.id.imgdefault);

            try {
                bm = new AsyncImage().execute(entry.getImg1()).get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            //  img.setImageResource(R.drawable.default_image);
            if (bm != null) img.setImageBitmap(bm);
        }
        //   Track track = (Track) getItem(arg0);
        TextView name = (TextView) arg1.findViewById(R.id.outname);
        name.setText(entry.getTitle());








        return arg1;
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


}
