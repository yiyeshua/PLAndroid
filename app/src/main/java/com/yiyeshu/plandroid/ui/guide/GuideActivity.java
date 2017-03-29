package com.yiyeshu.plandroid.ui.guide;

import android.os.Bundle;
import android.view.WindowManager;

import com.yiyeshu.plandroid.R;
import com.yiyeshu.plandroid.base.BaseActivity;

import butterknife.BindView;

/**
 * 引导页
 */

public class GuideActivity extends BaseActivity {

    @BindView(R.id.contentFrameLayout)
    PageFrameLayout contentFrameLayout;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_guide;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }

    @Override
    protected void initData() {
        //contentFrameLayout = (PageFrameLayout) findViewById(R.id.contentFrameLayout);
        // 设置资源文件和选中圆点
        contentFrameLayout.setUpViews(new int[]{
                R.layout.page_tab1,
                R.layout.page_tab2,
                R.layout.page_tab3,
                R.layout.page_tab4
        }, R.mipmap.banner_on, R.mipmap.banner_off);
    }

    @Override
    protected void initListener() {

    }

}
