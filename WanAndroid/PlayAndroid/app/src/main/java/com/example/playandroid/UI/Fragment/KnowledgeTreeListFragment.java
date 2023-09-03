package com.example.playandroid.UI.Fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.playandroid.Adapter.KnowledgeTreeListAdapter;
import com.example.playandroid.Adapter.KnowledgeTreeListAdapter2;
import com.example.playandroid.Data.TreeData;
import com.example.playandroid.R;
import com.example.playandroid.Tool.GETConnection;
import com.example.playandroid.Tool.JsonAnalyze;
import com.example.playandroid.UI.Activity.KnowledgeActivity;

import java.util.ArrayList;
import java.util.List;


public class KnowledgeTreeListFragment extends Fragment {
    private View view;
    private ListView listView;
    private ListView listView2;
    private List<TreeData> list=new ArrayList<>();
    private List<TreeData>list2=new ArrayList<>();
    GETConnection getConnection=new GETConnection();
    JsonAnalyze jsonAnalyze=new JsonAnalyze();
    private String responseData;
    KnowledgeTreeListAdapter adapter;
    private ProgressBar progressBar;
    private ImageView imageView;
    private Context context;

    private Handler handler=new Handler(){
        public void handleMessage(Message msg){
            switch (msg.what){
                case 1:
                    adapter=new KnowledgeTreeListAdapter(context,R.layout.simple_list_time,list);
                    listView.setAdapter(adapter);
                   // progressBar.setVisibility(View.GONE);
                    imageView.setVisibility(View.GONE);
                    break;
                case 2:
                    KnowledgeTreeListAdapter2 adapter2 = new KnowledgeTreeListAdapter2(context, R.layout.simple_list_time2, list2);
                    listView2.setAdapter(adapter2);
                    break;
                case 3:
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
    public void onAttach(Context mcontext) {
        super.onAttach(context);
        context = mcontext;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_knowledge_tree_list,container,false);
        inieView();
        new Thread(()->{
            responseData=getConnection.sendGetNetRequest("https://www.wanandroid.com/tree/json");
            if(responseData.equals("1")){
                showResponse(3);
            }else
            {
                list.clear();
                jsonAnalyze.JsonDataGet_knowledge_tree(responseData,list);
                showResponse(1);
            }
        }).start();
        initOnClike();


        return view;
    }

    private void initOnClike() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TreeData treeData = list.get(position);
                String name = treeData.getName();
                adapter.selectedItemPosition(position);
                adapter.notifyDataSetChanged();
                list2.clear();
                jsonAnalyze.JsonDataGet_knowledge_tree_item(responseData, list2, name);
                showResponse(2);
            }
        });

        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TreeData treeData = list2.get(position);
                String name = treeData.getName();
                int id_ = treeData.getId();

                Intent intent = new Intent(getActivity(), KnowledgeActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("id", id_);
                view.getContext().startActivity(intent);

            }
        });
    }

    private void showResponse(int i) {
        new Thread(()->{
            Message message=new Message();
            message.what=i;//只能用来放数字，message.obj用来放任何类型
            handler.sendMessage(message);
        }).start();
    }

    private void inieView() {
        listView = view.findViewById(R.id.tree_list);
        listView2 = view.findViewById(R.id.tree_item);
        progressBar = view.findViewById(R.id.ls_tree_bar);
        imageView=view.findViewById(R.id.iv_tree_em);
    }
}