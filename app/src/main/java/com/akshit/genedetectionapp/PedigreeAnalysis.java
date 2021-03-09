package com.akshit.genedetectionapp;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

        return mylayout;

    }
}