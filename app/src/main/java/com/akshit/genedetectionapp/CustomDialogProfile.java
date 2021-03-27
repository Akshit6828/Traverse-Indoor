package com.akshit.genedetectionapp;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Objects;


public class CustomDialogProfile  extends DialogFragment {
    EditText input_name,input_DOB,input_gender;
    TextView relation;
    int year,month,day;
    String final_name,final_dob,final_gender;
    private CustomDialogProfileListener listener;

    FirebaseDatabase database;
    DatabaseReference reference;

    /*@NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater= getActivity().getLayoutInflater();
        final View dialog_input =inflater.inflate(R.layout.custom_dialog_profile_input,null);
        Bundle mArgs = getArguments();
        final String relation_fetched = mArgs.getString("Relation");

        //Database variables and initializing
        database=FirebaseDatabase.getInstance();
        reference=database.getReference("Users Family Data");


        builder.setView(dialog_input);
        builder.setCancelable(true);

        input_name=dialog_input.findViewById(R.id.id_input_name);
        input_DOB=dialog_input.findViewById(R.id.id_input_dob);
        input_gender=dialog_input.findViewById(R.id.id_input_gender);
        relation=dialog_input.findViewById(R.id.id_relation);
        relation.setText("Relation : "+relation_fetched);
        final Button set_profile=dialog_input.findViewById(R.id.editbutton);
        final TextView cross=dialog_input.findViewById(R.id.close_input);
        cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        input_DOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar ca= Calendar.getInstance();
                final int y=ca.get(Calendar.YEAR);
                final  int m=ca.get(Calendar.MONTH);
                final int d=ca.get(Calendar.DAY_OF_MONTH);
                new DatePickerDialog(getActivity(),listenerdob,year,month,day).show();
            }
        });
        set_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final_name=input_name.getText().toString();
                final_dob=input_DOB.getText().toString();
                final_gender=input_gender.getText().toString();

                //Checking if relation isnt already there in firebase.
               // Write Logic here!

                //Storing Details in Firebase.
                StoringUserFamilyData storingUserFamilyData = new StoringUserFamilyData();
                storingUserFamilyData.setName(final_name);
                storingUserFamilyData.setDob(final_dob);
                storingUserFamilyData.setGender(final_gender);
                storingUserFamilyData.setRelation_with_user(relation_fetched);
                String child_id = reference.push().getKey();
                reference.child(child_id).setValue(storingUserFamilyData);

                switch (relation_fetched) {
                    case "Paternal Grand Father":
                        PedigreeAnalysis.im1.setBackground(null);
                        PedigreeAnalysis.im1.setBackgroundResource(R.drawable.male);
                        break;
                    case "Paternal Grand Mother":
                        PedigreeAnalysis.im2.setBackground(null);
                        PedigreeAnalysis.im2.setBackgroundResource(R.drawable.femalepng);
                        break;
                    case "Maternal Grand Father":
                        PedigreeAnalysis.im3.setBackground(null);
                        PedigreeAnalysis.im3.setBackgroundResource(R.drawable.male);
                        break;
                    case "Maternal Grand Mother":
                        PedigreeAnalysis.im4.setBackground(null);
                        PedigreeAnalysis.im4.setBackgroundResource(R.drawable.femalepng);
                        break;
                    case "Father":
                        PedigreeAnalysis.im5.setBackground(null);
                        PedigreeAnalysis.im5.setBackgroundResource(R.drawable.male);
                        break;
                    case "Mother":
                        PedigreeAnalysis.im6.setBackground(null);
                        PedigreeAnalysis.im6.setBackgroundResource(R.drawable.femalepng);
                        break;
                    case "Self":
                        if(final_gender.equals("Male")) {
                            PedigreeAnalysis.im7.setBackground(null);
                            PedigreeAnalysis.im7.setBackgroundResource(R.drawable.male);
                            break;
                        }
                        else{
                            PedigreeAnalysis.im7.setBackground(null);
                            PedigreeAnalysis.im7.setBackgroundResource(R.drawable.male);
                            break;
                        }

                    case "Child 1 Female":
                        PedigreeAnalysis.im8.setBackground(null);
                        PedigreeAnalysis.im8.setBackgroundResource(R.drawable.femalepng);
                        break;
                    case "Child 2 Male":
                        PedigreeAnalysis.im9.setBackground(null);
                        PedigreeAnalysis.im9.setBackgroundResource(R.drawable.male);
                        break;

                }
                listener.applyTexts(final_name,final_dob,final_gender,relation_fetched);
                dismiss();


            }
        });

            return builder.create();

    }*/

    DatePickerDialog.OnDateSetListener listenerdob = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int y, int m, int dOM) {
            input_DOB.setText(dOM+"-"+(m+1)+"-"+y);
            year=y;
            month=m;
            day=dOM;
        }
    };

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener=(CustomDialogProfileListener) getTargetFragment();
        } catch (ClassCastException e) {
            throw  new ClassCastException((context.toString()+" Please Implement Custom Dialog Profile Listener"));
        }

    }

    public interface CustomDialogProfileListener{
        void applyTexts(String name ,String dob, String gender,String relation);

    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow()
                .setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                        WindowManager.LayoutParams.WRAP_CONTENT);
    }

    /*@Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View myview= super.onCreateView(inflater, container, savedInstanceState);


            final View myview= inflater.inflate(R.layout.custom_dialog_profile_input, container, false);

            // Do all the stuff to initialize your custom view


        Bundle mArgs = getArguments();
        final String relation_fetched = mArgs.getString("Relation");

        //Database variables and initializing
        database=FirebaseDatabase.getInstance();
        reference=database.getReference("Users Family Data");


//        getDialog().getWindow()
//                .setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        input_name=(EditText)myview.findViewById(R.id.id_input_name);
        input_DOB=(EditText)myview.findViewById(R.id.id_input_dob);
        input_gender=(EditText)myview.findViewById(R.id.id_input_gender);
        relation=(TextView)myview.findViewById(R.id.id_relation);
        relation.setText("Relation : "+relation_fetched);
        final Button set_profile=myview.findViewById(R.id.editbutton);
        final TextView cross=myview.findViewById(R.id.close_input);
        cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });
        input_DOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar ca= Calendar.getInstance();
                final int y=ca.get(Calendar.YEAR);
                final  int m=ca.get(Calendar.MONTH);
                final int d=ca.get(Calendar.DAY_OF_MONTH);
                new DatePickerDialog(getActivity(),listenerdob,year,month,day).show();
            }
        });
        set_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final_name=input_name.getText().toString();
                final_dob=input_DOB.getText().toString();
                final_gender=input_gender.getText().toString();

                //Checking if relation isnt already there in firebase.
                // Write Logic here!

                //Storing Details in Firebase.
                StoringUserFamilyData storingUserFamilyData = new StoringUserFamilyData();
                storingUserFamilyData.setName(final_name);
                storingUserFamilyData.setDob(final_dob);
                storingUserFamilyData.setGender(final_gender);
                storingUserFamilyData.setRelation_with_user(relation_fetched);
                String child_id = reference.push().getKey();
                reference.child(child_id).setValue(storingUserFamilyData);
                PedigreeAnalysis objfragment =(PedigreeAnalysis) requireActivity().getSupportFragmentManager().findFragmentById(R.id.id_fragment_pedigreeAnalysis);

                if(objfragment!=null) {

                    switch (relation_fetched) {
                        case "Paternal Grand Father":
                            objfragment.im1.setBackground(null);
                            objfragment.im1.setBackgroundResource(R.drawable.male);
                            break;
                        case "Paternal Grand Mother":
                            objfragment.im2.setBackground(null);
                            objfragment.im2.setBackgroundResource(R.drawable.femalepng);
                            break;
                        case "Maternal Grand Father":
                            objfragment.im3.setBackground(null);
                            objfragment.im3.setBackgroundResource(R.drawable.male);
                            break;
                        case "Maternal Grand Mother":
                            objfragment.im4.setBackground(null);
                            objfragment.im4.setBackgroundResource(R.drawable.femalepng);
                            break;
                        case "Father":
                            objfragment.im5.setBackground(null);
                           objfragment.im5.setBackgroundResource(R.drawable.male);
                            break;
                        case "Mother":
                            objfragment.im6.setBackground(null);
                            objfragment.im6.setBackgroundResource(R.drawable.femalepng);
                            break;
                        case "Self":
                            if (final_gender.equals("Male")) {
                                objfragment.im7.setBackground(null);
                                objfragment.im7.setBackgroundResource(R.drawable.male);
                                break;
                            }
                            else {
                                objfragment.im7.setBackground(null);
                                objfragment.im7.setBackgroundResource(R.drawable.femalepng);
                                break;
                            }
                        case "Child 1 Female":
                                objfragment.im8.setBackground(null);
                                objfragment.im8.setBackgroundResource(R.drawable.femalepng);
                            break;
                        case "Child 2 Male":
                                objfragment.im9.setBackground(null);
                                objfragment.im9.setBackgroundResource(R.drawable.male);
                            break;

                    }


                }
                else
                    Toast.makeText(getActivity(), "obj_Fragment is null", Toast.LENGTH_SHORT).show();
                    listener.applyTexts(final_name, final_dob, final_gender, relation_fetched);

                getDialog().dismiss();
            }

        });
        return myview;

    }*/
}
