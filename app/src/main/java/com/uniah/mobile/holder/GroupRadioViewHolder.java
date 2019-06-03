package com.uniah.mobile.holder;

import android.content.Context;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;

import com.uniah.mobile.R;
import com.uniah.mobile.base.BaseViewHolder;
import com.uniah.mobile.layout.UniLinearLayout;

public class GroupRadioViewHolder extends GroupBaseViewHolder {


    public TextView mText;
    public RadioButton mRadio;

    public GroupRadioViewHolder(Context context, View convertView) {
        super(context, convertView);

        mItem = convertView.findViewById(R.id.item_group_item);
        mText = convertView.findViewById(R.id.item_group_text);
        mLine = convertView.findViewById(R.id.item_group_line);

        mRadio = convertView.findViewById(R.id.item_group_radio);
    }
}
