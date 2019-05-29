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
import com.uniah.mobile.adapter.LifeAdapter;
import com.uniah.mobile.base.BaseData;
import com.uniah.mobile.base.BaseFragment;
import com.uniah.mobile.bean.CarouselData;
import com.uniah.mobile.bean.FeedData;
import com.uniah.mobile.bean.LifeData;
import com.uniah.mobile.layout.UniPullLayout;
import com.uniah.mobile.view.UniSegmentTopBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class LifeFragment extends BaseFragment {

    UniSegmentTopBar mTopBar;
    RecyclerView mRecyclerView;
    UniPullLayout mPullRefreshLayout;
    LifeAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_life, container, false);
        initView(view);
        initTopBar();
        initData();

        return view;
    }

    private void initView(View view) {
        mTopBar = view.findViewById(R.id.top_bar);
        mRecyclerView = view.findViewById(R.id.pull_list_recycleView);
        mPullRefreshLayout = view.findViewById(R.id.pull_list_refresh_layout);

        mAdapter = new LifeAdapter(getActivity(), new ArrayList<BaseData>());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);

    }

    private void initData() {

        CarouselData data = new CarouselData();
        Map<Integer, String> map = new HashMap<>();
        map.put(0, "http://39.108.118.215/imgs/e8381b560354c331eb03a3620e0fc483.jpg");
        map.put(1, "http://39.108.118.215/imgs/5a66d885b0dd007736133ee715540c2c.jpg");
        map.put(2, "http://39.108.118.215/imgs/d57bf3ac7284d43091f1dbeed8c08d92.jpg");
        data.setImages(map);

        mAdapter.add(data);

        LifeData data1 = new LifeData();
        data1.setImg("http://39.108.118.215/imgs/e8381b560354c331eb03a3620e0fc483.jpg");
        data1.setTitle("团队");
        mAdapter.add(data1);

        LifeData data2 = new LifeData();
        data2.setImg("http://39.108.118.215/imgs/e8381b560354c331eb03a3620e0fc483.jpg");
        data2.setTitle("交友");
        mAdapter.add(data2);

        LifeData data3 = new LifeData();
        data3.setImg("http://39.108.118.215/imgs/e8381b560354c331eb03a3620e0fc483.jpg");
        data3.setTitle("任务");
        mAdapter.add(data3);

        LifeData data4 = new LifeData();
        data4.setImg("http://39.108.118.215/imgs/e8381b560354c331eb03a3620e0fc483.jpg");
        data4.setTitle("派对");
        mAdapter.add(data4);
    }

    public void initTopBar() {
        mTopBar.setTabLeft("生活");
        mTopBar.hasCursor(false);
        mTopBar.setButtonImage(R.drawable.ic_notifications_black_24dp);
        mTopBar.setShadow(0, 0);
        mTopBar.setButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mTopBar.setSubButtonImage(R.drawable.ic_notifications_black_24dp);
        mTopBar.setOnSubButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mTopBar.setOnTabClickListener(new UniSegmentTopBar.OnTabClickListener() {
            @Override
            public void onTabSelected(int index, View v) {
                Log.e("tabClick", "onTabSelected" + index);
            }

            @Override
            public void onTabUnselected(int index, View v) {
                Log.e("tabClick", "onTabUnselected" + index);
            }

            @Override
            public void onTabReselected(int index, View v) {
                Log.e("tabClick", "onTabReselected" + index);
            }

            @Override
            public void onDoubleTap(int index, View v) {
                Log.e("tabClick", "onDoubleTap" + index);
            }
        });
    }
}
