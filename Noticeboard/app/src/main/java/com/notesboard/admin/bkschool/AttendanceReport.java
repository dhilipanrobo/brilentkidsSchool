package com.notesboard.admin.bkschool;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.arturogutierrez.Badges;
import com.github.arturogutierrez.BadgesNotSupportedException;
import com.google.android.gms.ads.InterstitialAd;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class AttendanceReport extends AppCompatActivity implements AdapterView.OnItemClickListener,View.OnClickListener {
    private static final String TAG ="" ;
    ListView listView;
    String item;
    String[] date;
    String[] msno;
    String[] title;
    int xx;

    ProgressBar process;
    private ArrayList<HashMap<String, String>> list;

    private static final int START_LEVEL = 1;
    private int mLevel;
    private Button mNextLevelButton,bt;
    private InterstitialAd mInterstitialAd;
    private TextView mLevelTextView;
    private String[] rno,remark,states;
    EditText et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_attendance_report);
        listView = (ListView) findViewById(R.id.listview);
        process=(ProgressBar) findViewById(R.id.progressBar2);
        bt=findViewById ( R.id.button11 );
       bt.setOnClickListener ( this );
       et=findViewById ( R.id.editText6 );

        listView.setOnItemClickListener(this);
        process.setVisibility(View.GONE);
       ;
        try {

            Badges.setBadge(this, 5);
        } catch (BadgesNotSupportedException badgesNotSupportedException) {
            android.util.Log.d(TAG, badgesNotSupportedException.getMessage());
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {

            Intent v=new Intent(this,Need_Notifi.class);

            startActivity(v);

        }

        return super.onOptionsItemSelected(item);
    }

    private void loadIntoListView(String json) throws JSONException {
        try {
            JSONArray jsonArray = new JSONArray(json);
         //   final     String[] heroes = new String[jsonArray.length()];
            date = new String[jsonArray.length()];
            rno = new String[jsonArray.length()];
            states = new String[jsonArray.length()];
            remark= new String[jsonArray.length()];
            msno= new String[jsonArray.length()];
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
               // heroes[i] = "\t\t\t\t\t" + obj.getString("title") + "\n\n\n" + obj.getString("datee") + "\t\t\t\t\tMsg NO:" + obj.getString("sno");
                date[i] =  ""+ obj.getString("datee") ;
                rno[i] =  ""+ obj.getString("rno") ;
                states[i] =  ""+ obj.getString("states") ;
                remark[i] =  ""+ obj.getString("remark") ;
                msno[i] =  ""+ obj.getString("sno") ;






            }
            xx=date.length;
            process.setVisibility(View.GONE);
            AttendanceReport.AppointmentAdapter adapter = new AttendanceReport .AppointmentAdapter ();
            listView.setAdapter(adapter);


        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),"No Internet", Toast.LENGTH_SHORT).show();

        }}







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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onClick(View v) {

        getJSON1("https://www.welleservices.com/noticeboard/bkattrep.php?rno="+et.getText ().toString ());
        process.setVisibility(View.VISIBLE);


    }

    class AppointmentAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return xx;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            convertView = null;

            if (convertView == null) {
                convertView= getLayoutInflater().inflate(R.layout.attendanm,null);
                TextView textView13=convertView.findViewById(R.id.textView24);
                TextView textView12=convertView.findViewById(R.id.textView11);
                TextView textView11=convertView.findViewById(R.id.textView26);

                textView13.setText(date[position]);
                textView11.setText("Status : "+states[position]);
                textView12.setText("Remark : "+remark[position]);
            }
            return convertView;
        }
    }




}