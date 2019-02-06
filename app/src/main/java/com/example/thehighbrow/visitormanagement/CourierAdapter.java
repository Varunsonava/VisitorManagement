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

class CourierAdapter extends RecyclerView.Adapter<CourierAdapter.CourierHolder> {

    private ArrayList<Courier> CourierArrayList;
    CourierAdapter(ArrayList<Courier> couriers){CourierArrayList = couriers;}
    @NonNull
    @Override
    public CourierAdapter.CourierHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        View inflatedView = LayoutInflater.from(context).inflate(R.layout.courier_row,viewGroup,false);
        CourierAdapter.CourierHolder courierHolder = new CourierAdapter.CourierHolder(inflatedView);
        return courierHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CourierAdapter.CourierHolder courierHolder, int i) {

        Courier currentCourier = CourierArrayList.get(i);
        courierHolder.name.setText(currentCourier.getName());
        courierHolder.contact.setText(currentCourier.getContact());

        courierHolder.deliverto.setText(currentCourier.getDeliverto());

        courierHolder.time.setText(currentCourier.getTime());

        courierHolder.date.setText(currentCourier.getDate());

        Picasso.get().load(currentCourier.getPhotoUrl()).into(courierHolder.photo);


    }

    @Override
    public int getItemCount() {
        return CourierArrayList.size();
    }

    public class CourierHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView contact;
        TextView deliverto;
        TextView date;
        TextView time;
        ImageView photo;
        public CourierHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.namefield);
            contact = itemView.findViewById(R.id.contactfield);
            deliverto = itemView.findViewById(R.id.deliverfield);
            time = itemView.findViewById(R.id.timefield);
            date = itemView.findViewById(R.id.datefield);
            photo = itemView.findViewById(R.id.visitorphoto);
        }
    }

}
