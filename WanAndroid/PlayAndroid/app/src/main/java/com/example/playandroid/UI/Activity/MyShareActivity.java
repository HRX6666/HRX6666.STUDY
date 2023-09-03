package com.example.playandroid.UI.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.playandroid.Adapter.PublicSquareAdapter;
import com.example.playandroid.Data.UsefulData;
import com.example.playandroid.R;
import com.example.playandroid.Tool.GETConnection_2;
import com.example.playandroid.Tool.JsonAnalyze;
import com.example.playandroid.Tool.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;


public class MyShareActivity extends AppCompatActivity {
    private View view;
    private RecyclerView recyclerView;
    private List<UsefulData> list = new ArrayList<>();
    private PublicSquareAdapter squareAdapter= new PublicSquareAdapter(list);
    GETConnection_2 getConnection_2=new GETConnection_2();
    JsonAnalyze jsonAnalyze=new JsonAnalyze();
    private String responseData;
    private ImageView imageView;
    private TextView tv_enter;
    private TextView tv_nan;
    String cook;
    private Handler handler=new Handler(){
        public void handleMessage(Message message){
            switch (message.what){
                case 1:
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MyShareActivity.this);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(squareAdapter);
                    imageView.setVisibility(View.GONE);
                    if (list.size()==0){
                        tv_nan.setVisibility(View.VISIBLE);
                    }
                    break;
                case 2:
                    Toast.makeText(MyShareActivity.this, "请求超时", Toast.LENGTH_SHORT).show();
                case 3:
                    Toast toast = Toast.makeText(MyShareActivity.this, "请先登录", Toast.LENGTH_SHORT);
                    imageView.setVisibility(View.GONE);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
            }
        }
    };
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_my_share);
        initView();
        SharedPreferences save=this.getSharedPreferences("cook_data", Context.MODE_PRIVATE);
        cook=save.getString("cookie","");
        list.clear();
        if(TextUtils.isEmpty(cook)){
            tv_enter.setVisibility(View.VISIBLE);
            showResponse(3);
        }else{
            new Thread(() -> {
                responseData = getConnection_2.sendGetNetRequest("https://www.wanandroid.com/user/lg/private_articles/1/json", cook);
                if (responseData.equals("1")) {
                    showResponse(2);
                } else {
                    jsonAnalyze.JsonDataGet_shareUser_list(responseData, list);
                    showResponse(1);
                }
            }).start();
        }
        tv_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MyShareActivity.this, Enter.class);
                startActivity(intent);
            }
        });
    }



    private void initView() {
        recyclerView=findViewById(R.id.my_share_re_v);
        recyclerView.addItemDecoration(new SpacesItemDecoration(14));//设置间距
        tv_enter=findViewById(R.id.s_log_in_text);
        tv_nan=findViewById(R.id.nan_article);
        imageView=findViewById(R.id.re_my_iv);
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