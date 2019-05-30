package com.uniah.mobile.bean;

import com.uniah.mobile.R;
import com.uniah.mobile.base.BaseData;

import java.io.Serializable;

public class MineCardTitleData extends BaseData implements Serializable {

    private String title;
    private String subTitle;

    private boolean showMoreBtn;
    private boolean showBackBtn;

    @Override
    public int getLayoutId() {
        return R.layout.mine_card_title_item;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
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
}
