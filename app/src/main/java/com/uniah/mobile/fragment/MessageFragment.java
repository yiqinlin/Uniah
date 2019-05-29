package com.uniah.mobile.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uniah.mobile.R;
import com.uniah.mobile.base.BaseViewPagerAdapter;
import com.uniah.mobile.base.BaseFragment;
import com.uniah.mobile.view.UniSegmentTopBar;
import com.uniah.mobile.view.UniViewPager;

import java.util.ArrayList;
import java.util.List;

public class MessageFragment extends BaseFragment {

    UniSegmentTopBar mTopBar;
    UniViewPager mViewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, container, false);
        mTopBar = view.findViewById(R.id.top_bar);
        mViewPager = view.findViewById(R.id.message_view_pager);

        initViewPager();
        initTopBar();
        return view;
    }


    public void initViewPager() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new MsgListFragment());
        fragments.add(new NoticeListFragment());
        BaseViewPagerAdapter adapter = new BaseViewPagerAdapter(getChildFragmentManager(), fragments);
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(2);
    }

    public void initTopBar() {
        mTopBar.setTabLeft("消息");
        mTopBar.setTabRight("通知");
        mTopBar.setViewPager(mViewPager);
        mTopBar.hasCursor(true);
        mTopBar.setButtonImage(R.drawable.ic_notifications_black_24dp);
        mTopBar.setShadow(0, 1);
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
            }

            @Override
            public void onTabUnselected(int index, View v) {
            }

            @Override
            public void onTabReselected(int index, View v) {
            }

            @Override
            public void onDoubleTap(int index, View v) {
            }
        });
    }
}
