package com.midterm.uncc.recyclervw;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
private RecyclerView.Adapter madapter;
    private RecyclerView.LayoutManager mlayoutmanager;
    ArrayList<Email> data=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_m);
for(int i=0;i<10;i++)
{
    data.add(new Email("Subject"+i,"Summary"+i,"Sender"+i));
}
        RecyclerView rv= (RecyclerView) findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        mlayoutmanager =new LinearLayoutManager(this);
        rv.setLayoutManager(mlayoutmanager);
        madapter=new Emailhandler(data);
        rv.setAdapter(madapter);
    }
}
