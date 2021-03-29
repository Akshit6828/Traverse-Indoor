package com.akshit.genedetectionapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapterMatch_Symptoms extends  RecyclerView.Adapter<CustomAdapterMatch_Symptoms.myviewholder> {

    ArrayList<Data_model_match_symptoms> dataholder;
    String current_arraylist;
    public CustomAdapterMatch_Symptoms(ArrayList<Data_model_match_symptoms> dataholder) {
        this.dataholder = dataholder;
    }



    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.match_symptoms_single_layout,parent,false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
       // holder.checkedTextView.setText(dataholder.get(position).get);
        //current_arraylist=holder.checkedTextView()
    }

    @Override
    public int getItemCount() {
        return dataholder.size();
    }

    class myviewholder extends RecyclerView.ViewHolder {
        CheckedTextView checkedTextView;
        ArrayList<String> arrayListfordisease;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            checkedTextView=itemView.findViewById(R.id.checkedTextView);
            checkedTextView.setChecked(false);
            checkedTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    checkedTextView.setChecked(!checkedTextView.isChecked());
                }
            });
            //Logic for adding to matched symptoms and then checking the probability.


        }
    }
}
