package com.example.thehighbrow.visitormanagement;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;

public class visitorActivity extends AppCompatActivity {
    String TAG= "visitorActivity";
    private ImageButton muploadbtn;
    private ImageView mImageView;
    String durl;
    String path;
    StorageReference filepath;

    private static final int CAMERA_REQUEST_CODE=1;
    private StorageReference mStorage;
    private ProgressDialog mProgress;
    String downloadUrl;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitor);

        mStorage=FirebaseStorage.getInstance().getReference();

        muploadbtn=findViewById(R.id.upload);

        mProgress= new ProgressDialog(this);

        muploadbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,CAMERA_REQUEST_CODE);

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(visitorActivity.this,GuestType.class));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==CAMERA_REQUEST_CODE&&resultCode==RESULT_OK){
            mProgress.setMessage("Uploading Image...");
            mProgress.show();
            Log.e(TAG, "onActivityResult: entered into function");
         //   Uri uri  = data.getData();
            Context inContext = visitorActivity.this;
            Bitmap btmp = (Bitmap)data.getExtras().get("data");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            btmp.compress(Bitmap.CompressFormat.JPEG,100,baos);
            byte[] idata=baos.toByteArray();

            Log.e(TAG, "onActivityResult: 1");

          //  path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), btmp, "Title", null);
            final Uri uri = Uri.parse(path);
            filepath = mStorage.child("photos").child(uri.getLastPathSegment());

            Log.e(TAG, "onActivityResult: 2");

            final StorageTask<UploadTask.TaskSnapshot> taskSnapshotStorageTask = filepath.putFile(uri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    mProgress.dismiss();

                    Log.e(TAG, "onSuccess: 3");

            filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    Log.e(TAG, "onSuccess: ");
                    //downloadUrl = mStorage.getDownloadUrl().toString();
                    durl = String.valueOf(uri);
                    Log.e("checking for dwnld URL", "onSuccess: "+durl);

                    Toast.makeText(visitorActivity.this, "Photo Captured, please proceed", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(visitorActivity.this, visitorDetail.class);
                    intent.putExtra("photourl", durl);
                    Log.e(TAG, "onSuccess: seding this url = " + durl);
                    startActivity(intent);
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
}
