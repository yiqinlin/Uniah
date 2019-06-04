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
import com.uniah.mobile.bean.GroupSwitchData;
import com.uniah.mobile.bean.GroupTextData;
import com.uniah.mobile.bean.GroupUserData;
import com.uniah.mobile.layout.IUniLayout;
import com.uniah.mobile.layout.UniPullLayout;
import com.uniah.mobile.util.UniStatusBarHelper;
import com.uniah.mobile.view.UniCommonTopBar;

import java.util.ArrayList;

public class ChatSetActivity extends AppCompatActivity {
    UniCommonTopBar mTopBar;
    RecyclerView mRecyclerView;
    UniPullLayout mPullRefreshLayout;
    GroupItemAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_set);
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
        userData.setHideRadiusSide(IUniLayout.HIDE_RADIUS_SIDE_BOTTOM);
        userData.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChatSetActivity.this, SetUserDataActivity.class);
                startActivity(intent);
            }
        });
        mAdapter.add(userData);

        GroupTextData textData = new GroupTextData();
        textData.setText("发起群聊");
        textData.setShowBtn(true);
        textData.setHideRadiusSide(IUniLayout.HIDE_RADIUS_SIDE_TOP);
        textData.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChatSetActivity.this, SetAccountSafeActivity.class);
                startActivity(intent);
            }
        });
        mAdapter.add(textData);


        GroupSwitchData switchData = new GroupSwitchData();
        switchData.setText("置顶聊天");
        switchData.setHideRadiusSide(IUniLayout.HIDE_RADIUS_SIDE_BOTTOM);
        switchData.setChecked(true);
        mAdapter.add(switchData);

        GroupSwitchData switchData1 = new GroupSwitchData();
        switchData1.setText("关小黑屋");
        switchData1.setHideRadiusSide(IUniLayout.HIDE_RADIUS_SIDE_TOP);
        switchData1.setChecked(true);
        mAdapter.add(switchData1);




        GroupTextData textData1 = new GroupTextData();
        textData1.setText("查找聊天记录");
        textData1.setShowBtn(true);
        textData1.setHideRadiusSide(IUniLayout.HIDE_RADIUS_SIDE_BOTTOM);
        textData1.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChatSetActivity.this, SetMsgNoticeActivity.class);
                startActivity(intent);
            }
        });
        mAdapter.add(textData1);

        GroupTextData textData2 = new GroupTextData();
        textData2.setText("清空聊天记录");
        textData2.setShowBtn(true);
        textData2.setHideRadiusSide(IUniLayout.HIDE_RADIUS_SIDE_TOP);
        textData2.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChatSetActivity.this, SetDisturbActivity.class);
                startActivity(intent);
            }
        });
        mAdapter.add(textData2);



        GroupTextData textData3 = new GroupTextData();
        textData3.setText("举报");
        textData3.setShowBtn(true);
        textData3.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChatSetActivity.this, SetShieldActivity.class);
                startActivity(intent);
            }
        });
        mAdapter.add(textData3);
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
