package com.akshit.genedetectionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.akshit.genedetectionapp.databinding.ActivityNotificationMessageBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class NotificationMessage extends AppCompatActivity {

    ActivityNotificationMessageBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //View Binding method
        binding=ActivityNotificationMessageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Bundle bundle = getIntent().getExtras();
        final String relation=bundle.getString("relationship");
        final String msg=bundle.getString("message");
        binding.tvRelation.setText("Relation :"+relation);
        binding.tvMessage.setText("Message: "+msg);
        binding.call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
//               intent.setData(Uri.parse("tel:0987654321"));
                startActivity(intent);
            }
        });
        binding.messageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                sendIntent.putExtra("sms_body", msg);
                sendIntent.setType("vnd.android-dir/mms-sms");
                startActivity(sendIntent);
            }
        });


    }
}