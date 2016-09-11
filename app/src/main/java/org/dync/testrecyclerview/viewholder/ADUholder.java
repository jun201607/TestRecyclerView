package org.dync.testrecyclerview.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.dync.testrecyclerview.R;
import org.dync.testrecyclerview.entry.ChatModel;

import java.util.List;

/**
 * Created by KathLine on 2016/9/3.
 */
public class ADUholder extends BaseViewHolder<ChatModel> {
    public ImageView imageView;
    public TextView title;
    public TextView name;

    public ADUholder(View itemView) {
        super(itemView);
        imageView = findViewById(R.id.imageView);
        title = findViewById(R.id.tv_title);
        name = findViewById(R.id.tv_name);
    }

    @Override
    public void fillData(Context context, List<ChatModel> datas, int position) {
        ChatModel model = datas.get(position);
        title.setText(model.name + position);
        name.setText(model.mMsg);
    }

}
