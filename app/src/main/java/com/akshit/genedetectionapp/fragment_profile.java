package com.akshit.genedetectionapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_profile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_profile extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public fragment_profile() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_profile.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment_profile newInstance(String param1, String param2) {
        fragment_profile fragment = new fragment_profile();
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
        View myview= inflater.inflate(R.layout.fragment_profile, container, false);
        preferences=getActivity().getSharedPreferences("Local_Details",Context.MODE_PRIVATE);
        editor=preferences.edit();
        String get_name=preferences.getString("username_key","Akshit");
        String get_phone=preferences.getString("userphone_in_sharedpreference","9465675515");
        int get_age=preferences.getInt("Age_user",20);
        String get_blood_group=preferences.getString("Bloodgroup_user","A+");
        TextView name=myview.findViewById(R.id.name);
        TextView age=myview.findViewById(R.id.age);
        TextView bloodgroup=myview.findViewById(R.id.blood);
        TextView contact=myview.findViewById(R.id.phone);

        name.setText("Name : "+get_name);
        contact.setText("Contact : "+get_phone);
        age.setText("Age : "+get_age);
        bloodgroup.setText("Blood Group : "+get_blood_group);

        return myview;


    }
}