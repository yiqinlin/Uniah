package com.uniah.mobile.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.uniah.mobile.R;
import com.uniah.mobile.base.BaseAdapter;
import com.uniah.mobile.base.BaseData;
import com.uniah.mobile.base.BaseViewHolder;
import com.uniah.mobile.bean.FeedData;
import com.uniah.mobile.bean.FeedGridData;
import com.uniah.mobile.holder.FeedViewHolder;
import com.uniah.mobile.util.UniImageHelper;

import java.util.ArrayList;
import java.util.List;

public class FeedAdapter extends BaseAdapter<BaseData> {

    private FeedViewHolder viewHolder;
    private FeedData data;
    private int pos = -1;

    public FeedAdapter(Context context, List<BaseData> list) {
        super(context, list);
    }

    @Override
    public void convert(BaseViewHolder holder, int position, BaseData item) {
        if (item instanceof FeedData) {
            viewHolder = (FeedViewHolder) holder;
            pos = position;
            data = (FeedData) item;

            UniImageHelper.displayImage(mContext, data.getHead(), viewHolder.mHead);

            viewHolder.mNick.setText(data.getNick());
            viewHolder.mTime.setText(data.smartTime());
            viewHolder.mMore.setVisibility(View.VISIBLE);
            viewHolder.mMore.setOnClickListener(mMoreClickListener);
            viewHolder.mContent.setText(data.getFeedContent());

            imgSet();

            viewHolder.mHot.setVisibility(data.hasHot() ? View.VISIBLE : View.GONE);
            viewHolder.mHotLike.setOnClickListener(mHotLikeClickListener);
            viewHolder.mLikeImg.setActivated(data.isHotLike());
            viewHolder.mHotLikeCount.setText(String.valueOf(data.getHotLikeCount()));
            viewHolder.mHotTitle.setText(data.getHotTitle());
            viewHolder.mHotContent.setText(data.getHotContent());

            viewHolder.mComment.setOnClickListener(mCommentClickListener);
            viewHolder.mCommentCount.setText(String.valueOf(data.getCommentCount()));

            viewHolder.mLike.setOnClickListener(mLikeClickListener);
            viewHolder.mLikeImg.setActivated(data.isFeedLike());
            viewHolder.mLikeCount.setText(String.valueOf(data.getLikeCount()));

        }
    }


    private void imgSet() {
        int size = data.getImgList().size();
        int numColumns = 0;

        if (size == 2 || size == 4) {
            numColumns = 2;
        } else if (size > 1) {
            numColumns = 3;
        }

        viewHolder.mGridView.setVisibility(numColumns == 0 ? View.GONE : View.VISIBLE);
        viewHolder.mSingleImg.setVisibility(numColumns == 0 ? View.VISIBLE : View.GONE);

        if (numColumns == 0) {
            UniImageHelper.displayImage(mContext, data.getImgList().get(0), viewHolder.mSingleImg);
        } else {
            FeedGridAdapter gridAdapter = new FeedGridAdapter(mContext, new ArrayList<BaseData>(), numColumns);
            for (String imgUrl : data.getImgList()) {
                FeedGridData gridData = new FeedGridData();
                gridData.setImgUrl(imgUrl);
                gridAdapter.add(gridData);
            }
            gridAdapter.setImgList(data.getImgList());


//            Transferee transferee = Transferee.getDefault(mContext);
//            TransferConfig config = TransferConfig.build()
//                    .setSourceImageList(data.getImgList())
//                    .setProgressIndicator(new ProgressPieIndicator())
//                    .setIndexIndicator(new NumberIndexIndicator())
//                    .setJustLoadHitImage(true)
//                    .setOnLongClickListener(new Transferee.OnTransfereeLongClickListener() {
//                        @Override
//                        public void onLongClick(ImageView imageView, int pos) {
//                            //saveImageByUniversal(imageView);
//                        }
//                    })
//                    .bindListView(viewHolder.mGridView, R.id.feed_grid_img);
//
//            gridAdapter.setTransferee(transferee);
//            gridAdapter.setConfig(config);

            viewHolder.mGridView.setNumColumns(numColumns);
            viewHolder.mGridView.setAdapter(gridAdapter);
        }
    }

    private View.OnClickListener mMoreClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(mContext, "click more", Toast.LENGTH_SHORT).show();
        }
    };

    private View.OnClickListener mHotLikeClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            data.setHotLikeCount(data.isHotLike() ? data.getHotLikeCount() - 1 : data.getHotLikeCount() + 1);
            data.setHotLike(!data.isHotLike());
            viewHolder.mHotLikeImg.setActivated(data.isHotLike());
            viewHolder.mHotLikeCount.setActivated(data.isHotLike());
            viewHolder.mHotLikeCount.setText(String.valueOf(data.getHotLikeCount()));
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
            data.setLikeCount(data.isFeedLike() ? data.getLikeCount() - 1 : data.getLikeCount() + 1);
            data.setFeedLike(!data.isFeedLike());
            viewHolder.mLikeImg.setActivated(data.isFeedLike());
            viewHolder.mLikeCount.setActivated(data.isFeedLike());
            viewHolder.mLikeCount.setText(String.valueOf(data.getLikeCount()));
        }
    };
}
