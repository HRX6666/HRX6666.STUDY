package com.example.playandroid.UI.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.playandroid.Adapter.KnowledgeTreeListAdapter;
import com.example.playandroid.R;
import com.example.playandroid.UI.Activity.SearchActivity;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;


public class KnowledgeHierarchyFragment extends Fragment {
    private View view;
    private ViewPager viewPager;
    TextView textView;
    List<Fragment> fragmentList=new ArrayList<>();
   // List<String> fragmentTitle=new ArrayList<>();
    TabLayout tabLayout;
    Button sreach_tb;
    KnowledgeTreeListFragment knowledgeTreeListFragment=new KnowledgeTreeListFragment();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.fragment_knowledge_hierarchy,container,false);
        initView();
        initOnClike();
        fragmentList.clear();
        //fragmentTitle.clear();
        //fragmentTitle.add("知识体系");
        fragmentList.add(knowledgeTreeListFragment);
        viewPager.setAdapter(new ViewPagerAdapter(getChildFragmentManager(),ViewPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT));
        //tabLayout.setupWithViewPager(viewPager);
        viewPager.setOffscreenPageLimit(2);
//        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
        return view;
    }

    private void initOnClike() {
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });
    }


    private void initView() {
     
        viewPager=view.findViewById(R.id.knowledge_hierarchy_content);
        //tabLayout=view.findViewById(R.id.tabs_2);
        textView=view.findViewById(R.id.know_search);
        sreach_tb=view.findViewById(R.id.know_search_bt);

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
        //@Nullable
       // @Override
        //public CharSequence getPageTitle(int position) {
           // return fragmentTitle.get(position);
        //}
    }
}