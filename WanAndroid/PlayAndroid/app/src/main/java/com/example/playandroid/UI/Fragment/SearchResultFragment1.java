package com.example.playandroid.UI.Fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.playandroid.Adapter.CommonsAdapter;
import com.example.playandroid.Data.UsefulData;
import com.example.playandroid.R;
import com.example.playandroid.Tool.JsonAnalyze;
import com.example.playandroid.Tool.POSTConnection_1;
import com.example.playandroid.Tool.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class SearchResultFragment1 extends Fragment {
    View view;
    POSTConnection_1 postConnection_1 = new POSTConnection_1();
    private RecyclerView recyclerView;
    private List<UsefulData> list = new ArrayList<>();
    private CommonsAdapter commonsAdapter = new CommonsAdapter(list);
    JsonAnalyze jsonAnalyze = new JsonAnalyze();
    private String responseData = null;
    HashMap<String, String> hashMap = new HashMap<>();
    TextView textView;
    private String key;
    private String cook;
    private ImageView progressBar;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case 1:
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(commonsAdapter);
                    textView.setText("搜索词:" + key);

                    progressBar.setVisibility(View.GONE);
                    break;
                case 2:
                    Toast.makeText(getActivity(), "请求超时", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.fragment_saerch_result1,container,false);
        initView();
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("cook_data", MODE_PRIVATE);
        cook = sharedPreferences.getString("cookie", "");

        list.clear();
        hashMap.clear();
        key = getArguments().getString("k");//返回实例化片段时提供的参数”k"
        hashMap.put("k", key);

        new Thread(() -> {
            responseData  = postConnection_1.sendGetNetRequest("https://www.wanandroid.com/article/query/0/json", hashMap, cook);
            if (responseData.equals("1")) {
                showResponse(2);
            } else {
                jsonAnalyze.JsonDataGet_article(responseData, list);
                showResponse(1);
            }
        }).start();

        // Inflate the layout for this fragment
        return view;
    }



    public void initView() {
        recyclerView = view.findViewById(R.id.search_result_v);
        recyclerView.addItemDecoration(new SpacesItemDecoration(14));
        textView = view.findViewById(R.id.search_key);
        progressBar = view.findViewById(R.id.f_search_bar_1);

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