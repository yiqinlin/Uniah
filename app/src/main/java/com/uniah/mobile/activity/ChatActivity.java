package com.uniah.mobile.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.uniah.mobile.R;
import com.uniah.mobile.adapter.ChatAdapter;
import com.uniah.mobile.adapter.CommentAdapter;
import com.uniah.mobile.base.BaseData;
import com.uniah.mobile.bean.CardTitleData;
import com.uniah.mobile.bean.ChatFriendData;
import com.uniah.mobile.bean.ChatUserData;
import com.uniah.mobile.bean.CommentData;
import com.uniah.mobile.bean.DetailHeadData;
import com.uniah.mobile.layout.UniPullLayout;
import com.uniah.mobile.util.SoftHideKeyBoardUtil;
import com.uniah.mobile.util.UniStatusBarHelper;
import com.uniah.mobile.view.UniCommonTopBar;

import java.util.ArrayList;
import java.util.HashMap;

public class ChatActivity extends AppCompatActivity {

    UniCommonTopBar mTopBar;
    RecyclerView mRecyclerView;
    UniPullLayout mPullRefreshLayout;
    ChatAdapter mAdapter;

    int mAcceptId;
    String mAcceptNick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        UniStatusBarHelper.translucent(this);
        SoftHideKeyBoardUtil.assistActivity(this);

        Intent intent = getIntent();
        mAcceptNick = intent.getStringExtra("acceptNick");
        mAcceptId = intent.getIntExtra("acceptId", -1);

        initView();
        initTopBar();
        initData();
    }

    private void initView() {
        mTopBar = findViewById(R.id.top_bar);
        mRecyclerView = findViewById(R.id.pull_list_recycleView);
        mPullRefreshLayout = findViewById(R.id.pull_list_refresh_layout);

        mAdapter = new ChatAdapter(this, new ArrayList<BaseData>());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initData() {

        ChatUserData userData = new ChatUserData();
        userData.setMsgType(0);
        userData.setMsgContent("看起来效果不错，早点休息哟，晚安～");
        userData.setUserHead("http://39.108.118.215/imgs/eb84d6962419be7594bd804bca2cbaa0.jpg");

        mAdapter.add(userData);

        ChatUserData userData1 = new ChatUserData();
        userData1.setMsgType(1);
        userData1.setMsgContent("http://39.108.118.215/imgs/e8381b560354c331eb03a3620e0fc483.jpg");
        userData1.setUserHead("http://39.108.118.215/imgs/eb84d6962419be7594bd804bca2cbaa0.jpg");
        mAdapter.add(userData1);

        ChatFriendData friendData = new ChatFriendData();
        friendData.setMsgType(0);
        friendData.setMsgContent("给你发一个用于测试长文本的消息，想看看长消息的气泡是什么效果");
        friendData.setUserHead("http://39.108.118.215/imgs/eb84d6962419be7594bd804bca2cbaa0.jpg");
        mAdapter.add(friendData);

        ChatFriendData friendData1 = new ChatFriendData();
        friendData1.setMsgType(1);
        friendData1.setMsgContent("http://39.108.118.215/imgs/e8381b560354c331eb03a3620e0fc483.jpg");
        friendData1.setUserHead("http://39.108.118.215/imgs/eb84d6962419be7594bd804bca2cbaa0.jpg");
        mAdapter.add(friendData1);
    }

    public void initTopBar() {
        mTopBar.setTitle(mAcceptNick);
        mTopBar.setLeftButtonImage(R.drawable.ic_back_theme);
        mTopBar.setOnLeftButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mTopBar.setRightButtonImage(R.drawable.ic_set_theme);
        mTopBar.setOnRightButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChatActivity.this, ChatSetActivity.class);
                intent.putExtra("acceptId", mAcceptId);
                intent.putExtra("acceptNick", mAcceptNick);
                startActivity(intent);
            }
        });
    }
}
