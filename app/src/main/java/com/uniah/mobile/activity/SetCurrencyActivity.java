package com.uniah.mobile.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.uniah.mobile.R;
import com.uniah.mobile.adapter.GroupItemAdapter;
import com.uniah.mobile.base.BaseData;
import com.uniah.mobile.bean.GroupSwitchData;
import com.uniah.mobile.bean.GroupTextData;
import com.uniah.mobile.bean.HintData;
import com.uniah.mobile.layout.IUniLayout;
import com.uniah.mobile.layout.UniPullLayout;
import com.uniah.mobile.util.UniStatusBarHelper;
import com.uniah.mobile.view.UniCommonTopBar;

import java.util.ArrayList;

public class SetCurrencyActivity extends AppCompatActivity {
    UniCommonTopBar mTopBar;
    RecyclerView mRecyclerView;
    UniPullLayout mPullRefreshLayout;
    GroupItemAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_currency);
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

        GroupTextData textData1 = new GroupTextData();
        textData1.setText("图片设置");
        textData1.setSubText("自适应");
        textData1.setShowBtn(true);
        textData1.setHideRadiusSide(IUniLayout.HIDE_RADIUS_SIDE_BOTTOM);
        mAdapter.add(textData1);

        GroupTextData textData2 = new GroupTextData();
        textData2.setText("视频和动图自动播放");
        textData2.setSubText("4G和WIFI");
        textData2.setShowBtn(true);
        textData2.setHideRadiusSide(IUniLayout.HIDE_RADIUS_SIDE_MID);
        mAdapter.add(textData2);

        GroupTextData textData3 = new GroupTextData();
        textData3.setText("视频上传清晰度");
        textData3.setSubText("720P");
        textData3.setShowBtn(true);
        textData3.setHideRadiusSide(IUniLayout.HIDE_RADIUS_SIDE_TOP);
        mAdapter.add(textData3);

        GroupSwitchData switchData = new GroupSwitchData();
        switchData.setText("WiFi下自动下载安装包");
        switchData.setChecked(true);
        mAdapter.add(switchData);
    }

    public void initTopBar() {
        mTopBar.setTitle("通用");
        mTopBar.setLeftButtonImage(R.drawable.ic_home_black_24dp);
        mTopBar.setOnLeftButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
