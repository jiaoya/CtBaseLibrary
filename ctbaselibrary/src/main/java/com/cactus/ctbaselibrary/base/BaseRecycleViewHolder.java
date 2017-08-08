package com.cactus.ctbaselibrary.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * <br>Authour:       焦亚
 * <br>Created Time:  2017/3/16 10:02
 * <br>Description:
 * <br>Alter By:
 */

public class BaseRecycleViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> mViews;
    private View mConvertView = null;
    private Context mContext = null;


    public BaseRecycleViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mConvertView = itemView;
        mViews = new SparseArray<>();
    }

    public BaseRecycleViewHolder(@NonNull Context context, @NonNull View itemView) {
        super(itemView);
        mContext = context;
        ButterKnife.bind(this, itemView);
        mConvertView = itemView;
    }

    /**
     * 不使用ButterKnife，可以在onBindViewHolder初始化控件
     *
     * @param context
     * @param itemView
     * @param parent
     */
    public BaseRecycleViewHolder(Context context, @NonNull View itemView, ViewGroup parent) {
        super(itemView);
        mContext = context;
        mConvertView = itemView;
    }

    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }
}
