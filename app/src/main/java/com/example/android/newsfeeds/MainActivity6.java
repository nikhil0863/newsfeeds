package com.example.android.newsfeeds;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

public class MainActivity6 extends AppCompatActivity {
    EditText email,password;
    Button login;
    FirebaseAuth firebaseAuth;
    ActionBar actionBar;


    public CheckBox remember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        email=(EditText)findViewById(R.id.emailidlogin);
        password=(EditText)findViewById(R.id.loginpassword);
        login=(Button)findViewById(R.id.loginlogin);
        firebaseAuth=FirebaseAuth.getInstance();
        actionBar=getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1FB6FB")));
        remember=(CheckBox)findViewById(R.id.checkBox);





        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email=email.getText().toString();
                String Password=password.getText().toString();
                if (Email.isEmpty()){
                    email.setError("fill email");
                    return;
                }
                else {
                    if (Password.isEmpty()){
                        password.setError("fill password");
                        return;
                    }
                }
                firebaseAuth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(MainActivity6.this, "Log in done", Toast.LENGTH_SHORT).show();
                            if (remember.isChecked()) {
                                SharedPreferences sharedPreferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("remember", "true");
                                editor.apply();
                            }
                            else if (!remember.isChecked()){
                                SharedPreferences sharedPreferences=getSharedPreferences("checkbox", MODE_PRIVATE);
                                SharedPreferences.Editor editor=sharedPreferences.edit();
                                editor.putString("remember","false");
                                editor.apply();
                            }
                            Intent kt=new Intent(MainActivity6.this,Language.class);
                            startActivity(kt);
                            finish();
                        }
                        else{
                            Toast.makeText(MainActivity6.this, "Invalid id", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        remember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isChecked()){
                    Toast.makeText(MainActivity6.this, " We will remember you", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}