package com.example.playandroid.Adapter;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.playandroid.Data.CollectData;
import com.example.playandroid.Data.ErrorMsgData;
import com.example.playandroid.Data.MessageData;
import com.example.playandroid.R;
import com.example.playandroid.Tool.JsonAnalyze;
import com.example.playandroid.Tool.POSTConnection_2;
import com.example.playandroid.UI.Activity.WebActivity;

import java.util.List;

public class MyMessageAdapter extends RecyclerView.Adapter<MyMessageAdapter.ViewHolder> {
    private List<MessageData> mdata;
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView message;
        TextView fromuser,title,tag;
        TextView time;

        public ViewHolder(View view) {
            super(view);
            fromuser = view.findViewById(R.id.msg_fromuser);
            time = view.findViewById(R.id.msg_time);
            title= view.findViewById(R.id.msg_title);
            tag= view.findViewById(R.id.msg_tag);
            message = view.findViewById(R.id.msg_msg);

        }
    }

    public MyMessageAdapter(List<MessageData> mdata) {
        this.mdata = mdata;
    }

    @NonNull
    @Override
    public MyMessageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_item, parent, false);
        final  MyMessageAdapter.ViewHolder holder = new  MyMessageAdapter.ViewHolder(view);
        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                MessageData usefulData = mdata.get(position);
                String link = usefulData.getLink();
                String title = usefulData.getTitle();
                int iid = usefulData.getId();
                Log.e("mesge_link", link);

                Intent intent = new Intent(view.getContext(), WebActivity.class);
                intent.putExtra("links", link);
                intent.putExtra("id", iid);
                intent.putExtra("title", title);
                view.getContext().startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyMessageAdapter.ViewHolder holder, int position) {
//        message;
//        fromuser,title,tag
//        time;
        MessageData usefulData = mdata.get(position);
        holder.message.setText(usefulData.getMessage());
        holder.fromuser.setText(usefulData.getFromUser());
        holder.title.setText(usefulData.getTitle());
        holder.tag.setText(usefulData.getTag());
        holder.time.setText(usefulData.getNiceDate());


    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

}
