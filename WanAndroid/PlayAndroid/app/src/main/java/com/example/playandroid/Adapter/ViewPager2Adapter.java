package com.example.playandroid.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.playandroid.R;

import java.util.List;

public class ViewPager2Adapter extends RecyclerView.Adapter <ViewPager2Adapter.ViewPagerHolder>{
    private List<View> mViewList;
    @NonNull
    @Override
    public ViewPagerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewPagerHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pager,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewPagerHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return mViewList.size();
    }
    class ViewPagerHolder extends RecyclerView.ViewHolder{
        TextView textView;
        RelativeLayout container;

        public ViewPagerHolder(@NonNull View itemView) {
            super(itemView);
            container=itemView.findViewById(R.id.container);
            textView=itemView.findViewById(R.id.main_tv);

        }
    }
}
