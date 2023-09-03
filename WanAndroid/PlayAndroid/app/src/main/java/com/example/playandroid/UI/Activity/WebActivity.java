package com.example.playandroid.UI.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.playandroid.Data.ErrorMsgData;
import com.example.playandroid.R;
import com.example.playandroid.Tool.JsonAnalyze;
import com.example.playandroid.Tool.POSTConnection_1;
import com.example.playandroid.Tool.POSTConnection_2;

import java.util.HashMap;

public class WebActivity extends AppCompatActivity {
    private String link;
    private int id;
    private String cid;
    private String title;

    private String cook;
    ErrorMsgData errorMsgData1 = new ErrorMsgData();
    ErrorMsgData errorMsgData2 = new ErrorMsgData();
    ErrorMsgData errorMsgData3 = new ErrorMsgData();
    private String re;
    private String re2;
    private String re3;
    JsonAnalyze jsonAnalyze = new JsonAnalyze();
    POSTConnection_2 post_connection_2 = new POSTConnection_2();
    POSTConnection_1 post_connection = new POSTConnection_1();

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    Toast.makeText(WebActivity.this, errorMsgData1.getErrorMsg(), Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    Toast.makeText(WebActivity.this, errorMsgData2.getErrorMsg(), Toast.LENGTH_SHORT).show();
                    break;
                case 3:
                    Toast.makeText(WebActivity.this, "收藏成功", Toast.LENGTH_SHORT).show();
                    break;
                case 4:
                    Toast.makeText(WebActivity.this, "分享成功", Toast.LENGTH_SHORT).show();
                    break;
                case 5:
                    Toast.makeText(WebActivity.this, errorMsgData3.getErrorMsg(), Toast.LENGTH_SHORT).show();
                    break;
                case 6:
                    Toast.makeText(WebActivity.this, "请求超时", Toast.LENGTH_SHORT).show();
//                case 7:
//                    Toast.makeText(WebActivity.this,"取消收藏",Toast.LENGTH_SHORT).show();

            }
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_collect:
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
                break;
            case R.id.add_share:
                new Thread(() -> {
                    HashMap<String, String> map = new HashMap<>();
                    map.put("title", title);
                    map.put("link", link);
                    re2 = post_connection.sendGetNetRequest("https://www.wanandroid.com/lg/user_article/add/json", map, cook);
                    if (re2.equals("1")){
                        showResponse(6);
                    }else {
                        jsonAnalyze.JsonDataGet_share_web(re2, errorMsgData2);
                        if (errorMsgData2.getErrorCode()== 0) {
                            showResponse(4);
                        } else {
                            showResponse(2);
                        }
                        Log.e("re2", re2);
                    }

                }).start();
                break;
            case R.id.web_collect:
                new Thread(() -> {
                    HashMap<String, String> map = new HashMap<>();
                    map.put("name", title);
                    map.put("link", link);
                    re3 = post_connection.sendGetNetRequest("https://www.wanandroid.com/lg/collect/addtool/json", map, cook);
                    if (re3.equals("1")){
                        showResponse(6);
                    }else {
                        jsonAnalyze.JsonDataGet_share_web(re3,errorMsgData3);
                        if (errorMsgData3.getErrorCode() == 0) {
                            showResponse(3);
                        } else {
                            showResponse(5);
                        }
                        Log.e("re3", re3);
                    }

                }).start();


        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        SharedPreferences save_da = getSharedPreferences("cook_data", MODE_PRIVATE);
        cook = save_da.getString("cookie", "");
        Log.e("cookie", cook);
        Intent intent = getIntent();
        link = intent.getStringExtra("links");
        id = intent.getIntExtra("id", 0);
        title = intent.getStringExtra("title");
        cid = String.valueOf(id);

        WebView webView = (WebView) findViewById(R.id.web_view);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(link);

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