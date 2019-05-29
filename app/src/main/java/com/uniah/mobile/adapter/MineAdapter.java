package com.uniah.mobile.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Toast;

import com.uniah.mobile.base.BaseAdapter;
import com.uniah.mobile.base.BaseData;
import com.uniah.mobile.base.BaseViewHolder;
import com.uniah.mobile.bean.MineHeadData;
import com.uniah.mobile.holder.MineHeadViewHolder;
import com.uniah.mobile.util.UniDisplayHelper;
import com.uniah.mobile.util.UniImageHelper;
import com.uniah.mobile.util.UniStatusBarHelper;

import java.util.List;

public class MineAdapter extends BaseAdapter<BaseData> {

    Context mContext;
    Activity mActivity;
    MineHeadViewHolder viewHolder;


    public MineAdapter(Context context, List<BaseData> list) {
        super(context, list);
        this.mContext = context;
        if (mContext instanceof Activity) {
            this.mActivity = (Activity) mContext;
        }
    }

    @Override
    public void convert(BaseViewHolder holder, int position, BaseData item) {
        if (item instanceof MineHeadData) {
            onBindMineHead(holder, position, item);
        }
    }

    private void onBindMineHead(BaseViewHolder holder, int position, BaseData item) {

        MineHeadData data = (MineHeadData) item;
        viewHolder = new MineHeadViewHolder(holder);

        int dp9 = UniDisplayHelper.dp2px(mContext, 9);
        int dp2 = UniDisplayHelper.dp2px(mContext, 2);
        viewHolder.mMineHeadItem.setRadiusAndShadow(dp9, dp2, 0.1f);

        UniImageHelper.displayImage(mContext, data.getUserHead(), viewHolder.mHead);

        int statusBar = UniStatusBarHelper.getTranslucentHeight(mActivity);
        LayoutParams lp = (LayoutParams) viewHolder.mMineHeadItem.getLayoutParams();
        lp.topMargin = statusBar;
        viewHolder.mMineHeadItem.setLayoutParams(lp);


        viewHolder.mNick.setText(data.getUserNick());
        viewHolder.mSlogan.setText(data.getUserSlogan());

        viewHolder.mLeftInfoCount.setText(String.valueOf(data.getLeftCount()));
        viewHolder.mMidInfoCount.setText(String.valueOf(data.getMidCount()));
        viewHolder.mRightInfoCount.setText(String.valueOf(data.getRightCount()));

        viewHolder.mLeftInfoTitle.setText(data.getLeftTitle());
        viewHolder.mMidInfoTitle.setText(data.getMidTitle());
        viewHolder.mRightInfoTitle.setText(data.getRightTitle());

        viewHolder.mMineInfo.setOnClickListener(mUserClickListener);
        viewHolder.mBtnLayout.setOnClickListener(mBtnClickListener);
        viewHolder.mSubBtnLayout.setOnClickListener(mSubBtnClickListener);

        viewHolder.mLeftInfo.setOnClickListener(mLeftInfoClickListener);
        viewHolder.mMidInfo.setOnClickListener(mMidClickListener);
        viewHolder.mRightInfo.setOnClickListener(mRightInfoClickListener);
    }

    private View.OnClickListener mUserClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(mContext, "click user", Toast.LENGTH_SHORT).show();
        }
    };


    private View.OnClickListener mBtnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(mContext, "click btn", Toast.LENGTH_SHORT).show();
        }
    };

    private View.OnClickListener mSubBtnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(mContext, "click sub btn", Toast.LENGTH_SHORT).show();
        }
    };

    private View.OnClickListener mLeftInfoClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(mContext, "click left info", Toast.LENGTH_SHORT).show();
        }
    };

    private View.OnClickListener mMidClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(mContext, "click mid info", Toast.LENGTH_SHORT).show();
        }
    };

    private View.OnClickListener mRightInfoClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(mContext, "click right info", Toast.LENGTH_SHORT).show();
        }
    };

}
