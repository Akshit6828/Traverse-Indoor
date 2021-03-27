package com.akshit.genedetectionapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;

public class PedigreeAnalysis extends Fragment implements CustomDialogProfile.CustomDialogProfileListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG="PedigreeAnalysis";
    int mStackLevel;
    private String mParam1;
    private String mParam2;
    private ImageButton im1,im2,im3,im4,im5,im6,im7,im8,im9;
    SharedPreferences preferences;
    private FirebaseAuth mAuth;
    EditText input_name, input_gender,input_DOB;
    Dialog dialog,dialog_input;
    TextView relation_user;
    //    String name,dob,gender;
    public  String fetched_name,fetched_dob,fetched_relation,fetched_gender;
    int year,month,day;
    int flag=0;
    boolean ans;
    FirebaseDatabase database;
    DatabaseReference reference,relation_Reference;
    ArrayList<String> stringArrayList;
    boolean i1,i2,i3,i4,i5,i6,i7,i8,i9;
    DatabaseReference ref1,ref2,ref3,ref4,ref5,ref6,ref7,ref8,ref9;
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
//        i1=true;
//        i2=true;
//        i3=true;
//        i4=true;
//        i5=true;
//        i6=true;
//        i7=true;
//        i9=true;
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
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
        //PedigreeAnalysis fragment= new PedigreeAnalysis();

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

        reference=database.getReference("Users Family Data");

        stringArrayList  = new ArrayList<String>();


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot i:dataSnapshot.getChildren()){

                    StoringUserFamilyData obj= i.getValue(StoringUserFamilyData.class);
                    String fetched_result=obj.getRelation_with_user();
                    Toast.makeText(getActivity(), "=>"+fetched_result, Toast.LENGTH_SHORT).show();
                    stringArrayList.add(fetched_result);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Toast.makeText(getActivity(), "Outer Toast", Toast.LENGTH_SHORT).show();

        for(int index=0;index<stringArrayList.size();index++){
            String  s=stringArrayList.get(index);
            Toast.makeText(getActivity(), "--->"+s, Toast.LENGTH_LONG).show();

            switch (s) {
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
                    im7.setBackgroundResource(R.drawable.femalepng);
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
        im1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ans=false;
                //boolean check=checkReferencePresentInFirebase("Paternal Grand Father");
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



                /*if(check) {

                    im1.setBackground(null);
                    im1.setBackgroundResource(R.drawable.male);

                   reference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for (DataSnapshot i : dataSnapshot.getChildren()) {
                                    StoringUserFamilyData obj = i.getValue(StoringUserFamilyData.class);
                                    String relation_with_user = obj.getRelation_with_user();
                                  //  Toast.makeText(getActivity(), "relatoion fetched is " + relation_with_user, Toast.LENGTH_SHORT).show();
                                    if (relation_with_user != null) {
                                       // Toast.makeText(getActivity(), "RLT = NOt Null " + relation_with_user, Toast.LENGTH_SHORT).show();
                                        if (relation_with_user.equals("Paternal Grand Father")) {
                                            fetched_name = obj.getName();
                                            fetched_dob = obj.getDob();
                                            fetched_gender = obj.getGender();
                                            fetched_relation = relation_with_user;
                                            showCustomDialog(fetched_name, fetched_gender, fetched_relation, fetched_dob);
                                            break;
                                        }
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                Toast.makeText(getActivity(), "onCancelled Called", Toast.LENGTH_SHORT).show();

                            }
                        });


                }
                else {
                    showInputCustomDialog("Paternal Grand Father");
                }*/
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
                                String relation_with_user = obj.getRelation_with_user();//pgf-->pgm
                                if (relation_with_user != null) {
                                    if (relation_with_user.equals("Maternal Grand Father")) {
                                        im3.setBackground(null);
                                        im3.setBackgroundResource(R.drawable.femalepng);
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

                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot i : dataSnapshot.getChildren()) {
                            StoringUserFamilyData obj = i.getValue(StoringUserFamilyData.class);
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
                                    break;
                                } else {
                                    //Fetch DataBase Details:
                                    showInputCustomDialog("Maternal Grand Mother");
                                }
                            }
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

                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot i : dataSnapshot.getChildren()) {
                            StoringUserFamilyData obj = i.getValue(StoringUserFamilyData.class);
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
                                    break;
                                }
                            }
                            else {
                                //Fetch DataBase Details:
                                showInputCustomDialog("Father");
                            }
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


                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot i : dataSnapshot.getChildren()) {
                            StoringUserFamilyData obj = i.getValue(StoringUserFamilyData.class);
                            String relation_with_user = obj.getRelation_with_user();
                            if (relation_with_user != null) {
                                if (relation_with_user.equals("Mother")) {
                                    fetched_name = obj.getName();
                                    fetched_dob = obj.getDob();
                                    fetched_gender = obj.getGender();
                                    fetched_relation = relation_with_user;
                                    im6.setBackground(null);
                                    im6.setBackgroundResource(R.drawable.femalepng);
                                    showCustomDialog(fetched_name, fetched_gender, fetched_relation, fetched_dob);
                                    break;
                                }
                            }
                            else {
                                //Fetch DataBase Details:
                                showInputCustomDialog("Mother");
                            }
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
                            String relation_with_user = obj.getRelation_with_user();
                            if (relation_with_user != null) {
                                if (relation_with_user.equals("Self")) {
                                    fetched_name = obj.getName();
                                    fetched_dob = obj.getDob();
                                    fetched_gender = obj.getGender();
                                    fetched_relation = relation_with_user;
                                    im7.setBackground(null);
                                    flag=1;
                                    im7.setBackgroundResource(R.drawable.male);
                                    showCustomDialog(fetched_name, fetched_gender, fetched_relation, fetched_dob);

                                    break;
                                }
                            }
                            else {
                                //Fetch DataBase Details:
                                showInputCustomDialog("Self");
                            }
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
                            String relation_with_user = obj.getRelation_with_user();
                            if (relation_with_user != null) {
                                if (relation_with_user.equals("Child 1 Female")) {
                                    fetched_name = obj.getName();
                                    fetched_dob = obj.getDob();
                                    fetched_gender = obj.getGender();
                                    fetched_relation = relation_with_user;
                                    im8.setBackground(null);
                                    im8.setBackgroundResource(R.drawable.femalepng);
                                    flag=1;
                                    showCustomDialog(fetched_name, fetched_gender, fetched_relation, fetched_dob);
                                    break;
                                }
                            }
                            else {
                                //Fetch DataBase Details:
                                showInputCustomDialog("Child 1 Female");
                            }
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

                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot i : dataSnapshot.getChildren()) {
                            StoringUserFamilyData obj = i.getValue(StoringUserFamilyData.class);
                            String relation_with_user = obj.getRelation_with_user();
                            if (relation_with_user != null) {
                                if (relation_with_user.equals("Child 2 Male")) {
                                    fetched_name = obj.getName();
                                    fetched_dob = obj.getDob();
                                    fetched_gender = obj.getGender();
                                    fetched_relation = relation_with_user;
                                    im9.setBackground(null);
                                    im9.setBackgroundResource(R.drawable.male);
                                    showCustomDialog(fetched_name, fetched_gender, fetched_relation, fetched_dob);
                                    break;
                                }
                            }
                            else {
                                //Fetch DataBase Details:
                                showInputCustomDialog("Child 2 Male");
                            }
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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // dialog.dismiss();
        //  dialog_input.dismiss();
    }


    public void showInputCustomDialog(final String relation) {

        /*
        dialog_input.setContentView(R.layout.custom_dialog_profile_input);
        dialog_input.setCancelable(false);
        input_name=dialog_input.findViewById(R.id.id_input_name);
        input_DOB=dialog_input.findViewById(R.id.id_input_dob);
        input_gender=dialog_input.findViewById(R.id.id_input_gender);
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
                new DatePickerDialog(getActivity(),listener,year,month,day).show();
            }
        });
        set_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                 final_name=input_name.getText().toString();
               final_dob=input_DOB.getText().toString();
             final_gender=input_gender.getText().toString();
             flag[0] =1;
             data.add(final_name);
             data.add(final_dob);
             data.add(final_gender);

            }
        });
        dialog_input.show();*/
     /*   FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        Fragment prev = getActivity().getSupportFragmentManager().findFragmentById(R.id.idinputFragment);
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        Bundle args = new Bundle();
        args.putString("Relation", relation);
        DialogFragment dialogFragment = new CustomDialogProfile();
        dialogFragment.setArguments(args);
        dialogFragment.show(getActivity().getSupportFragmentManager(),"Dialog");*/

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

        //************ IMPORTANT TO WRITE.....................................................
        dialog_input.setContentView(R.layout.custom_dialog_profile_input);

        database=FirebaseDatabase.getInstance();
        reference=database.getReference("Users Family Data");

        dialog_input.getWindow()
                .setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                        WindowManager.LayoutParams.WRAP_CONTENT);


        final EditText input_name=dialog_input.findViewById(R.id.id_input_name);
        final EditText input_DOB=dialog_input.findViewById(R.id.id_input_dob);
        final EditText input_gender=dialog_input.findViewById(R.id.id_input_gender);
        final Button set_profile=dialog_input.findViewById(R.id.input_editbutton);
        final TextView cross=dialog_input.findViewById(R.id.close_input);
        TextView relation_user= dialog_input.findViewById(R.id.id_input_relation);


        fetched_relation=relation;
        relation_user.setText(fetched_relation);

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

                //Checking if relation isnt already there in firebase.
                // Write Logic here!
                reference=database.getReference("Users Family Data");
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
                        i1=true;
                        break;
                    case "Paternal Grand Mother":
                        im2.setBackground(null);
                        im2.setBackgroundResource(R.drawable.femalepng);
                        i2=true;
                        break;
                    case "Maternal Grand Father":
                        im3.setBackground(null);
                        im3.setBackgroundResource(R.drawable.male);
                        i3=true;
                        break;
                    case "Maternal Grand Mother":
                        im4.setBackground(null);
                        im4.setBackgroundResource(R.drawable.femalepng);
                        i4=true;
                        break;
                    case "Father":
                        im5.setBackground(null);
                        im5.setBackgroundResource(R.drawable.male);
                        i5=true;
                        break;
                    case "Mother":
                        im6.setBackground(null);
                        im6.setBackgroundResource(R.drawable.femalepng);
                        i6=true;
                        break;
                    case "Self":
                        if (final_gender.equals("Male")) {
                            im7.setBackground(null);
                            im7.setBackgroundResource(R.drawable.male);
                            i7=true;
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

   /* @Override
    public void onPause() {
        super.onPause();
        dialog.dismiss();
        dialog_input.dismiss();
    }*/


    private void showCustomDialog(String name_of_current_profile, String gender_user, String relation_with_user, String dob) {
        if(dialog_input!=null){
            dialog_input.dismiss();
        }
// 1-->2-->3-->...9
        //Making sure about other opened dialog.

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
        final Button edit_profile=dialog.findViewById(R.id.editbuttonoutput);

        l1=dialog.findViewById(R.id.setReminder);
        l2=dialog.findViewById(R.id.match_symptoms);
        l3=dialog.findViewById(R.id.preventive_measures);

        if(gender_user!=null){
            Uri imgUri;
            if(gender_user.equals("Female")){
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
        edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //Cross button listener
        cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        //Linear layout listeners.
        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Code for Setting reminder fragment and dismissing the dialog.
            }
        });
        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Code for Symptoms match fragment and dismissing the dialog.
            }
        });
        l3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Code for Preventive Measures for fragment and dismissing the dialog.
                dialog.dismiss();
            }
        });

        dialog.show();

    }

/*
     // TREE VIEW CODE USING NODE IMPLEMENTATION STARTS............

      TreeView treeView = mylayout.findViewById(R.id.idTreeView);

        BaseTreeAdapter<Viewholder> adapter = new BaseTreeAdapter<Viewholder>(getActivity(),R.layout.tree_view_node) {
            @NonNull
            @Override
            public Viewholder onCreateViewHolder(View view) {
                return new Viewholder(view);
            }

            @Override
            public void onBindViewHolder(Viewholder viewHolder, Object data, int position) {
                viewHolder.textView.setText(data.toString());
                Uri imgUri=Uri.parse("android.resource://com.akshit.genedetectionapp/"+R.drawable.ic_add);
                viewHolder.imageView.setImageURI(null);
                viewHolder.imageView.setImageURI(imgUri);
                //viewHolder.imageView.setImageURI();//------ Convert image to URI ......
            }
        };
        treeView.setAdapter(adapter);
        TreeNode root= new TreeNode("Grand\nAncestor");
        TreeNode pgf= new TreeNode(" Paternal\nGrand Father");
        TreeNode pgm= new TreeNode(" Paternal\nGrand Mother");
        TreeNode mgf= new TreeNode(" Maternal\nGrand Father");
        TreeNode mgm= new TreeNode("Maternal\n Grand Mother");
        TreeNode father= new TreeNode("Father");
        TreeNode mother= new TreeNode("Mother");
        TreeNode me=new TreeNode("You");
        TreeNode child1boy=new TreeNode("Child male");
        TreeNode child2girl=new TreeNode("Child female");
        root.addChildren(pgf,pgm,mgf,mgm);
        //pgf.addChild(father);
        pgm.addChild(father);
        mgm.addChild(mother);
        father.addChild(me);

        me.addChildren(child1boy,child2girl);

        //root.addChild(child2girl);
        //root.setParent(parent1);
        //root.setParent(parent2);
        //root.setParent(parent3);
        //parent1.setParent(parent4);
        //parent2.setParent(parent5);
        //parent2.setParent(parent6);

        adapter.setRootNode(root);
       // adapter.setRootNode(me);


       //TREE VIEW CODE USING NODE XML ENDS............
*/
       /*graphView = mylayout.findViewById(R.id.idGraphView);
        final Uri imageuri = Uri.parse("android.resource://com.akshit.genedetectionapp/drawable/ic_add");
        final Graph graph = new Graph();
        final Node node1 = new Node("You");
        final Node node2 = new Node("Father");
        final Node node3 = new Node("Mother");
       // graph.addEdge(node1, node2);
        //graph.addEdge(node1, node3);
       // graph.addNode(node2);
        //graph.addNode(node3);
     //   graph.hasPredecessor(node1);
        graph.addNodes(node1,node2,node3);
        graph.addEdge(node1,node2);
        graph.addEdge(node1,node3);
        /*graph.predecessorsOf(node2);
        graph.predecessorsOf(node3);

       /* graphAdapter= new GraphAdapter<GraphView.ViewHolder>(graph) {
            @NonNull
            @NotNull
            @Override
            public GraphView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup viewGroup, int i) {
                final View view = LayoutInflater.from(getActivity()).inflate(R.layout.tree_view_node, viewGroup, false);
                return new Viewholder(view);

            }

            @Override
            public void onBindViewHolder(@NonNull @NotNull GraphView.ViewHolder viewHolder, @NotNull Object o, int i) {
                ((Viewholder) viewHolder).textView.setText(o.toString());
            //    ((Viewholder) viewHolder).imageView.setImageURI(imageuri);

            }

            @Override
            public int getCount() {
                return 0;
            }

            @Override
            public Object getItem(int i) {
                return null;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }
        };
        graphView.setAdapter(graphAdapter);
        final BuchheimWalkerConfiguration configuration = new BuchheimWalkerConfiguration.Builder()
                .setSiblingSeparation(100)
                .setLevelSeparation(300)
                .setSubtreeSeparation(300)
                .setOrientation(BuchheimWalkerConfiguration.ORIENTATION_TOP_BOTTOM)
                .build();
        graphView.setLayout(new BuchheimWalkerAlgorithm(configuration));*/

    //Image button code goes here!...........
    //Connecting UI and Backend!


    DatePickerDialog.OnDateSetListener listenerdob = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int y, int m, int dOM) {
            input_DOB.setText(dOM+"-"+m+"-"+y);
            year=y;
            month=m;
            day=dOM;
        }
    };



    //---Checking Database Value Existence in Firebase........
    boolean checkReferencePresentInFirebase(final String relation) {

        /*referenceinner.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue()!=null) {
                    for (DataSnapshot i : dataSnapshot.getChildren()) {
                        StoringUserFamilyData obj = i.getValue(StoringUserFamilyData.class);
                        String relation_with_user = obj.getRelation_with_user();
                        if(relation_with_user!=null) {
                            if (relation_with_user.equals(relation)) {
                                Toast.makeText(getActivity(), "rel with user is true and " + relation_with_user + " is the reltation found", Toast.LENGTH_SHORT).show();
                                ans =true;
                                break;
                            }
                            else{
                                Toast.makeText(getActivity(), "rel not equall"+relation_with_user, Toast.LENGTH_SHORT).show();
                            }

                        }
                        else
                            ans=false;
                    }
                }
                else{
                    ans=false;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

       /* reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                StoringUserFamilyData obj= new StoringUserFamilyData();
                if (dataSnapshot.getValue() != null) {
                    dataSnapshot.getValue(obj.getRelation_with_user()).toString();
                    if(dataSnapshot.getValue().toString().equals(relation)){
                        Toast.makeText(getActivity(), "Value fetched is "+dataSnapshot.getValue().toString(), Toast.LENGTH_SHORT).show();
                        ans[0] = true;
                    }
                    else{
                        Toast.makeText(getActivity(), "Value isnt fetched which isnt equall"+dataSnapshot.getValue().toString(), Toast.LENGTH_SHORT).show();
                    }



                } else {

                    ans[0] = false;
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/
        /*referenceinner.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue()!=null) {
                    for (DataSnapshot i : dataSnapshot.getChildren()) {
                        StoringUserFamilyData obj = i.getValue(StoringUserFamilyData.class);
                        String relation_with_user = obj.getRelation_with_user();
                        if(relation_with_user!=null) {
                            if (relation_with_user.equals(relation)) {
                                Toast.makeText(getActivity(), "rel with user is true and " + relation_with_user + " is the reltation found", Toast.LENGTH_SHORT).show();
                                ans =true;
                                break;
                            }
                            else{
                                Toast.makeText(getActivity(), "rel not equall"+relation_with_user, Toast.LENGTH_SHORT).show();
                            }

                        }
                        else
                            ans=false;
                    }
                }
                else{
                    ans=false;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
      //  Toast.makeText(getActivity(), "Return val ="+ans, Toast.LENGTH_SHORT).show();
        //return ans;*/


        if(reference.child("Users Family Data")!=null){
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot i:dataSnapshot.getChildren()){
                        StoringUserFamilyData obj=i.getValue(StoringUserFamilyData.class);
                        String rel_is_there=obj.getRelation_with_user();
                        if(rel_is_there!=null){
                            if(rel_is_there.equals(relation)) {
                                ans = true;
                                Toast.makeText(getActivity(), "yes ="+rel_is_there, Toast.LENGTH_SHORT).show();
                                break;
                            }
                            else{
                                Toast.makeText(getActivity(), "not equall", Toast.LENGTH_SHORT).show();
                            }
                        }
//                    if(snapshot.getChildren().equals(relation)){
//                        ans=true;
//                        Toast.makeText(getActivity(), "YES DB has "+relation+"\n as a child", Toast.LENGTH_SHORT).show();
//                    }
                        else {
                            ans=false;
                            Toast.makeText(getActivity(), "Not it doesnt have"+relation, Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        else
            ans=false;

        Toast.makeText(getActivity(), "return value is "+ans, Toast.LENGTH_SHORT).show();
        return  ans;


    }




    @Override
    public void applyTexts(String name, String dob, String gender, String relation) {
        Toast.makeText(getActivity(), "Details fetched in Pedigree are :"+name+"\n"+dob+"\n"+gender+"\n"+relation, Toast.LENGTH_SHORT).show();
       /* fetched_dob= dob;
        fetched_gender=gender;
        fetched_name=name;
        fetched_relation=relation;*/

    }
}

