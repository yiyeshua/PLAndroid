package com.yiyeshu.plandroid.module.splash;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.yiyeshu.plandroid.R;
import com.yiyeshu.plandroid.base.BaseActivity;
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
    CountDownTimer countDownTimer = new CountDownTimer(4000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            btnSplash.setText("跳过 " + (millisUntilFinished / 1000 - 1));
        }

        @Override
        public void onFinish() {
            btnSplash.setClickable(true);
            mPresenter.jumpTo(countDownTimer);
        }
    };

    @Override
    protected void initListener() {
        btnSplash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.jumpTo(countDownTimer);
            }
        });


    }

    @Override
    protected void initData() {
        mPresenter.getSplashImage();
        countDownTimer.start();
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

    @Override
    public void startActivity(Class<? extends BaseActivity> toActivity) {
        openActivity(toActivity);
    }


}
