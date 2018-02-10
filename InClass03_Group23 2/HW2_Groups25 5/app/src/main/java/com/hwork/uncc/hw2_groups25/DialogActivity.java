package com.hwork.uncc.hw2_groups25;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class DialogActivity extends AppCompatActivity {

    private ListView listView;
    public List<Contact> listContacts,selectedList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dialog);
        listContacts = new ArrayList<Contact>();
        selectedList = new ArrayList<Contact>();

        listView = (ListView) findViewById(R.id.listContact);
        listContacts = CreateContactActivity.retrieveContactDetails();
        /*alertDialogBuilder.setTitle("Your Title");
        alertDialogBuilder
                .setMessage("Click yes to exit!")
                .setCancelable(false)
                .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id ) {
                        listContacts.remove(position);
                        DialogActivity.this.finish();

                    }
                })
                .setNegativeButton("No",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {

                        dialog.cancel();
                    }
                });


        AlertDialog alertDialog = alertDialogBuilder.create();
*/

        //alertDialog.show();
    }
}
