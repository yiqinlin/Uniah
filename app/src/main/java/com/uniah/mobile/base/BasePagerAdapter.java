package com.uniah.mobile.base;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Stark on 2018/2/13.
 */

public class BasePagerAdapter extends PagerAdapter {
    private List<View> mListViews;

    public BasePagerAdapter(List<View> mListViews) {
        this.mListViews = mListViews;
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        // TODO Auto-generated method stub
        return arg0 == arg1;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mListViews.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // TODO Auto-generated method stub
        container.removeView(mListViews.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // TODO Auto-generated method stub
        container.addView(mListViews.get(position));
        return mListViews.get(position);
    }
}