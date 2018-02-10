package com.hwork.uncc.hw2_groups25;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.hwork.uncc.hw2_groups25.CreateContactActivity.*;

public class DisplayActivity extends AppCompatActivity {
private ListView listView;
    public List<Contact>  listContacts,selectedList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_display_main);
        listContacts= new ArrayList<Contact>();
        selectedList= new ArrayList<Contact>();
        listView= (ListView) findViewById(R.id.listContact);

        listContacts=CreateContactActivity.retrieveContactDetails();
        /*listContacts.add(new Contact("Rishi","ë@uncc.edu","9898989898"));
        listContacts.add(new Contact("Bhavya","ë@uncc.edu","9898989898"));
        listContacts.add(new Contact("Doctor","ë@uncc.edu","9898989898"));*/
        ContactsAdapter cadapt=new ContactsAdapter(this,listContacts);
        listView.setAdapter(cadapt);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(DisplayActivity.this, listContacts.get(position).toString(),Toast.LENGTH_SHORT).show();
                selectedList.add ( listContacts.get(position));
                Bundle bundle = new Bundle();
                bundle.putSerializable("value", (Serializable) selectedList);
                bundle.putSerializable("position", position);
                Intent intent = new Intent(getApplicationContext(), DetailedDisplay.class);
                intent.putExtras(bundle);

                Log.d("demoto",listContacts.get(position).toString());
                Log.d("demoeq",selectedList.toString());
                startActivity(intent);
            }
        });
    }
}
