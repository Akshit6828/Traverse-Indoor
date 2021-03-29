package com.akshit.genedetectionapp;

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
//    public MainAdapter(ArrayList<ArrayList<String>> all_disease_arraylist) {
//        this.all_disease_arraylist = all_disease_arraylist;
//    }
    String [] all_Array_list;

   /* public MainAdapter(String[] disease) {
        this.disease = disease;
    }*/

     public MainAdapter(String [] all_disease_arraylist) {
           this.all_Array_list = all_disease_arraylist;
        }
  //  String [] disease;
//    String[]  diabetes;
//    String[] migrane;
//    String[] hyperthyroid;
//    String[] hypothyroid;
//    String[] congenital_heart_disease;
//    String[] thalassemia;
//    String[] rheumatoid_arthritis;

//    public  MainAdapter(String[] diabetes, String[] migrane, String[] hyperthyroid, String[] hypothyroid, String[] congenital_heart_disease, String[] thalassemia, String[] rheumatoid_arthritis) {
//        this.diabetes = diabetes;
//        this.migrane = migrane;
//        this.hyperthyroid = hyperthyroid;
//        this.hypothyroid = hypothyroid;
//        this.congenital_heart_disease = congenital_heart_disease;
//        this.thalassemia = thalassemia;
//        this.rheumatoid_arthritis = rheumatoid_arthritis;
//    }

    @NonNull
    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.match_symptoms_single_layout,parent,false);
       return new ViewHolder(view);
    }
/* holder.returnedArraylist = all_Array_list.get(position);
        holder.checkedTextView.setText(holder.returnedArraylist[position]);*/


    /*       holder.returnedArraylist = all_Array_list.get(position);
         for(int i=0;i<holder.returnedArraylist.length;i++)
        holder.checkedTextView.setText(holder.returnedArraylist[i]);*/
    @Override
    public void onBindViewHolder(@NonNull MainAdapter.ViewHolder holder, int position) {
//       // for(int i=0;i<7;i++){
//        for(int j=0;j<all_Array_list.size();j++) {//all_Arrayylist obj1,obj2..obj7
//            holder.returnedArray = all_Array_list.get(j);
//            //holder.returnedArraylist;
//            for (int i = 0; i < holder.returnedArray.length; i++)
//                holder.checkedTextView.setText(holder.returnedArray[i]);
//        }
        holder.checkedTextView.setText(all_Array_list[position]);
        holder.checkedTextView.setChecked(false);


            /*if( position==1){
                diabetes=holder.returnedArraylist;
                for(int j=0;j<holder.returnedArraylist.length;j++) {
                    holder.checkedTextView.setText(holder.returnedArraylist[position]);
                }
            }
            else if(position==) {
                migrane=holder.returnedArraylist;
                for(int j=0;j<migrane.length;j++) {
                    holder.checkedTextView.setText(holder.returnedArraylist[position]);
                    holder.checkedTextView.setChecked(false);
                }
            }
            else  if(position==2){
                hyperthyroid=holder.returnedArraylist;
                for(int j=0;j<hyperthyroid.length;j++){
                    holder.checkedTextView.setText(hyperthyroid[j]);
                    holder.checkedTextView.setChecked(false);
                }
            }

            else if(position==3)
            {
                hypothyroid=holder.returnedArraylist;
                for(int j=0;j<hypothyroid.length;j++){
                    holder.checkedTextView.setText(hypothyroid[j]);
                    holder.checkedTextView.setChecked(false);
                }

            }
            else if(position==4)
            {
                congenital_heart_disease=holder.returnedArraylist;
                for(int j=0;j<congenital_heart_disease.length;j++){
                    holder.checkedTextView.setText(congenital_heart_disease[j]);
                    holder.checkedTextView.setChecked(false);
                }
            }
            else if(position==5)
            {
                thalassemia=holder.returnedArraylist;
                for(int j=0;j<thalassemia.length;j++){
                    holder.checkedTextView.setText(thalassemia[j]);
                    holder.checkedTextView.setChecked(false);
                }

            }
            else if(position==6)
            {
                for(int j=0;j<rheumatoid_arthritis.length;j++){
                    holder.checkedTextView.setText(rheumatoid_arthritis[j]);
                    holder.checkedTextView.setChecked(false);
                }
            }*/


        }


   // }

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
