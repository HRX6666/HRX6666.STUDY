package com.example.playandroid.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.playandroid.Data.TreeData;
import com.example.playandroid.R;
import com.example.playandroid.UI.Activity.ProjectActivity;

import java.util.List;

public class ProjectTreeAdapter2 extends RecyclerView.Adapter<ProjectTreeAdapter2.ViewHolder> {
    private List<TreeData> mdata;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView bt_Name;


        public ViewHolder(View view) {
            super(view);
            bt_Name = view.findViewById(R.id.project_tree_name);
        }
    }

    public ProjectTreeAdapter2(List<TreeData> mdata) {
        this.mdata = mdata;
    }

    @NonNull
    @Override
    public ProjectTreeAdapter2.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_projecttree_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.bt_Name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                TreeData projectTreeData=mdata.get(position);
                String name = projectTreeData.getName();
                int id = projectTreeData.getId();
                Intent intent=new Intent(view.getContext(), ProjectActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("name",name);
                view.getContext().startActivity(intent);
            }
        });


        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectTreeAdapter2.ViewHolder holder, int position) {
        TreeData projectTreeData = mdata.get(position);
        holder.bt_Name.setText(projectTreeData.getName());

    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }


}
