package com.uniah.mobile.holder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.uniah.mobile.R;
import com.uniah.mobile.base.BaseViewHolder;
import com.uniah.mobile.view.UniRadiusView;

public class LifeViewHolder extends BaseViewHolder {

    public UniRadiusView mBackgroundImg;
    public TextView mTitle;

    public LifeViewHolder(Context context, View convertView) {
        super(context, convertView);
        mBackgroundImg = convertView.findViewById(R.id.life_img);
        mTitle = convertView.findViewById(R.id.life_title);
    }
}
