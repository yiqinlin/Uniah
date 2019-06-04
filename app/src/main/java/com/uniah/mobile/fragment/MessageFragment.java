package com.uniah.mobile.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uniah.mobile.R;
import com.uniah.mobile.activity.ContactActivity;
import com.uniah.mobile.base.BaseViewPagerAdapter;
import com.uniah.mobile.base.BaseFragment;
import com.uniah.mobile.dialog.MoreDialog;
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
        mTopBar.setTabNum(2);
        mTopBar.setTabLeft("消息");
        mTopBar.setTabMid("通知");
        mTopBar.setTabGravity(Gravity.START);
        mTopBar.setViewPager(mViewPager);
        mTopBar.hasCursor(true);
        mTopBar.setRightButtonImage(R.drawable.ic_more_theme);
        mTopBar.setShadow(0, 1);
        mTopBar.setRightButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MoreDialog dialog = new MoreDialog(getActivity(), getMoreDialogList());
                dialog.show();
            }
        });

        mTopBar.setRightSubButtonImage(R.drawable.ic_contact_theme);
        mTopBar.setOnRightSubButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ContactActivity.class);
                startActivity(intent);
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
