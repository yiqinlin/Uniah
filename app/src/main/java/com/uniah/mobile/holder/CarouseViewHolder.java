package com.uniah.mobile.holder;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.uniah.mobile.R;
import com.uniah.mobile.base.BaseViewHolder;
import com.uniah.mobile.view.UniRadiusView;
import com.uniah.mobile.view.UniViewPager;

public class CarouseViewHolder extends BaseViewHolder {


    public LayoutInflater mInflater;

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

    public CarouseViewHolder(Context context, View convertView) {
        super(context, convertView);
        mInflater = LayoutInflater.from(context);
        mViewPager = convertView.findViewById(R.id.carousel_viewPager);
        mLeftIndicator = convertView.findViewById(R.id.indicator_left);
        mMidIndicator = convertView.findViewById(R.id.indicator_mid);
        mRightIndicator = convertView.findViewById(R.id.indicator_right);

        mLeftCarousePage = mInflater.inflate(R.layout.carousel_page, null);
        mMidCarousePage = mInflater.inflate(R.layout.carousel_page, null);
        mRightCarousePage = mInflater.inflate(R.layout.carousel_page, null);

        mLeftCarouseImg = mLeftCarousePage.findViewById(R.id.carousel_img);
        mMidCarouseImg = mMidCarousePage.findViewById(R.id.carousel_img);
        mRightCarouseImg = mRightCarousePage.findViewById(R.id.carousel_img);
    }
}
