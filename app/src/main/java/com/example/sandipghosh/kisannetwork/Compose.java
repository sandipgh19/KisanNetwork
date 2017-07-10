package com.example.sandipghosh.kisannetwork;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Random;

/**
 * Created by sandipghosh on 10/07/17.
 */

public class Compose extends AppCompatActivity {

    EditText otp;
    Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);

        otp = (EditText) findViewById(R.id.otp);

        send = (Button) findViewById(R.id.send);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });

        randomNo();

    }

    private void randomNo() {
        Random r = new Random(System.currentTimeMillis());
        String no = ""+(1 + r.nextInt(2)) * 1000
                + r.nextInt(100);

        otp.setText("Hi. Your OTP is: " +no);

    }
}
