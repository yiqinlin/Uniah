package com.uniah.mobile.adapter;

import android.content.Context;

import com.uniah.mobile.base.BaseAdapter;
import com.uniah.mobile.base.BaseData;
import com.uniah.mobile.base.BaseViewHolder;
import com.uniah.mobile.bean.MoreDialogData;
import com.uniah.mobile.holder.MoreDialogViewHolder;

import java.util.List;

public class MoreDialogAdapter extends BaseAdapter<BaseData> {

    public MoreDialogAdapter(Context context, List<BaseData> list) {
        super(context, list);
    }

    @Override
    public void convert(BaseViewHolder holder, int position, BaseData item) {
        if (item instanceof MoreDialogData) {
            MoreDialogData data = (MoreDialogData) item;
            MoreDialogViewHolder viewHolder = (MoreDialogViewHolder) holder;

            viewHolder.mIcon.setImageResource(data.getIconId());
            viewHolder.mText.setText(data.getText());
            viewHolder.mItem.setOnClickListener(data.getOnItemClickListener());
        }
    }
}
