package com.uniah.mobile.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.uniah.mobile.activity.CommentActivity;
import com.uniah.mobile.base.BaseAdapter;
import com.uniah.mobile.base.BaseData;
import com.uniah.mobile.base.BaseViewHolder;
import com.uniah.mobile.bean.DataHomeData;
import com.uniah.mobile.bean.FeedData;
import com.uniah.mobile.dialog.UniListDialog;
import com.uniah.mobile.holder.DataHomeViewHolder;
import com.uniah.mobile.holder.FeedViewHolder;
import com.uniah.mobile.util.UniImageHelper;
import com.uniah.mobile.util.UniTextHelper;

import java.util.ArrayList;
import java.util.List;

public class UserHomeAdapter extends BaseAdapter<BaseData> {

    private Activity mActivity;
    private FeedViewHolder FeedViewHolder;
    private FeedData FeedData;

    public UserHomeAdapter(Context context, List<BaseData> list) {
        super(context, list);
        if (mContext instanceof Activity) {
            this.mActivity = (Activity) mContext;
        }
    }

    @Override
    public void convert(BaseViewHolder holder, int position, BaseData item) {
        setBottomMargin(holder, position);
        if (item instanceof DataHomeData) {
            onBindHomeHead(holder, position, item);
        } else if (item instanceof FeedData) {
            onBindFeed(holder, position, item);
        }
    }

    private void onBindFeed(BaseViewHolder holder, int position, BaseData item) {
        FeedViewHolder = (FeedViewHolder) holder;
        FeedData = (FeedData) item;

        UniImageHelper.displayImage(mContext, FeedData.getHead(), FeedViewHolder.mHead);
        FeedViewHolder.mItem.setOnClickListener(mFeedItemClickListener);
        FeedViewHolder.mNick.setText(FeedData.getNick());
        FeedViewHolder.mTime.setText(FeedData.smartTime());
        FeedViewHolder.mMore.setVisibility(View.VISIBLE);
        FeedViewHolder.mMore.setOnClickListener(mMoreClickListener);
        FeedViewHolder.mContent.setText(FeedData.getFeedContent());


        FeedViewHolder.mHot.setVisibility(FeedData.hasHot() ? View.VISIBLE : View.GONE);
        FeedViewHolder.mHotLike.setOnClickListener(mHotLikeClickListener);
        FeedViewHolder.mLikeImg.setActivated(FeedData.isHotLike());
        FeedViewHolder.mHotLikeCount.setText(String.valueOf(FeedData.getHotLikeCount()));
        FeedViewHolder.mHotTitle.setText(FeedData.getHotTitle());
        FeedViewHolder.mHotContent.setText(FeedData.getHotContent());

        FeedViewHolder.mComment.setOnClickListener(mCommentClickListener);
        FeedViewHolder.mCommentCount.setText(String.valueOf(FeedData.getCommentCount()));

        FeedViewHolder.mLike.setOnClickListener(mLikeClickListener);
        FeedViewHolder.mLikeImg.setActivated(FeedData.isFeedLike());
        FeedViewHolder.mLikeCount.setText(String.valueOf(FeedData.getLikeCount()));

        FeedViewHolder.imgSet(mContext, FeedData.getImgList());
    }


    private View.OnClickListener mFeedItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(mContext, CommentActivity.class);
            mContext.startActivity(intent);
        }
    };

    private View.OnClickListener mMoreClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            List<String> list = new ArrayList<>();
            list.add("举报");
            UniListDialog dialog = new UniListDialog.Builder(mContext)
                    .setChoiceItems(list.toArray(new String[0]), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(mContext, "已举报", Toast.LENGTH_SHORT).show();
                        }
                    }).setCancelable(true).show();
        }
    };

    private View.OnClickListener mHotLikeClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            FeedData.setHotLikeCount(FeedData.isHotLike() ? FeedData.getHotLikeCount() - 1 : FeedData.getHotLikeCount() + 1);
            FeedData.setHotLike(!FeedData.isHotLike());
            FeedViewHolder.mHotLikeImg.setActivated(FeedData.isHotLike());
            FeedViewHolder.mHotLikeCount.setActivated(FeedData.isHotLike());
            FeedViewHolder.mHotLikeCount.setText(String.valueOf(FeedData.getHotLikeCount()));
        }
    };

    private View.OnClickListener mCommentClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(mContext, CommentActivity.class);
            mContext.startActivity(intent);
        }
    };

    private View.OnClickListener mLikeClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            FeedData.setLikeCount(FeedData.isFeedLike() ? FeedData.getLikeCount() - 1 : FeedData.getLikeCount() + 1);
            FeedData.setFeedLike(!FeedData.isFeedLike());
            FeedViewHolder.mLikeImg.setActivated(FeedData.isFeedLike());
            FeedViewHolder.mLikeCount.setActivated(FeedData.isFeedLike());
            FeedViewHolder.mLikeCount.setText(String.valueOf(FeedData.getLikeCount()));
        }
    };

    private void onBindHomeHead(BaseViewHolder holder, int position, BaseData item) {
        DataHomeViewHolder viewHolder = (DataHomeViewHolder) holder;
        DataHomeData data = (DataHomeData) item;

        UniImageHelper.displayImage(mContext, data.getWall(), viewHolder.mWall);
        UniImageHelper.displayImage(mContext, data.getHead(), viewHolder.mHead);
        viewHolder.mNick.setText(data.getName());
        viewHolder.mSlogan.setText(data.getSlogan());
        viewHolder.mAccount.setText(String.valueOf("ID:" + data.getAccount()));
        if (!UniTextHelper.isEmpty(data.getFlagLeft())) {
            viewHolder.mFlagLeft.setVisibility(View.VISIBLE);
            viewHolder.mFlagLeft.setText(data.getFlagLeft());
        } else {
            viewHolder.mFlagLeft.setVisibility(View.GONE);
        }


        if (!UniTextHelper.isEmpty(data.getFlagRight())) {
            viewHolder.mFlagRight.setVisibility(View.VISIBLE);
            viewHolder.mFlagRight.setText(data.getFlagRight());
        } else {
            viewHolder.mFlagRight.setVisibility(View.GONE);
        }
        if (!UniTextHelper.isEmpty(data.getInfoFirst()) && !UniTextHelper.isEmpty(data.getInfoSecond()) && !UniTextHelper.isEmpty(data.getInfoThird())) {
            viewHolder.mInfoLayout.setVisibility(View.VISIBLE);
            viewHolder.mInfoFirst.setText(data.getInfoFirst());
            viewHolder.mInfoSecond.setText(data.getInfoSecond());
            viewHolder.mInfoThird.setText(data.getInfoThird());
        } else {
            viewHolder.mInfoLayout.setVisibility(View.GONE);
        }

    }


}
