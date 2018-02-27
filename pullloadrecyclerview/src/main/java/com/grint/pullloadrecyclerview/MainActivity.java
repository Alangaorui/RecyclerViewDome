package com.grint.pullloadrecyclerview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button mPullLoadRecyclerViewBtn;

    private Button mLFRecyclerViewBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();

    }

    private void initview() {
        mPullLoadRecyclerViewBtn = (Button) findViewById(R.id.pullLoadMore_btn_id);
        mPullLoadRecyclerViewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,PullLoadRecyclerViewActivity.class);
                startActivity(intent);
            }
        });

        mLFRecyclerViewBtn = (Button) findViewById(R.id.lfrecyclerview_btn_id);
        mLFRecyclerViewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,LFRecyclerViewActivity.class);
                startActivity(intent);
            }
        });

    }

}
