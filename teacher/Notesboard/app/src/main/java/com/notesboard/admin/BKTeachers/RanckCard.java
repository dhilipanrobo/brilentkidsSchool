package com.notesboard.admin.BKTeachers;

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

import java.util.HashMap;
import java.util.Map;

public class RanckCard extends AppCompatActivity implements View.OnClickListener {
EditText et1,et2,et3,et4,et5,et6,et7,et8;
Button bt1;
Spinner sp1;
String tamil,english,maths,science,social,rank,remark,total;
    String[] term = { "I-Midterm", "QuarterlyExam", "II-Midterm", "HalfYearly", "III-Midterm","AnnualExam"  };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_ranck_card);
        sp1=(Spinner)findViewById(R.id.spinner3);

       bt1=(Button) findViewById(R.id.button5);
        bt1.setOnClickListener(this);

        et1=(EditText) findViewById(R.id.editText11);
        et2=(EditText) findViewById(R.id.editText12);
        et3=(EditText) findViewById(R.id.editText15);
        et4=(EditText) findViewById(R.id.editText16);
        et5=(EditText) findViewById(R.id.editText17);
        et6=(EditText) findViewById(R.id.editText19);
        et7=(EditText) findViewById(R.id.editText21);
        et8=(EditText) findViewById(R.id.editText22);
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,term);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        et1.requestFocus();
        sp1.setAdapter(aa);

    }

    @Override
    public void onClick(View v) {

        tamil = et1.getText().toString();
        english = et2.getText().toString();
        maths = et3.getText().toString();
        science = et4.getText().toString();
        social = et5.getText().toString();
        total = et6.getText().toString();
        rank = et7.getText().toString();
       final String ter =sp1.getSelectedItem().toString();

        if(tamil.isEmpty())
        {
            Toast.makeText(this,"Title FIELD IS EMPTY",Toast.LENGTH_LONG).show();

        }

        else {

            if(english.isEmpty())
            {
                Toast.makeText(this,"Title FIELD IS EMPTY",Toast.LENGTH_LONG).show();

            }

            else {

                if(maths.isEmpty())
                {
                    Toast.makeText(this,"Title FIELD IS EMPTY",Toast.LENGTH_LONG).show();

                }

                else {
                    if(science.isEmpty())
                    {
                        Toast.makeText(this,"Title FIELD IS EMPTY",Toast.LENGTH_LONG).show();

                    }

                    else {

                            if(et8.getText ().toString ().isEmpty())
                            {
                                Toast.makeText(this,"RNO IS EMPTY",Toast.LENGTH_LONG).show();

                            }

                            else {
                        if(social.isEmpty())
                        {
                            Toast.makeText(this,"Title FIELD IS EMPTY",Toast.LENGTH_LONG).show();

                        }

                        else {
                            if(total.isEmpty())
                            {
                                Toast.makeText(this,"Title FIELD IS EMPTY",Toast.LENGTH_LONG).show();

                            }

                            else {
                                if(rank.isEmpty())
                                {
                                    Toast.makeText(this,"Title FIELD IS EMPTY",Toast.LENGTH_LONG).show();

                                }

                                else {
                                    StringRequest stringRequest = new StringRequest(Request.Method.POST,Constants.URL_REGISTER6,
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

                                            params.put("tamil",tamil);
                                            params.put("english",english);
                                            params.put("maths",maths);
                                            params.put("science",science);
                                            params.put("social",social);
                                            params.put("total",total);
                                            params.put("rank",rank);
                                            params.put("sno","");
                                            params.put("term",ter);
                                            params.put("rno",et8.getText ().toString ());
                                            return params;
                                        }
                                    };
                                    RequestQueue requestQueue = Volley.newRequestQueue(this);
                                    requestQueue.add(stringRequest);
                                   // reg1();

                                    et1.setText(""); et1.setText(""); et2.setText(""); et3.setText(""); et4.setText("");
                                    et5.setText(""); et6.setText(""); et7.setText("");




                                }

            }


    }
}}}}}}}
