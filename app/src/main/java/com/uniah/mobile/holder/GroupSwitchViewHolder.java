package com.uniah.mobile.holder;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.uniah.mobile.R;
import com.uniah.mobile.base.BaseViewHolder;
import com.uniah.mobile.layout.UniLinearLayout;

public class GroupSwitchViewHolder extends GroupBaseViewHolder {


    public TextView mText;
    public Switch mSwitch;

    public GroupSwitchViewHolder(Context context, View convertView) {
        super(context, convertView);

        mText = convertView.findViewById(R.id.item_group_text);

        mSwitch = convertView.findViewById(R.id.item_group_switch);
    }
}
