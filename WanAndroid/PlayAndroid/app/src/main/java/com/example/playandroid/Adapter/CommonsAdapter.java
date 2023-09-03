package com.example.playandroid.Adapter;



import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.playandroid.Data.ArticleData;
import com.example.playandroid.Data.ErrorMsgData;
import com.example.playandroid.Data.UsefulData;
import com.example.playandroid.R;
import com.example.playandroid.Tool.JsonAnalyze;
import com.example.playandroid.Tool.POSTConnection_1;
import com.example.playandroid.Tool.POSTConnection_2;
import com.example.playandroid.Data.CollectData;
import com.example.playandroid.UI.Activity.ShareUserActivity;
import com.example.playandroid.UI.Activity.WebActivity;

import java.util.List;

public class CommonsAdapter extends RecyclerView.Adapter<CommonsAdapter.ViewHolder> {
    private List<UsefulData> mdata;
    private List<CollectData> mdata1;
    private List<ArticleData> mdata2;
    private Context mcontext;
    ErrorMsgData errorMsgData1 = new ErrorMsgData();
    ErrorMsgData errorMsgData2 = new ErrorMsgData();
    ErrorMsgData errorMsgData3 = new ErrorMsgData();
    JsonAnalyze jsonAnalyze = new JsonAnalyze();
    POSTConnection_2 post_connection_2 = new POSTConnection_2();
    POSTConnection_1 post_connection = new POSTConnection_1();
    private String responseData;
    private View vi;
    boolean iflike=true;
    private String re;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    Toast.makeText( vi.getContext(), errorMsgData1.getErrorMsg(), Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    Toast.makeText(vi.getContext(), errorMsgData2.getErrorMsg(), Toast.LENGTH_SHORT).show();
                    break;
                case 3:
                    Toast.makeText(vi.getContext(), "收藏成功", Toast.LENGTH_SHORT).show();
                    break;
                case 4:
                    Toast.makeText(vi.getContext(), "分享成功", Toast.LENGTH_SHORT).show();
                    break;
                case 5:
                    Toast.makeText(vi.getContext(), errorMsgData3.getErrorMsg(), Toast.LENGTH_SHORT).show();
                    break;
                case 6:
                    Toast.makeText(vi.getContext(), "请求超时", Toast.LENGTH_SHORT).show();
                    break;
                case 7:
                    Toast.makeText(vi.getContext(), "取消收藏",Toast.LENGTH_SHORT).show();
                    break;
                case 8:
                    Toast.makeText(vi.getContext(), "取消失败",Toast.LENGTH_SHORT).show();
                    break;

            }
        }
    };

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView_title;
        TextView textView_superChapterName;
        TextView textView_chapterName;
        TextView textView_niceTime;
        TextView textView_top;
        TextView textView_shareUser;
        TextView textView_author;
        ImageView imageView;
        LinearLayout layout;

        public ViewHolder(View view) {
            super(view);
            layout=view.findViewById(R.id.ly_ry);
            textView_chapterName = view.findViewById(R.id.article_chapterName);
            textView_niceTime = view.findViewById(R.id.article_niceTime);
            textView_superChapterName = view.findViewById(R.id.article_superChapterName);
            textView_title = view.findViewById(R.id.article_title);
            textView_top = view.findViewById(R.id.top_article);
            textView_shareUser = view.findViewById(R.id.article_shareUser);
            textView_author = view.findViewById(R.id.article_ath);
            imageView = view.findViewById(R.id.common_like);

        }
    }

    public CommonsAdapter(List<UsefulData> mdata) {
        this.mdata = mdata;
    }

    @NonNull
    @Override
    public CommonsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerviw_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        if (mcontext == null) {
            mcontext = parent.getContext();
        }
        SharedPreferences save_da = parent.getContext().getSharedPreferences("cook_data", MODE_PRIVATE);
        String cook = save_da.getString("cookie", "");


        holder.textView_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                UsefulData usefulData = mdata.get(position);
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

        holder.textView_author.setOnClickListener(new View.OnClickListener() {
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

        holder.textView_shareUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                UsefulData usefulData = mdata.get(position);
                int userId = usefulData.getUserId();
                Log.e("userId item", String.valueOf(userId));
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
                vi= v;

                int position = holder.getAdapterPosition();
                UsefulData usefulData = mdata.get(position);
                String cid = String.valueOf(usefulData.getId());
                if(iflike){
                    iflike=false;
                    holder.imageView.setImageResource(R.drawable.like);
                    new Thread(() -> {
                        re = post_connection_2.sendGetNetRequest("https://www.wanandroid.com/lg/collect/" + cid + "/json", cook);
                        if (re.equals("1")){
                            showResponse(6);
                        }else {
                            jsonAnalyze.JsonDataGet_share_web(re,errorMsgData1);
                            Log.e("errorCode1", String.valueOf(errorMsgData1.getErrorCode()));
                            if (errorMsgData1.getErrorCode() == 0) {
                                showResponse(3);
                            } else {
                                showResponse(1);
                            }
                            Log.e("re", re);
                        }

                    }).start();
                }else {
                    iflike=true;
//                    CollectData usefulData1 = mdata1.get(position);
//                    String originId = String.valueOf(usefulData1.getOriginId());
                    holder.imageView.setImageResource(R.drawable.unselect);
                    new Thread(() -> {
                        Log.e("取消收藏", "begin");
                        responseData = post_connection_2.sendGetNetRequest("https://www.wanandroid.com/lg/uncollect_originId/" + cid + "/json", cook);
                        if (responseData.equals("1")){
                            showResponse(6);
                        }else {
                            jsonAnalyze.JsonDataGet_share_web(responseData, errorMsgData1);
                            if (errorMsgData1.getErrorCode() == 0) {
                                showResponse(7);
                            } else {
                                showResponse(8);
                            }
                        }

                    }).start();
                }

            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CommonsAdapter.ViewHolder holder, int position) {
        UsefulData usefulData = mdata.get(position);
        holder.textView_title.setText(Html.fromHtml(usefulData.getTitle()));
        holder.textView_superChapterName.setText(usefulData.getSuperChapterName());
        holder.textView_niceTime.setText(usefulData.getNiceDate());
        holder.textView_chapterName.setText(usefulData.getChapterName());
        holder.textView_top.setText(usefulData.getTop());
        holder.textView_shareUser.setText(usefulData.getShareUser());
        holder.textView_author.setText(usefulData.getAuthor());
      Glide.with(mcontext).load(usefulData.getCollect()).into(holder.imageView);

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
