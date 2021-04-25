package com.notesboard.admin.bkschool;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NEWS extends AppCompatActivity implements View.OnClickListener{
    Button bt1,bt2;

    TextView tv1;
    String Ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_news);
        Ref=getIntent().getExtras().getString("ref");
  //      Toast.makeText(getApplicationContext(),"ref"+Ref, Toast.LENGTH_SHORT).show();
        tv1 = (TextView) findViewById(R.id.textView2);


        bt1=(Button) findViewById(R.id.button5);
        bt2=(Button) findViewById(R.id.button6);
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
   //    reg();
        getJSON1("https://www.welleservices.com/noticeboard/bkgetmater.php?datee="+Ref);
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


                String sno2=sno1.replace("\"","");
                String  sno3=sno2.replace("]","");
                String  sno4=sno3.replace("[","");
                tv1.setText("\t\t\t"+sno4);
            //   Toast.makeText(getApplicationContext(), sno3, Toast.LENGTH_SHORT).show();
               // try {
                 //   loadIntoListView(s);
               // } catch (JSONException e) {
               //     e.printStackTrace();
               // }
            }


            @Override
            protected String doInBackground(Void... voids) {



                try {
                    //creating a URL
                    URL url = new URL( urlWebService);

                    //Opening the URL using HttpURLConnection
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
con.setRequestMethod("POST");
con.connect();
                    InputStream is = con.getInputStream();
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
        int id = v.getId();
        if (id== R.id.button5){


            Intent vv=new Intent(this,MainActivity.class);

            startActivity(vv);
            finish();
        }
        else if(id== R.id.button6){
            Intent vv=new Intent(this,Mainpage.class);

            startActivity(vv);
            finish();

        }

    }


}
