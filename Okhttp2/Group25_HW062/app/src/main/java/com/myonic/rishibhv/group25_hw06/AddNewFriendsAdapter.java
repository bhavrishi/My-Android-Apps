package com.myonic.rishibhv.group25_hw06;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by rosyazad on 19/11/17.
 */

public class AddNewFriendsAdapter extends RecyclerView.Adapter<AddNewFriendsAdapter.ViewHolder> {

    private ArrayList<User> mdata;
    Context context;

    public AddNewFriendsAdapter(Context context, ArrayList<User> mdata) {
        this.mdata = mdata;
        this.context=context;
    }

    @Override
    public AddNewFriendsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_add_new_friend, parent, false);
        Context context = parent.getContext();
        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(final AddNewFriendsAdapter.ViewHolder holder, final int position) {
        final User user = mdata.get(position);
        holder.tvfriendName.setText(user.getName());
        holder.imageViewAddFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mdata.remove(position);
                notifyDataSetChanged();
                holder.hContext.callAddDeleteMethod(user.getId(),position,user);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvfriendName;
        ImageView imageViewAddFriend;
        FriendsTabActivity hContext;
        public ViewHolder(View itemView, Context context) {
            super(itemView);
            tvfriendName =(TextView) itemView.findViewById(R.id.textViewAddNewFrnd);
            imageViewAddFriend =(ImageView) itemView.findViewById(R.id.imageViewAddNewFrnd);
            hContext = (FriendsTabActivity) context;
        }
    }
}
