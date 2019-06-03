package com.uniah.mobile.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.uniah.mobile.R;
import com.uniah.mobile.activity.LauncherActivity;
import com.uniah.mobile.activity.NoticeActivity;
import com.uniah.mobile.base.BaseAdapter;
import com.uniah.mobile.base.BaseData;
import com.uniah.mobile.base.BaseViewHolder;
import com.uniah.mobile.bean.MsgListData;
import com.uniah.mobile.bean.MsgListHeadData;
import com.uniah.mobile.bean.NoticeData;
import com.uniah.mobile.bean.SearchData;
import com.uniah.mobile.holder.MsgListHeadViewHolder;
import com.uniah.mobile.holder.MsgListViewHolder;
import com.uniah.mobile.holder.NoticeViewHolder;
import com.uniah.mobile.util.UniImageHelper;

import java.util.List;

public class NoticeAdapter extends BaseAdapter<BaseData> {

    public NoticeAdapter(Context context, List<BaseData> list) {
        super(context, list);
    }

    @Override
    public void convert(BaseViewHolder holder, int position, BaseData item) {
        setBottomMargin(holder, position);
        if (item instanceof NoticeData) {
            NoticeData data = (NoticeData) item;
            NoticeViewHolder viewHolder = (NoticeViewHolder) holder;

            UniImageHelper.displayImage(mContext, data.getHead(), viewHolder.mHead);
            UniImageHelper.displayImage(mContext, data.getFromThumb(), viewHolder.mFromThumb);

            viewHolder.mItem.setOnClickListener(mItemClickListener);
            viewHolder.mNick.setText(data.getNick());
            viewHolder.mTime.setText(data.smartTime());
            viewHolder.mContent.setText(data.getContent());
            viewHolder.mFromContent.setText(data.getFromContent());
        }


    }
    private View.OnClickListener mItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(mContext, "click item", Toast.LENGTH_SHORT).show();
        }
    };
}
