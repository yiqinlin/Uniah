package com.uniah.mobile.bean;

import android.view.View;

import com.uniah.mobile.base.BaseData;
import com.uniah.mobile.imagebrowserlibrary.listeners.OnClickListener;
import com.uniah.mobile.layout.IUniLayout;

public abstract class GroupBaseData extends BaseData {

    private View.OnClickListener onItemClickListener;
    private int hideRadiusSide;
    private boolean isLastItem;
    private boolean lineNone;

    public View.OnClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(View.OnClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public int getHideRadiusSide() {
        return hideRadiusSide;
    }

    public void setHideRadiusSide(int hideRadiusSide) {
        this.hideRadiusSide = hideRadiusSide;
    }

    public boolean isLastItem() {
        return isLastItem;
    }

    public void setLastItem(boolean lastItem) {
        isLastItem = lastItem;
    }

    public boolean isLineNone() {
        return lineNone;
    }

    public void setLineNone(boolean lineNone) {
        this.lineNone = lineNone;
    }
}
