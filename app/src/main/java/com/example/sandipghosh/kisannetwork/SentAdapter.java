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

public class SentAdapter extends ArrayAdapter<String> {

    private String[] name;
    private String[] date;
    private String[] otp;
    private Activity context;

    public SentAdapter(Activity context, String[] name, String[] date, String[] otp) {
        super(context, R.layout.activity_item, name);
        this.context = context;
        this.name = name;
        this.date = date;
        this.otp = otp;
    }

    //item data
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.activity_sent_item, null, true);
        TextView textView_name = (TextView) listViewItem.findViewById(R.id.name);
        TextView textView_date = (TextView) listViewItem.findViewById(R.id.date);
        TextView textView_otp = (TextView) listViewItem.findViewById(R.id.otp);

        textView_name.setText(name[position]);
        textView_date.setText(date[position]);
        textView_otp.setText("OTP: "+otp[position]);

        return listViewItem;
    }
}
