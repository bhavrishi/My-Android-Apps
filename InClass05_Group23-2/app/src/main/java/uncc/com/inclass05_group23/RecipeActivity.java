package uncc.com.inclass05_group23;
/*#Assignment: In Class Assignment $
     #   File Name : Group#_InClass04.zip
        #Full Name: Rosy Azad, Bhavya Gedi*/

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class RecipeActivity extends AppCompatActivity {


    TextView objTitle;
    TextView objIngredients;
    TextView objUrl;
    ImageView objImage;
    Button objFinish;
    ImageButton objFirst;
    ImageButton objPrevious;
    ImageButton objNext;
    ImageButton objLast;
    ArrayList<Recipe> objRecipeList = new ArrayList<>();
    int count =0;
    int sizeOfList;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        objTitle =(TextView) findViewById(R.id.textViewTitle);
        objIngredients =(TextView)findViewById(R.id.textViewIngredients);
        objUrl =(TextView)findViewById(R.id.textViewURL);
        objImage =(ImageView) findViewById(R.id.imageView);
        objFinish =(Button) findViewById(R.id.buttonFinish);
        objFirst =(ImageButton) findViewById(R.id.imageButtonFirst);
        objPrevious =(ImageButton)findViewById(R.id.imageButtonPrevious);
        objNext =(ImageButton)findViewById(R.id.imageButtonNext);
        objLast =(ImageButton)findViewById(R.id.imageButtonLast);

        new AsyncActivityRecipe().execute("http://www.recipepuppy.com/api/?i=onions,garlic&q=omelet");


        objFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        objFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count = 0;
                setRecipeToUI(objRecipeList.get(count));

            }
        });

        objLast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count = objRecipeList.size() - 1;
                //Log.d("demoImage",objRecipeList.get(count).getImage());
               /* if ((objRecipeList.get(count).getImage()) != null)
                {
                    Log.d("demoImage",objRecipeList.get(count).getImage());
                    setRecipeToUI(objRecipeList.get(count));

            }
            else{
                    objRecipeList.get(count).setImage("https://www.google.com/imgres?imgurl=https%3A%2F%2Fupload.wikimedia.org%2Fwikipedia%2Fcommons%2Fa%2Fac%2FNo_image_available.svg&imgrefurl=https%3A%2F%2Fen.wikipedia.org%2Fwiki%2FFile%3ANo_image_available.svg&docid=029W-ajBtZqZzM&tbnid=9e9JeDQI0DBZrM%3A&vet=10ahUKEwjVz5-C3cHWAhVG4iYKHXb3DxMQMwg8KAAwAA..i&w=300&h=300&bih=782&biw=1440&q=no%20image&ved=0ahUKEwjVz5-C3cHWAhVG4iYKHXb3DxMQMwg8KAAwAA&iact=mrc&uact=8");
            */        setRecipeToUI(objRecipeList.get(count));
               // }

            }
        });

        objNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count >= objRecipeList.size() )
                {
                    Toast.makeText(RecipeActivity.this, "It is the last element", Toast.LENGTH_SHORT).show();
                }
                else {

                    setRecipeToUI(objRecipeList.get(count+1));
                    count++;
                }
            }
        });


        objPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count <= 0 )
                {
                    Toast.makeText(RecipeActivity.this, "It is the last element", Toast.LENGTH_SHORT).show();
                }
                else {
                    setRecipeToUI(objRecipeList.get(count - 1));
                    count--;
                }
            }
        });

        objUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(objUrl.getText().length() !=0) {
                    try {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(objUrl.getText().toString()));
                        startActivity(intent);
                    }
                    catch (Exception ex){
                        Toast.makeText(RecipeActivity.this, "Invalid Uri", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });




    }

    private void setRecipeToUI(Recipe objRecipe) {

        //if (objRecipe.getImage() == "")
         //   new SetImage().execute("https://www.google.com/imgres?imgurl=https%3A%2F%2Fupload.wikimedia.org%2Fwikipedia%2Fcommons%2Fa%2Fac%2FNo_image_available.svg&imgrefurl=https%3A%2F%2Fen.wikipedia.org%2Fwiki%2FFile%3ANo_image_available.svg&docid=029W-ajBtZqZzM&tbnid=9e9JeDQI0DBZrM%3A&vet=10ahUKEwjVz5-C3cHWAhVG4iYKHXb3DxMQMwg8KAAwAA..i&w=300&h=300&bih=782&biw=1440&q=no%20image&ved=0ahUKEwjVz5-C3cHWAhVG4iYKHXb3DxMQMwg8KAAwAA&iact=mrc&uact=8");
        //  objRecipeList.get(count).setImage("https://www.google.com/imgres?imgurl=https%3A%2F%2Fupload.wikimedia.org%2Fwikipedia%2Fcommons%2Fa%2Fac%2FNo_image_available.svg&imgrefurl=https%3A%2F%2Fen.wikipedia.org%2Fwiki%2FFile%3ANo_image_available.svg&docid=029W-ajBtZqZzM&tbnid=9e9JeDQI0DBZrM%3A&vet=10ahUKEwjVz5-C3cHWAhVG4iYKHXb3DxMQMwg8KAAwAA..i&w=300&h=300&bih=782&biw=1440&q=no%20image&ved=0ahUKEwjVz5-C3cHWAhVG4iYKHXb3DxMQMwg8KAAwAA&iact=mrc&uact=8");
   // else

        new SetImage().execute(objRecipe.getImage());
       // Log.d("demo",objRecipe.getTitle());
        objTitle.setText(objRecipe.getTitle());
        objIngredients.setText(objRecipe.getIngredients());
        objUrl.setText(objRecipe.getUrl());
    }



    public void getRecipes(ArrayList<Recipe> objRecipe) {
        for (Recipe recipe:objRecipe
                ) {
            System.out.println(recipe.toString());

        }

        objRecipeList = objRecipe;
        sizeOfList = objRecipeList.size();
        if(count<sizeOfList) {
            setRecipeToUI(objRecipeList.get(count));
            //count++;
        }
    }

    public class SetImage extends AsyncTask<String,Void,Bitmap> {

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if(bitmap!=null)
                objImage.setImageBitmap(bitmap);
        }



        @Override
        protected Bitmap doInBackground(String... params) {
            String urldisplay = params[0];
            Bitmap bitmap = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                bitmap = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }
    }

    public class AsyncActivityRecipe extends AsyncTask<String,Void,ArrayList<Recipe>> {

      //  uncc.com.inclass05_group23.AsyncActivityRecipe.Idata recipeActivity;

   /* public AsyncActivityRecipe(Idata mainActivity) {
        this.recipeActivity = mainActivity;
    }*/

        /*public AsyncActivityRecipe(uncc.com.inclass05_group23.AsyncActivityRecipe.Idata reecipeActivity) {
            recipeActivity=reecipeActivity;
        }*/


        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(RecipeActivity.this);
            dialog.setMessage("Doing something, please wait.");
            dialog.show();
        }


        @Override
        protected void onPostExecute(ArrayList<Recipe> recipes) {
            super.onPostExecute(recipes);
            //RecipeActivity recipeActivity =new RecipeActivity();
            if (dialog.isShowing()) {
                dialog.dismiss();
            }

                getRecipes(recipes);
        }


        @Override
        protected ArrayList<Recipe> doInBackground(String... params) {
            BufferedReader reader =null;
            StringBuilder sb =null;
            try {
                URL url =new URL(params[0]);
                HttpURLConnection con =(HttpURLConnection)url.openConnection();
                con.setRequestMethod("GET");
                con.connect();
                int statuscode = con.getResponseCode();
                if(statuscode== HttpURLConnection.HTTP_OK) {

                    reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    sb = new StringBuilder();
                    String line = "";
                    while ((line = reader.readLine()) != null) {
                        sb.append(line + "\n");

                    }
                }
                return RecipeUtil.jsonParsor(sb.toString());



            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                try {
                    if(reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

       /* public static interface Idata{

            public void getRecipes(ArrayList<Recipe> objArticle);
        }*/


    }
}
