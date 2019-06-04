package com.uniah.mobile.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;

import com.qmuiteam.qmui.widget.webview.QMUIWebViewClient;
import com.uniah.mobile.R;
import com.uniah.mobile.util.UniStatusBarHelper;
import com.uniah.mobile.view.UniCommonTopBar;
import com.uniah.mobile.view.UniWebView;

public class WebViewActivity extends AppCompatActivity {
    UniCommonTopBar mTopBar;
    UniWebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        UniStatusBarHelper.translucent(this);

        initView();
        initTopBar();
        initData();
    }

    private void initView() {
        mTopBar = findViewById(R.id.top_bar);
        mWebView = findViewById(R.id.uni_webView);
    }

    private void initData() {
        String url = getIntent().getStringExtra("url");
        mWebView.setWebViewClient(new QMUIWebViewClient(false, true) {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                String title = view.getTitle();
                if (!TextUtils.isEmpty(title)) {
                    mTopBar.setTitle(title);
                }
            }

        });
        mWebView.loadUrl(url);
    }

    public void initTopBar() {
        mTopBar.setLeftButtonImage(R.drawable.ic_back_theme);
        mTopBar.setOnLeftButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
