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
import com.uniah.mobile.base.BaseData;
import com.uniah.mobile.bean.GroupHeadData;
import com.uniah.mobile.bean.GroupTextData;
import com.uniah.mobile.dialog.UniListDialog;
import com.uniah.mobile.layout.IUniLayout;
import com.uniah.mobile.layout.UniPullLayout;
import com.uniah.mobile.util.UniStatusBarHelper;
import com.uniah.mobile.view.UniCommonTopBar;

import java.util.ArrayList;

public class SetUserDataActivity extends AppCompatActivity {
    UniCommonTopBar mTopBar;
    RecyclerView mRecyclerView;
    UniPullLayout mPullRefreshLayout;
    GroupItemAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_user_data);
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
        GroupHeadData headData = new GroupHeadData();
        headData.setHead("http://39.108.118.215/imgs/eb84d6962419be7594bd804bca2cbaa0.jpg");
        headData.setHint("点击更换头像");
        headData.setHideRadiusSide(IUniLayout.HIDE_RADIUS_SIDE_BOTTOM);
        headData.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SetUserDataActivity.this, SetUserDataActivity.class);
                startActivity(intent);
            }
        });
        mAdapter.add(headData);

        GroupTextData textData = new GroupTextData();
        textData.setText("友你号");
        textData.setSubText("未设置");
        textData.setShowBtn(true);
        textData.setHideRadiusSide(IUniLayout.HIDE_RADIUS_SIDE_MID);
        textData.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SetUserDataActivity.this, SetUserDataActivity.class);
                startActivity(intent);
            }
        });
        mAdapter.add(textData);

        GroupTextData textData1 = new GroupTextData();
        textData1.setText("昵称");
        textData1.setSubText("小笨狗");
        textData1.setShowBtn(true);
        textData1.setHideRadiusSide(IUniLayout.HIDE_RADIUS_SIDE_MID);
        textData1.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SetUserDataActivity.this, SetUserDataActivity.class);
                startActivity(intent);
            }
        });
        mAdapter.add(textData1);

        GroupTextData textData2 = new GroupTextData();
        textData2.setText("签名");
        textData2.setSubText("万物皆可破！");
        textData2.setShowBtn(true);
        textData2.setHideRadiusSide(IUniLayout.HIDE_RADIUS_SIDE_TOP);
        textData2.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SetUserDataActivity.this, SetUserDataActivity.class);
                startActivity(intent);
            }
        });
        mAdapter.add(textData2);


        final GroupTextData textData3 = new GroupTextData();
        textData3.setText("性别");
        textData3.setSubText("男");
        textData3.setShowBtn(true);
        textData3.setHideRadiusSide(IUniLayout.HIDE_RADIUS_SIDE_BOTTOM);
        textData3.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UniListDialog dialog = new UniListDialog.Builder(SetUserDataActivity.this)
                        .setChoiceItems(new String[]{"女", "男"}, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (!textData3.getSubText().equals(which == 1 ? "男" : "女")) {
                                    textData3.setSubText(which == 1 ? "男" : "女");
                                    mAdapter.notifyItemChangedWithObject(textData3);
                                }
                            }
                        }).setCancelable(true).show();
            }
        });
        mAdapter.add(textData3);

        GroupTextData textData4 = new GroupTextData();
        textData4.setText("生日");
        textData4.setSubText("1996-12-20");
        textData4.setShowBtn(true);
        textData4.setHideRadiusSide(IUniLayout.HIDE_RADIUS_SIDE_TOP);
        mAdapter.add(textData4);


        GroupTextData textData5 = new GroupTextData();
        textData5.setText("学校");
        textData5.setSubText("成都东软学院");
        textData5.setShowBtn(true);
        textData5.setHideRadiusSide(IUniLayout.HIDE_RADIUS_SIDE_BOTTOM);
        mAdapter.add(textData5);

        GroupTextData textData6 = new GroupTextData();
        textData6.setText("地区");
        textData6.setSubText("中国·四川·成都");
        textData6.setShowBtn(true);
        textData6.setHideRadiusSide(IUniLayout.HIDE_RADIUS_SIDE_TOP);
        mAdapter.add(textData6);
    }

    public void initTopBar() {
        mTopBar.setTitle("我的资料");
        mTopBar.setLeftButtonImage(R.drawable.ic_back_theme);
        mTopBar.setOnLeftButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mTopBar.setRightButtonImage(R.drawable.ic_qrcode_theme);
        mTopBar.setOnRightButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
