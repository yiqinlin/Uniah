package com.uniah.mobile.bean;

import android.content.Context;
import android.view.View;

import com.uniah.mobile.R;
import com.uniah.mobile.base.BaseViewHolder;
import com.uniah.mobile.holder.GroupEditViewHolder;
import com.uniah.mobile.holder.GroupSwitchViewHolder;

import java.io.Serializable;

public class GroupSwitchData extends GroupBaseData implements Serializable {

    private String text;
    private boolean isChecked;

    @Override
    public BaseViewHolder getViewHolder(Context context, View convertView) {
        return new GroupSwitchViewHolder(context, convertView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_group_switch;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

}
