package com.example.sandipghosh.kisannetwork;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;


import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sandipghosh on 10/07/17.
 */

public class Conatct extends Fragment {

    View rootView;
    ListView listView;

    private static final String List_URL = "https://sandipgh19.000webhostapp.com/kisanNetwork/contact.php";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.activity_contact, container, false);
        listView = (ListView) rootView.findViewById(R.id.conatct_list);


        if(isNetworkAvailable()) {

            getData();

        } else {

            Toast.makeText(getActivity(),"Please Check your Internet Connection",Toast.LENGTH_LONG).show();
        }

        return rootView;
    }

    //get all the data from server
    private void getData() {

        final ProgressDialog loading = ProgressDialog.show(getActivity(),"","Please wait...",false,false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST,List_URL ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {  //after successfull response
                        Log.i("MY TEST",response);

                        loading.dismiss();

                        //retrie the data comming from server
                        showJSON(response);
                    }
                },
                new Response.ErrorListener() {  //after error response
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        loading.dismiss();
                        Toast.makeText(getActivity(),error.getMessage().toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();


                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

    //retrive data
    private void showJSON(String json){
        ParseJSON pj = new ParseJSON(json);
        pj.parseJSON();
        CustomAdapter ca = new CustomAdapter(getActivity(), ParseJSON.first,ParseJSON.last,ParseJSON.contact);
        listView.setAdapter(ca);

        //tap on list item
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //retrive tap list item data
                String name = ((TextView) view.findViewById(R.id.first)).getText().toString();
                String last = ((TextView) view.findViewById(R.id.last)).getText().toString();
                String contact = ((TextView) view.findViewById(R.id.contact)).getText().toString();

                Intent intent = new Intent(getActivity(), ContactInfo.class);
                intent.putExtra("first",name);
                intent.putExtra("last",last);
                intent.putExtra("contact",contact);
                startActivity(intent);


            }
        });
    }

    //network checking

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
