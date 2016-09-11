package org.dync.testrecyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.dync.testrecyclerview.R;
import org.dync.testrecyclerview.entry.ChatModel;
import org.dync.testrecyclerview.entry.Type;
import org.dync.testrecyclerview.viewholder.ADUholder;
import org.dync.testrecyclerview.viewholder.BaseViewHolder;
import org.dync.testrecyclerview.viewholder.ChatHolder;

/**
 * Created by KathLine on 2016/8/29.
 */
public class RecyclerChatAdapter extends BaseAdapter<ChatModel> {
    public Context context;
    protected ADUholder holder;

    public RecyclerChatAdapter(Context context) {
        super();
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        return mDatas.get(position).type;
    }

    @Override
    protected BaseViewHolder addViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        switch (viewType) {
            case Type.chat:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat, parent, false);
                return new ChatHolder(view);
            case Type.other:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
                return new ADUholder(view);
        }
        return null;
    }

    @Override
    protected void fillData(RecyclerView.ViewHolder holder, int position, ChatModel model) {
        if (holder instanceof ChatHolder) {
            ChatHolder chatHolder = (ChatHolder) holder;
            chatHolder.fillData(context, mDatas, position);
        } else if (holder instanceof ADUholder) {
            ADUholder adUholder = (ADUholder) holder;
            adUholder.fillData(context, mDatas, position);
        }
    }
}