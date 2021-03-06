package com.uniah.mobile.holder;

import android.content.Context;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uniah.mobile.R;
import com.uniah.mobile.adapter.FeedGridAdapter;
import com.uniah.mobile.alpha.UniAlphaImageButton;
import com.uniah.mobile.base.BaseData;
import com.uniah.mobile.base.BaseViewHolder;
import com.uniah.mobile.bean.FeedGridData;
import com.uniah.mobile.layout.UniFrameLayout;
import com.uniah.mobile.layout.UniLinearLayout;
import com.uniah.mobile.util.UniImageHelper;
import com.uniah.mobile.view.UniImgBox;
import com.uniah.mobile.view.UniRadiusView;

import java.util.ArrayList;
import java.util.List;

public class FeedViewHolder extends BaseViewHolder {

    public UniLinearLayout mItem;

    public UniRadiusView mHead;
    public TextView mNick;
    public UniAlphaImageButton mBadge;
    public TextView mTime;
    public TextView mUni;
    public UniFrameLayout mMore;

    public TextView mContent;

    public GridView mGridView;
    public UniRadiusView mSingleImg;

    public LinearLayout mHot;
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

    public FeedViewHolder(Context context, View convertView) {
        super(context, convertView);

        mItem = convertView.findViewById(R.id.feed_item);

        mHead = convertView.findViewById(R.id.user_info_title_head);
        mNick = convertView.findViewById(R.id.user_info_title_nick);
        mBadge = convertView.findViewById(R.id.user_info_title_badge);
        mTime = convertView.findViewById(R.id.user_info_title_time);
        mUni = convertView.findViewById(R.id.user_info_title_uni);
        mMore = convertView.findViewById(R.id.user_info_title_more);

        mContent = convertView.findViewById(R.id.feed_item_content);
        mSingleImg = convertView.findViewById(R.id.feed_item_img_single);
        mGridView = convertView.findViewById(R.id.feed_item_img_grid);

        mHot = convertView.findViewById(R.id.feed_item_hot);
        mHotTitle = convertView.findViewById(R.id.feed_item_hot_title);
        mHotLike = convertView.findViewById(R.id.feed_item_hot_like);
        mHotLikeCount = convertView.findViewById(R.id.feed_item_hot_like_count);
        mHotLikeImg = convertView.findViewById(R.id.feed_item_hot_like_img);
        mHotContent = convertView.findViewById(R.id.feed_item_hot_content);

        mComment = convertView.findViewById(R.id.feed_item_comment);
        mCommentImg = convertView.findViewById(R.id.feed_item_comment_img);
        mCommentCount = convertView.findViewById(R.id.feed_item_comment_count);

        mLike = convertView.findViewById(R.id.feed_item_like);
        mLikeImg = convertView.findViewById(R.id.feed_item_like_img);
        mLikeCount = convertView.findViewById(R.id.feed_item_like_count);
    }


    public void imgSet(Context context, List<String> imgList) {
        int size = imgList.size();
        int numColumns = 0;

        if (size == 2 || size == 4) {
            numColumns = 2;
        } else if (size > 1) {
            numColumns = 3;
        }

        this.mGridView.setVisibility(size > 1 ? View.VISIBLE : View.GONE);
        this.mSingleImg.setVisibility(size == 1 ? View.VISIBLE : View.GONE);

        if (size > 0) {
            if (numColumns == 0) {
                UniImageHelper.displayImage(context, imgList.get(0), this.mSingleImg);
            } else {
                FeedGridAdapter gridAdapter = new FeedGridAdapter(context, new ArrayList<BaseData>(), numColumns);
                for (String imgUrl : imgList) {
                    FeedGridData gridData = new FeedGridData();
                    gridData.setImgUrl(imgUrl);
                    gridAdapter.add(gridData);
                }
                gridAdapter.setImgList(imgList);

                this.mGridView.setNumColumns(numColumns);
                this.mGridView.setAdapter(gridAdapter);
            }
        }
    }

}
