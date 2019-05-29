package com.uniah.mobile.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uniah.mobile.R;
import com.uniah.mobile.adapter.FeedAdapter;
import com.uniah.mobile.adapter.MineAdapter;
import com.uniah.mobile.base.BaseData;
import com.uniah.mobile.base.BaseFragment;
import com.uniah.mobile.bean.FeedData;
import com.uniah.mobile.bean.MineHeadData;
import com.uniah.mobile.layout.UniPullLayout;
import com.uniah.mobile.util.UniStatusBarHelper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends BaseFragment {
    RecyclerView mRecyclerView;
    UniPullLayout mPullRefreshLayout;
    MineAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        UniStatusBarHelper.translucent(getActivity());

        initView(view);
        initData();
        return view;
    }

    private void initView(View view) {
        mRecyclerView = view.findViewById(R.id.pull_list_recycleView);
        mPullRefreshLayout = view.findViewById(R.id.pull_list_refresh_layout);

        mAdapter = new MineAdapter(getActivity(), new ArrayList<BaseData>());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);

    }

    private void initData() {

        MineHeadData data = new MineHeadData();

        data.setUserHead("http://39.108.118.215/imgs/eb84d6962419be7594bd804bca2cbaa0.jpg");
        data.setUserNick("小笨狗");
        data.setUserSlogan("万物皆可破！");

        data.setLeftCount(12);
        data.setMidCount(111);
        data.setRightCount(20);

        data.setLeftTitle("动态");
        data.setMidTitle("我认识");
        data.setRightTitle("认识我");

        mAdapter.add(data);
    }

}
