package com.example.playandroid.Adapter;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.playandroid.Data.ErrorMsgData;
import com.example.playandroid.R;
import com.example.playandroid.Tool.JsonAnalyze;
import com.example.playandroid.Tool.POSTConnection_2;
import com.example.playandroid.Data.CollectData;
import com.example.playandroid.UI.Activity.WebActivity;

import java.util.List;

public class CollectArticleAdapter extends RecyclerView.Adapter<CollectArticleAdapter.ViewHolder> {
    private List<CollectData> mdata;
    POSTConnection_2 postConnection_2 = new POSTConnection_2();
    private String responseData;
    ErrorMsgData errorMsgData1 = new ErrorMsgData();
    JsonAnalyze jsonAnalyze = new JsonAnalyze();
    private View vi;

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    Toast.makeText(vi.getContext(),"取消成功",Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    Toast.makeText(vi.getContext(),"取消失败",Toast.LENGTH_SHORT).show();
                    break;
                case 3:
                    Toast.makeText(vi.getContext(),"请求超时",Toast.LENGTH_SHORT).show();

            }
        }
    };

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView_title;
        TextView textView_chapterName;
        TextView textView_niceTime;
        ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            textView_chapterName = view.findViewById(R.id.collect_article_chapterName);
            textView_niceTime = view.findViewById(R.id.collect_article_niceTime);
            textView_title = view.findViewById(R.id.collect_article_title);
            imageView = view.findViewById(R.id.collect_like);

        }
    }

    public CollectArticleAdapter(List<CollectData> mdata) {
        this.mdata = mdata;
    }

    @NonNull
    @Override
    public CollectArticleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ry_collect_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);

        SharedPreferences save_da = parent.getContext().getSharedPreferences("cook_data", MODE_PRIVATE);
        String cook = save_da.getString("cookie", "");

        holder.textView_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                CollectData usefulData = mdata.get(position);
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

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vi = v;
                int position = holder.getAdapterPosition();
                CollectData usefulData = mdata.get(position);
                String originId = String.valueOf(usefulData.getOriginId());
                holder.imageView.setImageResource(R.drawable.unselect);
                new Thread(() -> {
                    Log.e("取消收藏", "begin");
                    responseData = postConnection_2.sendGetNetRequest("https://www.wanandroid.com/lg/uncollect_originId/" + originId + "/json", cook);
                    if (responseData.equals("1")){
                        showResponse(3);
                    }else {
                        jsonAnalyze.JsonDataGet_share_web(responseData, errorMsgData1);
                        if (errorMsgData1.getErrorCode() == 0) {
                            showResponse(1);
                        } else {
                            showResponse(2);
                        }
                    }

                }).start();
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CollectArticleAdapter.ViewHolder holder, int position) {
        CollectData usefulData = mdata.get(position);
        holder.textView_title.setText(usefulData.getTitle());
        holder.textView_niceTime.setText(usefulData.getNiceDate());
        holder.textView_chapterName.setText(usefulData.getChapterName());

    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    private void showResponse(int num) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message message = new Message();
                message.what = num;
                handler.sendMessage(message);
            }
        }).start();
    }
}
