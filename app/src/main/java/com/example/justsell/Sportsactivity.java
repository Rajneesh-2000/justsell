package com.example.justsell;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class Sportsactivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST =1;
    private RelativeLayout mbuttonchoseimage;
    private Button mbuttonupload;
    private ImageView mimageview;
    UploadTask uploadTask;
    private Uri mimageUri;
    ProgressDialog dialog;
    private StorageReference mstorageRef;
    private DatabaseReference mdatabaseref;
    FirebaseDatabase database;

    TextInputEditText itemname, itemprice, itemdesc, location,contact;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sportsactivity);


        dialog=new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("Please Wait..");
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);




        itemname = findViewById(R.id.ItemName);
        itemprice = findViewById(R.id.ItemPrice);
        itemdesc = findViewById(R.id.ItemDesc);
        location = findViewById(R.id.ItemLoc);
        contact=findViewById(R.id.Itemnumber);



       mstorageRef= FirebaseStorage.getInstance().getReference("uploads");
       mdatabaseref= FirebaseDatabase.getInstance().getReference("uploads");
        mbuttonchoseimage=findViewById(R.id.choseimage);
        mbuttonupload= findViewById(R.id.uploadbutton);
        mimageview=findViewById(R.id.imageview);


        mbuttonchoseimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openFileChoser();
            }
        });



                 mbuttonupload.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {


                         // string get (input )input by user
                         final String name = itemname.getText().toString().trim();
                         final String number = itemprice.getText().toString().trim();
                         final String address = location.getText().toString().trim();
                         final String description = itemdesc.getText().toString().trim();
                         final String numbers = contact.getText().toString().trim();


                         // msg me email is not empty
                         if (name.isEmpty()) {
                             itemname.setError("Enter correct email");
                         }
                         // password is not empty
                         else if (number.isEmpty() || number.length() == 1) {
                             itemprice.setError("enter correct price");
                         } else if (description.isEmpty() || description.length() > 100) {
                             itemdesc.setError("enter proper description and its length is less then 100 ");
                         } else if (address.isEmpty()) {
                             location.setError("enter address");
                         }  else if (numbers.isEmpty() || numbers.length() !=10) {
                             contact.setError("enter correct number");
                         }
                         else {
                             uploadfile();

                         }
                     }

                 });
    }
    //this method is for chose image from gallery
    private void openFileChoser(){

        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode ==PICK_IMAGE_REQUEST && resultCode ==RESULT_OK
        && data != null && data.getData()  != null){
            mimageUri = data.getData();
            Picasso.get().load(mimageUri).into(mimageview);
            mimageview.setImageURI(mimageUri);
        }
    }
    //this method is for extension of our file
    private String getFileExtension(Uri uri){
        ContentResolver cR= getContentResolver();
        MimeTypeMap mime=MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }
    private void uploadfile(){
     dialog.show();
        final StorageReference reference = mstorageRef.child(System.currentTimeMillis() + "." + getFileExtension(mimageUri));
        uploadTask = reference.putFile(mimageUri);

        Task<Uri> uriTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful()) {
                            throw task.getException();
                        }
                        return reference.getDownloadUrl();

                    }

                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {

                        Uri downloadUri = task.getResult();


                        database =FirebaseDatabase.getInstance();
                    mdatabaseref =database.getReference("sports");

                    // string get (input )input by user
                     final String name = itemname.getText().toString().trim();
                      final String number = itemprice.getText().toString().trim();
                    final String description = itemdesc.getText().toString().trim();
                     final String address = location.getText().toString().trim();
                    final String numbers = contact.getText().toString().trim();
                        final String imageurl = downloadUri.toString();

                    //here fileReference if use to get url of image
                    upload uploads = new upload(name,number,description,address,numbers,imageurl);
                //  upload upload=new upload(itemname.getText().toString().trim(),taskSnapshot.getStorage().getDownloadUrl().toString());
                  mdatabaseref.child(name).setValue(uploads);
                        Toast.makeText(Sportsactivity.this, "Data save Successful", Toast.LENGTH_SHORT).show();
                 dialog.dismiss();

                }

            });


    }
}