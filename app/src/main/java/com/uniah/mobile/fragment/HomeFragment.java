package com.uniah.mobile.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uniah.mobile.R;
import com.uniah.mobile.activity.FeedPushActivity;
import com.uniah.mobile.activity.SetFeedbackActivity;
import com.uniah.mobile.base.BaseData;
import com.uniah.mobile.base.BaseViewPagerAdapter;
import com.uniah.mobile.base.BaseFragment;
import com.uniah.mobile.bean.MoreDialogData;
import com.uniah.mobile.dialog.MoreDialog;
import com.uniah.mobile.view.UniSegmentTopBar;
import com.uniah.mobile.view.UniViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment {

    UniSegmentTopBar mTopBar;
    UniViewPager mViewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        mTopBar = view.findViewById(R.id.top_bar);
        mViewPager = view.findViewById(R.id.home_view_pager);

        initViewPager();
        initTopBar();

        return view;
    }

    public void initViewPager() {
        List<Fragment> fragments = new ArrayList<Fragment>();
        fragments.add(new FeedFragment());
        fragments.add(new HotFragment());
        BaseViewPagerAdapter adapter = new BaseViewPagerAdapter(getChildFragmentManager(), fragments);
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(2);
    }

    public void initTopBar() {
        mTopBar.setTabNum(2);
        mTopBar.setTabLeft("趣趣");
        mTopBar.setTabMid("秀儿");
        mTopBar.setTabGravity(Gravity.START);
        mTopBar.setViewPager(mViewPager);
        mTopBar.hasCursor(true);
        mTopBar.setRightButtonImage(R.drawable.ic_more_theme);
        mTopBar.setRightButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MoreDialog dialog = new MoreDialog(getActivity(), getMoreDialogList());
                dialog.show();
            }
        });

        mTopBar.setRightSubButtonImage(R.drawable.ic_add_theme);
        mTopBar.setOnRightSubButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FeedPushActivity.class);
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
