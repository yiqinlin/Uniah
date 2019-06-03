package com.uniah.mobile.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.uniah.mobile.R;
import com.uniah.mobile.adapter.GroupItemAdapter;
import com.uniah.mobile.base.BaseData;
import com.uniah.mobile.bean.GroupHintData;
import com.uniah.mobile.bean.GroupSwitchData;
import com.uniah.mobile.bean.GroupTextData;
import com.uniah.mobile.bean.HintData;
import com.uniah.mobile.layout.IUniLayout;
import com.uniah.mobile.layout.UniPullLayout;
import com.uniah.mobile.util.UniStatusBarHelper;
import com.uniah.mobile.view.UniCommonTopBar;

import java.util.ArrayList;

public class SetPrivacyActivity extends AppCompatActivity {
    UniCommonTopBar mTopBar;
    RecyclerView mRecyclerView;
    UniPullLayout mPullRefreshLayout;
    GroupItemAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_privacy);
        UniStatusBarHelper.translucent(this);

        initView();
        initTopBar();
        initData();
    }

    private void initView() {
        mTopBar = findViewById(R.id.top_bar);
        mRecyclerView = findViewById(R.id.pull_list_recycleView);
        mPullRefreshLayout = findViewById(R.id.pull_list_refresh_layout);

        mAdapter = new GroupItemAdapter(this, new ArrayList<BaseData>());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);

    }

    private void initData() {

        GroupHintData hintData = new GroupHintData();
        hintData.setHint("通讯录");
        mAdapter.add(hintData);

        GroupSwitchData switchData = new GroupSwitchData();
        switchData.setText("允许给我推荐通讯录好友");
        switchData.setHideRadiusSide(IUniLayout.HIDE_RADIUS_SIDE_BOTTOM);
        switchData.setChecked(true);
        mAdapter.add(switchData);

        GroupSwitchData switchData1 = new GroupSwitchData();
        switchData1.setText("允许通过手机号找到我");
        switchData1.setHideRadiusSide(IUniLayout.HIDE_RADIUS_SIDE_TOP);
        switchData1.setChecked(true);
        mAdapter.add(switchData1);

        GroupHintData hintData1 = new GroupHintData();
        hintData1.setHint("互动");
        mAdapter.add(hintData1);

        GroupSwitchData switchData2 = new GroupSwitchData();
        switchData2.setText("不向别人展示我的喜欢");
        switchData2.setChecked(true);
        mAdapter.add(switchData2);
    }

    public void initTopBar() {
        mTopBar.setTitle("隐私");
        mTopBar.setLeftButtonImage(R.drawable.ic_home_black_24dp);
        mTopBar.setOnLeftButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
