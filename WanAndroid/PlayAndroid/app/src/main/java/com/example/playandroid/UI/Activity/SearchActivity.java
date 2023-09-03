package com.example.playandroid.UI.Activity;



import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.playandroid.Adapter.KnowledgeTreeListAdapter;
import com.example.playandroid.Data.TreeData;
import com.example.playandroid.R;
import com.example.playandroid.Tool.GETConnection;
import com.example.playandroid.Tool.JsonAnalyze;
import com.example.playandroid.UI.Fragment.SearchResultFragment1;
import com.example.playandroid.UI.Fragment.SearchResultFragment2;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    SearchResultFragment1 searchResultFragment = new SearchResultFragment1();
    SearchResultFragment2 searchResultFragment2 = new SearchResultFragment2();
    private Button button;
    private EditText editText;
    private boolean aBoolean = true;
    private boolean first_click = true;

    private ListView listView;
    private List<TreeData> list = new ArrayList<>();
    GETConnection get_connection = new GETConnection();
    JsonAnalyze jsonAnalyze = new JsonAnalyze();
    private String responseData;
    private String name;
    private TextView textView_cancel;

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    KnowledgeTreeListAdapter adapter = new KnowledgeTreeListAdapter(SearchActivity.this, R.layout.simple_list_time, list);
                    listView.setAdapter((ListAdapter) adapter);
                    break;
                case 2:
                    Toast.makeText(SearchActivity.this, "请求超时", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        button = findViewById(R.id.search_bt);
        editText = findViewById(R.id.search_s);
        textView_cancel = findViewById(R.id.search_cancel);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("k", String.valueOf(editText.getText()));
                searchResultFragment.setArguments(bundle);
                first_click=false;

                if (aBoolean) {
                    FragmentManager manager = getSupportFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.replace(R.id.search_f, searchResultFragment);
                    transaction.commit();
                    aBoolean = false;
                } else {
                    FragmentManager manager = getSupportFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.replace(R.id.search_f, searchResultFragment2);
                    transaction.commit();
                    aBoolean = true;
                }

            }
        });

        textView_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                if (searchResultFragment.isVisible()){
                    transaction.hide(searchResultFragment);
                    transaction.commit();
                    Log.e("hide","hide1");
                }else if (searchResultFragment2.isVisible()){
                    transaction.hide(searchResultFragment2);
                    transaction.commit();
                    Log.e("hide","hide2");
                }else {
                    finish();
                }
            }
        });

        listView = findViewById(R.id.hot_key);
        new Thread(() -> {
            responseData = get_connection.sendGetNetRequest("https://www.wanandroid.com//hotkey/json");
            if (responseData.equals("1")) {
                showResponse(2);
            } else {
                list.clear();
                jsonAnalyze.JsonDataGet_hotkey(responseData, list);
                showResponse(1);
            }
        }).start();
        
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TreeData treeData = list.get(position);
                name = treeData.getName();
                Bundle bundle = new Bundle();
                bundle.putString("k", name);
                searchResultFragment.setArguments(bundle);
                searchResultFragment2.setArguments(bundle);
                if (first_click) {
                    first_click = false;
                    FragmentManager manager = getSupportFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.replace(R.id.search_f, searchResultFragment);
                    transaction.commit();

                } else if(searchResultFragment.isHidden()){
                    FragmentManager manager = getSupportFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.replace(R.id.search_f, searchResultFragment2);
                    transaction.commit();
                } else {
                    FragmentManager manager = getSupportFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.replace(R.id.search_f, searchResultFragment);
                    transaction.commit();
                }

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