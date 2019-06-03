package com.uniah.mobile.bean;

import android.content.Context;
import android.view.View;

import com.uniah.mobile.R;
import com.uniah.mobile.base.BaseViewHolder;
import com.uniah.mobile.holder.GroupEditViewHolder;
import com.uniah.mobile.holder.GroupHeadViewHolder;

import java.io.Serializable;

public class GroupHeadData extends GroupBaseData implements Serializable {

    private String head;
    private String hint;

    @Override
    public BaseViewHolder getViewHolder(Context context, View convertView) {
        return new GroupHeadViewHolder(context, convertView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_group_head;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }
}
