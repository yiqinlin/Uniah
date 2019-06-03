package com.uniah.mobile.bean;

import android.content.Context;
import android.view.View;

import com.uniah.mobile.R;
import com.uniah.mobile.base.BaseData;
import com.uniah.mobile.base.BaseViewHolder;
import com.uniah.mobile.holder.GroupUserViewHolder;
import com.uniah.mobile.holder.MineUniViewHolder;

import java.io.Serializable;

public class GroupUserData extends GroupBaseData implements Serializable {

    private int userId;
    private String head;
    private String nick;
    private String flag;
    private String slogan;

    private boolean isShowSubBtn;
    private int subBackgroundResourceId;

    private boolean isShowArrow;

    public GroupUserData() {
    }

    @Override
    public BaseViewHolder getViewHolder(Context context, View convertView) {
        return new GroupUserViewHolder(context, convertView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_group_user;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public boolean isShowSubBtn() {
        return isShowSubBtn;
    }

    public void setShowSubBtn(boolean showSubBtn) {
        isShowSubBtn = showSubBtn;
    }

    public int getSubBackgroundResourceId() {
        return subBackgroundResourceId;
    }

    public void setSubBackgroundResourceId(int subBackgroundResourceId) {
        this.subBackgroundResourceId = subBackgroundResourceId;
    }

    public boolean isShowArrow() {
        return isShowArrow;
    }

    public void setShowArrow(boolean showArrow) {
        isShowArrow = showArrow;
    }
}
