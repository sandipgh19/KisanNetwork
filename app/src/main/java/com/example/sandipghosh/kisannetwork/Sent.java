package com.example.sandipghosh.kisannetwork;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
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

public class Sent extends Fragment {

    View rootView;
    ListView listView;

    private static final String Sent_URL = "https://sandipgh19.000webhostapp.com/kisanNetwork/sent.php";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.activity_sent, container, false);
        listView = (ListView) rootView.findViewById(R.id.sent_list);

        getData();

        return rootView;
    }

    private void getData() {
        final ProgressDialog loading = ProgressDialog.show(getActivity(),"","Please wait...",false,false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Sent_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("MY TEST",response);

                        loading.dismiss();

                        showJSON(response);
                    }
                },
                new Response.ErrorListener() {
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

    private void showJSON(String json){
        SentJSON sj = new SentJSON(json);
        sj.sentJSON();
        SentAdapter sa = new SentAdapter(getActivity(), SentJSON.name,SentJSON.date,SentJSON.otp);
        listView.setAdapter(sa);

    }
}
