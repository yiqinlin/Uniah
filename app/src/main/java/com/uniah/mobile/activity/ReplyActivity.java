package com.uniah.mobile.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.uniah.mobile.R;
import com.uniah.mobile.adapter.CommentAdapter;
import com.uniah.mobile.base.BaseData;
import com.uniah.mobile.bean.CardTitleData;
import com.uniah.mobile.bean.CommentData;
import com.uniah.mobile.bean.DetailHeadData;
import com.uniah.mobile.layout.UniPullLayout;
import com.uniah.mobile.util.UniStatusBarHelper;
import com.uniah.mobile.view.UniCommonTopBar;

import java.util.ArrayList;
import java.util.HashMap;

public class ReplyActivity extends AppCompatActivity {

    UniCommonTopBar mTopBar;
    RecyclerView mRecyclerView;
    UniPullLayout mPullRefreshLayout;
    CommentAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply);
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

        mAdapter = new CommentAdapter(this, new ArrayList<BaseData>());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);

    }

    private void initData() {

        final DetailHeadData data = new DetailHeadData();

        data.setHead("http://39.108.118.215/imgs/eb84d6962419be7594bd804bca2cbaa0.jpg");
        data.setNick("小笨狗");
        data.setTime("2019-05-20 21:30:00");
        data.setContent("语雀是一款优雅高效的在线文档编辑与协同工具，让每个企业轻松拥有文档中心阿里巴巴集团内部使用多年，众多中小企业首选。");
        HashMap<Integer, String> map = new HashMap<>();
        data.setFiles(map);

        data.setLikeCount(23);

        mAdapter.add(data);

        CardTitleData data1 = new CardTitleData();
        data1.setTitle("全部回复");
        mAdapter.add(data1);

        CommentData data2 = new CommentData();

        data2.setUserHead("http://39.108.118.215/imgs/eb84d6962419be7594bd804bca2cbaa0.jpg");
        data2.setUserNick("小笨狗");
        data2.setCommentTime("2019-05-20 21:30:00");
        data2.setCommentContent("Ant Design是一个服务于企业级产品的设计体系，基于『确定』和『自然』的设计价值观和模块化的解决方案，让设计者专注于更好的用户体验。");

        data2.setCommentType(2);

        data2.setLikeCount(23);

        mAdapter.add(data2);
    }

    public void initTopBar() {
        mTopBar.setTitle("详情");
        mTopBar.setShadow(0, 0);
        mTopBar.setLeftButtonImage(R.drawable.ic_home_black_24dp);
        mTopBar.setOnLeftButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
