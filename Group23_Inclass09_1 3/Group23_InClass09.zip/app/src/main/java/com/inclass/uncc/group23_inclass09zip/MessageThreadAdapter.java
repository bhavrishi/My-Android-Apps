package com.inclass.uncc.group23_inclass09zip;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Rishi on 07/11/17.
 */

public class MessageThreadAdapter extends RecyclerView.Adapter<MessageThreadAdapter.ViewHolder> {
    private ArrayList<MessageThread> mdata;
    MessageThreadsActivity context;int userid;

    public MessageThreadAdapter(ArrayList<MessageThread> mdata, MessageThreadsActivity context,int uid) {
        this.mdata = mdata;
        this.context=context;
        this.userid=uid;
    }
/*MessageThreadAdapter(ArrayList<Course> mdata, DBManager dm) {
        this.mdata = mdata;

        this.dm = dm;
    }*/

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_messagethread_display, parent, false);
        Context context = parent.getContext();
        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final MessageThread messageThread =mdata.get(position);

        holder.objTextViewMessageThread.setText(messageThread.getTitle());
        // holder.objTextViewMessageThread.setText("hello");
        holder.objImageViewRemove.setVisibility(View.INVISIBLE);
        if((messageThread.getUser_id())==userid)
            holder.objImageViewRemove.setVisibility(View.VISIBLE);
        holder.objImageViewRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mdata.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,mdata.size());
                //notifyDataSetChanged();
                context.deleteMessageThread(messageThread);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.createIntentforChatbox(messageThread);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mdata.size();
        //return 2;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView objTextViewMessageThread;
        ImageView objImageViewRemove;

        public ViewHolder(View itemView, Context context) {
            super(itemView);

            objTextViewMessageThread = (TextView) itemView.findViewById(R.id.textViewMsgThread);
            objImageViewRemove =(ImageView) itemView.findViewById(R.id.imageViewRemoveIcon);
        }

    }
}
