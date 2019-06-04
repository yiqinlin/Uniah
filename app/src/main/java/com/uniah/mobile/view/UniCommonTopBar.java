package com.uniah.mobile.view;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uniah.mobile.R;
import com.uniah.mobile.layout.UniFrameLayout;
import com.uniah.mobile.util.UniDisplayHelper;
import com.uniah.mobile.util.UniNotchHelper;
import com.uniah.mobile.util.UniStatusBarHelper;

public class UniCommonTopBar extends UniFrameLayout {

    private Context mContext;
    private Activity mActivity;
    private LinearLayout mTopBar;

    private TextView mTitleView;

    private ImageView mBtnLeftSrc;
    private UniFrameLayout mBtnLeftLayout;

    private ImageView mBtnSubLeftSrc;
    private UniFrameLayout mBtnSubLeftLayout;

    private ImageView mBtnRightSrc;
    private UniFrameLayout mBtnRightLayout;

    private ImageView mBtnSubRightSrc;
    private UniFrameLayout mBtnSubRightLayout;

    private float mShadowAlpha = 0.25f;
    private int mShadowElevationDp = 6;

    private UniSegmentTopBar.OnTabClickListener mTabClickListener;
    private GestureDetector mLeftGestureDetector;
    private GestureDetector mMidGestureDetector;

    public UniCommonTopBar(@NonNull Context context) {
        this(context, null);
    }

    public UniCommonTopBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        if (context instanceof Activity) {
            mActivity = (Activity) context;
        }
        initView();
    }

    private void initView() {
        mTopBar = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.topbar_common, null);

        mTitleView = mTopBar.findViewById(R.id.top_bar_title);

        mBtnLeftSrc = mTopBar.findViewById(R.id.top_bar_btn_left_img);
        mBtnLeftLayout = mTopBar.findViewById(R.id.top_bar_btn_left_layout);
        mBtnLeftLayout.setChangeAlphaWhenPress(true);

        mBtnSubLeftSrc = mTopBar.findViewById(R.id.top_bar_btn_left_sub_img);
        mBtnSubLeftLayout = mTopBar.findViewById(R.id.top_bar_btn_left_sub_layout);
        mBtnSubLeftLayout.setChangeAlphaWhenPress(true);

        mBtnRightSrc = mTopBar.findViewById(R.id.top_bar_btn_right_img);
        mBtnRightLayout = mTopBar.findViewById(R.id.top_bar_btn_right_layout);
        mBtnRightLayout.setChangeAlphaWhenPress(true);

        mBtnSubRightSrc = mTopBar.findViewById(R.id.top_bar_btn_right_sub_img);
        mBtnSubRightLayout = mTopBar.findViewById(R.id.top_bar_btn_right_sub_layout);
        mBtnSubRightLayout.setChangeAlphaWhenPress(true);

        addView(mTopBar);
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


    private void setTranslucent() {
        if (mActivity != null && UniStatusBarHelper.isTranslucent(mActivity)) {
            int statusBarHeight = UniNotchHelper.getSafeInsetTop(mActivity);
            if (statusBarHeight == 0) {
                statusBarHeight = UniStatusBarHelper.getStatusbarHeight(mActivity);
            }
            mTopBar.setPadding(0, statusBarHeight, 0, 0);
        }
    }

    public void setTitle(String title) {
        if (title != null) {
            mTitleView.setText(title);
        }
    }

    public void setLeftButtonImage(int drawableResId) {
        mBtnLeftSrc.setImageResource(drawableResId);
    }

    public void setOnLeftButtonClickListener(View.OnClickListener clickListener) {
        mBtnLeftLayout.setOnClickListener(clickListener);
    }

    public void setSubLeftButtonImage(int drawableResId) {
        mBtnSubLeftSrc.setImageResource(drawableResId);
        mBtnSubLeftLayout.setVisibility(VISIBLE);
        mBtnSubRightLayout.setVisibility(VISIBLE);
    }

    public void setOnSubLeftButtonClickListener(View.OnClickListener clickListener) {
        mBtnSubLeftLayout.setOnClickListener(clickListener);
    }


    public void setRightButtonImage(int drawableResId) {
        mBtnRightSrc.setImageResource(drawableResId);
    }

    public void setOnRightButtonClickListener(View.OnClickListener clickListener) {
        mBtnRightLayout.setOnClickListener(clickListener);
    }

    public void setSubRightButtonImage(int drawableResId) {
        mBtnSubRightSrc.setImageResource(drawableResId);
        mBtnSubLeftLayout.setVisibility(VISIBLE);
        mBtnSubRightLayout.setVisibility(VISIBLE);
    }

    public void setOnSubRightButtonClickListener(View.OnClickListener clickListener) {
        mBtnSubRightLayout.setOnClickListener(clickListener);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            setTranslucent();
        }
    }
}

