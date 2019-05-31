package com.uniah.mobile.base;

import android.content.Context;
import android.view.View;

public abstract class BaseData {

    public BaseData() {
    }

    public abstract BaseViewHolder getViewHolder(Context context, View convertView);

    public abstract int getLayoutId();
}
