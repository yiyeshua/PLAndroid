package com.yiyeshu.plandroid.ui.register;

import com.yiyeshu.plandroid.mvpframe.base.BaseModel;
import com.yiyeshu.plandroid.mvpframe.base.BasePresenter;
import com.yiyeshu.plandroid.mvpframe.base.BaseView;

/**
 * Created by lhw on 2017/4/14.
 */
public interface RegisterContract {
    //负责获取图片，不管从何处获取。数据库or网络or内存卡
    interface Model extends BaseModel {
    }

    //负责加载图片，该方法由实现LoginContract.View接口的activity实现具体逻辑
    interface View extends BaseView {
    }

    //Presenter负责获取图片，该方法的逻辑由继承了LoginContract.Presenter类的presenter类实现，
    abstract class Presenter extends BasePresenter<Model, View> {
    }
}
