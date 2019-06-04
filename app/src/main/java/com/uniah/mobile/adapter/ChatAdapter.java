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
import com.uniah.mobile.bean.ChatFriendData;
import com.uniah.mobile.bean.ChatUserData;
import com.uniah.mobile.bean.HintData;
import com.uniah.mobile.bean.MsgListData;
import com.uniah.mobile.bean.MsgListHeadData;
import com.uniah.mobile.bean.SearchData;
import com.uniah.mobile.bean.UserInfoData;
import com.uniah.mobile.holder.ChatViewHolder;
import com.uniah.mobile.holder.HintViewHolder;
import com.uniah.mobile.holder.MsgListHeadViewHolder;
import com.uniah.mobile.holder.MsgListViewHolder;
import com.uniah.mobile.holder.UserInfoViewHolder;
import com.uniah.mobile.util.UniImageHelper;
import com.uniah.mobile.util.UniTextHelper;

import java.util.List;

public class ChatAdapter extends BaseAdapter<BaseData> {

    public ChatAdapter(Context context, List<BaseData> list) {
        super(context, list);
    }

    @Override
    public void convert(BaseViewHolder holder, int position, BaseData item) {
        setBottomMargin(holder, position);
        if (item instanceof ChatUserData) {
            ChatUserData data = (ChatUserData) item;
            ChatViewHolder viewHolder = (ChatViewHolder) holder;

            UniImageHelper.displayImage(mContext, data.getUserHead(), viewHolder.mHead);
            if (data.getMsgType() == 0) {
                viewHolder.mText.setVisibility(View.VISIBLE);
                viewHolder.mImg.setVisibility(View.GONE);
                viewHolder.mText.setText(data.getMsgContent());
            } else if (data.getMsgType() == 1) {
                viewHolder.mText.setVisibility(View.GONE);
                viewHolder.mImg.setVisibility(View.VISIBLE);
                UniImageHelper.displayImage(mContext, data.getMsgContent(), viewHolder.mImg);
            }

            if (data.getSendState() == 2) {
                viewHolder.mAnim.startAnimation();
                viewHolder.mAnim.setVisibility(View.VISIBLE);
            } else if (data.getSendState() == 1) {
                viewHolder.mAnim.setVisibility(View.GONE);
                viewHolder.mAnim.stopAnimation();
            }

        } else if (item instanceof ChatFriendData) {
            ChatFriendData data = (ChatFriendData) item;
            ChatViewHolder viewHolder = (ChatViewHolder) holder;

            UniImageHelper.displayImage(mContext, data.getUserHead(), viewHolder.mHead);
            if (data.getMsgType() == 0) {
                viewHolder.mText.setVisibility(View.VISIBLE);
                viewHolder.mImg.setVisibility(View.GONE);
                viewHolder.mText.setText(data.getMsgContent());
            } else if (data.getMsgType() == 1) {
                viewHolder.mText.setVisibility(View.GONE);
                viewHolder.mImg.setVisibility(View.VISIBLE);
                UniImageHelper.displayImage(mContext, data.getMsgContent(), viewHolder.mImg);
            }
        }
    }

}
