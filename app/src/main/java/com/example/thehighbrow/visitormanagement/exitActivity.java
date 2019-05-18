package com.example.thehighbrow.visitormanagement;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class exitActivity extends AppCompatActivity {

    RecyclerView recyclerView ;
    RecyclerView.Adapter adapter;
    ProgressBar progressBar;
    DatabaseReference databaseVisitor;
    ArrayList<Visitor> visitors;
    String TAG="exitActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exit);

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
               /* case 4:
                    return "Couriers";*/
                default:
                    return "noTitle";
            }

        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = new ExitFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("pos", position);
            fragment.setArguments(bundle);
            Intent i= getIntent();

            String uid = i.getStringExtra("id");
            String udate = i.getStringExtra("date");
            bundle.putString("id",uid);
            bundle.putString("date",udate);

            String dayid = i.getStringExtra("dayid");
            String daydate = i.getStringExtra("daydate");
            bundle.putString("daydate",daydate);
            bundle.putString("dayid",dayid);

            String leadid = i.getStringExtra("leadid");
            String leaddate = i.getStringExtra("leaddate");
            bundle.putString("leadid",leadid);
            bundle.putString("leaddate",leaddate);


            String vendorid = i.getStringExtra("vendorid");
            String vendordate = i.getStringExtra("vendordate");
            bundle.putString("vendorid",vendorid);
            bundle.putString("vendordate",vendordate);


            return fragment;

        }

        @Override
        public int getCount() {
            return 4;
        }
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(exitActivity.this,GuestType.class);
        startActivity(intent);
    }
}
