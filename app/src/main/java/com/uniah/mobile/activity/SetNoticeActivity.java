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

public class SetNoticeActivity extends AppCompatActivity {
    UniCommonTopBar mTopBar;
    RecyclerView mRecyclerView;
    UniPullLayout mPullRefreshLayout;
    GroupItemAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_notice);
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

        GroupSwitchData switchData = new GroupSwitchData();
        switchData.setText("震动");
        switchData.setHideRadiusSide(IUniLayout.HIDE_RADIUS_SIDE_BOTTOM);
        switchData.setChecked(true);
        mAdapter.add(switchData);

        GroupSwitchData switchData1 = new GroupSwitchData();
        switchData1.setText("声音");
        switchData1.setHideRadiusSide(IUniLayout.HIDE_RADIUS_SIDE_TOP);
        switchData1.setChecked(true);
        mAdapter.add(switchData1);

        GroupHintData hintData = new GroupHintData();
        hintData.setHint("声音类型");
        mAdapter.add(hintData);

        GroupTextData textData = new GroupTextData();
        textData.setText("铃声1");
        textData.setShowBtn(true);
        textData.setHideRadiusSide(IUniLayout.HIDE_RADIUS_SIDE_BOTTOM);
        textData.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        mAdapter.add(textData);

        GroupTextData textData1 = new GroupTextData();
        textData1.setText("铃声2");
        textData1.setShowBtn(true);
        textData1.setHideRadiusSide(IUniLayout.HIDE_RADIUS_SIDE_MID);
        textData1.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        mAdapter.add(textData1);

        GroupTextData textData2 = new GroupTextData();
        textData2.setText("铃声3");
        textData2.setShowBtn(true);
        textData2.setHideRadiusSide(IUniLayout.HIDE_RADIUS_SIDE_MID);
        textData2.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        mAdapter.add(textData2);

        GroupTextData textData3 = new GroupTextData();
        textData3.setText("铃声4");
        textData3.setShowBtn(true);
        textData3.setHideRadiusSide(IUniLayout.HIDE_RADIUS_SIDE_MID);
        textData3.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        mAdapter.add(textData3);

        GroupTextData textData4 = new GroupTextData();
        textData4.setText("铃声5");
        textData4.setShowBtn(true);
        textData4.setHideRadiusSide(IUniLayout.HIDE_RADIUS_SIDE_TOP);
        textData4.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        mAdapter.add(textData3);
    }

    public void initTopBar() {
        mTopBar.setTitle("通知设置");
        mTopBar.setLeftButtonImage(R.drawable.ic_home_black_24dp);
        mTopBar.setOnLeftButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
