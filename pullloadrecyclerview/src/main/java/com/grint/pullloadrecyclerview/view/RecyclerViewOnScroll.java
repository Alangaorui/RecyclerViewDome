package com.grint.pullloadrecyclerview.view;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

/**
 * Created by grant on 2018/2/27 0027.
 */

public class RecyclerViewOnScroll extends RecyclerView.OnScrollListener {

    private PullLoadRecyclerView mPullLoadRecyclerView;

    public RecyclerViewOnScroll(PullLoadRecyclerView pullLoadRecyclerView) {
        this.mPullLoadRecyclerView = pullLoadRecyclerView;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        int lastItem = 0;
        int firstItem = 0;
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        int totalItemCount = layoutManager.getItemCount();
        if (layoutManager instanceof LinearLayoutManager) {
            LinearLayoutManager linearLayoutManager = ((LinearLayoutManager) layoutManager);
            firstItem = linearLayoutManager.findFirstCompletelyVisibleItemPosition();
            lastItem = linearLayoutManager.findLastCompletelyVisibleItemPosition();
            if (lastItem == -1) lastItem = linearLayoutManager.findLastVisibleItemPosition();
        } else if (layoutManager instanceof GridLayoutManager) {
            GridLayoutManager gridLayoutManager = ((GridLayoutManager) layoutManager);
            firstItem = gridLayoutManager.findFirstCompletelyVisibleItemPosition();
            lastItem = gridLayoutManager.findLastCompletelyVisibleItemPosition();
            if (lastItem == -1) lastItem = gridLayoutManager.findLastVisibleItemPosition();
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            StaggeredGridLayoutManager staggeredGridLayoutManager = ((StaggeredGridLayoutManager) layoutManager);
            int[] lastPositions = new int[((StaggeredGridLayoutManager) layoutManager).getSpanCount()];
            staggeredGridLayoutManager.findLastCompletelyVisibleItemPositions(lastPositions);
            lastItem = findMax(lastPositions);
            firstItem = staggeredGridLayoutManager.findFirstVisibleItemPositions(lastPositions)[0];
        }
        if (firstItem == 0 || firstItem == RecyclerView.NO_POSITION) {
            if (mPullLoadRecyclerView.getPullRefreshEnable())
                mPullLoadRecyclerView.setSwipeRefreshEnable(true);
        } else {
            mPullLoadRecyclerView.setSwipeRefreshEnable(false);
        }
        if (mPullLoadRecyclerView.getPushRefreshEnable()
                && !mPullLoadRecyclerView.isRefresh()
                && mPullLoadRecyclerView.isHasMore()
                && (lastItem == totalItemCount - 1)
                && !mPullLoadRecyclerView.isLoadMore()
                && (dx > 0 || dy > 0)) {
            mPullLoadRecyclerView.setIsLoadMore(true);
            mPullLoadRecyclerView.loadMore();

        }
    }

    private int findMax(int[] lastPositions) {
        int max = lastPositions[0];
        for (int value : lastPositions) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }
}
