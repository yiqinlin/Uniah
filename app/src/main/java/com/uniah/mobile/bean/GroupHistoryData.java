package com.uniah.mobile.bean;

import android.content.Context;
import android.view.View;

import com.uniah.mobile.R;
import com.uniah.mobile.base.BaseData;
import com.uniah.mobile.base.BaseViewHolder;
import com.uniah.mobile.holder.GroupHistoryViewHolder;
import com.uniah.mobile.holder.UserInfoViewHolder;

import java.io.Serializable;

public class GroupHistoryData extends GroupBaseData implements Serializable {

    private int contentId;

    private String head;
    private String name;
    private String flag;
    private int imgId;

    private int contentType;
    private View.OnClickListener onItemClickListener;
    private View.OnClickListener onBtnClickListener;

    public GroupHistoryData() {
    }

    @Override
    public BaseViewHolder getViewHolder(Context context, View convertView) {
        return new GroupHistoryViewHolder(context, convertView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_group_history;
    }

    public int getContentId() {
        return contentId;
    }

    public void setContentId(int contentId) {
        this.contentId = contentId;
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

    public void setName(String nick) {
        this.name = nick;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public int getContentType() {
        return contentType;
    }

    public void setContentType(int contentType) {
        this.contentType = contentType;
    }

    public View.OnClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(View.OnClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public View.OnClickListener getOnBtnClickListener() {
        return onBtnClickListener;
    }

    public void setOnBtnClickListener(View.OnClickListener onBtnClickListener) {
        this.onBtnClickListener = onBtnClickListener;
    }
}
