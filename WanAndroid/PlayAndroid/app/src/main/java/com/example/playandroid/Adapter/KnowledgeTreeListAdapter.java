package com.example.playandroid.Adapter;

import static com.example.playandroid.R.drawable.dig_dian;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.playandroid.Data.TreeData;
import com.example.playandroid.R;
import com.example.playandroid.UI.Activity.SearchActivity;

import java.util.List;

public class KnowledgeTreeListAdapter extends ArrayAdapter<TreeData> {
    private int srid;
    private int selectedPositon;

    public KnowledgeTreeListAdapter(Context context,int sid, List<TreeData> list){
        super(context,sid,list);
        srid = sid;
    }

    @SuppressLint("ResourceAsColor")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TreeData treeData=getItem(position);
        View v = LayoutInflater.from(getContext()).inflate(srid,parent,false);
        TextView textView= v.findViewById(R.id.system_name);
        LinearLayout layout=v.findViewById(R.id.ll_kn);
        textView.setText(treeData.getName());
        if (selectedPositon==position){
          textView.setTextColor(R.color.hui);
        }
        return  v;
    }
    public void selectedItemPosition(int position) {
        this.selectedPositon = position;
    }
}
