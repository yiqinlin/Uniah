package com.uniah.mobile.holder;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uniah.mobile.R;
import com.uniah.mobile.base.BaseViewHolder;
import com.uniah.mobile.layout.UniFrameLayout;

public class CardTitleViewHolder extends BaseViewHolder {

    public LinearLayout mTitleItem;

    public View mCursor;
    public TextView mTitle;
    public TextView mSubTitle;

    public UniFrameLayout mMoreBtn;
    public UniFrameLayout mBackBtn;

    public CardTitleViewHolder(Context context, View convertView) {
        super(context, convertView);

        mTitleItem = convertView.findViewById(R.id.card_title_item);

        mCursor = convertView.findViewById(R.id.title_single_cursor);
        mTitle = convertView.findViewById(R.id.title_single_title);
        mSubTitle = convertView.findViewById(R.id.title_single_sub_title);

        mMoreBtn = convertView.findViewById(R.id.title_single_btn_layout);
        mBackBtn = convertView.findViewById(R.id.title_single_btn_sub_layout);
    }
}
