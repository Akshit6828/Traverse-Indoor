package com.akshit.genedetectionapp;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import de.blox.graphview.Graph;
import de.blox.graphview.GraphAdapter;
import de.blox.graphview.GraphView;
import de.blox.graphview.Node;
import de.blox.graphview.tree.BuchheimWalkerAlgorithm;
import de.blox.graphview.tree.BuchheimWalkerConfiguration;
import de.blox.treeview.BaseTreeAdapter;
import de.blox.treeview.TreeNode;
import de.blox.treeview.TreeView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PedigreeAnalysis#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PedigreeAnalysis extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ImageButton im1,im2,im3,im4,im5,im6,im7,im8,im9;
    SharedPreferences preferences;

    public PedigreeAnalysis() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PedigreeAnalysis.
     */
    // TODO: Rename and change types and number of parameters
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
        im1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Drawable img=im1.getDrawable();
                if (img!=null&&img.toString().equals(R.drawable.ic_add)) {
                    showCustomDialog("No Name","Unknown","Unknown");
                }
                else {
                    //Fetch DataBase Details:
                    Toast.makeText(getActivity(), "Diff Image", Toast.LENGTH_SHORT).show();
                }

            }
        });
        im2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Drawable img=im2.getDrawable();
                if (img!=null&&img.toString().equals(R.drawable.ic_add)) {
                    showCustomDialog("No Name","Unknown","Unknown");
                }
                else {
                    //Fetch DataBase Details:
                    Toast.makeText(getActivity(), "Diff Image", Toast.LENGTH_SHORT).show();
                }

            }
        });
        im3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Drawable img=im3.getDrawable();
                if (img!=null&&img.toString().equals(R.drawable.ic_add)) {
                    showCustomDialog("No Name","Unknown","Unknown");
                }
                else {
                    //Fetch DataBase Details:
                    Toast.makeText(getActivity(), "Diff Image", Toast.LENGTH_SHORT).show();
                }

            }
        });
        im4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Drawable img=im4.getDrawable();
                if (img!=null&&img.toString().equals(R.drawable.ic_add)) {
                    showCustomDialog("No Name","Unknown","Unknown");
                }
                else {
                    //Fetch DataBase Details:
                    Toast.makeText(getActivity(), "Diff Image", Toast.LENGTH_SHORT).show();
                }
            }
        });
        im5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Drawable img=im5.getDrawable();
                if (img!=null&&img.toString().equals(R.drawable.ic_add)) {
                    showCustomDialog("No Name","Unknown","Unknown");
                }
                else {
                    //Fetch DataBase Details:
                    Toast.makeText(getActivity(), "Diff Image", Toast.LENGTH_SHORT).show();
                }

            }
        });

        im6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Drawable img=im6.getDrawable();
                if (img!=null&&img.toString().equals(R.drawable.ic_add)) {
                    showCustomDialog("No Name","Unknown","Unknown");
                }
                else {
                    //Fetch DataBase Details:
                    Toast.makeText(getActivity(), "Diff Image", Toast.LENGTH_SHORT).show();
                }

            }
        });
        im7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                im7.setBackgroundResource(R.drawable.boyfather);
                im7.setScaleType(ImageView.ScaleType.CENTER_CROP);
                preferences= getActivity().getSharedPreferences("Local_Details",0);
                String getname=preferences.getString("username_in_sharedpreference","User");
                showCustomDialog(getname,"Male","Self");

            }
        });
        im8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Drawable img=im8.getDrawable();
                if (img!=null&&img.toString().equals(R.drawable.ic_add)) {
                    showCustomDialog("No Name","Unknown","Unknown");
                }
                else {
                    //Fetch DataBase Details:
                    Toast.makeText(getActivity(), "Diff Image", Toast.LENGTH_SHORT).show();
                }
            }
        });
        im9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Drawable img=im9.getDrawable();
                if (img != null) {
                if(img.equals(R.drawable.ic_add)){
                    showCustomDialog("No Name","Unknown","Unknown");
                }

                }
                else {
                    //Fetch DataBase Details:
                    Toast.makeText(getActivity(), "Diff Image", Toast.LENGTH_SHORT).show();
                }
            }
        });



        return mylayout;

    }

    private void showCustomDialog(String name_of_current_profile,String gender_user,String relation_with_user) {
        final Dialog dialog= new Dialog(getActivity());
        dialog.setContentView(R.layout.cutom_dialog_profile);
        final EditText name= dialog.findViewById(R.id.idname_custom_dialog);
        final TextView gender= dialog.findViewById(R.id.idgender_custom_dialog);
        final TextView relation= dialog.findViewById(R.id.idrelation_custom_dialog);
        final TextView cross=dialog.findViewById(R.id.close);
        LinearLayout l1,l2,l3;
        l1=dialog.findViewById(R.id.setReminder);
        l2=dialog.findViewById(R.id.match_symptoms);
        l3=dialog.findViewById(R.id.preventive_measures);


        name.setCursorVisible(false);
        name.setLongClickable(false);
        name.setClickable(false);
        name.setFocusable(false);
        name.setSelected(false);
        name.setKeyListener(null);
        name.setBackgroundResource(android.R.color.transparent);

        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name.setClickable(true);
                name.setCursorVisible(true);
                name.setFocusable(true);

            }
        });


        name.setText(name_of_current_profile);
        gender.setText("Gender : "+gender_user);
        relation.setText("Relation : "+relation_with_user);
        dialog.show();

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

}