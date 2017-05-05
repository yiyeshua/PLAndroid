package com.yiyeshu.plandroid.module.login;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.yiyeshu.common.utils.ToastUtils;
import com.yiyeshu.common.views.EditTextPlus;
import com.yiyeshu.plandroid.MainActivity;
import com.yiyeshu.plandroid.R;
import com.yiyeshu.plandroid.bean.User;
import com.yiyeshu.plandroid.module.register.RegisterActivity;
import com.yiyeshu.plandroid.mvpframe.base.BaseFrameActivity;

import java.util.List;

import butterknife.BindView;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class LoginActivity extends BaseFrameActivity<LoginPresent, LoginModel> implements LoginContract.View, View.OnClickListener {

    @BindView(R.id.logo)
    ImageView mLogo;
    @BindView(R.id.et_mobile)
    EditTextPlus mEtMobile;
    @BindView(R.id.et_password)
    EditTextPlus mEtPassword;
    @BindView(R.id.btn_login)
    Button mBtnLogin;
    @BindView(R.id.regist)
    TextView mRegist;
    @BindView(R.id.forget_password)
    TextView mForgetPassword;
    @BindView(R.id.scrollView)
    ScrollView mScrollView;
    @BindView(R.id.service)
    LinearLayout mService;
    @BindView(R.id.root)
    RelativeLayout mRoot;

    private int screenHeight = 0;//屏幕高度
    private int keyHeight = 0; //软件盘弹起后所占高度
    private float scale = 0.6f; //logo缩放比例
    private View service;
    private ProgressDialog mProgressDialog;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        AndroidBug5497Workaround.assistActivity(this);
        service = findViewById(R.id.service);
        screenHeight = this.getResources().getDisplayMetrics().heightPixels; //获取屏幕高度
        keyHeight = screenHeight / 3;//弹起高度为屏幕高度的1/3
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        mBtnLogin.setOnClickListener(this);
        mRegist.setOnClickListener(this);
        mForgetPassword.setOnClickListener(this);
        mEtMobile.setOnButtonClickListener(new EditTextPlus.OnButtonClickListener() {
            @Override
            public void onButtonClick() {
                mEtMobile.setText("");
            }
        });

        mScrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        mRoot.addOnLayoutChangeListener(new ViewGroup.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
              /* old是改变前的左上右下坐标点值，没有old的是改变后的左上右下坐标点值
              现在认为只要控件将Activity向上推的高度超过了1/3屏幕高，就认为软键盘弹起*/
                if (oldBottom != 0 && bottom != 0 && (oldBottom - bottom > keyHeight)) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mScrollView.smoothScrollTo(0, mScrollView.getHeight());
                        }
                    }, 0);
                    zoomIn(mLogo, (oldBottom - bottom) - keyHeight);
                    service.setVisibility(View.INVISIBLE);
                } else if (oldBottom != 0 && bottom != 0 && (bottom - oldBottom > keyHeight)) {
                    //键盘收回后，logo恢复原来大小，位置同样回到初始位置
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mScrollView.smoothScrollTo(0, mScrollView.getHeight());
                        }
                    }, 0);
                    zoomOut(mLogo, (bottom - oldBottom) - keyHeight);
                    service.setVisibility(View.VISIBLE);
                }
            }
        });
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login :
                login();
                break;
            case R.id.regist:
                openActivity(RegisterActivity.class);
                break;
            case R.id.forget_password:
                ToastUtils.showToast(this,"联系管理员");
                break;
        }
    }

    private void login() {
        //0、验证用户名和密码格式
        String username=mEtMobile.getText().toString();
        String password = mEtPassword.getText().toString();
        if(TextUtils.isEmpty(username) || TextUtils.isEmpty(password)){
            ToastUtils.showToast(this,"用户名或密码不能为空");
            return;
        }
  /*      if (!password.matches("[A-Za-z0-9]+")) {
            ToastUtils.showToast(this,"请使用字母和数字组合作为密码");
            mEtPassword.setText("");
        }*/
        //1、显示登录加载对话框
        showDialog();
        //2、另开线程检查用户名和密码
        User user=new User();
        user.setName(username);
        user.setPassword(password);


        BmobQuery<User> query = new BmobQuery<User>();
        query.addWhereEqualTo("name",username);
        query.addWhereEqualTo("password",password);
        query.findObjects(new FindListener<User>() {
            @Override
            public void done(List<User> list, BmobException e) {
                //3、 如果有则关闭对话框，跳转主页，保存token或者登录标记，没有则弹出提示，用户名或密码错误

                if(list!=null && list.size()>0){
                    Log.e("bmob","查询成功："+list.toString());
                    Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    openActivity(MainActivity.class);
                }else{
                    Log.e("bmob","失败："+e.getMessage()+","+e.getErrorCode());
                    Toast.makeText(LoginActivity.this, "登录失败，未查询到该用户，请先注册", Toast.LENGTH_SHORT).show();
                    openActivity(RegisterActivity.class);
                }
                mProgressDialog.dismiss();
            }
        });

     /*   BmobQuery bmobQuery = new BmobQuery("User");
        bmobQuery.findObjectsByTable(new QueryListener<JSONArray>() {
            @Override
            public void done(JSONArray jsonArray, BmobException e) {
                //3、 如果有则关闭对话框，跳转主页，保存token或者登录标记，没有则弹出提示，用户名或密码错误
                if(e==null){
                    Log.e("bmob","查询成功："+jsonArray.toString());
                    Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();

                    openActivity(MainActivity.class);
                }else{
                    Log.e("bmob","失败："+e.getMessage()+","+e.getErrorCode());
                    Toast.makeText(LoginActivity.this, "登录失败，未查询到该用户，请先注册", Toast.LENGTH_SHORT).show();
                    openActivity(RegisterActivity.class);
                }
                mProgressDialog.dismiss();
            }
        });*/




        
    }

    private void showDialog() {
        mProgressDialog = new ProgressDialog(LoginActivity.this);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setMessage("正在登录");
        mProgressDialog.show();

    }


    /**
     * 缩小
     * @param view
     */
    public void zoomIn(final View view, float dist) {
        view.setPivotY(view.getHeight());
        view.setPivotX(view.getWidth() / 2);
        AnimatorSet mAnimatorSet = new AnimatorSet();
        ObjectAnimator mAnimatorScaleX = ObjectAnimator.ofFloat(view, "scaleX", 1.0f, scale);
        ObjectAnimator mAnimatorScaleY = ObjectAnimator.ofFloat(view, "scaleY", 1.0f, scale);
        ObjectAnimator mAnimatorTranslateY = ObjectAnimator.ofFloat(view, "translationY", 0.0f, -dist);

        mAnimatorSet.play(mAnimatorTranslateY).with(mAnimatorScaleX);
        mAnimatorSet.play(mAnimatorScaleX).with(mAnimatorScaleY);
        mAnimatorSet.setDuration(1000);
        mAnimatorSet.start();
    }

    /**
     * 放大
     * @param view
     */
    public void zoomOut(final View view, float dist) {
        view.setPivotY(view.getHeight());
        view.setPivotX(view.getWidth() / 2);
        AnimatorSet mAnimatorSet = new AnimatorSet();

        ObjectAnimator mAnimatorScaleX = ObjectAnimator.ofFloat(view, "scaleX", scale, 1.0f);
        ObjectAnimator mAnimatorScaleY = ObjectAnimator.ofFloat(view, "scaleY", scale, 1.0f);
        ObjectAnimator mAnimatorTranslateY = ObjectAnimator.ofFloat(view, "translationY", -dist, 0);
        //ObjectAnimator mAnimatorTranslateY = ObjectAnimator.ofFloat(view, "translationY", view.getTranslationY(), 0);

        mAnimatorSet.play(mAnimatorTranslateY).with(mAnimatorScaleX);
        mAnimatorSet.play(mAnimatorScaleX).with(mAnimatorScaleY);
        mAnimatorSet.setDuration(1000);
        mAnimatorSet.start();
    }

}
