package com.uniah.mobile.holder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.uniah.mobile.R;
import com.uniah.mobile.base.BaseViewHolder;

public class HintViewHolder extends BaseViewHolder {

    public TextView mHintText;

    public HintViewHolder(Context context, View itemView) {
        super(context, itemView);
        mHintText = itemView.findViewById(R.id.hint_text);
    }
}
