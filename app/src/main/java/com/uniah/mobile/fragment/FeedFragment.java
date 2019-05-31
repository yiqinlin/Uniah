package com.uniah.mobile.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uniah.mobile.R;
import com.uniah.mobile.adapter.FeedAdapter;
import com.uniah.mobile.base.BaseData;
import com.uniah.mobile.base.BaseFragment;
import com.uniah.mobile.bean.FeedData;
import com.uniah.mobile.layout.UniPullLayout;
import com.uniah.mobile.util.UniDisplayHelper;
import com.uniah.mobile.widget.pullRefreshLayout.UniPullRefreshLayout;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeedFragment extends BaseFragment {

    RecyclerView mRecyclerView;
    UniPullLayout mPullRefreshLayout;
    FeedAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initView(View view) {
        mRecyclerView = view.findViewById(R.id.pull_list_recycleView);
        mPullRefreshLayout = view.findViewById(R.id.pull_list_refresh_layout);

        mAdapter = new FeedAdapter(getActivity(), new ArrayList<BaseData>());
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

        final FeedData data = new FeedData();

        data.setHead("http://39.108.118.215/imgs/eb84d6962419be7594bd804bca2cbaa0.jpg");
        data.setNick("小笨狗");
        data.setFeedTime("2019-05-20 21:30:00");
        data.setFeedContent("这是文字加三张图的布局～");
        HashMap<Integer, String> map = new HashMap<>();
        map.put(0, "http://39.108.118.215/imgs/e8381b560354c331eb03a3620e0fc483.jpg");
        map.put(1, "http://39.108.118.215/imgs/5a66d885b0dd007736133ee715540c2c.jpg");
        map.put(2, "http://39.108.118.215/imgs/d57bf3ac7284d43091f1dbeed8c08d92.jpg");
//        map.put(3, "http://39.108.118.215/imgs/d57bf3ac7284d43091f1dbeed8c08d92.jpg");
//        map.put(4, "http://39.108.118.215/imgs/d57bf3ac7284d43091f1dbeed8c08d92.jpg");
//        map.put(5, "http://39.108.118.215/imgs/d57bf3ac7284d43091f1dbeed8c08d92.jpg");
        data.setFiles(map);
        data.setHotContent("小笨狗：这可真秀啊");
        data.setHotTitle("独秀");
        data.setHasHot(true);

        data.setCommentCount(12);
        data.setLikeCount(23);

        mAdapter.add(data);
        mAdapter.add(data);
    }

}
