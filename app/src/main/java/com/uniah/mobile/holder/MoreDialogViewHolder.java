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

public class MoreDialogViewHolder extends BaseViewHolder {

    public LinearLayout mItem;
    public ImageView mIcon;
    public TextView mText;

    public MoreDialogViewHolder(Context context, View convertView) {
        super(context, convertView);

        mItem = convertView.findViewById(R.id.more_dialog_item);
        mIcon = convertView.findViewById(R.id.more_dialog_icon);
        mText = convertView.findViewById(R.id.more_dialog_text);
    }
}
