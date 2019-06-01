package com.uniah.mobile.holder;

import android.content.Context;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uniah.mobile.R;
import com.uniah.mobile.alpha.UniAlphaImageButton;
import com.uniah.mobile.base.BaseViewHolder;
import com.uniah.mobile.layout.UniFrameLayout;
import com.uniah.mobile.layout.UniLinearLayout;
import com.uniah.mobile.view.UniRadiusView;

public class CommentViewHolder extends BaseViewHolder {

    public LinearLayout mItem;

    public UniRadiusView mHead;
    public TextView mNick;

    public UniFrameLayout mLike;
    public ImageView mLikeImg;
    public TextView mLikeCount;

    public TextView mContent;

    public TextView mTime;
    public TextView mReply;



    public CommentViewHolder(Context context, View convertView) {
        super(context, convertView);

        mItem = convertView.findViewById(R.id.comment_item);

        mHead = convertView.findViewById(R.id.user_info_title_head);
        mNick = convertView.findViewById(R.id.user_info_title_nick);

        mLike = convertView.findViewById(R.id.user_info_title_like);
        mLikeImg = convertView.findViewById(R.id.user_info_title_like_img);
        mLikeCount = convertView.findViewById(R.id.user_info_title_like_count);

        mContent = convertView.findViewById(R.id.comment_item_content);

        mTime = convertView.findViewById(R.id.user_info_title_time);
        mReply = convertView.findViewById(R.id.user_info_title_reply_btn);
    }

}
