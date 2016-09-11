package org.dync.testrecyclerview.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;

import org.dync.testrecyclerview.R;
import org.dync.testrecyclerview.adapter.RecyclerChatAdapter;
import org.dync.testrecyclerview.entry.Type;
import org.dync.testrecyclerview.util.SoftKeyboardUtil;
import org.dync.testrecyclerview.entry.ChatModel;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {
    private RecyclerChatAdapter mAdapter;
    private List<ChatModel> mDatas;
    private RecyclerView mDataRv;
    private boolean keybardOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        mDataRv = (RecyclerView) findViewById(R.id.rv_recyclerview_data);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mDataRv.setLayoutManager(layoutManager);

        mAdapter = new RecyclerChatAdapter(this);

        mDatas = loadChatModelDatas();
        mAdapter.setDatas(mDatas);
        mDataRv.setAdapter(mAdapter);
        mDataRv.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                if (keybardOpen) {
                    if (e.getAction() == MotionEvent.ACTION_DOWN) {
                        SoftKeyboardUtil.hideKeyboard(RecyclerViewActivity.this);
                    }
                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent motionEvent) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

        SoftKeyboardUtil.observeSoftKeyboard(this, new SoftKeyboardUtil.OnSoftKeyboardChangeListener() {
            @Override
            public void onSoftKeyBoardChange(int softKeybardHeight, boolean isShow) {
                keybardOpen = isShow;
                if (isShow) {
                    mDataRv.scrollToPosition(mDatas.size() - 1);
                } else {

                }
            }
        });
    }

    public List<ChatModel> loadChatModelDatas() {
        List<ChatModel> datas = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            if (i % 3 == 0) {
                datas.add(new ChatModel("消息" + i, ChatModel.UserType.To, Type.chat));
            } else if (i % 2 == 0) {
                datas.add(new ChatModel("消息" + i, ChatModel.UserType.From, Type.chat));
            } else {
                datas.add(new ChatModel("消息" + i, ChatModel.UserType.From, Type.other));
            }
        }
        return datas;
    }
}
