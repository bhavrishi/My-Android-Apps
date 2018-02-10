package com.hwork.uncc.hw2_groups25;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Rishi on 19/09/17.
 */

public class ContactsAdapter extends BaseAdapter {

    public ContactsAdapter(Context mcontext, List<Contact> mlist) {
        this.mcontext = mcontext;
        this.mlist = mlist;
    }

    private Context mcontext;
    private List<Contact> mlist;



    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public Object getItem(int arg0) {
        return mlist.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @Override
    public View getView(int arg0, View arg1, ViewGroup arg2) {
Contact entry=mlist.get(arg0);
if(arg1==null) {
    LayoutInflater inflater = LayoutInflater.from(mcontext);
    arg1 = inflater.inflate(R.layout.layout_display, null);
}

       if(entry.getImg()==null) {
           ImageView img= (ImageView) arg1.findViewById(R.id.imgdefault);
           img.setImageResource(R.drawable.default_image);
           //img.setImageBitmap(entry.getImg());
       }
       else{
        ImageView img2= (ImageView) arg1.findViewById(R.id.imgdefault);
        img2.setImageBitmap(entry.getImg());}
    TextView name= (TextView) arg1.findViewById(R.id.name);
    name.setText( entry.getFirstName() +  " "+ entry.getLastname());
    TextView email= (TextView) arg1.findViewById(R.id.email);
    email.setText( entry.getEmail());
    TextView phone= (TextView) arg1.findViewById(R.id.phone);
    phone.setText(entry.getPhoneNumber());




        return arg1;
    }
}
