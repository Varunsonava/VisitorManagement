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

public class leadDetail extends AppCompatActivity {


    EditText name;
    EditText contact;
    EditText email;

    private ImageButton muploadbtn;
    private ImageView mImageView;
    String path;
    String durl;
    private static final int CAMERA_REQUEST_CODE = 1;
    private StorageReference mStorage;
    private ProgressDialog mProgress;
    String downloadUrl;


    EditText reach;
    DatabaseReference databaseVisitor;
    TextView submit;
    Calendar calendar;
    String formattedDate;

    String photoUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lead_detail);

        mStorage = FirebaseStorage.getInstance().getReference();

        reach=findViewById(R.id.reachfield);
        name=findViewById(R.id.namefield);
        contact=findViewById(R.id.contactfield);
        email=findViewById(R.id.emailfield);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA_REQUEST_CODE);

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        formattedDate = dateFormat.format(c);
        databaseVisitor = FirebaseDatabase.getInstance().getReference("Noida Sec1/"+formattedDate+"/91lead");


        submit=findViewById(R.id.proceedBtn);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addlead();
            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {

            //   Uri uri  = data.getData();
            Context inContext = leadDetail.this;
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
                                    durl = String.valueOf(uri);
                                    Log.e("checking for dwnld URL", "onSuccess: " + durl);

                                    Toast.makeText(leadDetail.this, "Photo Upload Complete", Toast.LENGTH_SHORT).show();

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

    private void addlead()
    {
        String dname = name.getText().toString().trim();
        String dcontact = contact.getText().toString().trim();
        String demail = email.getText().toString().trim();
        String dreach = reach.getText().toString().trim();

        if (!TextUtils.isEmpty(dname) && !TextUtils.isEmpty(dcontact) && !TextUtils.isEmpty(demail))
        {
            String id = databaseVisitor.push().getKey();

            Date d=new Date();
            SimpleDateFormat sdf=new SimpleDateFormat("hh:mm a");
            String currentDateTimeString = sdf.format(d);

            String Date = DateFormat.getDateInstance(DateFormat.DATE_FIELD).format(new Date()).toString();
            Log.e("visitorDetail", "addVisitor: TIME = "+currentDateTimeString+"DATE = "+Date);
            Lead lead = new Lead(dname,dcontact,demail,dreach,durl, currentDateTimeString,formattedDate,id,"");

            databaseVisitor.child(id).setValue(lead);

            Toast.makeText(this, "Lead member Registered", Toast.LENGTH_SHORT).show();
            Intent intent= new Intent(leadDetail.this,Welcome.class);
            intent.putExtra("vname",dname);
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
            if (reach.getText().toString().trim().equalsIgnoreCase("")) {
                reach.setError("This field can not be blank");
            }
            if (email.getText().toString().trim().equalsIgnoreCase("")) {
                email.setError("This field can not be blank");
            }
            Toast.makeText(this, "All Fields Mandatory", Toast.LENGTH_LONG).show();
        }
    }
}