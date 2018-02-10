package com.hwork.uncc.hw2_groups25;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DeleteActivity extends AppCompatActivity {
    private ListView listView;
    public List<Contact> listContacts,selectedList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_display_main);
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(DeleteActivity.this);
        listContacts = new ArrayList<Contact>();
        selectedList = new ArrayList<Contact>();

        listView = (ListView) findViewById(R.id.listContact);
        listContacts = CreateContactActivity.retrieveContactDetails();
        ContactsAdapter cadapt = new ContactsAdapter(this, listContacts);
        listView.setAdapter(cadapt);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
               // Toast.makeText(DeleteActivity.this, listContacts.get(position).toString(), Toast.LENGTH_SHORT).show();

                alertDialogBuilder.setTitle("Do u really want to delete?");
                alertDialogBuilder
                        .setMessage("Click yes to delete!")
                        .setCancelable(false)

                        .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id ) {
listContacts.remove(position);
                                DeleteActivity.this.finish();

                            }
                        })
                        .setNegativeButton("No",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {

                                dialog.cancel();
                            }
                        });


                AlertDialog alertDialog = alertDialogBuilder.create();


                alertDialog.show();
                /*Intent intent = new Intent(getApplicationContext(), DialogActivity.class);
intent.putExtra("position",position);
                startActivity(intent);*/
            }
        });

    }
}
