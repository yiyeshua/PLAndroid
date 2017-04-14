package com.yiyeshu.plandroid.ui.register;

import android.os.Bundle;

import com.yiyeshu.plandroid.R;
import com.yiyeshu.plandroid.mvpframe.base.BaseFrameActivity;

public class RegisterActivity extends BaseFrameActivity<RegisterPresent,RegisterModel> implements RegisterContract.View {

    @Override
    protected int getLayoutID() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }
}
