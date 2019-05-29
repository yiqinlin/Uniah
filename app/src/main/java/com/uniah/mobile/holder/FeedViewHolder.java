package com.uniah.mobile.holder;

import android.widget.ImageView;
import android.widget.TextView;

import com.uniah.mobile.R;
import com.uniah.mobile.alpha.UniAlphaImageButton;
import com.uniah.mobile.base.BaseViewHolder;
import com.uniah.mobile.layout.UniFrameLayout;
import com.uniah.mobile.layout.UniLinearLayout;
import com.uniah.mobile.view.UniImgBox;
import com.uniah.mobile.view.UniRadiusView;

public class FeedViewHolder{

    public UniLinearLayout mItem;
    public UniRadiusView mHead;
    public TextView mNick;
    public UniAlphaImageButton mBadge;
    public TextView mTime;
    public TextView mUni;
    public UniFrameLayout mMore;

    public TextView mContent;
    public UniImgBox mImgBox;

    public UniLinearLayout mHot;
    public TextView mHotTitle;
    public UniFrameLayout mHotLike;
    public TextView mHotLikeCount;
    public ImageView mHotLikeImg;
    public TextView mHotContent;

    public UniFrameLayout mComment;
    public ImageView mCommentImg;
    public TextView mCommentCount;

    public UniFrameLayout mLike;
    public ImageView mLikeImg;
    public TextView mLikeCount;

    public FeedViewHolder(BaseViewHolder holder) {
        mItem = holder.findViewById(R.id.feed_item);

        mHead = holder.findViewById(R.id.user_info_title_head);
        mNick = holder.findViewById(R.id.user_info_title_nick);
        mBadge = holder.findViewById(R.id.user_info_title_badge);
        mTime = holder.findViewById(R.id.user_info_title_time);
        mUni = holder.findViewById(R.id.user_info_title_uni);
        mMore = holder.findViewById(R.id.user_info_title_more);

        mContent = holder.findViewById(R.id.feed_item_content);
        mImgBox = holder.findViewById(R.id.feed_item_img_box);

        mHot = holder.findViewById(R.id.feed_item_hot);
        mHotTitle = holder.findViewById(R.id.feed_item_hot_title);
        mHotLike = holder.findViewById(R.id.feed_item_hot_like);
        mHotLikeCount = holder.findViewById(R.id.feed_item_hot_like_count);
        mHotLikeImg = holder.findViewById(R.id.feed_item_hot_like_img);
        mHotContent = holder.findViewById(R.id.feed_item_hot_content);

        mComment = holder.findViewById(R.id.feed_item_comment);
        mCommentImg = holder.findViewById(R.id.feed_item_comment_img);
        mCommentCount = holder.findViewById(R.id.feed_item_comment_count);

        mLike = holder.findViewById(R.id.feed_item_like);
        mLikeImg = holder.findViewById(R.id.feed_item_like_img);
        mLikeCount = holder.findViewById(R.id.feed_item_like_count);
    }

}
