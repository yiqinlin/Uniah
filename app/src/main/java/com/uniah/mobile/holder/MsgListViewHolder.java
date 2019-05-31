package com.uniah.mobile.holder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.uniah.mobile.R;
import com.uniah.mobile.base.BaseViewHolder;
import com.uniah.mobile.util.UniDateHelper;
import com.uniah.mobile.view.UniRadiusView;

public class MsgListViewHolder extends BaseViewHolder {

    public UniRadiusView mHead;
    public TextView mNick;
    public TextView mTime;
    public TextView mContent;
    public TextView mCount;

    public MsgListViewHolder(Context context, View convertView) {
        super(context, convertView);

        mHead = convertView.findViewById(R.id.msg_list_head);
        mNick = convertView.findViewById(R.id.msg_list_nick);
        mTime = convertView.findViewById(R.id.msg_list_time);
        mContent = convertView.findViewById(R.id.msg_list_content);
        mCount = convertView.findViewById(R.id.msg_list_count);
    }
}
