package com.yiyeshu.plandroid.module.splash;

import android.graphics.Bitmap;
import android.os.CountDownTimer;

import com.yiyeshu.plandroid.base.BaseActivity;
import com.yiyeshu.plandroid.mvpframe.base.BaseModel;
import com.yiyeshu.plandroid.mvpframe.base.BasePresenter;
import com.yiyeshu.plandroid.mvpframe.base.BaseView;

import rx.Observable;

/**
 * Created by lhw on 2017/3/21.
 */
public interface SplashContract {
    //负责获取图片，不管从何处获取。数据库or网络or内存卡
    interface Model extends BaseModel {
        Observable<Bitmap> getSplahImg();
    }

    //负责加载图片，该方法由实现SplashContract.View接口的activity实现具体逻辑
    interface View extends BaseView {
        void loadSplashImage(Bitmap bitmap);
        void startActivity(Class<? extends BaseActivity> toActivity);
    }

    //Presenter负责获取图片，该方法的逻辑由继承了SplashContract.Presenter类的presenter类实现，
    abstract class Presenter extends BasePresenter<Model, View> {
        abstract void getSplashImage();
        abstract void jumpTo(CountDownTimer countDownTimer);
    }
}
