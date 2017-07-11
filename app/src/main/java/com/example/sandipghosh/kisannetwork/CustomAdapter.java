package com.example.sandipghosh.kisannetwork;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by sandipghosh on 10/07/17.
 */

public class CustomAdapter extends ArrayAdapter<String> {

    private String[] first;
    private String[] last;
    private String[] contact;
    private Activity context;

    public CustomAdapter(Activity context, String[] first, String[] last, String[] contact) {
        super(context, R.layout.activity_item, first);
        this.context = context;
        this.first = first;
        this.last = last;
        this.contact = contact;
    }
    //list item init.
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.activity_item, null, true);
        TextView textView_first = (TextView) listViewItem.findViewById(R.id.first);
        TextView textView_last = (TextView) listViewItem.findViewById(R.id.last);
        TextView textView_contact = (TextView) listViewItem.findViewById(R.id.contact);

        textView_first.setText(first[position]);
        textView_last.setText(last[position]);
        textView_contact.setText(contact[position]);

        return listViewItem;
    }
}
