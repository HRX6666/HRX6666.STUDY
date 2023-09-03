package com.example.playandroid.Adapter;

import android.content.Context;
import android.content.Intent;
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
import com.example.playandroid.Data.SubilsData;
import com.example.playandroid.Data.UsefulData;
import com.example.playandroid.R;
import com.example.playandroid.UI.Activity.ShareUserActivity;
import com.example.playandroid.UI.Activity.WebActivity;

import java.util.List;

public class SubilsActivityAdapter extends RecyclerView.Adapter<SubilsActivityAdapter.ViewHolder> {
    private List<UsefulData> mdata;
    private Context mcontext;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView_title;
        public ViewHolder(View view) {
            super(view);
            textView_title = view.findViewById(R.id.su_tree_name);
        }
    }

    public SubilsActivityAdapter(List<UsefulData> mdata) {
        this.mdata = mdata;
    }

    @NonNull
    @Override
    public SubilsActivityAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (mcontext == null) {
            mcontext = parent.getContext();
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_subilsitem_item, parent, false);
        final SubilsActivityAdapter.ViewHolder holder = new SubilsActivityAdapter.ViewHolder(view);


        holder.textView_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                UsefulData subilsData = mdata.get(position);
                int id = subilsData.getId();
                String link = subilsData.getLink();
                String title = subilsData.getTitle();
                Intent intent = new Intent(view.getContext(), WebActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("links", link);
                intent.putExtra("title", title);
                view.getContext().startActivity(intent);
            }
        });

        return holder;
    }

        @Override
        public void onBindViewHolder (@NonNull SubilsActivityAdapter.ViewHolder holder,int position)
        {
            UsefulData subilsData = mdata.get(position);
            holder.textView_title.setText(subilsData.getTitle());
        }

        @Override
        public int getItemCount () {
            return mdata.size();
        }


    }

