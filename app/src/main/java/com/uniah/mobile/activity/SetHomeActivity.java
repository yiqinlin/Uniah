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

public class SetHomeActivity extends AppCompatActivity {
    UniCommonTopBar mTopBar;
    RecyclerView mRecyclerView;
    UniPullLayout mPullRefreshLayout;
    GroupItemAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_home);
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
        GroupUserData userData = new GroupUserData();
        userData.setNick("小笨狗");
        userData.setHead("http://39.108.118.215/imgs/eb84d6962419be7594bd804bca2cbaa0.jpg");
        userData.setSlogan("万物皆可破！");
        userData.setShowArrow(true);
        userData.setShowSubBtn(true);
        userData.setSubImageResourceId(R.drawable.ic_qrcode_grey);
        userData.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SetHomeActivity.this, SetUserDataActivity.class);
                startActivity(intent);
            }
        });
        mAdapter.add(userData);

        GroupTextData textData = new GroupTextData();
        textData.setText("账号与安全");
        textData.setShowBtn(true);
        textData.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SetHomeActivity.this, SetAccountSafeActivity.class);
                startActivity(intent);
            }
        });
        mAdapter.add(textData);

        GroupTextData textData1 = new GroupTextData();
        textData1.setText("消息通知");
        textData1.setShowBtn(true);
        textData1.setHideRadiusSide(IUniLayout.HIDE_RADIUS_SIDE_BOTTOM);
        textData1.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SetHomeActivity.this, SetMsgNoticeActivity.class);
                startActivity(intent);
            }
        });
        mAdapter.add(textData1);

        GroupTextData textData2 = new GroupTextData();
        textData2.setText("勿扰模式");
        textData2.setShowBtn(true);
        textData2.setHideRadiusSide(IUniLayout.HIDE_RADIUS_SIDE_TOP);
        textData2.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SetHomeActivity.this, SetDisturbActivity.class);
                startActivity(intent);
            }
        });
        mAdapter.add(textData2);

        GroupTextData textData3 = new GroupTextData();
        textData3.setText("小黑屋");
        textData3.setShowBtn(true);
        textData3.setHideRadiusSide(IUniLayout.HIDE_RADIUS_SIDE_BOTTOM);
        textData3.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SetHomeActivity.this, SetShieldActivity.class);
                startActivity(intent);
            }
        });
        mAdapter.add(textData3);

        GroupTextData textData4 = new GroupTextData();
        textData4.setText("隐私");
        textData4.setShowBtn(true);
        textData4.setHideRadiusSide(IUniLayout.HIDE_RADIUS_SIDE_TOP);
        textData4.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SetHomeActivity.this, SetPrivacyActivity.class);
                startActivity(intent);
            }
        });
        mAdapter.add(textData4);

        GroupTextData textData5 = new GroupTextData();
        textData5.setText("通用");
        textData5.setShowBtn(true);
        textData5.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SetHomeActivity.this, SetCurrencyActivity.class);
                startActivity(intent);
            }
        });
        mAdapter.add(textData5);

        GroupTextData textData6 = new GroupTextData();
        textData6.setText("清理缓存");
        textData6.setSubText("20.8M");
        textData6.setShowBtn(true);
        textData6.setHideRadiusSide(IUniLayout.HIDE_RADIUS_SIDE_BOTTOM);
        textData6.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mAdapter.add(textData6);

        GroupTextData textData7 = new GroupTextData();
        textData7.setText("关于友你");
        textData7.setSubText("V1.0.0");
        textData7.setShowBtn(true);
        textData7.setHideRadiusSide(IUniLayout.HIDE_RADIUS_SIDE_TOP);
        textData7.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SetHomeActivity.this, SetAboutActivity.class);
                startActivity(intent);
            }
        });
        mAdapter.add(textData7);

        GroupTextData textData8 = new GroupTextData();
        textData8.setText("退出");
        textData8.setShowBtn(true);
        textData8.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mAdapter.add(textData8);
    }

    public void initTopBar() {
        mTopBar.setTitle("设置");
        mTopBar.setLeftButtonImage(R.drawable.ic_back_theme);
        mTopBar.setOnLeftButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
