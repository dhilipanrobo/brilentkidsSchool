package com.notesboard.admin.BKTeachers;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
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
import java.util.HashMap;
import java.util.Map;

public class Delete extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView listView;
    String so;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_delete);
        listView = (ListView) findViewById(R.id.listview);
        listView.setOnItemClickListener(this);
        getJSON1("https://www.welleservices.com/noticeboard/bkget.php");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


        String dd= listView.getItemAtPosition( position).toString();
        String ds[]=dd.split(":");
        String ds1=dd.split(":")[1];
        for (int y=0;y<ds.length;y++){

            so=ds1;


        }

        Toast.makeText(this,"so"+so,Toast.LENGTH_LONG).show();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Enter Password");

        LayoutInflater inflater = getLayoutInflater();
        View dialoglayout = inflater.inflate(R.layout.alb, null);
        final EditText userAnswer = (EditText) dialoglayout.findViewById(R.id.editText);
        builder.setNegativeButton("ok", new DialogInterface.OnClickListener() {


            @Override
            public void onClick(DialogInterface arg0, int arg1) {



                String pass=userAnswer.getText().toString();
                if("13579".equals(pass)){

                    x(so);
                    reg1(so);


                }








            }
        });




        builder.setView(dialoglayout);

        builder.show();

    }


    void x(String a){

        Intent vv=new Intent(this,Home.class);

        startActivity(vv);
        finish();}

    private void loadIntoListView(String json) throws JSONException {
        JSONArray jsonArray = new JSONArray(json);
        String[] heroes = new String[jsonArray.length()];
        String[] date = new String[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            heroes[i] = obj.getString("title")+"\n\n"+obj.getString("datee")+"\t\t\t\t\t\t\tMsg NO:"+obj.getString("sno");

        }



        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.lis, heroes);

        listView.setAdapter(arrayAdapter);

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



    private void reg1(final String no){


        StringRequest stringRequest = new StringRequest(Request.Method.POST,Constants.URL_REGISTER1,
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

                params.put("id",no);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


}


