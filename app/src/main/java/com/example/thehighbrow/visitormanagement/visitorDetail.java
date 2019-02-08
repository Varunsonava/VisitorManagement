package com.example.thehighbrow.visitormanagement;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class visitorDetail extends AppCompatActivity {

    private static final String TAG = "visitordetail";
    EditText name;
    EditText contact;
    String ahost;
    EditText autoAlternate;
    String Date;
    URL url;
    EditText companions;
    BufferedReader bufferedReader;
    private ImageButton muploadbtn;
    private ImageView mImageView;
    String durl;
    String path;
    StorageReference filepath;
    String[] tokens;

    private static final int CAMERA_REQUEST_CODE=1;
    private StorageReference mStorage;
    private ProgressDialog mProgress;
    String downloadUrl;
    EditText host;
    DatabaseReference databaseVisitor;
    TextView submit;
    AutoCompleteTextView vhost;

    Calendar calendar;
    String photoUrl;


    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitordetail);
        mStorage= FirebaseStorage.getInstance().getReference();
        
        readCompanies();
        
        /*Intent intent = getIntent();
        photoUrl = intent.getStringExtra("photourl");*/
        Log.e(TAG, "onCreate: photo url = "+photoUrl);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,CAMERA_REQUEST_CODE);
        autoAlternate = findViewById(R.id.autoCompleteAlternate);
        name=findViewById(R.id.namefield);
        contact=findViewById(R.id.contactfield);
        vhost = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        companions = findViewById(R.id.companionfield);

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = dateFormat.format(c);

        databaseVisitor = FirebaseDatabase.getInstance().getReference("Noida Sec1/"+formattedDate+"/visitor");

        ArrayAdapter<Companies> adapter = new ArrayAdapter<>
                (this, android.R.layout.select_dialog_item,companiesList  );

        // (this, android.R.layout.select_dialog_item, hostCompanies);
        vhost.setThreshold(1);//will start working from first character
        Log.e(TAG, "onCreate: tokens beiing set is : "+tokens);
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



    private List<Companies> companiesList= new ArrayList<>();
    private void readCompanies() {


        //InputStream is = getResources().openRawResource(R.raw.report_team_plan_counts3);
       // BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
        

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // this url was generated by Publishing the csv file via Google Sheets.The original file is uploaded on my drive
                    url = new URL("https://docs.google.com/spreadsheets/d/e/2PACX-1vTFtyFBJEp6-ZPWb1sVOnyt1QidT1td98a-DAARwYQp0caRUAbXhb4mSGLiP09trLwtyFfjt9FZwvIw/pub?gid=1191315906&single=true&output=csv");

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                try{
                    bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));

                }catch (IOException e){
                    e.printStackTrace();
                }
                String line = "";
                try {
                    //step over headers
                    bufferedReader.readLine();
                    while ((line = bufferedReader.readLine()) != null) {
                        //splitting by ,
                        tokens = line.split(",");
                        //reading data
                        Companies companies = new Companies();
                        companies.setName(tokens[0]);
            /*    if (tokens[1].length()>0) {
                    companies.setEmail(tokens[1]);
                }
                else{
                    companies.setEmail("");
                }
*/
                        companiesList.add(companies);

                        Log.e(TAG, "readCompanies: Created - "+companies);
                    }

                }catch(IOException e){
                    Log.e(TAG, "readCompanies: errror reading file "+line);
                    e.printStackTrace();

                }

            }
        });
        thread.start();

    }
       // Companies companies;



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==CAMERA_REQUEST_CODE&&resultCode==RESULT_OK){

            Log.e(TAG, "onActivityResult: entered into function");
            //   Uri uri  = data.getData();
            Context inContext = visitorDetail.this;
            Bitmap btmp = (Bitmap)data.getExtras().get("data");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            btmp.compress(Bitmap.CompressFormat.JPEG,0,baos);
            byte[] idata=baos.toByteArray();

            Log.e(TAG, "onActivityResult: 1");

            path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), btmp, "Title", null);
            final Uri uri = Uri.parse(path);
            filepath = mStorage.child("photos").child(uri.getLastPathSegment());

            Log.e(TAG, "onActivityResult: 2");

            final StorageTask<UploadTask.TaskSnapshot> taskSnapshotStorageTask = filepath.putFile(uri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            Log.e(TAG, "onSuccess: 3");

                            filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    Log.e(TAG, "onSuccess: ");
                                    //downloadUrl = mStorage.getDownloadUrl().toString();
                                    durl = String.valueOf(uri);
                                    Log.e("checking for dwnld URL", "onSuccess: "+durl);

                                    Toast.makeText(visitorDetail.this, "Photo Upload Complete", Toast.LENGTH_SHORT).show();

                                    Log.e(TAG, "onSuccess: seding this url = " + durl);

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.e(TAG, "onFailure: ");
                                }
                            });


                        }
                    });
        }
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

                Date = DateFormat.getDateInstance(DateFormat.DATE_FIELD).format(new Date());
                Log.e("visitorDetail", "addVisitor: TIME = "+currentDateTimeString+"DATE = "+Date);

            Visitor visitor = new Visitor(vname,vcontact,ahost,durl,currentDateTimeString,"",Date,vcompanions,id);


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