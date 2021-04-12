package com.akshit.genedetectionapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class PedigreeAnalysis extends Fragment  {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG="PedigreeAnalysis";
    private String mParam1;
    private String mParam2;
    private ImageButton im1,im2,im3,im4,im5,im6,im7,im8,im9;
    EditText input_name, input_gender,input_DOB;
    Dialog dialog,dialog_input;
    static boolean firsttime;
    TextView relation_user;
    public  String fetched_name,fetched_dob,fetched_relation,fetched_gender;
    int year,month,day;
    int flag=0;
    boolean ans;
    FirebaseDatabase database;
    DatabaseReference reference,reference_user;
    ArrayList<String> stringArrayList;
    ArrayList<Double> default_zero;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    int done=0;

    public PedigreeAnalysis() {
        // mStackLevel=0;

    }

    public static PedigreeAnalysis newInstance(String param1, String param2) {
        PedigreeAnalysis fragment = new PedigreeAnalysis();
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
        firsttime=false;
    }

    @Override
    public void onPause() {
        super.onPause();
        if(dialog_input!=null){
            dialog_input.dismiss();
        }
        else if(dialog!=null){
            dialog.dismiss();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View mylayout= inflater.inflate(R.layout.fragment_pedigree_analysis, container, false);

//............Write your code here for fragment.......................

        im1=mylayout.findViewById(R.id.ib1);
        im2=mylayout.findViewById(R.id.ib2);
        im3=mylayout.findViewById(R.id.ib3);
        im4=mylayout.findViewById(R.id.ib4);
        im5=mylayout.findViewById(R.id.ib5);
        im6=mylayout.findViewById(R.id.ib6);
        im7=mylayout.findViewById(R.id.ib7);
        im8=mylayout.findViewById(R.id.ib8);
        im9=mylayout.findViewById(R.id.ib9);

        database=FirebaseDatabase.getInstance();
        preferences=getActivity().getSharedPreferences("Local_Details", Context.MODE_PRIVATE);//Mode private as with it the file can only be accessed using calling application
        editor=preferences.edit();

        final String name_of_current_user=preferences.getString("username_key","User");
        String child_user=preferences.getString("Child_Key",null);

        reference=database.getReference("Users_Database_1").child(child_user).child(name_of_current_user).child("Users Family Data");
        stringArrayList  = new ArrayList<String>();
        if(!firsttime)
        Toast.makeText(getActivity(), "Please wait a little for Activity to Load..", Toast.LENGTH_SHORT).show();
        firsttime=true;
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot i:dataSnapshot.getChildren()){

                    StoringUserFamilyData obj= i.getValue(StoringUserFamilyData.class);
                    String fetched_result=obj.getRelation_with_user();
                    switch (fetched_result){
                        case "Paternal Grand Father":
                            im1.setBackground(null);
                            im1.setBackgroundResource(R.drawable.male);
                            break;
                        case "Paternal Grand Mother":
                            im2.setBackground(null);
                            im2.setBackgroundResource(R.drawable.femalepng);
                            break;
                        case "Maternal Grand Father":
                            im3.setBackground(null);
                            im3.setBackgroundResource(R.drawable.male);
                            break;
                        case"Maternal Grand Mother":
                            im4.setBackground(null);
                            im4.setBackgroundResource(R.drawable.femalepng);
                            break;
                        case"Father":
                            im5.setBackground(null);
                            im5.setBackgroundResource(R.drawable.male);
                            break;
                        case"Mother":
                            im6.setBackground(null);
                            im6.setBackgroundResource(R.drawable.femalepng);
                            break;
                        case"Self":
                            im7.setBackground(null);
                            im7.setBackgroundResource(R.drawable.male);
                            break;
                        case"Child 1 Female":
                            im8.setBackground(null);
                            im8.setBackgroundResource(R.drawable.femalepng);
                            break;
                        case"Child 2 Male":
                            im9.setBackground(null);
                            im9.setBackgroundResource(R.drawable.male);
                            break;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        im1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag=0;
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot i : dataSnapshot.getChildren()) {
                            StoringUserFamilyData obj = i.getValue(StoringUserFamilyData.class);
                            if (obj != null) {
                                String relation_with_user = obj.getRelation_with_user();
                                if (relation_with_user != null) {
                                    if (relation_with_user.equals("Paternal Grand Father")) {
                                        im1.setBackground(null);
                                        im1.setBackgroundResource(R.drawable.male);
                                        fetched_name = obj.getName();
                                        fetched_dob = obj.getDob();
                                        fetched_gender = obj.getGender();
                                        fetched_relation = relation_with_user;

                                        showCustomDialog(fetched_name, fetched_gender, fetched_relation, fetched_dob);
                                        flag=1;
                                        break;
                                    }
                                }
                                else{
                                    showInputCustomDialog("Paternal Grand Father");
                                }
                            }
                        }
                        if(flag==0){
                            showInputCustomDialog("Paternal Grand Father");
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
        im2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                flag=0;
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot i : dataSnapshot.getChildren()) {
                            StoringUserFamilyData obj = i.getValue(StoringUserFamilyData.class);
                            if (obj != null) {
                                String relation_with_user = obj.getRelation_with_user();//pgf-->pgm
                                if (relation_with_user != null) {
                                    if (relation_with_user.equals("Paternal Grand Mother")) {
                                        im2.setBackground(null);
                                        im2.setBackgroundResource(R.drawable.femalepng);
                                        fetched_name = obj.getName();
                                        flag=1;
                                        fetched_dob = obj.getDob();
                                        fetched_gender = obj.getGender();
                                        fetched_relation = relation_with_user;
                                        showCustomDialog(fetched_name, fetched_gender, fetched_relation, fetched_dob);
                                        break;
                                    }

                                }
                                else{
                                    showInputCustomDialog("Paternal Grand Mother");
                                }
                            }
                        }
                        if(flag==0){
                            showInputCustomDialog("Paternal Grand Mother");
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }

        });
        im3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                flag=0;
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot i : dataSnapshot.getChildren()) {
                            StoringUserFamilyData obj = i.getValue(StoringUserFamilyData.class);
                            if (obj != null) {
                                String relation_with_user = obj.getRelation_with_user();
                                if (relation_with_user != null) {
                                    if (relation_with_user.equals("Maternal Grand Father")) {
                                        im3.setBackground(null);
                                        im3.setBackgroundResource(R.drawable.male);
                                        fetched_name = obj.getName();
                                        flag=1;
                                        fetched_dob = obj.getDob();
                                        fetched_gender = obj.getGender();
                                        fetched_relation = relation_with_user;

                                        showCustomDialog(fetched_name, fetched_gender, fetched_relation, fetched_dob);
                                        break;
                                    }

                                }
                                else{
                                    showInputCustomDialog("Maternal Grand Father");
                                }
                            }
                        }
                        if(flag==0){
                            showInputCustomDialog("Maternal Grand Father");
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
        im4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag=0;
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot i : dataSnapshot.getChildren()) {
                            StoringUserFamilyData obj = i.getValue(StoringUserFamilyData.class);
                            if (obj != null) {
                                String relation_with_user = obj.getRelation_with_user();
                                if (relation_with_user != null) {
                                    if (relation_with_user.equals("Maternal Grand Mother")) {
                                        fetched_name = obj.getName();
                                        fetched_dob = obj.getDob();
                                        fetched_gender = obj.getGender();
                                        fetched_relation = relation_with_user;
                                        im4.setBackground(null);
                                        im4.setBackgroundResource(R.drawable.femalepng);
                                        showCustomDialog(fetched_name, fetched_gender, fetched_relation, fetched_dob);
                                        flag=1;
                                        break;
                                    }
                                }
                                else{
                                    showInputCustomDialog("Maternal Grand Mother");
                                }
                            }
                        }
                        if(flag==0){
                            showInputCustomDialog("Maternal Grand Mother");
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
        im5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag=0;
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot i : dataSnapshot.getChildren()) {
                            StoringUserFamilyData obj = i.getValue(StoringUserFamilyData.class);
                            if (obj != null) {
                                String relation_with_user = obj.getRelation_with_user();
                                if (relation_with_user != null) {
                                    if (relation_with_user.equals("Father")) {
                                        fetched_name = obj.getName();
                                        fetched_dob = obj.getDob();
                                        fetched_gender = obj.getGender();
                                        fetched_relation = relation_with_user;
                                        im5.setBackground(null);
                                        im5.setBackgroundResource(R.drawable.male);

                                        showCustomDialog(fetched_name, fetched_gender, fetched_relation, fetched_dob);
                                        flag=1;
                                        break;
                                    }
                                }
                                else{
                                    showInputCustomDialog("Father");
                                }
                            }
                        }
                        if(flag==0){
                            showInputCustomDialog("Father");
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        im6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag=0;
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot i : dataSnapshot.getChildren()) {
                            StoringUserFamilyData obj = i.getValue(StoringUserFamilyData.class);
                            if (obj != null) {
                                String relation_with_user = obj.getRelation_with_user();
                                if (relation_with_user != null) {
                                    if (relation_with_user.equals("Mother")) {
                                        im6.setBackground(null);
                                        im6.setBackgroundResource(R.drawable.femalepng);
                                        fetched_name = obj.getName();
                                        fetched_dob = obj.getDob();
                                        fetched_gender = obj.getGender();
                                        fetched_relation = relation_with_user;

                                        showCustomDialog(fetched_name, fetched_gender, fetched_relation, fetched_dob);
                                        flag=1;
                                        break;
                                    }
                                }
                                else{
                                    showInputCustomDialog("Mother");
                                }
                            }
                        }
                        if(flag==0){
                            showInputCustomDialog("Mother");
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        im7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag=0;
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot i : dataSnapshot.getChildren()) {
                            StoringUserFamilyData obj = i.getValue(StoringUserFamilyData.class);
                            if (obj != null) {
                                String relation_with_user = obj.getRelation_with_user();

                                if (relation_with_user != null) {
                                    if (relation_with_user.equals("Self")) {
                                        fetched_gender=obj.getGender();
                                                if(fetched_gender.equals("Male")) {
                                                  im7.setBackground(null);
                                                  im7.setBackgroundResource(R.drawable.male);
                                                }
                                                else if(fetched_gender.equals("Female")){
                                                    im7.setBackground(null);
                                                    im7.setBackgroundResource(R.drawable.femalepng);
                                                }
                                        fetched_name = obj.getName();
                                        fetched_dob = obj.getDob();
                                        fetched_gender = obj.getGender();
                                        fetched_relation = relation_with_user;
                                        showCustomDialog(fetched_name, fetched_gender, fetched_relation, fetched_dob);
                                        editor.putString("Gender_KEY",fetched_gender);
                                        editor.commit();
                                        flag=1;
                                        break;
                                    }
                                }
                                else{
                                    showInputCustomDialog("Self");
                                }
                            }
                        }
                        if(flag==0){
                            showInputCustomDialog("Self");
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }


        });
        im8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag=0;
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot i : dataSnapshot.getChildren()) {
                            StoringUserFamilyData obj = i.getValue(StoringUserFamilyData.class);
                            if (obj != null) {
                                String relation_with_user = obj.getRelation_with_user();
                                if (relation_with_user != null) {
                                    if (relation_with_user.equals("Child 1 Female")) {
                                        im8.setBackground(null);
                                        im8.setBackgroundResource(R.drawable.femalepng);
                                        fetched_name = obj.getName();
                                        fetched_dob = obj.getDob();
                                        fetched_gender = obj.getGender();
                                        fetched_relation = relation_with_user;

                                        showCustomDialog(fetched_name, fetched_gender, fetched_relation, fetched_dob);
                                        flag=1;
                                        break;
                                    }
                                }
                                else{
                                    showInputCustomDialog("Child 1 Female");
                                }
                            }
                        }
                        if(flag==0){
                            showInputCustomDialog("Child 1 Female");
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });
        im9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag=0;
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot i : dataSnapshot.getChildren()) {
                            StoringUserFamilyData obj = i.getValue(StoringUserFamilyData.class);
                            if (obj != null) {
                                String relation_with_user = obj.getRelation_with_user();
                                if (relation_with_user != null) {
                                    if (relation_with_user.equals("Child 2 Male")) {
                                        im9.setBackground(null);
                                        im9.setBackgroundResource(R.drawable.male);
                                        fetched_name = obj.getName();
                                        fetched_dob = obj.getDob();
                                        fetched_gender = obj.getGender();
                                        fetched_relation = relation_with_user;

                                        showCustomDialog(fetched_name, fetched_gender, fetched_relation, fetched_dob);
                                        flag=1;
                                        break;
                                    }
                                }
                                else{
                                    showInputCustomDialog("Child 2 Male");
                                }
                            }
                        }
                        if(flag==0){
                            showInputCustomDialog("Child 2 Male");
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });


        return mylayout;

    }

    //*********************INPUT DIALOG CODE GOES HERE***************************************


    public void showInputCustomDialog(final String relation) {

        //**********OPENING DIALOG USING CUSTOM_DIALOG_CLASS ----------

        /*DialogFragment show_input_dialog_obj = new CustomDialogProfile();
        Bundle args = new Bundle();
        args.putString("Relation", relation);
        show_input_dialog_obj.setArguments(args);
        show_input_dialog_obj.show(getActivity().getSupportFragmentManager(),"Opening Input Dialog");*/
        if(dialog!=null){
            dialog.dismiss();
            dialog=null;
        }
        dialog_input= new Dialog(getActivity());
        //Setting the content view for input dialog.
        dialog_input.setContentView(R.layout.custom_dialog_profile_input);
        dialog_input.setCanceledOnTouchOutside(true);

        //Adjusting the Dialog width and height...
        dialog_input.getWindow()
                .setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                        WindowManager.LayoutParams.WRAP_CONTENT);
        //Fetching the widgets of CustomInputDialog..
        final EditText input_name=dialog_input.findViewById(R.id.id_input_name);
        final EditText input_DOB=dialog_input.findViewById(R.id.id_input_dob);
        final EditText input_gender=dialog_input.findViewById(R.id.id_input_gender);
        final Button set_profile=dialog_input.findViewById(R.id.input_editbutton);
        final TextView cross=dialog_input.findViewById(R.id.close_input);
        TextView relation_user= dialog_input.findViewById(R.id.id_input_relation);

        fetched_relation=relation;
        relation_user.setText(fetched_relation);
        if(fetched_relation.equals("Paternal Grand Father")||fetched_relation.equals("Maternal Grand Father")||fetched_relation.equals("Father")||fetched_relation.equals("Child 2 Male")) {
            //input_gender.setBackground(null);
            input_gender.setInputType(0);
            input_gender.setEnabled(false);
            input_gender.setText("Male");
        }
        else if(fetched_relation.equals("Paternal Grand Mother")||fetched_relation.equals("Maternal Grand Mother")||fetched_relation.equals("Mother")) {
           // input_gender.setBackground(null);
            input_gender.setInputType(0);
            input_gender.setEnabled(false);
            input_gender.setText("Female");
        }

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
                new DatePickerDialog(getActivity(),listenerdob,y,m,d).show();
            }
        });
        set_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String final_name=input_name.getText().toString();
                String final_dob=input_DOB.getText().toString();
                String final_gender=input_gender.getText().toString();
                //Storing Details in Firebase.
                StoringUserFamilyData storingUserFamilyData = new StoringUserFamilyData();
                storingUserFamilyData.setName(final_name);
                storingUserFamilyData.setDob(final_dob);
                storingUserFamilyData.setGender(final_gender);
                storingUserFamilyData.setRelation_with_user(fetched_relation);
                //String child_id = reference.push().getKey();
                //reference.child(child_id).setValue(storingUserFamilyData);
                reference.child(relation).setValue(storingUserFamilyData);

                switch (fetched_relation) {
                    case "Paternal Grand Father":
                        im1.setBackground(null);
                        im1.setBackgroundResource(R.drawable.male);
                        break;
                    case "Paternal Grand Mother":
                        im2.setBackground(null);
                        im2.setBackgroundResource(R.drawable.femalepng);
                        break;
                    case "Maternal Grand Father":
                        im3.setBackground(null);
                        im3.setBackgroundResource(R.drawable.male);
                        break;
                    case "Maternal Grand Mother":
                        im4.setBackground(null);
                        im4.setBackgroundResource(R.drawable.femalepng);
                        break;
                    case "Father":
                        im5.setBackground(null);
                        im5.setBackgroundResource(R.drawable.male);
                        break;
                    case "Mother":
                        im6.setBackground(null);
                        im6.setBackgroundResource(R.drawable.femalepng);
                        break;
                    case "Self":
                        if (final_gender.equals("Male") ||final_gender.equals("male")) {
                            im7.setBackground(null);
                            im7.setBackgroundResource(R.drawable.male);
                            break;
                        }
                        else {
                            im7.setBackground(null);
                            im7.setBackgroundResource(R.drawable.femalepng);
                            break;
                        }
                    case "Child 1 Female":
                        im8.setBackground(null);
                        im8.setBackgroundResource(R.drawable.femalepng);
                        break;
                    case "Child 2 Male":
                        im9.setBackground(null);
                        im9.setBackgroundResource(R.drawable.male);
                        break;

                }

                dialog_input.dismiss();
            }

        });
        dialog_input.show();

    }

    //******************CUSTOM DIALOG PROFILE TO BE SHOWN*****************************

    private void showCustomDialog(final String name_of_current_profile, String gender_user, final String relation_with_user, String dob) {
        if(dialog_input!=null){
            dialog_input.dismiss();
        }
        if(dialog!=null){
            dialog.dismiss();
            dialog=null;
        }
        dialog= new Dialog(getActivity());
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(R.layout.cutom_dialog_profile);
        LinearLayout l1,l2,l3;

        //Fetching widgets Id from dialog.
        final TextView name= dialog.findViewById(R.id.idname_custom_dialog);
        final ImageView userimage =dialog.findViewById(R.id.iduserimage_custom_dialog);
        final TextView gender= dialog.findViewById(R.id.idgender_custom_dialog);
        final TextView relation= dialog.findViewById(R.id.idrelation_custom_dialog);
        final TextView dobget= dialog.findViewById(R.id.iddob_custom_dialog);
        final TextView cross=dialog.findViewById(R.id.close);
       // final Button edit_profile=dialog.findViewById(R.id.editbuttonoutput);
        final Button close_profile_output=dialog.findViewById(R.id.closebuttondialog);

        l1=dialog.findViewById(R.id.setReminder);
        l2=dialog.findViewById(R.id.match_symptoms);
        l3=dialog.findViewById(R.id.preventive_measures);
        TextView tvreminder=dialog.findViewById(R.id.tvsetReminder);
        TextView tvmatch_symptoms=dialog.findViewById(R.id.tvmatch_symptoms);
        TextView tvpreventive_measures=dialog.findViewById(R.id.tvpreventive_measures);

        if(gender_user!=null){
            Uri imgUri;
            if(gender_user.equals("Female")||gender_user.equals("female")){
                imgUri = Uri.parse("android.resource://com.akshit.genedetectionapp/" + R.drawable.mother);
            }
            else{
                imgUri = Uri.parse("android.resource://com.akshit.genedetectionapp/" + R.drawable.boyfather);
            }
            userimage.setImageURI(imgUri);

        }
        name.setText(name_of_current_profile);
        dobget.setText("DOB : "+dob);
        gender.setText("Gender : "+gender_user);
        relation.setText("Relation : "+relation_with_user);

        //Edit button listener not working..Need to write code to Bring again the inputprofile button

        close_profile_output.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        close_profile_output.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                dialog.dismiss();
                return false;
            }
        });

        //Cross button listener
        cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        //Linear layout listeners and their text views listeners.
        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Code for Setting reminder fragment and dismissing the dialog.
                String backstackname="PedigreeAnalyis";
                fragment_set_reminder obj = new fragment_set_reminder ();
                Bundle args = new Bundle();
                args.putString("UserRelation", relation_with_user);
                args.putString("UserName", name_of_current_profile);
                obj.setArguments(args);
                getActivity().getSupportFragmentManager().
                        beginTransaction().
                        replace(R.id.fragment_container,obj).addToBackStack(backstackname).commit();
                dialog.dismiss();
            }
        });
        tvreminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String backstackname="PedigreeAnalyis";
                fragment_set_reminder obj = new fragment_set_reminder ();
                Bundle args = new Bundle();
                args.putString("UserRelation", relation_with_user);
                args.putString("UserName", name_of_current_profile);
                obj.setArguments(args);
                getActivity().getSupportFragmentManager().
                        beginTransaction().
                        replace(R.id.fragment_container,obj).
                        addToBackStack(backstackname).commit();
                dialog.dismiss();
            }
        });
        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Code for Symptoms match fragment and dismissing the dialog.
                String backstackname="PedigreeAnalyis";
                Match_Symptoms obj= new Match_Symptoms();
                Bundle args = new Bundle();
                args.putString("UserRelation", relation_with_user);
                args.putString("UserName", name_of_current_profile);
                obj.setArguments(args);

                getActivity().getSupportFragmentManager().
                        beginTransaction().
                        replace(R.id.fragment_container,obj).
                        addToBackStack(backstackname).commit();
                dialog.dismiss();
            }
        });
        tvmatch_symptoms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String backstackname="PedigreeAnalyis";
                Match_Symptoms obj= new Match_Symptoms();
                Bundle args = new Bundle();
                args.putString("UserRelation", relation_with_user);
                args.putString("UserName", name_of_current_profile);

                obj.setArguments(args);

                getActivity().getSupportFragmentManager().
                        beginTransaction().
                        replace(R.id.fragment_container,obj).
                        addToBackStack(backstackname).commit();
                dialog.dismiss();


            }
        });
        l3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(relation_with_user!=null){
                    preventiveMeasuresCheck(relation_with_user,name_of_current_profile);
                }
                dialog.dismiss();
            }
        });
        tvpreventive_measures.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(relation_with_user!=null){
                    preventiveMeasuresCheck(relation_with_user,name_of_current_profile);
                }
                dialog.dismiss();
            }
        });
        dialog.show();

    }

    DatePickerDialog.OnDateSetListener listenerdob = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int y, int m, int dOM) {
            input_DOB.setText(dOM+"-"+m+"-"+y);
            year=y;
            month=m;
            day=dOM;
        }
    };


    public void preventiveMeasuresCheck(String relation_with_user,String name_of_current_profile){
        String backstackname="PedigreeAnalyis";
        preventive_measures obj = new preventive_measures();
        Bundle args = new Bundle();
        args.putString("UserRelation", relation_with_user);
        args.putString("UserName", name_of_current_profile);
        switch (relation_with_user){
            case "Paternal Grand Father":
                if(Login.percentage_gf1!=null&&Login.percentage_gf1.size()>0) {
                    args.putInt("SentNo_Key", 11);
                    obj.setArguments(args);
                    getActivity().getSupportFragmentManager().
                            beginTransaction().
                            replace(R.id.fragment_container, obj).addToBackStack(backstackname).commit();
                }
                else{
                    Toast.makeText(getActivity(), "Please fill the symptoms first..", Toast.LENGTH_SHORT).show();
                }
                break;
            case "Paternal Grand Mother":
                if(Login.percentage_gm1!=null&&Login.percentage_gm1.size()>0) {
                    args.putInt("SentNo_Key", 12);
                    obj.setArguments(args);
                    getActivity().getSupportFragmentManager().
                            beginTransaction().
                            replace(R.id.fragment_container, obj).addToBackStack(backstackname).commit();
                }
                else{
                    Toast.makeText(getActivity(), "Please match the symptoms first...", Toast.LENGTH_SHORT).show();
                }
                break;
            case "Maternal Grand Father":
                if(Login.percentage_gf2!=null&&Login.percentage_gf2.size()>0) {
                    args.putInt("SentNo_Key", 13);
                    obj.setArguments(args);
                    getActivity().getSupportFragmentManager().
                            beginTransaction().
                            replace(R.id.fragment_container, obj).addToBackStack(backstackname).commit();
                }
                else{
                    Toast.makeText(getActivity(), "Please Match the Symptoms first...", Toast.LENGTH_SHORT).show();
                }
                break;


            case"Maternal Grand Mother":
                if(Login.percentage_gm2!=null&&Login.percentage_gm2.size()>0) {
                    args.putInt("SentNo_Key", 14);
                    obj.setArguments(args);
                    getActivity().getSupportFragmentManager().
                            beginTransaction().
                            replace(R.id.fragment_container, obj).addToBackStack(backstackname).commit();
                }
                else{
                    Toast.makeText(getActivity(), "Please Match the Symptoms first...", Toast.LENGTH_SHORT).show();
                }
                break;
            case"Father":
                if(Login.percentage_dad!=null&&Login.percentage_dad.size()>0) {
                    args.putInt("SentNo_Key", 15);
                    obj.setArguments(args);
                    getActivity().getSupportFragmentManager().
                            beginTransaction().
                            replace(R.id.fragment_container, obj).addToBackStack(backstackname).commit();
                }
                else{
                    Toast.makeText(getActivity(), "Please match symptoms first...", Toast.LENGTH_SHORT).show();
                }
                break;
            case"Mother":
                if(Login.percentage_mom!=null&&Login.percentage_mom.size()>0) {
                    args.putInt("SentNo_Key", 16);
                    obj.setArguments(args);
                    getActivity().getSupportFragmentManager().
                            beginTransaction().
                            replace(R.id.fragment_container, obj).addToBackStack(backstackname).commit();
                }
                else{
                    Toast.makeText(getActivity(), "Please Match Symptoms first...", Toast.LENGTH_SHORT).show();
                }
                break;
            case"Self":
                if(Login.percentage_me!=null&&Login.percentage_me.size()>0) {
                    args.putInt("SentNo_Key", 17);
                    obj.setArguments(args);
                    getActivity().getSupportFragmentManager().
                            beginTransaction().
                            replace(R.id.fragment_container, obj).addToBackStack(backstackname).commit();
                }
                else
                    Toast.makeText(getActivity(), "Please Match Symptoms first...", Toast.LENGTH_SHORT).show();
                break;
            case"Child 1 Female":
                if(Login.percentage_child1female!=null&&Login.percentage_child1female.size()>0) {
                    args.putInt("SentNo_Key", 18);
                    obj.setArguments(args);
                    getActivity().getSupportFragmentManager().
                            beginTransaction().
                            replace(R.id.fragment_container, obj).addToBackStack(backstackname).commit();
                }
                else
                    Toast.makeText(getActivity(), "Please Match Symptoms first...", Toast.LENGTH_SHORT).show();
                break;
            case"Child 2 Male":
                if(Login.percentage_child2male!=null&&Login.percentage_child2male.size()>0) {
                    args.putInt("SentNo_Key", 19);
                    obj.setArguments(args);
                    getActivity().getSupportFragmentManager().
                            beginTransaction().
                            replace(R.id.fragment_container, obj).addToBackStack(backstackname).commit();
                }
                else
                    Toast.makeText(getActivity(), "Please Match Symptoms first...", Toast.LENGTH_SHORT).show();
                break;

        }
    }
}

