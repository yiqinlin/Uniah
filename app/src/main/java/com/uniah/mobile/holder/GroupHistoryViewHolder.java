package com.uniah.mobile.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uniah.mobile.R;
import com.uniah.mobile.base.BaseViewHolder;
import com.uniah.mobile.layout.UniFrameLayout;
import com.uniah.mobile.view.UniRadiusView;

public class GroupHistoryViewHolder extends GroupBaseViewHolder {


    public UniRadiusView mHead;
    public TextView mNick;
    public TextView mFlag;

    public UniFrameLayout mBtnLayout;
    public ImageView mBtnImg;


    public GroupHistoryViewHolder(Context context, View convertView) {
        super(context, convertView);


        mHead = convertView.findViewById(R.id.item_group_history_head);
        mNick = convertView.findViewById(R.id.item_group_history_nick);
        mFlag = convertView.findViewById(R.id.item_group_history_flag);

        mBtnLayout = convertView.findViewById(R.id.item_group_history_btn_layout);
        mBtnImg = convertView.findViewById(R.id.item_group_history_btn_img);
    }
}
