package com.inclass.uncc.group23_inclass09zip;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.ocpsoft.prettytime.PrettyTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static android.R.id.icon;

/**
 * Created by Rishi on 06/11/17.
 */

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {
    private ArrayList<Chat> mdata;
    MessageActivity context;
int userid;
    public MessageAdapter(MessageActivity ctx,ArrayList<Chat> mdata,int uid) {
        this.mdata = mdata;
        userid=uid;
        this.context=ctx;
    }
/*MessageThreadAdapter(ArrayList<Course> mdata, DBManager dm) {
        this.mdata = mdata;

        this.dm = dm;
    }*/

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_message_list, parent, false);
        Context context = parent.getContext();
        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Chat messageThread =mdata.get(position);

        holder.objTextViewMessageThread.setText(messageThread.getMsg());
        holder.outname.setText(messageThread.getName());
        String time=messageThread.getTime();
        //SimpleDateFormat convertToDate=new SimpleDateFormat("EEE MMM dd hh:mm:ss z yyyy");
        SimpleDateFormat newDateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
        Date inputDate = null;
        try {
            inputDate = newDateFormat.parse(time);
            String formattedDateString = newDateFormat.format(inputDate);
            Date outputDate = newDateFormat.parse(formattedDateString);
            PrettyTime prettyTime=new PrettyTime();
            String prettyTimeString=prettyTime.format(outputDate);
            holder.outtime.setText(prettyTimeString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
   
      
        if(messageThread.getUserid()==userid)
        {  holder.objImageViewRemove.setVisibility(View.VISIBLE);
            //holder.objImageViewRemove.setImageResource(R.drawable.delete);

            }
            holder.objImageViewRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mdata.remove(position);
                    context.callDelete(messageThread.getMsgid());
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, mdata.size());

                }
            });

        // holder.objTextViewMessageThread.setText("hello");
       // if(messageThread.getUser_id().equals(""))
            //holder.objImageViewRemove.setVisibility(View.INVISIBLE);
    }

    @Override
    public int getItemCount() {
        return mdata.size();
        //return 2;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView objTextViewMessageThread;
        TextView outname;
        TextView outtime;
        ImageView objImageViewRemove;

        public ViewHolder(View itemView, Context context) {
            super(itemView);

            objTextViewMessageThread = (TextView) itemView.findViewById(R.id.outmsg);
            outname = (TextView) itemView.findViewById(R.id.outname);
            outtime = (TextView) itemView.findViewById(R.id.outtime);
            objImageViewRemove =(ImageView) itemView.findViewById(R.id.imgremv);
            objImageViewRemove.setVisibility(View.INVISIBLE);

        }

    }
}