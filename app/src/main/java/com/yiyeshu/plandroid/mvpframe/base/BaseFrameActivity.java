package com.yiyeshu.plandroid.mvpframe.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.yiyeshu.plandroid.base.BaseActivity;
import com.yiyeshu.plandroid.mvpframe.util.TUtil;


/**
 * Created by Administrator on 2016/12/28.
 */

public abstract class BaseFrameActivity<P extends BasePresenter, M extends BaseModel> extends BaseActivity implements BaseView{
    public P mPresenter;

    public M mModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = TUtil.getT(this, 0);
        Log.e("mPresenter", "====: "+mPresenter);
        mModel = TUtil.getT(this, 1);
        if (this instanceof BaseView) {
            mPresenter.attachVM(this, mModel);
        }
    }

    /**
     * view生命周期结束，解除绑定
     */
    @Override
    protected void onDestroy() {
        if (mPresenter != null) mPresenter.detachVM();
        super.onDestroy();
    }

    @Override
    public void onRequestStart() {

    }

    @Override
    public void onRequestError(String msg) {

    }

    @Override
    public void onRequestEnd() {

    }

    @Override
    public void onInternetError() {

    }
}
