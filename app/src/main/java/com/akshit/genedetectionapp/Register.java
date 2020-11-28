package com.akshit.genedetectionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
                String name=e1.getText().toString();
                String phone=e2.getText().toString();
                int age=Integer.parseInt(e3.getText().toString());
                String bloodgroup=e4.getText().toString();
                StoringUserDetails storingUserDetails = new StoringUserDetails();
                storingUserDetails.setName(name);
                storingUserDetails.setPhone_number(phone);
                storingUserDetails.setAge(age);
                storingUserDetails.setBloodgroup(bloodgroup);
                String child_id=reference.push().getKey();
                reference.child(child_id).setValue(storingUserDetails);
                Toast.makeText(Register.this, "Registered Successfully...\n  Thankyou", Toast.LENGTH_SHORT).show();
                Intent i= new Intent(Register.this,MainActivity.class);
                startActivity(i);
                //Toast.makeText(Register.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
               // Intent i = new Intent(Register.this, Login.class);
               // startActivity(i);
            }
        });

    }
}
