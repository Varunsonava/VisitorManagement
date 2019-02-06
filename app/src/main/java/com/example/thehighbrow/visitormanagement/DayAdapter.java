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

public class DayAdapter extends RecyclerView.Adapter<DayAdapter.DayHolder> {

    private ArrayList<DayVisitor> DayArrayList;
    DayAdapter(ArrayList<DayVisitor> dayVisitors){DayArrayList = dayVisitors;}
    @NonNull
    @Override
    public DayAdapter.DayHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        View inflatedView = LayoutInflater.from(context).inflate(R.layout.day_row,viewGroup,false);
        DayAdapter.DayHolder dayHolder = new DayAdapter.DayHolder(inflatedView);
        return dayHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DayAdapter.DayHolder dayHolder, int i) {

        DayVisitor currentdayvisitor = DayArrayList.get(i);
        dayHolder.name.setText(currentdayvisitor.getName());
        dayHolder.contact.setText(currentdayvisitor.getContact());
        dayHolder.email.setText(currentdayvisitor.getEmail());
        dayHolder.invoiceName.setText(currentdayvisitor.getInvoice());
        dayHolder.gst.setText(currentdayvisitor.getGst());
        dayHolder.time.setText(currentdayvisitor.getTime());
        dayHolder.date.setText(currentdayvisitor.getDate());
        dayHolder.outtime.setText(currentdayvisitor.getOuttime());


        Picasso.get().load(currentdayvisitor.getPhotoUrl()).into(dayHolder.photo);


    }

    @Override
    public int getItemCount() {
        return DayArrayList.size();
    }

    public class DayHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView contact;
        TextView email;
        TextView gst;
        TextView invoiceName;
        TextView date;
        TextView time;
        ImageView photo;
        TextView outtime;
        public DayHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.namefield);
            contact = itemView.findViewById(R.id.contactfield);
            email = itemView.findViewById(R.id.emailfield);
            gst = itemView.findViewById(R.id.gstfield);
            invoiceName = itemView.findViewById(R.id.invoicefield);
            time = itemView.findViewById(R.id.timefield);
            date = itemView.findViewById(R.id.datefield);
            photo = itemView.findViewById(R.id.visitorphoto);
            outtime = itemView.findViewById(R.id.outtimefield);
        }
    }
}
