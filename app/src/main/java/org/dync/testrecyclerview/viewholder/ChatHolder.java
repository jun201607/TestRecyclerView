package org.dync.testrecyclerview.viewholder;

import android.content.Context;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.dync.testrecyclerview.R;
import org.dync.testrecyclerview.entry.ChatModel;

import java.util.List;

/**
 * Created by KathLine on 2016/9/11.
 */
public class ChatHolder extends BaseViewHolder<ChatModel> {
    protected RelativeLayout rl_item_chat_to;
    protected RelativeLayout rl_item_chat_from;
    protected TextView tv_item_chat_from_msg;
    protected TextView tv_item_chat_to_msg;

    public ChatHolder(View view) {
        super(view);
        rl_item_chat_to = findViewById(R.id.rl_item_chat_to);
        rl_item_chat_from = findViewById(R.id.rl_item_chat_from);
        tv_item_chat_from_msg = findViewById(R.id.tv_item_chat_from_msg);
        tv_item_chat_to_msg = findViewById(R.id.tv_item_chat_to_msg);
    }

    @Override
    public void fillData(Context context, List<ChatModel> datas, int position) {
        ChatModel model = datas.get(position);
        if (model.mUserType == ChatModel.UserType.From) {
            rl_item_chat_to.setVisibility(View.GONE);
            rl_item_chat_from.setVisibility(View.VISIBLE);
            String msg = String.format(mContent.getString(R.string.color_msg_from), model.mMsg);
            Spanned htmlMsg = Html.fromHtml(msg);
            tv_item_chat_from_msg.setText(htmlMsg,TextView.BufferType.SPANNABLE);
        } else {
            rl_item_chat_to.setVisibility(View.VISIBLE);
            rl_item_chat_from.setVisibility(View.GONE);
            String msg = String.format(mContent.getString(R.string.color_msg_from), model.mMsg);
            Spanned htmlMsg = Html.fromHtml(msg);
            tv_item_chat_to_msg.setText(htmlMsg,TextView.BufferType.SPANNABLE);
        }
    }
}
