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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;

import com.uniah.mobile.R;
import com.uniah.mobile.layout.UniFrameLayout;
import com.uniah.mobile.util.UniColorHelper;
import com.uniah.mobile.util.UniDisplayHelper;
import com.uniah.mobile.util.UniNotchHelper;
import com.uniah.mobile.util.UniStatusBarHelper;

public class UniSegmentTopBar extends UniFrameLayout {

    private Context mContext;
    private Activity mActivity;
    private LinearLayout mTopBar;
    private ViewPager mViewPager;
    private int mSelect = 0;
    private int mSelectTemp = 0;

    private TextView mTabLeftView;
    private TextView mTabMidView;
    private TextView mTabRightView;

    private Space mTabLeftSpace;
    private Space mTabRightSpace;
    private View mCursor;

    private ImageView mBtnSrc;
    private UniFrameLayout mBtnLayout;

    private ImageView mBtnSubSrc;
    private UniFrameLayout mBtnSubLayout;

    private int mSegmentWidth;
    private int mSpaceWidth;

    private boolean isShowCursor = false;

    private float mShadowAlpha = 0.25f;
    private int mShadowElevationDp = 6;

    private int mTextColorSelect = 0xff333333;
    private int mTextColorUnSelect = 0xff999999;

    private OnTabClickListener mTabClickListener;
    private GestureDetector mLeftGestureDetector;
    private GestureDetector mMidGestureDetector;

    public UniSegmentTopBar(@NonNull Context context) {
        this(context, null);
    }

    public UniSegmentTopBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        if (context instanceof Activity) {
            mActivity = (Activity) context;
        }
        initView();
        initListener();
    }

    private void initView() {
        mTopBar = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.topbar_segment, null);

        mTabLeftView = mTopBar.findViewById(R.id.tab_left);
        mTabMidView = mTopBar.findViewById(R.id.tab_mid);
        mTabRightView = mTopBar.findViewById(R.id.tab_right);

        mTabLeftSpace = mTopBar.findViewById(R.id.tab_space_left);
        mTabRightSpace = mTopBar.findViewById(R.id.tab_space_right);

        mCursor = mTopBar.findViewById(R.id.tab_cursor);

        mBtnSrc = mTopBar.findViewById(R.id.top_bar_btn_right_img);
        mBtnLayout = mTopBar.findViewById(R.id.top_bar_btn_right_layout);
        mBtnLayout.setChangeAlphaWhenPress(true);

        mBtnSubSrc = mTopBar.findViewById(R.id.top_bar_btn_right_sub_img);
        mBtnSubLayout = mTopBar.findViewById(R.id.top_bar_btn_right_sub_layout);
        mBtnSubLayout.setChangeAlphaWhenPress(true);

        mSegmentWidth = UniDisplayHelper.dp2px(mContext, 88);
        mSpaceWidth = UniDisplayHelper.dp2px(mContext, 16);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((mSegmentWidth - mSpaceWidth) / 2, ViewGroup.LayoutParams.MATCH_PARENT);
        mCursor.setLayoutParams(params);

        hasCursor(isShowCursor);

        addView(mTopBar);
        setShadow();
        onTabSelect(0);
    }

    @SuppressLint("ClickableViewAccessibility")
    public void initListener() {
        mLeftGestureDetector = new GestureDetector(mContext, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                if (mSelect != 0) {
                    if (mViewPager != null) {
                        mViewPager.setCurrentItem(0);
                    }
                } else {
                    mTabClickListener.onTabReselected(0, mTabLeftView);
                }
                return false;
            }

            @Override
            public boolean onDoubleTap(MotionEvent e) {
                if (mTabClickListener != null) {
                    mTabClickListener.onDoubleTap(0, mTabLeftView);
                }
                return false;
            }
        });

        mMidGestureDetector = new GestureDetector(mContext, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                if (mSelect != 1) {
                    if (mViewPager != null) {
                        mViewPager.setCurrentItem(1);
                    }
                } else {
                    mTabClickListener.onTabReselected(1, mTabMidView);
                }
                return false;
            }

            @Override
            public boolean onDoubleTap(MotionEvent e) {
                if (mTabClickListener != null) {
                    mTabClickListener.onDoubleTap(1, mTabMidView);
                }
                return false;
            }
        });

        mTabLeftView.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mLeftGestureDetector.onTouchEvent(event);
                return true;
            }
        });

        mTabMidView.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mMidGestureDetector.onTouchEvent(event);
                return true;
            }
        });
    }

    public void setTabLeft(String text) {
        mTabLeftView.setText(text);
    }

    public void setTabRight(String text) {
        mTabMidView.setText(text);
    }

    public void hasCursor(boolean isShowCursor) {
        this.isShowCursor = isShowCursor;
        if (isShowCursor) {
            this.mCursor.setVisibility(VISIBLE);
        } else {
            this.mCursor.setVisibility(INVISIBLE);
        }
    }

    private void onTabSelect(int position) {
        mTabLeftView.setTextColor(position == 0 ? mTextColorSelect : mTextColorUnSelect);
        mTabMidView.setTextColor(position == 1 ? mTextColorSelect : mTextColorUnSelect);
        if (isShowCursor) {
            mCursor.setX((mSegmentWidth + mSpaceWidth) / 2 * position);
        }
        if (mTabClickListener != null) {
            if (position != mSelect) {
                mTabClickListener.onTabSelected(position, position == 0 ? mTabLeftView : mTabMidView);
                mTabClickListener.onTabUnselected(mSelect, mSelect == 0 ? mTabLeftView : mTabMidView);
            }
        }
        mSelect = position;
    }

    public void setViewPager(ViewPager viewPager) {
        this.mViewPager = viewPager;
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == 0) {
                    onTabSelect(mSelectTemp);
                }
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                switch (position) {
                    case 0:
                        if (isShowCursor) {
                            mCursor.setX((mSegmentWidth + mSpaceWidth) / 2 * positionOffset);
                        }
                        mTabLeftView.setTextColor(UniColorHelper.computeColor(mTextColorSelect, mTextColorUnSelect, positionOffset));
                        mTabMidView.setTextColor(UniColorHelper.computeColor(mTextColorSelect, mTextColorUnSelect, 1f - positionOffset));
                        break;
                }
            }

            @Override
            public void onPageSelected(int position) {
                mSelectTemp = position;
            }
        });
    }

    public void setOnTabClickListener(OnTabClickListener tabClickListener) {
        this.mTabClickListener = tabClickListener;
    }

    public abstract static class OnTabClickListener implements TabClickListener {
        @Override
        public void onTabUnselected(int index, View v) {
        }

        @Override
        public void onTabReselected(int index, View v) {
        }

        @Override
        public void onDoubleTap(int index, View v) {
        }
    }

    public interface TabClickListener {
        /**
         * 当某个 Tab 被选中时会触发
         */
        void onTabSelected(int index, View v);

        /**
         * 当某个 Tab 被取消选中时会触发
         */
        void onTabUnselected(int index, View v);

        /**
         * 当某个 Tab 处于被选中状态下再次被点击时会触发
         */
        void onTabReselected(int index, View v);

        /**
         * 当某个 Tab 被双击时会触发
         */
        void onDoubleTap(int index, View v);
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
            mTopBar.setPadding(0, UniStatusBarHelper.getTranslucentHeight(mActivity), 0, 0);
        }
    }

    public void setButtonImage(int drawableResId) {
        mBtnSrc.setBackgroundResource(drawableResId);
    }

    public void setButtonClickListener(OnClickListener clickListener) {
        mBtnLayout.setOnClickListener(clickListener);
    }

    public void setSubButtonImage(int drawableResId) {
        mBtnSubSrc.setBackgroundResource(drawableResId);
        mBtnSubLayout.setVisibility(VISIBLE);
    }

    public void setOnSubButtonClickListener(OnClickListener clickListener) {
        mBtnSubLayout.setOnClickListener(clickListener);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            setTranslucent();
        }
    }
}
