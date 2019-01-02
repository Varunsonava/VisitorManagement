package com.example.thehighbrow.visitormanagement;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class adminActivity extends AppCompatActivity {

    RecyclerView recyclerView ;
    RecyclerView.Adapter adapter;
    ProgressBar progressBar;
    DatabaseReference databaseVisitor;
    ArrayList<Visitor>visitors;
    String TAG="adminActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        MyViewPagerAdapter myViewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(myViewPagerAdapter);
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);


    }

    class MyViewPagerAdapter extends FragmentPagerAdapter {

        public MyViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Visitors";
                case 1:
                    return "Day Passes";
                case 2:
                    return "91 Lead";
                case 3:
                    return "Vendors";
                case 4:
                    return "Couriers";
                default:
                    return "noTitle";
            }

        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = new MyFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("pos", position);
            fragment.setArguments(bundle);
            return fragment;
        }

        @Override
        public int getCount() {
            return 5;
        }
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(adminActivity.this,MainActivity.class);
        intent.putExtra("Visitor","adminEnd");
        startActivity(intent);
    }

}
