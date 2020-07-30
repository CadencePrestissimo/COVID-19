package com.example.covid_19;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
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

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class call_helpline extends AppCompatActivity {

    ListView listView;
    String str;
    ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_helpline);
        listView = (ListView) findViewById(R.id.statelist);
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
        arrayList.add("Jammu & Kashmir");
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
        arrayList.add("Dadra and Nagar Haveli and Daman & Diu");
        arrayList.add("Meghalaya");
        arrayList.add("Sikkim");
        arrayList.add("Mizoram");
        arrayList.add("Andaman and Nicobar Islands");
        arrayList.add("Lakshadweep");


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList) {

            @Override
            public View getView(int position, View convertView, ViewGroup viewGroup) {

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
                str = (String) listView.getItemAtPosition(position);
                DownloadTask task = new DownloadTask();
                task.execute("https://covid-19india-api.herokuapp.com/v2.0/helpline_numbers");

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

            }

            return null;
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            try {
                JSONArray jsonArray = new JSONArray(result);
                JSONObject jsonObject1 = new JSONObject(jsonArray.get(1).toString());
                String string = jsonObject1.getString("contact_details");
                JSONArray jsonObject2 = new JSONArray(string);
                for (int i = 0; i < jsonObject2.length(); i++) {

                    JSONObject jsonObject = jsonObject2.getJSONObject(i);
                    if (jsonObject.getString("state_or_UT").equalsIgnoreCase(str)) {
                        String cases = jsonObject.getString("helpline_number");
                        Toast.makeText(getApplicationContext(),cases,Toast.LENGTH_LONG).show();
                        Intent callIntent = new Intent(Intent.ACTION_CALL);
                        callIntent.setData(Uri.parse("tel:"+cases));

                        if (ActivityCompat.checkSelfPermission(call_helpline.this,
                                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            return;
                        }
                        startActivity(callIntent);
                        }





            }} catch (Exception ignored) {

            }
        }


    }
}