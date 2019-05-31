package com.uniah.mobile.bean;

import android.content.Context;
import android.view.View;

import com.uniah.mobile.R;
import com.uniah.mobile.base.BaseData;
import com.uniah.mobile.base.BaseViewHolder;
import com.uniah.mobile.holder.MineCardViewHolder;

import java.io.Serializable;

public class MineToolData extends BaseData implements Serializable {

    private String toolTitle;
    private String toolSubTitle;

    private String firstName;
    private String secondName;
    private String thirdName;
    private String fourthName;

    private int firstBackgroundResource;
    private int secondBackgroundResource;
    private int thirdBackgroundResource;
    private int fourthBackgroundResource;

    private int firstImageResource;
    private int secondImageResource;
    private int thirdImageResource;
    private int fourthImageResource;

    private int firstCount;
    private int secondCount;
    private int thirdCount;
    private int fourthCount;

    private boolean showMoreBtn;
    private boolean showBackBtn;


    public MineToolData() {
    }

    @Override
    public BaseViewHolder getViewHolder(Context context, View convertView) {
        return new MineCardViewHolder(context, convertView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.mine_tool_item;
    }

    public String getToolTitle() {
        return toolTitle;
    }

    public void setToolTitle(String toolTitle) {
        this.toolTitle = toolTitle;
    }

    public String getToolSubTitle() {
        return toolSubTitle;
    }

    public void setToolSubTitle(String toolSubTitle) {
        this.toolSubTitle = toolSubTitle;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getThirdName() {
        return thirdName;
    }

    public String getFourthName() {
        return fourthName;
    }

    public void setFourthName(String fourthName) {
        this.fourthName = fourthName;
    }

    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
    }

    public int getFirstBackgroundResource() {
        return firstBackgroundResource;
    }

    public void setFirstBackgroundResource(int firstBackgroundResource) {
        this.firstBackgroundResource = firstBackgroundResource;
    }

    public int getSecondBackgroundResource() {
        return secondBackgroundResource;
    }

    public void setSecondBackgroundResource(int secondBackgroundResource) {
        this.secondBackgroundResource = secondBackgroundResource;
    }

    public int getThirdBackgroundResource() {
        return thirdBackgroundResource;
    }

    public void setThirdBackgroundResource(int thirdBackgroundResource) {
        this.thirdBackgroundResource = thirdBackgroundResource;
    }

    public int getFourthBackgroundResource() {
        return fourthBackgroundResource;
    }

    public void setFourthBackgroundResource(int fourthBackgroundResource) {
        this.fourthBackgroundResource = fourthBackgroundResource;
    }

    public int getFirstImageResource() {
        return firstImageResource;
    }

    public void setFirstImageResource(int firstImageResource) {
        this.firstImageResource = firstImageResource;
    }

    public int getSecondImageResource() {
        return secondImageResource;
    }

    public void setSecondImageResource(int secondImageResource) {
        this.secondImageResource = secondImageResource;
    }

    public int getThirdImageResource() {
        return thirdImageResource;
    }

    public void setThirdImageResource(int thirdImageResource) {
        this.thirdImageResource = thirdImageResource;
    }

    public int getFourthImageResource() {
        return fourthImageResource;
    }

    public void setFourthImageResource(int fourthImageResource) {
        this.fourthImageResource = fourthImageResource;
    }

    public int getFirstCount() {
        return firstCount;
    }

    public void setFirstCount(int firstCount) {
        this.firstCount = firstCount;
    }

    public int getSecondCount() {
        return secondCount;
    }

    public void setSecondCount(int secondCount) {
        this.secondCount = secondCount;
    }

    public int getThirdCount() {
        return thirdCount;
    }

    public void setThirdCount(int thirdCount) {
        this.thirdCount = thirdCount;
    }

    public int getFourthCount() {
        return fourthCount;
    }

    public void setFourthCount(int fourthCount) {
        this.fourthCount = fourthCount;
    }

    public boolean isShowMoreBtn() {
        return showMoreBtn;
    }

    public void setShowMoreBtn(boolean showMoreBtn) {
        this.showMoreBtn = showMoreBtn;
    }

    public boolean isShowBackBtn() {
        return showBackBtn;
    }

    public void setShowBackBtn(boolean showBackBtn) {
        this.showBackBtn = showBackBtn;
    }

    public String firstCountString() {
        return firstCount > 99 ? "99+" : firstCount + "";
    }

    public String secondCountString() {
        return secondCount > 99 ? "99+" : secondCount + "";
    }

    public String thirdCountString() {
        return thirdCount > 99 ? "99+" : thirdCount + "";
    }

    public String fourthCountString() {
        return fourthCount > 99 ? "99+" : fourthCount + "";
    }
}
