package org.dync.testrecyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;

import org.dync.testrecyclerview.activity.RecyclerViewActivity;
import org.dync.testrecyclerview.activity.UpdataItemActivity;
import org.dync.testrecyclerview.adapter.RecyclerChatAdapter;
import org.dync.testrecyclerview.entry.ChatModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerChatAdapter mAdapter;
    private List<ChatModel> mDatas;
    private RecyclerView mDataRv;
    private boolean keybardOpen = false;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click(View view) {
        switch (view.getId()) {
            case R.id.button1:
                startActivity(new Intent(MainActivity.this, RecyclerViewActivity.class));
                break;
            case R.id.button2:
                startActivity(new Intent(MainActivity.this, UpdataItemActivity.class));
                break;
        }
    }
}