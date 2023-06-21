package com.example.justsell;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;


public class EditimageActivity extends AppCompatActivity {
EditText namee,numberr,addresss;
Button save;
ImageView imageView;
    FirebaseDatabase database;
ProgressDialog dialog;
    private StorageReference storageRef;
    DatabaseReference databaseref;
    FirebaseFirestore fStore;
    private static final int PICK_IMAGE_REQUEST =1;
    private Uri mimageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editimage);

        dialog=new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("Please Wait..");
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);




        imageView=findViewById(R.id.pro_img);
        namee = findViewById(R.id.name);
         numberr = findViewById(R.id.number);
         addresss = findViewById(R.id.address);
         save = findViewById(R.id.save);
        storageRef= FirebaseStorage.getInstance().getReference("uploads");
        databaseref= FirebaseDatabase.getInstance().getReference("uploads");
        fStore=FirebaseFirestore.getInstance();



        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openFileChoser();
            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkcredentials();

            }

            // this method is user too check data enter is valid or not
            private void checkcredentials() {

                // string get (input )input by user
                final String name = namee.getText().toString().trim();
                final String number = numberr.getText().toString().trim();
                final String address = addresss.getText().toString().trim();



                // msg me email is not empty
                if (name.isEmpty()) {
                    namee.setError("Enter correct email");
                }
                // password is not empty
                else if (number.isEmpty() || number.length() < 10) {
                    numberr.setError("enter correct number");
                } else if (address.isEmpty()) {
                    addresss.setError("enter address");
                } else {

                    uploadfile();


                }


            }

        });

    }

    private void openFileChoser() {
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
            Picasso.get().load(mimageUri).into(imageView);
            imageView.setImageURI(mimageUri);
        }
    }
    private String getFileExtension(Uri uri){
        ContentResolver cR= getContentResolver();
        MimeTypeMap mime=MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }


    private void uploadfile() {
        dialog.show();
        if (mimageUri != null) {
            StorageReference fileReference = storageRef.child(System.currentTimeMillis() + "." + getFileExtension(mimageUri));
            fileReference.putFile(mimageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //   mprogressBar.setProgress(0);
                        }
                    }, 500);
                    Toast.makeText(EditimageActivity.this, "Upload sucessful", Toast.LENGTH_LONG).show();
                    database = FirebaseDatabase.getInstance();
                    databaseref = database.getReference("users");

                    final String names = namee.getText().toString().trim();
                    final String numbers = numberr.getText().toString().trim();
                    final String addresse = addresss.getText().toString().trim();


                    HelperClass helperClass = new HelperClass(names, numbers, addresse, fileReference.getDownloadUrl().toString());
                    databaseref.child(names).setValue(helperClass);
                    dialog.dismiss();

                }


                });


        }
    }
}