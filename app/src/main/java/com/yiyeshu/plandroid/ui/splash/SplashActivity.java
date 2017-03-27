package com.yiyeshu.plandroid.ui.splash;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.yiyeshu.plandroid.R;
import com.yiyeshu.plandroid.mvpframe.base.BaseFrameActivity;

import butterknife.BindView;


/**
 * 欢迎页
 */
public class SplashActivity extends BaseFrameActivity<SplashPresent, SplashModel> implements SplashContract.View {


    @BindView(R.id.img_splash)
    ImageView imgSplash;
    @BindView(R.id.btn_splash)
    Button btnSplash;

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        mPresenter.getSplashImage();
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_splash;
    }

    @Override
    public void loadSplashImage(Bitmap bitmap) {
        imgSplash.setImageBitmap(bitmap);
    }

}
