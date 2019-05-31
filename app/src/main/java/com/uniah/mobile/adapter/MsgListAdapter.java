package com.uniah.mobile.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.uniah.mobile.R;
import com.uniah.mobile.activity.LauncherActivity;
import com.uniah.mobile.base.BaseAdapter;
import com.uniah.mobile.base.BaseData;
import com.uniah.mobile.base.BaseViewHolder;
import com.uniah.mobile.bean.MsgListData;
import com.uniah.mobile.bean.SearchData;
import com.uniah.mobile.holder.MsgListViewHolder;
import com.uniah.mobile.util.UniImageHelper;

import java.util.List;

public class MsgListAdapter extends BaseAdapter<BaseData> {

    public MsgListAdapter(Context context, List<BaseData> list) {
        super(context, list);
    }

    @Override
    public void convert(BaseViewHolder holder, int position, BaseData item) {
        if (item instanceof SearchData) {
            LinearLayout linearLayout = holder.findViewById(R.id.search_layout);
            linearLayout.setOnClickListener(mSearchClickListener);
        } else if (item instanceof MsgListData) {
            MsgListData data = (MsgListData) item;
            MsgListViewHolder viewHolder = (MsgListViewHolder) holder;

            UniImageHelper.displayImage(mContext, data.getAcceptHead(), viewHolder.mHead);
            viewHolder.mNick.setText(data.getAcceptNick());
            viewHolder.mTime.setText(data.smartTime());
            viewHolder.mContent.setText(data.getMsgContent());
            if (data.getMsgCount() > 0) {
                viewHolder.mCount.setText(data.countStr());
                viewHolder.mCount.setVisibility(View.VISIBLE);
            } else {
                viewHolder.mCount.setVisibility(View.GONE);
            }
        }
    }

    private View.OnClickListener mSearchClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(mContext, LauncherActivity.class);
            mContext.startActivity(intent);
        }
    };
}
