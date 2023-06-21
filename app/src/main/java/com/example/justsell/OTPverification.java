package com.example.justsell;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class OTPverification extends AppCompatActivity {

    EditText mphone,motp;
    Button btngenotp, btnverifyotp;
    FirebaseAuth mAuth;
    String verificationID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpverification);

      mphone=findViewById(R.id.Phone);
      motp=findViewById(R.id.otp);
      btngenotp=findViewById(R.id.otpsend);
      btnverifyotp=findViewById(R.id.otpenter);
      mAuth=FirebaseAuth.getInstance();


      btngenotp.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              String number = null;
              if (TextUtils.isEmpty((mphone.getText().toString()))) {
                  Toast.makeText(OTPverification.this, "enter valid phone number", Toast.LENGTH_SHORT).show();
              }
                  number = mphone.getText().toString();

              generatecode(number);
          }
      });
        btnverifyotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            if(TextUtils.isEmpty(btngenotp.getText().toString()))
            {
                Toast.makeText(OTPverification.this, "wrong otp entered", Toast.LENGTH_SHORT).show();
            }else
                sendverificationcode(motp.getText().toString());
            }
        });


    }
    private void generatecode(String phoneNumber) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber("+91"+phoneNumber)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);

    }
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks
    mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential credential) {

          final String code = credential.getSmsCode();
          if (code!=null){
              sendverificationcode(code);
          }
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Toast.makeText(OTPverification.this, "verification faild", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCodeSent(@NonNull String s,
                @NonNull PhoneAuthProvider.ForceResendingToken token) {


           super.onCodeSent(s, token);
           verificationID=s;
        }
    };

    private void sendverificationcode(String code) {
         PhoneAuthCredential credential=PhoneAuthProvider.getCredential(verificationID,code);
         signinbyCredential(credential);
    }

    private void signinbyCredential(PhoneAuthCredential credential) {
        FirebaseAuth firebaseAuth= FirebaseAuth.getInstance();
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(OTPverification.this, "Login successful", Toast.LENGTH_SHORT).show();
                       startActivity(new Intent(OTPverification.this,HomeActivity.class));
                        }
                    }
                });
    }

}