package com.example.playandroid.Adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.viewpager.widget.PagerAdapter;

import com.example.playandroid.Data.AdvertCallBack;


public class GuideAdapter extends PagerAdapter {
    private List<View> viewList;
    private Context con;
    private int flag = 0;
    private AdvertCallBack callBack;

    public GuideAdapter(List<View> viewList) {
        super();
        this.viewList = viewList;
    }

    public GuideAdapter(List<View> viewList, AdvertCallBack callBack, int flag) {
        this.viewList = viewList;
        this.callBack=callBack;
        this.flag = flag;
    }

    @Override
    public int getCount() {
        return viewList.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(viewList.get(position));
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        View view = viewList.get(position);
        if (1 == flag) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callBack.callBack(position);
                }
            });
        }
        container.addView(view);
        return view;
    }

}