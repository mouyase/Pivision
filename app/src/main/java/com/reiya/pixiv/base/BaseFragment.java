package com.reiya.pixiv.base;


import android.support.v4.app.Fragment;

/**
 * Created by lenovo on 2016/5/31.
 */

public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements BaseView {
    protected static final String PAGE = "page";
    protected T mPresenter;

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
