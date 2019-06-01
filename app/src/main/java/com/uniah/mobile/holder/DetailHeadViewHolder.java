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
import com.uniah.mobile.view.UniRadiusView;

import java.util.ArrayList;
import java.util.List;

public class DetailHeadViewHolder extends BaseViewHolder {

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

    public LinearLayout mComment;
    public ImageView mCommentImg;
    public TextView mCommentCount;

    public LinearLayout mLike;
    public ImageView mLikeImg;
    public TextView mLikeCount;

    public DetailHeadViewHolder(Context context, View convertView) {
        super(context, convertView);

        mHead = convertView.findViewById(R.id.user_info_title_head);
        mNick = convertView.findViewById(R.id.user_info_title_nick);
        mBadge = convertView.findViewById(R.id.user_info_title_badge);
        mTime = convertView.findViewById(R.id.user_info_title_time);
        mUni = convertView.findViewById(R.id.user_info_title_uni);
        mMore = convertView.findViewById(R.id.user_info_title_more);

        mContent = convertView.findViewById(R.id.feed_item_content);
        mSingleImg = convertView.findViewById(R.id.feed_item_img_single);
        mGridView = convertView.findViewById(R.id.feed_item_img_grid);

        mComment = convertView.findViewById(R.id.detail_head_left_btn);
        mCommentImg = convertView.findViewById(R.id.detail_head_left_btn_img);
        mCommentCount = convertView.findViewById(R.id.detail_head_left_btn_text);

        mLike = convertView.findViewById(R.id.detail_head_right_btn);
        mLikeImg = convertView.findViewById(R.id.detail_head_right_btn_img);
        mLikeCount = convertView.findViewById(R.id.detail_head_right_btn_text);
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
