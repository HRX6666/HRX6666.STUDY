package com.example.playandroid.Adapter;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.playandroid.Data.WebData;
import com.example.playandroid.R;
import com.example.playandroid.UI.Activity.WebActivity;

import java.util.List;

public class WebRecyclerViewAdapter extends RecyclerView.Adapter<WebRecyclerViewAdapter.ViewHolder> {
    private List<WebData> mdata;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView_name;
        RelativeLayout linearLayout;

        public ViewHolder(View view) {
            super(view);
            textView_name = view.findViewById(R.id.web_item_name);
            linearLayout = view.findViewById(R.id.web_item_whole);
        }
    }

    public WebRecyclerViewAdapter(List<WebData> mdata) {
        this.mdata = mdata;
    }

    @NonNull
    @Override
    public WebRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ry_web_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                WebData webData = mdata.get(position);
                String link = webData.getLink();
                String name = webData.getName();
                Log.e("mesge_link",link);
                Log.e("mesge_name",name);

                Intent intent=new Intent(view.getContext(), WebActivity.class);
                intent.putExtra("links",link);
                intent.putExtra("title",name);
                view.getContext().startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull WebRecyclerViewAdapter.ViewHolder holder, int position) {
        WebData webData=mdata.get(position);
        holder.textView_name.setText(webData.getName());


    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }


}
