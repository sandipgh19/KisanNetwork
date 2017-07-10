package com.example.sandipghosh.kisannetwork;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_info);

        final Intent intent = getIntent();
        firstName = intent.getStringExtra("first");
        lastName = intent.getStringExtra("last");
        contactNo = intent.getStringExtra("contact");
        first = (TextView) findViewById(R.id.first);
        last = (TextView) findViewById(R.id.last);
        contact = (TextView) findViewById(R.id.contact);
        button = (Button) findViewById(R.id.send);

        first.setText("First Name: "+firstName);
        last.setText("Last Name: "+lastName);
        contact.setText("Contact No: "+contactNo);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactInfo.this, Compose.class);
                intent.putExtra("first",firstName);
                intent.putExtra("last",lastName);
                intent.putExtra("contact",contactNo);
                startActivity(intent);
            }
        });


    }
}
