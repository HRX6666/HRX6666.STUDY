package com.example.playandroid.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.playandroid.Data.TreeData;
import com.example.playandroid.R;

import java.util.List;

public class KnowledgeTreeListAdapter2 extends ArrayAdapter<TreeData> {
    private int srid;

    public KnowledgeTreeListAdapter2(Context context, int sid, List<TreeData> list){
        super(context,sid,list);
        srid = sid;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TreeData treeData=getItem(position);
        View v = LayoutInflater.from(getContext()).inflate(srid,parent,false);
        TextView textView= v.findViewById(R.id.system_name2);
        textView.setText(treeData.getName());
        return  v;
    }
}
