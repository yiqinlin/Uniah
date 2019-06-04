package com.uniah.mobile.activity;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.View;

import com.uniah.mobile.R;
import com.uniah.mobile.base.BaseFragmentActivity;
import com.uniah.mobile.base.BaseViewPagerAdapter;
import com.uniah.mobile.fragment.FeedFragment;
import com.uniah.mobile.fragment.HotFragment;
import com.uniah.mobile.fragment.NoticeFragment;
import com.uniah.mobile.util.UniStatusBarHelper;
import com.uniah.mobile.view.UniSegmentTopBar;
import com.uniah.mobile.view.UniViewPager;

import java.util.ArrayList;
import java.util.List;

public class NoticeActivity extends BaseFragmentActivity {


    private int mNoticeType;

    UniSegmentTopBar mTopBar;
    UniViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        UniStatusBarHelper.translucent(this);
        mNoticeType = getIntent().getIntExtra("noticeType", 0);
        initView();
        initViewPager();
        initTopBar();

    }

    public void initView() {
        mTopBar = findViewById(R.id.top_bar);
        mViewPager = findViewById(R.id.home_view_pager);
    }


    public void initViewPager() {
        List<Fragment> fragments = new ArrayList<Fragment>();
        fragments.add(new NoticeFragment().setType(0));
        fragments.add(new NoticeFragment().setType(1));
        fragments.add(new NoticeFragment().setType(2));
        BaseViewPagerAdapter adapter = new BaseViewPagerAdapter(getSupportFragmentManager(), fragments);
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setCurrentItem(mNoticeType);
    }

    public void initTopBar() {
        mTopBar.setTabNum(3);
        mTopBar.setTabLeft("点赞");
        mTopBar.setTabMid("评论");
        mTopBar.setTabRight("@我");
        mTopBar.setViewPager(mViewPager);
        mTopBar.hasCursor(true);
        mTopBar.setRightButtonClickListener(null);
        mTopBar.setLeftButtonImage(R.drawable.ic_back_theme);
        mTopBar.setLeftButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTopBar.onTabSelect(mNoticeType);
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
