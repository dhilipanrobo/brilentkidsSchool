package com.notesboard.admin.bkschool;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.HashMap;
import java.util.Map;

public class Mainpage extends Activity implements View.OnClickListener {
Button bt3,bt4,bt6,bt7,bt8,bt9;
String date,a;
EditText et1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView( R.layout.mainpg);



        String appurl = "https://www.welleservices.com/noticeboard/bkfcm_insert.php";
       // String appurl = "http://192.168.43.122/notesboard/fcm_insert.php";
        notfi(appurl);
        bt3 = (Button) findViewById(R.id.button3);
        bt3.setOnClickListener(this);
        bt4 = (Button) findViewById(R.id.button4);
        bt4.setOnClickListener(this);
        bt6 = (Button) findViewById(R.id.button8);
        bt6.setOnClickListener(this);
        bt7 = (Button) findViewById(R.id.button7);
        bt7.setOnClickListener(this);

        bt9 = (Button) findViewById(R.id.button15);
        bt9.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {


        int id = view.getId();
        if (id== R.id.button3){

            Intent v=new Intent(this,MainActivity.class);


            startActivity(v);


        }

        else if(id== R.id.button8) {


            Intent v=new Intent(this,AttendanceReport.class);


            startActivity(v);


        }
        else if(id== R.id.button4){


            Intent v=new Intent(this,Log.class);


            startActivity(v);

        }

        else if(id== R.id.button7){

            Intent v=new Intent(this,SelectDate.class);


            startActivity(v);


        }
        else if(id== R.id.button15){

            Intent v=new Intent(this,fooddate.class);


            startActivity(v);


        }




    }


    void notfi(String appurl){


        String ff = FirebaseInstanceId.getInstance().getToken();

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(getString(R.string.FCM_PREF), Context.MODE_PRIVATE);
        final String token = sharedPreferences.getString(getString(R.string.FCM_TOKEN),ff);


        // Toast.makeText(getApplicationContext(),"token"+token, Toast.LENGTH_SHORT).show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,appurl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {;

            }
        }){


            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                a= Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
                Map<String, String> params = new HashMap<>();
                params.put("fcm_token",token);
                params.put("imi",a);


                return params;


            }
        };
        MYSingleton.getmInstance(this).addToRequestque(stringRequest);




    }
}
