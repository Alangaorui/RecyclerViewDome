package com.grint.pullloadrecyclerview.view;

import android.support.v4.widget.SwipeRefreshLayout;

/**
 * Created by grant on 2018/2/27 0027.
 */

public class SwipeRefreshLayoutOnRefresh implements SwipeRefreshLayout.OnRefreshListener {

    private PullLoadRecyclerView mPullLoadRecyclerView;

    public SwipeRefreshLayoutOnRefresh(PullLoadRecyclerView pullLoadRecyclerView) {
          this.mPullLoadRecyclerView = pullLoadRecyclerView;
    }

    @Override
    public void onRefresh() {
        if (!mPullLoadRecyclerView.isRefresh()) {
            mPullLoadRecyclerView.setIsRefresh(true);
            mPullLoadRecyclerView.refresh();
        }
    }
}
