package com.akshit.genedetectionapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import de.blox.graphview.GraphView;

public class Viewholder extends GraphView.ViewHolder {
    TextView textView;
    ImageView imageView;

    Viewholder(View itemview) {
        super(itemview);
        imageView=itemview.findViewById(R.id.idIvNode);
        textView = itemview.findViewById(R.id.idTvNode);
    }
}
