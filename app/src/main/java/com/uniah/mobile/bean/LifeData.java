package com.uniah.mobile.bean;

import android.content.Context;
import android.view.View;

import com.uniah.mobile.R;
import com.uniah.mobile.base.BaseData;
import com.uniah.mobile.base.BaseViewHolder;
import com.uniah.mobile.holder.LifeViewHolder;

import java.io.Serializable;
import java.util.Map;

public class LifeData extends BaseData implements Serializable {

    private String title;
    private String img;
    private String url;

    public LifeData() {
    }

    @Override
    public BaseViewHolder getViewHolder(Context context, View convertView) {
        return new LifeViewHolder(context, convertView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.life_item;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
