package com.example.playandroid.UI.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

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

import com.example.playandroid.Adapter.ProjectActivityAdapter;
import com.example.playandroid.Data.UsefulData;
import com.example.playandroid.R;
import com.example.playandroid.Tool.GETConnection;
import com.example.playandroid.Tool.JsonAnalyze;

import java.util.ArrayList;
import java.util.List;

public class ProjectActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<UsefulData> list = new ArrayList<>();
    private ProjectActivityAdapter dataAdapter = new ProjectActivityAdapter(list);
    GETConnection get_connection = new GETConnection();
    JsonAnalyze jsonAnalyze = new JsonAnalyze();
    private String responseData;
    private ImageView progressBar;
    private TextView textView_title;
    private ImageView imageView;

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(dataAdapter);
                    progressBar.setVisibility(View.GONE);
                    break;
                case 2:
                    Toast.makeText(ProjectActivity.this, "请求超时", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);
        recyclerView = findViewById(R.id.project_at);
        progressBar = findViewById(R.id.re_pr_ac_bar);
        textView_title = findViewById(R.id.project_title);
        imageView = findViewById(R.id.pr_back);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        int id = intent.getIntExtra("id", 0);
        String cid = String.valueOf(id);
        Log.e("ididid", cid);
        textView_title.setText(name);

        list.clear();

        new Thread(() -> {
            responseData = get_connection.sendGetNetRequest("https://www.wanandroid.com/project/list/0/json?cid=" + id);
            if (responseData.equals("1")) {
                showResponse(2);
            } else {
                jsonAnalyze.JsonDataGet_project_list(responseData, list);
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