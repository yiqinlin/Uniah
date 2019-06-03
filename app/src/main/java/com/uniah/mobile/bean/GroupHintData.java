package com.uniah.mobile.bean;

import android.content.Context;
import android.view.View;

import com.uniah.mobile.R;
import com.uniah.mobile.base.BaseData;
import com.uniah.mobile.base.BaseViewHolder;
import com.uniah.mobile.holder.GroupHintViewHolder;
import com.uniah.mobile.holder.HintViewHolder;

public class GroupHintData extends BaseData {

    private String hint;

    @Override
    public BaseViewHolder getViewHolder(Context context, View convertView) {
        return new GroupHintViewHolder(context, convertView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_group_hint;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }
}
