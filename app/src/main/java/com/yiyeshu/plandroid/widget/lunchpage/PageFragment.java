package com.yiyeshu.plandroid.widget.lunchpage;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yiyeshu.plandroid.MainActivity;
import com.yiyeshu.plandroid.R;


/**
 *简单的引导页
 * data：2017/2/22
 */
public class PageFragment extends Fragment {
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle args = getArguments();
        int index = args.getInt("index");
        int layoutId = args.getInt("layoutId");
        int count = args.getInt("count");
        rootView = inflater.inflate(layoutId, null);
        // 滑动到最后一页有点击事件
        if (index == count - 1) {
            rootView.findViewById(R.id.id_ok).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(getActivity(), MainActivity.class);
                    getActivity().startActivity(intent);
                }
            });
        }
        if (index == count - 4) {
            rootView.findViewById(R.id.id_skip).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(getActivity(), MainActivity.class);
                    getActivity().startActivity(intent);
                }
            });
        }

        return rootView;
    }
}
