package com.example.playandroid.UI.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.playandroid.Base.BaseApplication;
import com.example.playandroid.R;
import com.example.playandroid.UI.Fragment.HomePageFragment;
import com.example.playandroid.UI.Fragment.KnowledgeHierarchyFragment;
import com.example.playandroid.UI.Fragment.MyselfFragment;

import com.example.playandroid.UI.Fragment.PublicSquareFragment;

public class HomePageActivity extends BaseApplication {
    private ImageView iv_home,iv_knowledge_system,iv_public,iv_myself;
    private TextView tv_home,tv_knowledge,tv_public,tv_myself;


    HomePageFragment homePageFragment = new HomePageFragment();
    KnowledgeHierarchyFragment knowledgeHierarchyFragment = new KnowledgeHierarchyFragment();
    MyselfFragment myselfFragment = new MyselfFragment();
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.honm_page);
        initView();
        initOnclick();
        Context context=getApplicationContext();
        String channelId = "测试渠道";
        // Create an explicit intent for an Activity in your app
        Intent intent = new Intent(this,HomePageActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);//点击
        Notification.BigPictureStyle bigPictureStyle = new Notification.BigPictureStyle()
                .setBigContentTitle("玩转安卓")
                .setSummaryText("点击此处，一起开启Android学习之旅吧！")
                .bigPicture(BitmapFactory.decodeResource(context.getResources(),R.drawable.set));
        Notification notification = new Notification.Builder(context,channelId)
                .setContentTitle("玩转安卓")
                .setContentText("正在运行")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_lau_background)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.set))   //设置大图标
                .setStyle(bigPictureStyle)
                .setContentIntent(pendingIntent)
                .build();
// 2. 获取系统的通知管理器‘

        NotificationManager notificationManager = (NotificationManager)context.getSystemService(NOTIFICATION_SERVICE);
// 3. 创建NotificationChannel(这里传入的channelId要和创建的通知channelId一致，才能为指定通知建立通知渠道)
        NotificationChannel channel = new NotificationChannel(channelId,"正在运行", NotificationManager.IMPORTANCE_DEFAULT);
        notificationManager.createNotificationChannel(channel);
// 4. 发送通知
        notificationManager.notify(1123, notification);

    }

    private void initOnclick() {
        iv_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActionBar actionBar=getSupportActionBar();
                if(actionBar!=null){
                    actionBar.hide();
                }
                iv_home.setImageResource(R.drawable.home_page);
                iv_knowledge_system.setImageResource(R.drawable.no_knowledge_system);
                iv_myself.setImageResource(R.drawable.no_myself_spuare);
//                iv_public.setImageResource(R.drawable.no_public_square);
                tv_home.setTextColor(Color.parseColor("#BCBEEF"));
//                tv_public.setTextColor(Color.parseColor("#7A7A7A"));
                tv_knowledge.setTextColor(Color.parseColor("#7A7A7A"));
                tv_myself.setTextColor(Color.parseColor("#7A7A7A"));

                FragmentManager fragmentManager=getSupportFragmentManager();
                FragmentTransaction transaction=fragmentManager.beginTransaction();
                transaction.replace(R.id.home_page_v,homePageFragment);
                transaction.commit();
            }
        });
        iv_knowledge_system.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActionBar actionBar=getSupportActionBar();
                if(actionBar!=null){
                    actionBar.hide();
                }
                iv_home.setImageResource(R.drawable.no_home_page);
                iv_knowledge_system.setImageResource(R.drawable.knowledge_system);
                iv_myself.setImageResource(R.drawable.no_myself_spuare);
//                iv_public.setImageResource(R.drawable.no_public_square);
//                tv_public.setTextColor(Color.parseColor("#7A7A7A"));
                tv_home.setTextColor(Color.parseColor("#7A7A7A"));

                tv_knowledge.setTextColor(Color.parseColor("#BCBEEF"));
                tv_myself.setTextColor(Color.parseColor("#7A7A7A"));

                FragmentManager fragmentManager=getSupportFragmentManager();
                FragmentTransaction transaction=fragmentManager.beginTransaction();
                transaction.replace(R.id.home_page_v,knowledgeHierarchyFragment);
                transaction.commit();
            }
        });
        iv_myself.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActionBar actionBar=getSupportActionBar();
                if(actionBar!=null){
                    actionBar.hide();
                }
                iv_home.setImageResource(R.drawable.no_home_page);
                iv_knowledge_system.setImageResource(R.drawable.no_knowledge_system);
                iv_myself.setImageResource(R.drawable.myself_space);
//                iv_public.setImageResource(R.drawable.no_public_square);
//                tv_public.setTextColor(Color.parseColor("#7A7A7A"));
                tv_home.setTextColor(Color.parseColor("#7A7A7A"));

                tv_knowledge.setTextColor(Color.parseColor("#7A7A7A"));
                tv_myself.setTextColor(Color.parseColor("#BCBEEF"));

                FragmentManager fragmentManager=getSupportFragmentManager();
                FragmentTransaction transaction=fragmentManager.beginTransaction();
                transaction.replace(R.id.home_page_v,myselfFragment);
                transaction.commit();
            }
        });
//        iv_public.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ActionBar actionBar=getSupportActionBar();
//                if(actionBar!=null){
//                    actionBar.hide();
//                }
//                iv_home.setImageResource(R.drawable.no_home_page);
//                iv_knowledge_system.setImageResource(R.drawable.no_knowledge_system);
//                iv_myself.setImageResource(R.drawable.no_myself_spuare);
//                iv_public.setImageResource(R.drawable.public_square);
//                tv_home.setTextColor(Color.parseColor("#7A7A7A"));
//                tv_public.setTextColor(Color.parseColor("#BCBEEF"));
//                tv_knowledge.setTextColor(Color.parseColor("#7A7A7A"));
//                tv_myself.setTextColor(Color.parseColor("#7A7A7A"));
//
//                FragmentManager fragmentManager=getSupportFragmentManager();
//                FragmentTransaction transaction=fragmentManager.beginTransaction();
//                transaction.replace(R.id.home_page_v,publicSquareFragment);
//                transaction.commit();
//            }
//        });
         iv_home.callOnClick();
    }

    private void initView() {
    iv_home=findViewById(R.id.home_home);
    iv_knowledge_system=findViewById(R.id.knowledge_system);
    iv_myself=findViewById(R.id.myself_space);
//    iv_public=findViewById(R.id.public_square);
    tv_home=findViewById(R.id.home_tv);
    tv_knowledge=findViewById(R.id.system_tv);
//    tv_public=findViewById(R.id.public_tv);
    tv_myself=findViewById(R.id.my_tv);

    }


}