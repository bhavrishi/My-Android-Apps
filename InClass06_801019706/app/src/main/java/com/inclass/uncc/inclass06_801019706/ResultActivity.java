package com.inclass.uncc.inclass06_801019706;
/*#Assignment: In Class Assignment $
     #   File Name : Table1_InClass06.zip
        #Full Name:  Bhavya Gedi*/
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class ResultActivity extends AppCompatActivity {
    Bitmap bm;
     ArrayList<Receipe> rlist;
    int count=0,length;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_result);
        Bundle b = getIntent().getExtras();
    rlist =new ArrayList<Receipe>();
        if (b != null) {
            rlist = (ArrayList<Receipe>) b.getSerializable("ALIST");
            Log.d("demointent", rlist.toString());
        }
final int length=rlist.size();

      final  TextView title= (TextView) findViewById(R.id.texttitle);
        title.setText(rlist.get(0).getTitle());
        final TextView ing= (TextView) findViewById(R.id.textingredients);
        ing.setText(rlist.get(0).getIngredients());
        final TextView url= (TextView) findViewById(R.id.texturl);
        url.setText(rlist.get(0).getUrl());

        ImageView img= (ImageView) findViewById(R.id.imgresult);
        Button fin= (Button) findViewById(R.id.button);
        ImageView imgnext= (ImageView) findViewById(R.id.imgnext);
        ImageView imgpre= (ImageView) findViewById(R.id.imgprevious);
        ImageView imgfirst= (ImageView) findViewById(R.id.imgfirst);
        ImageView imglast= (ImageView) findViewById(R.id.imglast);

url.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if(url.getText().length() !=0) {
            try {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(rlist.get(0).getUrl().toString().trim()));
                startActivity(intent);
            }
            catch (Exception ex){
                Toast.makeText(ResultActivity.this, "Invalid Uri", Toast.LENGTH_SHORT).show();
            }
        }
    }
});
        try {
       bm =new AsyncImage().execute("https://c1.staticflickr.com/5/4286/35513985750_2690303c8b_z.jpg").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        img.setImageBitmap(bm);
        Log.d("demo",""+bm);

imglast.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        title.setText(rlist.get(length-1).getTitle());
        url.setText(rlist.get(length-1).getUrl());
        ing.setText(rlist.get(length-1).getIngredients());
    }
});
        imgfirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title.setText(rlist.get(0).getTitle());
                url.setText(rlist.get(0).getUrl());
                ing.setText(rlist.get(0).getIngredients());
            }
        });
        imgnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                title.setText(rlist.get(count).getTitle());
                url.setText(rlist.get(count).getUrl());
                ing.setText(rlist.get(count).getIngredients());
            }
        });
        imgpre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count--;
                title.setText(rlist.get(count).getTitle());
                url.setText(rlist.get(count).getUrl());
                ing.setText(rlist.get(count).getIngredients());
            }
        });

        fin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
