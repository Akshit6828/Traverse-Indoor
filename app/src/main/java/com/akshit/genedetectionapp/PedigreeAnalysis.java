package com.akshit.genedetectionapp;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

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

            }
        });
        im2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        im3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        im4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        im5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        im6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        im7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        im8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        im9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });



        return mylayout;

    }
}