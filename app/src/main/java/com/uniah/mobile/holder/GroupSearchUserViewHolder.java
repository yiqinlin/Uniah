package com.uniah.mobile.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.uniah.mobile.R;
import com.uniah.mobile.layout.UniFrameLayout;
import com.uniah.mobile.view.UniRadiusView;

public class GroupSearchUserViewHolder extends GroupBaseViewHolder {

    public UniRadiusView mHead;
    public TextView mNick;
    public TextView mFlag;
    public TextView mSlogan;

    public TextView mBtn;

    public GroupSearchUserViewHolder(Context context, View convertView) {
        super(context, convertView);
        mHead = convertView.findViewById(R.id.item_group_user_head);
        mNick = convertView.findViewById(R.id.item_group_user_name);
        mFlag = convertView.findViewById(R.id.item_group_user_flag);
        mSlogan = convertView.findViewById(R.id.item_group_user_slogan);

        mBtn = convertView.findViewById(R.id.item_group_btn);
    }
}
