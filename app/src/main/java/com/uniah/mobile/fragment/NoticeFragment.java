package com.uniah.mobile.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uniah.mobile.R;
import com.uniah.mobile.adapter.NoticeAdapter;
import com.uniah.mobile.adapter.NoticeListAdapter;
import com.uniah.mobile.base.BaseData;
import com.uniah.mobile.base.BaseFragment;
import com.uniah.mobile.bean.MsgListData;
import com.uniah.mobile.bean.MsgListHeadData;
import com.uniah.mobile.bean.NoticeData;
import com.uniah.mobile.bean.SearchData;
import com.uniah.mobile.layout.UniPullLayout;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class NoticeFragment extends BaseFragment {

    RecyclerView mRecyclerView;
    UniPullLayout mPullRefreshLayout;
    NoticeAdapter mAdapter;
    int mNoticeType = 0;

    public NoticeFragment() {

    }

    public NoticeFragment setType(int type) {
        this.mNoticeType = type;
        return this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notice, container, false);

        initView(view);
        initData();
        return view;
    }

    private void initView(View view) {
        mRecyclerView = view.findViewById(R.id.pull_list_recycleView);
        mPullRefreshLayout = view.findViewById(R.id.pull_list_refresh_layout);

        mAdapter = new NoticeAdapter(getActivity(), new ArrayList<BaseData>());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);

    }

    private void initData() {

        mPullRefreshLayout.setOnPullRefreshListener(new UniPullLayout.OnPullRefreshListener() {
            @Override
            public void onMoveTarget(int offset) {
            }

            @Override
            public void onMoveRefreshView(int offset) {
            }

            @Override
            public void onRefresh() {
                mPullRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPullRefreshLayout.finishRefreshDelay("刷新成功");
                    }
                }, 2000);
            }
        });




        NoticeData data = new NoticeData();
        data.setHead("http://39.108.118.215/imgs/eb84d6962419be7594bd804bca2cbaa0.jpg");
        data.setNick("安吉拉");
        data.setContent("这是一条测试内容");
        data.setTime("2019-05-20 19:30:00");
        data.setFromContent("小笨狗：这是文字加三张图的布局～");
        data.setFromThumb("http://39.108.118.215/imgs/e8381b560354c331eb03a3620e0fc483.jpg");

        mAdapter.add(data);
    }

}
