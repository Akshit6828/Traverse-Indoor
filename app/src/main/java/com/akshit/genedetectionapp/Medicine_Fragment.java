package com.akshit.genedetectionapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Medicine_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Medicine_Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    TextView textView1,textView2;
    ImageView doc,med;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    String choice;

    public Medicine_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Medicine_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Medicine_Fragment newInstance(String param1, String param2) {
        Medicine_Fragment fragment = new Medicine_Fragment();
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
        View mylayout= inflater.inflate(R.layout.fragment_medicine_, container, false);
        textView1=mylayout.findViewById(R.id.data);
        textView2=mylayout.findViewById(R.id.textView28);
        doc=mylayout.findViewById(R.id.imageView5);
        med=mylayout.findViewById(R.id.imageView7);
        preferences=getActivity().getSharedPreferences("Local_Details", Context.MODE_PRIVATE);//Mode private as with it the file can only be accessed using calling application
        editor=preferences.edit();
        choice= preferences.getString("Choice",null);
        if(choice!=null) {
            // String choice=bundle.getString("CHOICE_KEY2");
            if (choice.equals("asthma")) {
               textView1.setText(R.string.medicine_asthma);
            }
            else if(choice.equals("cold")){
                textView1.setText(R.string.medicine_cold);
            }
            else if(choice.equals("depression")){
                textView1.setText(R.string.antidepressant);
            }
            else if(choice.equals("diabetes")){
                textView1.setText(R.string.med_diabetes);

            }
            else if(choice.equals("bloodpressure")){
                textView1.setText(R.string.med_hbp);

            }
            else if(choice.equals("migraine")){
                textView1.setText(R.string.migraine_med);
            }
            else if(choice.equals("thyroid"))
                textView1.setText(R.string.thyroid_med);
            else if(choice.equals("cholesterol"))
                textView1.setText(R.string.chol_med);
            else if(choice.equals("pinkeye"))
                textView1.setText(R.string.med_conj);
            else if(choice.equals("diarrhea"))
                textView1.setText(R.string.med_diarrhea);
            else if(choice.equals("insomnia"))
                textView1.setText(R.string.med_sleep);

        }

        return mylayout;
    }
}