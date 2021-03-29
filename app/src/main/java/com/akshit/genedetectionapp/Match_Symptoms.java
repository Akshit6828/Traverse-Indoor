package com.akshit.genedetectionapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Match_Symptoms#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Match_Symptoms extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    //Here data_model class is same as arraylist object for the purpose.
   // ArrayList<Data_model_match_symptoms> dataholder;
    String[] all_disease_Arraylist;
    RecyclerView recyclerView;
//    String[]  diabetes;
//    String[] migrane;
//    String[] hyperthyroid;
//    String[] hypothyroid;
//    String[] congenital_heart_disease;
//    String[] thalassemia;
//    String[] rheumatoid_arthritis;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    public Match_Symptoms() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Match_Symptoms.
     */
    // TODO: Rename and change types and number of parameters
    public static Match_Symptoms newInstance(String param1, String param2) {
        Match_Symptoms fragment = new Match_Symptoms();
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
        View myview =inflater.inflate(R.layout.fragment_match__symptoms, container, false);
        recyclerView=myview.findViewById(R.id.recycler_viewMatch_symptoms);
        recyclerView.setHasFixedSize(true);
        layoutManager= new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
       // all_disease_Arraylist= new ArrayList<>();
    //   dataholder= new ArrayList<>();


//        Data_holder_al_for_Symptoms=new ArrayList<>();
//        Data_model_match_symptoms  obj1= new Data_model_match_symptoms("Urinate Alot at Night");
//        Data_holder_al_for_Symptoms.add(obj1);
//        Data_model_match_symptoms  obj2= new Data_model_match_symptoms("Gets hungry after small intervals of food intake");
//        Data_holder_al_for_Symptoms.add(obj2);
        //Data_model_match_symptoms<> obj= new Data_model_match_symptoms(diabetes);
        
        
        //diabetes= getResources().getStringArray(R.array.diabetes_symptoms);
       // adapter=new MainAdapter(diabetes);
       // recyclerView.setAdapter(adapter);
//        migrane= getResources().getStringArray(R.array.migrane_symptoms);
//        //adapter=new MainAdapter(migrane);
//        //recyclerView.setAdapter(adapter);
//        hyperthyroid= getResources().getStringArray(R.array.hyperthyroid_symptoms);
//        hypothyroid= getResources().getStringArray(R.array.hypothyroid_symptoms);
//        congenital_heart_disease= getResources().getStringArray(R.array.congenital_heart_disease_symptoms);
//        thalassemia= getResources().getStringArray(R.array.thalassemia_symptoms);
//        rheumatoid_arthritis= getResources().getStringArray(R.array.rheumatoid_arthritis_symptoms);
//        all_disease_Arraylist.add(diabetes);
//        all_disease_Arraylist.add(migrane);
//        all_disease_Arraylist.add(hyperthyroid);
//        all_disease_Arraylist.add(hypothyroid);
//        all_disease_Arraylist.add(congenital_heart_disease);
//        all_disease_Arraylist.add(thalassemia);
//        all_disease_Arraylist.add(rheumatoid_arthritis);
       //Data_model_match_symptoms obj1 = new Data_model_match_symptoms(all_disease_Arraylist);
      //  adapter= new MainAdapter(diabetes,migrane,hyperthyroid,hypothyroid,congenital_heart_disease,thalassemia,rheumatoid_arthritis);
        all_disease_Arraylist=getResources().getStringArray(R.array.all_symptoms);
      adapter= new MainAdapter(all_disease_Arraylist);
        recyclerView.setAdapter(adapter);
        // recyclerView.setAdapter(new CustomAdapterMatch_Symptoms(diabetes));



        return myview;
    }
}