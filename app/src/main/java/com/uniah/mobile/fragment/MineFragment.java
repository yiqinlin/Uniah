package com.uniah.mobile.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uniah.mobile.R;
import com.uniah.mobile.adapter.MineAdapter;
import com.uniah.mobile.base.BaseData;
import com.uniah.mobile.base.BaseFragment;
import com.uniah.mobile.bean.CardTitleData;
import com.uniah.mobile.bean.MineHeadData;
import com.uniah.mobile.bean.MineTeamData;
import com.uniah.mobile.bean.MineToolData;
import com.uniah.mobile.bean.MineUniData;
import com.uniah.mobile.layout.UniPullLayout;
import com.uniah.mobile.util.UniStatusBarHelper;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends BaseFragment {
    RecyclerView mRecyclerView;
    UniPullLayout mPullRefreshLayout;
    MineAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        UniStatusBarHelper.translucent(getActivity());

        initView(view);
        initData();
        return view;
    }

    private void initView(View view) {
        mRecyclerView = view.findViewById(R.id.pull_list_recycleView);
        mPullRefreshLayout = view.findViewById(R.id.pull_list_refresh_layout);

        mAdapter = new MineAdapter(getActivity(), new ArrayList<BaseData>());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);

    }

    private void initData() {

        MineHeadData data = new MineHeadData();

        data.setUserHead("http://39.108.118.215/imgs/eb84d6962419be7594bd804bca2cbaa0.jpg");
        data.setUserNick("小笨狗");
        data.setUserSlogan("万物皆可破！");

        data.setLeftCount(12);
        data.setMidCount(111);
        data.setRightCount(20);

        data.setLeftTitle("动态");
        data.setMidTitle("我认识");
        data.setRightTitle("认识我");

        mAdapter.add(data);

        MineUniData data1 = new MineUniData();

        data1.setUniLogo("http://39.108.118.215/imgs/eb84d6962419be7594bd804bca2cbaa0.jpg");
        data1.setUniName("成都东软学院");
        data1.setUniSlogan("精勤博学，学以致用");
        data1.setFlag("未认证");

        mAdapter.add(data1);

        MineToolData data2 = new MineToolData();

        data2.setToolTitle("我的工具");

        data2.setFirstImageResource(R.drawable.ic_home_black_24dp);
        data2.setSecondImageResource(R.drawable.ic_home_black_24dp);
        data2.setThirdImageResource(R.drawable.ic_home_black_24dp);
        data2.setFourthImageResource(R.drawable.ic_home_black_24dp);

        data2.setFirstName("我的赞");
        data2.setSecondName("我的评论");
        data2.setThirdName("收藏");
        data2.setFourthName("表情");

        mAdapter.add(data2);


        CardTitleData data3 = new CardTitleData();
        data3.setTitle("我的卡片");
        mAdapter.add(data3);

        MineTeamData data4 = new MineTeamData();

        data4.setToolTitle("我的团队");
        data4.setShowMoreBtn(true);

        data4.setFirstLogo("http://39.108.118.215/imgs/eb84d6962419be7594bd804bca2cbaa0.jpg");
        data4.setSecondLogo("http://39.108.118.215/imgs/eb84d6962419be7594bd804bca2cbaa0.jpg");
        data4.setThirdLogo("http://39.108.118.215/imgs/eb84d6962419be7594bd804bca2cbaa0.jpg");
        data4.setFourthLogo("http://39.108.118.215/imgs/eb84d6962419be7594bd804bca2cbaa0.jpg");

        data4.setFirstName("移动MM");
        data4.setSecondName("ACM实验室");
        data4.setThirdName("社团联合会");
        data4.setFourthName("计科五班");

        mAdapter.add(data4);
    }

}
