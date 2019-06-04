package com.uniah.mobile.bean;

import android.content.Context;
import android.view.View;

import com.uniah.mobile.R;
import com.uniah.mobile.base.BaseViewHolder;
import com.uniah.mobile.holder.ChatViewHolder;

public class ChatFriendData extends ChatBaseData {


    @Override
    public BaseViewHolder getViewHolder(Context context, View convertView) {
        return new ChatViewHolder(context, convertView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.chat_left_text_item;
    }
}
