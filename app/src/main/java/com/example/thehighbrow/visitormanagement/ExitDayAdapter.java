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

public class ExitDayAdapter extends RecyclerView.Adapter<ExitDayAdapter.ExitDayHolder>{
    private ArrayList<DayVisitor> ExitDayArrayList;
    private Context mContext;



    ExitDayAdapter(ArrayList<DayVisitor> dayVisitors, Context context){
        ExitDayArrayList = dayVisitors;
        this.mContext=context;
    }


    @NonNull
    @Override
    public ExitDayAdapter.ExitDayHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        View inflatedView = LayoutInflater.from(context).inflate(R.layout.exit_visitor_row,viewGroup,false);
        ExitDayAdapter.ExitDayHolder exitDayHolder = new ExitDayAdapter.ExitDayHolder(inflatedView);
        return exitDayHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ExitDayAdapter.ExitDayHolder exitDayHolder, int i) {
        final DayVisitor currentdayvisitor = ExitDayArrayList.get(i);
        exitDayHolder.name.setText(currentdayvisitor.getName());
        exitDayHolder.id.setText(currentdayvisitor.getId());
        exitDayHolder.outtime.setText(currentdayvisitor.getOuttime());
        exitDayHolder.time.setText(currentdayvisitor.getTime());
        final String uid = exitDayHolder.id.getText().toString();

        exitDayHolder.photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Bundle bundle=new Bundle();
                bundle.putString("dayid",uid);
                Fragment exitFragment = new ExitFragment();

                Log.e(TAG, "onClick: "+uid);
                exitFragment.setArguments(bundle);
                Intent intent = new Intent(mContext.getApplicationContext(),exitActivity.class);
                intent.putExtra("dayid",uid);
                mContext.startActivity(intent);

            }
        });
        Picasso.get().load(currentdayvisitor.getPhotoUrl()).into(exitDayHolder.photo);
    }



    @Override
    public int getItemCount() {
        return ExitDayArrayList.size();
    }

    public class ExitDayHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView photo;
        TextView id;
        TextView outtime;
        TextView time;

        public ExitDayHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.namefield);
            photo = itemView.findViewById(R.id.visitorphoto);
            id = itemView.findViewById(R.id.idfield);
            outtime = itemView.findViewById(R.id.outtimefield);
            time = itemView.findViewById(R.id.intimefield);
        }
    }
}
