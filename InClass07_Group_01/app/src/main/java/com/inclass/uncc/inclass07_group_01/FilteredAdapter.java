package com.inclass.uncc.inclass07_group_01;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Rishi on 23/10/17.
 */

public class FilteredAdapter  extends RecyclerView.Adapter<FilteredAdapter.ViewHolder> {
    ArrayList<ITunes> mdata;


    public FilteredAdapter(ArrayList<ITunes> mdata) {
        this.mdata = mdata;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.filtered_list,parent,false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ITunes iTunes=mdata.get(position);
        holder.name.setText(iTunes.name);
        holder.price.setText(iTunes.price);
        // holder.text3.setText(email.summary);
        holder.iTunes=iTunes;
    }



    @Override
    public int getItemCount() {
        return mdata.size();
    }
    public static  class ViewHolder extends RecyclerView.ViewHolder{
        TextView name,price,text3;
        ITunes iTunes;
        public ViewHolder(View itemView) {
            super(itemView);
            this.iTunes=iTunes;
            name=itemView.findViewById(R.id.appname);
            price=itemView.findViewById(R.id.price);
          //  text3=itemView.findViewById(R.id.textView3);
            itemView.findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("demo","Clicked button"+iTunes.name);
                }
            });
          /*  itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("demo","Clicked"+email.sender);
                }
            });*/
        }
    }
}