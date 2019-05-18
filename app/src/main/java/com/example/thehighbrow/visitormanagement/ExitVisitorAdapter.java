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

import org.w3c.dom.Text;

import java.util.ArrayList;

import static android.support.constraint.Constraints.TAG;

public class ExitVisitorAdapter extends RecyclerView.Adapter<ExitVisitorAdapter.ExitVisitorHolder> {
    private ArrayList<Visitor> ExitVisitorArrayList;
    private Context mContext;



    ExitVisitorAdapter(ArrayList<Visitor> exitVisitors, Context context){
        ExitVisitorArrayList = exitVisitors;
        this.mContext=context;
    }


    @NonNull
    @Override
    public ExitVisitorAdapter.ExitVisitorHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        View inflatedView = LayoutInflater.from(context).inflate(R.layout.exit_visitor_row,viewGroup,false);
        ExitVisitorAdapter.ExitVisitorHolder exitVisitorHolder = new ExitVisitorAdapter.ExitVisitorHolder(inflatedView);
        return exitVisitorHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ExitVisitorAdapter.ExitVisitorHolder exitHolder, int i) {

        final Visitor currentdayvisitor = ExitVisitorArrayList.get(i);
        exitHolder.name.setText(currentdayvisitor.getName());
        exitHolder.id.setText(currentdayvisitor.getId());
        exitHolder.outtime.setText(currentdayvisitor.getOuttime());
        exitHolder.intime.setText(currentdayvisitor.getTime());
        exitHolder.date.setText(currentdayvisitor.getDate());
        final String uid = exitHolder.id.getText().toString();

         final String udate = exitHolder.date.getText().toString();


        exitHolder.photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Bundle bundle=new Bundle();
                bundle.putString("id",uid);
                bundle.putString("date",udate);

                Fragment exitFragment = new ExitFragment();

                Log.e(TAG, "onClick: ID and DATE "+uid+udate);

                exitFragment.setArguments(bundle);
                Intent intent = new Intent(mContext.getApplicationContext(),exitActivity.class);
            //    intent.putExtra("id",uid);
              //  intent.putExtra("date",udate);
                intent.putExtras(bundle);
                mContext.startActivity(intent);

            }
        });
        Picasso.get().load(currentdayvisitor.getPhotoUrl()).into(exitHolder.photo);


    }

    @Override
    public int getItemCount() {
        return ExitVisitorArrayList.size();
    }

    public class ExitVisitorHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView photo;
        TextView id;
        TextView outtime;
        TextView intime;
        TextView date;

        public ExitVisitorHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.namefield);
            photo = itemView.findViewById(R.id.visitorphoto);
            id = itemView.findViewById(R.id.idfield);
            outtime = itemView.findViewById(R.id.outtimefield);
            intime = itemView.findViewById(R.id.intimefield);
            date = itemView.findViewById(R.id.datefield);

        }
    }
}
