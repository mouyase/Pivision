package com.reiya.pixiv.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.reiya.pixiv.R;
import com.reiya.pixiv.adapter.BaseAdapter;
import com.reiya.pixiv.adapter.BookmarkTagFilterAdapter;
import com.reiya.pixiv.bean.BookmarkTag;
import com.reiya.pixiv.network.HttpService;
import com.reiya.pixiv.network.NetworkRequest;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

/**
 * Created by zhengyirui on 2017/9/8.
 */

public class BookmarkTagFilterDialog extends DialogFragment {
    private String mType;
    private OnBookmarkTagSelectedCallback mOnBookmarkTagSelectedCallback;
    private BookmarkTagFilterAdapter mAdapter;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_bookmark_tag_filter, null);

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setView(view);

        final Dialog dialog = builder.create();

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new BookmarkTagFilterAdapter(getActivity(), new ArrayList<BookmarkTag>());
        mAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onClick(Object item, List list, int position) {
                if (mOnBookmarkTagSelectedCallback != null) {
                    mOnBookmarkTagSelectedCallback.onTagSelected(((BookmarkTag) item).getName());
                }
                dialog.dismiss();
            }
        });
        recyclerView.setAdapter(mAdapter);


        NetworkRequest.getBookmarkTags(mType)
                .subscribe(new Subscriber<HttpService.BookmarkTagsResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(HttpService.BookmarkTagsResponse bookmarkTagsResponse) {
                        mAdapter.setItems(bookmarkTagsResponse.getBookmarkTags());
                        String nextUrl = bookmarkTagsResponse.getNextUrl();
                        getNext(nextUrl);
                    }
                });

        return dialog;
    }

    private void getNext(String nextUrl) {
        if (nextUrl != null) {
            NetworkRequest.getBookmarkTagsNext(nextUrl)
                    .subscribe(new Subscriber<HttpService.BookmarkTagsResponse>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(HttpService.BookmarkTagsResponse bookmarkTagsResponse) {
                            mAdapter.addItems(bookmarkTagsResponse.getBookmarkTags());
                            String nextUrl = bookmarkTagsResponse.getNextUrl();
                            getNext(nextUrl);
                        }
                    });
        }
    }

    public void setType(String type) {
        mType = type;
    }

    public void setOnBookmarkTagSelectedCallback(OnBookmarkTagSelectedCallback onBookmarkTagSelectedCallback) {
        mOnBookmarkTagSelectedCallback = onBookmarkTagSelectedCallback;
    }

    public interface OnBookmarkTagSelectedCallback {
        void onTagSelected(String tag);
    }
}
