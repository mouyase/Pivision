package com.reiya.pixiv.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;

/**
 * Created by lenovo on 2016/10/4.
 */

public class LoaderRecyclerView extends RecyclerView {
    private OnLoadMoreListener mOnLoadMoreListener;
    private boolean mIsLoading = false;
    public LoaderRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        addOnScrollListener(new OnScrollListener() {
            int lastVisibleItem;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && getAdapter() != null && lastVisibleItem >= getAdapter().getItemCount() && !mIsLoading) {
                    mIsLoading = true;
                    mOnLoadMoreListener.load();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
               super.onScrolled(recyclerView, dx, dy);
                LayoutManager layoutManager = getLayoutManager();
                if (layoutManager instanceof LinearLayoutManager) {
                    lastVisibleItem = ((LinearLayoutManager) getLayoutManager()).findLastVisibleItemPosition() + 1;
                } else {
                    lastVisibleItem = ((StaggeredGridLayoutManager) getLayoutManager()).findLastVisibleItemPositions(null)[0] + ((StaggeredGridLayoutManager) getLayoutManager()).getSpanCount();
                }
            }
        });
    }

    public interface OnLoadMoreListener {
        void load();
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        mOnLoadMoreListener = onLoadMoreListener;
    }

    public void finishLoading() {
        mIsLoading = false;
    }
}
