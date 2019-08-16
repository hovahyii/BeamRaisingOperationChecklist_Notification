package com.hovah_inc.beamraisingoperationchecklist;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class Checklist extends AppCompatActivity {

    private WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checklist);


        webview = findViewById(R.id.webview);
        webview.setWebViewClient(new WebViewClient());

        webview.loadUrl("https://docs.google.com/spreadsheets/d/1_TM4DOhR0G3D-kfn7vtUCC-zSobs1QSuFLjgJZZDxss/edit?usp=sharing");

        webview.getSettings().setLoadWithOverviewMode(true);
        webview.getSettings().setUseWideViewPort(true);
        String userAgent = "Mozilla/5.0 (X11; Linux x86_64)  AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.137 Safari/537.36";
        webview.getSettings().setUserAgentString(userAgent);

        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setLoadWithOverviewMode(true);
        webview.getSettings().setUseWideViewPort(true);

        webview.getSettings().setSupportZoom(true);
        webview.getSettings().setBuiltInZoomControls(true);
        webview.getSettings().setDisplayZoomControls(false);

        webview.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        webview.setScrollbarFadingEnabled(false);
    }

    @Override
    public void onBackPressed() {
        if(webview.canGoBack()){
            webview.goBack();
        }else {
            super.onBackPressed();
        }
    }


}