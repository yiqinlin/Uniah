package com.uniah.mobile.bean;

import android.content.Context;
import android.view.View;

import com.uniah.mobile.R;
import com.uniah.mobile.base.BaseData;
import com.uniah.mobile.base.BaseViewHolder;
import com.uniah.mobile.holder.MineHeadViewHolder;
import com.uniah.mobile.view.UniRadiusView;

import java.io.Serializable;
import java.util.Map;

public class MineHeadData extends BaseData implements Serializable {

    private String userHead;
    private String userNick;
    private String userSlogan;

    private int leftCount;
    private String leftTitle;

    private int midCount;
    private String midTitle;

    private int rightCount;
    private String rightTitle;

    public MineHeadData() {
    }

    @Override
    public BaseViewHolder getViewHolder(Context context, View convertView) {
        return new MineHeadViewHolder(context, convertView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.mine_head_item;
    }

    public String getUserHead() {
        return userHead;
    }

    public void setUserHead(String userHead) {
        this.userHead = userHead;
    }

    public String getUserNick() {
        return userNick;
    }

    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }

    public String getUserSlogan() {
        return userSlogan;
    }

    public void setUserSlogan(String userSlogan) {
        this.userSlogan = userSlogan;
    }

    public int getLeftCount() {
        return leftCount;
    }

    public void setLeftCount(int leftCount) {
        this.leftCount = leftCount;
    }

    public String getLeftTitle() {
        return leftTitle;
    }

    public void setLeftTitle(String leftTitle) {
        this.leftTitle = leftTitle;
    }

    public int getMidCount() {
        return midCount;
    }

    public void setMidCount(int midCount) {
        this.midCount = midCount;
    }

    public String getMidTitle() {
        return midTitle;
    }

    public void setMidTitle(String midTitle) {
        this.midTitle = midTitle;
    }

    public int getRightCount() {
        return rightCount;
    }

    public void setRightCount(int rightCount) {
        this.rightCount = rightCount;
    }

    public String getRightTitle() {
        return rightTitle;
    }

    public void setRightTitle(String rightTitle) {
        this.rightTitle = rightTitle;
    }
}
