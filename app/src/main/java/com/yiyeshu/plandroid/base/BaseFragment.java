package com.yiyeshu.plandroid.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/12/31.
 */

public abstract class BaseFragment extends Fragment{
    protected  final String TAG = this.getClass().getSimpleName();
    protected BaseActivity mActivity;
    protected View mContentView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        init();
        this.mContentView=getContentView(container);
        ButterKnife.bind(mContentView);
        initData();
        initView(savedInstanceState);
        initListener();
        return mContentView;
    }

    private void init() {
        mActivity = (BaseActivity) getActivity();
    }

    protected abstract int getLayoutId();

    public View getContentView(ViewGroup container) {
        return mActivity.getLayoutInflater().inflate(getLayoutId(), container, false);
    }


    protected abstract void initData();

    protected abstract void initView(Bundle savedInstanceState);

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
