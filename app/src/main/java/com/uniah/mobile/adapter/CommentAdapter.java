package com.uniah.mobile.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.uniah.mobile.activity.ReplyActivity;
import com.uniah.mobile.base.BaseAdapter;
import com.uniah.mobile.base.BaseData;
import com.uniah.mobile.base.BaseViewHolder;
import com.uniah.mobile.bean.CardTitleData;
import com.uniah.mobile.bean.CommentData;
import com.uniah.mobile.bean.DetailHeadData;
import com.uniah.mobile.bean.FeedGridData;
import com.uniah.mobile.holder.CardTitleViewHolder;
import com.uniah.mobile.holder.CommentViewHolder;
import com.uniah.mobile.holder.DetailHeadViewHolder;
import com.uniah.mobile.util.UniImageHelper;
import com.uniah.mobile.util.UniTextHelper;

import java.util.ArrayList;
import java.util.List;

public class CommentAdapter extends BaseAdapter<BaseData> {

    private DetailHeadViewHolder mDetailHeadViewHolder;
    private CommentViewHolder mCommentViewHolder;

    private CardTitleViewHolder mCardTitleViewHolder;

    private DetailHeadData mDetailHeadData;
    private CommentData mCommentData;

    public CommentAdapter(Context context, List<BaseData> list) {
        super(context, list);
    }

    @Override
    public void convert(BaseViewHolder holder, int position, BaseData item) {
        setBottomMargin(holder, position);
        if (item instanceof DetailHeadData) {
            onBindDetailHead(holder, position, item);
        } else if (item instanceof CardTitleData) {
            onBindCardTitle(holder, position, item);
        } else if (item instanceof CommentData) {
            onBindComment(holder, position, item);
        }
    }

    private void onBindComment(BaseViewHolder holder, int position, BaseData item) {
        mCommentData = (CommentData) item;
        mCommentViewHolder = (CommentViewHolder) holder;


        UniImageHelper.displayImage(mContext, mCommentData.getUserHead(), mCommentViewHolder.mHead);
        mCommentViewHolder.mNick.setText(mCommentData.getUserNick());

        mCommentViewHolder.mLike.setOnClickListener(mCommentLikeClickListener);
        mCommentViewHolder.mLikeImg.setActivated(mCommentData.isCommentLike());
        mCommentViewHolder.mLikeCount.setText(String.valueOf(mCommentData.getLikeCount()));

        mCommentViewHolder.mContent.setText(mCommentData.getCommentContent());
        mCommentViewHolder.mContent.setOnClickListener(mCommentItemClickListener);

        mCommentViewHolder.mTime.setText(mCommentData.smartTime());
        mCommentViewHolder.mReply.setText(String.format("%s回复", mCommentData.getCommentCount() != 0 ? mCommentData.getCommentCount() : ""));
        mCommentViewHolder.mReply.setOnClickListener(mCommentItemClickListener);
    }

    private View.OnClickListener mCommentItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (mCommentData.getCommentType()) {
                case 1:
                    Intent intent = new Intent(mContext, ReplyActivity.class);
                    mContext.startActivity(intent);
                    break;
                case 2:
                    break;
            }
        }
    };

    private View.OnClickListener mCommentLikeClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mCommentData.setLikeCount(mCommentData.isCommentLike() ? mCommentData.getLikeCount() - 1 : mCommentData.getLikeCount() + 1);
            mCommentData.setCommentLike(!mCommentData.isCommentLike());
            mCommentViewHolder.mLikeImg.setActivated(mCommentData.isCommentLike());
            mCommentViewHolder.mLikeCount.setActivated(mCommentData.isCommentLike());
            mCommentViewHolder.mLikeCount.setText(String.valueOf(mCommentData.getLikeCount()));
        }
    };

    private void onBindCardTitle(BaseViewHolder holder, int position, BaseData item) {
        CardTitleData data = (CardTitleData) item;
        mCardTitleViewHolder = (CardTitleViewHolder) holder;

        mCardTitleViewHolder.mTitle.setText(data.getTitle());
        if (!UniTextHelper.isEmpty(data.getSubTitle())) {
            mCardTitleViewHolder.mSubTitle.setText(data.getSubTitle());
            mCardTitleViewHolder.mSubTitle.setVisibility(View.VISIBLE);
        } else {
            mCardTitleViewHolder.mSubTitle.setVisibility(View.GONE);
        }

        if (data.isShowMoreBtn()) {
            mCardTitleViewHolder.mMoreBtn.setOnClickListener(mCardTitleMoreClickListener);
            mCardTitleViewHolder.mMoreBtn.setVisibility(View.VISIBLE);
        } else {
            mCardTitleViewHolder.mMoreBtn.setVisibility(View.GONE);
        }

        if (data.isShowBackBtn()) {
            mCardTitleViewHolder.mBackBtn.setOnClickListener(mCardTitleBackClickListener);
            mCardTitleViewHolder.mBackBtn.setVisibility(View.VISIBLE);
        } else {
            mCardTitleViewHolder.mBackBtn.setVisibility(View.GONE);
        }

        mCardTitleViewHolder.mTitleItem.setOnClickListener(mCardTitleItemClickListener);
    }

    private View.OnClickListener mCardTitleItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(mContext, "click title", Toast.LENGTH_SHORT).show();
        }
    };

    private View.OnClickListener mCardTitleMoreClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(mContext, "click more", Toast.LENGTH_SHORT).show();
        }
    };

    private View.OnClickListener mCardTitleBackClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(mContext, "click back", Toast.LENGTH_SHORT).show();
        }
    };

    private void onBindDetailHead(BaseViewHolder holder, int position, BaseData item) {
        mDetailHeadViewHolder = (DetailHeadViewHolder) holder;
        mDetailHeadData = (DetailHeadData) item;

        UniImageHelper.displayImage(mContext, mDetailHeadData.getHead(), mDetailHeadViewHolder.mHead);

        mDetailHeadViewHolder.mNick.setText(mDetailHeadData.getNick());
        mDetailHeadViewHolder.mTime.setText(mDetailHeadData.smartTime());
        mDetailHeadViewHolder.mMore.setVisibility(View.VISIBLE);
        mDetailHeadViewHolder.mMore.setOnClickListener(mMoreClickListener);
        mDetailHeadViewHolder.mContent.setText(mDetailHeadData.getContent());

        mDetailHeadViewHolder.mComment.setOnClickListener(mCommentClickListener);

        mDetailHeadViewHolder.mLike.setOnClickListener(mLikeClickListener);
        mDetailHeadViewHolder.mLikeImg.setActivated(mDetailHeadData.isLike());
        mDetailHeadViewHolder.mLikeCount.setText(String.valueOf(mDetailHeadData.getLikeCount()));

        mDetailHeadViewHolder.imgSet(mContext, mDetailHeadData.getImgList());
    }

    private View.OnClickListener mMoreClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(mContext, "click more", Toast.LENGTH_SHORT).show();
        }
    };

    private View.OnClickListener mCommentClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(mContext, "click comment", Toast.LENGTH_SHORT).show();
        }
    };

    private View.OnClickListener mLikeClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mDetailHeadData.setLikeCount(mDetailHeadData.isLike() ? mDetailHeadData.getLikeCount() - 1 : mDetailHeadData.getLikeCount() + 1);
            mDetailHeadData.setLike(!mDetailHeadData.isLike());
            mDetailHeadViewHolder.mLikeImg.setActivated(mDetailHeadData.isLike());
            mDetailHeadViewHolder.mLikeCount.setActivated(mDetailHeadData.isLike());
            mDetailHeadViewHolder.mLikeCount.setText(String.valueOf(mDetailHeadData.getLikeCount()));
        }
    };
}
