package com.uniah.mobile.adapter;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.uniah.mobile.base.BaseAdapter;
import com.uniah.mobile.base.BaseData;
import com.uniah.mobile.base.BasePagerAdapter;
import com.uniah.mobile.base.BaseViewHolder;
import com.uniah.mobile.bean.CarouselData;
import com.uniah.mobile.bean.LifeData;
import com.uniah.mobile.holder.CarouseViewHolder;
import com.uniah.mobile.holder.LifeViewHolder;
import com.uniah.mobile.util.UniColorHelper;
import com.uniah.mobile.util.UniImageHelper;

import java.util.ArrayList;
import java.util.List;

public class LifeAdapter extends BaseAdapter<BaseData> {

    private Context mContext;
    private CarouseViewHolder viewHolder;

    private float maxIndicatorScale = 1f;
    private float minIndicatorScale = 0.8f;

    private float maxAlpha = 1f;
    private float minAlpha = 0.5f;

    private int page;

    public LifeAdapter(Context context, List<BaseData> list) {
        super(context, list);
        this.mContext = context;
    }

    @Override
    public void convert(BaseViewHolder holder, int position, BaseData item) {
        if (item instanceof CarouselData) {
            CarouselData data = (CarouselData) item;
            viewHolder = new CarouseViewHolder(holder);

            final List<View> viewList = new ArrayList<View>();
            viewList.add(viewHolder.mLeftCarousePage);
            viewList.add(viewHolder.mMidCarousePage);
            viewList.add(viewHolder.mRightCarousePage);
            viewHolder.mViewPager.setAdapter(new BasePagerAdapter(viewList));

            UniImageHelper.displayImage(mContext, data.getImg(0), viewHolder.mLeftCarouseImg);
            UniImageHelper.displayImage(mContext, data.getImg(1), viewHolder.mMidCarouseImg);
            UniImageHelper.displayImage(mContext, data.getImg(2), viewHolder.mRightCarouseImg);

            setPage(0);
            viewHolder.mViewPager.addOnPageChangeListener(mPageChangeListener);
            viewHolder.mViewPager.startAutoScroll();
        } else if (item instanceof LifeData) {
            LifeData data = (LifeData) item;
            LifeViewHolder viewHolder = new LifeViewHolder(holder);

            UniImageHelper.displayImage(mContext,data.getImg(),viewHolder.mBackgroundImg);
            viewHolder.mTitle.setText(data.getTitle());
        }
    }

    private ViewPager.OnPageChangeListener mPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            float scaleOffset = (maxIndicatorScale - minIndicatorScale) * positionOffset;
            float colorOffset = (maxAlpha - minAlpha) * positionOffset;
            switch (position) {
                case 0:
                    viewHolder.mLeftIndicator.setScaleX(maxIndicatorScale - scaleOffset);
                    viewHolder.mLeftIndicator.setScaleY(maxIndicatorScale - scaleOffset);
                    viewHolder.mMidIndicator.setScaleX(minIndicatorScale + scaleOffset);
                    viewHolder.mMidIndicator.setScaleY(minIndicatorScale + scaleOffset);
                    viewHolder.mLeftIndicator.setAlpha(maxAlpha - colorOffset);
                    viewHolder.mMidIndicator.setAlpha(minAlpha + colorOffset);
                    break;
                case 1:
                    viewHolder.mMidIndicator.setScaleX(maxIndicatorScale - scaleOffset);
                    viewHolder.mMidIndicator.setScaleY(maxIndicatorScale - scaleOffset);
                    viewHolder.mRightIndicator.setScaleX(minIndicatorScale + scaleOffset);
                    viewHolder.mRightIndicator.setScaleY(minIndicatorScale + scaleOffset);
                    viewHolder.mMidIndicator.setAlpha(maxAlpha - colorOffset);
                    viewHolder.mRightIndicator.setAlpha(minAlpha + colorOffset);
                    break;
            }
        }

        @Override
        public void onPageSelected(int position) {
            page = position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            if (state == 0) {
                setPage(page);
            }
        }
    };

    private void setPage(int index) {
        viewHolder.mLeftIndicator.setScaleX(index == 0 ? maxIndicatorScale : minIndicatorScale);
        viewHolder.mLeftIndicator.setScaleY(index == 0 ? maxIndicatorScale : minIndicatorScale);
        viewHolder.mMidIndicator.setScaleX(index == 1 ? maxIndicatorScale : minIndicatorScale);
        viewHolder.mMidIndicator.setScaleY(index == 1 ? maxIndicatorScale : minIndicatorScale);
        viewHolder.mRightIndicator.setScaleX(index == 2 ? maxIndicatorScale : minIndicatorScale);
        viewHolder.mRightIndicator.setScaleY(index == 2 ? maxIndicatorScale : minIndicatorScale);

        viewHolder.mLeftIndicator.setAlpha(index == 0 ? maxAlpha : minAlpha);
        viewHolder.mMidIndicator.setAlpha(index == 1 ? maxAlpha : minAlpha);
        viewHolder.mRightIndicator.setAlpha(index == 2 ? maxAlpha : minAlpha);
    }

}
