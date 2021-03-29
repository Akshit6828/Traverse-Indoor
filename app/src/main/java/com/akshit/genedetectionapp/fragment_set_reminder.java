package com.akshit.genedetectionapp;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_set_reminder#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_set_reminder extends Fragment {


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    FloatingActionButton fab;
    EditText c,m,d,t,msg;
    Button set;
    Spinner r;
    String relation[];
    String username,userrelation;

    public fragment_set_reminder() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_set_reminder.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment_set_reminder newInstance(String param1, String param2) {
        fragment_set_reminder fragment = new fragment_set_reminder();
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

        View mylayout=  inflater.inflate(R.layout.fragment_set_reminder, container, false);
        fab=mylayout.findViewById(R.id.floatingActionButton);
        if(getArguments()!=null) {
            username = getArguments().getString("UserName");
            userrelation = getArguments().getString("UserRelation");
            if (username == null || userrelation == null) {
                Toast.makeText(getActivity(), "No reminder set", Toast.LENGTH_SHORT).show();
            } else {
                //Logic to show in Recycler View.
            }
        }
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 final Dialog dialog=new Dialog(getActivity());
                 dialog.setContentView(R.layout.custom_dialog);
                 c=dialog.findViewById(R.id.editTextPhone);
                 r=dialog.findViewById(R.id.spinner);
                 d=dialog.findViewById(R.id.editTextDate);
                 t=dialog.findViewById(R.id.editTextTime);
                 m=dialog.findViewById(R.id.editTextMessage);
                 set=dialog.findViewById(R.id.button_set);

                    dialog.show();
                    final int y,mm,dd,h,min;
                    Calendar ca=Calendar.getInstance();
                    y=ca.get(Calendar.YEAR);
                    mm=ca.get(Calendar.MONTH);
                    dd=ca.get(Calendar.DAY_OF_MONTH);
                    h=ca.get(Calendar.HOUR_OF_DAY);
                    min=ca.get(Calendar.MINUTE);

                relation=getResources().getStringArray(R.array.relation);
                ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,relation);
                r.setAdapter(adapter);

                d.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        new DatePickerDialog(getActivity(),ld,y,mm,dd).show();
                    }
                });

                t.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        new TimePickerDialog(getActivity(),lt,h,min,false).show();
                    }
                });

                set.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (r.getSelectedItemPosition() > 0) {
                          // get selected item value
                            String itemvalue = String.valueOf(r.getSelectedItem());
                        } else {
                          // set error message on spinner
                            TextView errorTextview = (TextView) r.getSelectedView();
                            errorTextview.setText("Invalid Choice");
                            errorTextview.setTextColor(Color.RED);
                        }

                    }
                });
            }

        });



        return  mylayout;
    }
    DatePickerDialog.OnDateSetListener ld=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
            d.setText(dayOfMonth+"/"+(month+1)+"/"+year);
        }
    };

    TimePickerDialog.OnTimeSetListener lt=new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
            t.setText(hourOfDay+":"+minute);
        }
    };
}