package com.akshit.genedetectionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

public class SplashScreen extends AppCompatActivity {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        //Remove Status Bar
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        //getSupportActionBar().hide();
        pref= getSharedPreferences("Local_Details", Context.MODE_PRIVATE);
        editor=pref.edit();


        //Remove Toolbar of this activity only


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String name = pref.getString("username_key", null);
                String key=pref.getString("logout_key",null);
                if(name==null && key==null) {
                    Intent i = new Intent(SplashScreen.this,MainActivity.class);
                    startActivity(i);
                    finish();
                }
                else
                {
                    Intent i = new Intent(SplashScreen.this, MainPage.class);
                    //i.putExtra("name_key",name);
                    startActivity(i);
                    finish();
                }

            }
        },2020);
    }
}
