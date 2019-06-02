package com.uniah.mobile.bean;

import android.content.Context;
import android.view.View;

import com.uniah.mobile.R;
import com.uniah.mobile.base.BaseData;
import com.uniah.mobile.base.BaseViewHolder;
import com.uniah.mobile.holder.MsgListHeadViewHolder;

import java.io.Serializable;

public class MsgListHeadData extends BaseData implements Serializable {


    private static final long serialVersionUID = 8106736455427597184L;

    private String leftName;
    private String midName;
    private String rightName;

    private int leftBackgroundResource = R.drawable.bg_circle_green;
    private int midBackgroundResource = R.drawable.bg_circle_yellow;
    private int rightBackgroundResource = R.drawable.bg_circle_blue;

    private int leftImageResource;
    private int midImageResource;
    private int rightImageResource;

    private int leftCount;
    private int midCount;
    private int rightCount;


    @Override
    public BaseViewHolder getViewHolder(Context context, View convertView) {
        return new MsgListHeadViewHolder(context, convertView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.msg_list_head_item;
    }


    public String getLeftName() {
        return leftName;
    }

    public void setLeftName(String leftName) {
        this.leftName = leftName;
    }

    public String getMidName() {
        return midName;
    }

    public void setMidName(String midName) {
        this.midName = midName;
    }

    public String getRightName() {
        return rightName;
    }

    public void setRightName(String rightName) {
        this.rightName = rightName;
    }

    public int getLeftBackgroundResource() {
        return leftBackgroundResource;
    }

    public void setLeftBackgroundResource(int leftBackgroundResource) {
        this.leftBackgroundResource = leftBackgroundResource;
    }

    public int getMidBackgroundResource() {
        return midBackgroundResource;
    }

    public void setMidBackgroundResource(int midBackgroundResource) {
        this.midBackgroundResource = midBackgroundResource;
    }

    public int getRightBackgroundResource() {
        return rightBackgroundResource;
    }

    public void setRightBackgroundResource(int rightBackgroundResource) {
        this.rightBackgroundResource = rightBackgroundResource;
    }

    public int getLeftImageResource() {
        return leftImageResource;
    }

    public void setLeftImageResource(int leftImageResource) {
        this.leftImageResource = leftImageResource;
    }

    public int getMidImageResource() {
        return midImageResource;
    }

    public void setMidImageResource(int midImageResource) {
        this.midImageResource = midImageResource;
    }

    public int getRightImageResource() {
        return rightImageResource;
    }

    public void setRightImageResource(int rightImageResource) {
        this.rightImageResource = rightImageResource;
    }

    public int getLeftCount() {
        return leftCount;
    }

    public void setLeftCount(int leftCount) {
        this.leftCount = leftCount;
    }

    public int getMidCount() {
        return midCount;
    }

    public void setMidCount(int midCount) {
        this.midCount = midCount;
    }

    public int getRightCount() {
        return rightCount;
    }

    public void setRightCount(int rightCount) {
        this.rightCount = rightCount;
    }

    public String leftCountString() {
        return leftCount > 99 ? "99+" : leftCount + "";
    }

    public String midCountString() {
        return midCount > 99 ? "99+" : midCount + "";
    }

    public String rightCountString() {
        return rightCount > 99 ? "99+" : rightCount + "";
    }

}
