package com.akshit.genedetectionapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
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
    String finalChoice;
    int[] tabIcons = {R.drawable.herbal2,R.drawable.medication};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_illness__remedies);
       /* Bundle b=getIntent().getExtras();
        if(b!=null){
            String choice=b.getString("CHOICE_KEY");
            Toast.makeText(this, "B is not null", Toast.LENGTH_SHORT).show();
            Natural_Medi_Fragment obj= new Natural_Medi_Fragment();
            Bundle bundle=new Bundle();
            bundle.putString("CHOICE_KEY2",choice);
            obj.setArguments(bundle);
//        Toast.makeText(getActivity(), "Bundal value"+bundle, Toast.LENGTH_SHORT).show();


        }
        else{
            Toast.makeText(this, "bundle on Illness is null", Toast.LENGTH_SHORT).show();
        }*/




        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        viewPager = findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        tabLayout =findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
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

