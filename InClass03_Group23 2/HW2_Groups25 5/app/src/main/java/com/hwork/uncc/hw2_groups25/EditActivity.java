package com.hwork.uncc.hw2_groups25;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EditActivity extends AppCompatActivity {
    private ListView listView;
    public List<Contact> listContacts,selectedList;
    private ContactsAdapter contactsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_display_main);
        listContacts = new ArrayList<Contact>();
        selectedList = new ArrayList<Contact>();
        listView = (ListView) findViewById(R.id.listContact);

        listContacts = CreateContactActivity.retrieveContactDetails();
        /*listContacts.add(new Contact("Rishi","ë@uncc.edu","9898989898"));
        listContacts.add(new Contact("Bhavya","ë@uncc.edu","9898989898"));
        listContacts.add(new Contact("Doctor","ë@uncc.edu","9898989898"));*/
        contactsAdapter = new ContactsAdapter(this, listContacts);
        listView.setAdapter(contactsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedList.add ( listContacts.get(position));
                Bundle bundle = new Bundle();
                //bundle.putSerializable("value", (Serializable) selectedList);
               bundle.putInt("position", position);
                Intent intent = new Intent(getApplicationContext(), EditSaveActivity.class);
                intent.putExtras(bundle);

                //Toast.makeText(EditActivity.this, listContacts.get(position).toString(), Toast.LENGTH_SHORT).show();
                Log.d("demoto",listContacts.get(position).toString());
                Log.d("demoeq",selectedList.toString());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(contactsAdapter != null) {
            contactsAdapter.notifyDataSetChanged();
        }
    }
}

