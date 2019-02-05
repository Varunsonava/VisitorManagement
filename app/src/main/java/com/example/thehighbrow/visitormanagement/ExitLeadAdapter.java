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

public class ExitLeadAdapter extends RecyclerView.Adapter<ExitLeadAdapter.ExitLeadHolder> {

    private ArrayList<Lead> ExitLeadArrayList;
    private Context mContext;



    ExitLeadAdapter(ArrayList<Lead> leads, Context context){
        ExitLeadArrayList = leads;
        this.mContext=context;
    }


    @NonNull
    @Override
    public ExitLeadAdapter.ExitLeadHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        View inflatedView = LayoutInflater.from(context).inflate(R.layout.exit_visitor_row,viewGroup,false);
        ExitLeadAdapter.ExitLeadHolder exitLeadHolder = new ExitLeadAdapter.ExitLeadHolder(inflatedView);
        return exitLeadHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ExitLeadAdapter.ExitLeadHolder exitLeadHolder, int i) {
        final Lead currentlead = ExitLeadArrayList.get(i);
        exitLeadHolder.name.setText(currentlead.getName());
        exitLeadHolder.id.setText(currentlead.getId());
        exitLeadHolder.outtime.setText(currentlead.getOuttime());
        exitLeadHolder.intime.setText(currentlead.getTime());
        final String uid = exitLeadHolder.id.getText().toString();

        exitLeadHolder.photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Bundle bundle=new Bundle();
                bundle.putString("leadid",uid);
                Fragment exitFragment = new ExitFragment();

                Log.e(TAG, "onClick: "+uid);
                exitFragment.setArguments(bundle);
                Intent intent = new Intent(mContext.getApplicationContext(),exitActivity.class);
                intent.putExtra("leadid",uid);
                mContext.startActivity(intent);

            }
        });
        Picasso.get().load(currentlead.getPhotoUrl()).into(exitLeadHolder.photo);
    }



    @Override
    public int getItemCount() {
        return ExitLeadArrayList.size();
    }

    public class ExitLeadHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView photo;
        TextView id;
        TextView outtime;
        TextView intime;

        public ExitLeadHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.namefield);
            photo = itemView.findViewById(R.id.visitorphoto);
            id = itemView.findViewById(R.id.idfield);
            outtime = itemView.findViewById(R.id.outtimefield);
            intime = itemView.findViewById(R.id.intimefield);
        }
    }
}
