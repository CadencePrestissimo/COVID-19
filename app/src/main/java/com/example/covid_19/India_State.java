package com.example.covid_19;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;


import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonIOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;


public class India_State extends AppCompatActivity {
    ListView listView;
    LinearLayout layout;
    ConstraintLayout c;
    TextView t1, t2, t3, t4, t5, t6, t7, t8;
    ArrayList<String> arrayList;
    int g=0;
    String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_india__state);
        c=findViewById(R.id.layout_start);

        t1 = (TextView) findViewById(R.id.textView49);
        t2 = (TextView) findViewById(R.id.textView222);
        t3 = (TextView) findViewById(R.id.textView23);
        t4 = (TextView) findViewById(R.id.textView25);
        t5 = (TextView) findViewById(R.id.textView41);
        t6 = (TextView) findViewById(R.id.textView45);
        t7 = (TextView) findViewById(R.id.textView47);

        listView = (ListView) findViewById(R.id.statelist);
        layout = (LinearLayout) findViewById(R.id.list201);
        arrayList = new ArrayList<>();
        arrayList.add("Maharashtra");
        arrayList.add("Tamil Nadu");
        arrayList.add("Delhi");
        arrayList.add("Karnataka");
        arrayList.add("Andhra Pradesh");
        arrayList.add("Uttar Pradesh");
        arrayList.add("Gujarat");
        arrayList.add("West Bengal");
        arrayList.add("Telangana");
        arrayList.add("Rajasthan");
        arrayList.add("Bihar");
        arrayList.add("Haryana");
        arrayList.add("Assam");
        arrayList.add("Madhya Pradesh");
        arrayList.add("Odisha");
        arrayList.add("Jammu and Kashmir");
        arrayList.add("Kerala");
        arrayList.add("Punjab");
        arrayList.add("Jharkhand");
        arrayList.add("Chattisgarh");
        arrayList.add("Uttarakhand");
        arrayList.add("Goa");
        arrayList.add("Tripura");
        arrayList.add("Puducherry");
        arrayList.add("Manipur");
        arrayList.add("Himachal Pradesh");
        arrayList.add("Ladakh");
        arrayList.add("Nagaland");
        arrayList.add("Arunachal Pradesh");
        arrayList.add("Chandigarh");
        arrayList.add("Dadra and Nagar Haveli and Daman and Diu");
        arrayList.add("Meghalaya");
        arrayList.add("Sikkim");
        arrayList.add("Mizoram");
        arrayList.add("Andaman and Nicobar ");
        arrayList.add("Lakshadweep");

        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.setVisibility(View.INVISIBLE);
            }
        });


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList) {

            @Override
            public View getView(int position, View convertView,  ViewGroup viewGroup) {
                View view = super.getView(position, convertView, viewGroup);

                TextView textView = (TextView) view.findViewById(android.R.id.text1);

                /*YOUR CHOICE OF COLOR*/
                textView.setTextColor(Color.WHITE);
                return view;
            }
        };



        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position,
                                    long id) {
                if(g==0) {
                    g=1;
                    layout.setVisibility(View.VISIBLE);
                    str= (String) listView.getItemAtPosition(position);
                    DownloadTask task = new DownloadTask();
                    task.execute("https://covid-19india-api.herokuapp.com/v2.0/state_data");
                }
                else{
                    layout.setVisibility(View.INVISIBLE);
                    t1.setText("Loading...");
                    t2.setText("Loading...");
                    t3.setText("Loading...");
                    t4.setText("Loading...");
                    t5.setText("Loading...");
                    t6.setText("Loading...");
                    t7.setText("Loading...");
                    g=0;
                }



            }
        });

    }


    String result;
    int data;
    URLConnection urlConnection;

    public class DownloadTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            result = "";
            URL url;
            urlConnection = null;

            try {
                url = new URL(urls[0]);
                urlConnection = url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                data = reader.read();
                while (data != -1) {
                    char current = (char) data;
                    result = result + current;
                    data = reader.read();
                }
                return result;

            } catch (Exception e) {
               t2.setText("Fuck");
            }

            return null;
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            try {
                JSONArray jsonArray=new JSONArray(result);
                JSONObject jsonObject1 = new JSONObject(jsonArray.get(1).toString());
                String string=jsonObject1.getString("state_data");
                JSONArray jsonObject2 = new JSONArray(string);
                for(int i=0;i<jsonObject2.length();i++)
                {

                    JSONObject jsonObject = jsonObject2.getJSONObject(i);
                    if(jsonObject.getString("state").equalsIgnoreCase(str))
                    {  String cases = jsonObject.getString("active_rate");
                    String todayCases = jsonObject.getString("confirmed");
                    String deaths = jsonObject.getString("deaths");
                    String todayDeaths = jsonObject.getString("death_rate");
                    String recovered = jsonObject.getString("recovered");
                    String active = jsonObject.getString("active");
                    String critical = jsonObject.getString("recovered_rate");
                    t1.setText(active);
                    t2.setText(cases);
                    t3.setText(todayCases);
                    t4.setText(recovered);
                    t5.setText(critical);
                    t6.setText(deaths);
                    t7.setText(todayDeaths);}
                }
                  /*  JSONArray jsonArray=new JSONArray(result);
                    JSONObject jsonPart=jsonArray.getJSONObject(0);
                    String deaths = jsonPart.getString("deaths");
                    String recovered = jsonPart.getString("recovered");
                    String confirmed = jsonPart.getString("confirmed");
                    String active = jsonPart.getString("active");
                    String achanges = jsonPart.getString("achanges");
                    String cchanges = jsonPart.getString("cchanges");
                    String dchanges = jsonPart.getString("dchanges");
                    String rchanges = jsonPart.getString("rchanges");

                    t6.setText(rchanges);
                    t8.setText(dchanges);
                    t2.setText(achanges);
                    t4.setText(cchanges);
                    t5.setText(recovered);
                    t7.setText(deaths);
                    t1.setText(active);
                    t3.setText(confirmed);*/

            } catch (Exception e ) {
                t5.setText("naah");
            }
        }


    }
}

/*   */