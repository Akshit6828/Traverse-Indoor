package com.akshit.genedetectionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class NucleotideBlast extends AppCompatActivity {
    WebView wb;
    Button b1,b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nucleotide_blast);
        wb=findViewById(R.id.wb1);
        b1=findViewById(R.id.b1);
        b2=findViewById(R.id.b2);
        WebSettings ws=wb.getSettings();
        wb.getSettings().setBuiltInZoomControls(true);
        ws.setJavaScriptEnabled(true);
        wb.setWebViewClient(new WebViewClient());
        wb.loadUrl("https://blast.ncbi.nlm.nih.gov/Blast.cgi");
        b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    wb.goBack();
                }
            });
        b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                wb.goForward();
            }
        });
    }
}
