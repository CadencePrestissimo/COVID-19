package com.example.covid_19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
   ImageButton b1,b2,b3,b4,b6,b7,b8;
   TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=(TextView)findViewById(R.id.textView2);
        b1=(ImageButton)findViewById(R.id.b1);
        b2=(ImageButton)findViewById(R.id.b2);
        b3=(ImageButton)findViewById(R.id.b3);
        b4=(ImageButton)findViewById(R.id.b4);
        b6=(ImageButton)findViewById(R.id.b6);
        b7=(ImageButton)findViewById(R.id.b7);
        b8=(ImageButton)findViewById(R.id.b8);

        textView.animate().scaleXBy(.85f).scaleYBy(.85f).setDuration(2600);
        b1.animate().rotation(360).setDuration(2600);
        b2.animate().rotation(360).setDuration(2600);
        b3.animate().rotation(360).setDuration(2600);
        b4.animate().rotation(360).setDuration(2600);
        b6.animate().rotation(360).setDuration(2600);
        b7.animate().rotation(360).setDuration(2600);
        b8.animate().rotation(360).setDuration(2600);

        b1.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),MainActivity2.class);
                startActivity(intent);
            }
        });
        b2.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),MainActivity3.class);
                startActivity(intent);
            }
        });
        b4.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),MainActivity4.class);
                startActivity(intent);
            }
        });
        b3.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),MainActivity6.class);
                startActivity(intent);
            }
        });
        b7.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),call_helpline.class);
                startActivity(intent);
            }
        });
        b6.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),MainActivity5.class);
                startActivity(intent);
            }
        });
        b8.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),madeby.class);
                startActivity(intent);
            }
        });
    }
}