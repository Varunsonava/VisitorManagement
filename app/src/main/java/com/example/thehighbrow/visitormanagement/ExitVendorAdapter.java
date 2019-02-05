package com.example.thehighbrow.visitormanagement;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static android.support.constraint.Constraints.TAG;

class ExitVendorAdapter extends RecyclerView.Adapter<ExitVendorAdapter.ExitVendorHolder> {

    private ArrayList<Vendor> ExitVendorArrayList;
    private Context mContext;

    public ExitVendorAdapter(ArrayList<Vendor> vendors, Context context) {
        ExitVendorArrayList = vendors;
        mContext = context;
    }

    @NonNull
    @Override
    public ExitVendorAdapter.ExitVendorHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        View inflatedView = LayoutInflater.from(context).inflate(R.layout.exit_visitor_row,viewGroup,false);
        ExitVendorAdapter.ExitVendorHolder exitVendorHolder = new ExitVendorAdapter.ExitVendorHolder(inflatedView);
        return exitVendorHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ExitVendorAdapter.ExitVendorHolder exitVendorHolder, int i) {
        final Vendor currentVendor = ExitVendorArrayList.get(i);
        exitVendorHolder.name.setText(currentVendor.getName());
        exitVendorHolder.id.setText(currentVendor.getId());
        exitVendorHolder.outtime.setText(currentVendor.getOuttime());
        exitVendorHolder.intime.setText(currentVendor.getTime());
        final String uid = exitVendorHolder.id.getText().toString();

        exitVendorHolder.photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Bundle bundle=new Bundle();
                bundle.putString("vendorid",uid);
                Fragment exitFragment = new ExitFragment();

                Log.e(TAG, "onClick: "+uid);
                exitFragment.setArguments(bundle);
                Intent intent = new Intent(mContext.getApplicationContext(),exitActivity.class);
                intent.putExtra("vendorid",uid);
                mContext.startActivity(intent);

            }
        });
        Picasso.get().load(currentVendor.getPhotoUrl()).into(exitVendorHolder.photo);

    }

    @Override
    public int getItemCount() {
        return ExitVendorArrayList.size();
    }

    public class ExitVendorHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView photo;
        TextView id;
        TextView outtime;
        TextView intime;

        public ExitVendorHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.namefield);
            photo = itemView.findViewById(R.id.visitorphoto);
            id = itemView.findViewById(R.id.idfield);
            outtime = itemView.findViewById(R.id.outtimefield);
            intime = itemView.findViewById(R.id.intimefield);
        }
    }
}
