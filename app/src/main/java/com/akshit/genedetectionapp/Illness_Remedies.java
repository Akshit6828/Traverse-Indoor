package com.akshit.genedetectionapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.util.BuddhistCalendar;
import android.os.Bundle;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class Illness_Remedies extends AppCompatActivity  {
    androidx.appcompat.widget.Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    String choice;
    int[] tabIcons = {R.drawable.herbal2,R.drawable.medication};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_illness__remedies);
        preferences=this.getSharedPreferences("Local_Details", Context.MODE_PRIVATE);//Mode private as with it the file can only be accessed using calling application
        editor=preferences.edit();
        choice= preferences.getString("Choice",null);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        viewPager = findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        tabLayout =findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
        if(choice.equals("asthma"))
            getSupportActionBar().setTitle("Asthma | Breathing Problem");
        else if(choice.equals("cold"))
            getSupportActionBar().setTitle("Cold and Flu");
        else if(choice.equals("depression"))
            getSupportActionBar().setTitle("Depression");
        else if(choice.equals("diabetes"))
            getSupportActionBar().setTitle("Diabetes");
        else if(choice.equals("bloodpressure"))
            getSupportActionBar().setTitle("High Blood Pressure");
        else if(choice.equals("migraine"))
            getSupportActionBar().setTitle("Migraine");
        else if(choice.equals("thyroid"))
            getSupportActionBar().setTitle("Hyperthyroidism | Thyroid");
        else if(choice.equals("cholesterol"))
            getSupportActionBar().setTitle("Cholesterol");
        else if(choice.equals("pinkeye"))
            getSupportActionBar().setTitle("Conjunctivitis | pink eye");
        else if(choice.equals("diarrhea"))
            getSupportActionBar().setTitle("Diarrhea");
        else if(choice.equals("insomnia"))
            getSupportActionBar().setTitle("Insomnia | Sleeplessness");







    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPageAdapter adapter = new ViewPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new Natural_Medi_Fragment(), "Natural Remedies");
        adapter.addFragment(new Medicine_Fragment(),"Medication");

        viewPager.setAdapter(adapter);

    }
    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
    }




    class ViewPageAdapter extends FragmentPagerAdapter{

        List<Fragment> list=new ArrayList<Fragment>();
        List<String> titleList=new ArrayList<String>();

        public ViewPageAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titleList.get(position);
        }

        public void addFragment(Fragment fragment, String title) {
            list.add(fragment);
            titleList.add(title);
        }
    }
}

