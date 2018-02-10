package itis5180.inclass7;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Assignment: InClass07
 * Name: FilteredAdapter.java
 * Date: 10/24/2017
 * Bradley Wright
 * Anvesh Kottapelli
 * Bhavya Gedi
 */

class FilteredAdapter extends RecyclerView.Adapter<FilteredAdapter.ViewHolder> {
    private ArrayList<ITunes> mdata;
    private ArrayList<ITunes> response;
    private DBManager dm;


    FilteredAdapter(ArrayList<ITunes> mdata, ArrayList<ITunes> response, DBManager dm) {
        this.mdata = mdata;
        this.response = response;
        this.dm = dm;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.filtered_list, parent, false);
        Context context = parent.getContext();
        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ITunes iTunes = mdata.get(position);
        holder.name.setText(iTunes.name);
        holder.price.setText(iTunes.price);

        Log.d("demo", iTunes.toString());

        String[] cost = iTunes.price.split("\\$");

        Picasso.with(holder.hcontext).load(iTunes.image).into(holder.thumbnail);

        Double doubleprice = Double.parseDouble(cost[1]);

        if (doubleprice < 1.99)
            holder.coin.setImageResource(R.drawable.price_low);
        else if (doubleprice > 2 && doubleprice < 4.99)
            holder.coin.setImageResource(R.drawable.price_medium);
        else if (doubleprice > 5)
            holder.coin.setImageResource(R.drawable.price_high);
        holder.iTunes = iTunes;
    }

    public void delete(int position, View v) { //removes the row

        mdata.remove(position);
        notifyItemRemoved(position);
        if (mdata.size() == 0) {
            v.findViewById(R.id.noApps).setVisibility(View.VISIBLE);
            Log.d("autolog", "1+");
        }
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, price, noapp;
        ImageView coin, thumbnail;
        ITunes iTunes;
        ImageButton deleteButton;
        Context hcontext;

        public ViewHolder(View itemView, Context context) {
            super(itemView);
            name = itemView.findViewById(R.id.appname);
            price = itemView.findViewById(R.id.price);
            coin = itemView.findViewById(R.id.dollarimage);
            thumbnail = itemView.findViewById(R.id.thumbnail);
            deleteButton = itemView.findViewById(R.id.delete);
            hcontext = context;
            noapp = itemView.getRootView().findViewById(R.id.noApps);

            itemView.findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("demo", "Clicked button" + iTunes.name);
                    View r = view.getRootView();

                    dm.deleteNote(mdata.get(getAdapterPosition()));

                    ListView lv = r.findViewById(R.id.lv);
                    response.add(mdata.get(getAdapterPosition()));
                    DisplayAdapter cadapt;
                    cadapt = new DisplayAdapter(r.getContext(), response);

                    Switch s = r.findViewById(R.id.switchOrder);

                    if (s.isChecked()) {
                        cadapt.sort(new Comparator<ITunes>() {
                            @Override
                            public int compare(ITunes iTunes, ITunes i2) {

                                return iTunes.getPrice().compareTo(i2.getPrice());
                            }
                        });
                    } else {
                        cadapt.sort(new Comparator<ITunes>() {
                            @Override
                            public int compare(ITunes iTunes, ITunes i2) {

                                return i2.getPrice().compareTo(iTunes.getPrice());
                            }
                        });
                    }

                    lv.setAdapter(cadapt);
                    delete(getAdapterPosition(), r);
                }
            });
        }
    }
}
