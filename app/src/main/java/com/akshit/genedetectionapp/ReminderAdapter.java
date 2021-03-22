package com.akshit.genedetectionapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ReminderAdapter extends RecyclerView.Adapter<ReminderAdapter.MyViewHolder> {
    String relation[],contact[],date[],time[],message[];
    Activity activity;

    ReminderAdapter(String relation[],String contact[],String date[],String time[],String message[],Activity activity){
        this.activity=activity;
        this.contact=contact;
        this.relation=relation;
        this.date=date;
        this.time=time;
        this.message=message;
    }


    @NonNull
    @Override
    public ReminderAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mylayout=activity.getLayoutInflater().inflate(R.layout.custom_layout,parent,false);
        MyViewHolder holder=new MyViewHolder(mylayout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.rel.setText(relation[position]);
        holder.con.setText(contact[position]);
        holder.dat.setText(date[position]);
        holder.tim.setText(time[position]);
        holder.msg.setText(message[position]);
    }

    @Override
    public int getItemCount() {
        return contact.length;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
       TextView rel,con,dat,tim,msg;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.rel=itemView.findViewById(R.id.relation_value);
            this.con=itemView.findViewById(R.id.contact_value);
            this.dat=itemView.findViewById(R.id.date_value);
            this.tim=itemView.findViewById(R.id.time_value);
            this.msg=itemView.findViewById(R.id.msg_value);

        }
    }
}
