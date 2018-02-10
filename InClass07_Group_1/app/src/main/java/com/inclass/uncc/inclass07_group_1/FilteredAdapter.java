package com.inclass.uncc.inclass07_group_1;

/**
 * Created by Rishi on 23/10/17.
 */


import android.content.Context;
import android.graphics.Bitmap;
import android.provider.ContactsContract;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Rishi on 23/10/17.
 */

public class FilteredAdapter  extends RecyclerView.Adapter<FilteredAdapter.ViewHolder> {
    DBManager dm;
int position;
    public FilteredAdapter(Context mcontext, ArrayList<ITunes> mlist) {
        super();
        this.mcontext = mcontext;
        this.mdata = mlist;
        dm = new DBManager(mcontext);

    }

    private static Context mcontext;
    Bitmap bm;
    private static ArrayList<ITunes> mdata;

    List<Integer> selectedpos = null;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.filtered_list, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(FilteredAdapter.ViewHolder holder, int position) {
        ITunes iTunes=mdata.get(position);
        position=position;
        holder.name.setText(iTunes.name);
        int priceleng = iTunes.getPrice().length();
        String price = iTunes.getPrice().substring(1, priceleng);
        holder.price.setText("USD" + price);
        Double doubleprice = Double.parseDouble(price);
        if (iTunes.image!= null) {


            try {
                bm = new AsyncImage().execute(iTunes.image).get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }


            if (bm != null) holder.img.setImageBitmap(bm);
        }

        if (doubleprice < 1.99)
            holder.starsilver.setImageResource(R.drawable.price_low);
        else if (doubleprice > 2 && doubleprice < 4.99)
            holder.starsilver.setImageResource(R.drawable.price_medium);

        else if (doubleprice > 5)
            holder.starsilver.setImageResource(R.drawable.price_high);
        // holder.text3.setText(email.summary);
        holder.iTunes=iTunes;
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

public class ViewHolder extends RecyclerView.ViewHolder{
    TextView name,price,text3;ImageView starsilver; ImageView deleteimg;   ImageView img;
    ITunes iTunes;
    public ViewHolder(View itemView) {
        super(itemView);
        this.iTunes = iTunes;
     img = (ImageView) itemView.findViewById(R.id.thumbnail);
        name = (TextView) itemView.findViewById(R.id.appname);
        price = (TextView) itemView.findViewById(R.id.filteredprice);
        starsilver  = (ImageView) itemView.findViewById(R.id.dollarimage);
        deleteimg= (ImageView) itemView.findViewById(R.id.imageButton2);
        deleteimg.setFocusable(true);
        //  text3=itemView.findViewById(R.id.textView3);
        itemView.findViewById(R.id.imageButton2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dm.deleteNote(mdata.get(position));
                Toast.makeText(mcontext,"Deleted App from Filetered List",Toast.LENGTH_SHORT).show();
                mdata.remove(position);
                Log.d("demo",""+position);
                notifyDataSetChanged();
               /* try {
                    new AsyncTaskJSON((AsyncTaskJSON.AsyncResponse) mcontext,mcontext).execute("https://itunes.apple.com/us/rss/toppaidapplications/limit=25/json");
                } catch (Exception e) {
                    e.printStackTrace();
                }*/
                Log.d("demo", "Clicked button" + iTunes.name);
            }
        });
    }}}
          /*  itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("demo","Clicked"+email.sender);
                }
            });*/
        //      }
   /* @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public ITunes getItem(int arg0) {
        return mlist.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @Override
    public View getView(final int arg0, View arg1, ViewGroup arg2) {
        ITunes entry = mlist.get(arg0);
        if (arg1 == null) {
            LayoutInflater inflater = LayoutInflater.from(mcontext);
            arg1 = inflater.inflate(R.layout.filtered_list, null);
        }
        // String imgurl = "http://image.tmdb.org/t/p/w154/" + entry.getImage();
       if (entry.getImage() != null) {
            ImageView img = (ImageView) arg1.findViewById(R.id.thumbnail);

            try {
                bm = new AsyncImage().execute(entry.getImage()).get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }


            if (bm != null) img.setImageBitmap(bm);
        }

        int priceleng = entry.getPrice().length();
        String price = entry.getPrice().substring(1, priceleng);
        TextView name = (TextView) arg1.findViewById(R.id.appname);
        name.setText(entry.getName());
        TextView tvprice = (TextView) arg1.findViewById(R.id.filteredprice);
        tvprice.setText("USD" + price);
        final ImageView starsilver = (ImageView) arg1.findViewById(R.id.dollarimage);
        final ImageView deleteimg = (ImageView) arg1.findViewById(R.id.imageButton2);
        Double doubleprice = Double.parseDouble(price);

        if (doubleprice < 1.99)
            starsilver.setImageResource(R.drawable.price_low);
        else if (doubleprice > 2 && doubleprice < 4.99)
            starsilver.setImageResource(R.drawable.price_medium);

        else if (doubleprice > 5)
            starsilver.setImageResource(R.drawable.price_high);
        deleteimg.setFocusable(true);
       // deleteimg.setFocusableInTouchMode(true);
        deleteimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dm.deleteNote(mlist.get(arg0));
                Toast.makeText(mcontext,"Deleted App from Filetered List",Toast.LENGTH_SHORT).show();
                mlist.remove(arg0);
notifyDataSetChanged();
            }
        });
        deleteimg.setFocusable(true);
      //  deleteimg.setFocusableInTouchMode(true);
        return arg1;
    }

}
*/






     /*   RecyclerView.Adapter<FilteredAdapter.ViewHolder> {
    ArrayList<ITunes> mdata;
    public FilteredAdapter(ArrayList<ITunes> mdata) {
        this.mdata = mdata;
    }
    @Override
    public FilteredAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.filtered_list,parent,false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FilteredAdapter.ViewHolder holder, int position) {
        ITunes iTunes=mdata.get(position);
        holder.name.setText(iTunes.name);
        holder.price.setText(iTunes.price);
        // holder.text3.setText(email.summary);
        holder.iTunes=iTunes;
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }
    public static  class ViewHolder extends RecyclerView.ViewHolder{
        TextView name,price,text3;
        ITunes iTunes;
        public ViewHolder(View itemView) {
            super(itemView);
            this.iTunes=iTunes;

            name= (TextView) itemView.findViewById(R.id.appname);
            price= (TextView) itemView.findViewById(R.id.price);
            //  text3=itemView.findViewById(R.id.textView3);
            itemView.findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("demo","Clicked button"+iTunes.name);
                }
            });
          /*  itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("demo","Clicked"+email.sender);
                }
            });*/
  //      }
//}}*/