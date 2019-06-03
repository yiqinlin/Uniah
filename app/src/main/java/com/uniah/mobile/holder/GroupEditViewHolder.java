package com.uniah.mobile.holder;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.uniah.mobile.R;
import com.uniah.mobile.base.BaseViewHolder;
import com.uniah.mobile.layout.UniFrameLayout;
import com.uniah.mobile.layout.UniLinearLayout;

public class GroupEditViewHolder extends GroupBaseViewHolder {


    public TextView mText;
    public EditText mEdit;

    public GroupEditViewHolder(Context context, View convertView) {
        super(context, convertView);

        mText = convertView.findViewById(R.id.item_group_text);
        mEdit = convertView.findViewById(R.id.item_group_edit);
    }
}
