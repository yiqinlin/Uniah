package com.uniah.mobile.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.uniah.mobile.R;
import com.uniah.mobile.adapter.ChatAdapter;
import com.uniah.mobile.adapter.GroupItemAdapter;
import com.uniah.mobile.base.BaseData;
import com.uniah.mobile.bean.ChatFriendData;
import com.uniah.mobile.bean.ChatUserData;
import com.uniah.mobile.bean.GroupCardTitleData;
import com.uniah.mobile.bean.GroupHistoryData;
import com.uniah.mobile.bean.GroupSearchUserData;
import com.uniah.mobile.holder.GroupHistoryViewHolder;
import com.uniah.mobile.layout.IUniLayout;
import com.uniah.mobile.layout.UniPullLayout;
import com.uniah.mobile.util.UniStatusBarHelper;
import com.uniah.mobile.view.UniCommonTopBar;
import com.uniah.mobile.view.UniSearchTopBar;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    UniSearchTopBar mTopBar;
    RecyclerView mRecyclerView;
    UniPullLayout mPullRefreshLayout;
    GroupItemAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
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

        GroupCardTitleData cardTitleData = new GroupCardTitleData();
        cardTitleData.setText("历史记录");
        cardTitleData.setHideRadiusSide(IUniLayout.HIDE_RADIUS_SIDE_BOTTOM);

        mAdapter.add(cardTitleData);

        GroupHistoryData historyData = new GroupHistoryData();
        historyData.setName("小笨狗");
        historyData.setHead("http://39.108.118.215/imgs/eb84d6962419be7594bd804bca2cbaa0.jpg");
        historyData.setFlag("(啊哈哈)");
        historyData.setHideRadiusSide(IUniLayout.HIDE_RADIUS_SIDE_MID);
        historyData.setLineNone(true);

        mAdapter.add(historyData);


        GroupHistoryData historyData1 = new GroupHistoryData();
        historyData1.setName("小笨狗");
        historyData1.setHead("http://39.108.118.215/imgs/eb84d6962419be7594bd804bca2cbaa0.jpg");
        historyData1.setFlag("(啊哈哈)");
        historyData1.setHideRadiusSide(IUniLayout.HIDE_RADIUS_SIDE_MID);
        historyData1.setLineNone(true);

        mAdapter.add(historyData1);


        GroupHistoryData historyData2 = new GroupHistoryData();
        historyData2.setName("小笨狗");
        historyData2.setHead("http://39.108.118.215/imgs/eb84d6962419be7594bd804bca2cbaa0.jpg");
        historyData2.setFlag("(啊哈哈)");
        historyData2.setHideRadiusSide(IUniLayout.HIDE_RADIUS_SIDE_TOP);
        historyData2.setLineNone(true);

        mAdapter.add(historyData2);


        GroupCardTitleData cardTitleData1 = new GroupCardTitleData();
        cardTitleData1.setText("联系人");
        cardTitleData1.setSubText("更多");
        cardTitleData1.setHideRadiusSide(IUniLayout.HIDE_RADIUS_SIDE_BOTTOM);
        cardTitleData1.setShowBtn(true);

        mAdapter.add(cardTitleData1);


        GroupSearchUserData searchUserData = new GroupSearchUserData();
        searchUserData.setNick("小笨狗");
        searchUserData.setHead("http://39.108.118.215/imgs/eb84d6962419be7594bd804bca2cbaa0.jpg");
        searchUserData.setFlag("(啊哈哈)");
        searchUserData.setHideRadiusSide(IUniLayout.HIDE_RADIUS_SIDE_MID);
        searchUserData.setSlogan("万物皆可破！");
        searchUserData.setLineNone(true);

        mAdapter.add(searchUserData);

        GroupSearchUserData searchUserData1 = new GroupSearchUserData();
        searchUserData1.setNick("小笨狗");
        searchUserData1.setHead("http://39.108.118.215/imgs/eb84d6962419be7594bd804bca2cbaa0.jpg");
        searchUserData1.setFlag("(啊哈哈)");
        searchUserData1.setHideRadiusSide(IUniLayout.HIDE_RADIUS_SIDE_MID);
        searchUserData1.setSlogan("万物皆可破！");
        searchUserData1.setLineNone(true);

        mAdapter.add(searchUserData1);

        GroupSearchUserData searchUserData2 = new GroupSearchUserData();
        searchUserData2.setNick("小笨狗");
        searchUserData2.setHead("http://39.108.118.215/imgs/eb84d6962419be7594bd804bca2cbaa0.jpg");
        searchUserData2.setFlag("(啊哈哈)");
        searchUserData2.setHideRadiusSide(IUniLayout.HIDE_RADIUS_SIDE_TOP);
        searchUserData2.setSlogan("万物皆可破！");
        searchUserData2.setBtnText("结交");
        searchUserData2.setOnBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        searchUserData2.setLineNone(true);

        mAdapter.add(searchUserData2);
    }

    public void initTopBar() {
        mTopBar.setHint("搜索");
        mTopBar.setLeftButtonImage(R.drawable.ic_back_theme);
        mTopBar.setOnLeftButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mTopBar.setRightButtonText("搜索");
        mTopBar.setOnRightButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
