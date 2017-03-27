package com.yiyeshu.plandroid.ui.splash;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.yiyeshu.plandroid.global.Constants;
import com.yiyeshu.plandroid.mvpframe.rx.RxSchedulerHelper;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by lhw on 2017/3/21.
 */
public class SplashModel implements SplashContract.Model {
    @Override
    public Observable<Bitmap> getSplahImg() {

        return Observable.create(new Observable.OnSubscribe<Bitmap>() {
            @Override
            public void call(Subscriber<? super Bitmap> subscriber) {
                OkHttpClient client = new OkHttpClient();
                //获取请求对象
                Request request = new Request.Builder().url(Constants.IMG_SPLASH_PATH).build();
                //获取响应体
                ResponseBody body = null;
                try {
                    body = client.newCall(request).execute().body();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //获取流
                InputStream in = body.byteStream();
                //转化为bitmap
                Bitmap bitmap = BitmapFactory.decodeStream(in);
                if(bitmap!=null){
                    subscriber.onNext(bitmap);
                }
            }
        }).compose(RxSchedulerHelper.<Bitmap>io_main());
    }
}
