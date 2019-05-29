package com.uniah.mobile.holder;

import android.media.Image;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uniah.mobile.R;
import com.uniah.mobile.base.BaseViewHolder;
import com.uniah.mobile.view.UniRadiusView;

public class MsgListHeadViewHolder {

    public LinearLayout mLeft;
    public View mLeftBackground;
    public ImageView mLeftImg;
    public TextView mLeftText;
    public TextView mLeftCount;

    public LinearLayout mMid;
    public View mMidBackground;
    public ImageView mMidImg;
    public TextView mMidText;
    public TextView mMidCount;

    public LinearLayout mRight;
    public View mRightBackground;
    public ImageView mRightImg;
    public TextView mRightText;
    public TextView mRightCount;

    public MsgListHeadViewHolder(BaseViewHolder holder) {
        mLeft = holder.findViewById(R.id.msg_list_head_left);
        mLeftBackground = holder.findViewById(R.id.msg_list_head_left_img_bg);
        mLeftImg = holder.findViewById(R.id.msg_list_head_left_img);
        mLeftText = holder.findViewById(R.id.msg_list_head_left_text);
        mLeftCount = holder.findViewById(R.id.msg_list_head_left_count);

        mMid = holder.findViewById(R.id.msg_list_head_mid);
        mMidBackground = holder.findViewById(R.id.msg_list_head_mid_img_bg);
        mMidImg = holder.findViewById(R.id.msg_list_head_mid_img);
        mMidText = holder.findViewById(R.id.msg_list_head_mid_text);
        mMidCount = holder.findViewById(R.id.msg_list_head_mid_count);

        mRight = holder.findViewById(R.id.msg_list_head_right);
        mRightBackground = holder.findViewById(R.id.msg_list_head_right_img_bg);
        mRightImg = holder.findViewById(R.id.msg_list_head_right_img);
        mRightText = holder.findViewById(R.id.msg_list_head_right_text);
        mRightCount = holder.findViewById(R.id.msg_list_head_right_count);
    }
}
