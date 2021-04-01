package com.akshit.genedetectionapp;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Results_Adapter extends RecyclerView.Adapter<Results_Adapter.MyViewHolder2> {

    ArrayList<Double> values_fetched;
    ArrayList<Double> values_expected;
    Integer[] img_disease;
    String[] disease_name;
    public Results_Adapter( ArrayList<Double> values_expected,ArrayList<Double> values_fetched, Integer[] img_disease,String [] disease_name) {
        this.values_fetched = values_fetched;
        this.values_expected = values_expected;
        this.img_disease = img_disease;
this.disease_name=disease_name;
    }


    @NonNull
    @Override
    public Results_Adapter.MyViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_single_row_results,parent,false);
        return new MyViewHolder2(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Results_Adapter.MyViewHolder2 holder, int position) {

        holder.imv.setImageResource(img_disease[position]);
        holder.expected_value_disease.setText("Expected Value:\n"+values_expected.get(position).toString()+"%");
        holder.actual_value_disease.setText("Actual Value:\n"+values_fetched.get(position).toString()+"%");
        holder.disease.setText(disease_name[position]);
        if(Double.parseDouble(values_fetched.get(position).toString())>=Double.parseDouble(values_expected.get(position).toString())){
            holder.actual_value_disease.setTextColor(Color.RED);
        }
        else if(Double.parseDouble(values_fetched.get(position).toString())<Double.parseDouble(values_expected.get(position).toString())){
            holder.actual_value_disease.setTextColor(Color.GREEN);

        }


    }

    @Override
    public int getItemCount() {
        return img_disease.length;
    }

    public class MyViewHolder2 extends RecyclerView.ViewHolder {

        ImageView imv;
        TextView expected_value_disease;
        TextView actual_value_disease;
        TextView disease;
        public MyViewHolder2(@NonNull View itemView) {
            super(itemView);
            imv=itemView.findViewById(R.id.image_disease);
            expected_value_disease=itemView.findViewById(R.id.expected_Value);
            actual_value_disease=itemView.findViewById(R.id.actualvalue);
            disease=itemView.findViewById(R.id.disease_name);

        }
    }


}
