package com.akshit.genedetectionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class GoogleSearchPage extends AppCompatActivity {
    WebView wb;
    Button b1,b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_search_page);
        wb=findViewById(R.id.wb1);
        TextView close=findViewById(R.id.close);
        WebSettings ws=wb.getSettings();
        wb.getSettings().setBuiltInZoomControls(true);
        ws.setJavaScriptEnabled(true);
        wb.setWebViewClient(new WebViewClient());
        wb.loadUrl("https://www.google.com");
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
