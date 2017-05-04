package com.yiyeshu.plandroid.module.guide;

import android.os.Bundle;
import android.view.View;

import com.yiyeshu.common.utils.AppManager;
import com.yiyeshu.common.utils.SpUtils;
import com.yiyeshu.plandroid.MainActivity;
import com.yiyeshu.plandroid.R;
import com.yiyeshu.plandroid.base.BaseFragment;
import com.yiyeshu.plandroid.global.Constants;


/**
 *简单的引导页
 * data：2017/2/22
 */
public class PageFragment extends BaseFragment {
    private int layoutId;
    private int index;
    private  int count;

    @Override
    protected int getLayoutId() {
        Bundle args = getArguments();
        index = args.getInt("index");
        layoutId = args.getInt("layoutId");
        count = args.getInt("count");
        return layoutId;
    }

    @Override
    public void initView(View contentView, Bundle savedInstanceState) {
        // 滑动到最后一页有点击事件
        if (index == count - 1) {
            mContentView.findViewById(R.id.id_ok).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean isUsered = (boolean) SpUtils.get(mActivity, Constants.IS_FIRST_USERED, false);
                    if(!isUsered){
                        SpUtils.put(mActivity,Constants.IS_FIRST_USERED,true);
                    }
                    openActivity(MainActivity.class);
                    AppManager.getAppManager().finishActivity();
                }
            });
        }
    }

    @Override
    public void initData() {

    }
}
