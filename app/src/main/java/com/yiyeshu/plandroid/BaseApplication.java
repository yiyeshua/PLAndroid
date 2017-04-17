package com.yiyeshu.plandroid;

import android.app.Application;

import com.yiyeshu.common.utils.AppUtils;

import cn.bmob.v3.Bmob;

/**
 * Created by lhw on 2017/3/22.
 */
public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AppUtils.init(this);
        Bmob.initialize(this, "13f0b9290c2503fcb7e42cef90ab5388");
    }
}
