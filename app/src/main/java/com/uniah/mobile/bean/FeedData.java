package com.uniah.mobile.bean;

import com.uniah.mobile.R;
import com.uniah.mobile.base.BaseData;
import com.uniah.mobile.util.UniDateHelper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FeedData extends BaseData implements Serializable {

    private static final long serialVersionUID = 7458065166731618893L;

    private int feedId;
    private int userId;
    private String head;
    private String nick;

    private String feedTime;
    private String feedContent;
    private int feedType;
    private Map<Integer, String> files;

    private int feedState;
    private String locationName;
    private String locationLongitude;
    private String locationLatitude;

    private int commentCount;
    private int likeCount;
    private boolean feedLike;

    private boolean hasHot;
    private String hotNick;
    private String hotContent;
    private int hotLikeCount;
    private boolean hotLike;
    private String hotTitle;

    public FeedData() {
    }

    @Override
    public int getLayoutId() {
        return R.layout.feed_item;
    }

    public int getFeedId() {
        return feedId;
    }

    public void setFeedId(int feedId) {
        this.feedId = feedId;
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

    public String getFeedTime() {
        return feedTime;
    }

    public void setFeedTime(String feedTime) {
        this.feedTime = feedTime;
    }

    public String smartTime() {
        try {
            return UniDateHelper.SmartTime(feedTime);
        } catch (Throwable e) {
            e.printStackTrace();
            return "";
        }
    }

    public String getFeedContent() {
        return feedContent;
    }

    public void setFeedContent(String feedContent) {
        this.feedContent = feedContent;
    }

    public int getFeedType() {
        return feedType;
    }

    public void setFeedType(int feedType) {
        this.feedType = feedType;
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

    public int getFeedState() {
        return feedState;
    }

    public void setFeedState(int feedState) {
        this.feedState = feedState;
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

    public boolean isFeedLike() {
        return feedLike;
    }

    public void setFeedLike(boolean feedLike) {
        this.feedLike = feedLike;
    }

    public boolean hasHot() {
        return hasHot;
    }

    public void setHasHot(boolean hasHot) {
        this.hasHot = hasHot;
    }

    public String getHotNick() {
        return hotNick;
    }

    public void setHotNick(String hotNick) {
        this.hotNick = hotNick;
    }

    public String getHotContent() {
        return hotContent;
    }

    public void setHotContent(String hotContent) {
        this.hotContent = hotContent;
    }


    public int getHotLikeCount() {
        return hotLikeCount;
    }

    public void setHotLikeCount(int hotLikeCount) {
        this.hotLikeCount = hotLikeCount;
    }

    public boolean isHotLike() {
        return hotLike;
    }

    public void setHotLike(boolean hotLike) {
        this.hotLike = hotLike;
    }

    public String getHotTitle() {
        return hotTitle;
    }

    public void setHotTitle(String hotTitle) {
        this.hotTitle = hotTitle;
    }
}
