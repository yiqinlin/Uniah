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
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;

import com.uniah.mobile.R;
import com.uniah.mobile.layout.UniFrameLayout;
import com.uniah.mobile.util.UniColorHelper;
import com.uniah.mobile.util.UniDisplayHelper;
import com.uniah.mobile.util.UniStatusBarHelper;

public class UniSegmentTopBar extends UniFrameLayout {

    private Context mContext;
    private Activity mActivity;
    private LinearLayout mTopBar;
    private ViewPager mViewPager;
    private int mSelect = 0;
    private int mSelectTemp = 0;

    private LinearLayout mSegmentLayout;
    private FrameLayout mSegment;
    private TextView mTabLeftView;
    private TextView mTabMidView;
    private TextView mTabRightView;

    private Space mTabLeftSpace;
    private Space mTabRightSpace;
    private View mCursor;

    private ImageView mLeftBtnSrc;
    private UniFrameLayout mLeftBtnLayout;

    private ImageView mLeftSubBtnSrc;
    private UniFrameLayout mLeftSubBtnLayout;

    private ImageView mRightBtnSrc;
    private UniFrameLayout mRightBtnLayout;

    private ImageView mRightSubBtnSrc;
    private UniFrameLayout mRightSubBtnLayout;

    private int mSegmentWidth;
    private int mSpaceWidth;
    private int mCursorWidth;

    private boolean isShowCursor = false;

    private float mShadowAlpha = 0.25f;
    private int mShadowElevationDp = 6;

    private int mTextColorSelect = 0xff333333;
    private int mTextColorUnSelect = 0xff999999;

    private OnTabClickListener mTabClickListener;
    private GestureDetector mLeftGestureDetector;
    private GestureDetector mMidGestureDetector;
    private GestureDetector mRightGestureDetector;

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
        mTopBar = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.topbar_segment_third, null);

        mSegmentLayout = mTopBar.findViewById(R.id.tab_segment_layout);
        mSegment = mTopBar.findViewById(R.id.tab_segment);

        mTabLeftView = mTopBar.findViewById(R.id.tab_left);
        mTabMidView = mTopBar.findViewById(R.id.tab_mid);
        mTabRightView = mTopBar.findViewById(R.id.tab_right);

        mTabLeftSpace = mTopBar.findViewById(R.id.tab_space_left);
        mTabRightSpace = mTopBar.findViewById(R.id.tab_space_right);

        mCursor = mTopBar.findViewById(R.id.tab_cursor);

        mLeftBtnSrc = mTopBar.findViewById(R.id.top_bar_btn_left_img);
        mLeftBtnLayout = mTopBar.findViewById(R.id.top_bar_btn_left_layout);
        mLeftBtnLayout.setChangeAlphaWhenPress(true);

        mLeftSubBtnSrc = mTopBar.findViewById(R.id.top_bar_btn_left_sub_img);
        mLeftSubBtnLayout = mTopBar.findViewById(R.id.top_bar_btn_left_sub_layout);
        mLeftSubBtnLayout.setChangeAlphaWhenPress(true);

        mRightBtnSrc = mTopBar.findViewById(R.id.top_bar_btn_right_img);
        mRightBtnLayout = mTopBar.findViewById(R.id.top_bar_btn_right_layout);
        mRightBtnLayout.setChangeAlphaWhenPress(true);

        mRightSubBtnSrc = mTopBar.findViewById(R.id.top_bar_btn_right_sub_img);
        mRightSubBtnLayout = mTopBar.findViewById(R.id.top_bar_btn_right_sub_layout);
        mRightSubBtnLayout.setChangeAlphaWhenPress(true);

        hasCursor(isShowCursor);

        addView(mTopBar);
        setShadow();
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


        mRightGestureDetector = new GestureDetector(mContext, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                if (mSelect != 2) {
                    if (mViewPager != null) {
                        mViewPager.setCurrentItem(2);
                    }
                } else {
                    mTabClickListener.onTabReselected(2, mTabRightView);
                }
                return false;
            }

            @Override
            public boolean onDoubleTap(MotionEvent e) {
                if (mTabClickListener != null) {
                    mTabClickListener.onDoubleTap(2, mTabRightView);
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

        mTabRightView.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mRightGestureDetector.onTouchEvent(event);
                return true;
            }
        });
    }

    public void setTabLeft(String text) {
        mTabLeftView.setText(text);
    }

    public void setTabMid(String text) {
        mTabMidView.setText(text);
    }

    public void setTabRight(String text) {
        mTabRightView.setText(text);
    }

    public void setTabGravity(int gravity) {
        mSegmentLayout.setGravity(gravity);
    }

    public void setTabNum(int num) {
        mTabLeftView.setVisibility(num > 0 ? VISIBLE : GONE);
        mTabMidView.setVisibility(num > 1 ? VISIBLE : GONE);
        mTabRightView.setVisibility(num > 2 ? VISIBLE : GONE);
        mTabLeftSpace.setVisibility(num > 1 ? VISIBLE : GONE);
        mTabRightSpace.setVisibility(num > 2 ? VISIBLE : GONE);
        switch (num) {
            case 0:
                mSegmentWidth = 0;
                break;
            case 1:
                mSegmentWidth = UniDisplayHelper.dp2px(mContext, 36);
                break;
            case 2:
                mSegmentWidth = UniDisplayHelper.dp2px(mContext, 88);
                break;
            case 3:
                mSegmentWidth = UniDisplayHelper.dp2px(mContext, 140);
                break;
        }
        mSpaceWidth = UniDisplayHelper.dp2px(mContext, 16);
        mCursorWidth = (mSegmentWidth - mSpaceWidth * (num - 1)) / num;
        mCursorWidth = mCursorWidth < 0 ? 0 : mCursorWidth;

        ViewGroup.LayoutParams params = mCursor.getLayoutParams();
        params.width = mCursorWidth;
        mCursor.setLayoutParams(params);

        ViewGroup.LayoutParams segmentParams = mSegment.getLayoutParams();
        segmentParams.width = mSegmentWidth;
        mSegment.setLayoutParams(segmentParams);
    }

    public void hasCursor(boolean isShowCursor) {
        this.isShowCursor = isShowCursor;
        if (isShowCursor) {
            this.mCursor.setVisibility(VISIBLE);
        } else {
            this.mCursor.setVisibility(INVISIBLE);
        }
    }

    public void onTabSelect(int position) {
        mTabLeftView.setTextColor(position == 0 ? mTextColorSelect : mTextColorUnSelect);
        mTabMidView.setTextColor(position == 1 ? mTextColorSelect : mTextColorUnSelect);
        mTabRightView.setTextColor(position == 2 ? mTextColorSelect : mTextColorUnSelect);
        if (isShowCursor) {
            mCursor.setX(position * (mCursorWidth + mSpaceWidth));
        }
        if (mTabClickListener != null) {
            if (position != mSelect) {
                mTabClickListener.onTabSelected(position, position == 0 ? mTabLeftView : (position == 1 ? mTabMidView : mTabRightView));
                mTabClickListener.onTabUnselected(mSelect, mSelect == 0 ? mTabLeftView : (mSelect == 1 ? mTabMidView : mTabRightView));
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
                if (isShowCursor) {
                    mCursor.setX((position + positionOffset) * (mCursorWidth + mSpaceWidth));
                }
                switch (position) {
                    case 0:
                        mTabLeftView.setTextColor(UniColorHelper.computeColor(mTextColorSelect, mTextColorUnSelect, positionOffset));
                        mTabMidView.setTextColor(UniColorHelper.computeColor(mTextColorSelect, mTextColorUnSelect, 1f - positionOffset));
                        break;
                    case 1:
                        mTabMidView.setTextColor(UniColorHelper.computeColor(mTextColorSelect, mTextColorUnSelect, positionOffset));
                        mTabRightView.setTextColor(UniColorHelper.computeColor(mTextColorSelect, mTextColorUnSelect, 1f - positionOffset));
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

    public void setRightButtonImage(int drawableResId) {
        mRightBtnSrc.setBackgroundResource(drawableResId);
    }

    public void setRightButtonClickListener(OnClickListener clickListener) {
        mRightBtnLayout.setOnClickListener(clickListener);
        mRightBtnLayout.setVisibility(VISIBLE);
    }

    public void setRightSubButtonImage(int drawableResId) {
        mRightSubBtnSrc.setBackgroundResource(drawableResId);
    }

    public void setOnRightSubButtonClickListener(OnClickListener clickListener) {
        mRightSubBtnLayout.setOnClickListener(clickListener);
        mRightSubBtnLayout.setVisibility(VISIBLE);
    }

    public void setLeftButtonImage(int drawableResId) {
        mLeftBtnSrc.setBackgroundResource(drawableResId);
    }

    public void setLeftButtonClickListener(OnClickListener clickListener) {
        mLeftBtnLayout.setOnClickListener(clickListener);
        mLeftBtnLayout.setVisibility(VISIBLE);
    }

    public void setLeftSubButtonImage(int drawableResId) {
        mLeftSubBtnSrc.setBackgroundResource(drawableResId);
    }

    public void setOnLeftSubButtonClickListener(OnClickListener clickListener) {
        mLeftSubBtnLayout.setOnClickListener(clickListener);
        mLeftSubBtnLayout.setVisibility(VISIBLE);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            setTranslucent();
        }
    }
}
