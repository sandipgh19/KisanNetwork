package com.example.sandipghosh.kisannetwork;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by sandipghosh on 10/07/17.
 */

public class ContactInfo extends AppCompatActivity {

    TextView first,last,contact;
    String firstName,lastName,contactNo;
    Button button;
    String name;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_info);

        setTitle("Contact Info");
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        final Intent intent = getIntent();
        firstName = intent.getStringExtra("first");
        lastName = intent.getStringExtra("last");
        contactNo = intent.getStringExtra("contact");
        first = (TextView) findViewById(R.id.first);
        last = (TextView) findViewById(R.id.last);
        contact = (TextView) findViewById(R.id.contact);
        button = (Button) findViewById(R.id.send);

        //set the data
        first.setText("First Name: "+firstName);
        last.setText("Last Name: "+lastName);
        contact.setText("Contact No: "+contactNo);

        name = firstName+" "+lastName;

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactInfo.this, Compose.class);
                intent.putExtra("name",name);
                intent.putExtra("contact",contactNo);
                startActivity(intent);
            }
        });


    }
}
