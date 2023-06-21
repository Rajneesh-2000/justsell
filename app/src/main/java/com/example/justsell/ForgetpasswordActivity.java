package com.example.justsell;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetpasswordActivity extends AppCompatActivity {

    private Button mforgotbutton;
    TextView mbacktologin;
    EditText memail;
    String emailpattern = "[a-zA-Z\\d._-]+@[a-z]+\\.+[a-z]+";
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword);


        mforgotbutton=findViewById(R.id.forgotbuttonn);
        memail=findViewById(R.id.emailid);
        mbacktologin=findViewById(R.id.Backtologin);
        mAuth = FirebaseAuth.getInstance();


        //if click on back to login   then go to login  page
        mbacktologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ForgetpasswordActivity.this, MainActivity.class));
            }
        });

        mforgotbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reserpassword();
            }
        });

    }

    private void reserpassword() {
        String email =memail.getText().toString().trim();

        // msg me email is not empty
        if (!email.matches(emailpattern)) {
            memail.setError("Enter correct email");
            return;
        }
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(ForgetpasswordActivity.this, "check your email to reset password", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(ForgetpasswordActivity.this, "Try again something wrong happens", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}