package com.uniah.mobile.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.uniah.mobile.R;
import com.uniah.mobile.activity.LauncherActivity;
import com.uniah.mobile.base.BaseAdapter;
import com.uniah.mobile.base.BaseData;
import com.uniah.mobile.base.BaseViewHolder;
import com.uniah.mobile.bean.HintData;
import com.uniah.mobile.bean.MsgListData;
import com.uniah.mobile.bean.MsgListHeadData;
import com.uniah.mobile.bean.SearchData;
import com.uniah.mobile.bean.UserInfoData;
import com.uniah.mobile.holder.HintViewHolder;
import com.uniah.mobile.holder.MsgListHeadViewHolder;
import com.uniah.mobile.holder.MsgListViewHolder;
import com.uniah.mobile.holder.UserInfoViewHolder;
import com.uniah.mobile.util.UniImageHelper;
import com.uniah.mobile.util.UniTextHelper;

import java.util.List;

public class ContactAdapter extends BaseAdapter<BaseData> {

    public ContactAdapter(Context context, List<BaseData> list) {
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
        } else if (item instanceof HintData) {
            HintData data = (HintData) item;
            HintViewHolder viewHolder = (HintViewHolder) holder;
            viewHolder.mHintText.setText(UniTextHelper.isEmpty(data.getHint()) ? "" : data.getHint());
        } else if (item instanceof UserInfoData) {
            UserInfoData data = (UserInfoData) item;
            UserInfoViewHolder viewHolder = (UserInfoViewHolder) holder;

            UniImageHelper.displayImage(mContext, data.getHead(), viewHolder.mHead);

            viewHolder.mNick.setText(data.getName());
            if (!UniTextHelper.isEmpty(data.getFlag())) {
                viewHolder.mFlag.setText(data.getFlag());
                viewHolder.mFlag.setVisibility(View.VISIBLE);
            } else {
                viewHolder.mFlag.setVisibility(View.GONE);
            }
            if (data.getImgId() != 0) {
                viewHolder.mBtnImg.setBackgroundResource(data.getImgId());
                viewHolder.mBtnLayout.setVisibility(View.VISIBLE);
                viewHolder.mBtnLayout.setOnClickListener(mUserInfoBtnClickListener);
            } else {
                viewHolder.mBtnLayout.setVisibility(View.GONE);
            }
        }
    }

    private View.OnClickListener mUserInfoBtnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(mContext, "click user info btn", Toast.LENGTH_SHORT).show();
        }
    };


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
            Intent intent = new Intent(mContext, LauncherActivity.class);
            mContext.startActivity(intent);
        }
    };

    private View.OnClickListener mMsgHeadMidClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(mContext, LauncherActivity.class);
            mContext.startActivity(intent);
        }
    };

    private View.OnClickListener mMsgHeadRightClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(mContext, LauncherActivity.class);
            mContext.startActivity(intent);
        }
    };
}
