package com.uniah.mobile.holder;

import android.widget.TextView;

import com.uniah.mobile.R;
import com.uniah.mobile.base.BaseViewHolder;
import com.uniah.mobile.util.UniDateHelper;
import com.uniah.mobile.view.UniRadiusView;

public class MsgListViewHolder {

    public UniRadiusView mHead;
    public TextView mNick;
    public TextView mTime;
    public TextView mContent;
    public TextView mCount;

    public MsgListViewHolder(BaseViewHolder holder) {
        mHead = holder.findViewById(R.id.msg_list_head);
        mNick = holder.findViewById(R.id.msg_list_nick);
        mTime = holder.findViewById(R.id.msg_list_time);
        mContent = holder.findViewById(R.id.msg_list_content);
        mCount = holder.findViewById(R.id.msg_list_count);
    }
}
