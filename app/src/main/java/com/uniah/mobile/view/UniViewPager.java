package com.uniah.mobile.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.uniah.mobile.base.BasePagerAdapter;

import java.lang.ref.WeakReference;

public class UniViewPager extends ViewPager {

    boolean isScrollable = true;

    // 默认滚动间隔时间
    private static final int DEFAULT_SLIDE_INTERVAL = 3000;
    // 自动滚动间隔时间
    private int slideDuration = DEFAULT_SLIDE_INTERVAL;

    // 滑动方向向右
    public static final int DIRECTION_RIGHT = 1;
    // 向左滑动
    public static final int DIRECTION_LEFT = 0;
    // 滑动方向
    private int direction = DIRECTION_RIGHT;
    // 触摸时停止滚动
    private boolean stopWhenTouch = true;

    // 滚动消息
    private static final int MSG_SCROLL = 1;

    // 是否是自动滚动
    private boolean isAutoScroll = false;
    // 是否已经触摸停止滚动
    private boolean isStopWhenTouch;

    private AutoScrollHandler mHandler;

    public UniViewPager(@NonNull Context context) {
        super(context);
    }

    public UniViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mHandler = new AutoScrollHandler(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return isScrollable && super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return isScrollable && super.onInterceptTouchEvent(ev);
    }

    public void setScrollable(boolean scrollable) {
        this.isScrollable = scrollable;
    }

    public int getDirection() {
        return direction;
    }

    /**
     * 设置滑动方向
     *
     * @param direction
     */
    public void setDirection(int direction) {
        this.direction = direction;
    }

    public boolean isStopWhenTouch() {
        return stopWhenTouch;
    }

    /**
     * 触摸时停止滑动
     *
     * @param stopWhenTouch
     */
    public void setStopWhenTouch(boolean stopWhenTouch) {
        this.stopWhenTouch = stopWhenTouch;
    }

    /**
     * 设置滑动时间
     *
     * @param slideDuration
     */
    public void setSlideDuration(int slideDuration) {
        this.slideDuration = slideDuration;
    }

    /**
     * ViewPager自动滚动方法
     */
    private void scroll() {
        PagerAdapter adapter = getAdapter();
        int currentItem = getCurrentItem();
        int totalCount = adapter == null ? 0 : adapter.getCount();
        if (totalCount <= 1) {
            return;
        }
        int nextItem = (direction == DIRECTION_RIGHT) ? (++currentItem + totalCount) % totalCount : (--currentItem + totalCount) % totalCount;
        setCurrentItem(nextItem, true);
    }

    /**
     * 开始自动滚动
     */
    public void startAutoScroll() {
        startAutoScroll(slideDuration);
    }

    /**
     * 开启自动滚动
     *
     * @param delayTime 滚动间隔时间，会覆盖之前设置的间隔时间。
     */
    public void startAutoScroll(int delayTime) {
        isAutoScroll = true;
        slideDuration = delayTime;
        sendScrollMsg(delayTime);
    }

    /**
     * 停止自动滚动
     */
    public void stopAutoScroll() {
        isAutoScroll = false;
        mHandler.removeMessages(MSG_SCROLL);
    }

    /**
     * 发送滚动消息
     */
    private void sendScrollMsg(long delayTime) {
        mHandler.removeMessages(MSG_SCROLL);
        mHandler.sendEmptyMessageDelayed(MSG_SCROLL, delayTime);
    }

    /**
     * 控制自动滚动的handler
     */
    private static class AutoScrollHandler extends Handler {
        private WeakReference<UniViewPager> hostWeakReference;

        public AutoScrollHandler(UniViewPager host) {
            hostWeakReference = new WeakReference<UniViewPager>(host);
        }

        @Override
        public void handleMessage(Message msg) {
            UniViewPager host = hostWeakReference.get();
            if (host != null) {
                host.scroll();
                host.sendScrollMsg(host.slideDuration);
            }
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int action = MotionEventCompat.getActionMasked(ev);
        if (stopWhenTouch) {
            switch (action) {
                case MotionEvent.ACTION_DOWN:
                    if (isAutoScroll) {
                        isStopWhenTouch = true;
                        stopAutoScroll();
                    }
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_OUTSIDE:
                    if (isStopWhenTouch) {
                        startAutoScroll();
                    }
                    break;
            }
        }
        return super.dispatchTouchEvent(ev);
    }

}
