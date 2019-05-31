package com.uniah.mobile.holder;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uniah.mobile.R;
import com.uniah.mobile.base.BaseViewHolder;
import com.uniah.mobile.layout.UniFrameLayout;
import com.uniah.mobile.layout.UniLinearLayout;
import com.uniah.mobile.view.UniRadiusView;

public class MineHeadViewHolder extends BaseViewHolder {

    public UniLinearLayout mMineHeadItem;

    public LinearLayout mMineInfo;

    public UniRadiusView mHead;
    public TextView mNick;
    public TextView mSlogan;

    public UniFrameLayout mBtnLayout;
    public UniFrameLayout mSubBtnLayout;

    public LinearLayout mLeftInfo;
    public LinearLayout mMidInfo;
    public LinearLayout mRightInfo;

    public TextView mLeftInfoCount;
    public TextView mMidInfoCount;
    public TextView mRightInfoCount;

    public TextView mLeftInfoTitle;
    public TextView mMidInfoTitle;
    public TextView mRightInfoTitle;

    public MineHeadViewHolder(Context context, View convertView) {
        super(context, convertView);

        mMineHeadItem = convertView.findViewById(R.id.mine_head_item);

        mMineInfo = convertView.findViewById(R.id.mine_head_user_layout);

        mHead = convertView.findViewById(R.id.mine_head_user_head);
        mNick = convertView.findViewById(R.id.mine_head_user_nick);
        mSlogan = convertView.findViewById(R.id.mine_head_user_slogan);

        mBtnLayout = convertView.findViewById(R.id.mine_head_btn_layout);
        mSubBtnLayout = convertView.findViewById(R.id.mine_head_btn_sub_layout);

        mLeftInfo = convertView.findViewById(R.id.mine_head_info_left_layout);
        mMidInfo = convertView.findViewById(R.id.mine_head_info_mid_layout);
        mRightInfo = convertView.findViewById(R.id.mine_head_info_right_layout);

        mLeftInfoCount = convertView.findViewById(R.id.mine_head_info_left_count);
        mMidInfoCount = convertView.findViewById(R.id.mine_head_info_mid_count);
        mRightInfoCount = convertView.findViewById(R.id.mine_head_info_right_count);

        mLeftInfoTitle = convertView.findViewById(R.id.mine_head_info_left_title);
        mMidInfoTitle = convertView.findViewById(R.id.mine_head_info_mid_title);
        mRightInfoTitle = convertView.findViewById(R.id.mine_head_info_right_title);
    }
}
