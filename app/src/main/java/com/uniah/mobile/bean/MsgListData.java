package com.uniah.mobile.bean;

import android.content.Context;
import android.view.View;

import com.uniah.mobile.R;
import com.uniah.mobile.base.BaseData;
import com.uniah.mobile.base.BaseViewHolder;
import com.uniah.mobile.holder.MsgListViewHolder;
import com.uniah.mobile.util.UniDateHelper;

import java.io.Serializable;

public class MsgListData extends BaseData implements Serializable {

    private static final long serialVersionUID = -5925380109055102040L;

    private int acceptId;
    private String acceptHead;
    private String acceptNick;
    private String msgContent;
    private String msgTime;
    private int msgType;
    private int msgState;
    private int msgCount;

    public MsgListData() {
    }

    @Override
    public BaseViewHolder getViewHolder(Context context, View convertView) {
        return new MsgListViewHolder(context, convertView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.msg_list_item;
    }


    public int getAcceptId() {
        return acceptId;
    }

    public void setAcceptId(int acceptId) {
        this.acceptId = acceptId;
    }

    public String getAcceptHead() {
        return acceptHead;
    }

    public void setAcceptHead(String acceptHead) {
        this.acceptHead = acceptHead;
    }

    public String getAcceptNick() {
        return acceptNick;
    }

    public void setAcceptNick(String acceptNick) {
        this.acceptNick = acceptNick;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public String getMsgTime() {
        return msgTime;
    }

    public void setMsgTime(String msgTime) {
        this.msgTime = msgTime;
    }

    public String smartTime() {
        try {
            return UniDateHelper.SmartTime(msgTime);
        } catch (Throwable e) {
            e.printStackTrace();
            return "";
        }
    }

    public int getMsgType() {
        return msgType;
    }

    public void setMsgType(int msgType) {
        this.msgType = msgType;
    }

    public int getMsgState() {
        return msgState;
    }

    public void setMsgState(int msgState) {
        this.msgState = msgState;
    }

    public int getMsgCount() {
        return msgCount;
    }

    public void setMsgCount(int msgCount) {
        this.msgCount = msgCount;
    }

    public String countStr() {
        return msgCount > 99 ? "99+" : msgCount + "";
    }

}
