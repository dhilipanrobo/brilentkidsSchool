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
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

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

public class fooddate extends AppCompatActivity  implements AdapterView.OnItemClickListener {
    private static final String TAG ="" ;
    ListView listView;
    String item;
    ProgressBar process;
    String[] date;
    String[] msno;
    String[] title;
    int xx;
    private ArrayList<HashMap<String, String>> list;

    private static final int START_LEVEL = 1;
    private int mLevel;
    private Button mNextLevelButton;
    private InterstitialAd mInterstitialAd;
    private TextView mLevelTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_fooddate);
        listView = (ListView) findViewById(R.id.listview);
        process=(ProgressBar) findViewById(R.id.progressBar2);

        listView.setOnItemClickListener(this);
        try {

            Badges.setBadge(this, 5);
        } catch (BadgesNotSupportedException badgesNotSupportedException) {
            android.util.Log.d(TAG, badgesNotSupportedException.getMessage());
        }

        getJSON1("https://www.welleservices.com/noticeboard/bkgetfood.php");
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
            String[] heroes = new String[jsonArray.length()];
            title = new String[jsonArray.length()];
            date = new String[jsonArray.length()];
            msno = new String[jsonArray.length()];
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                heroes[i] = "\t\t\t\t\t" + obj.getString("title") + "\n\n\n" + obj.getString("datee") + "\t\t\t\t\t\t\tMsg NO:" + obj.getString("sno");
                date[i] =  ""+ obj.getString("datee") ;
                title[i] =  ""+ obj.getString("title") ;
                msno[i] =  ""+ obj.getString("sno") ;
            }


            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.lisfood, heroes);

            listView.setAdapter(arrayAdapter);

            xx=date.length;
            AppointmentAdapter adapter = new AppointmentAdapter();
            listView.setAdapter(adapter);
            process.setVisibility(View.GONE);
        } catch (Exception e) {


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

        TextView text = (TextView) parent.findViewById(R.id.textView25);

        String tEXT = msno[position];
        Intent v=new Intent(this,food.class);
        v.putExtra("ref",tEXT);
        startActivity(v);





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
                convertView= getLayoutInflater().inflate(R.layout.listvw,null);
                TextView textView13=convertView.findViewById(R.id.textView24);
                TextView textView12=convertView.findViewById(R.id.textView25);
                TextView textView11=convertView.findViewById(R.id.textView26);

                textView13.setText(date[position]);
                textView11.setText(title[position]);
                textView12.setText(msno[position]);
            }
            return convertView;
        }
    }
}
