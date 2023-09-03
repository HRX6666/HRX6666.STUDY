package com.example.playandroid.UI.Fragment;


import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.playandroid.Adapter.SubilsDataAdapter;

import com.example.playandroid.Data.SubilsData;

import com.example.playandroid.R;
import com.example.playandroid.Tool.GETConnection;
import com.example.playandroid.Tool.JsonAnalyze;

import java.util.ArrayList;
import java.util.List;


public class PublicSquareFragment extends Fragment {
    private View view;
    private ListView listView;
    private ListView listView2;
    private List<SubilsData> list=new ArrayList<>();
    GETConnection getConnection=new GETConnection();
    private SubilsDataAdapter subilsDataAdapter=new SubilsDataAdapter(list);
    JsonAnalyze jsonAnalyze=new JsonAnalyze();
    private RecyclerView recyclerView;
    private String responseData;
    private ProgressBar progressBar;
    private ImageView imageView;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 1);
                    recyclerView.setAdapter(subilsDataAdapter);
                    recyclerView.setLayoutManager(layoutManager);
                    progressBar.setVisibility(View.GONE);
                    imageView.setVisibility(View.GONE);
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
        view = inflater.inflate(R.layout.fragment_public_recycler, container, false);
        recyclerView = view.findViewById(R.id.sut_recycler);
        list.clear();
        initView();
        new Thread(() -> {
            responseData = getConnection.sendGetNetRequest("https://www.wanandroid.com/chapter/547/sublist/json");
            if (responseData.equals("1")) {
                showResponse(2);
            } else {
                jsonAnalyze.JsonDataGet_subils_tree(responseData, list);
                showResponse(1);
            }
        }).start();

        return view;
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

    private void initView() {
        progressBar = view.findViewById(R.id.re_su_bar);
        imageView=view.findViewById(R.id.iv_su_em);
    }

}