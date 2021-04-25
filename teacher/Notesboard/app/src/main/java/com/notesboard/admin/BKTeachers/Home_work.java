package com.notesboard.admin.BKTeachers;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Home_work extends AppCompatActivity implements View.OnClickListener {
    Spinner sp,sp2;
    Button bt1,bt2;
    String date,work,sub,cla,tech;
    EditText et1;
    String[] term = { "All","Tamil", "English", "Mathematics", "Scienc", "SocialScience","CS","Hindi","GK"  };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_home_work);
        SharedPreferences sharedPreferences = getSharedPreferences("userlog", Context.MODE_PRIVATE);
        tech=sharedPreferences.getString("username","");

       // getJSON1(" http://www.welleservices.com/noticeboard/getsubject.php");
        getJSON2("https://www.welleservices.com/noticeboard/deviclass.php");
     //   getJSON1(" http://192.168.43.122/notesboard/getsubject.php");
       // getJSON2(" http://192.168.43.122/notesboard/class.php");
        sp=(Spinner)findViewById(R.id.spinner);
        sp2=(Spinner)findViewById(R.id.spinner2);
        bt1=(Button) findViewById(R.id.button9);
        bt1.setOnClickListener(this);

        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,term);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner

        sp.setAdapter(aa);
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
        et1=(EditText) findViewById(R.id.editText3);
    }

    private void getJSON1(final String urlWebService) {

        class GetJSON extends AsyncTask<Void, Void, String> {


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }


            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                String sno1=s;


                String sno2=sno1.replace("{=","");
                String  sno3=sno2.replace("}","");
                //   Toast.makeText(getApplicationContext(), sno3, Toast.LENGTH_SHORT).show();
                try {
                    loadIntoListView(s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


            @Override
            protected String doInBackground(Void... voids) {



                try {
                    //creating a URL
                    URL url = new URL(urlWebService);

                    //Opening the URL using HttpURLConnection
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();

                    //StringBuilder object to read the string from the service
                    StringBuilder sb = new StringBuilder();

                    //We will use a buffered reader to read the string from service
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    //A simple string to read values from each line
                    String json;

                    //reading until we don't find null
                    while ((json = bufferedReader.readLine()) != null) {

                        //appending it to string builder
                        sb.append(json + "\n");
                    }

                    //finally returning the read string
                    return sb.toString().trim();
                } catch (Exception e) {
                    return null;
                }

            }
        }

        //creating asynctask object and executing it
        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }
    private void getJSON2(final String urlWebService) {

        class GetJSON extends AsyncTask<Void, Void, String> {


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }


            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                String sno1=s;


                String sno2=sno1.replace("{=","");
                String  sno3=sno2.replace("}","");
                //   Toast.makeText(getApplicationContext(), sno3, Toast.LENGTH_SHORT).show();
                try {
                    loadIntoListView1(s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


            @Override
            protected String doInBackground(Void... voids) {



                try {
                    //creating a URL
                    URL url = new URL(urlWebService);

                    //Opening the URL using HttpURLConnection
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();

                    //StringBuilder object to read the string from the service
                    StringBuilder sb = new StringBuilder();

                    //We will use a buffered reader to read the string from service
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    //A simple string to read values from each line
                    String json;

                    //reading until we don't find null
                    while ((json = bufferedReader.readLine()) != null) {

                        //appending it to string builder
                        sb.append(json + "\n");
                    }

                    //finally returning the read string
                    return sb.toString().trim();
                } catch (Exception e) {
                    return null;
                }

            }
        }

        //creating asynctask object and executing it
        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }


    private void loadIntoListView(String json) throws JSONException {
        JSONArray jsonArray = new JSONArray(json);
        String[] heroes = new String[jsonArray.length()];
        String[] date = new String[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            heroes[i] = obj.getString("sub");

        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.sp_lis, heroes);

        sp.setAdapter(arrayAdapter);

    }
    private void loadIntoListView1(String json) throws JSONException {
        JSONArray jsonArray = new JSONArray(json);
        String[] heroes = new String[jsonArray.length()];
        String[] date = new String[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            heroes[i] = obj.getString("class");

        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.sp_lis, heroes);

        sp2.setAdapter(arrayAdapter);

    }

    @Override
    public void onClick(View v) {
        reg();

    }

    private void reg(){

        work = et1.getText().toString();
        sub =sp.getSelectedItem().toString();
        cla=sp2.getSelectedItem().toString();

       // Toast.makeText(this,""+sub+cla,Toast.LENGTH_LONG).show();

        if(work.isEmpty())
        {
            Toast.makeText(this,"Title FIELD IS EMPTY",Toast.LENGTH_LONG).show();

        }

        else {

            StringRequest stringRequest = new StringRequest(Request.Method.POST,Constants.URL_REGISTER4,
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

                    params.put("subject",sub);
                    params.put("class",cla);
                    params.put("work",work);
                    params.put("date",date);
                    params.put("tech",tech);
                    params.put("sno","");
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
            //   reg1();

            et1.setText("");





        }}


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
                params.put("title",sub);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}

