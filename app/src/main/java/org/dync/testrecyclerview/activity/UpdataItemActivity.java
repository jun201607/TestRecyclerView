package org.dync.testrecyclerview.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.dync.testrecyclerview.R;
import org.dync.testrecyclerview.adapter.ADUadapter;
import org.dync.testrecyclerview.entry.ChatModel;
import org.dync.testrecyclerview.viewholder.BaseViewHolder;
import org.dync.testrecyclerview.viewholder.BaseViewHolderHelper;

import java.util.ArrayList;
import java.util.List;

public class UpdataItemActivity extends AppCompatActivity {
    public RadioGroup rg;
    public RadioButton useable;
    public RadioButton disuserable;
    public Button updata;
    public EditText input;
    public EditText inputPosition;
    public RecyclerView recyclerView;

    public ADUadapter adUadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updata_item);

        rg = (RadioGroup) findViewById(R.id.rg);
        useable = (RadioButton) findViewById(R.id.radioButton);
        disuserable = (RadioButton) findViewById(R.id.radioButton2);
        updata = (Button) findViewById(R.id.updata);
        input = (EditText) findViewById(R.id.edt_input);
        inputPosition = (EditText) findViewById(R.id.edt_input1);
        recyclerView = (RecyclerView) findViewById(R.id.rv_recyclerview_data);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adUadapter = new ADUadapter(this);
        recyclerView.setAdapter(adUadapter);

        List<ChatModel> datas = new ArrayList<>();
        ChatModel item = null;
        for (int i = 0; i < 20; i++) {
            item = new ChatModel();
            item.name = "title" + i;
            item.mMsg = "name" + i;
            datas.add(item);
        }
        adUadapter.setDatas(datas);
        adUadapter.setOnItemClickListener(new BaseViewHolder.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(UpdataItemActivity.this, "position: " + position, Toast.LENGTH_SHORT).show();
            }
        });

        adUadapter.setOnLongItemClickListener(new BaseViewHolder.OnLongItemClickListener() {
            @Override
            public boolean onLongItemClick(View view, int position) {
                String title = adUadapter.getItem(position).name;
                Toast.makeText(UpdataItemActivity.this, title + ",position: " + position, Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        adUadapter.setOnChildItemClickListener(new BaseViewHolderHelper.OnChildItemClickListener() {
            @Override
            public void onChildItemClick(View view, int position) {
                String title = adUadapter.getItem(position).name;
                Toast.makeText(UpdataItemActivity.this, title + "，你点了图片,position: " + position, Toast.LENGTH_SHORT).show();
            }
        });

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.radioButton) {
                    adUadapter.getViewHolder().setItemable(true, true);
                } else if (i == R.id.radioButton2) {
                    adUadapter.getViewHolder().setItemable(false, false);
                }
            }
        });

        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
    }

    public void click(View view) {
        switch (view.getId()) {
            case R.id.updata:
                String updataStr = input.getText().toString();
                String positionStr = inputPosition.getText().toString();
                int position = Integer.parseInt(positionStr);
                if (position < 0 || position > adUadapter.getItemCount() - 1) {
                    Toast.makeText(UpdataItemActivity.this, "更新数据不再范围之内", Toast.LENGTH_SHORT).show();
                }
                adUadapter.getItem(position).name = updataStr;
//                adUadapter.notifyItemChanged(position);//使用该方法更新item会出现闪烁的情况
                adUadapter.notifyDataSetChanged();
                break;
        }
    }

}
