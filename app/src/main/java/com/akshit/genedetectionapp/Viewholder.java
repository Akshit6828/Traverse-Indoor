package com.akshit.genedetectionapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Viewholder {
    TextView textView;
    ImageView imageView;

    Viewholder(View view) {
        imageView=view.findViewById(R.id.idIvNode);
        textView = view.findViewById(R.id.idTvNode);
    }
}
