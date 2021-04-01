package com.akshit.genedetectionapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class Match_Symptoms extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    String[] all_disease_common;
    String[] not_common_disease;
    RecyclerView recyclerView,recyclerView2;
    RecyclerView.Adapter adapter,adapter2;
    int sentno;
    RecyclerView.LayoutManager layoutManager,layoutManager2;
    //public  static  ArrayList<String> selected_symptoms;
    public  static  ArrayList<String> matched_symptoms_user1;
    public  static  ArrayList<String> matched_symptoms_user2;
    public  static  ArrayList<String> matched_symptoms_user3;
    public  static  ArrayList<String> matched_symptoms_user4;
    public  static  ArrayList<String> matched_symptoms_user5;
    public  static  ArrayList<String> matched_symptoms_user6;
    public  static  ArrayList<String> matched_symptoms_user7;
    public  static  ArrayList<String> matched_symptoms_user8;
    public  static  ArrayList<String> matched_symptoms_user9;
    Button button;
    String cur_username,cur_relation;
    public Match_Symptoms() {
        // Required empty public constructor
    }

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
        matched_symptoms_user1=new ArrayList<>();
        matched_symptoms_user2=new ArrayList<>();
        matched_symptoms_user3=new ArrayList<>();
        matched_symptoms_user4=new ArrayList<>();
        matched_symptoms_user5=new ArrayList<>();
        matched_symptoms_user6=new ArrayList<>();
        matched_symptoms_user7=new ArrayList<>();
        matched_symptoms_user8=new ArrayList<>();
        matched_symptoms_user9=new ArrayList<>();
        //selected_symptoms= new ArrayList<>();-- reserved for preserving the matched symptoms.
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View my_view =inflater.inflate(R.layout.fragment_match__symptoms, container, false);
        button=my_view.findViewById(R.id.submit_symptom);
        if(getArguments()!=null){
            cur_username=getArguments().getString("UserName");
            cur_relation=getArguments().getString("UserRelation");

        }
        else{
            Toast.makeText(getActivity(), "Sorry No args fetched. ", Toast.LENGTH_SHORT).show();
        }
        if(cur_relation!=null) {
            switch (cur_relation) {
                case "Paternal Grand Father": sentno = 1;
                    break;
                case "Paternal Grand Mother": sentno = 2;
                  //  Toast.makeText(getActivity(), "currltn!=null with sent no=2", Toast.LENGTH_SHORT).show();
                    break;
                case "Maternal Grand Father": sentno = 3;
                    break;
                case "Maternal Grand Mother": sentno = 4;
                    break;
                case "Father":sentno = 5;
                    break;
                case "Mother":sentno = 6;
                    break;
                case "Self":sentno = 7;
                    break;
                case "Child 1 Female":sentno = 8;
                    break;
                case "Child 1 Male":sentno = 9;
                    break;
            }
        }

        recyclerView=my_view.findViewById(R.id.recycler_viewMatch_symptoms);
        recyclerView2=my_view.findViewById(R.id.recycler_view2);
        recyclerView.setHasFixedSize(true);
        recyclerView2.setHasFixedSize(true);

        layoutManager= new LinearLayoutManager(getActivity());
        layoutManager2= new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView2.setLayoutManager(layoutManager2);
        all_disease_common=getResources().getStringArray(R.array.all_symptoms);
        not_common_disease=getResources().getStringArray(R.array.not_common_symptoms);
        adapter= new MainAdapter(all_disease_common,sentno);
        adapter2= new MainAdapter(not_common_disease,sentno);

        recyclerView.setAdapter(adapter);
        recyclerView2.setAdapter(adapter2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                preventive_measures obj= new preventive_measures();
                Bundle args= new Bundle();
                String backstack="PedigreeAnalysis";
                    switch (sentno){
                        case 1:
                            if(matched_symptoms_user1.size()>0)
                                args.putInt("SentNo_Key",sentno);
                            else
                                args.putInt("SentNo_Key",0);
                            obj.setArguments(args);
                            break;
                        case 2:
                            if(matched_symptoms_user2.size()>0)
                                args.putInt("SentNo_Key", sentno);
                            else
                                args.putInt("SentNo_Key", 0);
                            obj.setArguments(args);
                            break;
                        case 3:
                            if(matched_symptoms_user3.size()>0)
                                args.putInt("SentNo_Key",sentno);
                            else
                                args.putInt("SentNo_Key",0);
                            obj.setArguments(args);
                            break;
                        case 4:
                            if(matched_symptoms_user4.size()>0)
                                args.putInt("SentNo_Key",sentno);
                            else
                                args.putInt("SentNo_Key",0);
                            obj.setArguments(args);
                            break;
                        case 5:
                            if(matched_symptoms_user5.size()>0)
                                args.putInt("SentNo_Key",sentno);
                            else
                                args.putInt("SentNo_Key",0);
                            obj.setArguments(args);
                            break;
                        case 6:
                            if(matched_symptoms_user6.size()>0)
                                args.putInt("SentNo_Key",sentno);
                            else
                                args.putInt("SentNo_Key",0);
                            obj.setArguments(args);
                            break;
                        case 7:
                            if(matched_symptoms_user7.size()>0)
                                args.putInt("SentNo_Key",sentno);
                            else
                                args.putInt("SentNo_Key",0);
                            obj.setArguments(args);
                            break;
                        case 8:
                            if(matched_symptoms_user8.size()>0)
                                args.putInt("SentNo_Key",sentno);
                            else
                                args.putInt("SentNo_Key",0);
                            obj.setArguments(args);
                            break;
                        case 9:
                            if(matched_symptoms_user9.size()>0)
                                args.putInt("SentNo_Key",sentno);
                            else
                                args.putInt("SentNo_Key",0);
                            obj.setArguments(args);
                            break;

                    }
                getActivity().getSupportFragmentManager().
                        beginTransaction().
                        replace(R.id.fragment_container,obj).addToBackStack(backstack).commit();



            }
        });

        return my_view;
    }
}