package com.example.playandroid.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.playandroid.Data.SubilsData;
import com.example.playandroid.Data.UsefulData;
import com.example.playandroid.R;
import com.example.playandroid.UI.Activity.SubilsActivity;
import com.example.playandroid.UI.Activity.WebActivity;

import java.util.List;

public class ToolAdapter extends RecyclerView.Adapter<ToolAdapter.ViewHolder> {
    private List<UsefulData> mdata;
    private Context mcontext;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView_title;
        TextView textView_chapterName;
        TextView textView_desc;
        TextView textView_shareUser;

        ImageView imageView;


        public ViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.s_image);
            textView_chapterName = view.findViewById(R.id.s_chapterName);
            textView_desc = view.findViewById(R.id.s_desc);
            textView_title = view.findViewById(R.id.s_title);
            textView_shareUser=view.findViewById(R.id.s_shareUser);
        }
    }

    public ToolAdapter(List<UsefulData> mdata) {
        this.mdata = mdata;
    }

    @NonNull
    @Override
    public ToolAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (mcontext == null) {
            mcontext = parent.getContext();
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_subils_item, parent, false);
        final ToolAdapter.ViewHolder holder = new ToolAdapter.ViewHolder(view);

        holder.textView_desc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                UsefulData subilsData = mdata.get(position);
                Boolean isExpend = subilsData.getaBoolean();
                if (isExpend) {
                    holder.textView_desc.setMinLines(0);
                    holder.textView_desc.setMaxLines(Integer.MAX_VALUE);
                    subilsData.setaBoolean(false);
                } else {
                    holder.textView_desc.setLines(2);
                    subilsData.setaBoolean(true);
                }
            }
        });

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                UsefulData subilsData  = mdata.get(position);
                int id =  subilsData.getId();
                Intent intent=new Intent(view.getContext(), WebActivity.class);
                intent.putExtra("id",id);
                view.getContext().startActivity(intent);
            }
        });
        holder.textView_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                UsefulData subilsData  = mdata.get(position);
                int id = subilsData .getId();
                String title =  subilsData.getTitle();
                Intent intent=new Intent(view.getContext(), WebActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("title",title);
                view.getContext().startActivity(intent);
            }
        });

        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull ToolAdapter.ViewHolder holder, int position) {
        UsefulData subilsData = mdata.get(position);
        holder.textView_title.setText(subilsData.getTitle());
        holder.textView_shareUser.setText(subilsData.getAuthor());
        holder.textView_desc.setText(subilsData.getDesc());
        //Glide.with(mcontext).load(subilsData.getCover()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

}
