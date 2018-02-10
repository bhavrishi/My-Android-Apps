package uncc.com.group25_hw06;

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
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Rishi on 02/11/17.
 */

public class InstructorAdapter extends RecyclerView.Adapter<InstructorAdapter.ViewHolder> {
    private ArrayList<Instructor> mdata;
    private static RecyclerViewClickListener itemListener;
    int lastCheckedPosition = 0;

    //private InstructorDBManager dm;
    Bitmap bitmap;

    InstructorAdapter(ArrayList<Instructor> mdata, RecyclerViewClickListener itemListener) {
        this.mdata = mdata;


        this.itemListener=itemListener;
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
        holder.insname.setText(ins.getFname());
       // Log.d("insimg",);
        if(ins.getImg()!=null)
        {
            try {
                byte [] encodeByte= Base64.decode(ins.getImg(), Base64.DEFAULT);
                bitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
                Log.d("bitmap",""+bitmap);

            } catch(Exception e) {
                e.getMessage();

            }
        }
        Log.d("demoin adapter", ins.toString());
holder.insimg.setImageBitmap(bitmap);
        //   holder.deleteButton.setImageResource(R.drawable.rsz_remove);

        // Picasso.with(holder.hcontext).load().into(holder.deleteButton);


        holder.ins = ins;

        holder.radioButtonInst.setChecked(position == lastCheckedPosition);

    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public interface RecyclerViewClickListener {

        public void recyclerViewListClicked(View v, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView insname;

        Instructor ins;
        ImageView insimg;
        Context hcontext;
        RadioButton radioButtonInst;

        public ViewHolder(View itemView, Context context) {
            super(itemView);
            insname = (TextView) itemView.findViewById(R.id.insname);


            insimg = (ImageView) itemView.findViewById(R.id.thumbnail);
            radioButtonInst = (RadioButton) itemView.findViewById(R.id.radioButtonInst);
            hcontext = context;
            itemView.setOnClickListener(this);

            radioButtonInst.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    lastCheckedPosition = getPosition();
                    //because of this blinking problem occurs so
                    //i have a suggestion to add notifyDataSetChanged();
                    //   notifyItemRangeChanged(0, list.length);//blink list problem
                    notifyDataSetChanged();

                }
            });

          /*  itemView.findViewById(R.id.recyclerView).

                    setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick (View view){
                            itemListener.recyclerViewListClicked(view, hcontext.);
                        }
                    });*/

        }

        @Override
        public void onClick(View v) {


            itemListener.recyclerViewListClicked(v, this.getPosition());
        }
    }
}
