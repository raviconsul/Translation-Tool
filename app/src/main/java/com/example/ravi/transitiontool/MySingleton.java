package com.example.ravi.transitiontool;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Ravi on 11-07-2017.
 */


// Boiler plate code for using volley library
public class MySingleton {

    private static MySingleton mInstance;
    private RequestQueue requestQueue;
    private static Context mCtx;

    private MySingleton(Context context){
        mCtx = context;
        requestQueue = getRequestQueue();
    }

    public RequestQueue getRequestQueue(){
        if(requestQueue==null){
            requestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return requestQueue;
    }

    public static synchronized MySingleton getInstance(Context context){
        if(mInstance==null){
            mInstance = new MySingleton(context);
        }
        return mInstance;
    }

    public void addToRequestQue(Request request){
        requestQueue.add(request);
    }
}

