package com.uniah.mobile.bean;

import android.content.Context;
import android.view.View;

import com.uniah.mobile.R;
import com.uniah.mobile.base.BaseData;
import com.uniah.mobile.base.BaseViewHolder;
import com.uniah.mobile.holder.FeedViewHolder;
import com.uniah.mobile.holder.NoticeViewHolder;
import com.uniah.mobile.util.UniDateHelper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NoticeData extends BaseData implements Serializable {

    private int contentId;
    private int userId;
    private String head;
    private String nick;

    private String time;
    private String Content;

    private int contentType;
    private int contentState;

    private String fromThumb;
    private String fromContent;

    public NoticeData() {
    }

    @Override
    public BaseViewHolder getViewHolder(Context context, View convertView) {
        return new NoticeViewHolder(context, convertView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.notice_item;
    }

    public String smartTime() {
        try {
            return UniDateHelper.SmartTime(time);
        } catch (Throwable e) {
            e.printStackTrace();
            return "";
        }
    }

    public int getContentId() {
        return contentId;
    }

    public void setContentId(int contentId) {
        this.contentId = contentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public int getContentType() {
        return contentType;
    }

    public void setContentType(int contentType) {
        this.contentType = contentType;
    }

    public int getContentState() {
        return contentState;
    }

    public void setContentState(int contentState) {
        this.contentState = contentState;
    }

    public String getFromThumb() {
        return fromThumb;
    }

    public void setFromThumb(String fromThumb) {
        this.fromThumb = fromThumb;
    }

    public String getFromContent() {
        return fromContent;
    }

    public void setFromContent(String fromContent) {
        this.fromContent = fromContent;
    }
}
