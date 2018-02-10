package com.myonic.rishibhv.group25_hw06;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by rosyazad on 21/11/17.
 */

public class AllFriendsAdapter extends RecyclerView.Adapter<AllFriendsAdapter.ViewHolder>{


    private ArrayList<User> mdata;
    Context context;

    public AllFriendsAdapter(Context context, ArrayList<User> mdata) {
        this.mdata = mdata;
        this.context=context;
    }
    @Override
    public AllFriendsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_all_friends, parent, false);
        Context context = parent.getContext();
        return new ViewHolder(view, context);    }

    @Override
    public void onBindViewHolder(AllFriendsAdapter.ViewHolder holder, int position) {
        final User user = mdata.get(position);
        holder.tvfriendName.setText(user.getName());

        /*holder.imageViewAddFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mdata.remove(position);
                notifyDataSetChanged();
                holder.hContext.callAddDeleteMethod(user.getId(),position,user);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvfriendName;
        ImageView imageViewRemoveFriend;
        FriendsTabActivity hContext;
        public ViewHolder(View itemView, Context context) {
            super(itemView);
            tvfriendName =(TextView) itemView.findViewById(R.id.textViewAllFriends);
            imageViewRemoveFriend =(ImageView) itemView.findViewById(R.id.imageViewAllFriends);
            hContext = (FriendsTabActivity) context;
        }
    }
}
