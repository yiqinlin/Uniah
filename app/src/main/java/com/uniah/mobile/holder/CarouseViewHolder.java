package com.uniah.mobile.holder;

import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.uniah.mobile.R;
import com.uniah.mobile.base.BaseViewHolder;
import com.uniah.mobile.view.UniRadiusView;
import com.uniah.mobile.view.UniViewPager;

public class CarouseViewHolder {

    public UniViewPager mViewPager;

    public View mLeftIndicator;
    public View mMidIndicator;
    public View mRightIndicator;

    public View mLeftCarousePage;
    public View mMidCarousePage;
    public View mRightCarousePage;

    public UniRadiusView mLeftCarouseImg;
    public UniRadiusView mMidCarouseImg;
    public UniRadiusView mRightCarouseImg;

    public CarouseViewHolder(BaseViewHolder holder) {

        mViewPager = holder.findViewById(R.id.carousel_viewPager);
        mLeftIndicator = holder.findViewById(R.id.indicator_left);
        mMidIndicator = holder.findViewById(R.id.indicator_mid);
        mRightIndicator = holder.findViewById(R.id.indicator_right);

        mLeftCarousePage = holder.inflate(R.layout.carousel_page, null);
        mMidCarousePage = holder.inflate(R.layout.carousel_page, null);
        mRightCarousePage = holder.inflate(R.layout.carousel_page, null);

        mLeftCarouseImg = mLeftCarousePage.findViewById(R.id.carousel_img);
        mMidCarouseImg = mMidCarousePage.findViewById(R.id.carousel_img);
        mRightCarouseImg = mRightCarousePage.findViewById(R.id.carousel_img);
    }
}
