package com.uniah.mobile.bean;

import android.content.Context;
import android.view.View;

import com.uniah.mobile.R;
import com.uniah.mobile.base.BaseViewHolder;
import com.uniah.mobile.holder.GroupSearchUserViewHolder;
import com.uniah.mobile.holder.GroupUserViewHolder;

import java.io.Serializable;

public class GroupSearchUserData extends GroupBaseData implements Serializable {

    private int userId;
    private String head;
    private String nick;
    private String flag;
    private String slogan;

    private String btnText;
    private View.OnClickListener onBtnClickListener;

    public GroupSearchUserData() {
    }

    @Override
    public BaseViewHolder getViewHolder(Context context, View convertView) {
        return new GroupSearchUserViewHolder(context, convertView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_group_search_user;
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

    public String getBtnText() {
        return btnText;
    }

    public void setBtnText(String btnText) {
        this.btnText = btnText;
    }

    public View.OnClickListener getOnBtnClickListener() {
        return onBtnClickListener;
    }

    public void setOnBtnClickListener(View.OnClickListener onBtnClickListener) {
        this.onBtnClickListener = onBtnClickListener;
    }
}
