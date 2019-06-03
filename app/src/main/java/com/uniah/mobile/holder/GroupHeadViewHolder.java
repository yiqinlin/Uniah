package com.uniah.mobile.holder;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.uniah.mobile.R;
import com.uniah.mobile.base.BaseViewHolder;
import com.uniah.mobile.layout.UniLinearLayout;
import com.uniah.mobile.view.UniRadiusView;

public class GroupHeadViewHolder extends GroupBaseViewHolder {

    public LinearLayout mHeadLayout;
    public UniRadiusView mHead;
    public TextView mHeadHint;

    public GroupHeadViewHolder(Context context, View convertView) {
        super(context, convertView);

        mHeadLayout = convertView.findViewById(R.id.item_group_head_layout);
        mHead = convertView.findViewById(R.id.item_group_head_img);
        mHeadHint = convertView.findViewById(R.id.item_group_head_hint);
    }
}
