package com.example.covid_19;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
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
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class India_District extends AppCompatActivity {
     EditText state;
     EditText district;
     ImageView imageView;
     TextView t1,t2,t3,t4;
     LinearLayout linearLayout;
     String state1;
     String district1;
     public void go(View view)
     {
         state1=state.getText().toString();
         district1=district.getText().toString();
         t2.setText("Loading...");
         t1.setText("Loading...");
         t4.setText("Loading...");
         t3.setText("Loading...");
         imageView.setVisibility(View.INVISIBLE);
         linearLayout.setVisibility(View.VISIBLE);
         try {

             DownloadTask task = new DownloadTask();
             task.execute("https://api.covid19india.org/state_district_wise.json");
         } catch (Exception e) {
             e.printStackTrace();
         }
     }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_india__district);

        state=(EditText)findViewById(R.id.editTextTextPersonName);
        district=(EditText)findViewById(R.id.editTextTextPersonName2);
        imageView=(ImageView)findViewById(R.id.imageView7);
        linearLayout=(LinearLayout)findViewById(R.id.list1);
        t1=(TextView)findViewById(R.id.textView31);
        t2=(TextView)findViewById(R.id.textView33);
        t3=(TextView)findViewById(R.id.textView37);
        t4=(TextView)findViewById(R.id.textView29);

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
            } catch (Exception ignored) {
                t2.setText("Nahh");
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            try {
                JSONObject jsonObject1 = new JSONObject(result);
                JSONObject jd=jsonObject1.getJSONObject(state1);
                JSONObject jsonObject2=jd.getJSONObject("districtData");

                JSONObject jsonObject=jsonObject2.getJSONObject(district1);
                String deaths = jsonObject.getString("deceased");
                String recovered = jsonObject.getString("recovered");
                String active = jsonObject.getString("active");
                String confirmed= jsonObject.getString("confirmed");
                t2.setText(active);
                t1.setText(confirmed);
                t4.setText(deaths);
                t3.setText(recovered);


            } catch (JSONException ignored) {
                t3.setText("Nahh");
            }
        }
    }
}