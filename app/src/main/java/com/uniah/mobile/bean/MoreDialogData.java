package com.uniah.mobile.bean;

import android.content.Context;
import android.view.View;

import com.uniah.mobile.R;
import com.uniah.mobile.base.BaseData;
import com.uniah.mobile.base.BaseViewHolder;
import com.uniah.mobile.holder.MoreDialogViewHolder;

import java.io.Serializable;

public class MoreDialogData extends BaseData implements Serializable {

    private int iconId;
    private String text;
    private View.OnClickListener onItemClickListener;

    public MoreDialogData() {
    }

    @Override
    public BaseViewHolder getViewHolder(Context context, View convertView) {
        return new MoreDialogViewHolder(context, convertView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.dialog_more_item;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public View.OnClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(View.OnClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
