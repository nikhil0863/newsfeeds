package com.example.android.newsfeeds;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    ActionBar actionBar;
    public  void senotp(View view){
        Intent intent=new Intent(MainActivity.this,MainActivity4.class);
        startActivity(intent);
        finish();

    }
    public void byEmail(View view){
        Intent intent=new Intent(MainActivity.this,MainActivity5.class);
        startActivity(intent);
        finish();

    }

    public void byGoogle(View view){
        Intent intent=new Intent(MainActivity.this,GoogleActivity.class);
        startActivity(intent);
        finish();
    }


    @Override 
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences preferences=getSharedPreferences("checkbox",MODE_PRIVATE);
        String checkbox=preferences.getString("remember","");
        actionBar=getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1FB6FB")));

        if (checkbox.equals("true")){
            Intent intent=new Intent(MainActivity.this,MainActivity3.class);
            startActivity(intent);

        }
        SharedPreferences preferences2=getSharedPreferences("checkbox2",MODE_PRIVATE);
        String checkbox2=preferences2.getString("remember2","");
        if (checkbox2.equals("true")){
            Intent intent=new Intent(MainActivity.this,MainActivity3.class);
            startActivity(intent);

        }







    }

}