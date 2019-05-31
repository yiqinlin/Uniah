package com.uniah.mobile.bean;

import android.content.Context;
import android.view.View;

import com.uniah.mobile.R;
import com.uniah.mobile.base.BaseData;
import com.uniah.mobile.base.BaseViewHolder;
import com.uniah.mobile.holder.CarouseViewHolder;
import com.uniah.mobile.util.UniDateHelper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class CarouselData extends BaseData implements Serializable {

    private Map<Integer, String> images;
    private Map<Integer, String> urls;

    public CarouselData() {
    }

    @Override
    public BaseViewHolder getViewHolder(Context context, View convertView) {
        return new CarouseViewHolder(context, convertView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.carousel;
    }

    public String getImg(int index){
        return images.get(index);
    }

    public String getUrl(int index){
        return urls.get(index);
    }

    public Map<Integer, String> getImages() {
        return images;
    }

    public void setImages(Map<Integer, String> images) {
        this.images = images;
    }

    public Map<Integer, String> getUrls() {
        return urls;
    }

    public void setUrls(Map<Integer, String> urls) {
        this.urls = urls;
    }
}
