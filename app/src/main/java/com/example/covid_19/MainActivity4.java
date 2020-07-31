package com.example.covid_19;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity4 extends AppCompatActivity {
    WebView displayVideo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        String frameVideo =(String)"\n" +
                "<iframe width=\"400\" height=\"229\" src=\"https://www.youtube.com/embed/U8r3oTVMtQ0\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>";

        displayVideo = (WebView)findViewById(R.id.Web1);
        displayVideo.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
        WebSettings webSettings = displayVideo.getSettings();
        webSettings.setJavaScriptEnabled(true);
        displayVideo.loadData(frameVideo, "text/html", "utf-8");
    }
}