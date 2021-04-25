package com.notesboard.admin.bkschool;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RankCard extends AppCompatActivity implements View.OnClickListener {
TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8;
    Button bt1;
    Spinner sp1;
    String id;
    String[] term = { "I-Midterm", "QuarterlyExam", "II-Midterm", "HalfYearly", "III-Midterm","AnnualExam"  };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_rank_card);
        SharedPreferences sharedPreferences = getSharedPreferences("userlog", Context.MODE_PRIVATE);
        id=sharedPreferences.getString("username","");
        tv1=(TextView) findViewById(R.id.textView15);
        tv2=(TextView) findViewById(R.id.textView17);
        tv3=(TextView) findViewById(R.id.textView19);
        tv4=(TextView) findViewById(R.id.textView21);
        tv5=(TextView) findViewById(R.id.textView14);
        tv6=(TextView) findViewById(R.id.textView4);
        tv7=(TextView) findViewById(R.id.textView6);
       // getJSON1(" http://192.168.43.122/notesboard/getrank.php?term=" + ter);
        //  getJSON1(" http://www.welleservices.com/noticeboard/getrank.php?term=" + ter);
       // getJSON1("http://www.welleservices.com/noticeboard/class.php");
        sp1=(Spinner)findViewById(R.id.spinner5);
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,term);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        sp1.setAdapter(aa);

        bt1=(Button) findViewById(R.id.button13);
        bt1.setOnClickListener(this);

    }

    @SuppressLint("ResourceAsColor")
    private void loadIntoListView(String json) throws JSONException {
        JSONArray jsonArray = new JSONArray(json);
       String[] tamil = new String[jsonArray.length()];
        String[] english = new String[jsonArray.length()];
        String[] maths = new String[jsonArray.length()];
        String[] science = new String[jsonArray.length()];
        String[] social = new String[jsonArray.length()];
        String[] total = new String[jsonArray.length()];
        String[] rank = new String[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            tamil[i] = obj.getString("tamil");
            english[i] = obj.getString("english");
            maths[i] = obj.getString("maths");
            science[i] = obj.getString("science");
            social[i] = obj.getString("social");
            total[i] = obj.getString("total");
            rank[i] = obj.getString("rank");

        }



int no=0;
        try {


            tv1.setText("" + tamil[no]);
          //  tv1.setTextColor(Color.RED);
            tv2.setText("" + english[no]);
            tv3.setText("" + maths[no]);
            tv4.setText("" + science[no]);
            tv5.setText("" + social[no]);
            tv6.setText("" + total[no]);
            tv7.setText("" + rank[no]);
        }catch (Exception e) {
            sp1.requestFocus();
            Toast.makeText(this, "Select Correct Exam", Toast.LENGTH_LONG).show();

        }
    }


    private void loadIntoListView1(String json) throws JSONException {
        JSONArray jsonArray = new JSONArray(json);
        String[] tamil = new String[jsonArray.length()];

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            tamil[i] = obj.getString("tamil");
            //  Toast.makeText(this, "pass"+tamil[0], Toast.LENGTH_LONG).show();

        }



        int no=0;
        try {


            String pass1=tamil[0];


        }catch (Exception e) {

            Toast.makeText(this, "User Name Or Password Incorrect", Toast.LENGTH_LONG).show();

        }
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

    @Override
    public void onClick(View v) {
        String ter =sp1.getSelectedItem().toString();

        try {
         //   Toast.makeText(this, ""+ter, Toast.LENGTH_LONG).show();
         //  getJSON1(" http://192.168.43.122/notesboard/getrank.php?term="+ter);
           getJSON1(" https://www.welleservices.com/noticeboard/bkgetrank.php?term=" + ter+"&rno="+id);
          //  Toast.makeText(this, "term:  "+ter, Toast.LENGTH_LONG).show();
        }catch (Exception e) {
            sp1.requestFocus();
            Toast.makeText(this, "Select Correct Exam", Toast.LENGTH_LONG).show();

        }
    }


}
