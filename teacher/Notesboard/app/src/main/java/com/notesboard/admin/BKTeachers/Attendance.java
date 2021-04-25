package com.notesboard.admin.BKTeachers;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Attendance extends AppCompatActivity implements View.OnClickListener{
    Spinner spinner;
    String[] term = { "Leave", "Absent" };
    String date,tech;
    EditText et1,et2;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_attendance );
        SharedPreferences sharedPreferences = getSharedPreferences("userlog", Context.MODE_PRIVATE);
        tech=sharedPreferences.getString("username","");
        spinner=(Spinner)findViewById(R.id.spinner3);
        et1=findViewById ( R.id.editText4 );
        et2=findViewById ( R.id.editText6 );
        button=(Button) findViewById(R.id.button5);
        button.setOnClickListener(this);
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,term);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(aa);
        cal ();


    }


    void cal(){ Calendar cal= Calendar.getInstance();
        int y=cal.get(Calendar.YEAR);
        int m=cal.get(Calendar.MONTH)+1;
        int d=cal.get(Calendar.DATE);
        int h=cal.get(Calendar.HOUR);
        int mm=cal.get(Calendar.MINUTE);
        String dd = String.valueOf(d);
        String mon = String.valueOf(m);
        String yy = String.valueOf(y);
        date=dd+"-"+mon+"-"+yy;}


    private void reg(){

       final String reg = et1.getText().toString();
      final String  states =spinner.getSelectedItem().toString();
       final String remark=et2.getText().toString();

        // Toast.makeText(this,""+sub+cla,Toast.LENGTH_LONG).show();

        if(reg.isEmpty())
        {
            Toast.makeText(this,"RNO FIELD IS EMPTY",Toast.LENGTH_LONG).show();

        }

        else {

            if(remark.isEmpty())
            {
                Toast.makeText(this,"Remark FIELD IS EMPTY",Toast.LENGTH_LONG).show();

            }

            else {

            StringRequest stringRequest = new StringRequest( Request.Method.POST,Constants.URL_REGISTER8,
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
                    Map<String, String> params = new HashMap<> ();

                    params.put("rno",reg);
                    params.put("states",states);
                    params.put("remark",remark);
                    params.put("date",date);
                    params.put("tech",tech);
                    params.put("sno","");
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
          //  reg1();

            et1.setText("");
                et2.setText("");





        }}}


    private void reg1(){


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
                params.put("title","Attendance");

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onClick(View v) {
        reg ();
    }
}
