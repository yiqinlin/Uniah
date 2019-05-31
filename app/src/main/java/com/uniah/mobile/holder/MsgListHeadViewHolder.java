package com.uniah.mobile.holder;

import android.content.Context;
import android.media.Image;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uniah.mobile.R;
import com.uniah.mobile.base.BaseViewHolder;
import com.uniah.mobile.view.UniRadiusView;

public class MsgListHeadViewHolder extends BaseViewHolder {

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

    public MsgListHeadViewHolder(Context context, View convertView) {
        super(context, convertView);

        mLeft = convertView.findViewById(R.id.msg_list_head_left);
        mLeftBackground = convertView.findViewById(R.id.msg_list_head_left_img_bg);
        mLeftImg = convertView.findViewById(R.id.msg_list_head_left_img);
        mLeftText = convertView.findViewById(R.id.msg_list_head_left_text);
        mLeftCount = convertView.findViewById(R.id.msg_list_head_left_count);

        mMid = convertView.findViewById(R.id.msg_list_head_mid);
        mMidBackground = convertView.findViewById(R.id.msg_list_head_mid_img_bg);
        mMidImg = convertView.findViewById(R.id.msg_list_head_mid_img);
        mMidText = convertView.findViewById(R.id.msg_list_head_mid_text);
        mMidCount = convertView.findViewById(R.id.msg_list_head_mid_count);

        mRight = convertView.findViewById(R.id.msg_list_head_right);
        mRightBackground = convertView.findViewById(R.id.msg_list_head_right_img_bg);
        mRightImg = convertView.findViewById(R.id.msg_list_head_right_img);
        mRightText = convertView.findViewById(R.id.msg_list_head_right_text);
        mRightCount = convertView.findViewById(R.id.msg_list_head_right_count);
    }
}
