package com.akshit.genedetectionapp;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.nio.file.attribute.UserPrincipalLookupService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    String [] all_Array_list;
   // public static  ArrayList<String> selected_symptoms;
    int number;
     public MainAdapter(String [] all_disease_arraylist,int number) {
           this.all_Array_list = all_disease_arraylist;
           this.number=number;

     }

    @NonNull
    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.match_symptoms_single_layout,parent,false);
       return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final MainAdapter.ViewHolder holder, int position) {
        String symptom=all_Array_list[position];
     /*if (Match_Symptoms.selected_symptoms.contains(symptom)){
          holder.checkedTextView.setChecked(true);
      }*/

        holder.checkedTextView.setText(symptom);
        switch (number) {
            case 1: holder.checkedTextView.setChecked(Match_Symptoms.matched_symptoms_user1.contains(symptom));
                break;
            case 2: holder.checkedTextView.setChecked(Match_Symptoms.matched_symptoms_user2.contains(symptom));
                break;
            case 3 : holder.checkedTextView.setChecked(Match_Symptoms.matched_symptoms_user3.contains(symptom));
                break;
            case 4 : holder.checkedTextView.setChecked(Match_Symptoms.matched_symptoms_user4.contains(symptom));
                break;
            case 5: holder.checkedTextView.setChecked(Match_Symptoms.matched_symptoms_user5.contains(symptom));
                break;
            case 6: holder.checkedTextView.setChecked(Match_Symptoms.matched_symptoms_user6.contains(symptom));
                break;
            case 7: holder.checkedTextView.setChecked(Match_Symptoms.matched_symptoms_user7.contains(symptom));
                break;
            case 8: holder.checkedTextView.setChecked(Match_Symptoms.matched_symptoms_user8.contains(symptom));
                break;
            case 9: holder.checkedTextView.setChecked(Match_Symptoms.matched_symptoms_user9.contains(symptom));
                break;

        }


        holder.checkedTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.checkedTextView.setChecked(!holder.checkedTextView.isChecked());
                    switch (number) {
                        case 1:
                            if(holder.checkedTextView.isChecked())
                            Match_Symptoms.matched_symptoms_user1.add(holder.checkedTextView.getText().toString());
                            else
                                Match_Symptoms.matched_symptoms_user1.remove(holder.checkedTextView.getText().toString());
                            break;
                        case 2:
                            if(holder.checkedTextView.isChecked()){
                            Match_Symptoms.matched_symptoms_user2.add(holder.checkedTextView.getText().toString());
                          // Logic for Preserving the matched symptoms.
                                //  Match_Symptoms.selected_symptoms.add(holder.checkedTextView.getText().toString());
                            }else
                                Match_Symptoms.matched_symptoms_user2.remove(holder.checkedTextView.getText().toString());
                            break;
                        case 3 :
                            if(holder.checkedTextView.isChecked())
                            Match_Symptoms.matched_symptoms_user3.add(holder.checkedTextView.getText().toString());
                            else
                                Match_Symptoms.matched_symptoms_user3.remove(holder.checkedTextView.getText().toString());
                            break;
                        case 4 :
                            if(holder.checkedTextView.isChecked())
                            Match_Symptoms.matched_symptoms_user4.add(holder.checkedTextView.getText().toString());
                            else
                                Match_Symptoms.matched_symptoms_user4.remove(holder.checkedTextView.getText().toString());
                            break;
                        case 5:
                            if(holder.checkedTextView.isChecked())
                            Match_Symptoms.matched_symptoms_user5.add(holder.checkedTextView.getText().toString());
                            else
                                Match_Symptoms.matched_symptoms_user5.remove(holder.checkedTextView.getText().toString());
                            break;
                        case 6:
                            if(holder.checkedTextView.isChecked())
                            Match_Symptoms.matched_symptoms_user6.add(holder.checkedTextView.getText().toString());
                            else
                                Match_Symptoms.matched_symptoms_user6.remove(holder.checkedTextView.getText().toString());
                            break;
                        case 7:
                            if(holder.checkedTextView.isChecked())
                            Match_Symptoms.matched_symptoms_user7.add(holder.checkedTextView.getText().toString());
                            else
                                Match_Symptoms.matched_symptoms_user7.remove(holder.checkedTextView.getText().toString());
                            break;
                        case 8:
                            if(holder.checkedTextView.isChecked())
                            Match_Symptoms.matched_symptoms_user8.add(holder.checkedTextView.getText().toString());
                            else
                                Match_Symptoms.matched_symptoms_user8.remove(holder.checkedTextView.getText().toString());
                            break;
                        case 9:
                            if(holder.checkedTextView.isChecked())
                            Match_Symptoms.matched_symptoms_user9.add(holder.checkedTextView.getText().toString());
                            else
                                Match_Symptoms.matched_symptoms_user9.remove(holder.checkedTextView.getText().toString());
                            break;
                    }
            }
        });
        }

    @Override
    public int getItemCount() {
        return all_Array_list.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CheckedTextView checkedTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            checkedTextView=itemView.findViewById(R.id.checkedTextView);

            checkedTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                       // Match_Symptoms obj= new Match_Symptoms();
                    checkedTextView.setChecked(!checkedTextView.isChecked());
                            if(checkedTextView.isChecked()){
                               // Match_Symptoms.selected_symptoms.add(checkedTextView.getText().toString());
                   }
                }
            });
        }


    }
}
