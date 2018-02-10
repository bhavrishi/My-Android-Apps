package com.myonic.rishibhv.inclass10_group;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.value;

/**
 * Created by Rishi on 12/11/17.
 */

class CustomListAdapater extends BaseAdapter {

    private List<Contact> contacts;

    private DisplayActivity activity;

    private int x=0;



    public CustomListAdapater(DisplayActivity activity, List<Contact> contacts) {
        this.contacts=contacts;
        this.activity=activity;
    }

    @Override
    public int getCount() {
        return contacts.size();
    }

    @Override
    public Object getItem(int i) {
        return contacts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView defaultimg;
        view =  LayoutInflater.from(activity.getApplicationContext()).inflate(R.layout.layout_contact_list,viewGroup,false);
        ((TextView)view.findViewById(R.id.outname)).setText(contacts.get(i).getName());
        ((TextView)view.findViewById(R.id.outemail)).setText(contacts.get(i).getEmail());
        ((TextView)view.findViewById(R.id.outphone)).setText(contacts.get(i).getPhone());
        ((TextView)view.findViewById(R.id.outDept)).setText(contacts.get(i).getDept());
        defaultimg= (ImageView) view.findViewById(R.id.outimg);
        //((TextView)view.findViewById(R.id.)).setText(contacts.get(i).getDept());
        String value=contacts.get(i).getImg();
        if (value.equals("img1")) {
            defaultimg.setImageResource(R.drawable.avatar_f_1);

        }

        if (value.equals("img2")) {
            defaultimg.setImageResource(R.drawable.avatar_f_2);

        }
        if (value.equals("img3")) {
            defaultimg.setImageResource(R.drawable.avatar_f_3);

        }

        if (value.equals("img4")) {
            defaultimg.setImageResource(R.drawable.avatar_m_1);

        }
        if (value.equals("img5")) {
            defaultimg.setImageResource(R.drawable.avatar_m_2);

        }
        if (value.equals("img6")) {
            defaultimg.setImageResource(R.drawable.avatar_m_3);

        }
        Log.d("demo", value);
        return view;
    }
}