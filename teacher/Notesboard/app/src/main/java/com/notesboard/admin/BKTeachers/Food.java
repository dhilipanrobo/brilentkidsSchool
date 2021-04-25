package com.notesboard.admin.BKTeachers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Food extends AppCompatActivity implements View.OnClickListener {
    Button bt1,bt2;
    EditText et1,et2,et3;
    TextView tv1;
    String date;
    String title;
    SimpleDateFormat date1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_food);
        tv1 = (TextView) findViewById(R.id.textView);

        et1=(EditText) findViewById(R.id.editText2);
        et2=(EditText) findViewById(R.id.editText5);
        bt1=(Button) findViewById(R.id.button);
        bt1.setOnClickListener(this);
        Calendar cal= Calendar.getInstance();
        int y=cal.get(Calendar.YEAR);
        int m=cal.get(Calendar.MONTH)+1;
        int d=cal.get(Calendar.DATE);
        int h=cal.get(Calendar.HOUR);
        int mm=cal.get(Calendar.MINUTE);
        String dd = String.valueOf(d);
        String mon = String.valueOf(m);
        String yy = String.valueOf(y);
        date=dd+"-"+mon+"-"+yy;
        tv1.setText("DATE:"+date);
    }

    @Override
    public void onClick(View v) {
        reg();

    }

    private void reg(){
        final    String  title =et1.getText().toString().trim();
        final    String mater =et2.getText().toString().trim();

        if(title.isEmpty())
        {
            Toast.makeText(this,"Title FIELD IS EMPTY",Toast.LENGTH_LONG).show();

        }
        else{
            if(mater.isEmpty())
            {
                Toast.makeText(this,"Mater FIELD IS EMPTY",Toast.LENGTH_LONG).show();

            }
            else {

                StringRequest stringRequest = new StringRequest(Request.Method.POST,Constants.URL_REGISTER7,
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
                        params.put("date",date);
                        params.put("title",title);
                        params.put("food",mater);
                        params.put("sno","");
                        return params;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(this);
                requestQueue.add(stringRequest);





            }} et1.setText("");
        et2 .setText("");
        reg1();
        Intent vv=new Intent(this,Mainpage.class);

        startActivity(vv);

    }




    private void reg1(){
        title =et1.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,Constants.URL_REGISTER3,
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
                params.put("send_notification","send_notification");
                params.put("title","Food Menu");

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


}
