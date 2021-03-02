package com.akshit.genedetectionapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainPage extends AppCompatActivity {
//    LinearLayout l1, l2, l3, l4;
//    TextView usersname;
    FragmentManager fm=getSupportFragmentManager();
    FragmentTransaction ft=fm.beginTransaction();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        ft.add(R.id.main_page,new fragment_dashboad());
        ft.commit();
        //Fetching Id's of the UI Widgets.
       /* l1=findViewById(R.id.layout1);
        l2=findViewById(R.id.layout2);
        l3=findViewById(R.id.layout3);
        l4=findViewById(R.id.layout4);
        usersname=findViewById(R.id.tv1);

       /* Bundle b =getIntent().getExtras();
       String getname= b.getString("username");
       String firstname="";
       for(int j=0;j<getname.length();j++)
       {
           if(getname.charAt(j)==' ')
               break;
           else
               firstname+=getname.charAt(j);
       }
       usersname.setText("Welcome "+firstname+"!");*/
       /* usersname.setText("Sample");
        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(MainPage.this,NucleotideBlast.class);
                startActivity(i);
            }
        });
        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(MainPage.this,Familytree.class);
                startActivity(i);

            }
        });
        l3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(MainPage.this,GoogleSearchPage.class);
                startActivity(i);

            }
        });
        l4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(MainPage.this,Versionupdates.class);
                startActivity(i);
            }
        });
    }*/
    }
}
