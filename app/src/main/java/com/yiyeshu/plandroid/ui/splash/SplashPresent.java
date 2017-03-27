package com.yiyeshu.plandroid.ui.splash;

import android.graphics.Bitmap;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.yiyeshu.common.utils.AppUtils;
import com.yiyeshu.common.utils.ImageUtils;
import com.yiyeshu.plandroid.R;

import rx.Subscriber;

/**
 * Created by lhw on 2017/3/21.
 */
public class SplashPresent extends SplashContract.Presenter {
    @Override
    void getSplashImage() {
        mRxManager.add(mModel.getSplahImg()
        .subscribe(new Subscriber<Bitmap>() {
            @Override
            public void onCompleted() {
                Log.e("TAG", "====: onCompleted");

            }

            @Override
            public void onError(Throwable e) {
                Log.e("TAG", "====: 网络图片加载出错"+e.getMessage().toString());
                mView.loadSplashImage(ImageUtils.drawableToBitmap(ContextCompat.getDrawable(AppUtils.getAppContext(),R.drawable.img_splash)));
            }

            @Override
            public void onNext(Bitmap bitmap) {
                mView.loadSplashImage(bitmap);
            }
        }));
    }
}
