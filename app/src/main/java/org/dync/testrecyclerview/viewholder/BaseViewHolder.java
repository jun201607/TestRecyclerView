package org.dync.testrecyclerview.viewholder;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;

import java.util.List;

/**
 * Created by KathLine on 2016/9/7.
 */
public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
    protected final SparseArray<View> mViews = new SparseArray();
    protected Context mContent;
    public static boolean isClick = true;//默认是可以点击的
    public static boolean isLongClick = true;//默认是可以长按的
    protected OnItemClickListener onItemClickListener;
    protected OnLongItemClickListener onLongItemClickListener;

    public BaseViewHolder(View itemView) {
        super(itemView);
        if (itemView != null) {
            mContent = itemView.getContext();
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }
    }

    public abstract void fillData(Context context, List<T> datas, int position);

    /**
     * 查找View，这个方法可以让我们省去强转操作
     */
    public <T extends View> T findViewById(@IdRes int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    @Override
    public void onClick(View view) {
        Log.i("TAG", "onClick: " + isClick);
        if (view.getId() == this.itemView.getId() && null != onItemClickListener && isClick) {
            onItemClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    @Override
    public boolean onLongClick(View view) {
        if (view.getId() == this.itemView.getId() && null != onLongItemClickListener && isLongClick) {
            return onLongItemClickListener.onLongItemClick(view, getAdapterPosition());
        }
        return false;
    }

    /**
     * 设置item是否可以点击
     *
     * @param isClickable     是否可以单击
     * @param isLongClickable 是否可以长按
     */
    public void setItemable(boolean isClickable, boolean isLongClickable) {
        setItemClickable(isClickable);
        setItemLongClickable(isLongClickable);
    }

    public void setItemClickable(boolean usable) {
        isClick = usable;
        Log.i("TAG", "setItemClickable: " + usable);
    }

    public void setItemLongClickable(boolean usable) {
        isLongClick = usable;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        onItemClickListener = listener;
    }

    public interface OnLongItemClickListener {
        boolean onLongItemClick(View view, int position);
    }

    public void setOnLongItemClickListener(OnLongItemClickListener listener) {
        onLongItemClickListener = listener;
    }
}
