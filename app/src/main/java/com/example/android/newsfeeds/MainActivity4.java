package com.example.android.newsfeeds;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.hbb20.CountryCodePicker;

public class MainActivity4 extends AppCompatActivity {

        EditText number;
        Button signup;
    ActionBar actionBar;
        CountryCodePicker countryCodePicker;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main4);
            number=(EditText)findViewById(R.id.phoneNumber);
            signup=(Button)findViewById(R.id.signup);
            countryCodePicker=(CountryCodePicker)findViewById(R.id.ccp);
            countryCodePicker.registerCarrierNumberEditText(number);
            actionBar=getSupportActionBar();
            actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1FB6FB")));



            signup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(MainActivity4.this,MainActivity2.class);
                    intent.putExtra("number",countryCodePicker.getFullNumberWithPlus().trim());
                    startActivity(intent);
                }
            });

        }

}