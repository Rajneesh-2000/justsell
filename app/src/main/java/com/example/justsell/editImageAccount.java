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

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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

public class editImageAccount extends AppCompatActivity {
    EditText etname,etnumber,etaddress;
    Button save;
    ImageView imageView;
    Uri imageUri;
    UploadTask uploadTask;
    StorageReference storageReference;
    FirebaseDatabase database =FirebaseDatabase.getInstance();
    DatabaseReference databaseReference;
    FirebaseFirestore db=FirebaseFirestore.getInstance();
    DocumentReference documentReference;
    ProgressDialog dialog;
String currentUserId;
// here use allmember class for store data in real time
    ALLUserMember member;
    private static final int PICK_IMAGE_REQUEST =1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_image_account);

        dialog=new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("Please Wait..");
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);



        member=new ALLUserMember();
        imageView=findViewById(R.id.pro_img);
        etname = findViewById(R.id.names);
        etnumber = findViewById(R.id.numbers);
        etaddress = findViewById(R.id.addresss);
        save = findViewById(R.id.save);

        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        currentUserId=user.getUid();
// for cloud data storage
        documentReference =db.collection("user").document(currentUserId);
        //for image store in storage
        storageReference = FirebaseStorage.getInstance().getReference("profile images");
        //for realtime
        databaseReference=database.getReference("all users");


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=etname.getText().toString();
                String number=etnumber.getText().toString();
                String address=etaddress.getText().toString();
                // msg me email is not empty
                if (name.isEmpty()) {
                    etname.setError("Enter correct email");
                }
                // password is not empty
                else if (number.isEmpty() || number.length() != 10) {
                    etnumber.setError("enter correct number");
                } else if (address.isEmpty()) {
                    etaddress.setError("enter address");
                } else {

                    uploadData();
                }
            }
        });
     // foe select image from gallery we create new intent
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent,PICK_IMAGE_REQUEST);
            }
        });
    }

    //to display gallery image
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
          if (requestCode==PICK_IMAGE_REQUEST ||  resultCode ==RESULT_OK ||
          data != null || data.getData() !=null){
          imageUri =data.getData();
              Picasso.get().load(imageUri).into(imageView);
              imageView.setImageURI(imageUri);
          }
    }
    private String getFileExtension(Uri uri){
        ContentResolver cR= getContentResolver();
        MimeTypeMap mime=MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType((cR.getType(uri)));
    }

    private void uploadData() {
        String name=etname.getText().toString();
        String number=etnumber.getText().toString();
        String address=etaddress.getText().toString();
        dialog.show();
        // takesnapshot of image from storage
        final  StorageReference reference= storageReference.child(System.currentTimeMillis()+"."+ getFileExtension(imageUri));
        uploadTask=reference.putFile(imageUri);

        Task<Uri>  uriTask=uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
              if (!task.isSuccessful()){
        throw  task.getException();
              }
              return  reference.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {

                Uri downloadUri=task.getResult();

                Map<String,String> profile=new HashMap<>();
                profile.put("name",name);
                profile.put("number",number);
                profile.put("Url",downloadUri.toString());
                profile.put("address",address);
                profile.put("privacy","Public");

                member.setName(name);
                member.setNumber(number);
                member.setAddress(address);
                member.setUid(currentUserId);
                member.setUrl(downloadUri.toString());

                databaseReference.child(currentUserId).setValue(member);

                documentReference.set(profile)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {

                                Toast.makeText(editImageAccount.this, "profile created", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                                Handler handler=new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        //when data store then automatically move to editimage
                                      Intent intent=new Intent(editImageAccount.this,AcountFragment.class);
                                      startActivity(intent);
                                    }
                                },2000);
                            }
                        });

            }
        });
    }
}