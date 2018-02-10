package com.myonic.rishibhv.group25_hw06;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import junit.framework.Test;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by rosyazad on 20/11/17.
 */

public class RequestPendingAdapter extends RecyclerView.Adapter<RequestPendingAdapter.ViewHolder> {

    private ArrayList<User> mdata;
    Context context;

    public RequestPendingAdapter(Context context, ArrayList<User> mdata) {
        this.mdata = mdata;
        this.context=context;
    }
    @Override
    public RequestPendingAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_requests_pending, parent, false);
        Context context = parent.getContext();
        return new ViewHolder(view, context);    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
    @Override
    public void onBindViewHolder(final RequestPendingAdapter.ViewHolder holder, final int position) {
        final User user = mdata.get(position);
        holder.objTvUserRqstPending.setText(user.getName());
        if(user.getPassword().equals("Sent")){
            holder.objImageAccept.setVisibility(View.INVISIBLE);
        }

        holder.objImageAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //holder.itemView.setVisibility(View.INVISIBLE);
                Log.d("mdata1",mdata.toString());
                holder.hContext.onAcceptRequest(user.getId(),user);
                mdata.remove(position);
                notifyDataSetChanged();
                Log.d("mdata",mdata.toString());
            }
        });

        holder.objImageReject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                holder.hContext.onRejectRequest(user.getId(),user);
                mdata.remove(position);
                notifyDataSetChanged();

            }
        });
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView objTvUserRqstPending;
        ImageView objImageAccept;
        ImageView objImageReject;
        FriendsTabActivity hContext;
        public ViewHolder(View itemView , Context context) {
            super(itemView);
            objTvUserRqstPending =(TextView) itemView.findViewById(R.id.textViewRequestPendingUser);
            objImageAccept =(ImageView) itemView.findViewById(R.id.imageViewAcceptRequest);
            objImageReject =(ImageView) itemView.findViewById(R.id.imageViewRejectRequest);
            hContext =(FriendsTabActivity) context;
        }
    }
}
