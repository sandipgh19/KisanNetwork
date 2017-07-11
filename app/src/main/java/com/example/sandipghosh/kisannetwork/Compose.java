package com.example.sandipghosh.kisannetwork;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by sandipghosh on 10/07/17.
 */

public class Compose extends AppCompatActivity {

    EditText otp;
    Button send;
    public static final String DATA_URL = "https://sandipgh19.000webhostapp.com/kisanNetwork/data.php";
    String formattedDate;
    String name,contact;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);

        setTitle("Compose");
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        otp = (EditText) findViewById(R.id.otp);

        send = (Button) findViewById(R.id.send);
        //get all the data from privious page
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        contact = intent.getStringExtra("contact");


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                currentDate();
            }
        });

        randomNo();
    }
    //generate random no
    private void randomNo() {
        Random r = new Random(System.currentTimeMillis());
        String no = ""+(2 + r.nextInt(2)) * 1000
                + r.nextInt(100);

        otp.setText("Hi. Your OTP is: " +no);

    }
    //current system data and time
    public void currentDate() {

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        formattedDate = df.format(c.getTime());

        sendData();
    }


    //send to the server
    private void sendData() {
        final ProgressDialog loading = ProgressDialog.show(this,"","Please wait...",false,false);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, DATA_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) { //after successfull response
                        Log.i("MY TEST",response);

                        loading.dismiss();

                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            String message;
                            boolean success = jsonResponse.getBoolean("error");
                            message =jsonResponse.getString("message") ;

                            if(!success) {

                                Intent intent = new Intent(Compose.this,MainActivity.class);
                                startActivity(intent);

                            }
                            Toast.makeText(Compose.this,message,Toast.LENGTH_LONG).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() { //if getting error from server
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
                        Toast.makeText(Compose.this,error.getMessage().toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();

                params.put("name",name);
                params.put("otp",otp.getText().toString());
                params.put("contact",contact);
                params.put("date",formattedDate);

                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
