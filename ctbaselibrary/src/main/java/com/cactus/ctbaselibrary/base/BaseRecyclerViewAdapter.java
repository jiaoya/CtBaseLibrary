package com.cactus.ctbaselibrary.base;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * <br>Authour:       焦亚
 * <br>Created Time:  2017/3/16 10:02
 * <br>Description:
 * <br>Alter By:
 */
public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<BaseRecycleViewHolder>
        implements View.OnClickListener {

    protected static final int TYPE_HEADER = 0, TYPE_ITEM = 1, TYPE_FOOT = 2;
    protected ArrayList<T> mList = new ArrayList<T>();
    protected Activity mContext = null;
    protected LayoutInflater mInflater;
    protected int headViewLayoutId = 0;
    protected int normalLayoutId = 0;
    protected LoadMoreDescribe mLoadMoreDescribe;

    protected OnRecyclerViewItemClickListener itemClickListener;
    protected View headView = null;
    protected View footView = null;
    protected int headViewSize = 0;
    protected int footViewSize = 0;
    protected boolean isAddFoot = false;
    protected boolean isAddHead = false;
    public ChangeGridLayoutManager changeGridLayoutManager = null;


    public BaseRecyclerViewAdapter(Activity context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mLoadMoreDescribe = new LoadMoreDescribe(0);
    }

    public BaseRecyclerViewAdapter(Activity context, int layoutId) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        this.normalLayoutId = layoutId;
        mLoadMoreDescribe = new LoadMoreDescribe(0);
    }

    public BaseRecyclerViewAdapter(Activity context, int headViewLayoutId, int normalLayoutId) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        this.headViewLayoutId = normalLayoutId;
        this.normalLayoutId = normalLayoutId;
        mLoadMoreDescribe = new LoadMoreDescribe(0);
    }

    @Override
    public BaseRecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (normalLayoutId == 0) {
            return null;
        }
        switch (viewType) {
            case TYPE_HEADER:
                if (headViewLayoutId != 0) {
                    headView = mInflater.inflate(headViewLayoutId, parent, false);
                    return new BaseRecycleViewHolder(mContext, headView, parent);
                } else {
                    View head = headView;
                    return new BaseRecycleViewHolder(head);
                }
            case TYPE_ITEM:
                View view = mInflater.inflate(normalLayoutId, parent, false);
                BaseRecycleViewHolder viewHolder = new BaseRecycleViewHolder(mContext, view, parent);
                view.setTag(viewHolder);
                view.setOnClickListener(this);
                return viewHolder;
            case TYPE_FOOT:
                View foot = footView;
                return new BaseRecycleViewHolder(mContext, foot, parent);
        }
        return null;
    }

    /**
     * 加载描述
     *
     * @param loadMoreDescribe
     */
    public void setLoadMore(LoadMoreDescribe loadMoreDescribe) {
        mLoadMoreDescribe = loadMoreDescribe;
        this.notifyDataSetChanged();
    }

    public void addHeadView(View view) {
        headView = view;
        headViewSize = 1;
        isAddHead = true;
        notifyDataSetChanged();
    }

    public void addFootView(View view) {
        footView = view;
        footViewSize = 1;
        isAddFoot = true;
        notifyDataSetChanged();
    }

    public void removeFootView() {
        footViewSize = 0;
        notifyItemRemoved(getItemCount() - 1);
    }

    @Override
    public int getItemViewType(int position) {
        int type = TYPE_ITEM;
        if (headViewSize == 1 && position == 0) {
            type = TYPE_HEADER;
        } else if (footViewSize == 1 && position == getItemCount() - 1) {
            //最后一个位置
            type = TYPE_FOOT;
        }
        return type;
    }

    @Override
    public int getItemCount() {
        if (mList != null) {
            return mList.size() + headViewSize + footViewSize;
        }
        return 0;
    }

    public void setItemList(ArrayList<T> list) {
        this.mList = list;
        notifyDataSetChanged();
    }

    /**
     * 得到ArrayList
     *
     * @return ArrayList
     */
    public ArrayList<T> getList() {
        return mList;
    }

    /**
     * 得到固定位置的数据
     *
     * @param position
     * @return
     */
    public Object getItem(int position) {
        return mList == null ? null : mList.get(position);
    }


    /**
     * 添加数据，并更新
     *
     * @param lists arraylist对象
     */
    public void appendToList(ArrayList<T> lists) {

        if (lists == null) {
            return;
        }
        mList.addAll(lists);
        notifyDataSetChanged();
    }

    /**
     * 加载更多数据使用，添加单个
     *
     * @param t
     */
    public void appendToList(T t) {
        if (t == null) {
            return;
        }
        mList.add(t);
        //notifyDataSetChanged();
        notifyItemInserted(mList.size() - 1);
    }

    /**
     * 清理数据
     */
    public void clearList() {
        mList.clear();
        notifyDataSetChanged();
    }

    /**
     * 移除一个条目，并更新
     *
     * @param pos
     */
    public void removeItem(int pos) {
        mList.remove(pos);
        notifyItemRemoved(pos);
    }

    /**
     * 移除一段条目，并更新
     *
     * @parampos
     */
    public void removeItem(int beg, int end) {
        ArrayList<T> temp = new ArrayList<>(mList.subList(beg, end));
        mList.removeAll(temp);
        notifyItemRangeRemoved(beg, end);
    }

    public void removeItemNoRefresh(int beg, int end) {
        ArrayList<T> temp = new ArrayList<>(mList.subList(beg, end));
        mList.removeAll(temp);
    }

    /**
     * 插入一个条目
     *
     * @param objext
     * @param position
     */
    public void addItem(T objext, int position) {
        mList.add(position, objext);
        notifyItemInserted(position);
    }

    public void replace(T objext, int position) {
        mList.set(position, objext);
        notifyItemChanged(position);
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onItemClick(v, (int) v.getTag());
    }

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int position);
    }

    public OnRecyclerViewItemClickListener getItemClickListener() {
        return itemClickListener;
    }

    public void setItemClickListener(OnRecyclerViewItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public interface ChangeGridLayoutManager {
        void change(int size, boolean isAddHead, boolean isAddFoot);
    }

    public void setChangeGridLayoutManager(ChangeGridLayoutManager changeGridLayoutManager) {
        this.changeGridLayoutManager = changeGridLayoutManager;
        changeGridLayoutManager.change(getItemCount() - 1, isAddHead, isAddFoot);
    }


}
