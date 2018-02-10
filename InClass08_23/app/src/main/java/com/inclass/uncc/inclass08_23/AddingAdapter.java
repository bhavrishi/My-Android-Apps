package com.inclass.uncc.inclass08_23;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;


public class AddingAdapter extends RecyclerView.Adapter<AddingAdapter.ViewHolder> {
    Context ctx; RecyclerView rv2;
    private RecyclerView.Adapter madapter;
    private RecyclerView.LayoutManager mlayoutmanager;
    ArrayList<Integer> clicked;
    public static ArrayList<String> input;
    private String[] mDataset;
    String temp=null;
    public AddingAdapter(Context activity, ArrayList<Integer> clicked) {
        this.ctx=activity;
        this.clicked=clicked;
        input=new ArrayList<String>();
        mDataset= new String[5];
    }

    @Override
    public AddingAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_dynamic, parent, false);
        Context context = parent.getContext();
        return new ViewHolder(view, context/*new MyCustomEditTextListener()*/);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.textvw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                  }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mDataset[clicked.size()] = holder.textvw.getText().toString();
               // pos=holder.getAdapterPosition();
                Log.d("rosy_demoPos",Integer.toString(holder.getAdapterPosition()));
                temp=mDataset[clicked.size()];
                Log.d("demostring", mDataset[clicked.size()]+" "+temp);


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        input.add(temp);
        Log.d("demostringa", temp+" "+holder.getAdapterPosition()+" "+clicked.size());
      //  holder.myCustomEditTextListener.updatePosition(holder.getAdapterPosition());
     //  input.add(mDataset.get());
      // input.add(holder.textvw.getText().toString()) ;
      //  Log.d("demostring",mDataset.get(holder.getAdapterPosition()) );

        holder.imgadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.imgadd.getTag().equals("add")&&clicked.size()<4) {
                           /* if(clicked.size() == 3)
                            {
                                imgadd.setTag("remove");
                                imgadd.setImageResource(R.drawable.remove);

                            }*/
                    holder.imgadd.setTag("remove");
                    holder.imgadd.setImageResource(R.drawable.remove);

                    //  RecipePuppyFragment.mlayoutmanager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
                    // rv2.setLayoutManager(mlayoutmanager);
                    //  madapter = new AddingAdapter(context, clicked);
                    clicked.add(0, clicked.size() + 1);

                    Log.d("RosyDemo",clicked.toString());
                    //notifyItemInserted(0);  // Rosy

                    RecipePuppyFragment.madapter.notifyItemInserted(clicked.size() -1);



                    //clicked.add(clicked.size() + 1);
                    Log.d("demo", "inadp2");
                    //rv2.setAdapter(madapter);
                    // RecipePuppyFragment.madapter.notifyDataSetChanged();
                } else {
                    if(holder.imgadd.getTag().equals("remove")) {
                        Toast.makeText(ctx, "Removing Ingredient", Toast.LENGTH_SHORT).show();
                        clicked.remove(holder.getAdapterPosition());
                        input.remove(input.indexOf(input.get(holder.getAdapterPosition())));
                        holder.textvw.setText("");
                        holder.imgadd.setImageResource(R.drawable.add);
                        holder.imgadd.setTag("add");


                        /*holder.textvw.setText("");
                        holder.imgadd.setImageResource(R.drawable.add);*/
                        RecipePuppyFragment.madapter.notifyItemRemoved(position);

                        //RecipePuppyFragment.madapter.notifyDataSetChanged();

                        //if(clicked.size() )


                    }
                    else if(clicked.size()>=4)
                    {
                        Toast.makeText(ctx, "Only 5 ingredients are permitted", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
/*
    @Override
    public void onBindViewHolder(RecycleAdapter.ViewHolder holder, int position) {
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
    }*/

    @Override
    public int getItemCount() {
        return clicked.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        EditText textvw;
        ImageView imgadd;
        Context hcontext;
        public EditText mEditText;
    //    public MyCustomEditTextListener myCustomEditTextListener;

        public ViewHolder(View itemView, final Context context) {

            super(itemView);
            textvw= (EditText) itemView.findViewById(R.id.editTextIngredient);
          //  this.myCustomEditTextListener = myCustomEditTextListener;

          /* this.textvw.addTextChangedListener(myCustomEditTextListener);*/


            imgadd= (ImageView) itemView.findViewById(R.id.imageViewbutton);
            rv2    = (RecyclerView) itemView.findViewById(R.id.rvinput);

Log.d("demo","inadp");

            imgadd.setTag("add");

           /*     itemView.findViewById(R.id.imageViewbutton).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(imgadd.getTag().equals("add")&&clicked.size()<4) {
                           *//* if(clicked.size() == 3)
                            {
                                imgadd.setTag("remove");
                                imgadd.setImageResource(R.drawable.remove);

                            }*//*
                            imgadd.setTag("remove");
                            imgadd.setImageResource(R.drawable.remove);

                            //  RecipePuppyFragment.mlayoutmanager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
                            // rv2.setLayoutManager(mlayoutmanager);
                            //  madapter = new AddingAdapter(context, clicked);
                            clicked.add(0, clicked.size() + 1);

                            Log.d("RosyDemo",clicked.toString());
                            //notifyItemInserted(0);  // Rosy

                            notifyItemInserted(clicked.size() -1);

                            //clicked.add(clicked.size() + 1);
                            Log.d("demo", "inadp2");
                            //rv2.setAdapter(madapter);
                           // RecipePuppyFragment.madapter.notifyDataSetChanged();
                        } else {
                            if(imgadd.getTag().equals("remove")) {
                                Toast.makeText(context, "Removing Ingredient", Toast.LENGTH_SHORT).show();
                                clicked.remove(getAdapterPosition());
                                input.remove(input.indexOf(input.get(getAdapterPosition())));

                                textvw.setText("");
                                RecipePuppyFragment.madapter.notifyDataSetChanged();

                                //if(clicked.size() )
                                imgadd.setTag("add");
                                imgadd.setImageResource(R.drawable.add);

                            }
                            else if(clicked.size()>=4)
                            {
                                Toast.makeText(context, "Only 5 ingredients are permitted", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });*/


        }
    }
   /* private class MyCustomEditTextListener implements TextWatcher {
        private int position;

        public void updatePosition(int position) {
            this.position = position;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            // no op
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (charSequence.length() > 0) {
                mDataset[position] =
                // mDataset[position] = charSequence.toString();
            }
        }
        @Override
        public void afterTextChanged(Editable e) {
         input.add(mDataset[position]);
            Log.d("demostring",input.get(position));
        }
    }*/
}
