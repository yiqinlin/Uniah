package com.uniah.mobile.holder;

import android.content.Context;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.uniah.mobile.R;
import com.uniah.mobile.base.BaseViewHolder;

public class GroupHintViewHolder extends BaseViewHolder {


    public TextView mHintText;

    public GroupHintViewHolder(Context context, View convertView) {
        super(context, convertView);
        mHintText = itemView.findViewById(R.id.item_group_hint);
    }
}
