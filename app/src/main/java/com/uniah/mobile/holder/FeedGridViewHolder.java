package com.uniah.mobile.holder;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.uniah.mobile.R;
import com.uniah.mobile.base.BaseViewHolder;
import com.uniah.mobile.util.UniDisplayHelper;
import com.uniah.mobile.view.UniRadiusView;

public class FeedGridViewHolder extends BaseViewHolder {

    public UniRadiusView mImg;
    private final static float totalWidthDp = 360;
    private final static float totalPaddingDp = 48;
    private final static float spacingDp = 3;

    public FeedGridViewHolder(Context context, View itemView) {
        super(context, itemView);
        mImg = itemView.findViewById(R.id.feed_grid_img);
    }

    public void setImgSize(Context context, int num) {
        if (num > 1) {
            ViewGroup.LayoutParams layoutParams = mImg.getLayoutParams();
            layoutParams.height = layoutParams.width = UniDisplayHelper.dp2px(context, (int) ((totalWidthDp - totalPaddingDp - (num - 1) * spacingDp) / num));
            mImg.setLayoutParams(layoutParams);
            mImg.setVisibility(View.VISIBLE);
        } else {
            mImg.setVisibility(View.GONE);
        }
    }

}
