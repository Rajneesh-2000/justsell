package com.example.justsell;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    TextView CreatenewAccount, mforgotpassword;
    EditText memail, mpassword;
    String emailpattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    Button mlogin;

    FirebaseAuth mAuth;
    FirebaseUser mUser;
    ProgressBar progressbar;
    int count = 0;
    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAdView = findViewById(R.id.addView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        //define by id
        CreatenewAccount = findViewById(R.id.newaccount);
        memail = findViewById(R.id.email);
        mpassword = findViewById(R.id.passwordrr);
        mlogin = findViewById(R.id.registeraccount);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mforgotpassword = findViewById(R.id.forgot);
        progressbar=findViewById(R.id.progressBar);


        //if click on create new account then go to registration page
        CreatenewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            }
        });


        // when click on login
        mlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressbar.setVisibility(View.VISIBLE);

                // string get (input )input by user
                final String email = memail.getText().toString().trim();
                final String password = mpassword.getText().toString().trim();
              // progress bar

                // msg me email is not empty
                if (!email.matches(emailpattern)) {
                    memail.setError("Enter correct email");
                    progressbar.setVisibility(View.GONE);
                }
                // password is not empty
                else if (password.isEmpty() || password.length() < 6) {
                    mpassword.setError("Password must be atleast 6 characters");
                    progressbar.setVisibility(View.GONE);


                } else {
                    progressbar.setVisibility(View.VISIBLE);
                    //
                    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // this sendUsertonextactivity use later here only declare
                                if (mAuth.getCurrentUser().isEmailVerified()) {
                                    SendUserToNextActivity();
                                    progressbar.setVisibility(View.GONE);
                                    Toast.makeText(MainActivity.this, "Login sucessfully", Toast.LENGTH_SHORT).show();
                                } else
                                {
                                    progressbar.setVisibility(View.GONE);
                                    Toast.makeText(MainActivity.this, "firstly verify your account", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                progressbar.setVisibility(View.GONE);
                                Toast.makeText(MainActivity.this, "" + task.getException(), Toast.LENGTH_SHORT).show();

                            }
                        }

                        private void SendUserToNextActivity() {
                            Intent intent = new Intent(MainActivity.this, StyleActivity.class);
                            startActivity(intent);
                        }
                    });
                }

            }
        });
        //if click on create new account then go to forgot password page
        mforgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ForgetpasswordActivity.class));
            }
        });
    }

}