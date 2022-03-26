package com.example.android.newsfeeds;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

public class MainActivity5 extends AppCompatActivity {
    EditText name,email,password;
    Button signup;
    ActionBar actionBar;
    FirebaseAuth firebaseAuth;
    TextView Alt;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        name=(EditText)findViewById(R.id.name);
        email=(EditText)findViewById(R.id.emailemail);
        password=(EditText)findViewById(R.id.emailpassword);
        signup=(Button)findViewById(R.id.emailsignup);
        Alt=(TextView)findViewById(R.id.loginagain);
        actionBar=getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1FB6FB")));
        if (!email.getText().toString().isEmpty()&&!password.getText().toString().isEmpty()){
            signup.setBackground(ContextCompat.getDrawable(this,R.drawable.red));
        }
        SharedPreferences sharedPreferences=getSharedPreferences("checkbox", MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("remember","false");
        editor.apply();


        firebaseAuth=FirebaseAuth.getInstance();
        Alt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent i=new Intent(MainActivity5.this,MainActivity6.class);
               startActivity(i);
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email=email.getText().toString();
                String Password=password.getText().toString();
                String Name=name.getText().toString();
                if (Email.isEmpty()){
                    email.setError("fill Email");
                    return;
                }
                else {
                    if (Password.isEmpty()){
                        password.setError("fill password");
                        return;
                    }
                    firebaseAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(MainActivity5.this, " Sign in done", Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(MainActivity5.this,MainActivity6.class);
                                startActivity(intent);;

                            }
                            else {
                                Toast.makeText(MainActivity5.this, "Aleady exist", Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(MainActivity5.this,MainActivity6.class);
                                startActivity(intent);;
                            }
                        }
                    });

                }
            }
        });



    }

}