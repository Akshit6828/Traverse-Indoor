package com.akshit.genedetectionapp;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class Adapter_Natural_Asthma extends RecyclerView.Adapter<Adapter_Natural_Asthma.MyViewHolder> {
    private final Activity activity;
    Integer pic_asthma[],text_asthma[];
    String title_asthma[];

    public Adapter_Natural_Asthma(Activity activity, Integer pic_asthma[], String title_asthma[], Integer text_asthma[]) {
        this.activity = activity;
        this.pic_asthma = pic_asthma;
        this.text_asthma = text_asthma;
        this.title_asthma = title_asthma;
    }

    @NonNull
    @Override
    public Adapter_Natural_Asthma.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mylayout=activity.getLayoutInflater().inflate(R.layout.custom_natural_remedies,parent,false);
        Adapter_Natural_Asthma.MyViewHolder holder= new Adapter_Natural_Asthma.MyViewHolder(mylayout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_Natural_Asthma.MyViewHolder holder, int position) {
        holder.pic.setImageResource(pic_asthma[position]);
        holder.title.setText(title_asthma[position]);
        holder.text.setText(text_asthma[position]);
        holder.cardView.setBackgroundResource(R.drawable.container_bg);
    }

    @Override
    public int getItemCount() {
        return pic_asthma.length;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView pic;
        TextView title,text;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.pic = itemView.findViewById(R.id.pic_cure);
            this.title = itemView.findViewById(R.id.title_cure);
            this.text = itemView.findViewById(R.id.text_cure);
            this.cardView = itemView.findViewById(R.id.asthma_view);

        }
    }
}
