package com.akshit.genedetectionapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_medication#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_medication extends Fragment implements MedicationAdapter.OnClickItem, MainPage.IOnBackPressed {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RecyclerView recyclerView;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
   // public fragment_medication_listener listener;



    Integer img_des[]={R.drawable.asthma1,R.drawable.cholesterol,R.drawable.cold2,R.drawable.conjunctivitis2,R.drawable.depression,R.drawable.diabetes2,R.drawable.diarrhea2,R.drawable.bloodpresssure,R.drawable.insomnia,R.drawable.migraine2,R.drawable.thyroid2};
    String text_des[]={"Asthma | Breathing Problem","Cholesterol","Colds and Flu","Conjunctivitis | pink eye","Depression","Diabetes","Diarrhea","High Blood Pressure","Insomnia | Sleeplessness","Migraine","Hyperthyroidism | Thyroid"};
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
        //cardView.setBackgroundResource(R.drawable.container_bg);
        MedicationAdapter adapter=new MedicationAdapter(img_des,text_des,type_des,getActivity(),this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(adapter);
        return mylayout;
    }

    @Override
    public void ItemClick(int position) {
        String choice="";
        switch (position)
        {
            case 0:
                choice="asthma";
                break;
            case 1:
                choice="cholesterol";
                break;
            case 2:
                choice="cold";
                break;
            case 3:
                choice="pinkeye";
                break;
            case 4:
                choice="depression";
                break;
            case 5:
                choice="diabetes";
                break;
            case 6:
                choice="diarrhea";
                break;
            case 7:
                choice="bloodpressure";
                break;
            case 8:
                choice="insomnia";
                break;
            case 9:
                choice="migraine";
                break;
            case 10:
                choice="thyroid";
                break;
        }
//        Natural_Medi_Fragment natural_medi_fragment=new Natural_Medi_Fragment();
//        Bundle bundle=new Bundle();
//        bundle.putString("CHOICE_KEY",choice);
//        Toast.makeText(getActivity(), "Bundal value"+bundle, Toast.LENGTH_SHORT).show();

      //  natural_medi_fragment.setArguments(bundle);
        // 1 Activity-->3 frament---->fragment


        //--pref
        preferences=getActivity().getSharedPreferences("Local_Details",Context.MODE_PRIVATE);//Mode private as with it the file can only be accessed using calling application
       //preferences=getActivity().getSharedPreferences()
        editor=preferences.edit();
        editor.putString("Choice",choice);
        editor.commit();


        Intent intent = new Intent(getActivity(), Illness_Remedies.class);
        //intent.putExtra("CHOICE_KEY",choice);
        startActivity(intent);


    }


    @Override
    public boolean onBackPressed() {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new fragment_dashboad()).commit();
        return false;
    }
}