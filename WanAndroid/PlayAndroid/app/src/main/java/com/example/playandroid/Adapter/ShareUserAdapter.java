package com.example.playandroid.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.playandroid.Data.UsefulData;
import com.example.playandroid.R;
import com.example.playandroid.UI.Activity.WebActivity;

import java.util.List;

public class ShareUserAdapter extends RecyclerView.Adapter<ShareUserAdapter.ViewHolder>{

    private List<UsefulData> mdata;
    private Context mcontext;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView_title;
        TextView textView_superChapterName;
        TextView textView_chapterName;
        TextView textView_niceTime;
        TextView textView_top;
        TextView textView_shareUser;
        TextView textView_author;
        ImageView imageView_heard;

        public ViewHolder(View view) {
            super(view);
            textView_chapterName = view.findViewById(R.id.article_chapterName);
            textView_niceTime = view.findViewById(R.id.article_niceTime);
            textView_superChapterName = view.findViewById(R.id.article_superChapterName);
            textView_title = view.findViewById(R.id.article_title);
            textView_top = view.findViewById(R.id.top_article);
            textView_shareUser = view.findViewById(R.id.article_shareUser);
            textView_author = view.findViewById(R.id.article_ath);
            imageView_heard= view.findViewById(R.id.common_like);

        }
    }

    public ShareUserAdapter(List<UsefulData> mdata) {
        this.mdata = mdata;
    }

    @NonNull
    @Override
    public ShareUserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerviw_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
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
                int iid= usefulData.getId();
                Log.e("mesge_link",link);

                Intent intent=new Intent(view.getContext(), WebActivity.class);
                intent.putExtra("links",link);
                intent.putExtra("id",iid);
                intent.putExtra("title",title);
                view.getContext().startActivity(intent);
            }
        });


        return holder;
    }



    @Override
    public void onBindViewHolder(@NonNull ShareUserAdapter.ViewHolder holder, int position) {
        UsefulData usefulData = mdata.get(position);
        holder.textView_title.setText(usefulData.getTitle());
        holder.textView_superChapterName.setText(usefulData.getSuperChapterName());
        holder.textView_niceTime.setText(usefulData.getNiceDate());
        holder.textView_chapterName.setText(usefulData.getChapterName());
        holder.textView_top.setText(usefulData.getTop());
        holder.textView_shareUser.setText(usefulData.getShareUser());
        holder.textView_author.setText(usefulData.getAuthor());
        Glide.with(mcontext).load(usefulData.getCollect()).into(holder.imageView_heard);

    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

}
