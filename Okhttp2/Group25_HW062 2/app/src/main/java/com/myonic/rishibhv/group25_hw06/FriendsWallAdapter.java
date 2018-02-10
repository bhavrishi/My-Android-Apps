package com.myonic.rishibhv.group25_hw06;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.ocpsoft.prettytime.PrettyTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Rishi on 15/11/17.
 */

public class FriendsWallAdapter extends RecyclerView.Adapter<FriendsWallAdapter.ViewHolder> {
    private ArrayList<Post> mdata;
    Activity context;

    public FriendsWallAdapter( Activity context,ArrayList<Post> mdata) {
        this.mdata = mdata;
        this.context=context;
    }
/*MessageThreadAdapter(ArrayList<Course> mdata, DBManager dm) {
        this.mdata = mdata;

        this.dm = dm;
    }*/

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_post_list, parent, false);
        Context context = parent.getContext();
        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Post post =mdata.get(position);

        holder.name.setText(post.getName());
        // holder.objTextViewM
        // essageThread.setText("hello");
        holder.msg.setText(post.getMsg());
        String time=post.getTime();
        //SimpleDateFormat convertToDate=new SimpleDateFormat("EEE MMM dd hh:mm:ss z yyyy");
        SimpleDateFormat newDateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
        Date inputDate = null;
        try {
            inputDate = newDateFormat.parse(time);
            String formattedDateString = newDateFormat.format(inputDate);
            Date outputDate = newDateFormat.parse(formattedDateString);
            PrettyTime prettyTime=new PrettyTime();
            String prettyTimeString=prettyTime.format(outputDate);
            holder.time.setText(prettyTimeString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // context.callMethod(post.getName());
             /* Intent i=new Intent(context,WallActivity.class);
              context.startActivity(i);*/
            }
        });

    }

    @Override
    public int getItemCount() {
        return mdata.size();
        //return 2;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView time,msg,name;


        public ViewHolder(View itemView, Context context) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.vwname);
            msg =(TextView) itemView.findViewById(R.id.vwmsg);
            time=(TextView)itemView.findViewById(R.id.vwtime);
        }

    }
}

