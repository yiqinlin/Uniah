package com.uniah.mobile.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uniah.mobile.R;
import com.uniah.mobile.base.BaseViewHolder;
import com.uniah.mobile.layout.UniFrameLayout;
import com.uniah.mobile.view.UniRadiusView;

public class MineCardViewHolder extends BaseViewHolder {

    public LinearLayout mTitleItem;
    public TextView mTitle;
    public TextView mSubTitle;

    public UniFrameLayout mMoreBtn;
    public UniFrameLayout mBackBtn;

    public LinearLayout mFirst;
    public UniRadiusView mFirstBackground;
    public ImageView mFirstImg;
    public TextView mFirstText;
    public TextView mFirstCount;

    public LinearLayout mSecond;
    public UniRadiusView mSecondBackground;
    public ImageView mSecondImg;
    public TextView mSecondText;
    public TextView mSecondCount;

    public LinearLayout mThird;
    public UniRadiusView mThirdBackground;
    public ImageView mThirdImg;
    public TextView mThirdText;
    public TextView mThirdCount;

    public LinearLayout mFourth;
    public UniRadiusView mFourthBackground;
    public ImageView mFourthImg;
    public TextView mFourthText;
    public TextView mFourthCount;

    public MineCardViewHolder(Context context, View convertView) {
        super(context, convertView);

        mTitleItem = convertView.findViewById(R.id.card_title_item);
        mTitle = convertView.findViewById(R.id.card_title_text);
        mSubTitle = convertView.findViewById(R.id.card_title_sub_title);
        mMoreBtn = convertView.findViewById(R.id.card_title_btn_layout);
        mBackBtn = convertView.findViewById(R.id.card_title_btn_sub_layout);

        mFirst = convertView.findViewById(R.id.card_part_four_first);
        mFirstBackground = convertView.findViewById(R.id.card_part_four_first_img_bg);
        mFirstImg = convertView.findViewById(R.id.card_part_four_first_img);
        mFirstText = convertView.findViewById(R.id.card_part_four_first_text);
        mFirstCount = convertView.findViewById(R.id.card_part_four_first_count);

        mSecond = convertView.findViewById(R.id.card_part_four_second);
        mSecondBackground = convertView.findViewById(R.id.card_part_four_second_img_bg);
        mSecondImg = convertView.findViewById(R.id.card_part_four_second_img);
        mSecondText = convertView.findViewById(R.id.card_part_four_second_text);
        mSecondCount = convertView.findViewById(R.id.card_part_four_second_count);

        mThird = convertView.findViewById(R.id.card_part_four_third);
        mThirdBackground = convertView.findViewById(R.id.card_part_four_third_img_bg);
        mThirdImg = convertView.findViewById(R.id.card_part_four_third_img);
        mThirdText = convertView.findViewById(R.id.card_part_four_third_text);
        mThirdCount = convertView.findViewById(R.id.card_part_four_third_count);

        mFourth = convertView.findViewById(R.id.card_part_four_fourth);
        mFourthBackground = convertView.findViewById(R.id.card_part_four_fourth_img_bg);
        mFourthImg = convertView.findViewById(R.id.card_part_four_fourth_img);
        mFourthText = convertView.findViewById(R.id.card_part_four_fourth_text);
        mFourthCount = convertView.findViewById(R.id.card_part_four_fourth_count);
    }
}
