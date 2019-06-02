package com.uniah.mobile.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.uniah.mobile.R;
import com.uniah.mobile.adapter.ContactAdapter;
import com.uniah.mobile.base.BaseData;
import com.uniah.mobile.bean.HintData;
import com.uniah.mobile.bean.MsgListHeadData;
import com.uniah.mobile.bean.SearchData;
import com.uniah.mobile.bean.UserInfoData;
import com.uniah.mobile.layout.UniPullLayout;
import com.uniah.mobile.util.UniStatusBarHelper;
import com.uniah.mobile.view.UniCommonTopBar;

import java.util.ArrayList;

public class ContactActivity extends AppCompatActivity {
    UniCommonTopBar mTopBar;
    RecyclerView mRecyclerView;
    UniPullLayout mPullRefreshLayout;
    ContactAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        UniStatusBarHelper.translucent(this);

        initView();
        initTopBar();
        initData();
    }

    private void initView() {
        mTopBar = findViewById(R.id.top_bar);
        mRecyclerView = findViewById(R.id.pull_list_recycleView);
        mRecyclerView.setBackgroundResource(android.R.color.white);
        mPullRefreshLayout = findViewById(R.id.pull_list_refresh_layout);
        mPullRefreshLayout.setBackgroundColor(Color.WHITE);

        mAdapter = new ContactAdapter(this, new ArrayList<BaseData>());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);

    }

    private void initData() {
        MsgListHeadData data = new MsgListHeadData();

        data.setLeftImageResource(R.drawable.ic_home_black_24dp);
        data.setMidImageResource(R.drawable.ic_home_black_24dp);
        data.setRightImageResource(R.drawable.ic_home_black_24dp);

        data.setLeftName("新朋友");
        data.setMidName("群聊");
        data.setRightName("团队");

        mAdapter.add(new SearchData());
        mAdapter.add(data);

        HintData data1 = new HintData();
        data1.setHint("A");
        mAdapter.add(data1);

        UserInfoData data2 = new UserInfoData();
        data2.setHead("http://39.108.118.215/imgs/eb84d6962419be7594bd804bca2cbaa0.jpg");
        data2.setName("安吉拉");
        mAdapter.add(data2);

        HintData data3 = new HintData();
        data3.setHint("C");
        mAdapter.add(data3);

        UserInfoData data4 = new UserInfoData();
        data4.setHead("http://39.108.118.215/imgs/eb84d6962419be7594bd804bca2cbaa0.jpg");
        data4.setName("程明");
        mAdapter.add(data4);

        HintData data5 = new HintData();
        data5.setHint("F");
        mAdapter.add(data5);

        UserInfoData data6 = new UserInfoData();
        data6.setHead("http://39.108.118.215/imgs/eb84d6962419be7594bd804bca2cbaa0.jpg");
        data6.setName("傅小小");
        mAdapter.add(data6);

        UserInfoData data7 = new UserInfoData();
        data7.setHead("http://39.108.118.215/imgs/eb84d6962419be7594bd804bca2cbaa0.jpg");
        data7.setName("傅静");
        data7.setFlag("内部");
        mAdapter.add(data7);

    }

    public void initTopBar() {
        mTopBar.setTitle("通讯录");
        mTopBar.setLeftButtonImage(R.drawable.ic_home_black_24dp);
        mTopBar.setOnLeftButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
