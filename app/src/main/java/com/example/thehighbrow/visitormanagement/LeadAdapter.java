package com.example.thehighbrow.visitormanagement;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class LeadAdapter extends RecyclerView.Adapter<LeadAdapter.LeadHolder> {

    private ArrayList<Lead> leadArrayList;
    LeadAdapter(ArrayList<Lead> leads){leadArrayList = leads;}
    @NonNull
    @Override
    public LeadAdapter.LeadHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        View inflatedView = LayoutInflater.from(context).inflate(R.layout.lead_row,viewGroup,false);
        LeadHolder leadHolder = new LeadHolder(inflatedView);
        return leadHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LeadAdapter.LeadHolder leadHolder, int i) {

        Lead currentLead = leadArrayList.get(i);
        leadHolder.name.setText(currentLead.getName());
        leadHolder.contact.setText(currentLead.getContact());
        leadHolder.email.setText(currentLead.getEmail());
        leadHolder.reach.setText(currentLead.getReach());
        leadHolder.time.setText(currentLead.getTime());
        leadHolder.date.setText(currentLead.getDate());
        Picasso.get().load(currentLead.getPhotoUrl()).into(leadHolder.photo);


    }

    @Override
    public int getItemCount() {
        return leadArrayList.size();
    }

    public class LeadHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView contact;
        TextView email;
        TextView reach;
        TextView time;
        TextView date;
        ImageView photo;
        public LeadHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.namefield);
            contact = itemView.findViewById(R.id.contactfield);
            email = itemView.findViewById(R.id.emailfield);
            reach = itemView.findViewById(R.id.reachfield);
            time = itemView.findViewById(R.id.timefield);
            date = itemView.findViewById(R.id.datefield);
            photo = itemView.findViewById(R.id.visitorphoto);
        }
    }
}
