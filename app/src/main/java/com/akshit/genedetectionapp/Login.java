package com.akshit.genedetectionapp;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Login extends AppCompatActivity {
    EditText e1,e2,e3;
    Button b1,b2;
    String phonenumber="";
    ProgressBar progressBar;
    String verificationCodeBySystem;
    FirebaseDatabase database;
    DatabaseReference reference;
    String nameofuser="";
    int flag=0;
    String county_code;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;


    public static ArrayList<Double> grandfather1;
    public static ArrayList<Double> grandmother1;
    public static  ArrayList<Double>  grandfather2;
    public static ArrayList<Double> grandmother2;
    public static  ArrayList<Double> dad;
    public static ArrayList<Double> mom;
    public static ArrayList<Double> me;
    public static ArrayList<Double> child1female;
    public static ArrayList<Double> child2male;

    private FirebaseAuth firebaseAuth;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

    public static ArrayList<Double> percentage_gf1;
    public static ArrayList<Double> percentage_gf1_expected;
    public static ArrayList<Double> percentage_gf2;
    public static ArrayList<Double> percentage_gf2_expected;
    public static ArrayList<Double> percentage_gm2;
    public static ArrayList<Double> percentage_gm2_expected;
    public static ArrayList<Double> percentage_gm1;
    public static ArrayList<Double> percentage_gm1_expected;
    public static ArrayList<Double> percentage_dad;
    public static ArrayList<Double> percentage_dad_expected;
    public static ArrayList<Double> percentage_mom;
    public static ArrayList<Double> percentage_mom_expected;
    public static ArrayList<Double> percentage_me;
    public static ArrayList<Double> percentage_me_expected;
    public static ArrayList<Double> percentage_child1female;
    public static ArrayList<Double> percentage_child1female_expected;
    public static ArrayList<Double> percentage_child2male;
    public static ArrayList<Double> percentage_child2male_expected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        database=FirebaseDatabase.getInstance();
        reference=database.getReference("Users_Database_1");
        progressBar=findViewById(R.id.progressbar);

        grandfather1=new ArrayList<>();
        grandfather2=new ArrayList<>();
        grandmother1=new ArrayList<>();
        grandmother2=new ArrayList<>();
        dad=new ArrayList<>();
        mom=new ArrayList<>();
        me=new ArrayList<>();
        child1female=new ArrayList<>();
        child2male=new ArrayList<>();

        Login.percentage_gf1 = new ArrayList<>();
        Login.percentage_gm1 = new ArrayList<>();
        Login.percentage_gf2 = new ArrayList<>();
        Login.percentage_gm2 = new ArrayList<>();
        Login.percentage_dad = new ArrayList<>();
        Login.percentage_mom = new ArrayList<>();
        Login.percentage_me = new ArrayList<>();
        Login.percentage_child1female = new ArrayList<>();
        Login.percentage_child2male = new ArrayList<>();

        //Local Shared Preference Details.
        preferences=getSharedPreferences("Local_Details",Context.MODE_PRIVATE);//Mode private as with it the file can only be accessed using calling application
        editor=preferences.edit();
        b1=findViewById(R.id.b1);
        b2=findViewById(R.id.button2);
        e1=findViewById(R.id.e1);
        e2=findViewById(R.id.e2);
        e3=findViewById(R.id.county_code);
       firebaseAuth=FirebaseAuth.getInstance();


        b1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (isConnected(Login.this)) {

                if (e1.getText().toString().isEmpty())
                    e1.setError("Field Required");
                if(e3.getText().toString().isEmpty())
                    e3.setError("Field Required");
                else {

                    //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);----
                    //startActivity(i);//--------------------
                    phonenumber = e1.getText().toString();
                     county_code = e3.getText().toString();
                    //************************************************************************Confirming phone number from Database****************************************
                    reference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot i : dataSnapshot.getChildren()) {
                                StoringUserDetails obj = i.getValue(StoringUserDetails.class);
                                String phoneindb = obj.getPhone_number();
                                int age=obj.getAge();
                                String bloodgroup=obj.getBloodgroup();
                                if (phoneindb.equals(phonenumber)) {
                                    flag = 1;
                                    nameofuser = obj.getName();
                                    editor.putString("username_key",nameofuser);
                                    editor.putString("userphone_in_sharedpreference",phonenumber);
                                    editor.putInt("Age_user",age);
                                    editor.putString("Bloodgroup_user",bloodgroup);
                                    editor.putString("Child_Key",i.getKey());
                                    editor.commit();
                                    //Toast.makeText(Login.this, "User Confirmed and Name of User is "+nameofuser, Toast.LENGTH_SHORT).show();
                                    phonenumber = e1.getText().toString();
                                    progressBar.setVisibility(View.VISIBLE);

                                    sendVerificationCodeToUser(phonenumber);// ------- uncomment it to send sms verification .......
//                                     Intent ji=new Intent(Login.this,MainPage.class);
//                                    ji.putExtra("username",nameofuser);
//                                    startActivity(ji);
//                                    finish();

                                }
                            }
                            if (flag == 0) {
                                Toast.makeText(Login.this, "Number Not Registered..", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Login.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Toast.makeText(Login.this, "Database Error Occurred.", Toast.LENGTH_SHORT).show();

                        }
                    });
                    //******************************************************************************

                    if(!isConnected(Login.this))
                        Toast.makeText(Login.this, "Please Wait a Little..", Toast.LENGTH_SHORT).show();

                }
            }
                else{
                    showCustomDialog();
                }
            }
        });
        mCallbacks  = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                verificationCodeBySystem =s;
            }

            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                String code=phoneAuthCredential.getSmsCode();
                if(code!=null){
                    progressBar.setVisibility(View.INVISIBLE);
                    verifyCode(code);
                }
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                progressBar.setVisibility(View.INVISIBLE);
              Toast.makeText(Login.this, "Verification Failed because: \n"+e.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        };



        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyCode(e2.getText().toString());
            }
        });


    }


    private void showCustomDialog() {
        AlertDialog.Builder builder= new AlertDialog.Builder(Login.this);
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
                        startActivity(new Intent(Login.this,MainActivity.class));
                        finish();
                    }
                });
        builder.show();


    }

    private boolean isConnected(Login login) {
        ConnectivityManager connectivityManager = (ConnectivityManager) login.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wificonn=connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileconn=connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if(wificonn!=null&&wificonn.isConnected()||(mobileconn!=null&&mobileconn.isConnected()))
            return true;
        else
            return  false;
    }



    private void sendVerificationCodeToUser(String phonenumber) {
        phonenumber= county_code+phonenumber;
        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(firebaseAuth)
                .setPhoneNumber(phonenumber)
                .setTimeout(60L,TimeUnit.SECONDS)
                .setActivity(this)
                .setCallbacks(mCallbacks)
                .build();
        PhoneAuthProvider.verifyPhoneNumber(options);

    }


    private void verifyCode(String codeByUser) {

        PhoneAuthCredential credential= PhoneAuthProvider.getCredential(verificationCodeBySystem,codeByUser);
        signInByTheUser(credential);
    }

    private void signInByTheUser(PhoneAuthCredential credential) {
    //  firebaseAuth=FirebaseAuth.getInstance();
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Intent i = new Intent(Login.this,MainPage.class);
                       // i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);
                        Toast.makeText(Login.this, "Verification Successful", Toast.LENGTH_SHORT).show();
                      /* reference=null;
                       firebaseAuth=null;
                       mCallbacks=null;*/

                        finish();
                        }
                        else
                        {

                            Toast.makeText(Login.this, "Sorry Something went Wrong ", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        /*Toast.makeText(this, "On Destroy() called...", Toast.LENGTH_SHORT).show();
        reference=null;
        firebaseAuth=null;
        mCallbacks=null;*/
    }
}
