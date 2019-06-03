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

public class GroupTextViewHolder extends GroupBaseViewHolder {


    public TextView mText;
    public TextView mSubText;
    public UniFrameLayout mBtn;
    public UniFrameLayout mSubBtn;


    public GroupTextViewHolder(Context context, View convertView) {
        super(context, convertView);

        mText = convertView.findViewById(R.id.item_group_text);

        mBtn = convertView.findViewById(R.id.item_group_btn_layout);
        mSubText = convertView.findViewById(R.id.item_group_sub_text);
        mSubBtn = convertView.findViewById(R.id.item_group_btn_sub_layout);

    }
}
