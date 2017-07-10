package com.example.sandipghosh.kisannetwork;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by sandipghosh on 10/07/17.
 */

public class ParseJSON {

    public static String[] first;
    public static String[] last;
    public static String[] contact;

    public static final String JSON_ARRAY = "posts";
    public static final String KEY_FIRST = "first";
    public static final String KEY_LAST = "last";
    public static final String KEY_CONTACT = "contact";

    private JSONArray users = null;

    private String json;

    public ParseJSON(String json){
        this.json = json;
    }

    protected void parseJSON(){
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            users = jsonObject.getJSONArray(JSON_ARRAY);

            first = new String[users.length()];
            last = new String[users.length()];
            contact = new String[users.length()];

            for(int i=0;i<users.length();i++){
                JSONObject jo = users.getJSONObject(i);
                first[i] = jo.getString(KEY_FIRST);
                last[i] = jo.getString(KEY_LAST);
                contact[i] = jo.getString(KEY_CONTACT);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
