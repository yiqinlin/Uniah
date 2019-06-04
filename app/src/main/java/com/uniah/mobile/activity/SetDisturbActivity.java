package com.uniah.mobile.activity;

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

public class SetDisturbActivity extends AppCompatActivity {
    UniCommonTopBar mTopBar;
    RecyclerView mRecyclerView;
    UniPullLayout mPullRefreshLayout;
    GroupItemAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_disturb);
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
        switchData.setText("勿扰模式");
        switchData.setChecked(false);
        mAdapter.add(switchData);

        GroupHintData hintData = new GroupHintData();
        hintData.setHint("在设定时间内不会发出响铃或震动");
        mAdapter.add(hintData);

        GroupTextData textData1 = new GroupTextData();
        textData1.setText("开始时间");
        textData1.setSubText("22:00");
        textData1.setShowBtn(true);
        textData1.setHideRadiusSide(IUniLayout.HIDE_RADIUS_SIDE_BOTTOM);
        mAdapter.add(textData1);

        GroupTextData textData2 = new GroupTextData();
        textData2.setText("结束时间");
        textData2.setSubText("08:00");
        textData2.setShowBtn(true);
        textData2.setHideRadiusSide(IUniLayout.HIDE_RADIUS_SIDE_TOP);
        mAdapter.add(textData2);

    }

    public void initTopBar() {
        mTopBar.setTitle("勿扰模式");
        mTopBar.setLeftButtonImage(R.drawable.ic_back_theme);
        mTopBar.setOnLeftButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
