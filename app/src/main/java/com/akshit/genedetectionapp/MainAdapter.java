package com.akshit.genedetectionapp;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    ArrayList<ArrayList<String>> all_disease_arraylist;
    String [] all_Array_list;
     public MainAdapter(String [] all_disease_arraylist) {
           this.all_Array_list = all_disease_arraylist;
        }

    @NonNull
    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.match_symptoms_single_layout,parent,false);
       return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.ViewHolder holder, int position) {

        if(all_Array_list[position].equals("Please Note:\\n Below symptoms are disease specific. Please fill the following symptoms with care if your age is better 25–30 years or below.")){
            holder.checkedTextView.setCheckMarkDrawable(null);
            holder.checkedTextView.setBackground(null);
            holder.checkedTextView.setBackgroundColor(Color.RED);
            holder.checkedTextView.setTextColor(Color.WHITE);
            holder.checkedTextView.setText(all_Array_list[position]);
        }
        else if(all_Array_list[position].equals("Common Symptoms")){
            holder.checkedTextView.setCheckMarkDrawable(null);
            holder.checkedTextView.setBackground(null);
            String color="#555555";
            holder.checkedTextView.setBackgroundColor(Color.BLACK);
            holder.checkedTextView.setTextColor(Color.WHITE);
            holder.checkedTextView.setText(all_Array_list[position]);

        }
        else {
            holder.checkedTextView.setText(all_Array_list[position]);
        }





        }


    @Override
    public int getItemCount() {
        return all_Array_list.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CheckedTextView checkedTextView;
         String[] arr;
         String[] returnedArray;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            checkedTextView=itemView.findViewById(R.id.checkedTextView);
        }
        public  void setTEXTStoView(ViewHolder obj){

        }

    }
}
