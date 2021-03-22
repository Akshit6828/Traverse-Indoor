package com.akshit.genedetectionapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import de.blox.graphview.Graph;
import de.blox.graphview.GraphAdapter;
import de.blox.graphview.GraphView;
import de.blox.graphview.Node;

public class Usertree extends AppCompatActivity {
    GraphView graphView;
    GraphAdapter<GraphView.ViewHolder> graphAdapter;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_familytree);
        tv = findViewById(R.id.textView);
       // graphView = findViewById(R.id.graph);

        // example tree

        final Graph graph = new Graph();
        final Node node1 = new Node("You");
        final Node node2 = new Node("Father");
        final Node node3 = new Node("Mother");
        final Node node4 = new Node("Node4");

        graph.addEdge(node1, node2);
        graph.addEdge(node1, node3);
        graph.addNode(node4);
        graphAdapter = new GraphAdapter<GraphView.ViewHolder>(graph) {
            @NonNull
            @Override
            public GraphView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
                //final View view = LayoutInflater.from(Usertree.this).inflate(R.layout.treenode,false,getParent());
                //   return new SimpleViewHolder(view);
                return null;

            }

            @Override
            public void onBindViewHolder(GraphView.ViewHolder viewHolder, Object o, int i) {
                //    ((SimpleViewHolder) viewHolder).tv.setText(data.toString());

            }

            @Override
            public int getCount() {
                return 0;
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }
        };
    }
}
