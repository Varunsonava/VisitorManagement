package com.example.thehighbrow.visitormanagement;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import static android.view.View.VISIBLE;

public class ExitFragment extends Fragment {

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
    String uid;
    String dayid;
    String leadid;
    String vendorid;






    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= null;
        Bundle bundle=this.getArguments();
        int i = bundle.getInt("pos");
        Log.e(TAG, "onCreateView: "+ i);
        String a = this.getArguments().getString("id");
        Log.e(TAG, "onCreateView: "+a);
        if (i==0){
            v = inflater.inflate(R.layout.exit_fragment,container,false);
            FirebaseDatabase.getInstance().getReference("visitor");
            Log.e(TAG, "onCreateView: ");

           /* if (FirebaseDatabase.getInstance().getReference("visitor").getKey()){
                Log.e(TAG, "onCreateView: entered if");
            }*/
            databaseVisitor = FirebaseDatabase.getInstance().getReference("visitor");
            recyclerView= v.findViewById(R.id.recyclerView);
            final Context context = getContext();
            visitors = new ArrayList<Visitor>();
            Log.e(TAG, "onCreate: above llm");
            LinearLayoutManager mlm = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(mlm);
            mlm.setReverseLayout(true);
            mlm.setStackFromEnd(true);

            adapter=new ExitVisitorAdapter(visitors,context);



          /*  recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
                @Override
                public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
                    return false;
                }

                @Override
                public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {

                }

                @Override
                public void onRequestDisallowInterceptTouchEvent(boolean b) {

                }
            });*/
            uid = getArguments().getString("id");
            Log.e(TAG, "onCreateView: received "+uid);

            recyclerView.addOnItemTouchListener(new MyTouchListener(getContext(), recyclerView, new MyTouchListener.OnTouchActionListener() {
                @Override
                public void onLeftSwipe(View view, int position) {

                }

                @Override
                public void onRightSwipe(View view, int position) {
                    Log.e(TAG, "onClick: r swiped");

                }

                @Override
                public void onClick(View view, int position) {
                    Log.e(TAG, "onClick: clicked"+uid);


                }

                @Override
                public void onLongClick(View view, int position) {
                    Log.e(TAG, "onClick:long clicked");
                    Date d=new Date();
                    SimpleDateFormat sdf=new SimpleDateFormat("hh:mm a");
                    String currentDateTimeString = sdf.format(d);
                   // String s = getActivity().getIntent().getExtras().getString("uid");
                    if (uid!=null) {
                        Log.e(TAG, "onLongClick: id received in fragment " + uid);
                        Visitor visitor = new Visitor();
                        visitor.setOuttime(currentDateTimeString);
                        databaseVisitor.child(uid).child("outtime").setValue(currentDateTimeString);
                    }
                    else
                    {
                        Toast.makeText(context,"CLICK ON THE PHOTO FIRST",Toast.LENGTH_LONG);
                    }

                }
            }));

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
                        Visitor visitor = (Visitor) visitorSnapshot.getValue(Visitor.class);

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

            v = inflater.inflate(R.layout.exit_fragment,container,false);
            databaseVisitor = FirebaseDatabase.getInstance().getReference("dayVisitor");
            recyclerView= v.findViewById(R.id.recyclerView);
            final Context context = getContext();

            dayVisitors = new ArrayList<DayVisitor>();

            Log.e(TAG, "onCreate: above llm");
            LinearLayoutManager mlm = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(mlm);
            mlm.setReverseLayout(true);
            mlm.setStackFromEnd(true);

            adapter = new ExitDayAdapter(dayVisitors,context);

            dayid = getArguments().getString("dayid");

            recyclerView.addOnItemTouchListener(new MyTouchListener(getContext(), recyclerView, new MyTouchListener.OnTouchActionListener() {
                @Override
                public void onLeftSwipe(View view, int position) {

                }

                @Override
                public void onRightSwipe(View view, int position) {
                    Log.e(TAG, "onClick: r swiped");

                }

                @Override
                public void onClick(View view, int position) {
                    Log.e(TAG, "onClick: clicked");

                }

                @Override
                public void onLongClick(View view, int position) {
                    Log.e(TAG, "onClick:long clicked"+dayid);
                    Date d=new Date();
                    SimpleDateFormat sdf=new SimpleDateFormat("hh:mm a");
                    String currentDateTimeString = sdf.format(d);
                    // String s = getActivity().getIntent().getExtras().getString("uid");
                    if (dayid!=null) {
                        Log.e(TAG, "onLongClick: id received in fragment " + dayid);
                        Visitor visitor = new Visitor();
                        visitor.setOuttime(currentDateTimeString);
                        databaseVisitor.child(dayid).child("outtime").setValue(currentDateTimeString);
                    }
                    else
                    {
                        Toast.makeText(context,"CLICK ON THE PHOTO FIRST",Toast.LENGTH_LONG);
                    }


                }
            }));

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


            v = inflater.inflate(R.layout.exit_fragment,container,false);
            databaseVisitor = FirebaseDatabase.getInstance().getReference("91lead");
            recyclerView= v.findViewById(R.id.recyclerView);
            final Context context = getContext();

            leads = new ArrayList<Lead>();

            Log.e(TAG, "onCreate: above llm");
            LinearLayoutManager mlm = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(mlm);
            mlm.setReverseLayout(true);
            mlm.setStackFromEnd(true);
            MyFragment myFragment ;
            adapter = new ExitLeadAdapter(leads, context);

            leadid = getArguments().getString("leadid");

            recyclerView.addOnItemTouchListener(new MyTouchListener(getContext(), recyclerView, new MyTouchListener.OnTouchActionListener() {
                @Override
                public void onLeftSwipe(View view, int position) {

                }

                @Override
                public void onRightSwipe(View view, int position) {
                    Log.e(TAG, "onClick: r swiped");

                }

                @Override
                public void onClick(View view, int position) {
                    Log.e(TAG, "onClick: clicked");

                }

                @Override
                public void onLongClick(View view, int position) {
                    Log.e(TAG, "onClick:long clicked");
                    Date d=new Date();
                    SimpleDateFormat sdf=new SimpleDateFormat("hh:mm a");
                    String currentDateTimeString = sdf.format(d);
                    // String s = getActivity().getIntent().getExtras().getString("uid");
                    if (leadid!=null) {
                        Log.e(TAG, "onLongClick: id received in fragment " + leadid);
                        //Visitor visitor = new Visitor();
                        //visitor.setOuttime(currentDateTimeString);
                        Lead lead = new Lead();
                        lead.setOuttime(currentDateTimeString);
                        databaseVisitor.child(leadid).child("outtime").setValue(currentDateTimeString);
                    }
                    else
                    {
                        Toast.makeText(context,"CLICK ON THE PHOTO FIRST",Toast.LENGTH_LONG);
                    }


                }
            }));

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

            v = inflater.inflate(R.layout.exit_fragment,container,false);
            databaseVisitor = FirebaseDatabase.getInstance().getReference("vendor");
            recyclerView= v.findViewById(R.id.recyclerView);
            final Context context = getContext();

            vendors = new ArrayList<Vendor>();

            Log.e(TAG, "onCreate: above llm");
            LinearLayoutManager mlm = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(mlm);
            mlm.setReverseLayout(true);
            mlm.setStackFromEnd(true);
            MyFragment myFragment ;

            adapter = new ExitVendorAdapter(vendors, context);

            vendorid = getArguments().getString("vendorid");

            recyclerView.addOnItemTouchListener(new MyTouchListener(getContext(), recyclerView, new MyTouchListener.OnTouchActionListener() {
                @Override
                public void onLeftSwipe(View view, int position) {

                }

                @Override
                public void onRightSwipe(View view, int position) {
                    Log.e(TAG, "onClick: r swiped");

                }

                @Override
                public void onClick(View view, int position) {
                    Log.e(TAG, "onClick: clicked");

                }

                @Override
                public void onLongClick(View view, int position) {
                    Log.e(TAG, "onClick:long clicked");
                    Date d=new Date();
                    SimpleDateFormat sdf=new SimpleDateFormat("hh:mm a");
                    String currentDateTimeString = sdf.format(d);
                    // String s = getActivity().getIntent().getExtras().getString("uid");
                    if (vendorid!=null) {
                        Log.e(TAG, "onLongClick: id received in fragment " + vendorid);
                        //Visitor visitor = new Visitor();
                        //visitor.setOuttime(currentDateTimeString);
                        Vendor vendor = new Vendor();
                        vendor.setOuttime(currentDateTimeString);
                        databaseVisitor.child(vendorid).child("outtime").setValue(currentDateTimeString);
                    }
                    else
                    {
                        Toast.makeText(context,"CLICK ON THE PHOTO FIRST",Toast.LENGTH_LONG);
                        Log.e(TAG, "onLongClick: empty id");
                    }


                }
            }));

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
        /*else if (i==4){

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
            final Context context1 = getContext();
            MyFragment myFragment ;

            adapter = new CourierAdapter(couriers);
            recyclerView.addOnItemTouchListener(new MyTouchListener(getContext(), recyclerView, new MyTouchListener.OnTouchActionListener() {
                @Override
                public void onLeftSwipe(View view, int position) {

                }

                @Override
                public void onRightSwipe(View view, int position) {
                    Log.e(TAG, "onClick: r swiped");

                }

                @Override
                public void onClick(View view, int position) {
                    Log.e(TAG, "onClick: clicked");

                }

                @Override
                public void onLongClick(View view, int position) {
                    Log.e(TAG, "onClick:long clicked");


                }
            }));

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


        }*/
        return v;

    }

}
