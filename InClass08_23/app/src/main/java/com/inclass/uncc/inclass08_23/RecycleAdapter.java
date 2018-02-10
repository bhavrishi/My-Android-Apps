package com.inclass.uncc.inclass08_23;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by Rishi on 30/10/17.
 */

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder>{
    private ArrayList<Recipe> mdata;     Bitmap bm;


    RecycleAdapter(ArrayList<Recipe> mdata) {
        this.mdata = mdata;


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list, parent, false);
        Context context = parent.getContext();
        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.d("demoadap",""+mdata.toString());
        Recipe recipe = mdata.get(position);
        holder.textViewTitle.setText(recipe.title);
        holder.textViewIngredients.setText(recipe.ingredients);
        holder.textViewURL.setText(recipe.url);


        Log.d("demoin adapter", recipe.toString());

       if((recipe.image.length())==0){
           holder.showimg.setImageResource(R.drawable.noimage);}
        else{
            try {  bm = new AsyncImage().execute(recipe.image).get();
        } catch(InterruptedException e){
            e.printStackTrace();
        } catch(ExecutionException e){
            e.printStackTrace();
        }
        holder.showimg.setImageBitmap(bm);
    }
        // Picasso.with(holder.hcontext).load().into(holder.deleteButton);


        holder.recipe = recipe;
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle,textViewIngredients,textViewURL;

        Recipe recipe;
        ImageView showimg;
        Context hcontext;
        public ViewHolder(View itemView, final Context context) {
            super(itemView);
            textViewTitle = (TextView) itemView.findViewById(R.id.textViewTitle);
            textViewIngredients = (TextView) itemView.findViewById(R.id.textViewIngredients);
            textViewURL = (TextView) itemView.findViewById(R.id.textViewURL);

            showimg = (ImageView) itemView.findViewById(R.id.imageView);
            hcontext = context;

            itemView.findViewById(R.id.textViewURL).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(recipe.url.toString().length()>0) {
                        try {
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(recipe.url.toString().toString()));
                            context.startActivity(intent);
                        }
                        catch (Exception ex){
                            Toast.makeText(context, "Invalid Uri", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });

            itemView.findViewById(R.id.imageView).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {/*
                    Log.d("demo","clicked image "+getAdapterPosition());
                    Log.d("demo","clicked image "+mdata.get(0));
                    dm.deleteNote(mdata.get(getAdapterPosition()));
                    mdata.remove(getAdapterPosition());
                    notifyDataSetChanged();*/

                }
            });
        }
    }
}
