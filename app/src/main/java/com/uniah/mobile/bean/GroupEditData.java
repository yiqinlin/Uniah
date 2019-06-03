package com.uniah.mobile.bean;

import android.content.Context;
import android.view.View;

import com.uniah.mobile.R;
import com.uniah.mobile.base.BaseViewHolder;
import com.uniah.mobile.holder.GroupEditViewHolder;

import java.io.Serializable;

public class GroupEditData extends GroupBaseData implements Serializable {

    private String text;
    private String hint;
    private String editText;

    @Override
    public BaseViewHolder getViewHolder(Context context, View convertView) {
        return new GroupEditViewHolder(context, convertView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_group_edit;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public String getEditText() {
        return editText;
    }

    public void setEditText(String editText) {
        this.editText = editText;
    }
}
