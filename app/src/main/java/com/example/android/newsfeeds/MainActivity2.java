package com.example.android.newsfeeds;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

public class MainActivity2 extends AppCompatActivity {
    EditText otp;
    Button login;
    FirebaseAuth firebaseAuth;
    String number;
    String OTP;
    ActionBar actionBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        number=getIntent().getStringExtra("number").toString();
        otp=(EditText)findViewById(R.id.otp);
        login=(Button)findViewById(R.id.login);
        firebaseAuth=FirebaseAuth.getInstance();
        actionBar=getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1FB6FB")));
        getotp();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (otp.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity2.this, "fill otp", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (otp.getText().toString().length()!=6){
                        Toast.makeText(MainActivity2.this, "fill all six digits of otp", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        PhoneAuthCredential phoneAuthCredential= PhoneAuthProvider.getCredential(OTP,otp.getText().toString());
                        signinWithPhoneAuthCredential(phoneAuthCredential);
                    }
                }
            }
        });

    }
    private  void  getotp(){
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                number,
                60,
                TimeUnit.SECONDS,
                this,
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onCodeSent(@NonNull @org.jetbrains.annotations.NotNull String s, @NonNull @org.jetbrains.annotations.NotNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        super.onCodeSent(s, forceResendingToken);
                        OTP=s;
                    }

                    @Override
                    public void onVerificationCompleted(@NonNull @org.jetbrains.annotations.NotNull PhoneAuthCredential phoneAuthCredential) {
                        signinWithPhoneAuthCredential(phoneAuthCredential);

                    }

                    @Override
                    public void onVerificationFailed(@NonNull @org.jetbrains.annotations.NotNull FirebaseException e) {
                        Toast.makeText(MainActivity2.this, "Otp missmatch", Toast.LENGTH_SHORT).show();

                    }
                }
        );
    }
    private  void signinWithPhoneAuthCredential(PhoneAuthCredential credential){
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(MainActivity2.this, "login done", Toast.LENGTH_SHORT).show();
                    SharedPreferences sharedPreferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("remember", "true");
                    editor.apply();
                    Intent intent=new Intent(MainActivity2.this,Language.class);
                    startActivity(intent);
                    finish();

                }
                else {
                    Toast.makeText(MainActivity2.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}