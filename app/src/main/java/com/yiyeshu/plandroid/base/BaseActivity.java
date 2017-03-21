package com.yiyeshu.plandroid.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.yiyeshu.plandroid.util.ToastUtils;

import butterknife.ButterKnife;


/**
 * Created by lhw on 2016/12/26.
 * 基类 activity
 */

public abstract class BaseActivity extends AppCompatActivity {

    private final String TAG = getClass().getSimpleName();

    private int mFragmentId;
    protected Fragment mCurrFragment;
    private boolean isExit = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutID());
        ButterKnife.bind(this);
        initView();
        initData();
        initListener();
    }

    /*
返回布局文件id
*/
    protected abstract int getLayoutID();

    /*
       初始化视图控件
        */
    protected abstract void initView();

    /*
   初始化数据
    */
    protected abstract void initData();

    /*
        初始化监听
         */
    protected abstract void initListener();


    public void setFragmentId(int fragmentId) {
        mFragmentId = fragmentId;
    }

    public Fragment getCurrFragment() {
        return mCurrFragment;
    }

    public void setCurrFragment(Fragment currFragment) {
        this.mCurrFragment = currFragment;
    }

    protected void toFragment(Fragment toFragment) {
        if (mCurrFragment == null) {
            ToastUtils.showToast(this, "mCurrFragment is null");
            return;
        }

        if (toFragment == null) {
            ToastUtils.showToast(this, "toFragment is null");
            return;
        }

        if (toFragment.isAdded()) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .hide(mCurrFragment)
                    .show(toFragment)
                    .commit();
        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .hide(mCurrFragment)
                    .add(mFragmentId, toFragment)
                    .show(toFragment)
                    .commit();
        }
    }

    //不带参数跳转页面
    protected void openActivity(Class<? extends BaseActivity> toActivity) {
        openActivity(toActivity, null);
    }

    //带参数跳转页面
    protected void openActivity(Class<? extends BaseActivity> toActivity, Bundle parameter) {
        Intent intent = new Intent(this, toActivity);
        if (parameter != null) {
            intent.putExtras(parameter);
        }
        startActivity(intent);
    }

    public void setExit(boolean isExit) {
        this.isExit = isExit;
    }

    protected void setToolbar(Toolbar toolbar, String title) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
