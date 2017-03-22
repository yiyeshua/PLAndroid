package com.yiyeshu.plandroid;

import android.app.Application;

import com.yiyeshu.common.utils.AppUtils;

/**
 * Created by lhw on 2017/3/22.
 */
public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AppUtils.init(this);
    }
}
