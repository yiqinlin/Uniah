package com.uniah.mobile.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.uniah.mobile.R;
import com.uniah.mobile.adapter.GroupItemAdapter;
import com.uniah.mobile.adapter.UserHomeAdapter;
import com.uniah.mobile.base.BaseData;
import com.uniah.mobile.bean.DataHomeData;
import com.uniah.mobile.bean.GroupHeadData;
import com.uniah.mobile.bean.GroupTextData;
import com.uniah.mobile.bean.UserInfoData;
import com.uniah.mobile.dialog.UniListDialog;
import com.uniah.mobile.layout.IUniLayout;
import com.uniah.mobile.layout.UniPullLayout;
import com.uniah.mobile.util.UniStatusBarHelper;
import com.uniah.mobile.view.UniCommonTopBar;
import com.uniah.mobile.view.UniWebView;

import java.util.ArrayList;

public class UserHomeActivity extends AppCompatActivity {
    UniCommonTopBar mTopBar;
    RecyclerView mRecyclerView;
    UniPullLayout mPullRefreshLayout;
    UserHomeAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        UniStatusBarHelper.translucent(this);

        initView();
        initTopBar();
        initData();
    }

    private void initView() {
        mTopBar = findViewById(R.id.top_bar);
        mRecyclerView = findViewById(R.id.pull_list_recycleView);
        mPullRefreshLayout = findViewById(R.id.pull_list_refresh_layout);

        mAdapter = new UserHomeAdapter(this, new ArrayList<BaseData>());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);

    }

    private void initData() {

        DataHomeData dataHomeData = new DataHomeData();

        dataHomeData.setAccount(10001);
        dataHomeData.setWall("http://39.108.118.215/imgs/e8381b560354c331eb03a3620e0fc483.jpg");
        dataHomeData.setHead("http://39.108.118.215/imgs/eb84d6962419be7594bd804bca2cbaa0.jpg");

        dataHomeData.setFlagLeft("22·射手座");
        dataHomeData.setFlagRight("成都东软学院");

        dataHomeData.setName("安吉拉");
        dataHomeData.setSlogan("万物皆可破！");

        dataHomeData.setInfoFirst("获赞 20");
        dataHomeData.setInfoSecond("我认识 20");
        dataHomeData.setInfoThird("认识我 20");

        mAdapter.add(dataHomeData);
    }

    public void initTopBar() {

        mTopBar.setLeftButtonImage(R.drawable.ic_back_theme);
        mTopBar.setShadow(0, 0);
        mTopBar.setOnLeftButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
