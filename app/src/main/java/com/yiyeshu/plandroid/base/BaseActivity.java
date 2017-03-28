package com.yiyeshu.plandroid.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.yiyeshu.common.utils.AppManager;

import butterknife.ButterKnife;


/**
 * Created by lhw on 2016/12/26.
 * 基类 activity
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected Context mContext;

    protected final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActivityState(this);
        setContentView(getLayoutID());
        ButterKnife.bind(this);
        initView(savedInstanceState);
        init();
        initListener();
        initData();
        AppManager.getAppManager().addActivity(this);
    }

    /**
     * 执行一些初始化操作，如工具类初始化
     */
    protected  void init(){
        this.mContext=this;
    };

    /**
     * 设置 APP 只能竖屏显示
     * @param activity
     */
    public void setActivityState(Activity activity) {
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
    /**
    返回布局文件id
    */
    protected abstract int getLayoutID();

    /**
     *初始化视图控件
     */
    protected abstract void initView(Bundle savedInstanceState);

    /**
     * * 初始化数据
    */
    protected abstract void initData();

    /**
     *初始化监听
     */
    protected abstract void initListener();


    /**
     * 不带参数跳转页面
     * @param toActivity
     */
    protected void openActivity(Class<? extends BaseActivity> toActivity) {
        openActivity(toActivity, null);
    }

    /**
     * 带参数跳转
     * @param toActivity
     * @param parameter
     */
    protected void openActivity(Class<? extends BaseActivity> toActivity, Bundle parameter) {
        Intent intent = new Intent(this, toActivity);
        if (parameter != null) {
            intent.putExtras(parameter);
        }
        startActivity(intent);
    }

    /**
     * 设置toolbar
     * @param toolbar
     * @param title
     */
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

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "onStart: " );
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "onStop: " );
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onPause: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy: ");
       // AppManager.getAppManager().finishActivity();
    }
}
