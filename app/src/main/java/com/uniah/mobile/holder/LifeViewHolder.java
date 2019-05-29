package com.uniah.mobile.holder;

import android.widget.TextView;

import com.uniah.mobile.R;
import com.uniah.mobile.base.BaseViewHolder;
import com.uniah.mobile.view.UniRadiusView;

public class LifeViewHolder {

    public UniRadiusView mBackgroundImg;
    public TextView mTitle;

    public LifeViewHolder(BaseViewHolder holder) {
        mBackgroundImg = holder.findViewById(R.id.life_img);
        mTitle = holder.findViewById(R.id.life_title);
    }
}
