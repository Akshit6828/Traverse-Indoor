package com.akshit.genedetectionapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.ActivityManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

public class MainPage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, CustomDialogProfile.CustomDialogProfileListener {
    private DrawerLayout drawerLayout;
    AlertDialog.Builder builder;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    String message,getname;
    int flag=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        pref= getSharedPreferences("Local_Details",Context.MODE_PRIVATE);
        getname=pref.getString("username_key","User");
        editor = pref.edit();
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new fragment_dashboad()).commit();

    }

    @Override
    public void onBackPressed() {
        if(flag!=1)
        {
            message = "not pressed";
            editor.putString("logout_key", message);
            editor.commit();
        }
        else
        {
            editor.putString("username_key", getname);
            editor.commit();
        }
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_dashboad:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new fragment_dashboad()).commit();
                closeDrawer();
                break;
            case R.id.nav_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new fragment_profile()).commit();
                closeDrawer();
                break;
            case R.id.nav_about:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new fragment_about()).commit();
                closeDrawer();
                break;
            case R.id.nav_notify:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new fragment_set_reminder()).commit();
                closeDrawer();
                break;

            case R.id.nav_logout:
                closeDrawer();
                builder = new AlertDialog.Builder(MainPage.this);
                builder.setTitle("Logout");
                builder.setMessage("Are you sure, you want to logout?");
                builder.setIcon(R.drawable.alert_info);
                builder.setCancelable(false);

                builder.setPositiveButton("Yes, sure", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        message = null;
                        editor.putString("logout_key", message);
                        editor.commit();
                        flag = 1;
                        //clear cache
                        if (Build.VERSION_CODES.KITKAT <= Build.VERSION.SDK_INT) {
                            ((ActivityManager) MainPage.this.getSystemService(ACTIVITY_SERVICE))
                                    .clearApplicationUserData();
                            return;
                        }
                        Intent intent=new Intent(MainPage.this,MainActivity.class);
                        startActivity(intent);
                        finish();

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.show();
                break;
        }
        return true;
    }

    public void closeDrawer() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    @Override
    public void applyTexts(String name, String dob, String gender, String relation) {
     //   Toast.makeText(this, "MainPage applyText called..with name is "+name+"rel ="+relation+"dob is "+dob, Toast.LENGTH_SHORT).show();
       /* PedigreeAnalysis.fetched_name=name;
        PedigreeAnalysis.fetched_dob=dob;
        PedigreeAnalysis.fetched_gender=gender;
        PedigreeAnalysis.fetched_relation=relation;*/
    }
    public interface IOnBackPressed {
        boolean onBackPressed();
    }
}
