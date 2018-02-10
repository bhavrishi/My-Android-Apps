package com.hw.uncc.group25_hw06;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Rishi on 02/11/17.
 */

public class DetailedInsAdapter extends RecyclerView.Adapter<DetailedInsAdapter.ViewHolder> {
    private ArrayList<Instructor> mdata;

    private InstructorDBManager dm;
    Bitmap bitmap;

    DetailedInsAdapter(ArrayList<Instructor> mdata, InstructorDBManager dm, InstructorAdapter.RecyclerViewClickListener itemListener) {
        this.mdata = mdata;

        this.dm = dm;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_instructor_list, parent, false);
        Context context = parent.getContext();
        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.d("demoadap", "" + mdata.toString());
        Instructor ins = mdata.get(position);
        holder.insname.setText(ins.fname);
        holder.iemail.setText(ins.email);
        Log.d("insimg", "" + ins.img);
        if (ins.img != null) {
            try {
                byte[] encodeByte = Base64.decode(ins.img, Base64.DEFAULT);
                bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
                Log.d("bitmap", "" + bitmap);

            } catch (Exception e) {
                e.getMessage();

            }
        }
        Log.d("demoin adapter", ins.toString());
        holder.insimg.setImageBitmap(bitmap);
        //   holder.deleteButton.setImageResource(R.drawable.rsz_remove);

        // Picasso.with(holder.hcontext).load().into(holder.deleteButton);


        holder.ins = ins;
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView insname,iemail;

        Instructor ins;
        ImageView insimg;
        Context hcontext;

        public ViewHolder(View itemView, Context context) {
            super(itemView);
            insname = (TextView) itemView.findViewById(R.id.iname);

            iemail = (TextView) itemView.findViewById(R.id.iemail);
            insimg = (ImageView) itemView.findViewById(R.id.iimg);
            hcontext = context;

          /*  itemView.findViewById(R.id.recyclerView).

                    setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick (View view){
                            itemListener.recyclerViewListClicked(view, hcontext.);
                        }
                    });*/


        }
    }
}