package com.uniah.mobile.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uniah.mobile.R;
import com.uniah.mobile.base.BaseViewHolder;
import com.uniah.mobile.layout.UniFrameLayout;
import com.uniah.mobile.view.UniAnimImageView;
import com.uniah.mobile.view.UniRadiusView;

public class ChatViewHolder extends BaseViewHolder {

    public UniRadiusView mHead;

    public TextView mText;

    public UniRadiusView mImg;

    public UniAnimImageView mAnim;


    public ChatViewHolder(Context context, View convertView) {
        super(context, convertView);

        mHead = convertView.findViewById(R.id.chat_text_item_head);
        mText = convertView.findViewById(R.id.chat_text_item_msg);
        mImg = convertView.findViewById(R.id.chat_text_item_img);
        mAnim = convertView.findViewById(R.id.chat_text_item_anim);
    }
}
