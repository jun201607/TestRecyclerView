package org.dync.testrecyclerview.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import org.dync.testrecyclerview.viewholder.BaseViewHolder;
import org.dync.testrecyclerview.viewholder.BaseViewHolderHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KathLine on 2016/8/29.
 */
public abstract class BaseAdapter<M> extends RecyclerView.Adapter<BaseViewHolder> {
    protected List<M> mDatas;
    protected BaseViewHolder mHolder;
    protected BaseViewHolderHelper baseViewHolderHelper;
    protected BaseViewHolder.OnItemClickListener onItemClickListener;
    protected BaseViewHolder.OnLongItemClickListener onLongItemClickListener;
    protected BaseViewHolderHelper.OnChildItemClickListener onChildItemClickListener;
    protected BaseViewHolderHelper.OnChildLongItemClickListener onChildLongItemClickListener;
    protected BaseViewHolderHelper.OnChildItemCheckedChangeListener onChildItemCheckedChangeListener;

    public void setOnItemClickListener(BaseViewHolder.OnItemClickListener listener) {
        onItemClickListener = listener;
    }

    public void setOnLongItemClickListener(BaseViewHolder.OnLongItemClickListener listener) {
        onLongItemClickListener = listener;
    }

    public void setOnChildItemClickListener(BaseViewHolderHelper.OnChildItemClickListener listener) {
        onChildItemClickListener = listener;
    }

    public void setOnChildLongItemClickListener(BaseViewHolderHelper.OnChildLongItemClickListener listener) {
        onChildLongItemClickListener = listener;
    }

    public void setOnChildItemCheckedChangeListener(BaseViewHolderHelper.OnChildItemCheckedChangeListener listener) {
        onChildItemCheckedChangeListener = listener;
    }

    public BaseAdapter() {
        this.mDatas = new ArrayList<>();
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder holder = this.addViewHolder(parent, viewType);
        setViewHolder(holder);
        holder.setOnItemClickListener(onItemClickListener);
        holder.setOnLongItemClickListener(onLongItemClickListener);
        baseViewHolderHelper = new BaseViewHolderHelper(holder);
        setItemChildListener(holder);
        return holder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        this.fillData(holder, position, this.getItem(position));
    }

    protected abstract BaseViewHolder addViewHolder(ViewGroup parent, int viewType);

    protected abstract void fillData(RecyclerView.ViewHolder holder, int position, M item);

    public void setViewHolder(BaseViewHolder holder) {
        mHolder = holder;
    }

    public BaseViewHolder getViewHolder() {
        return mHolder;
    }

    /**
     * item项子控件的监听事件
     *
     * @param viewHolder
     */
    protected void setItemChildListener(BaseViewHolder viewHolder) {
        baseViewHolderHelper.setOnChildItemClickListener(onChildItemClickListener);
        baseViewHolderHelper.setOnChildLongItemClickListener(onChildLongItemClickListener);
        baseViewHolderHelper.setOnChildItemCheckedChangeListener(onChildItemCheckedChangeListener);
    }

    public BaseViewHolderHelper getBaseViewHolderHelper() {
        return baseViewHolderHelper;
    }

    @Override
    public int getItemCount() {
        return this.mDatas.size();
    }

    public M getItem(int position) {
        return this.mDatas.get(position);
    }

    public List<M> getDatas() {
        return this.mDatas;
    }

    public void addNewDatas(List<M> datas) {
        if (datas != null) {
            this.mDatas.addAll(0, datas);
            this.notifyItemRangeInserted(0, datas.size());
        }

    }

    public void addMoreDatas(List<M> datas) {
        if (datas != null) {
            this.mDatas.addAll(this.mDatas.size(), datas);
            this.notifyItemRangeInserted(this.mDatas.size(), datas.size());
        }

    }

    public void setDatas(List<M> datas) {
        if (datas != null) {
            this.mDatas = datas;
        } else {
            this.mDatas.clear();
        }

        this.notifyDataSetChanged();
    }

    public void clear() {
        this.mDatas.clear();
        this.notifyDataSetChanged();
    }

    public void removeItem(int position) {
        this.mDatas.remove(position);
        this.notifyItemRemoved(position);
    }

    public void removeItem(M model) {
        this.removeItem(this.mDatas.indexOf(model));
    }

    public void addItem(int position, M model) {
        this.mDatas.add(position, model);
        this.notifyItemInserted(position);
    }

    public void addFirstItem(M model) {
        this.addItem(0, model);
    }

    public void addLastItem(M model) {
        this.addItem(this.mDatas.size(), model);
    }

    public void setItem(int location, M newModel) {
        this.mDatas.set(location, newModel);
        this.notifyItemChanged(location);
    }

    public void setItem(M oldModel, M newModel) {
        this.setItem(this.mDatas.indexOf(oldModel), newModel);
    }

    public void moveItem(int fromPosition, int toPosition) {
        this.mDatas.add(toPosition, this.mDatas.remove(fromPosition));
        this.notifyItemMoved(fromPosition, toPosition);
    }
}
