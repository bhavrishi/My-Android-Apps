package com.inclass.uncc.previousfragment;

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
 * Created by Rishi on 29/10/17.
 */

public class ListViewAdapter  extends ArrayAdapter<Message> {
    private DBManager dm;

    ListViewAdapter(Context mcontext, ArrayList<Message> mlist,DBManager dm) {
        super(mcontext, R.layout.layout_list, mlist);
        this.mcontext = mcontext;
        this.mlist = mlist;
this.dm=dm;
    }

    private Context mcontext;
    private Bitmap bm;
    private static ArrayList<Message> mlist;

    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public Message getItem(int arg0) {
        return mlist.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @Override
    public View getView(final int arg0, View arg1, ViewGroup arg2) {
        Message entry = mlist.get(arg0);
        if (arg1 == null) {
            LayoutInflater inflater = LayoutInflater.from(mcontext);
            arg1 = inflater.inflate(R.layout.layout_list, null);
        }



        TextView name = arg1.findViewById(R.id.textmsg);
        name.setText(entry.getMsg());
        TextView id = arg1.findViewById(R.id.idd);
        id.setText(""+ entry.getId());
        final ImageView starsilver = arg1.findViewById(R.id.imageView);


starsilver.setFocusable(true);
        starsilver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("demo","clicked image "+arg0);
                Log.d("demo","clicked image "+mlist.get(0));
                dm.deleteNote(mlist.get(arg0));
                mlist.remove(arg0);
                notifyDataSetChanged();
            }
        });
        return arg1;
    }

    @Override
    public void add(Message product) {
        super.add(product);
        mlist.add(product);
        Log.d("demoadd", product.toString());
        notifyDataSetChanged();
    }

    @Override
    public void remove(Message product) {
        super.remove(product);
        mlist.remove(product);
        notifyDataSetChanged();
    }
/*
    static ArrayList<ITunes> returnList() {
        return mlist;
    }*/
}


