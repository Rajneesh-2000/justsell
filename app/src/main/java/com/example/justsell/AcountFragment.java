package com.example.justsell;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;


public class AcountFragment extends Fragment {

    Activity account;
   // onlu use for relational database


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        account = getActivity();
        return inflater.inflate(R.layout.fragment_acount, container, false);
    }






    public void onStart() {
        super.onStart();
        TextView name=(TextView) account.findViewById(R.id.tv_name);
        TextView contact=(TextView) account.findViewById(R.id.cnt_b1);

        TextView address=(TextView) account.findViewById(R.id.tv_bio);
        Button edit =(Button) account.findViewById(R.id.btn_edit);
        Button logout = (Button) account.findViewById(R.id.btn_login);
        ImageView imageView=(ImageView) account.findViewById(R.id.iv_f1);


        AdView mAdView=(AdView)  account.findViewById(R.id.addView);

        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String currentid=user.getUid();
        DocumentReference reference;
        FirebaseFirestore firestore=FirebaseFirestore.getInstance();

        reference=firestore.collection("user").document(currentid);


        reference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.getResult().exists()){
                    String nameResult =task.getResult().getString("name");
                    String numberResult =task.getResult().getString("number");
                    String addresseResult =task.getResult().getString("address");
                   String url =task.getResult().getString("Url");

                 Picasso.get().load(url).into(imageView);

                    name.setText(nameResult);
                    contact.setText(numberResult);
                    address.setText(addresseResult);


                }else {
                    Intent intent=new Intent(account,editImageAccount.class);
                    startActivity(intent);
                }
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Intent intent = new Intent(account, editImageAccount.class);
                                        startActivity(intent);
                                    }
                                });




         // if click on logout button then go to again login button
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(account, MainActivity.class);
                startActivity(intent);
            }
        });


    }



}