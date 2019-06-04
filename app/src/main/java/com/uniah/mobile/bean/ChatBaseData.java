package com.uniah.mobile.bean;

import android.content.Context;
import android.view.View;

import com.uniah.mobile.base.BaseData;

public abstract class ChatBaseData extends BaseData {

    private int userId;
    private String userNick;
    private String userHead;

    private int acceptId;
    private String acceptNick;
    private String acceptHead;

    private int msgId;
    private String msgContent;
    private String msgTime;
    private int msgType;
    private int msgState;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserNick() {
        return userNick;
    }

    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }

    public String getUserHead() {
        return userHead;
    }

    public void setUserHead(String userHead) {
        this.userHead = userHead;
    }

    public int getAcceptId() {
        return acceptId;
    }

    public void setAcceptId(int acceptId) {
        this.acceptId = acceptId;
    }

    public String getAcceptNick() {
        return acceptNick;
    }

    public void setAcceptNick(String acceptNick) {
        this.acceptNick = acceptNick;
    }

    public String getAcceptHead() {
        return acceptHead;
    }

    public void setAcceptHead(String acceptHead) {
        this.acceptHead = acceptHead;
    }

    public int getMsgId() {
        return msgId;
    }

    public void setMsgId(int msgId) {
        this.msgId = msgId;
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
}
