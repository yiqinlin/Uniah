package com.uniah.mobile.bean;

import android.content.Context;
import android.view.View;

import com.uniah.mobile.R;
import com.uniah.mobile.base.BaseData;
import com.uniah.mobile.base.BaseViewHolder;

public class SearchData extends BaseData {
    @Override
    public BaseViewHolder getViewHolder(Context context, View convertView) {
        return new BaseViewHolder(context,convertView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.search_item;
    }
}
