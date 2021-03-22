package com.akshit.genedetectionapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference reference;
    EditText e1,e2,e3,e4;
Button b1;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        database=FirebaseDatabase.getInstance();
        reference=database.getReference("Users_Database_1");
        e1=findViewById(R.id.et1r);
        e2=findViewById(R.id.et2r);
        e3=findViewById(R.id.et3r);
        e4=findViewById(R.id.et4r);
        b1=findViewById(R.id.button);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isConnected(Register.this)) {

                    String name = e1.getText().toString();
                    String phone = e2.getText().toString();
                    int age = Integer.parseInt(e3.getText().toString());
                    String bloodgroup = e4.getText().toString();

                    //Storing users details in DATABASE..
                    StoringUserDetails storingUserDetails = new StoringUserDetails();
                    storingUserDetails.setName(name);
                    storingUserDetails.setPhone_number(phone);
                    storingUserDetails.setAge(age);
                    storingUserDetails.setBloodgroup(bloodgroup);
                    String child_id = reference.push().getKey();
                    reference.child(child_id).setValue(storingUserDetails);
                    Toast.makeText(Register.this, "Registered Successfully...\n  Thankyou", Toast.LENGTH_SHORT).show();

                   //Shifting of Activity back to MainActivity.
                    Intent i = new Intent(Register.this, MainActivity.class);
                    startActivity(i);
                    //Toast.makeText(Register.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                    // Intent i = new Intent(Register.this, Login.class);
                    // startActivity(i);
                } else {
                    showCustomDialog();
                }
            }
        });


    }
    private void showCustomDialog() {
        AlertDialog.Builder builder= new AlertDialog.Builder(Register.this);
        builder.setMessage("Please Connect to Internet to Proceed Furthur!")
                .setCancelable(true)
                .setPositiveButton("Connect", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(Register.this,MainActivity.class));
                        finish();
                    }
                });
        builder.show();


    }

    private boolean isConnected(Register register) {
        ConnectivityManager connectivityManager = (ConnectivityManager) register.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wificonn=connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileconn=connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if(wificonn!=null&&wificonn.isConnected()||(mobileconn!=null&&mobileconn.isConnected()))
            return true;
        else
            return  false;
    }

}
