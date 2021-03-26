package com.akshit.genedetectionapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_medication#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_medication extends Fragment implements MedicationAdapter.OnClickItem {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private CardView cardView;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RecyclerView recyclerView;


    Integer img_des[]={R.drawable.asthma1,R.drawable.cholesterol,R.drawable.cold2,R.drawable.conjunctivitis2,R.drawable.depression,R.drawable.diabetes2,R.drawable.diarrhea2,R.drawable.bloodpresssure,R.drawable.insomnia,R.drawable.migraine2,R.drawable.thyroid2};
    String text_des[]={"Asthma | Breathing Problem","Cholesterol","Colds and Flu","Conjunctivitis | pink eye","Depression","Diabetes","Diarrhea","High Blood Pressure","Insomnia | Sleeplessness","Migraine","Thyroid"};
    String type_des[]={"Type: Genetic","Type: Genetic","Type: Infectious","Type: Infectious","Type: Heredity","Type: Genetic","Type: Infectious","Type: Genetic","Type: Genetic","Type: Genetic","Type: Genetic"};

    public fragment_medication() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_medication.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment_medication newInstance(String param1, String param2) {
        fragment_medication fragment = new fragment_medication();
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
        // Inflate the layout

        View mylayout= inflater.inflate(R.layout.fragment_medication, container, false);
        recyclerView=mylayout.findViewById(R.id.recyclerViewMedi);
        cardView=mylayout.findViewById(R.id.view_disease);
        //cardView.setBackgroundResource(R.drawable.container_bg);
        MedicationAdapter adapter=new MedicationAdapter(img_des,text_des,type_des,getActivity(),this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(adapter);
        return mylayout;
    }

    @Override
    public void ItemClick(int position) {
        Intent intent = new Intent(getActivity(), Illness_Remedies.class);
        startActivity(intent);

    }
}