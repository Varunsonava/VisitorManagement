package com.example.thehighbrow.visitormanagement;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
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

import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class vendorDetail extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    EditText name;
    EditText contact;
    String host;
    Spinner spinner;
    DatabaseReference databaseVisitor;
    TextView submit;
    Calendar calendar;
    String photoUrl;
    private ImageButton muploadbtn;
    private ImageView mImageView;
    String path;
    String vurl;
    private static final int CAMERA_REQUEST_CODE = 1;
    private StorageReference mStorage;
    private ProgressDialog mProgress;
    String downloadUrl;
    String formattedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_detail);

        mStorage = FirebaseStorage.getInstance().getReference();


        spinner = (Spinner) findViewById(R.id.spinner);
        name=findViewById(R.id.namefield);
        contact=findViewById(R.id.contactfield);

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        formattedDate = dateFormat.format(c);
        databaseVisitor = FirebaseDatabase.getInstance().getReference("Noida Sec1/"+formattedDate+"/vendor");

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA_REQUEST_CODE);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.spinner_options, android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(vendorDetail.this);

        submit=findViewById(R.id.proceedBtn);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addvendor();
            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {

            Context inContext = vendorDetail.this;
            Bitmap btmp = (Bitmap) data.getExtras().get("data");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            btmp.compress(Bitmap.CompressFormat.JPEG, 0, baos);
            byte[] idata = baos.toByteArray();

            path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), btmp, "Title", null);
            final Uri uri = Uri.parse(path);
//            Picasso.get().load(uri).into(photo);
            final StorageReference filepath = mStorage.child("photos").child(uri.getLastPathSegment());
            final StorageTask<UploadTask.TaskSnapshot> taskSnapshotStorageTask = filepath.putFile(uri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    vurl = String.valueOf(uri);

                                    Toast.makeText(vendorDetail.this, "Photo Upload Complete", Toast.LENGTH_SHORT).show();

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {

                                }
                            });



                        }
                    });
        }
    }


    private void addvendor()
    {
        String dname = name.getText().toString().trim();
        String dcontact = contact.getText().toString().trim();

        if (!TextUtils.isEmpty(dname) && !TextUtils.isEmpty(dcontact))
        {
            String id = databaseVisitor.push().getKey();

            Date d=new Date();
            SimpleDateFormat sdf=new SimpleDateFormat("hh:mm a");
            String currentDateTimeString = sdf.format(d);

            String Date = DateFormat.getDateInstance(DateFormat.DATE_FIELD).format(new Date()).toString();
            Log.e("visitorDetail", "addVisitor: TIME = "+currentDateTimeString+"DATE = "+Date);
//            Visitor visitor = new Visitor(dname,dcontact,dhost,photoUrl);
            Vendor vendor = new Vendor(dname,dcontact,host,vurl,currentDateTimeString, formattedDate,id,"");

//            databaseVisitor.child(id).setValue(visitor);
            databaseVisitor.child(id).setValue(vendor);

            Toast.makeText(this, "Vendor Registered", Toast.LENGTH_SHORT).show();
            Intent intent= new Intent(vendorDetail.this,Welcome.class);
            intent.putExtra("vname",dname);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this, "All Fields Mandatory", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        TextView spinnerDialogText = (TextView) view;
        host = spinnerDialogText.getText().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}