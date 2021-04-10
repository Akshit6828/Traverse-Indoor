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

public class NucleotideBlast extends AppCompatActivity {
    WebView wb;
    Button b1,b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nucleotide_blast);
        wb=findViewById(R.id.wb1);
        TextView close=findViewById(R.id.close);
        WebSettings ws=wb.getSettings();
        Toast t= Toast.makeText(this, "Please wait for few seconds to load..", Toast.LENGTH_SHORT);
        t.setGravity(Gravity.CENTER,0,0);
        t.show();
        wb.getSettings().setBuiltInZoomControls(true);
        ws.setJavaScriptEnabled(true);
        wb.setWebViewClient(new WebViewClient());
        wb.loadUrl("https://blast.ncbi.nlm.nih.gov/Blast.cgi");
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
