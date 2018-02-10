package com.inclass.uncc.previousfragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by Rishi on 30/10/17.
 */

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder>{
    private ArrayList<Message> mdata;

    private DBManager dm;
    RecycleAdapter(ArrayList<Message> mdata, DBManager dm) {
        this.mdata = mdata;

        this.dm = dm;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list, parent, false);
        Context context = parent.getContext();
        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.d("demoadap",""+mdata.toString());
        Message iTunes = mdata.get(position);
        holder.msg.setText(iTunes.msg);
        holder.id.setText(""+iTunes.id);

        Log.d("demoin adapter", iTunes.toString());

       holder.deleteButton.setImageResource(R.drawable.rsz_remove);

       // Picasso.with(holder.hcontext).load().into(holder.deleteButton);


        holder.iTunes = iTunes;
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView msg,id;

        Message iTunes;
        ImageButton deleteButton;
        Context hcontext;
    public ViewHolder(View itemView, Context context) {
        super(itemView);
        msg = itemView.findViewById(R.id.textmsg);
        id = itemView.findViewById(R.id.idd);

        deleteButton = itemView.findViewById(R.id.imageView);
        hcontext = context;


        itemView.findViewById(R.id.imageView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("demo","clicked image "+getAdapterPosition());
                Log.d("demo","clicked image "+mdata.get(0));
                dm.deleteNote(mdata.get(getAdapterPosition()));
                mdata.remove(getAdapterPosition());
                notifyDataSetChanged();

              /*  Log.d("demo", "Clicked button" + iTunes.name);
                View r = view.getRootView();

                dm.deleteNote(mdata.get(getAdapterPosition()));

                ListView lv = r.findViewById(R.id.lv);
                response.add(mdata.get(getAdapterPosition()));
                DisplayAdapter cadapt;
                cadapt = new DisplayAdapter(r.getContext(), response);

                Switch s = r.findViewById(R.id.switchOrder);

                if (s.isChecked()) {
                    cadapt.sort(new Comparator<ITunes>() {
                        @Override
                        public int compare(ITunes iTunes, ITunes i2) {

                            return iTunes.getPrice().compareTo(i2.getPrice());
                        }
                    });
                } else {
                    cadapt.sort(new Comparator<ITunes>() {
                        @Override
                        public int compare(ITunes iTunes, ITunes i2) {

                            return i2.getPrice().compareTo(iTunes.getPrice());
                        }
                    });
                }

                lv.setAdapter(cadapt);
                delete(getAdapterPosition(), r);*/
            }
        });
    }
}
}
