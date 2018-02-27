package com.grint.pullloadrecyclerview;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.grint.pullloadrecyclerview.view.PullLoadRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PullLoadRecyclerViewActivity extends AppCompatActivity implements PullLoadRecyclerView.PullLoadListener {

    private PullLoadRecyclerView mPullLoadRecyclerView;
    private int mCount = 1;
    private RecyclerView mRecyclerView;
    private RecyclerViewAdatper mRecyclerViewAdatper;
    private Object data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_load_recycler_view);
        initview();

    }

    private void initview() {
        getData();
        mPullLoadRecyclerView = findViewById(R.id.pullLoadRecyclerView);
        mRecyclerView = mPullLoadRecyclerView.getRecyclerView();
        mRecyclerView.setVerticalScrollBarEnabled(true);//无滚动条
        mPullLoadRecyclerView.setRefreshing(true);
        mPullLoadRecyclerView.setFooterViewText("正在加载...");
        mPullLoadRecyclerView.setLinearLayout();
        mPullLoadRecyclerView.setOnPullLoadListener(this);
        mRecyclerViewAdatper = new RecyclerViewAdatper(this);
        setListener();
        mPullLoadRecyclerView.setAdapter(mRecyclerViewAdatper);



    }

    private void setListener() {
        mRecyclerViewAdatper.setOnItemClickListener(new RecyclerViewAdatper.OnItemClickListener() {
            @Override
            public void OnItmeClick(View view, int position) {
                Toast.makeText(PullLoadRecyclerViewActivity.this,"点击了"+(position+1)+"条",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void OnLongClick(View view, final int position) {
                new AlertDialog.Builder(PullLoadRecyclerViewActivity.this)
                        .setTitle("确认删除吗？")
                        .setNegativeButton("取消", null)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                mRecyclerViewAdatper.removeData(position);
                            }
                        }).show();
            }
        });
    }


    //下拉刷新
    @Override
    public void onRefresh() {
        setRefresh();
        getData();
    }

    //上拉加载
    @Override
    public void onLoad() {
        mCount = mCount + 1;
        getData();
    }

    private void getData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mRecyclerViewAdatper.addAllData(setList());
                mPullLoadRecyclerView.setPullLoadCompleted();
            }

        },1000);
    }

    private List<String> setList() {
        List<String> dataList = new ArrayList<>();
        int start = 20 * (mCount - 1);
        for (int i = start; i < 20 * mCount; i++) {
            dataList.add("第" + i +"个");
        }
        return dataList;

    }

    private void setRefresh() {
        mRecyclerViewAdatper.clearData();
        mCount = 1;
    }
}
