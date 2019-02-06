package com.example.thehighbrow.visitormanagement;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.view.View.VISIBLE;

public class MyFragment extends Fragment {
    RecyclerView recyclerView ;
    RecyclerView.Adapter adapter;
    ProgressBar progressBar;
    DatabaseReference databaseVisitor;
    ArrayList<Visitor> visitors;
    String TAG="adminActivity";
    ArrayList<DayVisitor> dayVisitors;
    ArrayList<Lead> leads;
    ArrayList<Vendor> vendors;
    ArrayList<Courier> couriers;



    @Override
    public void onStart() {
        super.onStart();

    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= null;
        Bundle bundle=getArguments();
        int i = bundle.getInt("pos");
        if (i==0){
            v = inflater.inflate(R.layout.visitor_fragment,container,false);
            databaseVisitor = FirebaseDatabase.getInstance().getReference("visitor");
            recyclerView= v.findViewById(R.id.recyclerView);
            Context context = getContext();
            visitors = new ArrayList<Visitor>();
            Log.e(TAG, "onCreate: above llm");
            LinearLayoutManager mlm = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(mlm);
            mlm.setReverseLayout(true);
            mlm.setStackFromEnd(true);
            adapter=new VisitorAdapter(visitors);
            recyclerView.setAdapter(adapter);
            Log.e(TAG, "onCreate: below llm");


            final View finalV = v;
            databaseVisitor.addValueEventListener(new ValueEventListener() {


                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    progressBar = finalV.findViewById(R.id.progressBar);
                    progressBar.setVisibility(VISIBLE);


                    for (DataSnapshot visitorSnapshot : dataSnapshot.getChildren()) {
                        Log.e(TAG, "onDataChange: adding visitor to visitors");
                        Visitor visitor = visitorSnapshot.getValue(Visitor.class);
                        visitors.add(visitor);
                        adapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);

                        Log.e("MainActivity", "onDataChange: added visitor to visitors");

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getActivity(), "Problem fetching databse", Toast.LENGTH_SHORT).show();

                }
            });


        }
        else if (i==1){

            v = inflater.inflate(R.layout.visitor_fragment,container,false);
            databaseVisitor = FirebaseDatabase.getInstance().getReference("dayVisitor");
            recyclerView= v.findViewById(R.id.recyclerView);
            Context context = getContext();

            dayVisitors = new ArrayList<DayVisitor>();

            Log.e(TAG, "onCreate: above llm");
            LinearLayoutManager mlm = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(mlm);
            mlm.setReverseLayout(true);
            mlm.setStackFromEnd(true);

            adapter = new DayAdapter(dayVisitors);

            recyclerView.setAdapter(adapter);
            Log.e(TAG, "onCreate: below llm");


            final View finalV = v;
            databaseVisitor.addValueEventListener(new ValueEventListener() {


                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    progressBar = finalV.findViewById(R.id.progressBar);
                    progressBar.setVisibility(VISIBLE);


                    for (DataSnapshot visitorSnapshot : dataSnapshot.getChildren()) {
                        Log.e(TAG, "onDataChange: adding visitor to visitors");

                        DayVisitor dayVisitor = visitorSnapshot.getValue(DayVisitor.class);
                        dayVisitors.add(dayVisitor);

                        adapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);

                        Log.e("MainActivity", "onDataChange: added visitor to visitors");

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getActivity(), "Problem fetching databse", Toast.LENGTH_SHORT).show();

                }
            });



        }
        else if (i==2){


            v = inflater.inflate(R.layout.visitor_fragment,container,false);
            databaseVisitor = FirebaseDatabase.getInstance().getReference("91lead");
            recyclerView= v.findViewById(R.id.recyclerView);
            Context context = getContext();

            leads = new ArrayList<Lead>();

            Log.e(TAG, "onCreate: above llm");
            LinearLayoutManager mlm = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(mlm);
            mlm.setReverseLayout(true);
            mlm.setStackFromEnd(true);
            MyFragment myFragment ;
            adapter = new LeadAdapter(leads);

            recyclerView.setAdapter(adapter);
            Log.e(TAG, "onCreate: below llm");


            final View finalV = v;
            databaseVisitor.addValueEventListener(new ValueEventListener() {


                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    progressBar = finalV.findViewById(R.id.progressBar);
                    progressBar.setVisibility(VISIBLE);


                    for (DataSnapshot visitorSnapshot : dataSnapshot.getChildren()) {
                        Log.e(TAG, "onDataChange: adding visitor to visitors");

                        Lead lead = visitorSnapshot.getValue(Lead.class);
                        leads.add(lead);

                        adapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);

                        Log.e("MainActivity", "onDataChange: added visitor to visitors");

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getActivity(), "Problem fetching databse", Toast.LENGTH_SHORT).show();

                }
            });

        }
        else if (i==3){

            v = inflater.inflate(R.layout.visitor_fragment,container,false);
            databaseVisitor = FirebaseDatabase.getInstance().getReference("vendor");
            recyclerView= v.findViewById(R.id.recyclerView);
            Context context = getContext();

            vendors = new ArrayList<Vendor>();

            Log.e(TAG, "onCreate: above llm");
            LinearLayoutManager mlm = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(mlm);
            mlm.setReverseLayout(true);
            mlm.setStackFromEnd(true);
            MyFragment myFragment ;

            adapter = new VendorAdapter(vendors);

            recyclerView.setAdapter(adapter);
            Log.e(TAG, "onCreate: below llm");


            final View finalV = v;
            databaseVisitor.addValueEventListener(new ValueEventListener() {


                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    progressBar = finalV.findViewById(R.id.progressBar);
                    progressBar.setVisibility(VISIBLE);


                    for (DataSnapshot visitorSnapshot : dataSnapshot.getChildren()) {
                        Log.e(TAG, "onDataChange: adding visitor to visitors");

                        Vendor vendor = visitorSnapshot.getValue(Vendor.class);
                        vendors.add(vendor);

                        adapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);

                        Log.e("MainActivity", "onDataChange: added visitor to visitors");

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getActivity(), "Problem fetching databse", Toast.LENGTH_SHORT).show();

                }
            });

        }
        else if (i==4){

            v = inflater.inflate(R.layout.visitor_fragment,container,false);
            databaseVisitor = FirebaseDatabase.getInstance().getReference("courier");
            recyclerView= v.findViewById(R.id.recyclerView);
            Context context = getContext();

            couriers = new ArrayList<Courier>();

            Log.e(TAG, "onCreate: above llm");
            LinearLayoutManager mlm = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(mlm);
            mlm.setReverseLayout(true);
            mlm.setStackFromEnd(true);
            MyFragment myFragment ;

            adapter = new CourierAdapter(couriers);

            recyclerView.setAdapter(adapter);
            Log.e(TAG, "onCreate: below llm");


            final View finalV = v;
            databaseVisitor.addValueEventListener(new ValueEventListener() {


                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    progressBar = finalV.findViewById(R.id.progressBar);
                    progressBar.setVisibility(VISIBLE);


                    for (DataSnapshot visitorSnapshot : dataSnapshot.getChildren()) {
                        Log.e(TAG, "onDataChange: adding visitor to visitors");

                        Courier courier = visitorSnapshot.getValue(Courier.class);
                        couriers.add(courier);

                        adapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);

                        Log.e("MainActivity", "onDataChange: added visitor to visitors");

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getActivity(), "Problem fetching databse", Toast.LENGTH_SHORT).show();

                }
            });


        }
        return v;

    }

}
