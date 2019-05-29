/*
 * Tencent is pleased to support the open source community by making Uni_Android available.
 *
 * Copyright (C) 2017-2018 THL A29 Limited, a Tencent company. All rights reserved.
 *
 * Licensed under the MIT License (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 *
 * http://opensource.org/licenses/MIT
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.uniah.mobile.widget.pullRefreshLayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.NestedScrollingParentHelper;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.CircularProgressDrawable;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Scroller;

import com.uniah.mobile.BuildConfig;
import com.uniah.mobile.R;
import com.uniah.mobile.util.UniDisplayHelper;
import com.uniah.mobile.util.UniResHelper;

/**
 * 下拉刷新控件, 作为容器，下拉时会将子 View 下移, 并拉出 RefreshView（表示正在刷新的 View）
 * <ul>
 * <li>可通过继承并覆写 {@link #createRefreshView(Context)} 方法实现自己的 RefreshView</li>
 * <li>可通过 {@link #setRefreshOffsetCalculator(OffsetCalculator)} 自己决定在下拉过程中 RefreshView 的位置</li>
 * <li>可在 xml 中使用 {@link R.styleable#UniPullRefreshLayout} 这些属性或在 Java 设置对应的属性决定子View的开始位置、触发刷新的位置等值</li>
 * </ul>
 */
public class UniPullRefreshLayout extends ViewGroup implements NestedScrollingParent {
    private static final String TAG = "UniRefreshLayout";
    private static final int FLAG_NEED_SCROLL_TO_INIT_POSITION = 1;
    private static final int FLAG_NEED_SCROLL_TO_REFRESH_POSITION = 1 << 1;
    private static final int FLAG_NEED_SCROLL_TO_LOAD_POSITION = 1 << 2;
    private static final int FLAG_NEED_DO_REFRESH = 1 << 3;
    private static final int FLAG_NEED_DO_LOAD = 1 << 4;
    private static final int FLAG_NEED_DELIVER_VELOCITY = 1 << 5;
    private final NestedScrollingParentHelper mNestedScrollingParentHelper;

    private static final int FLAG_IS_REFRESH = 1;
    private static final int FLAG_IS_LOAD = 1 << 1;

    private int mPullFlag = 0;

    boolean mIsRefreshing = false;
    boolean mIsLoading = false;

    private View mTargetView;
    private IRefreshView mIRefreshView;
    private ILoadView mILoadView;
    private View mRefreshView;
    private View mLoadView;

    private int mRefreshZIndex = -1;
    private int mLoadZIndex = -1;

    private int mSystemTouchSlop;
    private OnPullRefreshListener mPullDownListener;
    private OnPullLoadListener mPullUpListener;
    private OnChildScrollUpCallback mChildScrollUpCallback;
    /**
     * RefreshView的初始offset
     */
    private int mRefreshInitOffset;
    private int mLoadInitOffset;
    /**
     * 刷新时RefreshView的offset
     */
    private int mRefreshEndOffset;
    private int mLoadEndOffset;
    /**
     * RefreshView当前offset
     */
    private int mRefreshCurrentOffset;
    private int mLoadCurrentOffset;
    /**
     * 是否自动根据RefreshView的高度计算RefreshView的初始位置
     */
    private boolean mAutoCalculateRefreshInitOffset = true;
    private boolean mAutoCalculateLoadInitOffset = true;
    /**
     * 是否自动根据TargetView在刷新时的位置计算RefreshView的结束位置
     */
    private boolean mAutoCalculateRefreshEndOffset = true;
    private boolean mAutoCalculateLoadEndOffset = true;
    /**
     * 自动让TargetView的刷新位置与RefreshView高度相等
     */
    private boolean mEqualTargetRefreshOffsetToRefreshViewHeight = false;
    private boolean mEqualTargetLoadOffsetToLoadViewHeight = false;
    /**
     * 当拖拽超过超过mAutoScrollToRefreshMinOffset时，自动滚动到刷新位置并触发刷新
     * mAutoScrollToRefreshMinOffset == - 1表示要mAutoScrollToRefreshMinOffset>=mTargetRefreshOffset
     */
    private int mAutoScrollToRefreshMinOffset = -1;
    private int mAutoScrollToLoadMinOffset = -1;
    /**
     * TargetView(ListView或者ScrollView等)的初始位置
     */
    private int mTargetInitOffset;
    /**
     * 下拉时 TargetView（ListView 或者 ScrollView 等）当前的位置。
     */
    private int mTargetCurrentOffset;
    /**
     * 刷新时TargetView(ListView或者ScrollView等)的位置
     */
    private int mTargetRefreshOffset;
    private int mTargetLoadOffset;

    private boolean mDisableNestScrollImpl = false;
    private boolean mEnableOverPull = true;
    private boolean mNestedScrollInProgress;
    @SuppressWarnings("FieldCanBeLocal")
    private float mDragRate = 0.5f;
    private int mPreStopDelay = 1000;
    private float mMiniVelocity;
    private Scroller mScroller;
    private int mScrollFlag = 0;
    private OffsetCalculator mRefreshOffsetCalculator;
    private OffsetCalculator mLoadOffsetCalculator;
    private boolean mNestScrollDurationRefreshing = false;
    private Runnable mPendingRefreshDirectlyAction = null;
    private boolean mSafeDisallowInterceptTouchEvent = false;


    public UniPullRefreshLayout(Context context) {
        this(context, null);
    }

    public UniPullRefreshLayout(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.UniPullRefreshLayoutStyle);
    }

    public UniPullRefreshLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setWillNotDraw(false);

        final ViewConfiguration vc = ViewConfiguration.get(context);
        mMiniVelocity = vc.getScaledMinimumFlingVelocity();
        mSystemTouchSlop = vc.getScaledTouchSlop();

        mScroller = new Scroller(getContext());
        mScroller.setFriction(getScrollerFriction());

        addRefreshView();
        addLoadView();
        ViewCompat.setChildrenDrawingOrderEnabled(this, true);

        mNestedScrollingParentHelper = new NestedScrollingParentHelper(this);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.UniPullRefreshLayout, defStyleAttr, 0);

        try {
            mRefreshInitOffset = array.getDimensionPixelSize(
                    R.styleable.UniPullRefreshLayout_uni_refresh_init_offset, Integer.MIN_VALUE);
            mRefreshInitOffset = array.getDimensionPixelSize(
                    R.styleable.UniPullRefreshLayout_uni_load_init_offset, Integer.MIN_VALUE);
            mRefreshEndOffset = array.getDimensionPixelSize(
                    R.styleable.UniPullRefreshLayout_uni_refresh_end_offset, Integer.MIN_VALUE);
            mRefreshEndOffset = array.getDimensionPixelSize(
                    R.styleable.UniPullRefreshLayout_uni_load_end_offset, Integer.MIN_VALUE);
            mTargetInitOffset = array.getDimensionPixelSize(
                    R.styleable.UniPullRefreshLayout_uni_target_init_offset, 0);
            mTargetRefreshOffset = array.getDimensionPixelSize(
                    R.styleable.UniPullRefreshLayout_uni_target_refresh_offset,
                    UniDisplayHelper.dp2px(getContext(), 72));
            mTargetLoadOffset = -array.getDimensionPixelSize(
                    R.styleable.UniPullRefreshLayout_uni_target_load_offset,
                    UniDisplayHelper.dp2px(getContext(), 72));
            mAutoCalculateRefreshInitOffset = mRefreshInitOffset == Integer.MIN_VALUE ||
                    array.getBoolean(R.styleable.UniPullRefreshLayout_uni_auto_calculate_refresh_init_offset, false);
            mAutoCalculateRefreshInitOffset = mRefreshInitOffset == Integer.MIN_VALUE ||
                    array.getBoolean(R.styleable.UniPullRefreshLayout_uni_auto_calculate_load_init_offset, false);
            mAutoCalculateRefreshEndOffset = mRefreshEndOffset == Integer.MIN_VALUE ||
                    array.getBoolean(R.styleable.UniPullRefreshLayout_uni_auto_calculate_refresh_end_offset, false);
            mAutoCalculateRefreshEndOffset = mRefreshEndOffset == Integer.MIN_VALUE ||
                    array.getBoolean(R.styleable.UniPullRefreshLayout_uni_auto_calculate_load_end_offset, false);
            mEqualTargetRefreshOffsetToRefreshViewHeight = array.getBoolean(R.styleable.UniPullRefreshLayout_uni_equal_target_refresh_offset_to_refresh_view_height, false);
            mEqualTargetLoadOffsetToLoadViewHeight = array.getBoolean(R.styleable.UniPullRefreshLayout_uni_equal_target_load_offset_to_load_view_height, false);
        } finally {
            array.recycle();
        }
        mRefreshCurrentOffset = mRefreshInitOffset;
        mLoadCurrentOffset = mLoadInitOffset;
        mTargetCurrentOffset = mTargetInitOffset;
    }

    public static boolean defaultCanScrollUp(View view) {
        if (view == null) {
            return false;
        }
        if (Build.VERSION.SDK_INT < 14) {
            if (view instanceof AbsListView) {
                final AbsListView absListView = (AbsListView) view;
                return absListView.getChildCount() > 0
                        && (absListView.getFirstVisiblePosition() > 0 || absListView.getChildAt(0)
                        .getTop() < absListView.getPaddingTop());
            } else {
                return ViewCompat.canScrollVertically(view, -1) || view.getScrollY() > 0;
            }
        } else {
            return ViewCompat.canScrollVertically(view, -1);
        }
    }

    public static boolean defaultCanScrollDown(View view) {
        if (view == null) {
            return false;
        }
        if (Build.VERSION.SDK_INT < 14) {
            if (view instanceof AbsListView) {
                final AbsListView absListView = (AbsListView) view;
                int totalItemCount = absListView.getChildCount();
                View lastView = absListView.getChildAt(totalItemCount - 1);
                return totalItemCount > 0 && lastView != null && lastView.getBottom() > absListView.getHeight();
            } else {
                return ViewCompat.canScrollVertically(view, 1);
            }
        } else {
            return ViewCompat.canScrollVertically(view, 1);
        }
    }

    public void setOnPullRefreshListener(OnPullRefreshListener listener) {
        mPullDownListener = listener;
        if (mPullDownListener != null && mRefreshView != null) {
            mRefreshView.setVisibility(VISIBLE);
        }
    }

    public void setOnPullLoadListener(OnPullLoadListener listener) {
        mPullUpListener = listener;
        if (mPullUpListener != null && mLoadView != null) {
            mLoadView.setVisibility(VISIBLE);
        }
    }

    public void setDisableNestScrollImpl(boolean disableNestScrollImpl) {
        mDisableNestScrollImpl = disableNestScrollImpl;
    }

    public void setDragRate(float dragRate) {
        // have no idea to change drag rate for nest scroll
        mDisableNestScrollImpl = true;
        mDragRate = dragRate;
    }

    public void setPreStopDelay(int delay) {
        // have no idea to change drag rate for nest scroll
        mPreStopDelay = delay;
    }

    public void setChildScrollUpCallback(OnChildScrollUpCallback childScrollUpCallback) {
        mChildScrollUpCallback = childScrollUpCallback;
    }

    protected float getScrollerFriction() {
        return ViewConfiguration.getScrollFriction();
    }

    public void setAutoScrollToRefreshMinOffset(int autoScrollToRefreshMinOffset) {
        mAutoScrollToRefreshMinOffset = autoScrollToRefreshMinOffset;
    }

    /**
     * 覆盖该方法以实现自己的 RefreshView。
     *
     * @return 自定义的 RefreshView, 注意该 View 必须实现 {@link IRefreshView} 接口
     */
    protected View createRefreshView(Context context) {
        return new RefreshView(context);
    }

    protected View createLoadView(Context context) {
        return new RefreshView(getContext());
    }

    private void addRefreshView() {
        if (mRefreshView == null) {
            mRefreshView = createRefreshView(getContext());
        }
        if (!(mRefreshView instanceof IRefreshView)) {
            throw new RuntimeException("refreshView must be a instance of IRefreshView");
        }
        mIRefreshView = (IRefreshView) mRefreshView;
        if (mRefreshView.getLayoutParams() == null) {
            mRefreshView.setLayoutParams(new LayoutParams(
                    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        }
        mRefreshView.setVisibility(GONE);
        addView(mRefreshView);
    }

    private void addLoadView() {
        if (mLoadView == null) {
            mLoadView = createLoadView(getContext());
        }
        if (!(mLoadView instanceof ILoadView)) {
            throw new RuntimeException("refreshView must be a instance of IRefreshView");
        }
        mILoadView = (ILoadView) mLoadView;
        if (mLoadView.getLayoutParams() == null) {
            mLoadView.setLayoutParams(new LayoutParams(
                    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        }
        mLoadView.setVisibility(GONE);
        addView(mLoadView);
    }

    /**
     * 设置在下拉过程中 RefreshView 的偏移量
     */
    public void setRefreshOffsetCalculator(OffsetCalculator refreshOffsetCalculator) {
        mRefreshOffsetCalculator = refreshOffsetCalculator;
    }

    /**
     * 设置在下拉过程中 LoadView 的偏移量
     */
    public void setLoadOffsetCalculator(OffsetCalculator loadOffsetCalculator) {
        mLoadOffsetCalculator = loadOffsetCalculator;
    }

    @Override
    protected int getChildDrawingOrder(int childCount, int i) {
        if (mRefreshZIndex < 0) {
            return i;
        }
        // 最后才绘制mRefreshView
        if (i == mRefreshZIndex) {
            return childCount - 1;
        }
        if (i > mRefreshZIndex) {
            return i - 1;
        }
        return i;
    }

    /**
     * child view call, to ensure disallowInterceptTouchEvent make sense
     * <p>
     * how to optimize this...
     */
    public void openSafeDisallowInterceptTouchEvent() {
        mSafeDisallowInterceptTouchEvent = true;
    }

    @Override
    public void requestDisallowInterceptTouchEvent(boolean b) {

        if (mSafeDisallowInterceptTouchEvent) {
            super.requestDisallowInterceptTouchEvent(b);
            mSafeDisallowInterceptTouchEvent = false;
        }

        // if this is a List < L or another view that doesn't support nested
        // scrolling, ignore this request so that the vertical scroll event
        // isn't stolen
        //noinspection StatementWithEmptyBody
        if ((Build.VERSION.SDK_INT < 21 && mTargetView instanceof AbsListView)
                || (mTargetView != null && !ViewCompat.isNestedScrollingEnabled(mTargetView))) {
            // Nope.
        } else {
            super.requestDisallowInterceptTouchEvent(b);
        }
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        ensureTargetView();
        if (mTargetView == null) {
            Log.d(TAG, "onMeasure: mTargetView == null");
            return;
        }
        int targetMeasureWidthSpec = MeasureSpec.makeMeasureSpec(
                getMeasuredWidth() - getPaddingLeft() - getPaddingRight(), MeasureSpec.EXACTLY);
        int targetMeasureHeightSpec = MeasureSpec.makeMeasureSpec(
                getMeasuredHeight() - getPaddingTop() - getPaddingBottom(), MeasureSpec.EXACTLY);
        mTargetView.measure(targetMeasureWidthSpec, targetMeasureHeightSpec);
        measureChild(mRefreshView, widthMeasureSpec, heightMeasureSpec);
        mRefreshZIndex = -1;
        for (int i = 0; i < getChildCount(); i++) {
            if (getChildAt(i) == mRefreshView) {
                mRefreshZIndex = i;
                break;
            }
        }
        measureChild(mLoadView, widthMeasureSpec, heightMeasureSpec);
        mLoadZIndex = -1;
        for (int i = 0; i < getChildCount(); i++) {
            if (getChildAt(i) == mLoadView) {
                mLoadZIndex = i;
                break;
            }
        }

        int refreshViewHeight = mRefreshView.getMeasuredHeight();
        if (mAutoCalculateRefreshInitOffset) {
            if (mRefreshInitOffset != -refreshViewHeight) {
                mRefreshInitOffset = -refreshViewHeight;
                mRefreshCurrentOffset = mRefreshInitOffset;
            }
        }

        int loadViewHeight = mLoadView.getMeasuredHeight();
        if (mAutoCalculateLoadInitOffset) {
            if (mLoadInitOffset != -loadViewHeight) {
                mLoadInitOffset = -loadViewHeight;
                mLoadCurrentOffset = mLoadInitOffset;
            }
        }

        if (mEqualTargetRefreshOffsetToRefreshViewHeight) {
            mTargetRefreshOffset = refreshViewHeight;
        }
        if (mEqualTargetLoadOffsetToLoadViewHeight) {
            mTargetLoadOffset = -loadViewHeight;
        }

        if (mAutoCalculateRefreshEndOffset) {
            mRefreshEndOffset = (mTargetRefreshOffset - refreshViewHeight) / 2;
        }
        if (mAutoCalculateLoadEndOffset) {
            mLoadEndOffset = (mTargetLoadOffset - loadViewHeight) / 2;
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        final int width = getMeasuredWidth();
        final int height = getMeasuredHeight();
        if (getChildCount() == 0) {
            return;
        }
        ensureTargetView();
        if (mTargetView == null) {
            Log.d(TAG, "onLayout: mTargetView == null");
            return;
        }

        final int childLeft = getPaddingLeft();
        final int childTop = getPaddingTop();
        final int childWidth = width - getPaddingLeft() - getPaddingRight();
        final int childHeight = height - getPaddingTop() - getPaddingBottom();
        mTargetView.layout(childLeft, childTop + mTargetCurrentOffset,
                childLeft + childWidth, childTop + childHeight + mTargetCurrentOffset);

        int refreshViewWidth = mRefreshView.getMeasuredWidth();
        int refreshViewHeight = mRefreshView.getMeasuredHeight();
        mRefreshView.layout((width / 2 - refreshViewWidth / 2), mRefreshCurrentOffset,
                (width / 2 + refreshViewWidth / 2), mRefreshCurrentOffset + refreshViewHeight);

        int loadViewWidth = mLoadView.getMeasuredWidth();
        int loadViewHeight = mLoadView.getMeasuredHeight();
        mLoadView.layout((width / 2 - loadViewWidth / 2), mLoadCurrentOffset,
                (width / 2 + loadViewHeight / 2), mLoadCurrentOffset + loadViewHeight);
    }

    private void ensureTargetView() {
        if (mTargetView == null) {
            for (int i = 0; i < getChildCount(); i++) {
                View view = getChildAt(i);
                if (!view.equals(mRefreshView) && !view.equals(mLoadView)) {
                    onSureTargetView(view);
                    mTargetView = view;
                    break;
                }
            }
        }
        if (mTargetView != null && mPendingRefreshDirectlyAction != null) {
            Runnable runnable = mPendingRefreshDirectlyAction;
            mPendingRefreshDirectlyAction = null;
            runnable.run();
        }
    }

    /**
     * 确定TargetView, 提供机会给子类来做一些初始化的操作
     */
    protected void onSureTargetView(View targetView) {

    }

    protected void onFinishPullDown(int vy, int refreshInitOffset, int refreshEndOffset, int refreshViewHeight,
                                    int targetCurrentOffset, int targetInitOffset, int targetRefreshOffset) {

    }

    private void finishPullDown(int vy) {
        info("finishPullDown: vy = " + vy + " ; mTargetCurrentOffset = " + mTargetCurrentOffset +
                " ; mTargetRefreshOffset = " + mTargetRefreshOffset + " ; mTargetInitOffset = " + mTargetInitOffset +
                " ; mScroller.isFinished() = " + mScroller.isFinished());
        int miniVy = vy / 1000; // 向下拖拽时， 速度不能太大
        onFinishPullDown(miniVy, mRefreshInitOffset, mRefreshEndOffset, mRefreshView.getHeight(),
                mTargetCurrentOffset, mTargetInitOffset, mTargetRefreshOffset);
        if (mTargetCurrentOffset >= mTargetRefreshOffset) {
            if (miniVy > 0) {
                mScrollFlag = mPullDownListener != null ? FLAG_NEED_SCROLL_TO_REFRESH_POSITION | FLAG_NEED_DO_REFRESH : FLAG_NEED_SCROLL_TO_INIT_POSITION;
                mScroller.fling(0, mTargetCurrentOffset, 0, miniVy,
                        0, 0, mTargetInitOffset, Integer.MAX_VALUE);
                invalidate();
            } else if (miniVy < 0) {
                mScroller.fling(0, mTargetCurrentOffset, 0, vy,
                        0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
                if (mScroller.getFinalY() < mTargetInitOffset) {
                    mScrollFlag = FLAG_NEED_DELIVER_VELOCITY;
                } else if (mScroller.getFinalY() < mTargetRefreshOffset) {
                    int dy = mTargetInitOffset - mTargetCurrentOffset;
                    mScroller.startScroll(0, mTargetCurrentOffset, 0, dy);
                } else if (mScroller.getFinalY() == mTargetRefreshOffset) {
                    mScrollFlag = mPullDownListener != null ? FLAG_NEED_DO_REFRESH : FLAG_NEED_SCROLL_TO_INIT_POSITION;
                } else if (mPullDownListener != null) {
                    mScroller.startScroll(0, mTargetCurrentOffset, 0, mTargetRefreshOffset - mTargetCurrentOffset);
                    mScrollFlag = FLAG_NEED_DO_REFRESH;
                } else {
                    mScrollFlag = FLAG_NEED_SCROLL_TO_INIT_POSITION;
                }
                invalidate();
            } else {
                if (mTargetCurrentOffset > mTargetRefreshOffset) {
                    if (mPullDownListener != null) {
                        mScroller.startScroll(0, mTargetCurrentOffset, 0, mTargetRefreshOffset - mTargetCurrentOffset);
                        mScrollFlag = FLAG_NEED_DO_REFRESH;
                    } else {
                        mScrollFlag = FLAG_NEED_SCROLL_TO_INIT_POSITION;
                    }
                }
                invalidate();
            }
        } else {
            if (miniVy > 0) {
                mScroller.fling(0, mTargetCurrentOffset, 0, miniVy, 0, 0, mTargetInitOffset, Integer.MAX_VALUE);
                //mScroller.startScroll(0, mTargetCurrentOffset, 0, mTargetRefreshOffset - mTargetCurrentOffset);
                if (mScroller.getFinalY() > mTargetRefreshOffset) {
                    mScrollFlag = mPullDownListener != null ? FLAG_NEED_SCROLL_TO_REFRESH_POSITION | FLAG_NEED_DO_REFRESH : FLAG_NEED_SCROLL_TO_INIT_POSITION;
                } else if (mAutoScrollToRefreshMinOffset >= 0 && mScroller.getFinalY() > mAutoScrollToRefreshMinOffset && mPullDownListener != null) {
                    mScroller.startScroll(0, mTargetCurrentOffset, 0, mTargetRefreshOffset - mTargetCurrentOffset);
                } else {
                    mScrollFlag = FLAG_NEED_SCROLL_TO_INIT_POSITION;
                }
                invalidate();
            } else if (miniVy < 0) {
                mScrollFlag = FLAG_NEED_SCROLL_TO_INIT_POSITION;//0
                mScroller.fling(0, mTargetCurrentOffset, 0, vy, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
                if (mScroller.getFinalY() < mTargetInitOffset) {
                    mScrollFlag = FLAG_NEED_DELIVER_VELOCITY;
                } else {
                    mScroller.startScroll(0, mTargetCurrentOffset, 0, mTargetInitOffset - mTargetCurrentOffset);
                    mScrollFlag = FLAG_NEED_SCROLL_TO_INIT_POSITION;//0
                }
                invalidate();
            } else {
                if (mTargetCurrentOffset == mTargetInitOffset) {
                    return;
                }
                if (mAutoScrollToRefreshMinOffset >= 0 && mTargetCurrentOffset >= mAutoScrollToRefreshMinOffset && mPullDownListener != null) {
                    mScroller.startScroll(0, mTargetCurrentOffset, 0, mTargetRefreshOffset - mTargetCurrentOffset);
                } else {
                    mScroller.startScroll(0, mTargetCurrentOffset, 0, mTargetInitOffset - mTargetCurrentOffset);
                    mScrollFlag = FLAG_NEED_SCROLL_TO_INIT_POSITION;//0
                }
                invalidate();
            }
        }
    }


    protected void onFinishPullUp(int vy, int loadInitOffset, int loadEndOffset, int loadViewHeight,
                                  int targetCurrentOffset, int targetInitOffset, int targetLoadOffset) {

    }

    private void finishPullUp(int vy) {
        info("finishPullUp: vy = " + vy + " ; mTargetCurrentOffset = " + mTargetCurrentOffset +
                " ; mTargetLoadOffset = " + mTargetLoadOffset + " ; mTargetInitOffset = " + mTargetInitOffset +
                " ; mScroller.isFinished() = " + mScroller.isFinished());
        int miniVy = vy / 1000; // 向下拖拽时， 速度不能太大
        onFinishPullUp(miniVy, mLoadInitOffset, mLoadEndOffset, mLoadView.getHeight(),
                mTargetCurrentOffset, mTargetInitOffset, mTargetLoadOffset);
        if (mTargetCurrentOffset <= mTargetLoadOffset) {
            if (miniVy < 0) {
                info("mTargetCurrentOffset:" + mTargetCurrentOffset + "   miniVy:" + miniVy);

                mScrollFlag = mPullUpListener != null ? FLAG_NEED_SCROLL_TO_LOAD_POSITION | FLAG_NEED_DO_LOAD : FLAG_NEED_SCROLL_TO_INIT_POSITION;
                mScroller.fling(0, mTargetCurrentOffset, 0, miniVy, 0, 0, Integer.MIN_VALUE, mTargetInitOffset);
                invalidate();
            } else if (miniVy > 0) {
                mScroller.fling(0, mTargetCurrentOffset, 0, vy,
                        0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
                if (mScroller.getFinalY() < mTargetInitOffset) {
                    mScrollFlag = FLAG_NEED_DELIVER_VELOCITY;
                } else if (mScroller.getFinalY() > mTargetLoadOffset) {
                    int dy = mTargetInitOffset - mTargetCurrentOffset;
                    mScroller.startScroll(0, mTargetCurrentOffset, 0, dy);
                } else if (mScroller.getFinalY() == mTargetLoadOffset) {
                    mScrollFlag = mPullUpListener != null ? FLAG_NEED_DO_LOAD : FLAG_NEED_SCROLL_TO_INIT_POSITION;
                } else if (mPullUpListener != null) {
                    mScroller.startScroll(0, mTargetCurrentOffset, 0, mTargetLoadOffset - mTargetCurrentOffset);
                    mScrollFlag = FLAG_NEED_DO_LOAD;
                } else {
                    mScrollFlag = FLAG_NEED_SCROLL_TO_INIT_POSITION;
                }
                invalidate();
            } else {
                if (mTargetCurrentOffset < mTargetLoadOffset && mPullUpListener != null) {
                    mScroller.startScroll(0, mTargetCurrentOffset, 0, mTargetLoadOffset - mTargetCurrentOffset);
                    mScrollFlag = FLAG_NEED_DO_LOAD;
                } else {
                    mScrollFlag = FLAG_NEED_SCROLL_TO_INIT_POSITION;
                }
                invalidate();
            }
        } else {
            if (miniVy < 0) {
                mScroller.fling(0, mTargetCurrentOffset, 0, miniVy, 0, 0, Integer.MIN_VALUE, mTargetInitOffset);
                if (mScroller.getFinalY() < mTargetLoadOffset) {
                    mScrollFlag = mPullUpListener != null ? FLAG_NEED_SCROLL_TO_LOAD_POSITION | FLAG_NEED_DO_LOAD : FLAG_NEED_SCROLL_TO_INIT_POSITION;
                } else if (mAutoScrollToLoadMinOffset >= 0 && mScroller.getFinalY() > mAutoScrollToLoadMinOffset && mPullUpListener != null) {
                    mScroller.startScroll(0, mTargetCurrentOffset, 0, mTargetLoadOffset - mTargetCurrentOffset);
                    mScrollFlag = FLAG_NEED_DO_LOAD;
                } else {
                    mScrollFlag = FLAG_NEED_SCROLL_TO_INIT_POSITION;
                }
                invalidate();
            } else if (miniVy > 0) {
                mScrollFlag = FLAG_NEED_SCROLL_TO_INIT_POSITION;//0
                mScroller.fling(0, mTargetCurrentOffset, 0, vy, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
                if (mScroller.getFinalY() < mTargetInitOffset) {
                    mScrollFlag = FLAG_NEED_DELIVER_VELOCITY;
                } else {
                    mScroller.startScroll(0, mTargetCurrentOffset, 0, mTargetInitOffset - mTargetCurrentOffset);
                    mScrollFlag = FLAG_NEED_SCROLL_TO_INIT_POSITION;//0
                }
                invalidate();
            } else {
                if (mTargetCurrentOffset == mTargetInitOffset) {
                    return;
                }
                if (mAutoScrollToLoadMinOffset >= 0 && mTargetCurrentOffset >= mAutoScrollToLoadMinOffset && mPullUpListener != null) {
                    mScroller.startScroll(0, mTargetCurrentOffset, 0, mTargetLoadOffset - mTargetCurrentOffset);
                    mScrollFlag = FLAG_NEED_DO_LOAD;
                } else {
                    mScroller.startScroll(0, mTargetCurrentOffset, 0, mTargetInitOffset - mTargetCurrentOffset);
                    mScrollFlag = FLAG_NEED_SCROLL_TO_INIT_POSITION;//0
                }
                invalidate();
            }
        }
    }

    protected void onRefresh() {
        if (mIsRefreshing) {
            return;
        }
        mIsRefreshing = true;
        mIRefreshView.doRefresh();
        if (mPullDownListener != null) {
            mPullDownListener.onRefresh();
        }
    }

    protected void onLoad() {
        if (mIsLoading) {
            return;
        }
        mIsLoading = true;
        mILoadView.doLoad();
        if (mPullUpListener != null) {
            mPullUpListener.onLoad();
        }
    }

    public void finishRefreshDelay(String msg) {
        if (!mIsRefreshing) {
            return;
        }
        mIRefreshView.preStop(msg);
        this.postDelayed(new Runnable() {
            @Override
            public void run() {
                finishRefresh();
            }
        }, mPreStopDelay);
    }

    public void finishRefresh() {
        mScrollFlag = FLAG_NEED_SCROLL_TO_INIT_POSITION;
        mScroller.forceFinished(true);
        mIsRefreshing = false;
        invalidate();
    }

    public void finishLoadDelay(String msg) {
        mILoadView.preStop(msg);
        this.postDelayed(new Runnable() {
            @Override
            public void run() {
                finishLoad();
            }
        }, mPreStopDelay);
    }

    public void finishLoad() {
        mScrollFlag = FLAG_NEED_SCROLL_TO_INIT_POSITION;
        mIsLoading = false;
        mScroller.forceFinished(true);
        invalidate();
    }

    public void setToRefreshDirectly() {
        setToRefreshDirectly(0);
    }

    public void setToRefreshDirectly(final long delay) {
        if (mTargetView != null) {
            postDelayed(new Runnable() {
                @Override
                public void run() {
                    setTargetViewToTop(mTargetView);
                    onRefresh();
                    mScrollFlag = FLAG_NEED_SCROLL_TO_REFRESH_POSITION;
                    invalidate();
                }
            }, delay);

        } else {
            mPendingRefreshDirectlyAction = new Runnable() {
                @Override
                public void run() {
                    setToRefreshDirectly(delay);
                }
            };
        }
    }


    public void setEnableOverPull(boolean enableOverPull) {
        mEnableOverPull = enableOverPull;
    }

    protected void setTargetViewToTop(View targetView) {
        if (targetView instanceof RecyclerView) {
            ((RecyclerView) targetView).scrollToPosition(0);
        } else if (targetView instanceof AbsListView) {
            AbsListView listView = (AbsListView) targetView;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                listView.setSelectionFromTop(0, 0);
            } else {
                listView.setSelection(0);
            }
        } else {
            targetView.scrollTo(0, 0);
        }
    }

    public void reset() {
        moveTargetViewTo(mTargetInitOffset, false);
        mIRefreshView.stop();
        mILoadView.stop();
        mIsRefreshing = false;
        mIsLoading = false;
        mScroller.forceFinished(true);
        mScrollFlag = 0;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        reset();
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        if (!enabled) {
            reset();
            invalidate();
        }
    }

    public boolean canChildScrollUp() {
        if (mChildScrollUpCallback != null) {
            return mChildScrollUpCallback.canChildScrollUp(this, mTargetView);
        }
        return defaultCanScrollUp(mTargetView);
    }

    public boolean canChildScrollDown() {
        if (mChildScrollUpCallback != null) {
            return mChildScrollUpCallback.canChildScrollDown(this, mTargetView);
        }
        return defaultCanScrollDown(mTargetView);
    }

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        info("onStartNestedScroll: nestedScrollAxes = " + nestedScrollAxes);
        return !mDisableNestScrollImpl && isEnabled() && (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    @Override
    public void onNestedScrollAccepted(View child, View target, int axes) {
        info("onNestedScrollAccepted: axes = " + axes);
        mScroller.abortAnimation();
        mNestedScrollingParentHelper.onNestedScrollAccepted(child, target, axes);
        mNestedScrollInProgress = true;
    }

    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        int parentCanConsume = mTargetCurrentOffset - mTargetInitOffset;

        if (parentCanConsume > 0 && !canChildScrollUp()) {
            mPullFlag = FLAG_IS_REFRESH;
            if (dy >= parentCanConsume) {
                consumed[1] = parentCanConsume;
                moveTargetViewTo(mTargetInitOffset, true);
            } else {
                consumed[1] = dy;
                moveTargetView(-dy * mDragRate, true);
            }
        } else if (parentCanConsume < 0 && !canChildScrollDown()) {
            mPullFlag = FLAG_IS_LOAD;
            if (dy <= parentCanConsume) {
                consumed[1] = parentCanConsume;
                moveTargetViewTo(mTargetInitOffset, true);
            } else {
                consumed[1] = dy;
                moveTargetView(-dy * mDragRate, true);
            }
        }
    }

    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        info("onNestedScroll: dxConsumed = " + dxConsumed + " ; dyConsumed = " + dyConsumed +
                " ; dxUnconsumed = " + dxUnconsumed + " ; dyUnconsumed = " + dyUnconsumed);
        if (dyUnconsumed < 0 && !canChildScrollUp() && mScroller.isFinished() && mScrollFlag == 0) {
            mPullFlag = FLAG_IS_REFRESH;
            moveTargetView(-dyUnconsumed * mDragRate, true);
        } else if (dyUnconsumed > 0 && !canChildScrollDown() && mScroller.isFinished() && mScrollFlag == 0) {
            mPullFlag = FLAG_IS_LOAD;
            moveTargetView(-dyUnconsumed * mDragRate, true);
        }
    }

    @Override
    public int getNestedScrollAxes() {
        return mNestedScrollingParentHelper.getNestedScrollAxes();
    }

    @Override
    public void onStopNestedScroll(View child) {
        info("onStopNestedScroll: mNestedScrollInProgress = " + mNestedScrollInProgress);
        mNestedScrollingParentHelper.onStopNestedScroll(child);
        if (mNestedScrollInProgress) {
            mNestedScrollInProgress = false;
            if (!mNestScrollDurationRefreshing) {
                if ((mPullFlag & FLAG_IS_REFRESH) == FLAG_IS_REFRESH) {
                    finishPullDown(0);
                } else if ((mPullFlag & FLAG_IS_LOAD) == FLAG_IS_LOAD) {
                    finishPullUp(0);
                }
            }
        }
    }

    @Override
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        info("onNestedPreFling: mTargetCurrentOffset = " + mTargetCurrentOffset +
                " ; velocityX = " + velocityX + " ; velocityY = " + velocityY);
        if (Math.abs(mTargetCurrentOffset) > mTargetInitOffset) {
            mNestedScrollInProgress = false;
            if (!mNestScrollDurationRefreshing) {
                if ((mPullFlag & FLAG_IS_REFRESH) == FLAG_IS_REFRESH) {
                    finishPullDown((int) -velocityY);
                } else if ((mPullFlag & FLAG_IS_LOAD) == FLAG_IS_LOAD) {
                    finishPullUp((int) -velocityY);
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        info("onNestedFling: velocityX = " + velocityX +
                " ; velocityY = " + velocityY + " ; consumed = " + consumed);
        try {
            return super.onNestedFling(target, velocityX, velocityY, consumed);
        } catch (Throwable e) {
            // android 24及以上ViewGroup会继续往上派发， 23以及以下直接返回false
            // 低于5.0的机器和RecyclerView配合工作时，部分机型会调用这个方法，但是ViewGroup并没有实现这个方法，会报错，这里catch一下
        }
        return false;
    }

    private int moveTargetView(float dy, boolean isDragging) {
        int target = (int) (mTargetCurrentOffset + dy);
        return moveTargetViewTo(target, isDragging);
    }

    private int moveTargetViewTo(int target, boolean isDragging) {
        return moveTargetViewTo(target, isDragging, false);
    }

    private int moveTargetViewTo(int target, boolean isDragging, boolean calculateAnyWay) {
        info("targetUp:" + target);
        if ((mPullFlag & FLAG_IS_REFRESH) == FLAG_IS_REFRESH) {
            target = calculateTargetOffset(target, mTargetInitOffset, mTargetRefreshOffset, mEnableOverPull);
            return moveTargetViewRefreshTo(target, isDragging, calculateAnyWay);
        } else if ((mPullFlag & FLAG_IS_LOAD) == FLAG_IS_LOAD) {
            target = calculateTargetDownOffset(target, mTargetInitOffset, mTargetLoadOffset, mEnableOverPull);
            return moveTargetViewLoadTo(target, isDragging, calculateAnyWay);
        }
        return 0;
    }

    private int moveTargetViewRefreshTo(int target, boolean isDragging, boolean calculateAnyWay) {
        int offset = 0;
        if (target != mTargetCurrentOffset || calculateAnyWay) {
            offset = target - mTargetCurrentOffset;
            ViewCompat.offsetTopAndBottom(mTargetView, offset);
            mTargetCurrentOffset = target;
            int total = mTargetRefreshOffset - mTargetInitOffset;
            if (isDragging) {
                mIRefreshView.onPull(Math.min(mTargetCurrentOffset - mTargetInitOffset, total), total,
                        mTargetCurrentOffset - mTargetRefreshOffset);
            }
            onMoveTargetView(mTargetCurrentOffset);
            if (mPullDownListener != null) {
                mPullDownListener.onMoveTarget(mTargetCurrentOffset);
            }

            if (mRefreshOffsetCalculator == null) {
                mRefreshOffsetCalculator = new UniDefaultRefreshOffsetCalculator();
            }
            int newRefreshOffset = mRefreshOffsetCalculator.calculateOffset(mRefreshInitOffset, mRefreshEndOffset, mRefreshView.getHeight(),
                    mTargetCurrentOffset, mTargetInitOffset, mTargetRefreshOffset);
            if (newRefreshOffset != mRefreshCurrentOffset) {
                ViewCompat.offsetTopAndBottom(mRefreshView, newRefreshOffset - mRefreshCurrentOffset);
                mRefreshCurrentOffset = newRefreshOffset;
                onMoveRefreshView(mRefreshCurrentOffset);
                if (mPullDownListener != null) {
                    mPullDownListener.onMoveRefreshView(mRefreshCurrentOffset);
                }
            }
        }
        if (target == 0 && !mIsRefreshing) {
            mScroller.forceFinished(true);
            mIRefreshView.stop();
        }
        return offset;
    }

    private int moveTargetViewLoadTo(int target, boolean isDragging, boolean calculateAnyWay) {
        int offset = 0;
        if (target != mTargetCurrentOffset || calculateAnyWay) {
            offset = target - mTargetCurrentOffset;
            ViewCompat.offsetTopAndBottom(mTargetView, offset);
            mTargetCurrentOffset = target;
            int total = mTargetLoadOffset - mTargetInitOffset;
            if (isDragging) {
                mILoadView.onPull(Math.min(-mTargetCurrentOffset - mTargetInitOffset, total), total,
                        -mTargetCurrentOffset - mTargetLoadOffset);
            }
            onMoveTargetView(mTargetCurrentOffset);
            if (mPullUpListener != null) {
                mPullUpListener.onMoveTarget(mTargetCurrentOffset);
            }

            if (mLoadOffsetCalculator == null) {
                mLoadOffsetCalculator = new UniDefaultLoadOffsetCalculator();
            }
            int newLoadOffset = mLoadOffsetCalculator.calculateOffset(mLoadInitOffset, mLoadEndOffset, mLoadView.getHeight(),
                    mTargetCurrentOffset, mTargetInitOffset, mTargetLoadOffset);
            if (newLoadOffset != mLoadCurrentOffset) {
                ViewCompat.offsetTopAndBottom(mLoadView, newLoadOffset - mLoadCurrentOffset);
                mLoadCurrentOffset = newLoadOffset;
                onMoveLoadView(mLoadCurrentOffset);
                if (mPullUpListener != null) {
                    mPullUpListener.onMoveLoadView(mLoadCurrentOffset);
                }
            }
        }
        if (target == 0) {
            mScroller.forceFinished(true);
            mILoadView.stop();
        }
        return offset;
    }


    protected int calculateTargetOffset(int target, int targetInitOffset, int targetRefreshOffset, boolean enableOverPull) {
        target = Math.max(target, targetInitOffset);
        if (!enableOverPull) {
            target = Math.min(target, targetRefreshOffset);
        }
        return target;
    }

    protected int calculateTargetDownOffset(int target, int targetInitOffset, int targetLoadOffset, boolean enableOverPull) {
        target = Math.min(target, targetInitOffset);
        if (!enableOverPull) {
            target = Math.max(target, targetLoadOffset);
        }
        return target;
    }

    public int getRefreshInitOffset() {
        return mRefreshInitOffset;
    }

    public int getLoadInitOffset() {
        return mLoadInitOffset;
    }

    public int getRefreshEndOffset() {
        return mRefreshEndOffset;
    }

    public int getLoadEndOffset() {
        return mLoadEndOffset;
    }

    public int getTargetInitOffset() {
        return mTargetInitOffset;
    }

    public int getTargetRefreshOffset() {
        return mTargetRefreshOffset;
    }

    public int getTargetLoadOffset() {
        return mTargetLoadOffset;
    }

    public void setTargetRefreshOffset(int targetRefreshOffset) {
        mEqualTargetRefreshOffsetToRefreshViewHeight = false;
        mTargetRefreshOffset = targetRefreshOffset;
    }

    public void setTargetLoadOffset(int targetLoadOffset) {
        mEqualTargetLoadOffsetToLoadViewHeight = false;
        mTargetLoadOffset = targetLoadOffset;
    }

    public View getTargetView() {
        return mTargetView;
    }

    protected void onMoveTargetView(int offset) {

    }

    protected void onMoveRefreshView(int offset) {

    }

    protected void onMoveLoadView(int offset) {

    }

    private boolean hasFlag(int flag) {
        return (mScrollFlag & flag) == flag;
    }

    private void removeFlag(int flag) {
        mScrollFlag = mScrollFlag & ~flag;
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            int offsetY = mScroller.getCurrY();
            moveTargetViewTo(offsetY, false);
            if (offsetY <= 0 && hasFlag(FLAG_NEED_DELIVER_VELOCITY)) {
                deliverVelocity();
                mScroller.forceFinished(true);
            }
            invalidate();
        } else if (hasFlag(FLAG_NEED_SCROLL_TO_INIT_POSITION)) {
            removeFlag(FLAG_NEED_SCROLL_TO_INIT_POSITION);
            if (mTargetCurrentOffset != mTargetInitOffset) {
                mScroller.startScroll(0, mTargetCurrentOffset, 0, mTargetInitOffset - mTargetCurrentOffset);
            }
            invalidate();
        } else if (hasFlag(FLAG_NEED_SCROLL_TO_REFRESH_POSITION)) {
            mPullFlag = FLAG_IS_REFRESH;
            removeFlag(FLAG_NEED_SCROLL_TO_REFRESH_POSITION);
            if (mTargetCurrentOffset != mTargetRefreshOffset) {
                mScroller.startScroll(0, mTargetCurrentOffset, 0, mTargetRefreshOffset - mTargetCurrentOffset);
            } else {
                moveTargetViewTo(mTargetRefreshOffset, false, true);
            }
            invalidate();
        } else if (hasFlag(FLAG_NEED_SCROLL_TO_LOAD_POSITION)) {
            mPullFlag = FLAG_IS_LOAD;
            removeFlag(FLAG_NEED_SCROLL_TO_LOAD_POSITION);
            if (mTargetCurrentOffset != mTargetLoadOffset) {
                mScroller.startScroll(0, mTargetCurrentOffset, 0, mTargetLoadOffset - mTargetCurrentOffset);
            } else {
                moveTargetViewTo(mTargetLoadOffset, false, true);
            }
            invalidate();
        } else if (hasFlag(FLAG_NEED_DO_REFRESH)) {
            removeFlag(FLAG_NEED_DO_REFRESH);
            onRefresh();
            moveTargetViewTo(mTargetRefreshOffset, false, true);
        } else if (hasFlag(FLAG_NEED_DO_LOAD)) {
            removeFlag(FLAG_NEED_DO_LOAD);
            onLoad();
            moveTargetViewTo(mTargetLoadOffset, false, true);
        } else {
            deliverVelocity();
        }
    }

    private void deliverVelocity() {
        if (hasFlag(FLAG_NEED_DELIVER_VELOCITY)) {
            removeFlag(FLAG_NEED_DELIVER_VELOCITY);
            if (mScroller.getCurrVelocity() > mMiniVelocity) {
                info("deliver velocity: " + mScroller.getCurrVelocity());
                // if there is a velocity, pass it on
                if (mTargetView instanceof RecyclerView) {
                    ((RecyclerView) mTargetView).fling(0, (int) mScroller.getCurrVelocity());
                } else if (mTargetView instanceof AbsListView && Build.VERSION.SDK_INT >= 21) {
                    ((AbsListView) mTargetView).fling((int) mScroller.getCurrVelocity());
                }
            }
        }
    }

    private void info(String msg) {
        if (BuildConfig.DEBUG) {
            Log.i(TAG, msg);
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        final int action = ev.getAction();
        if (action == MotionEvent.ACTION_DOWN) {
            mNestScrollDurationRefreshing = mIsRefreshing || (mScrollFlag & FLAG_NEED_DO_REFRESH) != 0;
        } else if (mNestScrollDurationRefreshing) {
            if (action == MotionEvent.ACTION_MOVE) {
                if (!mIsLoading && !mIsRefreshing && mScroller.isFinished() && mScrollFlag == 0) {
                    // 这里必须要 dispatch 一次 down 事件，否则不能触发 NestScroll，具体可参考 RecyclerView
                    // down 过程中会触发 onStopNestedScroll，mNestScrollDurationRefreshing 必须在之后
                    // 置为false，否则会触发 finishPullDown
                    ev.offsetLocation(0, -mSystemTouchSlop - 1);
                    ev.setAction(MotionEvent.ACTION_DOWN);
                    super.dispatchTouchEvent(ev);
                    mNestScrollDurationRefreshing = false;
                    ev.setAction(action);
                    // offset touch slop, 避免触发点击事件
                    ev.offsetLocation(0, mSystemTouchSlop + 1);
                }
            } else {
                mNestScrollDurationRefreshing = false;
            }
        }

        return super.dispatchTouchEvent(ev);
    }

    public interface OnPullRefreshListener {

        void onMoveTarget(int offset);

        void onMoveRefreshView(int offset);

        void onRefresh();
    }

    public interface OnPullLoadListener {

        void onMoveTarget(int offset);

        void onMoveLoadView(int offset);

        void onLoad();
    }


    public interface OnChildScrollUpCallback {
        boolean canChildScrollUp(UniPullRefreshLayout parent, @Nullable View child);

        boolean canChildScrollDown(UniPullRefreshLayout parent, @Nullable View child);
    }

    public interface OffsetCalculator {

        /**
         * 通过 targetView 的当前位置、targetView 的初始和刷新位置以及 refreshView 的初始与结束位置计算 RefreshView 的位置。
         *
         * @param refreshInitOffset   RefreshView 的初始 offset。
         * @param refreshEndOffset    刷新时 RefreshView 的 offset。
         * @param refreshViewHeight   RerfreshView 的高度
         * @param targetCurrentOffset 下拉时 TargetView（ListView 或者 ScrollView 等）当前的位置。
         * @param targetInitOffset    TargetView（ListView 或者 ScrollView 等）的初始位置。
         * @param targetRefreshOffset 刷新时 TargetView（ListView 或者 ScrollView等）的位置。
         * @return RefreshView 当前的位置。
         */
        int calculateOffset(int refreshInitOffset, int refreshEndOffset, int refreshViewHeight,
                            int targetCurrentOffset, int targetInitOffset, int targetRefreshOffset);
    }

    public interface IRefreshView {
        void preStop(String msg);

        void stop();

        void doRefresh();

        void onPull(int offset, int total, int overPull);
    }

    public interface ILoadView {
        void preStop(String msg);

        void stop();

        void doLoad();

        void onPull(int offset, int total, int overPull);
    }

    public class UniDefaultRefreshOffsetCalculator implements OffsetCalculator {
        @Override
        public int calculateOffset(int refreshInitOffset, int refreshEndOffset, int refreshViewHeight, int targetCurrentOffset, int targetInitOffset, int targetRefreshOffset) {
            int distance = targetRefreshOffset / 2 + refreshViewHeight / 2;
            int max = targetCurrentOffset - refreshViewHeight;
            return Math.min(max, targetCurrentOffset - distance);
        }
    }

    public class UniDefaultLoadOffsetCalculator implements OffsetCalculator {
        @Override
        public int calculateOffset(int refreshInitOffset, int refreshEndOffset, int refreshViewHeight, int targetCurrentOffset, int targetInitOffset, int targetRefreshOffset) {
            int distance = getHeight() - (targetRefreshOffset / 2 + refreshViewHeight / 2);
            int min = getHeight() + targetCurrentOffset;
            return Math.max(min, targetCurrentOffset + distance);
        }
    }

    public static class RefreshView extends AppCompatImageView implements IRefreshView, ILoadView {
        private static final int MAX_ALPHA = 255;
        private static final float TRIM_RATE = 0.85f;
        private static final float TRIM_OFFSET = 0.4f;

        static final int CIRCLE_DIAMETER = 40;
        static final int CIRCLE_DIAMETER_LARGE = 56;

        private CircularProgressDrawable mProgress;
        private int mCircleDiameter;

        public RefreshView(Context context) {
            super(context);
            mProgress = new CircularProgressDrawable(context);
            setColorSchemeColors(UniResHelper.getAttrColor(context, R.attr.uni_theme_color));
            mProgress.setStyle(CircularProgressDrawable.DEFAULT);
            mProgress.setAlpha(MAX_ALPHA);
            mProgress.setArrowScale(0.8f);
            setImageDrawable(mProgress);
            final DisplayMetrics metrics = getResources().getDisplayMetrics();
            mCircleDiameter = (int) (CIRCLE_DIAMETER * metrics.density);
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            setMeasuredDimension(mCircleDiameter, mCircleDiameter);
        }

        @Override
        public void onPull(int offset, int total, int overPull) {
            if (mProgress.isRunning()) {
                return;
            }
            float end = TRIM_RATE * offset / total;
            float rotate = TRIM_OFFSET * offset / total;
            if (overPull > 0) {
                rotate += TRIM_OFFSET * overPull / total;
            }
            mProgress.setArrowEnabled(true);
            mProgress.setStartEndTrim(0, end);
            mProgress.setProgressRotation(rotate);
        }

        public void setSize(@CircularProgressDrawable.ProgressDrawableSize int size) {
            if (size != CircularProgressDrawable.LARGE && size != CircularProgressDrawable.DEFAULT) {
                return;
            }
            final DisplayMetrics metrics = getResources().getDisplayMetrics();
            if (size == CircularProgressDrawable.LARGE) {
                mCircleDiameter = (int) (CIRCLE_DIAMETER_LARGE * metrics.density);
            } else {
                mCircleDiameter = (int) (CIRCLE_DIAMETER * metrics.density);
            }
            // force the bounds of the progress circle inside the circle view to
            // update by setting it to null before updating its size and then
            // re-setting it
            setImageDrawable(null);
            mProgress.setStyle(size);
            setImageDrawable(mProgress);
        }

        public void preStop(String msg) {
        }

        public void stop() {
            mProgress.stop();
        }

        public void doRefresh() {
            mProgress.start();
        }

        public void doLoad() {
            mProgress.start();
        }

        public void setColorSchemeResources(@ColorRes int... colorResIds) {
            final Context context = getContext();
            int[] colorRes = new int[colorResIds.length];
            for (int i = 0; i < colorResIds.length; i++) {
                colorRes[i] = ContextCompat.getColor(context, colorResIds[i]);
            }
            setColorSchemeColors(colorRes);
        }

        public void setColorSchemeColors(@ColorInt int... colors) {
            mProgress.setColorSchemeColors(colors);
        }
    }
}