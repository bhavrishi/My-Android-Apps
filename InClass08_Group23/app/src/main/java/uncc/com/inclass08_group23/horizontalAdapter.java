/*
package uncc.com.inclass08_group23;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

*/
/**
 * Created by Bhargav Nallani on 10/23/2017.
 *//*


public class horizontalAdapter extends RecyclerView.Adapter<horizontalAdapter.ViewHolder>{

    ArrayList<ITunes> mData;
    MainActivity context;
    DatabaseDataManager dm;
    public  interface LinkData{
        void sendData(ITunes itune);
    }


    public horizontalAdapter(ArrayList<ITunes> mData, MainActivity context) {
        this.mData = mData;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_horizontal, parent, false);
        horizontalAdapter.ViewHolder viewHolder = new horizontalAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        final ITunes itunes = mData.get(position);
        holder.itune = itunes;
        dm = new DatabaseDataManager(this.context);

        holder.aName.setText(itunes.getName());
        holder.aPrice.setText(itunes.getPrice());
        //Log.d("large",itunes.thumbnailLarge+"");
        Picasso.with(this.context).load(itunes.getThumbnail()).into(holder.imageLarge);

        holder.delete_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("demo","clicked"); // delete and add event in sqlite
                dm.deleteNote(itunes);
                mData.remove(itunes);
                //  notifyDataSetChanged();
                context.InflateVerticalView(itunes);
                context.sendData(itunes);

            }
        });
       */
/* String str = itunes.getPrice();

        if (str != null && str.length() > 0 && str.charAt(0) == '$') {
            str = str.substring(1, str.length() - 1);
            Log.d("str",str);
        }*//*


        Double price = itunes.getPriceDouble();

        if(0 < price && price <1.99 ){
            holder.imagePrice.setImageDrawable(ContextCompat.getDrawable(this.context,R.drawable.price_low));
        }else if (2 < price && price <5.99){
            holder.imagePrice.setImageDrawable(ContextCompat.getDrawable(this.context,R.drawable.price_medium));
        }
        else if(6  <= price){
            holder.imagePrice.setImageDrawable(ContextCompat.getDrawable(this.context,R.drawable.price_high));
        }

       // Picasso.with(this.context).load(itunes.getThumbnailLarge()).into(holder.imageLarge);

    }


    @Override
    public int getItemCount() {
        return mData.size();*/
/* return data.size*//*

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView aName,aPrice;
        ImageView imageLarge,imagePrice,delete_icon;
        ITunes itune;

       // email email;

        public ViewHolder(View itemView) {
            super(itemView);
            aName = itemView.findViewById(R.id.appName1);
            aPrice = itemView.findViewById(R.id.appPrice1);
            imageLarge = itemView.findViewById(R.id.imageLarge);
            imagePrice = itemView.findViewById(R.id.priceImage);
            delete_icon = itemView.findViewById(R.id.delete_icon);
            this.itune = itune;



        }


    }
}
*/
