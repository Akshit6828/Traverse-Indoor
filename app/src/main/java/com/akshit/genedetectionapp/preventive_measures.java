package com.akshit.genedetectionapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class preventive_measures extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    int number;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
ArrayList<String> selected_symptoms;
List<String> diabetes;
    public preventive_measures() {
        // Required empty public constructor
    }
    public preventive_measures(int number){
        this.number=number;
    }

    public static preventive_measures newInstance(String param1, String param2) {
        preventive_measures fragment = new preventive_measures();
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
        View v= inflater.inflate(R.layout.fragment_preventive_measures, container, false);
       //selected_symptoms=new ArrayList<>();
        diabetes= Arrays.asList(getResources().getStringArray(R.array.diabetes_symptoms));




       if(getArguments()!=null) {
           int c=getArguments().getInt("SentNo_Key");
           Toast.makeText(getActivity(), "Preventive_Measure  Args is +ve with userno = "+c, Toast.LENGTH_SHORT).show();
           switch (c){
               case 0:
                   Toast.makeText(getActivity(), "NO Disease Found..!", Toast.LENGTH_SHORT).show();
                    break;
               case 1:
                   Toast.makeText(getActivity(), "Symptoms founded for 1st person", Toast.LENGTH_SHORT).show();
                   checkProbability(Match_Symptoms.matched_symptoms_user2);
                   break;
               case 2:
                   Toast.makeText(getActivity(), "Symptoms founded for 2nd person", Toast.LENGTH_SHORT).show();
                   break;
               case 3:
                   Toast.makeText(getActivity(), "Symptoms founded for 3rd person", Toast.LENGTH_SHORT).show();
                   break;
               case 4:
                   Toast.makeText(getActivity(), "Symptoms found for 4th person ", Toast.LENGTH_SHORT).show();
                   break;
               case 5:
                   Toast.makeText(getActivity(), "Symptoms found for 5th person ", Toast.LENGTH_SHORT).show();
                   break;
               case 6:
                   Toast.makeText(getActivity(), "Symptoms found for 6th", Toast.LENGTH_SHORT).show();
                   break;
               case 7:
                   Toast.makeText(getActivity(), "Symptoms found for 7th", Toast.LENGTH_SHORT).show();
                   break;
               case 8:
                   Toast.makeText(getActivity(), "Symptoms found for 8th", Toast.LENGTH_SHORT).show();
                   break;
               case 9:
                   Toast.makeText(getActivity(), "Symptoms found for 9th", Toast.LENGTH_SHORT).show();
                   break;


           }
       }
       else {
           Toast.makeText(getActivity(), "Error in getting arguments.", Toast.LENGTH_SHORT).show();
       }
        return  v;
    }

    private void checkProbability(ArrayList<String> matched_symptoms_user2) {

    }
}