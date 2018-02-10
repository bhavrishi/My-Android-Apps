package com.hwork.uncc.datapassing;

import android.os.AsyncTask;

import java.util.LinkedList;

/**
 * Created by Rishi on 24/09/17.
 */

public class Async extends AsyncTask<String, Void,LinkedList<String>> {
   // MainActivity ma;
IData ma;


    public Async(IData mainActivity) {
        this.ma = mainActivity;
    }

    @Override
    protected void onPostExecute(LinkedList<String> strings) {
       ma.setupdata(strings);
        super.onPostExecute(strings);
    }

    @Override
    protected LinkedList<String> doInBackground(String... params) {
        LinkedList<String> llist=new LinkedList<String>();
        llist.add("Öne");

        llist.add("Two");
        llist.add("Three");
        llist.add("Four");

        return llist;
    }
    static public interface IData{
public void setupdata(LinkedList<String> strings);
    }
}
