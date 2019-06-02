package com.uniah.mobile.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uniah.mobile.R;
import com.uniah.mobile.base.BaseViewHolder;
import com.uniah.mobile.layout.UniFrameLayout;
import com.uniah.mobile.layout.UniLinearLayout;
import com.uniah.mobile.view.UniRadiusView;

public class UserInfoViewHolder extends BaseViewHolder {


    public LinearLayout mItem;

    public UniRadiusView mHead;
    public TextView mNick;
    public TextView mFlag;

    public UniFrameLayout mBtnLayout;
    public ImageView mBtnImg;


    public UserInfoViewHolder(Context context, View convertView) {
        super(context, convertView);

        mItem = convertView.findViewById(R.id.user_info);

        mHead = convertView.findViewById(R.id.user_info_head);
        mNick = convertView.findViewById(R.id.user_info_nick);
        mFlag = convertView.findViewById(R.id.user_info_flag);

        mBtnLayout = convertView.findViewById(R.id.user_info_btn_layout);
        mBtnImg = convertView.findViewById(R.id.user_info_btn_img);
    }
}
