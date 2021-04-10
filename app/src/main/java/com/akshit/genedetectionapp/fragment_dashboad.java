package com.akshit.genedetectionapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import androidx.fragment.app.FragmentTransaction;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_dashboad#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_dashboad extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    LinearLayout l1, l2, l3, l4;
    TextView usersname;
    SharedPreferences preferences;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public fragment_dashboad() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment dashboad.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment_dashboad newInstance(String param1, String param2) {
        fragment_dashboad fragment = new fragment_dashboad();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View mylayout= inflater.inflate(R.layout.fragment_dashboad, container, false);
        l1=mylayout.findViewById(R.id.layout1);
        l2=mylayout.findViewById(R.id.layout2);
        l3=mylayout.findViewById(R.id.layout3);
        l4=mylayout.findViewById(R.id.layout4);
        usersname=mylayout.findViewById(R.id.tv1);
        preferences= this.getActivity().getSharedPreferences("Local_Details",0);
       String getname=preferences.getString("username_key","User");


       String[] firstname=getname.split(" ");
       usersname.setText("Welcome "+firstname[0]+"!");
        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().
                        beginTransaction().
                        replace(R.id.fragment_container,new PedigreeAnalysis()).commit();
            }
        });
        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getActivity(),NucleotideBlast.class);
                startActivity(i);
            }
        });
        l3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String backstack="PedigreeAnalysis";
                getActivity().getSupportFragmentManager().
                        beginTransaction().
                        replace(R.id.fragment_container,new fragment_medication()).addToBackStack(backstack).commit();
                //Intent i= new Intent(getActivity(),GoogleSearchPage.class);
                //startActivity(i);

            }
        });
        l4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new fragment_set_reminder()).commit();
                Intent i= new Intent(getActivity(),GoogleSearchPage.class);
                startActivity(i);
            }
        });
        return mylayout;
    }
}