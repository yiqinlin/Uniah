package com.uniah.mobile.bean;

import android.content.Context;
import android.view.View;

import com.uniah.mobile.R;
import com.uniah.mobile.base.BaseViewHolder;
import com.uniah.mobile.holder.ChatViewHolder;

public class ChatUserData extends ChatBaseData {

    private int sendState;
    private int sendId;

    @Override
    public BaseViewHolder getViewHolder(Context context, View convertView) {
        return new ChatViewHolder(context, convertView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.chat_right_text_item;
    }

    public int getSendState() {
        return sendState;
    }

    public void setSendState(int sendState) {
        this.sendState = sendState;
    }

    public int getSendId() {
        return sendId;
    }

    public void setSendId(int sendId) {
        this.sendId = sendId;
    }
}
