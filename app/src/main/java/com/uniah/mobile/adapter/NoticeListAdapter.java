package com.uniah.mobile.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.uniah.mobile.R;
import com.uniah.mobile.activity.LauncherActivity;
import com.uniah.mobile.activity.NoticeActivity;
import com.uniah.mobile.base.BaseAdapter;
import com.uniah.mobile.base.BaseData;
import com.uniah.mobile.base.BaseViewHolder;
import com.uniah.mobile.bean.MsgListData;
import com.uniah.mobile.bean.MsgListHeadData;
import com.uniah.mobile.bean.SearchData;
import com.uniah.mobile.holder.MsgListHeadViewHolder;
import com.uniah.mobile.holder.MsgListViewHolder;
import com.uniah.mobile.util.UniImageHelper;

import java.util.List;

public class NoticeListAdapter extends BaseAdapter<BaseData> {

    public NoticeListAdapter(Context context, List<BaseData> list) {
        super(context, list);
    }

    @Override
    public void convert(BaseViewHolder holder, int position, BaseData item) {
        setBottomMargin(holder, position);
        if (item instanceof SearchData) {
            LinearLayout linearLayout = holder.findViewById(R.id.search_layout);
            linearLayout.setOnClickListener(mSearchClickListener);
        } else if (item instanceof MsgListHeadData) {
            MsgListHeadData data = (MsgListHeadData) item;
            MsgListHeadViewHolder viewHolder = (MsgListHeadViewHolder) holder;
            viewHolder.mLeft.setOnClickListener(mMsgHeadLeftClickListener);
            viewHolder.mLeftBackground.setBackgroundResource(data.getLeftBackgroundResource());
            viewHolder.mLeftImg.setBackgroundResource(data.getLeftImageResource());
            viewHolder.mLeftText.setText(data.getLeftName());
            if (data.getLeftCount() > 0) {
                viewHolder.mLeftCount.setText(data.leftCountString());
                viewHolder.mLeftCount.setVisibility(View.VISIBLE);
            } else {
                viewHolder.mLeftCount.setVisibility(View.GONE);
            }
            viewHolder.mMid.setOnClickListener(mMsgHeadMidClickListener);
            viewHolder.mMidBackground.setBackgroundResource(data.getMidBackgroundResource());
            viewHolder.mMidImg.setBackgroundResource(data.getMidImageResource());
            viewHolder.mMidText.setText(data.getMidName());
            if (data.getMidCount() > 0) {
                viewHolder.mMidCount.setText(data.midCountString());
                viewHolder.mMidCount.setVisibility(View.VISIBLE);
            } else {
                viewHolder.mMidCount.setVisibility(View.GONE);
            }
            viewHolder.mRight.setOnClickListener(mMsgHeadRightClickListener);
            viewHolder.mRightBackground.setBackgroundResource(data.getRightBackgroundResource());
            viewHolder.mRightImg.setBackgroundResource(data.getRightImageResource());
            viewHolder.mRightText.setText(data.getRightName());
            if (data.getRightCount() > 0) {
                viewHolder.mRightCount.setText(data.rightCountString());
                viewHolder.mRightCount.setVisibility(View.VISIBLE);
            } else {
                viewHolder.mRightCount.setVisibility(View.GONE);
            }

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

    private View.OnClickListener mMsgHeadLeftClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(mContext, NoticeActivity.class);
            intent.putExtra("noticeType",0);
            mContext.startActivity(intent);
        }
    };

    private View.OnClickListener mMsgHeadMidClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(mContext, NoticeActivity.class);
            intent.putExtra("noticeType",1);
            mContext.startActivity(intent);
        }
    };

    private View.OnClickListener mMsgHeadRightClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(mContext, NoticeActivity.class);
            intent.putExtra("noticeType",2);
            mContext.startActivity(intent);
        }
    };
}
