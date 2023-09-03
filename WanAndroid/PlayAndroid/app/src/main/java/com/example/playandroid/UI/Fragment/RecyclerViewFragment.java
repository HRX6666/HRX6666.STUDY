package com.example.playandroid.UI.Fragment;
import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.playandroid.Adapter.CommonsAdapter;
import com.example.playandroid.Data.ErrorMsgData;
import com.example.playandroid.Data.UsefulData;
import com.example.playandroid.R;
import com.example.playandroid.Tool.GETConnection_2;
import com.example.playandroid.Tool.JsonAnalyze;
import com.example.playandroid.Tool.SpacesItemDecoration;
import com.example.playandroid.UI.Activity.WebActivity;
import com.example.playandroid.UI.Wight.LuRecyclerView;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;
import com.scwang.smart.refresh.layout.wrapper.RefreshHeaderWrapper;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class RecyclerViewFragment extends Fragment {
    private View view;
    private RecyclerView recyclerView;
    private List<UsefulData> list = new ArrayList<>();
    private CommonsAdapter dataAdapter = new CommonsAdapter(list);
    GETConnection_2 get_connection = new GETConnection_2();
    JsonAnalyze jsonAnalyze = new JsonAnalyze();
    int i=0;
    private String top_responseData;
    private ImageView imageView;
    private String responseData;
    private String responseData1;
    private String responseData2;
    private String responseData3;
    private String responseData4;
    private String responseData5;
    private String responseData6;
    private String responseData7;
    private String responseData8;

    private String cook;
    ProgressBar progressBar ;
    Drawable drawable;
    SmartRefreshLayout refreshLayout;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(dataAdapter);
                    //progressBar.setVisibility(View.GONE)
                    imageView.setVisibility(View.GONE);
                    Log.e("UIchange", "ui");
                    break;
                case 2:
                    Toast.makeText(getActivity(), "请求超时", Toast.LENGTH_SHORT).show();
                    break;

            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_recycler_view, container, false);
        recyclerView = view.findViewById(R.id.recycle_v);
        refreshLayout=view.findViewById(R.id.refresh_layout);
        recyclerView.addItemDecoration(new SpacesItemDecoration(14));
        progressBar = view.findViewById(R.id.re_bar1);
        imageView=view.findViewById(R.id.iv_ry_em);
        setProgressDrawable(progressBar,R.drawable.pb_bg);
        SharedPreferences save_da = getActivity().getSharedPreferences("cook_data", MODE_PRIVATE);
        cook = save_da.getString("cookie", "");
        refreshLayout = (SmartRefreshLayout) this.refreshLayout.setRefreshFooter(new ClassicsFooter(getActivity()));
        refreshLayout=(SmartRefreshLayout)this.refreshLayout.setRefreshHeader(new ClassicsHeader(getActivity()));
        new Thread(() -> {
            top_responseData = get_connection.sendGetNetRequest("https://www.wanandroid.com/article/top/json",cook);
            responseData = get_connection.sendGetNetRequest("https://www.wanandroid.com/article/list/"+"0/json",cook);
            responseData1 = get_connection.sendGetNetRequest("https://www.wanandroid.com/article/list/"+"1/json",cook);
            responseData2= get_connection.sendGetNetRequest("https://www.wanandroid.com/article/list/"+"2/json",cook);
            responseData3 = get_connection.sendGetNetRequest("https://www.wanandroid.com/article/list/"+"3/json",cook);
            responseData4 = get_connection.sendGetNetRequest("https://www.wanandroid.com/article/list/"+"4/json",cook);
//            responseData5 = get_connection.sendGetNetRequest("https://www.wanandroid.com/article/list/"+"5/json",cook);
//            responseData6 = get_connection.sendGetNetRequest("https://www.wanandroid.com/article/list/"+"6/json",cook);
//            responseData7 = get_connection.sendGetNetRequest("https://www.wanandroid.com/article/list/"+"7/json",cook);
//            responseData8 = get_connection.sendGetNetRequest("https://www.wanandroid.com/article/list/"+"8/json",cook);
//            responseData9 = get_connection.sendGetNetRequest("https://www.wanandroid.com/article/list/"+"9/json",cook);
//            responseData10 = get_connection.sendGetNetRequest("https://www.wanandroid.com/article/list/"+"10/json",cook);
//            responseData12 = get_connection.sendGetNetRequest("https://www.wanandroid.com/article/list/"+"12/json",cook);
//            responseData13 = get_connection.sendGetNetRequest("https://www.wanandroid.com/article/list/"+"13/json",cook);
//            responseData14 = get_connection.sendGetNetRequest("https://www.wanandroid.com/article/list/"+"14/json",cook);
//            responseData15 = get_connection.sendGetNetRequest("https://www.wanandroid.com/article/list/"+"15/json",cook);
//            responseData16 = get_connection.sendGetNetRequest("https://www.wanandroid.com/article/list/"+"16/json",cook);
            if (top_responseData.equals("1")||responseData.equals("1")){
                showResponse(2);
            }else {
                jsonAnalyze.JsonDataGet_top_article(top_responseData, list);
                jsonAnalyze.JsonDataGet_article(responseData, list);
                refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
                    @Override
                    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                        i++;
                        if(i==1){
                            jsonAnalyze.JsonDataGet_article(responseData1, list);
                        }
                        if(i==2){
                            jsonAnalyze.JsonDataGet_article(responseData2, list);
                        }if(i==3){
                            jsonAnalyze.JsonDataGet_article(responseData3, list);
                        }
                        if(i==4){
                            jsonAnalyze.JsonDataGet_article(responseData4, list);
                        }if(i==5){
                            jsonAnalyze.JsonDataGet_article(responseData5, list);
                        }if(i==6){
                            jsonAnalyze.JsonDataGet_article(responseData6, list);
                        }if(i==7){
                            jsonAnalyze.JsonDataGet_article(responseData7, list);
                        }if(i==8){
                            jsonAnalyze.JsonDataGet_article(responseData8, list);
                        }

                        Log.e("UIchange", i+"xx");
                        dataAdapter.notifyDataSetChanged();
                        refreshLayout.finishLoadMore(2000);
                    }

                    @Override
                    public void onRefresh(@NonNull RefreshLayout refreshLayout) {

                    }
                });
                showResponse(1);
            }
            Log.e("list2", String.valueOf(list));
        }).start();

        return view;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
    @SuppressLint("NewApi")
    public static void setProgressDrawable(@NonNull ProgressBar bar, @DrawableRes int resId) {
        Drawable layerDrawable = bar.getResources().getDrawable(resId);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            Drawable d = getMethod("tileify", bar, new Object[] { layerDrawable, false });
            bar.setProgressDrawable(d);
        } else {
            bar.setProgressDrawableTiled(layerDrawable);
        }
    }

    private static Drawable getMethod(String methodName, Object o, Object[] paras) {
        Drawable newDrawable = null;
        try {
            Class<?> c[] = new Class[2];
            c[0] = Drawable.class;
            c[1] = boolean.class;
            Method method = ProgressBar.class.getDeclaredMethod(methodName, c);
            method.setAccessible(true);
            newDrawable = (Drawable) method.invoke(o, paras);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return newDrawable;
    }

}
