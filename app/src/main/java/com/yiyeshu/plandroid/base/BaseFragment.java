package com.yiyeshu.plandroid.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2016/12/31.
 */

public abstract class BaseFragment extends Fragment{
    protected BaseActivity mActivity;
    private View mContentView;
    private ViewGroup container;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mActivity = (BaseActivity) getActivity();
        initData();
        initView();
        initListener();
        this.container = container;
        return getContentView();
    }

    protected abstract int getLayoutId();

    public View getContentView() {
        return getActivity().getLayoutInflater().inflate(getLayoutId(), container, false);
    }

    public void setContentView(int viewId) {
        this.mContentView = getActivity().getLayoutInflater().inflate(viewId, container, false);
    }

    public void initData() {

    }

    public void initView() {

    }

    public void initListener() {

    }



    protected void openActivity(Class<? extends BaseActivity> toActivity) {
        openActivity(toActivity, null);
    }

    protected void openActivity(Class<? extends BaseActivity> toActivity, Bundle parameters) {
        Intent intent = new Intent(getActivity(), toActivity);
        if (parameters != null) {
            intent.putExtras(parameters);
        }
        startActivity(intent);
    }

}
