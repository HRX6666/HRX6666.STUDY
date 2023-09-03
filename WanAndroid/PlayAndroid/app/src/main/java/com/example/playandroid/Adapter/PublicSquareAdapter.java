package com.example.playandroid.Adapter;

import android.content.Context;
import android.content.Intent;
import android.service.autofill.UserData;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.playandroid.Data.UsefulData;
import com.example.playandroid.R;
import com.example.playandroid.UI.Activity.ShareUserActivity;
import com.example.playandroid.UI.Activity.WebActivity;

import java.util.List;


public class PublicSquareAdapter extends RecyclerView.Adapter<PublicSquareAdapter.ViewHolder> {
    private List<UsefulData> mdata;
    private Context mcontext;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView_title;
        TextView textView_chapterName;
        TextView textView_niceTime;
        TextView textView_shareUser;
        ImageView imageView;
        ImageView imageView_heard;

        public ViewHolder(View view) {
            super(view);
            textView_chapterName = view.findViewById(R.id.s_article_chapterName);
            textView_niceTime = view.findViewById(R.id.s_article_niceTime);
            textView_title = view.findViewById(R.id.s_article_title);
            textView_shareUser = view.findViewById(R.id.s_shareUser);
            imageView = view.findViewById(R.id.public_square_us_heard);
            imageView_heard = view.findViewById(R.id.square_like);

        }
    }

    public PublicSquareAdapter(List<UsefulData> mdata) {
        this.mdata = mdata;
    }

    @NonNull
    @Override
    public PublicSquareAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_square_item, parent, false);
        final PublicSquareAdapter.ViewHolder holder = new PublicSquareAdapter.ViewHolder(view);
        if (mcontext == null) {
            mcontext = parent.getContext();
        }

        holder.textView_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                UsefulData usefulData = mdata.get(position);
                String link = usefulData.getLink();
                String title = usefulData.getTitle();
                int iid = usefulData.getId();
                Log.e("item_link", link);

                Intent intent = new Intent(view.getContext(), WebActivity.class);
                intent.putExtra("links", link);
                intent.putExtra("id", iid);
                intent.putExtra("title", title);
                view.getContext().startActivity(intent);
            }
        });

        holder.textView_shareUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                UsefulData usefulData = mdata.get(position);
                int userId = usefulData.getUserId();
                if (userId == -1) {
                    Toast.makeText(v.getContext(), "非wanAndroid用户", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(view.getContext(), ShareUserActivity.class);
                    intent.putExtra("userId", userId);
                    view.getContext().startActivity(intent);
                }
            }
        });

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                UsefulData usefulData = mdata.get(position);
                int userId = usefulData.getUserId();
                if (userId == -1) {
                    Toast.makeText(v.getContext(), "非wanAndroid用户", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(view.getContext(), ShareUserActivity.class);
                    intent.putExtra("userId", userId);
                    view.getContext().startActivity(intent);
                }
            }
        });

        holder.imageView_heard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                UsefulData usefulData = mdata.get(position);


            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PublicSquareAdapter.ViewHolder holder, int position) {
        UsefulData usefulData = mdata.get(position);
        holder.textView_title.setText(usefulData.getTitle());
        holder.textView_niceTime.setText(usefulData.getNiceDate());
        holder.textView_chapterName.setText(usefulData.getSuperChapterName());
        holder.textView_shareUser.setText(usefulData.getShareUser());
        Glide.with(mcontext).load(usefulData.getCollect()).into(holder.imageView_heard);
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

}
