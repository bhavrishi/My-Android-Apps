package com.midterm.uncc.recyclervw;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Rishi on 22/10/17.
 */

public class Emailhandler extends RecyclerView.Adapter<Emailhandler.ViewHolder> {
    ArrayList<Email> mdata;


    public Emailhandler(ArrayList<Email> mdata) {
        this.mdata = mdata;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_email,parent,false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
Email email=mdata.get(position);
        holder.text1.setText(email.sub);
        holder.text2.setText(email.sender);
        holder.text3.setText(email.summary);
        holder.email=email;
    }



    @Override
    public int getItemCount() {
        return mdata.size();
    }
    public static  class ViewHolder extends RecyclerView.ViewHolder{
        TextView text1,text2,text3;
        Email email;
        public ViewHolder(View itemView) {
            super(itemView);
            this.email=email;
            text1=itemView.findViewById(R.id.textView);
            text2=itemView.findViewById(R.id.textView2);
            text3=itemView.findViewById(R.id.textView3);
            itemView.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("demo","Clicked button"+email.sender);
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("demo","Clicked"+email.sender);
                }
            });
        }
    }
}
