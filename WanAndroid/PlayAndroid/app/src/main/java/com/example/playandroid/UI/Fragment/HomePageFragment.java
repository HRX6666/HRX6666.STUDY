package com.example.playandroid.UI.Fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.playandroid.R;
import com.example.playandroid.UI.Activity.SearchActivity;
import com.example.playandroid.UI.Activity.WebActivity;
import com.google.android.material.tabs.TabLayout;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoaderInterface;

import java.util.ArrayList;
import java.util.List;

public class HomePageFragment extends Fragment {
    private View view;
    private Banner home_banner;
    private ViewPager home_content;
    private List<View> list1 = new ArrayList<>();
    private List<String> list = new ArrayList<>();
    private List<String> list_TITLE = new ArrayList<>();
    TabLayout tabLayout;
    TextView text;
    private ImageView imageView1;
    List<Fragment> fragmentList = new ArrayList<>();
    List<String> fragmentTitle = new ArrayList<>();
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    break;
            }

        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home, container, false);
        initView();
        bannetr();
        fragmentList.clear();
        fragmentTitle.clear();
        fragmentTitle.add("精选文章");
        fragmentTitle.add("常用网站");
        fragmentTitle.add("广场");
        fragmentTitle.add("项目");
        fragmentTitle.add("教程");
        fragmentList.add(new RecyclerViewFragment());
        fragmentList.add(new WebRecyclerViewFragment());
        fragmentList.add(new PublicSquareRecyclerViewFragment());
        fragmentList.add(new ProjectRecyclerFragment());
        fragmentList.add(new PublicSquareFragment());

        text.setTextColor(Color.BLACK);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });
        home_content.setAdapter(new ViewPagerAdapter(getChildFragmentManager(),
                HomePageFragment.ViewPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT));
        tabLayout.setupWithViewPager(home_content);
        home_content.setOffscreenPageLimit(2);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        return view;
    }
    private void initView() {
        home_banner = view.findViewById(R.id.home_banner);
        home_content = view.findViewById(R.id.home_content);
        tabLayout = view.findViewById(R.id.tabs_1);
        text = view.findViewById(R.id.home_search);

    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        public ViewPagerAdapter(FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentTitle.get(position);
        }
    }
private void bannetr() {
    list.clear();
    list_TITLE.clear();
    list.add("https://www.wanandroid.com/blogimgs/42da12d8-de56-4439-b40c-eab66c227a4b.png");
    list.add("https://www.wanandroid.com/blogimgs/62c1bd68-b5f3-4a3c-a649-7ca8c7dfabe6.png");
    list.add("https://www.wanandroid.com/blogimgs/50c115c2-cf6c-4802-aa7b-a4334de444cd.png");
    list_TITLE.add("我们支持订阅啦");
    list_TITLE.add("我们新增了一个常用导航");
    list_TITLE.add("一起来做个APP吧");
    showResponse();
    //设置内置样式
    home_banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
    home_banner.setImageLoader(new MyLoader());
    //设置图片网址或地址的集合
    home_banner.setImages(list);
    //设置轮播图的标题集合
    home_banner.setBannerTitles(list_TITLE);
    //设置轮播间隔时间
    home_banner.setDelayTime(2000);
    //设置是否为自动轮播 默认是 “是”
    home_banner.isAutoPlay(true);
    //设置显示器的位置   小点点 左中右
    home_banner.setIndicatorGravity(BannerConfig.CENTER);
    //设置轮播图的监听  必须调用start() 启动轮播图
    home_banner.setOnBannerListener(this::OnBannerClick);
    home_banner.start();//一定不能缺start
}



    //banner 单击方法
    public void OnBannerClick(int position) {
       if(position==0){
           Intent intent = new Intent(view.getContext(), WebActivity.class);
           intent.putExtra("links", "https://www.wanandroid.com/blog/show/3352");
           intent.putExtra("title", "我们支持订阅啦");
           startActivity(intent);
       }
        if(position==1){
            Intent intent = new Intent(view.getContext(), WebActivity.class);
            intent.putExtra("links", "https://www.wanandroid.com/navi");
            intent.putExtra("title", "我们新增了一个常用导航Tab");
            startActivity(intent);
        }
        if(position==2){
            Intent intent = new Intent(view.getContext(), WebActivity.class);
            intent.putExtra("links", "https://www.wanandroid.com/blog/show/2");
            intent.putExtra("title", "一起来做个App吧");
            startActivity(intent);
        }
    }
    //图片加载类
    private class MyLoader implements ImageLoaderInterface {
        @Override
        public void displayImage(Context context, Object path, View imageView) {
            Glide.with(getActivity()).load((String)path).into((ImageView) imageView);
        }

        @Override
        public View createImageView(Context context) {
            return null;
        }
    }


    private void showResponse() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message message = new Message();
                message.what = 1;
                handler.sendMessage(message);
            }
        }).start();
    }

}