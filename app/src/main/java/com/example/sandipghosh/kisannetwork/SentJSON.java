package com.example.sandipghosh.kisannetwork;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by sandipghosh on 10/07/17.
 */

public class SentJSON {

    public static String[] name;
    public static String[] date;
    public static String[] otp;

    public static final String JSON_ARRAY = "posts";
    public static final String KEY_NAME = "name";
    public static final String KEY_DATE = "date";
    public static final String KEY_OTP = "otp";

    private JSONArray users = null;

    private String json;

    public SentJSON(String json){
        this.json = json;
    }

    protected void sentJSON(){
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            users = jsonObject.getJSONArray(JSON_ARRAY);

            name = new String[users.length()];
            date = new String[users.length()];
            otp = new String[users.length()];

            for(int i=0;i<users.length();i++){
                JSONObject jo = users.getJSONObject(i);
                name[i] = jo.getString(KEY_NAME);
                date[i] = jo.getString(KEY_DATE);
                otp[i] = jo.getString(KEY_OTP);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
