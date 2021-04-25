package com.notesboard.admin.bkschool;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.arturogutierrez.Badges;
import com.github.arturogutierrez.BadgesNotSupportedException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Select_subject extends AppCompatActivity implements AdapterView.OnItemClickListener,View.OnClickListener {
String Ref;
    ListView listView;
    String item;
    Spinner sp,sp2;
    Button bt1,bt2;
    String[] title;
    String[] work;
    int xx;

    String[] term = { "All","Tamil", "English", "Mathematics", "Scienc", "SocialScience","CS","Hindi","GK"  };
    private static final String TAG ="" ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_select_subject);

        Ref=getIntent().getExtras().getString("ref");
      //  getJSON1("http://192.168.43.122/notesboard/gethomework.php?datee="+Ref);
        // getJSON2(" http://192.168.43.122/notesboard/getsubject.php");
       // getJSON3(" http://192.168.43.122/notesboard/class.php");
    //    getJSON2(" http://www.welleservices.com/noticeboard/getsubject.php");
        getJSON3("https://www.welleservices.com/noticeboard/bkclass.php");
        sp=(Spinner)findViewById(R.id.spinner4);
        sp2=(Spinner)findViewById(R.id.spinner3);
        bt1=(Button) findViewById(R.id.button12);
       bt1.setOnClickListener(this);
        listView = (ListView) findViewById(R.id.listview);
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,term);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner

        sp.setAdapter(aa);

    //    Toast.makeText(getApplicationContext(),""+Ref, Toast.LENGTH_SHORT).show(); listView = (ListView) findViewById(R.id.listview);
//        listView.setOnItemClickListener(this);
        try {

            Badges.setBadge(this, 0);
        } catch (BadgesNotSupportedException badgesNotSupportedException) {
            Log.d(TAG, badgesNotSupportedException.getMessage());
        }

      //  getJSON1("http://www.welleservices.com/noticeboard/get.php");

     //   getJSON1("http://192.168.43.122/notesboard/gethomework.php");
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
        try{
        JSONArray jsonArray = new JSONArray(json);
        String[] heroes = new String[jsonArray.length()];
     title = new String[jsonArray.length()];
             work = new String[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            heroes[i] = ""+obj.getString("title")+"\n\n\n"+obj.getString("work");
            title[i] =  ""+ obj.getString("title") ;
            work[i] =  ""+ obj.getString("work") ;
        }



            Select_subject.AppointmentAdapter adapter = new Select_subject .AppointmentAdapter ();
            listView.setAdapter(adapter);
            xx=work.length;
    }catch (Exception e) {
            Toast.makeText ( this, "No Homework", Toast.LENGTH_SHORT ).show ( );
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

        String dd= listView.getItemAtPosition( position).toString();
        String ds[]=dd.split(":");
        String ds1=dd.split(":")[1];
        for (int y=0;y<ds.length;y++){

            item=ds1;


        }

        Intent v=new Intent(this,NEWS.class);
        v.putExtra("ref",item);
        startActivity(v);
        finish();

        // Toast.makeText(getApplicationContext(), item, Toast.LENGTH_SHORT).show();
    }

    private void getJSON3(final String urlWebService) {

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
                    loadIntoListView2(s);
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


    private void loadIntoListView2(String json) throws JSONException {
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
        try{
        Toast.makeText ( this, ""+Ref, Toast.LENGTH_SHORT ).show ( );
       String sub =sp.getSelectedItem().toString();
      String cla=sp2.getSelectedItem().toString();

        getJSON1("https://www.welleservices.com/noticeboard/bkgethomework.php?datee="+Ref+"&clas="+cla+"&subj="+sub);

    }catch (Exception e) {}}


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
                convertView= getLayoutInflater().inflate(R.layout.homeworktex,null);

                TextView textView12=convertView.findViewById(R.id.textView27);
                TextView textView11=convertView.findViewById(R.id.textView23);


                textView11.setText(title[position]);
                textView12.setText("\t\t\t\t"+work[position]);
            }
            return convertView;
        }
    }}
