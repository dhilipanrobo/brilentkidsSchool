package com.notesboard.admin.bkschool;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.HashMap;
import java.util.Map;

public class Need_Notifi extends AppCompatActivity implements View.OnClickListener {
    Button bt1;String a;
    TelephonyManager telephonyManager;
    String IMEI_Number_Holder;
    String appurl="https://192.168.43.122/notesboard/fcm_insert.php";
 //   String appurl = "http://www.welleservices.com/noticeboard/fcm_insert.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_need__notifi);
        bt1 = (Button) findViewById(R.id.button2);
        bt1.setOnClickListener(this);

      //  Toast.makeText(getApplicationContext(),"imei"+IMEI_Number_Holder, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        a= Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
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
                Map<String, String> params = new HashMap<>();
                params.put("fcm_token",token);
                params.put("imi",a);


                return params;
            }
        };
        MYSingleton.getmInstance(this).addToRequestque(stringRequest);



        Intent vv=new Intent(Need_Notifi.this,MainActivity.class);
        startActivity(vv);

    }
}
