package com.uniah.mobile.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.uniah.mobile.R;
import com.uniah.mobile.base.BaseViewHolder;
import com.uniah.mobile.bean.GroupBaseData;
import com.uniah.mobile.layout.UniFrameLayout;
import com.uniah.mobile.layout.UniLinearLayout;
import com.uniah.mobile.view.UniRadiusView;

public class GroupUserViewHolder extends GroupBaseViewHolder {

    public UniRadiusView mHead;
    public TextView mNick;
    public TextView mFlag;
    public TextView mSlogan;

    public UniFrameLayout mSubBtn;
    public ImageView mSubImg;

    public UniFrameLayout mArrow;


    public GroupUserViewHolder(Context context, View convertView) {
        super(context, convertView);
        mHead = convertView.findViewById(R.id.item_group_user_head);
        mNick = convertView.findViewById(R.id.item_group_user_name);
        mFlag = convertView.findViewById(R.id.item_group_user_flag);
        mSlogan = convertView.findViewById(R.id.item_group_user_slogan);

        mSubBtn = convertView.findViewById(R.id.item_group_user_sub_img_layout);
        mSubImg = convertView.findViewById(R.id.item_group_user_sub_img);
        mArrow = convertView.findViewById(R.id.item_group_user_arrow);
    }
}
