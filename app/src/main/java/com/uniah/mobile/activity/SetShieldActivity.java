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
import com.uniah.mobile.bean.GroupTextData;
import com.uniah.mobile.bean.GroupUserData;
import com.uniah.mobile.layout.IUniLayout;
import com.uniah.mobile.layout.UniPullLayout;
import com.uniah.mobile.util.UniStatusBarHelper;
import com.uniah.mobile.view.UniCommonTopBar;

import java.util.ArrayList;

public class SetShieldActivity extends AppCompatActivity {
    UniCommonTopBar mTopBar;
    RecyclerView mRecyclerView;
    UniPullLayout mPullRefreshLayout;
    GroupItemAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_shield);
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
        textData.setText("添加用户");
        textData.setShowBtn(true);
        textData.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mAdapter.add(textData);

        GroupHintData hintData = new GroupHintData();
        hintData.setHint("已屏蔽用户");
        mAdapter.add(hintData);

        GroupUserData userData = new GroupUserData();
        userData.setNick("小笨狗");
        userData.setHead("http://39.108.118.215/imgs/eb84d6962419be7594bd804bca2cbaa0.jpg");
        userData.setSlogan("动态 互动 结交");
        userData.setShowArrow(false);
        userData.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mAdapter.add(userData);


    }

    public void initTopBar() {
        mTopBar.setTitle("小黑屋");
        mTopBar.setLeftButtonImage(R.drawable.ic_back_theme);
        mTopBar.setOnLeftButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
