package com.uniah.mobile.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.uniah.mobile.R;
import com.uniah.mobile.util.UniStatusBarHelper;
import com.uniah.mobile.view.UniCommonTopBar;

public class FeedPushActivity extends AppCompatActivity {

    UniCommonTopBar mTopBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_push);
        UniStatusBarHelper.translucent(this);

        initView();
        initTopBar();
        initData();
    }

    private void initView() {
        mTopBar = findViewById(R.id.top_bar);

    }

    private void initData() {

    }

    public void initTopBar() {
        mTopBar.setTitle("发趣趣");
        mTopBar.setLeftButtonImage(R.drawable.ic_back_theme);
        mTopBar.setRightButtonImage(R.drawable.ic_send_theme);
        mTopBar.setOnLeftButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
