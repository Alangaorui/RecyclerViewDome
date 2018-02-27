package com.grint.recyclerviewdome;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewMainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerViewActivity;
    private List<String> mListDta;
    private RecyclerViewAdatper mRecyclerViewAdatper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_main);
        initData();
        initview();//视图
    }

    private void initData() {
        mListDta = new ArrayList<>();
        for (int i = 1; i < 20; i++) {
            mListDta.add(i + "");
        }
    }

    private void initview() {
        mRecyclerViewActivity = (RecyclerView) this.findViewById(R.id.recyclerciew_activity_id);
        //加载视图数据
//        setDataViiew();
        //设置GridView
//        setGridView();
        //设置listview
        setListView();
    }

    private void setListView() {
        mRecyclerViewActivity.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewActivity.addItemDecoration(new DividerItemDecoration(RecyclerViewMainActivity.this, AddSpacerItemRecyclerView.VERTICAL_LIST));
        mRecyclerViewActivity.setItemAnimator(new DefaultItemAnimator());
        mRecyclerViewAdatper=new RecyclerViewAdatper(this, mListDta);
        setListener();
        mRecyclerViewActivity.setAdapter(mRecyclerViewAdatper);
    }

    private void setGridView() {
        mRecyclerViewActivity.setLayoutManager(new StaggeredGridLayoutManager(5,StaggeredGridLayoutManager.VERTICAL));
        mRecyclerViewActivity.setItemAnimator(new DefaultItemAnimator());
//        mRecyclerViewActivity.addItemDecoration(new AddSpacerItemRecyclerView(this));
        mRecyclerViewAdatper=new RecyclerViewAdatper(this, mListDta);
        setListener();
        mRecyclerViewActivity.setAdapter(mRecyclerViewAdatper);
    }


    private void setDataViiew() {
//       mRecyclerViewActivity.setLayoutManager(new StaggeredGridLayoutManager(RecyclerViewMainActivity.this,AddSpacerItemRecyclerView.VERTICAL_LIST));
        mRecyclerViewActivity.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewActivity.setItemAnimator(new DefaultItemAnimator());//添加动画
        mRecyclerViewAdatper = new RecyclerViewAdatper(this,mListDta);
        setListener();
        mRecyclerViewActivity.setAdapter(mRecyclerViewAdatper);
    }

    private void setListener() {
        mRecyclerViewAdatper.setOnItemClickListener(new RecyclerViewAdatper.OnItemClickListener() {
            @Override
            public void OnItmeClick(View view, int position) {
                Toast.makeText(RecyclerViewMainActivity.this,"点击了"+(position+1)+"条",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void OnLongClick(View view, final int position) {//长按事件
                new AlertDialog.Builder(RecyclerViewMainActivity.this)
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
}
