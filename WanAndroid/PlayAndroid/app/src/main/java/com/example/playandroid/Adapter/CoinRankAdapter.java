package com.example.playandroid.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.playandroid.Data.CoinData;
import com.example.playandroid.R;

import java.util.List;
public class CoinRankAdapter extends RecyclerView.Adapter<CoinRankAdapter.ViewHolder> {
    private List<CoinData> mdata;
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_rank,tv_name,tv_coin,tv_level;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_coin=itemView.findViewById(R.id.c_r_rank);
            tv_level=itemView.findViewById(R.id.c_r_level);
            tv_name=itemView.findViewById(R.id.c_r_name);
            tv_rank=itemView.findViewById(R.id.c_r_rank);
        }
    }
    public CoinRankAdapter(List<CoinData> mdata) {
        this.mdata = mdata;
    }
    @NonNull
    @Override
    public CoinRankAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.coin_rank_item,parent,false);
        final ViewHolder holder=new ViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull CoinRankAdapter.ViewHolder holder, int position) {
        CoinData ussdata=mdata.get(position);
        holder.tv_rank.setText(ussdata.getRank());
        holder.tv_name.setText(ussdata.getUsername());
        holder.tv_level.setText(ussdata.getLevel());
        holder.tv_coin.setText(ussdata.getCoinCount());
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }


}
