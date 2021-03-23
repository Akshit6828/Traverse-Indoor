package com.akshit.genedetectionapp;

import android.app.Activity;
import android.content.Intent;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class MedicationAdapter extends RecyclerView.Adapter<MedicationAdapter.MyViewHolder> {
    private final Activity activity;
    Integer pic_des[];
    String text_des[],type_des[];
    OnClickItem mOnClickItem;
    CardView cardView;


    MedicationAdapter(Integer pic_des[], String text_des[], String type_des[], Activity activity,OnClickItem mOnClickItem){
        this.pic_des=pic_des;
        this.text_des=text_des;
        this.type_des=type_des;
        this.activity=activity;
        this.mOnClickItem=mOnClickItem;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mylayout=activity.getLayoutInflater().inflate(R.layout.medicare_items,parent,false);
        MyViewHolder holder= new MyViewHolder(mylayout,mOnClickItem);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
          holder.pic.setImageResource(pic_des[position]);
          holder.text.setText(text_des[position]);
          holder.type.setText(type_des[position]);
          holder.cardView.setBackgroundResource(R.drawable.container_bg);
    }

    @Override
    public int getItemCount() {
        return text_des.length;
    }


        class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        CardView cardView;
        ImageView pic;
        TextView text,type;
        OnClickItem onClickItem;

        public MyViewHolder(@NonNull View itemView,OnClickItem onClickItem) {
            super(itemView);
            this.pic=itemView.findViewById(R.id.pic_disease);
            this.text=itemView.findViewById(R.id.text_disease);
            this.type=itemView.findViewById(R.id.type_disease);
            this.cardView=itemView.findViewById(R.id.view_disease);
            this.onClickItem=onClickItem;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onClickItem.ItemClick(getAdapterPosition());

        }
    }
    public interface OnClickItem{
        void ItemClick(int position);
    }
}


