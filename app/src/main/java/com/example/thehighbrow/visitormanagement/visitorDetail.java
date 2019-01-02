package com.example.thehighbrow.visitormanagement;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class visitorDetail extends AppCompatActivity {

    private static final String TAG = "visitordetail";
    EditText name;
    EditText contact;
    String ahost;

    EditText host;
    DatabaseReference databaseVisitor;
    TextView submit;
    AutoCompleteTextView vhost;

    Calendar calendar;
    String photoUrl;
    String[] hostCompanies={"spb",
            "Aarvee Idealab Technologies Private Limited",
            "Abhishek Chopra",
            "Abhishek Patel (patel.abhishek@yahoo.co.in)",
            "Achievers Enterprise",
            "Adezi Ventures LLP",
             "Adotka Smart Business Solutions Private Limited",
             "Aero Solutions",
             "AffiliateIndian",
             "AI HCM Services LLP",
              "Akshmohan Singh Photography (akshmohan@gmail.com)",
             "Amiger Solutions Pvt Ltd",
              "AMIT GUPTA (ca.agupta@gmail.com)",
              "APICE ENGINEERING AND CONSULTING PRIVATE LIMITED",
              "Archiz Solutions",
               "Arinsys Enterprise Solutions Private Limited",
              "Bizztouch Trading Pvt Ltd",
             "Blued City Holdings",
             "Casper Antivirus Inc.",
            "Code Ninjas",
            "Codinova Technologies Pvt Ltd",
            "Coherent Advisors Private Limited",
            "ConnectCue Services Pvt Ltd",
            "Consilat Consulting Services",
            "Cortar Technologies Private Limited",
            "Daily Bites",

};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitordetail);

        Intent intent = getIntent();
        photoUrl = intent.getStringExtra("photourl");
        Log.e(TAG, "onCreate: photo url = "+photoUrl);

        name=findViewById(R.id.namefield);
        contact=findViewById(R.id.contactfield);
        vhost = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);

        databaseVisitor = FirebaseDatabase.getInstance().getReference("visitor");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.select_dialog_item, hostCompanies);
        vhost.setThreshold(1);//will start working from first character
        vhost.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
        vhost.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ahost=parent.getItemAtPosition(position).toString();
            }
        });
        ahost = vhost.getText().toString();
        submit=findViewById(R.id.proceedBtn);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addVisitor();
            }
        });

    }





    private void addVisitor()
    {
        String vname = name.getText().toString().trim();
        String vcontact = contact.getText().toString().trim();
/*
        String vhost = host.getText().toString().trim();
*/

        if (!TextUtils.isEmpty(vname) && !TextUtils.isEmpty(vcontact) )
            {
            String id = databaseVisitor.push().getKey();

                Date d=new Date();
                SimpleDateFormat sdf=new SimpleDateFormat("hh:mm a");
                String currentDateTimeString = sdf.format(d);

                String Date = DateFormat.getDateInstance(DateFormat.DATE_FIELD).format(new Date());
                Log.e("visitorDetail", "addVisitor: TIME = "+currentDateTimeString+"DATE = "+Date);

            Visitor visitor = new Visitor(vname,vcontact,ahost,photoUrl,currentDateTimeString,Date);


                databaseVisitor.child(id).setValue(visitor);


            Toast.makeText(this, "Visitor Registered", Toast.LENGTH_SHORT).show();
            Intent intent= new Intent(visitorDetail.this,Welcome.class);
            intent.putExtra("vname",vname);
            startActivity(intent);
            }
        else
            {
            Toast.makeText(this, "All Fields Mandatory", Toast.LENGTH_LONG).show();
            }
    }
}