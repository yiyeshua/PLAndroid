package com.yiyeshu.plandroid.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yiyeshu.plandroid.R;
import com.yiyeshu.plandroid.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragmen extends BaseFragment {


    public NewsFragmen() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

}
