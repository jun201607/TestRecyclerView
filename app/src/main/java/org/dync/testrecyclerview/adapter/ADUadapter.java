package org.dync.testrecyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.dync.testrecyclerview.R;
import org.dync.testrecyclerview.entry.ChatModel;
import org.dync.testrecyclerview.viewholder.ADUholder;
import org.dync.testrecyclerview.viewholder.BaseViewHolder;

/**
 * Created by KathLine on 2016/9/3.
 */
public class ADUadapter extends BaseAdapter<ChatModel> {
    public Context context;
    protected ADUholder holder;

    public ADUadapter(Context context) {
        super();
        this.context = context;
    }

    @Override
    protected void setItemChildListener(BaseViewHolder viewHolder) {
        super.setItemChildListener(viewHolder);
        if (viewHolder instanceof ADUholder){
            ADUholder adUholder = (ADUholder) viewHolder;
            baseViewHolderHelper.setItemChildClickListener(adUholder.imageView);
        }
    }

    @Override
    protected BaseViewHolder addViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new ADUholder(view);
    }

    @Override
    protected void fillData(RecyclerView.ViewHolder holder, final int position, final ChatModel item) {
        this.holder = (ADUholder) holder;
        this.holder.title.setText(item.name);
        this.holder.name.setText(item.mMsg);
    }
}
