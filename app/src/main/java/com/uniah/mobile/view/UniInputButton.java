package com.uniah.mobile.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uniah.mobile.R;
import com.uniah.mobile.layout.UniFrameLayout;
import com.uniah.mobile.util.UniColorHelper;
import com.uniah.mobile.util.UniDisplayHelper;

public class UniInputButton extends UniFrameLayout {


    private Context mContext;

    private LinearLayout mInputBar;

    private TextView mBtnText;
    private LinearLayout mBtnLayout;


    private float mShadowAlpha = 0.5f;
    private int mShadowElevationDp = 12;


    public UniInputButton(@NonNull Context context) {
        this(context, null);
    }

    public UniInputButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView();
    }

    private void initView() {
        mInputBar = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.input_item, null);

        mBtnText = mInputBar.findViewById(R.id.input_text);
        mBtnLayout = mInputBar.findViewById(R.id.input_layout);

        addView(mInputBar);

        setShadow();
    }

    public void setShadow() {
        this.setShadow(mShadowElevationDp, mShadowAlpha);
    }

    public void setShadow(int elevation, float alpha) {
        setRadiusAndShadow(0, UniDisplayHelper.dp2px(getContext(), elevation), alpha);
    }

    public void clearShadow() {
        setRadiusAndShadow(0, 0, 0);
    }

    public void setButtonClickListener(View.OnClickListener clickListener) {
        mBtnLayout.setOnClickListener(clickListener);
    }

}
