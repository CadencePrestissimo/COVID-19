package com.example.covid_19;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class world extends AppCompatActivity {


    EditText editText;
    LinearLayout layout;
    ImageView imageView;
    TextView t1, t2, t3, t4, t5, t6, t7;

    public void give(View view) throws UnsupportedEncodingException {
        imageView.setVisibility(View.INVISIBLE);
        layout.setVisibility(View.VISIBLE);
        String name=editText.getText().toString();
        String country= URLEncoder.encode(name,"UTF-8");
        try {
            DownloadTask task = new DownloadTask();
            task.execute("https://coronavirus-19-api.herokuapp.com/countries/"+country);
        } catch (
                Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "no", Toast.LENGTH_LONG);
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_world);
        imageView = (ImageView) findViewById(R.id.imageView7);
        layout = (LinearLayout) findViewById(R.id.list1);
        editText=(EditText) findViewById(R.id.editTextTextPersonName);

        t1 = (TextView) findViewById(R.id.textView27);
        t2 = (TextView) findViewById(R.id.textView29);
        t3 = (TextView) findViewById(R.id.textView31);
        t4 = (TextView) findViewById(R.id.textView33);
        t5 = (TextView) findViewById(R.id.textView35);
        t6 = (TextView) findViewById(R.id.textView37);
        t7 = (TextView) findViewById(R.id.textView39);

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
                Toast.makeText(getApplicationContext(), "Could not find updates", Toast.LENGTH_LONG);
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            try {
                JSONObject jsonObject = new JSONObject(result);
                String cases = jsonObject.getString("cases");
                String todayCases = jsonObject.getString("todayCases");
                String deaths = jsonObject.getString("deaths");
                String todayDeaths = jsonObject.getString("todayDeaths");
                String recovered = jsonObject.getString("recovered");
                String active = jsonObject.getString("active");
                String critical = jsonObject.getString("critical");
                t1.setText(cases);
                t2.setText(todayCases);
                t3.setText(deaths);
                t4.setText(recovered);
                t5.setText(active);
                t6.setText(todayDeaths);
                t7.setText(critical);

            } catch (JSONException e) {
                Toast.makeText(getApplicationContext(), "Could not find", Toast.LENGTH_LONG);
            }
        }
    }
}