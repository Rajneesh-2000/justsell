package com.example.justsell;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {


    // declare email,etc
    TextView alreadyHaveaccount;
    EditText memail, mpassword, mconfirm;
    Button mRegistor, mlogin;
    String emailpattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    FirebaseAuth mAuth;
    FirebaseUser mUser;


    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });


        // define by id
        memail = findViewById(R.id.email);
        mpassword = findViewById(R.id.password);
        mRegistor = findViewById(R.id.registeraccount);
        mconfirm = findViewById(R.id.confirmpassword);
        mlogin = findViewById(R.id.loginbutton);
        alreadyHaveaccount = findViewById(R.id.already);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();


        // when click on login button then we go to login page
        mlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
            }
        });
        // this work when we click on register button
        mRegistor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // string get (input )input by user
                final String email = memail.getText().toString().trim();
                final String password = mpassword.getText().toString().trim();
                final String confirm = mconfirm.getText().toString().trim();


                // msg me email is not empty
                if (!email.matches(emailpattern)) {
                    memail.setError("Enter correct email");
                }
                // password is not empty
                else if (password.isEmpty() || password.length() < 6) {
                    mpassword.setError("Password must be atleast 6 characters");
                } else if (!password.equals(confirm)) {
                    mconfirm.setError("password not match");

                } else {
                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // email verification
                                Objects.requireNonNull(mAuth.getCurrentUser()).sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(RegisterActivity.this, "please verify your email account", Toast.LENGTH_SHORT).show();

                                            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                            startActivity(intent);

                                            if (mAuth.getCurrentUser().isEmailVerified()) {

                                                // this is only for send email to account fregment
                                                Intent intent1=new Intent(RegisterActivity.this,StyleActivity.class);
                                                intent1.putExtra("email",email);
                                                startActivity(intent1);

                                                Toast.makeText(RegisterActivity.this, "Register  sucessfully", Toast.LENGTH_SHORT).show();

                                            }

                                        } else {
                                            Toast.makeText(RegisterActivity.this, "" + task.getException(), Toast.LENGTH_SHORT).show();
                                        }

                                    }
                                });
                            }else{
                                Toast.makeText(RegisterActivity.this, "email already exist", Toast.LENGTH_SHORT).show();
                                }
                        }



                    });

                }
            }
        });
    }


}
