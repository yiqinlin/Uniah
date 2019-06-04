package com.uniah.mobile.bean;

import android.content.Context;
import android.view.View;

import com.uniah.mobile.R;
import com.uniah.mobile.base.BaseData;
import com.uniah.mobile.base.BaseViewHolder;
import com.uniah.mobile.holder.DataHomeViewHolder;
import com.uniah.mobile.holder.MineUniViewHolder;

import java.io.Serializable;

public class DataHomeData extends BaseData implements Serializable {

    private int contentId;

    private String wall;
    private String head;
    private String name;

    private String flagLeft;
    private String flagRight;

    private int account;
    private String slogan;

    private String infoFirst;
    private String infoSecond;
    private String infoThird;

    public DataHomeData() {
    }

    @Override
    public BaseViewHolder getViewHolder(Context context, View convertView) {
        return new DataHomeViewHolder(context, convertView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.data_home_item;
    }

    public int getContentId() {
        return contentId;
    }

    public void setContentId(int contentId) {
        this.contentId = contentId;
    }

    public String getWall() {
        return wall;
    }

    public void setWall(String wall) {
        this.wall = wall;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFlagLeft() {
        return flagLeft;
    }

    public void setFlagLeft(String flagLeft) {
        this.flagLeft = flagLeft;
    }

    public String getFlagRight() {
        return flagRight;
    }

    public void setFlagRight(String flagRight) {
        this.flagRight = flagRight;
    }

    public int getAccount() {
        return account;
    }

    public void setAccount(int account) {
        this.account = account;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public String getInfoFirst() {
        return infoFirst;
    }

    public void setInfoFirst(String infoFirst) {
        this.infoFirst = infoFirst;
    }

    public String getInfoSecond() {
        return infoSecond;
    }

    public void setInfoSecond(String infoSecond) {
        this.infoSecond = infoSecond;
    }

    public String getInfoThird() {
        return infoThird;
    }

    public void setInfoThird(String infoThird) {
        this.infoThird = infoThird;
    }
}
