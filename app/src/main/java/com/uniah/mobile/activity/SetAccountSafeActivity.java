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
import com.uniah.mobile.bean.GroupTextData;
import com.uniah.mobile.bean.GroupUserData;
import com.uniah.mobile.layout.IUniLayout;
import com.uniah.mobile.layout.UniPullLayout;
import com.uniah.mobile.util.UniStatusBarHelper;
import com.uniah.mobile.view.UniCommonTopBar;

import java.util.ArrayList;

public class SetAccountSafeActivity extends AppCompatActivity {
    UniCommonTopBar mTopBar;
    RecyclerView mRecyclerView;
    UniPullLayout mPullRefreshLayout;
    GroupItemAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_accout_safe);
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

        GroupTextData textData = new GroupTextData();
        textData.setText("友你ID");
        textData.setSubText("10001");
        textData.setShowBtn(false);
        mAdapter.add(textData);

        GroupTextData textData1 = new GroupTextData();
        textData1.setText("手机号");
        textData1.setSubText("18349249158");
        textData1.setShowBtn(true);
        textData1.setHideRadiusSide(IUniLayout.HIDE_RADIUS_SIDE_BOTTOM);
        textData1.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SetAccountSafeActivity.this, SetMsgNoticeActivity.class);
                startActivity(intent);
            }
        });
        mAdapter.add(textData1);

        GroupTextData textData2 = new GroupTextData();
        textData2.setText("密码");
        textData2.setSubText("修改密码");
        textData2.setShowBtn(true);
        textData2.setHideRadiusSide(IUniLayout.HIDE_RADIUS_SIDE_TOP);
        textData2.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SetAccountSafeActivity.this, SetDisturbActivity.class);
                startActivity(intent);
            }
        });
        mAdapter.add(textData2);

        GroupTextData textData3 = new GroupTextData();
        textData3.setText("安全锁");
        textData3.setSubText("关");
        textData3.setShowBtn(true);
        textData3.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SetAccountSafeActivity.this, SetSafeLockActivity.class);
                startActivity(intent);
            }
        });
        mAdapter.add(textData3);
    }

    public void initTopBar() {
        mTopBar.setTitle("账号与安全");
        mTopBar.setLeftButtonImage(R.drawable.ic_back_theme);
        mTopBar.setOnLeftButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
