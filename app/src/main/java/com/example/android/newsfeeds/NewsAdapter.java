package com.example.android.newsfeeds;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    List<NewsModel>newsModels=new ArrayList<>();
    Context context;
    public NewsAdapter(Context context, List<NewsModel> newsModels) {
        this.newsModels = newsModels;
        this.context = context;
    }


    @NonNull
    @NotNull
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
       View view=LayoutInflater.from(context).inflate(R.layout.list_layout,parent,false);
       ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull NewsAdapter.ViewHolder holder, int position) {
       holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,webView.class);
                intent.putExtra("url",newsModels.get(position).getUrl());
                context.startActivity(intent);
            }
        });

        holder.titleView.setText(newsModels.get(position).getTitle());
        Glide.with(context).load(newsModels.get(position).getUrlToImage()).into(holder.newsView);



    }

    @Override
    public int getItemCount() {
        return newsModels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView titleView;
        private ImageView newsView;
        private CardView cardView;



        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            titleView=itemView.findViewById(R.id.title1);
            newsView=itemView.findViewById(R.id.news_img);
            cardView=itemView.findViewById(R.id.cardView);


        }


    }
}
