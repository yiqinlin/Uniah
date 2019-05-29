package com.uniah.mobile.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uniah.mobile.R;
import com.uniah.mobile.adapter.MsgListAdapter;
import com.uniah.mobile.base.BaseData;
import com.uniah.mobile.base.BaseFragment;
import com.uniah.mobile.bean.MsgListData;
import com.uniah.mobile.bean.SearchData;
import com.uniah.mobile.layout.UniPullLayout;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MsgListFragment extends BaseFragment {
    RecyclerView mRecyclerView;
    UniPullLayout mPullRefreshLayout;
    MsgListAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_msg_list, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initView(View view) {
        mRecyclerView = view.findViewById(R.id.pull_list_recycleView);
        mPullRefreshLayout = view.findViewById(R.id.pull_list_refresh_layout);

        mAdapter = new MsgListAdapter(getActivity(), new ArrayList<BaseData>());
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

        mPullRefreshLayout.setOnPullLoadListener(new UniPullLayout.OnPullLoadListener() {
            @Override
            public void onMoveTarget(int offset) {
            }

            @Override
            public void onMoveLoadView(int offset) {
            }

            @Override
            public void onLoad() {
                mPullRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPullRefreshLayout.finishLoadDelay("加载成功");
                    }
                }, 2000);
            }
        });

        MsgListData data = new MsgListData();
        data.setAcceptHead("http://39.108.118.215/imgs/eb84d6962419be7594bd804bca2cbaa0.jpg");
        data.setAcceptNick("安吉拉");
        data.setMsgContent("这是一条测试内容");
        data.setMsgTime("2019-05-20 19:30:00");
        data.setMsgCount(1);

        mAdapter.add(new SearchData());
        mAdapter.add(data);
    }
}
