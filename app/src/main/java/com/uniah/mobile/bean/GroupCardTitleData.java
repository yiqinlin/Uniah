package com.uniah.mobile.bean;

import android.content.Context;
import android.view.View;

import com.uniah.mobile.R;
import com.uniah.mobile.base.BaseViewHolder;
import com.uniah.mobile.holder.GroupCardTitleViewHolder;
import com.uniah.mobile.holder.GroupTextViewHolder;

import java.io.Serializable;

public class GroupCardTitleData extends GroupBaseData implements Serializable {

    private String text;
    private String subText;

    private boolean showBtn;
    private boolean showSubBtn;
    View.OnClickListener onBtnClickListener;
    View.OnClickListener onSubBtnClickListener;

    @Override
    public BaseViewHolder getViewHolder(Context context, View convertView) {
        return new GroupCardTitleViewHolder(context, convertView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_group_card_title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSubText() {
        return subText;
    }

    public void setSubText(String subText) {
        this.subText = subText;
    }

    public boolean isShowBtn() {
        return showBtn;
    }

    public void setShowBtn(boolean showBtn) {
        this.showBtn = showBtn;
    }

    public boolean isShowSubBtn() {
        return showSubBtn;
    }

    public void setShowSubBtn(boolean showSubBtn) {
        this.showSubBtn = showSubBtn;
    }

    public View.OnClickListener getOnBtnClickListener() {
        return onBtnClickListener;
    }

    public void setOnBtnClickListener(View.OnClickListener onBtnClickListener) {
        this.onBtnClickListener = onBtnClickListener;
    }

    public View.OnClickListener getOnSubBtnClickListener() {
        return onSubBtnClickListener;
    }

    public void setOnSubBtnClickListener(View.OnClickListener onSubBtnClickListener) {
        this.onSubBtnClickListener = onSubBtnClickListener;
    }
}
