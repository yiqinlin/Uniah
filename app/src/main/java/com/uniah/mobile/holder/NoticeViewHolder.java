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

public class NoticeViewHolder extends BaseViewHolder {

    public UniLinearLayout mItem;

    public UniRadiusView mHead;
    public TextView mNick;
    public UniAlphaImageButton mBadge;
    public TextView mTime;
    public TextView mUni;
    public UniFrameLayout mMore;

    public TextView mContent;

    public LinearLayout mFrom;
    public UniRadiusView mFromThumb;
    public TextView mFromContent;

    public NoticeViewHolder(Context context, View convertView) {
        super(context, convertView);

        mItem = convertView.findViewById(R.id.notice_item);

        mHead = convertView.findViewById(R.id.user_info_title_head);
        mNick = convertView.findViewById(R.id.user_info_title_nick);
        mBadge = convertView.findViewById(R.id.user_info_title_badge);
        mTime = convertView.findViewById(R.id.user_info_title_time);
        mUni = convertView.findViewById(R.id.user_info_title_uni);
        mMore = convertView.findViewById(R.id.user_info_title_more);

        mContent = convertView.findViewById(R.id.notice_item_content);

        mFrom = convertView.findViewById(R.id.notice_item_from);
        mFromThumb = convertView.findViewById(R.id.notice_item_from_thumb);
        mFromContent = convertView.findViewById(R.id.notice_item_from_content);
    }
}
