package com.example.thehighbrow.visitormanagement;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

class VisitorAdapter extends RecyclerView.Adapter<VisitorAdapter.ViewHolder> {

    ArrayList<Visitor> visitors;

    public VisitorAdapter(ArrayList<Visitor> visitors) {
        this.visitors = visitors;
    }

    @NonNull
    @Override
    public VisitorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_row,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VisitorAdapter.ViewHolder viewHolder, int i) {

        viewHolder.name.setText(visitors.get(i).getName());
        viewHolder.contact.setText(visitors.get(i).getContact());
        viewHolder.host.setText(visitors.get(i).getHost());
  //      String purl = visitors.get(i).getPhotoUrl();
     /*   if (Uri.parse(purl)!=null){
            Uri puri = Uri.parse(purl);
            Picasso.get().load(puri).into(viewHolder.photo);
        }
        else{*/
            Uri imageuri = Uri.parse(visitors.get(i).getPhotoUrl());
            Picasso.get().load(imageuri).into(viewHolder.photo);
        //    viewHolder.photo.setImageURI(imageuri);
   //     }


    }

    @Override
    public int getItemCount() {
        return visitors.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView contact;
        public TextView host;
        public ImageView photo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.namefield);
            contact=itemView.findViewById(R.id.contactfield);
            host=itemView.findViewById(R.id.hostfield);
            photo = itemView.findViewById(R.id.visitorphoto);

        }
    }
}
