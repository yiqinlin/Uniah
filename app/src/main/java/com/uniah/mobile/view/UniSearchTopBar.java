package com.uniah.mobile.view;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uniah.mobile.R;
import com.uniah.mobile.layout.UniFrameLayout;
import com.uniah.mobile.util.UniDisplayHelper;
import com.uniah.mobile.util.UniNotchHelper;
import com.uniah.mobile.util.UniStatusBarHelper;

public class UniSearchTopBar extends UniFrameLayout{

    private Context mContext;
    private Activity mActivity;
    private LinearLayout mTopBar;

    private EditText mEditView;

    private ImageView mBtnLeftSrc;
    private UniFrameLayout mBtnLeftLayout;

    private TextView mBtnRightText;
    private UniFrameLayout mBtnRightLayout;


    private float mShadowAlpha = 0.25f;
    private int mShadowElevationDp = 6;


    public UniSearchTopBar(@NonNull Context context) {
        this(context, null);
    }

    public UniSearchTopBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        if (context instanceof Activity) {
            mActivity = (Activity) context;
        }
        initView();
    }

    private void initView() {
        mTopBar = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.topbar_search, null);

        mEditView = mTopBar.findViewById(R.id.top_bar_edit);

        mBtnLeftSrc = mTopBar.findViewById(R.id.top_bar_btn_left_img);
        mBtnLeftLayout = mTopBar.findViewById(R.id.top_bar_btn_left_layout);
        mBtnLeftLayout.setChangeAlphaWhenPress(true);

        mBtnRightText = mTopBar.findViewById(R.id.top_bar_text_right_content);
        mBtnRightLayout = mTopBar.findViewById(R.id.top_bar_text_right_layout);
        mBtnRightLayout.setChangeAlphaWhenPress(true);

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

    public void setHint(String text) {
        if (text != null) {
            mEditView.setHint(text);
        }
    }

    public void setEditText(String text) {
        if (text != null) {
            mEditView.setText(text);
        }
    }

    public void setLeftButtonImage(int drawableResId) {
        mBtnLeftSrc.setImageResource(drawableResId);
    }

    public void setOnLeftButtonClickListener(View.OnClickListener clickListener) {
        mBtnLeftLayout.setOnClickListener(clickListener);
    }

    public void setRightButtonText(String text) {
        mBtnRightText.setText(text);
    }

    public void setOnRightButtonClickListener(View.OnClickListener clickListener) {
        mBtnRightLayout.setOnClickListener(clickListener);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            setTranslucent();
        }
    }
}
