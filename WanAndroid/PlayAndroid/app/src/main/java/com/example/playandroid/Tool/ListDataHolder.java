package com.example.playandroid.Tool;



import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;
//holer类
public class ListDataHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> mHolderView;
    private View mItemView;

    public ListDataHolder(View itemView) {
        super(itemView);
        this.mItemView = itemView;

        if (mHolderView == null)
            mHolderView = new SparseArray<>();
    }


    public static ListDataHolder createViewHolder(ViewGroup parent, int layoutId) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        return new ListDataHolder(view);
    }

    public static ListDataHolder createViewHolder(View view) {
        return new ListDataHolder(view);
    }

    public <T extends View> T getView(int id) {
        View view = mHolderView.get(id);
        if (view == null) {
            view = mItemView.findViewById(id);
            mHolderView.put(id, view);
        }
        return (T) view;
    }

}
