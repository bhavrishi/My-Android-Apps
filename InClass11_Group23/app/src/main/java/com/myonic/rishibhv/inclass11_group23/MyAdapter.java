package com.myonic.rishibhv.inclass11_group23;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Rishi on 20/11/17.
 */

public class MyAdapter   extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

private Display_Contacts_Activity context;
private List<Contact> contatcs;

public MyAdapter(Display_Contacts_Activity context, List<Contact> uploads) {
        this.contatcs = uploads;
        this.context = context;
        }

@Override
public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.layout_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
        }

@Override
public void onBindViewHolder(ViewHolder holder, int position) {
        final Contact cont = contatcs.get(position);

        holder.fname.setText("Name: "+cont.getFname()+" "+cont.getLname());
    holder.email.setText("Email: "+cont.getEmail());
    holder.phone.setText("Phone"+cont.getPhone());

       // Glide.with(context).load(cont.getImgurl()).into(holder.imageView);
    Picasso.with(context).load(cont.getImgurl()).into(holder.imageView);

    holder.imgedit.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            context.editContactMethod(cont);
        }
    });
    holder.imgdelete.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            context.deleteContact(cont.getId(),cont.getPhone());
        }
    });
        }

@Override
public int getItemCount() {
        return contatcs.size();
        }

class ViewHolder extends RecyclerView.ViewHolder {

    public TextView fname,phone,email;
    public ImageView imageView,imgdelete,imgedit;

    public ViewHolder(View itemView) {
        super(itemView);

        fname = (TextView) itemView.findViewById(R.id.name);

        phone = (TextView) itemView.findViewById(R.id.phone);
        email = (TextView) itemView.findViewById(R.id.email);
        imageView = (ImageView) itemView.findViewById(R.id.imgcontact);
        imgdelete = (ImageView) itemView.findViewById(R.id.imgdelete);
        imgedit = (ImageView) itemView.findViewById(R.id.imgedit);
    }
}
}
