package com.example.android.newsfeeds;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity3 extends AppCompatActivity {
    private RecyclerView newsRecyclerView;
    private long bacpress;
    ActionBar actionBar;
    private List<NewsModel> newsModelList = new ArrayList<>();
    private NewsAdapter newsAdapter;
    FirebaseAuth firebaseAuth;
    String Base_Url = "https://gnews.io/api/v4/top-headlines?token=e0cb12dcff0918e2a9585603e5794278&country=in&lang=";
    String lang;




    public void onBackPressed() {
        if(bacpress + 2000 >System.currentTimeMillis()){
            finishAffinity();
            System.exit(0);
        } else {
            Toast.makeText(getBaseContext(),"Press back again  to exit",Toast.LENGTH_SHORT).show();
        }
        bacpress=System.currentTimeMillis();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        newsRecyclerView = findViewById(R.id.reclerView);
        lang=getIntent().getStringExtra("code");
        Base_Url=Base_Url+lang;
        loadNews();
        newsAdapter = new NewsAdapter(this, newsModelList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        newsRecyclerView.setLayoutManager(linearLayoutManager);
        newsRecyclerView.setAdapter(newsAdapter);
        newsAdapter.notifyDataSetChanged();
        actionBar=getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1FB6FB")));
        firebaseAuth = FirebaseAuth.getInstance();


    }


    private void loadNews() {

        StringRequest request = new StringRequest(Request.Method.GET, Base_Url,
                response -> {

                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("articles");
                        int length = jsonArray.length();
                        for (int i = 0; i < length; i++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            String title = jsonObject1.getString("title");
                            String urlToImage = jsonObject1.getString("image");
                            String url = jsonObject1.getString("url");
                            newsModelList.add(new NewsModel(title, urlToImage, url));
                        }
                        newsAdapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }, error -> Toast.makeText(this, "none", Toast.LENGTH_SHORT).show());

        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity3.this);
        requestQueue.add(request);


    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id =item.getItemId();
        if (id == R.id.logout) {

            SharedPreferences sharedPreferences=getSharedPreferences("checkbox", MODE_PRIVATE);
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putString("remember","false");
            editor.apply();
            SharedPreferences sharedPreferences2=getSharedPreferences("checkbox2", MODE_PRIVATE);
            SharedPreferences.Editor editor2=sharedPreferences2.edit();
            editor2.putString("remember2","false");
            editor2.apply();
            firebaseAuth.signOut();
            Intent intent=new Intent(MainActivity3.this,MainActivity.class);
            startActivity(intent);

        }
        return true;
    }
}