package com.uniah.mobile.holder;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.uniah.mobile.R;
import com.uniah.mobile.base.BaseViewHolder;
import com.uniah.mobile.layout.UniLinearLayout;

public class GroupBaseViewHolder extends BaseViewHolder {


    public UniLinearLayout mItem;
    public View mLine;


    public GroupBaseViewHolder(Context context, View convertView) {
        super(context, convertView);

        mItem = convertView.findViewById(R.id.item_group_item);
        mLine = convertView.findViewById(R.id.item_group_line);
    }
}
