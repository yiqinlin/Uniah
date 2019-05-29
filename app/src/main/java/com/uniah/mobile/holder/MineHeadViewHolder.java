package com.uniah.mobile.holder;

import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uniah.mobile.R;
import com.uniah.mobile.base.BaseViewHolder;
import com.uniah.mobile.layout.UniFrameLayout;
import com.uniah.mobile.layout.UniLinearLayout;
import com.uniah.mobile.view.UniRadiusView;

public class MineHeadViewHolder {

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

    public MineHeadViewHolder(BaseViewHolder holder) {

        mMineHeadItem = holder.findViewById(R.id.mine_head_item);

        mMineInfo = holder.findViewById(R.id.mine_head_user_layout);

        mHead = holder.findViewById(R.id.mine_head_user_head);
        mNick = holder.findViewById(R.id.mine_head_user_nick);
        mSlogan = holder.findViewById(R.id.mine_head_user_slogan);

        mBtnLayout = holder.findViewById(R.id.mine_head_btn_layout);
        mSubBtnLayout = holder.findViewById(R.id.mine_head_btn_sub_layout);

        mLeftInfo = holder.findViewById(R.id.mine_head_info_left_layout);
        mMidInfo = holder.findViewById(R.id.mine_head_info_mid_layout);
        mRightInfo = holder.findViewById(R.id.mine_head_info_right_layout);

        mLeftInfoCount = holder.findViewById(R.id.mine_head_info_left_count);
        mMidInfoCount = holder.findViewById(R.id.mine_head_info_mid_count);
        mRightInfoCount = holder.findViewById(R.id.mine_head_info_right_count);

        mLeftInfoTitle = holder.findViewById(R.id.mine_head_info_left_title);
        mMidInfoTitle = holder.findViewById(R.id.mine_head_info_mid_title);
        mRightInfoTitle = holder.findViewById(R.id.mine_head_info_right_title);
    }
}
