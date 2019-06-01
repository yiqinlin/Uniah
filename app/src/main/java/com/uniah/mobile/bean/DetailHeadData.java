package com.uniah.mobile.bean;

import android.content.Context;
import android.view.View;

import com.uniah.mobile.R;
import com.uniah.mobile.base.BaseData;
import com.uniah.mobile.base.BaseViewHolder;
import com.uniah.mobile.holder.DetailHeadViewHolder;
import com.uniah.mobile.util.UniDateHelper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DetailHeadData extends BaseData implements Serializable {

    private int contentId;
    private int userId;
    private String head;
    private String nick;

    private String time;
    private String content;

    private int contentType;
    private int contentState;

    private Map<Integer, String> files;

    private String locationName;
    private String locationLongitude;
    private String locationLatitude;

    private boolean isLike;
    private int likeCount;
    private boolean isFollow;

    public DetailHeadData() {
    }

    @Override
    public BaseViewHolder getViewHolder(Context context, View convertView) {
        return new DetailHeadViewHolder(context, convertView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.feed_detail_head_item;
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

    public String smartTime() {
        try {
            return UniDateHelper.SmartTime(time);
        } catch (Throwable e) {
            e.printStackTrace();
            return "";
        }
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getContentType() {
        return contentType;
    }

    public void setContentType(int contentType) {
        this.contentType = contentType;
    }

    public Map<Integer, String> getFiles() {
        return files;
    }

    public List<String> getImgList() {
        int index = 0;
        List<String> imgList = new ArrayList<>();
        while (files.containsKey(index)) {
            imgList.add(files.get(index));
            index++;
        }
        return imgList;
    }

    public void setFiles(Map<Integer, String> files) {
        this.files = files;
    }

    public int getContentState() {
        return contentState;
    }

    public void setContentState(int contentState) {
        this.contentState = contentState;
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

    public boolean isFollow() {
        return isFollow;
    }

    public void setFollow(boolean follow) {
        isFollow = follow;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public boolean isLike() {
        return isLike;
    }

    public void setLike(boolean like) {
        this.isLike = like;
    }

}
