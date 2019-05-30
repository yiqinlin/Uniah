package com.uniah.mobile.holder;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uniah.mobile.R;
import com.uniah.mobile.base.BaseData;
import com.uniah.mobile.base.BaseViewHolder;
import com.uniah.mobile.layout.UniFrameLayout;

public class MineCardTitleViewHolder {

    public LinearLayout mTitleItem;

    public View mCursor;
    public TextView mTitle;
    public TextView mSubTitle;

    public UniFrameLayout mMoreBtn;
    public UniFrameLayout mBackBtn;

    public MineCardTitleViewHolder(BaseViewHolder holder) {
        mTitleItem = holder.findViewById(R.id.mine_card_title_item);

        mCursor = holder.findViewById(R.id.title_single_cursor);
        mTitle = holder.findViewById(R.id.title_single_title);
        mSubTitle = holder.findViewById(R.id.title_single_sub_title);

        mMoreBtn = holder.findViewById(R.id.title_single_btn_layout);
        mBackBtn = holder.findViewById(R.id.title_single_btn_sub_layout);
    }
}
