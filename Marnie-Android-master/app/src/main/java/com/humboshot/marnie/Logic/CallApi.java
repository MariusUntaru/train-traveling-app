package com.humboshot.marnie.Logic;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by martin on 19-05-2017
 * Volley RequestQueue and gson builder.
 */

public class CallApi<T> {
    private static RequestQueue queue;
    private static Gson gson;

    public static RequestQueue q(Context context){
        if (queue == null) queue = Volley.newRequestQueue(context);
        return queue;
    }

    //initialise gson and set date format
    public static Gson getGson() {
        if (gson == null) {
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.setDateFormat(new DateHandler().getDateFormatString());
            gson = gsonBuilder.create();
        }
        return gson;
    }
    public JSONObject GetJsonObj(T object) {
        JSONObject obj = null;
        try {
            String jsonString = getGson().toJson(object);
            obj = new JSONObject(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
