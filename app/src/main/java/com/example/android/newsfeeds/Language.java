package com.example.android.newsfeeds;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class Language extends AppCompatActivity {

    List<Language_model> list_lang;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);
        actionBar=getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1FB6FB")));

        list_lang=new ArrayList<>();
        list_lang.add(new Language_model("English","en",R.drawable.english));
        list_lang.add(new Language_model("Hindi","hi",R.drawable.hindi));
        list_lang.add(new Language_model("Marathi","ml",R.drawable.marathi));
        list_lang.add(new Language_model("Tamil","ta",R.drawable.tamil));
        list_lang.add(new Language_model("Urdu","ur",R.drawable.urdu));
        list_lang.add(new Language_model("Malyalam","ma",R.drawable.malyalam));

        RecyclerView recyclerView=(RecyclerView) findViewById(R.id.languagr_recyclerview);
        Language_Adapter language_adapter=new Language_Adapter(this,list_lang);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(language_adapter);


    }

}