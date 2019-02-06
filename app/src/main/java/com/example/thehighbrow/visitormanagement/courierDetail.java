package com.example.thehighbrow.visitormanagement;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
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

import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class courierDetail extends AppCompatActivity {

    EditText name;
    EditText contact;
    EditText deliverto;

    DatabaseReference databaseVisitor;
    TextView submit;
    Calendar calendar;
    String photoUrl;

    private ImageButton muploadbtn;
    private ImageView mImageView;
    String path;
    String curl;
    private static final int CAMERA_REQUEST_CODE=1;
    private StorageReference mStorage;
    private ProgressDialog mProgress;
    String downloadUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courier_detail);

        mStorage= FirebaseStorage.getInstance().getReference();


        name=findViewById(R.id.namefield);
        contact=findViewById(R.id.contactfield);
        deliverto=findViewById(R.id.delivertofield);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra("android.intent.extras.LENS_FACING_FRONT", 1);
        startActivityForResult(intent,CAMERA_REQUEST_CODE);
        databaseVisitor = FirebaseDatabase.getInstance().getReference("courier");

        submit=findViewById(R.id.proceedBtn);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCourier();
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==CAMERA_REQUEST_CODE&&resultCode==RESULT_OK){

            Context inContext = courierDetail.this;
            Bitmap btmp = (Bitmap)data.getExtras().get("data");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            btmp.compress(Bitmap.CompressFormat.JPEG,0,baos);
            byte[] idata=baos.toByteArray();

            path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), btmp, "Title", null);
            final Uri uri = Uri.parse(path);
//            Picasso.get().load(uri).into(photo);
            final StorageReference filepath= mStorage.child("photos").child(uri.getLastPathSegment());
            final StorageTask<UploadTask.TaskSnapshot> taskSnapshotStorageTask = filepath.putFile(uri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {

                                    curl = String.valueOf(uri);
                                    Toast.makeText(courierDetail.this, "Photo Upload Complete, please proceed", Toast.LENGTH_SHORT).show();

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

    private void addCourier()
    {
        String cname = name.getText().toString().trim();
        String ccontact = contact.getText().toString().trim();
        String cdeliverto = deliverto.getText().toString().trim();

        if (!TextUtils.isEmpty(cname) && !TextUtils.isEmpty(ccontact) && !TextUtils.isEmpty(cdeliverto))
        {
            String id = databaseVisitor.push().getKey();

            Date d=new Date();
            SimpleDateFormat sdf=new SimpleDateFormat("hh:mm a");
            String currentDateTimeString = sdf.format(d);

            String Date = DateFormat.getDateInstance(DateFormat.DATE_FIELD).format(new Date()).toString();
            Log.e("visitorDetail", "addVisitor: TIME = "+currentDateTimeString+"DATE = "+Date);
//            Visitor visitor = new Visitor(dname,dcontact,dhost,photoUrl);
            Courier Courier = new Courier(cname,ccontact,cdeliverto,curl,currentDateTimeString, Date,id);

//            databaseVisitor.child(id).setValue(visitor);
            databaseVisitor.child(id).setValue(Courier);

            Toast.makeText(this, "Visitor Registered", Toast.LENGTH_SHORT).show();
            Intent intent= new Intent(courierDetail.this, Welcome.class);
            intent.putExtra("vname",cname);
            intent.putExtra("id",id);

            startActivity(intent);
        }
        else
        {
            if (name.getText().toString().trim().equalsIgnoreCase("")) {
                name.setError("This field can not be blank");
            }
            if (contact.getText().toString().trim().equalsIgnoreCase("")) {
                contact.setError("This field can not be blank");
            }
            if (deliverto.getText().toString().trim().equalsIgnoreCase("")) {
                deliverto.setError("This field can not be blank");
            }
            Toast.makeText(this, "All Fields Mandatory", Toast.LENGTH_LONG).show();
        }
    }
}
