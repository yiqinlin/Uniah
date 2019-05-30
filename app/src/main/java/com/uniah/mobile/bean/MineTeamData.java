package com.uniah.mobile.bean;

import com.uniah.mobile.R;
import com.uniah.mobile.base.BaseData;

import java.io.Serializable;

public class MineTeamData extends BaseData implements Serializable {

    private String toolTitle;
    private String toolSubTitle;

    private String firstName;
    private String secondName;
    private String thirdName;
    private String fourthName;

    private String firstLogo;
    private String secondLogo;
    private String thirdLogo;
    private String fourthLogo;

    private int firstCount;
    private int secondCount;
    private int thirdCount;
    private int fourthCount;

    private boolean showMoreBtn;
    private boolean showBackBtn;

    public MineTeamData() {
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

    public String getFirstLogo() {
        return firstLogo;
    }

    public void setFirstLogo(String firstLogo) {
        this.firstLogo = firstLogo;
    }

    public String getSecondLogo() {
        return secondLogo;
    }

    public void setSecondLogo(String secondLogo) {
        this.secondLogo = secondLogo;
    }

    public String getThirdLogo() {
        return thirdLogo;
    }

    public void setThirdLogo(String thirdLogo) {
        this.thirdLogo = thirdLogo;
    }

    public String getFourthLogo() {
        return fourthLogo;
    }

    public void setFourthLogo(String fourthLogo) {
        this.fourthLogo = fourthLogo;
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
