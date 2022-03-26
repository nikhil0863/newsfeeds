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

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Language_Adapter extends RecyclerView.Adapter<Language_Adapter.MyViewHolder> {


    private Context context;
    private List<Language_model> data;

    public Language_Adapter(Context context, List<Language_model> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater inflater=LayoutInflater.from(context);
        view=inflater.inflate(R.layout.language_cardview,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull Language_Adapter.MyViewHolder holder, int position) {

        holder.language_title.setText(data.get(position).getTitle());
        holder.language_img.setImageResource(data.get(position).getLanguage_image());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,MainActivity3.class);
                intent.putExtra("code",data.get(position).getCode());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class  MyViewHolder extends RecyclerView.ViewHolder{

        TextView language_title;
        ImageView language_img;
        CardView cardView;

        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            language_title=(TextView) itemView.findViewById(R.id.language_title);
            language_img=(ImageView) itemView.findViewById(R.id.language_image);
            cardView=(CardView) itemView.findViewById(R.id.cardView_id1);
        }
    }

}
