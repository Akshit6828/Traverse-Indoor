package com.akshit.genedetectionapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

public class PedigreeAnalysis extends Fragment implements CustomDialogProfile.CustomDialogProfileListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    public static ImageButton im1,im2,im3,im4,im5,im6,im7,im8,im9;
    ImageView userimage;
    SharedPreferences preferences;
    Dialog dialog, dialog_input;
//    EditText input_name, input_gender,input_DOB;
//    String name,dob,gender;
//    String final_dob,final_name,final_gender;
    public static  String fetched_name,fetched_dob,fetched_relation,fetched_gender;
   // int year,month,day;
    FirebaseDatabase database;
    DatabaseReference reference;

    public PedigreeAnalysis() { }

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
        reference=database.getReference("Users Family Data");

        dialog= new Dialog(getActivity());
        dialog_input= new Dialog(getActivity());
        im1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(im1.getBackground().getConstantState()!= getResources().getDrawable(R.drawable.ic_add).getConstantState()) {
                    reference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot i : dataSnapshot.getChildren()) {
                                StoringUserFamilyData obj = i.getValue(StoringUserFamilyData.class);
                                String relation_with_user = obj.getRelation_with_user();
                                if (relation_with_user != null) {
                                    if (relation_with_user.equals("Paternal Grand Father")) {
                                        fetched_name = obj.getName();
                                        fetched_dob=obj.getDob();
                                        fetched_gender=obj.getGender();
                                        fetched_relation=relation_with_user;
                                        showCustomDialog(fetched_name, fetched_gender, fetched_relation, fetched_dob);
                                        break;
                                    }
                                }

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                 else {
                    //Fetch DataBase Details:
                    showInputCustomDialog("Paternal Grand Father");

                }





            }
        });
        im2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(im2.getBackground().getConstantState()!= getResources().getDrawable(R.drawable.ic_add).getConstantState()){
                    showCustomDialog(fetched_name,fetched_gender,fetched_relation,fetched_dob);
                }

                else {
                    //Fetch DataBase Details:
                    showInputCustomDialog("Paternal Grand Mother");
                }

            }
        });
        im3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(im3.getBackground().getConstantState()!= getResources().getDrawable(R.drawable.ic_add).getConstantState()){
                    showCustomDialog(fetched_name,fetched_gender,fetched_relation,fetched_dob);
                }

                else {
                    //Fetch DataBase Details:
                    showInputCustomDialog("Maternal Grand Father");
                }

            }
        });
        im4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(im4.getBackground().getConstantState()!= getResources().getDrawable(R.drawable.ic_add).getConstantState()){
                    showCustomDialog(fetched_name,fetched_gender,fetched_relation,fetched_dob);
                }

                else {
                    //Fetch DataBase Details:
                    showInputCustomDialog("Maternal Grand Mother");
                }
            }
        });
        im5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(im5.getBackground().getConstantState()!= getResources().getDrawable(R.drawable.ic_add).getConstantState()){
                    showCustomDialog(fetched_name,fetched_gender,fetched_relation,fetched_dob);
                }

                else {
                    //Fetch DataBase Details:
                    showInputCustomDialog("Father");
                }

            }
        });

        im6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(im6.getBackground().getConstantState()!= getResources().getDrawable(R.drawable.ic_add).getConstantState()){
                    showCustomDialog(fetched_name,fetched_gender,fetched_relation,fetched_dob);
                }

                else {
                    //Fetch DataBase Details:
                    showInputCustomDialog("Mother");
                }

            }
        });
        im7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(im7.getBackground().getConstantState()!= getResources().getDrawable(R.drawable.ic_add).getConstantState()){
                    showCustomDialog(fetched_name,fetched_gender,fetched_relation,fetched_dob);
                }

                else {
                    //Fetch DataBase Details:
                    showInputCustomDialog("Self");
                }
            }
        });
        im8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(im8.getBackground().getConstantState()!= getResources().getDrawable(R.drawable.ic_add).getConstantState()){
                    showCustomDialog(fetched_name,fetched_gender,fetched_relation,fetched_dob);
                }

                else {
                    //Fetch DataBase Details:
                    showInputCustomDialog("Child 1");
                }
            }
        });
        im9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(im9.getBackground().getConstantState()!= getResources().getDrawable(R.drawable.ic_add).getConstantState()){
                    showCustomDialog(fetched_name,fetched_gender,fetched_relation,fetched_dob);
                }

                else {
                    //Fetch DataBase Details:
                    showInputCustomDialog("Child 2");
                }
            }
        });



        return mylayout;

    }

    //*********************INPUT DIALOG CODE GOES HERE***************************************

    public void showInputCustomDialog(String relation) {

        /*final ArrayList<String> data;
        final int[] flag = {0};
        data = new ArrayList<String>();
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
        DialogFragment show_input = new CustomDialogProfile();
        Bundle args = new Bundle();
        args.putString("Relation", relation);
        show_input.setArguments(args);
        show_input.show(getActivity().getSupportFragmentManager(),"Opening Input Dialog");

    }


    //******************CUSTOM DIALOG PROFILE TO BE SHOWN*****************************

    private void showCustomDialog(String name_of_current_profile,String gender_user,String relation_with_user,String dob) {

      //  Toast.makeText(getActivity(), "ShowCustomCalled and name ="+name_of_current_profile+"\n gender ="+gender_user+"\nrelation= "+relation_with_user+"\ndob ="+dob, Toast.LENGTH_SHORT).show();

        dialog.setContentView(R.layout.cutom_dialog_profile);
        final TextView name= dialog.findViewById(R.id.idname_custom_dialog);
        userimage =dialog.findViewById(R.id.iduserimage);
        final TextView gender= dialog.findViewById(R.id.idgender_custom_dialog);
        final TextView relation= dialog.findViewById(R.id.idrelation_custom_dialog);
        final TextView dobget= dialog.findViewById(R.id.iddob);
        final TextView cross=dialog.findViewById(R.id.close);
        final Button edit_profile=dialog.findViewById(R.id.editbutton);
        LinearLayout l1,l2,l3;
        l1=dialog.findViewById(R.id.setReminder);
        l2=dialog.findViewById(R.id.match_symptoms);
        l3=dialog.findViewById(R.id.preventive_measures);

        if(gender_user!=null){
            Uri imgUri;
            if(gender.equals("Female")){
                imgUri = Uri.parse("android.resource://com.akshit.genedetectionapp/" + R.drawable.femalepng);
            }
            else{
                imgUri = Uri.parse("android.resource://com.akshit.genedetectionapp/" + R.drawable.male);
            }
            userimage.setImageURI(imgUri);

        }

        name.setText(name_of_current_profile);
        dobget.setText("DOB : "+dob);
        gender.setText("Gender : "+gender_user);
        relation.setText("Relation : "+relation_with_user);
        dialog.show();


        //Edit button listener
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


//    DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
//        @Override
//        public void onDateSet(DatePicker datePicker, int y, int m, int dOM) {
//            input_DOB.setText(dOM+"-"+m+"-"+y);
//            year=y;
//            month=m;
//            day=dOM;
//        }
//    };


    @Override
    public void applyTexts(String name, String dob, String gender, String relation) {
        Toast.makeText(getActivity(), "Details fetched are :"+name+"\n"+dob+"\n"+gender+"\n"+relation, Toast.LENGTH_SHORT).show();
       /* fetched_dob= dob;
        fetched_gender=gender;
        fetched_name=name;
        fetched_relation=relation;*/
    }
}
