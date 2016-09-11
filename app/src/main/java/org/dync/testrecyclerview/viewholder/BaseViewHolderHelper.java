package org.dync.testrecyclerview.viewholder;

import android.support.annotation.IdRes;
import android.util.SparseArray;
import android.view.View;
import android.widget.CompoundButton;

/**
 * Created by KathLine on 2016/9/8.
 */
public class BaseViewHolderHelper implements View.OnClickListener, View.OnLongClickListener, CompoundButton.OnCheckedChangeListener {
    protected final SparseArray<View> mViews = new SparseArray();
    protected final BaseViewHolder mBaseViewHolder;
    protected final View itemView;
    protected int mPosition;
    protected OnChildItemClickListener onChildItemClickListener;
    protected OnChildLongItemClickListener onChildLongItemClickListener;
    protected OnChildItemCheckedChangeListener onChildItemCheckedChangeListener;

    public BaseViewHolderHelper(BaseViewHolder viewHolder) {
        mBaseViewHolder = viewHolder;
        itemView = viewHolder.itemView;
    }

    public void onClick(View v) {
        if (this.onChildItemClickListener != null) {
            this.onChildItemClickListener.onChildItemClick(v, this.getPosition());
        }
    }

    public boolean onLongClick(View v) {
        if (this.onChildLongItemClickListener != null) {
            return this.onChildLongItemClickListener.onChildLongItemClick(v, this.getPosition());
        }
        return false;
    }

    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (this.onChildItemCheckedChangeListener != null) {
            this.onChildItemCheckedChangeListener.onChildItemCheckedChanged(buttonView, this.getPosition(), isChecked);
        }

    }

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

    public void setPosition(int position) {
        this.mPosition = position;
    }

    public int getPosition() {
        return this.mBaseViewHolder != null ? this.mBaseViewHolder.getAdapterPosition() : this.mPosition;
    }

    public interface OnChildItemClickListener {
        void onChildItemClick(View view, int position);
    }

    public void setOnChildItemClickListener(OnChildItemClickListener listener) {
        onChildItemClickListener = listener;
    }

    public void setItemChildClickListener(@IdRes int viewId) {
        this.findViewById(viewId).setOnClickListener(this);
    }

    public void setItemChildClickListener(View view) {
        view.setOnClickListener(this);
    }

    public interface OnChildLongItemClickListener {
        boolean onChildLongItemClick(View view, int position);
    }

    public void setOnChildLongItemClickListener(OnChildLongItemClickListener listener) {
        onChildLongItemClickListener = listener;
    }

    public void setItemChildLongClickListener(@IdRes int viewId) {
        this.findViewById(viewId).setOnLongClickListener(this);
    }

    public void setItemChildLongClickListener(View view) {
        view.setOnLongClickListener(this);
    }

    public interface OnChildItemCheckedChangeListener {
        void onChildItemCheckedChanged(CompoundButton view, int position, boolean isCheck);
    }

    public void setOnChildItemCheckedChangeListener(OnChildItemCheckedChangeListener listener) {
        onChildItemCheckedChangeListener = listener;
    }

    public void setItemChildCheckedChangeListener(@IdRes int viewId) {
        if (this.findViewById(viewId) instanceof CompoundButton) {
            ((CompoundButton) this.findViewById(viewId)).setOnCheckedChangeListener(this);
        }
    }

    public void setItemChildCheckedChangeListener(View view) {
        if (view instanceof CompoundButton) {
            ((CompoundButton)view).setOnCheckedChangeListener(this);
        }
    }
}
