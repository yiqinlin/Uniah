package com.uniah.mobile.bean;

import android.content.Context;
import android.view.View;

import com.uniah.mobile.R;
import com.uniah.mobile.base.BaseData;
import com.uniah.mobile.base.BaseViewHolder;
import com.uniah.mobile.holder.FeedGridViewHolder;

public class FeedGridData extends BaseData {

    private String imgUrl;

    @Override
    public BaseViewHolder getViewHolder(Context context, View convertView) {
        return new FeedGridViewHolder(context, convertView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.feed_grid_img;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String mImgUrl) {
        this.imgUrl = mImgUrl;
    }
}
