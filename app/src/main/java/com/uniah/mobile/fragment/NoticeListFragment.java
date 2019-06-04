package com.uniah.mobile.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uniah.mobile.R;
import com.uniah.mobile.adapter.NoticeListAdapter;
import com.uniah.mobile.base.BaseData;
import com.uniah.mobile.base.BaseFragment;
import com.uniah.mobile.bean.MsgListData;
import com.uniah.mobile.bean.MsgListHeadData;
import com.uniah.mobile.bean.SearchData;
import com.uniah.mobile.layout.UniPullLayout;

import java.util.ArrayList;

public class NoticeListFragment extends BaseFragment {
    RecyclerView mRecyclerView;
    UniPullLayout mPullRefreshLayout;
    NoticeListAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notice_list, container, false);
        initView(view);
        initData();
        return view;
    }
    private void initView(View view) {
        mRecyclerView = view.findViewById(R.id.pull_list_recycleView);
        mPullRefreshLayout = view.findViewById(R.id.pull_list_refresh_layout);
        mPullRefreshLayout.setBackgroundColor(Color.WHITE);

        mAdapter = new NoticeListAdapter(getActivity(), new ArrayList<BaseData>());
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

        MsgListHeadData data = new MsgListHeadData();

        data.setLeftImageResource(R.drawable.ic_like_white);
        data.setMidImageResource(R.drawable.ic_comment_white);
        data.setRightImageResource(R.drawable.ic_at_white);

        data.setLeftName("点赞");
        data.setMidName("评论");
        data.setRightName("@我");

        data.setLeftCount(12);
        data.setMidCount(1);
        data.setRightCount(100);

        mAdapter.add(new SearchData());
        mAdapter.add(data);

        MsgListData data1 = new MsgListData();
        data1.setAcceptHead("http://39.108.118.215/imgs/eb84d6962419be7594bd804bca2cbaa0.jpg");
        data1.setAcceptNick("安吉拉");
        data1.setMsgContent("这是一条测试内容");
        data1.setMsgTime("2019-05-20 19:30:00");
        data1.setMsgCount(1);

        mAdapter.add(data1);
    }
}
