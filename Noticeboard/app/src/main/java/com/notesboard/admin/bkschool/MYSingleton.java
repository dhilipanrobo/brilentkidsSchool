package com.notesboard.admin.bkschool;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by admin on 1/1/2018.
 */

public class MYSingleton {
    private  static MYSingleton mInstance;
    private static Context mctx;
    private RequestQueue requestQueue;

    private MYSingleton(Context context){

        mctx=context;
        requestQueue =getRequestQueue();
    }


    private RequestQueue getRequestQueue()
    {
        if(requestQueue==null){

            requestQueue = Volley.newRequestQueue(mctx.getApplicationContext());
        }
        return requestQueue;
    }

    public static synchronized MYSingleton getmInstance(Context context){
        if (mInstance==null){

            mInstance = new MYSingleton(context);
        }
        return mInstance;
    }
    public<T>  void addToRequestque(Request<T> request){
        getRequestQueue().add(request);
    }

}
