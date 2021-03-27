package com.akshit.genedetectionapp;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;


public class CustomDialogProfile  extends DialogFragment {
    EditText input_name,input_DOB,input_gender;
    TextView relation;
    int year,month,day;
    String final_name,final_dob,final_gender;
    private CustomDialogProfileListener listener;

    FirebaseDatabase database;
    DatabaseReference reference;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Bundle mArgs = getArguments();
        final String relation_fetched = mArgs.getString("Relation");

        //database variables and initiallization
        database=FirebaseDatabase.getInstance();
        reference=database.getReference("Users Family Data");

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater= getActivity().getLayoutInflater();
        final View dialog_input =inflater.inflate(R.layout.custom_dialog_profile_input,null);
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


                 if(relation_fetched.equals("Paternal Grand Father")){
                    PedigreeAnalysis.im1.setBackground(null);
                    PedigreeAnalysis.im1.setBackgroundResource(R.drawable.male);
                }
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
                        }
                        else{
                            PedigreeAnalysis.im7.setBackground(null);
                            PedigreeAnalysis.im7.setBackgroundResource(R.drawable.male);
                        }
                        break;
                    case "Child 1":
                        PedigreeAnalysis.im8.setBackground(null);
                        PedigreeAnalysis.im8.setBackgroundResource(R.drawable.male);
                        break;
                    case "Child 2":
                        PedigreeAnalysis.im8.setBackground(null);
                        PedigreeAnalysis.im8.setBackgroundResource(R.drawable.femalepng);
                        break;

                }
                /*PedigreeAnalysis.fetched_name=final_name;
                PedigreeAnalysis.fetched_dob=final_dob;
                PedigreeAnalysis.fetched_gender=final_gender;
                PedigreeAnalysis.fetched_relation=relation_fetched;*/

                listener.applyTexts(final_name,final_dob,final_gender,relation_fetched);
                dismiss();


            }
        });

            return builder.create();

    }

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
        context= getActivity();
       try {
            listener=(CustomDialogProfileListener) context;
        } catch (ClassCastException e) {
            throw  new ClassCastException((context.toString()+" Please Implement CustomDialogProfile Listener"));
        }

    }

    public interface CustomDialogProfileListener{
        void applyTexts(String name ,String dob, String gender,String relation);

    }

   /*@Nullable
   @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

       View myview= inflater.inflate(R.layout.custom_dialog_profile_input, container, false);

        // Do all the stuff to initialize your custom view


        Bundle mArgs = getArguments();
        final String relation_fetched = mArgs.getString("Relation");
        Toast.makeText(getActivity(), "OnCreateView", Toast.LENGTH_SHORT).show();
        //AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        //  LayoutInflater inflater= getActivity().getLayoutInflater();
        //  final View dialog_input =inflater.inflate(R.layout.custom_dialog_profile_input,null);
        dialog_input = new Dialog(getActivity());
        dialog_input.setContentView(R.layout.custom_dialog_profile_input);
        dialog_input.setCancelable(false);
        input_name=dialog_input.findViewById(R.id.id_input_name);
        input_DOB=dialog_input.findViewById(R.id.id_input_dob);
        input_gender=dialog_input.findViewById(R.id.id_input_gender);
        gender=dialog_input.findViewById(R.id.id_relation);
        gender.setText(relation_fetched);
        final Button set_profile=dialog_input.findViewById(R.id.editbutton);
        final TextView cross=dialog_input.findViewById(R.id.close_input);
        cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog_input.dismiss();
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
                listener.applyTexts(final_name,final_dob,final_gender,relation_fetched);
            }
        });
        return myview;
    }*/
}
