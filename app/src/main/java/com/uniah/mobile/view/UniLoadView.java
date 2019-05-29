package com.uniah.mobile.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uniah.mobile.R;
import com.uniah.mobile.layout.UniPullLayout;

public class UniLoadView extends FrameLayout implements UniPullLayout.ILoadView {

    Context mContext;

    private ImageView mArrowImageView;
    private TextView headHint;
    private UniAnimImageView mProgressBar;

    private RotateAnimation mAnimation;
    private RotateAnimation mReverseAnimation;
    private boolean isArrowSimple;
    private boolean isCanUnLock = false;
    private boolean isLock = false;

    public UniLoadView(Context context) {
        super(context);
        this.mContext = context;
        initView();
    }

    public UniLoadView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView();
    }

    private void initView() {
        LinearLayout mRefreshView = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.refresh_view, null);

        mArrowImageView = mRefreshView.findViewById(R.id.refresh_view_arrow);
        mProgressBar = mRefreshView.findViewById(R.id.refresh_view_progress);
        headHint = mRefreshView.findViewById(R.id.refresh_view_text);

        mAnimation = new RotateAnimation(0, -180, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        mAnimation.setInterpolator(new LinearInterpolator());
        mAnimation.setDuration(250);
        mAnimation.setFillAfter(true);

        mReverseAnimation = new RotateAnimation(-180, 0, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        mReverseAnimation.setInterpolator(new LinearInterpolator());
        mReverseAnimation.setDuration(250);
        mReverseAnimation.setFillAfter(true);

        reset();
        addView(mRefreshView);
    }

    private void reset() {
        isArrowSimple = true;
        mProgressBar.setVisibility(GONE);
        mArrowImageView.clearAnimation();
        mArrowImageView.setVisibility(VISIBLE);
        mArrowImageView.setBackgroundResource(R.drawable.ic_notifications_black_24dp);
    }

    @Override
    public void stop() {
        reset();
    }

    @Override
    public void preStop(String msg) {
        isCanUnLock = true;
        mProgressBar.setVisibility(GONE);
        mArrowImageView.setVisibility(GONE);
        headHint.setText(msg);
    }

    @Override
    public void doLoad() {
        isCanUnLock = false;
        isLock = true;
        mProgressBar.setVisibility(VISIBLE);
        mArrowImageView.setVisibility(GONE);
        mArrowImageView.clearAnimation();
        headHint.setText("加载中...");
    }

    @Override
    public void onPull(int offset, int total, int overPull) {
        if (offset <= total / 3 && isCanUnLock) {
            isLock = false;
        }
        if (!isLock) {
            if (overPull > 0) {
                mProgressBar.setVisibility(GONE);
                if (isArrowSimple) {
                    mArrowImageView.clearAnimation();
                    mArrowImageView.setVisibility(VISIBLE);
                    mArrowImageView.startAnimation(mAnimation);
                    isArrowSimple = false;
                }
                headHint.setText("松手加载");
            } else {
                mProgressBar.setVisibility(GONE);
                if (!isArrowSimple) {
                    mArrowImageView.clearAnimation();
                    mArrowImageView.setVisibility(VISIBLE);
                    mArrowImageView.startAnimation(mReverseAnimation);
                    isArrowSimple = true;
                }
                headHint.setText("上拉加载");
            }
        }
    }
}