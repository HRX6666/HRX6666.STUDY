package com.example.playandroid.UI.Wight;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.animation.Interpolator;
import android.widget.Scroller;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;


public class BannerViewPager extends ViewPager {
    private Handler handler;
    private BannerViewPager bannerViewPager;
    private TaskRunnable taskRunnable;
    public static boolean mIsRunning = false; //是否正在执行

    public BannerViewPager(@NonNull Context context) {
         this(context,null);
    }

    public BannerViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        bannerViewPager=this;//滚动监听,空闲状态,手指触摸滑动,手指松开,惯性滑动
        OnPageChangeListener onPageChangeListener=new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                switch (state){
                    case ViewPager.SCROLL_STATE_IDLE://空闲状态
                        start();
                        break;
                    case ViewPager.SCROLL_STATE_DRAGGING://手指触摸滑动
                        stop();
                        break;
                    case SCROLL_STATE_SETTLING://手指松开，惯性滑动
                    break;
                }

            }
        };
        addOnPageChangeListener(onPageChangeListener);
        setVerticalPosition();

    }

    private void setVerticalPosition() {//修改滚动的速度
        try {
            Class <?> cs=Class.forName("androidx.viewpager.widget.ViewPager");//获得clss对象
            FiexedSpeedScroll fiexedSpeedScroll=new FiexedSpeedScroll(getContext());
            Field field=cs.getDeclaredField("mScrollor");
            field.setAccessible(true);
            field.set(this,fiexedSpeedScroll);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void startTimingTask(){
        if(handler==null&&!mIsRunning){
            taskRunnable=new TaskRunnable(bannerViewPager);
            handler.postDelayed(taskRunnable,60000);
            mIsRunning=true;

        }
    }

    private void stopTimingTask(){
        if(handler==null&&!mIsRunning){
            handler.removeCallbacks(taskRunnable);
            try {
                Thread.sleep(500);  //睡眠0.5秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            handler=null;
            mIsRunning=false;
        }
    }
    private void setCurrentItem(){
        setCurrentItem(getCurrentItem()+1,true);
        handler.postDelayed(taskRunnable,60000);
    }
    private void start(){
        startTimingTask();
    }
    private void stop(){
        stopTimingTask();
    }
    private class FiexedSpeedScroll extends Scroller{
        private int duration=700;//设置滚动持续时间
        public FiexedSpeedScroll(Context context) {
            super(context);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy, int duration) {
            super.startScroll(startX, startY, dx, dy, duration);//启动滚动

        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy) {
            super.startScroll(startX, startY, dx, dy);
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        //防止ViewPager可见时第一次切换无动画效果
        //滚动监听
        setFirstLayout(false);
        start();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stop();
    }

    private void setFirstLayout(boolean b) {
        try {
            Class<?> cs=Class.forName("androidx.viewpager.widget.ViewPager");
            Field field=cs.getDeclaredField("mFirstLayout");
            field.setAccessible(true);
            field.setBoolean(this,b);

        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    private static class TaskRunnable implements Runnable {

        private WeakReference<BannerViewPager> weakReference;

        TaskRunnable(BannerViewPager bannerViewPager) {
            this.weakReference = new WeakReference<>(bannerViewPager);
        }

        @Override
        public void run() {
            //执行切换任务
            BannerViewPager instance = weakReference.get();
            if (instance == null) return;
            instance.setCurrentItem();
        }
    }
}
