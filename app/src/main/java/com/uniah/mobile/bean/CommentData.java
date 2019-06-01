package com.uniah.mobile.bean;

import android.content.Context;
import android.view.View;

import com.uniah.mobile.R;
import com.uniah.mobile.base.BaseData;
import com.uniah.mobile.base.BaseViewHolder;
import com.uniah.mobile.holder.CommentViewHolder;
import com.uniah.mobile.util.UniDateHelper;

import java.io.Serializable;

public class CommentData extends BaseData implements Serializable {

    private int commentId;
    private int userId;
    private String userHead;
    private String userNick;

    private String commentTime;
    private String commentContent;

    private int commentType;
    private int commentState;

    private String locationName;
    private String locationLongitude;
    private String locationLatitude;

    private int commentCount;

    private int likeCount;
    private boolean commentLike;

    public CommentData() {
    }

    @Override
    public BaseViewHolder getViewHolder(Context context, View convertView) {
        return new CommentViewHolder(context, convertView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.comment_item;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserHead() {
        return userHead;
    }

    public void setUserHead(String userHead) {
        this.userHead = userHead;
    }

    public String getUserNick() {
        return userNick;
    }

    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public int getCommentType() {
        return commentType;
    }

    public void setCommentType(int commentType) {
        this.commentType = commentType;
    }

    public int getCommentState() {
        return commentState;
    }

    public void setCommentState(int commentState) {
        this.commentState = commentState;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationLongitude() {
        return locationLongitude;
    }

    public void setLocationLongitude(String locationLongitude) {
        this.locationLongitude = locationLongitude;
    }

    public String getLocationLatitude() {
        return locationLatitude;
    }

    public void setLocationLatitude(String locationLatitude) {
        this.locationLatitude = locationLatitude;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public boolean isCommentLike() {
        return commentLike;
    }

    public void setCommentLike(boolean commentLike) {
        this.commentLike = commentLike;
    }

    public String smartTime() {
        try {
            return UniDateHelper.SmartTime(commentTime);
        } catch (Throwable e) {
            e.printStackTrace();
            return "";
        }
    }

}
