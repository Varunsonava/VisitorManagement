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
    EditText autoAlternate;
    EditText companions;

    EditText host;
    DatabaseReference databaseVisitor;
    TextView submit;
    AutoCompleteTextView vhost;

    Calendar calendar;
    String photoUrl;
    String[] hostCompanies={"spb",
            "Aarvee Idealab Technologies Private Limited",
            "Abhishek Chopra",
            "Abhishek Patel ",
            "Achievers Enterprise",
            "Adezi Ventures LLP",
             "Adotka Smart Business Solutions Private Limited",
             "Aero Solutions",
             "AffiliateIndian",
             "AI HCM Services LLP",
              "Akshmohan Singh Photography ",
             "Amiger Solutions Pvt Ltd",
              "AMIT GUPTA ",
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
            "DC Web Services Pvt Ltd",
            "Deepayan Nandi",
            "Depth Infotech Pvt. Ltd.",
            "DesignOmate",
            "Devdatt Kushwaha",
            "DGTG Marketing & Retail Pvt. Ltd.",
            "Dietician For Health LLP",
            "Digibiz Infocom Pvt. Ltd.",
            "Digital Web Creativity LLP",
            "DNA Real Estate Private Limited",
            "Dr Karuna Batra",
    "DreamHii Creatives",
    "Edvanta Technologies Private Limited",
    "Eeziklik India Private Limited",
    "Enterprise System Solutions Pvt Ltd",
    "Envision Human Talent Consulting",
    "EnziMedia",
    "Equentia SCF Technologies Pvt Ltd",
    "Erudite Software and Learning Solutions Pvt. Ltd.",
    "Ethical Trading Initiative",
    "Evoke Inspiring Lives",
    "Evolving Edutainment Private Limited",
    "Falcon Minds Consulting Pvt. Ltd.",
    "Falcon Minds Consulting Pvt. Ltd.",
    "Finnovate Technology solutions Private ltd",
            "FlyHomes",
    "Fossbytes Media Private Limited",
    "Freshmint Foodworks & Services LLP",
    "Full Art Enterprises",
    "Gaea Digital Private Limited",
    "Geekslab Technologies Private Limited",
    "GoProducts Engineering India LLP",
    "GRANDAGE SERVICES PVT LTD",
    "Greenvent Acoustic Private Limited",
    "Greenvent Acoustic Private Limited",
    "HR Access",
    "Hurrsh Kaoul",
    "Ibuilthome",
    "Icarus Entertainment Private Limited",
    "idisha info labs pvt ltd",
    "Importal Technologies ITC Pvt. Ltd",
    "INDOBARODA SERVICEMART PVT LTD.",
    "INFIUS SYNERGY",
    "INSTALOCATE TECHNOLOGIES PVT LTD.",
    "INSTALOCATE TECHNOLOGIES PVT LTD.",
    "Intermesh Shopping Network Private Limited",
    "Iotech Designs Pvt. Ltd",
    "Ityukta Consultancy Services Pvt. Ltd.",
    "Jinigram Consulting Services Pvt Ltd",
    "JobsForHer Restart Portal Private Limited",
    "Kartik Arora",
    "LANISTA EDUCATIONAL SERVICES OPC PRIVATE LIMITED",
    "M.B.Technologies",
    "M/s Zeblok Technologies Private Limited",
    "Magus Services",
    "Manik IT & Infrastructure Services Private Limited",
    "Manik IT & Infrastructure Services Private Limited",
    "Marsh India Insurance Broker Pvt Ltd",
    "Marsh India Insurance Broker Pvt Ltd",
    "Matri Dua ",
    "Max Eleven",
    "Micro Incept Technologies Pvt Ltd",
    "Mindful Machine Pvt Ltd",
    "Mintware Ventures Private Limited",
    "Mintware Ventures Private Limited",
    "MLAI Technologies",
    "Mlinots Technologies PVT. Ltd.",
    "Mlinots Technologies PVT. Ltd.",
    "Mlinots Technologies PVT. Ltd.",
            "MLIT-18 Technology Private Limited",
    "Mode Street",
    "MomentSnap India Pvt. Ltd.",
    "mTalkz Mobility Service (P) Limited",
    "N M JHANWAR & ASSOCIATES",
    "Nationwide Solutions",
    "Neo SoftwareLabs Pvt. Ltd.",
    "Network Bulls Technologies Pvt Ltd",
    "odyssey Informatics (manuj@odysseymt.com)",
    "OLX India Private Limited",
    "ONE97 COMMUNICATIONS LIMITED",
    "OneNative Advertising pvt. Ltd.",
    "Onlybook Educational Technologies Private Limited",
    "OTREMA SPRL",
    "PAFMA MEGA SPECTRUM PVT LTD",
    "Passionate Discoveries (A unit of CoConnect)",
    "PDGT FRAMEWORKS PRIVATE LIMITED",
    "Pingzee Technologies Private Limited",
    "PINNAKL Edge Systems Private Limited",
    "Platinum One Learning Solutions Private Limited",
    "Pooja Nath (poojanath24@gmail.com)",
    "PranisCOM Cargo-Zippers",
    "Prashaant Sethi (psethi666@yahoo.co.in)",
    "Provenience Consulting Pvt Ltd",
    "QBF Media Private Limited",
    "Quetzal Online Private Limited",
    "Quokka Labs",
    "RDK PAYROLL Solutions",
    "Reimagine Tech Pvt Ltd",
    "Riddhi Corporate Services Pvt Ltd",
    "Rohit Gupta (hellorohit@gmail.com)",
    "RSVR Technologies Private Limited",
    "Samvath Fashion International LLP",
    "Sanjay Khurana (sanjaykhurana@hotmail.com)",
    "SAS India Collection LLP",
    "Scinnovation Consultants Pvt. Ltd.",
    "Scripting Solutions Pvt Ltd",
    "Sercomm Corporation",
    "Shelves Tech Private Limited",
    "Sia Trading Company",
    "SK Tuli Group Limited",
    "SKAJN Marketing Technologies Private Limited",
            "skidos",
    "Skrilo India Private LTD.",
    "SOLUCIONVALLEY IT SOLUTIONS OPC PVT LTD",
    "SPINOFF DIGITAL INDIA",
    "staffingine Technologies Pvt. Ltd",
    "StoreMore Storage Solutions Private Limited",
            "STREETHACK",
    "Sturdy Mouse",
    "Sugam Goel ",
    "sumit pal singh ",
    "Super Sports & Projects Pvt Ltd",
    "SWS Technologies Private Limited",
    "Tarun Harish Kumar & Co.",
    "Technologics and Controls (sa@tech-controls.com)",
    "Testxperts Infotech OPC Pvt. Ltd.",
    "Testxperts Infotech OPC Pvt. Ltd.",
    "The Perfect 1/One Search",
    "The Thinking Birds",
    "Think Money Services Pvt Ltd",
    "Tolexo Online Private Limited",
    "Transerv Private Limited",
    "Trigain Technologies Pvt Ltd",
    "Unifii-Now Technology Consulting Private Limited",
    "Unitalks Technologies",
    "Unreal AI Technologies Pvt. Ltd.",
            "Urbancrafts",
    "Vaishali Jain ",
    "Vasim Khan ",
    "VentureWeb Marketing Solutions India Private Limited",
    "Verito Technologies Pvt. Ltd.",
    "Verito Technologies Pvt. Ltd.",
    "Vernacular Consultancy Services Pvt. Ltd.",
            "Vikas Bagri",
    "Vinayak Solutions Pvt. Ltd",
    "Vinesh Sinhmar ",
    "Viragram Media",
    "Visawalk Consultancy Private Limited",
    "Vivaconnect Private Limited",
    "Vivish Technologies Private Limited",
    "VLSI Expert Private Limited",
    "VPREP EDUKRAFT PVT LTD",
    "Wesence Solutions Private Limited",
    "Witoni Enterprises (support@witoni.com)",
    "Wizquest Labs Pvt. Ltd.",
            "Xilytica/ Decifer",
    "XPLORYO TECH & SOLUTIONS PRIVATE LIMITED",
    "Xtreme Jaw (kushagrakalra7@gmail.com)",
    "Yash Compostables Limited",
            "ZenoChat",
    "Zetfly",
    "Zonestra Services"
};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitordetail);

        Intent intent = getIntent();
        photoUrl = intent.getStringExtra("photourl");
        Log.e(TAG, "onCreate: photo url = "+photoUrl);
        autoAlternate = findViewById(R.id.autoCompleteAlternate);
        name=findViewById(R.id.namefield);
        contact=findViewById(R.id.contactfield);
        vhost = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        companions = findViewById(R.id.companionfield);

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
    {   String valternate = autoAlternate.getText().toString().trim();
        String vname = name.getText().toString().trim();
        String vcontact = contact.getText().toString().trim();
        String vcompanions = companions.getText().toString().trim();
        if (vcompanions.equals(""))vcompanions="0";

/*
        String vhost = host.getText().toString().trim();
*/

        if (!TextUtils.isEmpty(vname) && !TextUtils.isEmpty(vcontact) && (!TextUtils.isEmpty(ahost)||!TextUtils.isEmpty(valternate)) )
            {
            String id = databaseVisitor.push().getKey();


                Date d=new Date();
                SimpleDateFormat sdf=new SimpleDateFormat("hh:mm a");
                String currentDateTimeString = sdf.format(d);

                String Date = DateFormat.getDateInstance(DateFormat.DATE_FIELD).format(new Date());
                Log.e("visitorDetail", "addVisitor: TIME = "+currentDateTimeString+"DATE = "+Date);

            Visitor visitor = new Visitor(vname,vcontact,ahost,photoUrl,currentDateTimeString,"",Date,vcompanions,id);


                databaseVisitor.child(id).setValue(visitor);


            Toast.makeText(this, "Visitor Registered", Toast.LENGTH_SHORT).show();
            Intent intent= new Intent(visitorDetail.this,Welcome.class);
            intent.putExtra("vname",vname);
            intent.putExtra("id",id);
            startActivity(intent);
            }
        else
            {
                if (contact.getText().toString().trim().equalsIgnoreCase("")) {
                    contact.setError("This field can not be blank");
                }
                if (name.getText().toString().trim().equalsIgnoreCase("")) {
                    name.setError("This field can not be blank");
                }


            Toast.makeText(this, "Kindly Fill the Mandatory Fields ", Toast.LENGTH_LONG).show();
            }
    }
}