package com.example.playandroid.UI.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.playandroid.Adapter.ShareUserAdapter;
import com.example.playandroid.Data.UsefulData;
import com.example.playandroid.R;
import com.example.playandroid.Tool.GETConnection;
import com.example.playandroid.Tool.JsonAnalyze;
import com.example.playandroid.Tool.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class ShareUserActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private List<UsefulData> list = new ArrayList<>();
    private List<String> list2 = new ArrayList<>();
    private List<Integer> list3 = new ArrayList<>();
    private ShareUserAdapter dataAdapter = new ShareUserAdapter(list);
    GETConnection get_connection = new GETConnection();
    JsonAnalyze jsonAnalyze = new JsonAnalyze();
    private String responseData;
    ImageView progressBar ;
    private TextView textView_name;
    private TextView textView_level;
    private TextView textView_rank;
    private TextView textView_coinCount;

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ShareUserActivity.this);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(dataAdapter);
                    Log.e("list2", String.valueOf(list2.size()));
                    Log.e("list3", String.valueOf(list3.size()));
                    textView_rank.setText(" 排名"+list2.get(1)+" ");
                    textView_name.setText(list2.get(0));
                    textView_coinCount.setText("积分："+String.valueOf(list3.get(0)));
                    textView_level.setText(" Lv"+String.valueOf(list3.get(1))+" ");
                    progressBar.setVisibility(View.GONE);
                    break;
                case 2:
                    Toast.makeText(ShareUserActivity.this, "请求超时", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_user);

        recyclerView = findViewById(R.id.share_user_list_re);
        recyclerView.addItemDecoration(new SpacesItemDecoration(14));
        progressBar = findViewById(R.id.re_us_bar);
        textView_coinCount = findViewById(R.id.us_coinCount);
        textView_level = findViewById(R.id.us_level);
        textView_name = findViewById(R.id.us_name);
        textView_rank = findViewById(R.id.us_rank);

        Intent intent = getIntent();
        String userId = String.valueOf(intent.getIntExtra("userId",2));
        Log.e("userId",userId);

        new Thread(() -> {
            responseData = get_connection.sendGetNetRequest("https://www.wanandroid.com/user/"+userId+"/share_articles/0/json");
            Log.e("Thread","begin");
            if (responseData.equals("1")){
                showResponse(2);
            }else {
                jsonAnalyze.JsonDataGet_shareUser_list(responseData, list);
                jsonAnalyze.JsonDataGet_shareUser_data(responseData,list2,list3);
                showResponse(1);
            }
        }).start();

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