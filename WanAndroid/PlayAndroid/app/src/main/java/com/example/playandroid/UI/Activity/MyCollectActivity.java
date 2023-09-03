package com.example.playandroid.UI.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.playandroid.Adapter.CollectArticleAdapter;
import com.example.playandroid.Adapter.CollectWebRecyclerViewAdapter;
import com.example.playandroid.Data.CollectData;
import com.example.playandroid.Data.WebData;
import com.example.playandroid.R;
import com.example.playandroid.Tool.GETConnection_2;
import com.example.playandroid.Tool.JsonAnalyze;

import java.util.ArrayList;
import java.util.List;

public class MyCollectActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView recyclerView_web;
    private List<CollectData> list_collect_article = new ArrayList<>();
    private List<WebData> list_collect_web = new ArrayList<>();
    private List<String> list_user_data1 = new ArrayList<>();
    private List<Integer> list_user_data2 = new ArrayList<>();
    private CollectArticleAdapter dataAdapter = new CollectArticleAdapter(list_collect_article);
    private CollectWebRecyclerViewAdapter webRecyclerViewAdapter = new CollectWebRecyclerViewAdapter(list_collect_web);
    GETConnection_2 get_connection = new GETConnection_2();
    JsonAnalyze jsonAnalyze = new JsonAnalyze();
    private String responseData1;
    private String responseData2;
    private String responseData3;
    ImageView progressBar;
    private String userId;
    private String cook;
    private TextView textView_name;
    private TextView textView_level;
    private TextView textView_rank;
    private TextView textView_coinCount;
    private TextView textView_login;

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    textView_rank.setText(" 排名" + list_user_data1.get(1) + " ");
                    textView_name.setText(list_user_data1.get(0));
                    textView_coinCount.setText("积分：" + String.valueOf(list_user_data2.get(0)));
                    textView_level.setText(" Lv" + String.valueOf(list_user_data2.get(1)) + " ");
                    break;
                case 2:
                    Toast.makeText(MyCollectActivity.this, "请求超时", Toast.LENGTH_SHORT).show();
                    break;
                case 3:
                    textView_rank.setText(" 排名 0 ");
                    textView_coinCount.setText("积分：0");
                    textView_level.setText(" Lv 0 ");
                    break;
                case 4:
                    LinearLayoutManager layoutManager2 = new LinearLayoutManager(MyCollectActivity.this);
                    layoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
                    recyclerView_web.setLayoutManager(layoutManager2);
                    recyclerView_web.setAdapter(webRecyclerViewAdapter);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MyCollectActivity.this);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(dataAdapter);
                    progressBar.setVisibility(View.GONE);
                    Log.e("list_collect", String.valueOf(list_collect_article.size()));
                    Log.e("uichange_collect","collect_entry");

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_collect);
        recyclerView = findViewById(R.id.my_collect_list_re);
        recyclerView_web = findViewById(R.id.re_collect_web);
        textView_coinCount = findViewById(R.id.c_my_coinCount);
        textView_level = findViewById(R.id.c_my_level);
        textView_name = findViewById(R.id.c_my_name);
        textView_rank = findViewById(R.id.c_my_rank);
        textView_login = findViewById(R.id.log_in_text);
        progressBar = findViewById(R.id.re_my_collect_bar);
        SharedPreferences user_da = getSharedPreferences("user_data", MODE_PRIVATE);
        userId = String.valueOf(user_da.getInt("user_id", 0));
        SharedPreferences save_da = getSharedPreferences("cook_data", MODE_PRIVATE);
        cook = save_da.getString("cookie", "");

        list_collect_article.clear();
        list_collect_web.clear();
        if (userId.equals("0")) {
            textView_name.setText("未登录");
            progressBar.setVisibility(View.GONE);
            textView_login.setVisibility(View.VISIBLE);
            showResponse(3);
            Toast toast = Toast.makeText(MyCollectActivity.this, "请先登录", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        } else {
            new Thread(() -> {
                responseData1 = get_connection.sendGetNetRequest("https://www.wanandroid.com/user/" + userId + "/share_articles/0/json", cook);
                Log.e("Thread my data", "begin");
                if (responseData1.equals("1")) {
                    showResponse(2);
                } else {
                    jsonAnalyze.JsonDataGet_shareUser_data(responseData1, list_user_data1, list_user_data2);
                    showResponse(1);
                }
            }).start();

            new Thread(() -> {
                responseData2 = get_connection.sendGetNetRequest("https://www.wanandroid.com/lg/collect/list/0/json", cook);
                responseData3 = get_connection.sendGetNetRequest("https://www.wanandroid.com/lg/collect/usertools/json", cook);
                Log.e("Thread collect", "begin");
                if (responseData2.equals("1")||responseData3.equals("1")) {
                    showResponse(2);
                } else {
                    jsonAnalyze.JsonDataGet_collect(responseData2, list_collect_article);
                    jsonAnalyze.JsonDataGet_collect_web(responseData3,list_collect_web);
                    showResponse(4);
                }
            }).start();
        }

        textView_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyCollectActivity.this, Enter.class);
                startActivity(intent);
            }
        });
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
