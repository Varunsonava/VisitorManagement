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

class VendorAdapter extends RecyclerView.Adapter<VendorAdapter.VendorHolder> {

    private ArrayList<Vendor> VendorArrayList;
    VendorAdapter(ArrayList<Vendor> vendors){VendorArrayList = vendors;}
    @NonNull
    @Override
    public VendorAdapter.VendorHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        View inflatedView = LayoutInflater.from(context).inflate(R.layout.vendor_row,viewGroup,false);
        VendorAdapter.VendorHolder vendorHolder = new VendorAdapter.VendorHolder(inflatedView);
        return vendorHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull VendorAdapter.VendorHolder vendorHolder, int i) {

        Vendor currentVendor = VendorArrayList.get(i);
        vendorHolder.name.setText(currentVendor.getName());
        vendorHolder.contact.setText(currentVendor.getContact());
        vendorHolder.host.setText(currentVendor.getHost());
        vendorHolder.time.setText(currentVendor.getTime());
        vendorHolder.date.setText(currentVendor.getDate());



        Picasso.get().load(currentVendor.getPhotoUrl()).into(vendorHolder.photo);


    }

    @Override
    public int getItemCount() {
        return VendorArrayList.size();
    }

    public class VendorHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView contact;
        TextView host;
        TextView date;
        TextView time;
        ImageView photo;
        public VendorHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.namefield);
            contact = itemView.findViewById(R.id.contactfield);
            host = itemView.findViewById(R.id.hostfield);
            time = itemView.findViewById(R.id.timefield);
            date = itemView.findViewById(R.id.datefield);
            photo = itemView.findViewById(R.id.visitorphoto);
        }
    }

}
