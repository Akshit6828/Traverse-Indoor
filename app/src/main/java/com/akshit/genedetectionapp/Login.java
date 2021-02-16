package com.akshit.genedetectionapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.TimeUnit;

public class Login extends AppCompatActivity {
    EditText e1,e2;
    Button b1,b2;
    String phonenumber="";
    ProgressBar progressBar;
    String verificationCodeBySystem;
    FirebaseDatabase database;
    DatabaseReference reference;
    String nameofuser="";
    int flag=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        database=FirebaseDatabase.getInstance();
        reference=database.getReference("Users_Database_1");
        progressBar=findViewById(R.id.progressbar);
        b1=findViewById(R.id.b1);
        b2=findViewById(R.id.button2);
        e1=findViewById(R.id.e1);
        e2=findViewById(R.id.e2);

        b1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                if(e1.getText().toString().isEmpty())
                   e1.setError("Field Required");
                else {
                          //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);----
                    //startActivity(i);//--------------------
                    phonenumber=e1.getText().toString();
                    //************************************************************************Confirming phone number from Database****************************************
                    reference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for(DataSnapshot i:dataSnapshot.getChildren())
                            {
                                StoringUserDetails obj=i.getValue(StoringUserDetails.class);
                                String phoneindb=obj.getPhone_number();
                                if(phoneindb.equals(phonenumber))
                                {
                                    flag=1;
                                    nameofuser=obj.getName();
                                    //Toast.makeText(Login.this, "User Confirmed and Name of User is "+nameofuser, Toast.LENGTH_SHORT).show();
                                    phonenumber = e1.getText().toString();
                                    progressBar.setVisibility(View.VISIBLE);
                                    sendVerificationCodeToUser(phonenumber);
                                   // Intent ji=new Intent(Login.this,MainPage.class);
                                    //ji.putExtra("username",nameofuser);
                                    //startActivity(ji);
                                }
                            }
                            if(flag==0)
                            {
                                Toast.makeText(Login.this, "Number Not Registered..", Toast.LENGTH_SHORT).show();
                              Intent intent= new Intent(Login.this,MainActivity.class);
                              startActivity(intent);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                    //******************************************************************************

                }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyCode(e2.getText().toString());
            }
        });


    }

   private String sendVerificationCodeToUser(String phonenumber) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
              "+91" +phonenumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                TaskExecutors.MAIN_THREAD,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks

       return verificationCodeBySystem;
    }
    private  PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks= new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

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
            Toast.makeText(Login.this, "Verification Failed", Toast.LENGTH_SHORT).show();
        }
    };

    private void verifyCode(String codeByUser) {

        PhoneAuthCredential credential= PhoneAuthProvider.getCredential(verificationCodeBySystem,codeByUser);
        signInByTheUser(credential);
    }

    private void signInByTheUser(PhoneAuthCredential credential) {
        FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Intent i = new Intent(Login.this,MainPage.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        i.putExtra("username",nameofuser);
                        startActivity(i);
                        }
                        else
                        {
                            Toast.makeText(Login.this, "Sorry Something went Wrong ", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}
